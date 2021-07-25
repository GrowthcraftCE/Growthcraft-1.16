package growthcraft.cellar.client.container;

import growthcraft.cellar.common.tileentity.RoasterTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarContainers;
import growthcraft.lib.util.FunctionalIntReferenceHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Objects;

public class RoasterContainer extends Container {

    private final RoasterTileEntity roasterTileEntity;
    private final IWorldPosCallable worldPosCallable;

    private final FunctionalIntReferenceHolder currentProcessingTime;
    private final FunctionalIntReferenceHolder maxProcessingTime;

    public RoasterContainer(final int windowID, final PlayerInventory playerInventory, final RoasterTileEntity tileEntity) {
        super(GrowthcraftCellarContainers.roaster_container.get(), windowID);

        this.roasterTileEntity = tileEntity;
        this.worldPosCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        int index = 0;
        int itemSlotWidth = 18;

        // Input Slot
        this.addSlot(new Slot(
                this.roasterTileEntity,
                index, 54, 42
        ));
        index++;

        // Redstone Input Slot
        this.addSlot(new Slot(
                this.roasterTileEntity,
                index, 80, 25
        ));
        index++;

        // Output Slot
        this.addSlot(
                new Slot(
                        this.roasterTileEntity,
                        index, 106, 42
                )
        );
        index++;

        /* Hotbar Inventory Slots */
        int hotbarBaseY = 142;
        int hotbarBaseX = 8;

        for (int column = 0; column < 9; column++) {
            Slot slot = new Slot(
                    playerInventory,
                    column,
                    hotbarBaseX + (column * itemSlotWidth),
                    hotbarBaseY);

            this.addSlot(slot);
            index++;
        }

        /* Player Inventory Slots */
        int playerInvBaseY = 84;
        int playerInvBaseX = 8;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                int slotIndex = 9 + (row * 9) + column;

                Slot slot = new Slot(
                        playerInventory,
                        slotIndex,
                        playerInvBaseX + (column * itemSlotWidth),
                        playerInvBaseY + (row * itemSlotWidth));

                this.addSlot(slot);
            }
        }

        this.trackInt(currentProcessingTime = new FunctionalIntReferenceHolder(
                this.roasterTileEntity::getCurrentProcessingTime,
                this.roasterTileEntity::setCurrentProcessingTime
        ));

        this.trackInt(maxProcessingTime = new FunctionalIntReferenceHolder(
                this.roasterTileEntity::getMaxProcessingTime,
                this.roasterTileEntity::setMaxProcessingTime
        ));

    }

    public RoasterContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer packetBuffer) {
        this(windowID, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    private static RoasterTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        Objects.requireNonNull(playerInventory, "Player inventory cannot be null!");
        Objects.requireNonNull(packetBuffer, "Packet buffer cannot be null!");

        final TileEntity tileEntity = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileEntity instanceof RoasterTileEntity) {
            return (RoasterTileEntity) tileEntity;
        }

        throw new IllegalStateException("RoasterContainer found invalid TileEntity: " + tileEntity);

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.roasterTileEntity.canOpen(playerIn);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            returnStack = slotStack.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }

    @OnlyIn(Dist.CLIENT)
    public int getProcessingTimeScaled(int size) {
        float scaledSize = 0;

        // This section was very problematic so we have debug breakpoints in case it breaks again.
        if (this.currentProcessingTime.get() != 0 && this.maxProcessingTime.get() != 0) {
            int a = this.currentProcessingTime.get();
            int b = this.maxProcessingTime.get();
            scaledSize = ((float) this.currentProcessingTime.get() / this.maxProcessingTime.get()) * size;
        }

        return (int) scaledSize;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isHeated() {
        return this.roasterTileEntity.isHeated();
    }

}

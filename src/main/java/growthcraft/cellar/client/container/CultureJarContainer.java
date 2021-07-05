package growthcraft.cellar.client.container;

import growthcraft.cellar.common.tileentity.CultureJarTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
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
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CultureJarContainer extends Container {

    private final CultureJarTileEntity cultureJarTileEntity;
    private final IWorldPosCallable worldPosCallable;

    private final FunctionalIntReferenceHolder currentProcessingTime;

    public CultureJarContainer(final int windowID, final PlayerInventory playerInventory, final CultureJarTileEntity tileEntity) {
        super(GrowthcraftCellarContainers.culture_jar_container.get(), windowID);

        this.cultureJarTileEntity = tileEntity;
        this.worldPosCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        int index = 0;
        int itemSlotWidth = 18;

        // Input Slot
        this.addSlot(new SlotItemHandler(
                cultureJarTileEntity.getInventory(),
                index, 80, 35
        ));
        index++;

        SlotItemHandler fluidInputSlot;

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
                this.cultureJarTileEntity::getCurrentProcessingTicks,
                this.cultureJarTileEntity::setCurrentProcessingTicks
        ));
    }

    public CultureJarContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer packetBuffer) {
        this(windowID, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    private static CultureJarTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        Objects.requireNonNull(playerInventory, "Player inventory cannot be null!");
        Objects.requireNonNull(packetBuffer, "Packet buffer cannot be null!");

        final TileEntity tileEntity = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileEntity instanceof CultureJarTileEntity) {
            return (CultureJarTileEntity) tileEntity;
        }

        throw new IllegalStateException("CultureJarContainer found invalid TileEntity: " + tileEntity);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(worldPosCallable, playerIn, GrowthcraftCellarBlocks.culture_jar.get());
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
        return this.currentProcessingTime.get() != 0 && this.cultureJarTileEntity.getMaxProcessingTime() != 0
                ? this.currentProcessingTime.get() * size / this.cultureJarTileEntity.getMaxProcessingTime()
                : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isHeated() {
        return this.cultureJarTileEntity.isHeated();
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getTileEntityFluidTank(int slot) {
        return this.cultureJarTileEntity.getInputFluidTank(0);
    }

}

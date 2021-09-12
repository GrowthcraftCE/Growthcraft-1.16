package growthcraft.cellar.client.container;

import growthcraft.cellar.common.tileentity.FruitPressTileEntity;
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

public class FruitPressContainer extends Container {

    private final FruitPressTileEntity fruitPressTileEntity;
    private final IWorldPosCallable worldPosCallable;

    private final FunctionalIntReferenceHolder currentProcessingTime;

    public FruitPressContainer(final int windowID, final PlayerInventory playerInventory, final FruitPressTileEntity tileEntity) {
        super(GrowthcraftCellarContainers.fruit_press_container.get(), windowID);

        this.fruitPressTileEntity = tileEntity;
        this.worldPosCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        int index = 0;
        int itemSlotWidth = 18;

        // Input Slot
        this.addSlot(new SlotItemHandler(
                fruitPressTileEntity.getInventory(),
                index, 52, 53
        ));
        index++;

        SlotItemHandler fluidOutputSlot;

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

        currentProcessingTime = new FunctionalIntReferenceHolder(
                this.fruitPressTileEntity::getCurrentProcessingTime,
                this.fruitPressTileEntity::setCurrentProcessingTime
        );

        this.trackInt(currentProcessingTime);

    }

    public FruitPressContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer packetBuffer) {
        this(windowID, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    private static FruitPressTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        Objects.requireNonNull(playerInventory, "Player inventory cannot be null!");
        Objects.requireNonNull(packetBuffer, "Packet buffer cannot be null!");

        final TileEntity tileEntity = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileEntity instanceof FruitPressTileEntity) {
            return (FruitPressTileEntity) tileEntity;
        }

        throw new IllegalStateException("CultureJarContainer found invalid TileEntity: " + tileEntity);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(worldPosCallable, playerIn, GrowthcraftCellarBlocks.FRUIT_PRESS.get());
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
        return this.currentProcessingTime.get() != 0 && this.fruitPressTileEntity.getMaxProcessingTime() != 0
                ? this.currentProcessingTime.get() * size / this.fruitPressTileEntity.getMaxProcessingTime()
                : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getTileEntityFluidTank(int slot) {
        return this.fruitPressTileEntity.getFluidTank(0);
    }

}

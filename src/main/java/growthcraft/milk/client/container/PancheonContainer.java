package growthcraft.milk.client.container;

import growthcraft.lib.util.FunctionalIntReferenceHolder;
import growthcraft.milk.common.tileentity.PancheonTileEntity;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkContainers;
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

import javax.annotation.Nonnull;
import java.util.Objects;

public class PancheonContainer extends Container {

    private final PancheonTileEntity tileEntity;
    public FunctionalIntReferenceHolder currentProcessingTime;
    private final IWorldPosCallable canInteractWithCallable;

    protected PancheonContainer(final int windowID, final PlayerInventory playerInventory, final PancheonTileEntity tileEntity) {
        super(GrowthcraftMilkContainers.PANCHEON_CONTAINTER.get(), windowID);

        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        int index = 0;
        int slotWidth = 18;

        /* Hotbar Inventory Slots */
        int hotbarBaseY = 142;
        int hotbarBaseX = 8;

        for (int column = 0; column < 9; column++) {
            Slot slot = new Slot(
                    playerInventory,
                    column,
                    hotbarBaseX + (column * slotWidth),
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
                        playerInvBaseX + (column * slotWidth),
                        playerInvBaseY + (row * slotWidth));

                this.addSlot(slot);
            }
        }

        this.trackInt(currentProcessingTime = new FunctionalIntReferenceHolder(
           this.tileEntity::getCurrentProcessingTime, this.tileEntity::setCurrentProcessingTime
        ));
    }

    public PancheonContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowID, playerInventory, getTileEntity(playerInventory, data));
    }

    private static PancheonTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player inventory cannot be null!");
        Objects.requireNonNull(data, "Packet buffer cannot be null!");

        final TileEntity tileEntity = playerInventory.player.world.getTileEntity(data.readBlockPos());

        if (tileEntity instanceof PancheonTileEntity) {
            return (PancheonTileEntity) tileEntity;
        }

        throw new IllegalStateException("TileEntity is not correct " + tileEntity);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, GrowthcraftMilkBlocks.PANCHEON.get());
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
    public int getProgressionScaled(int size) {
        return this.currentProcessingTime.get() != 0 && this.tileEntity.getMaxProcessingTime != 0
                ? this.currentProcessingTime.get() * size / this.tileEntity.getMaxProcessingTime
                : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getInputFluidTank(int id) {
        return this.tileEntity.getFluidTank(id);
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getOutputFluidTank(int id) {
        return this.tileEntity.getFluidTank(id);
    }

}

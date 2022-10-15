package growthcraft.milk.client.container;

import growthcraft.lib.common.handler.NonInteractiveSlotItemHandler;
import growthcraft.lib.util.FunctionalIntReferenceHolder;
import growthcraft.milk.common.tileentity.MixingVatTileEntity;
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
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class MixingVatContainer extends Container {

    private final MixingVatTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    public FunctionalIntReferenceHolder currentProcessingTime;

    public MixingVatContainer(final int windowID, final PlayerInventory playerInventory, final MixingVatTileEntity tileEntity) {
        super(GrowthcraftMilkContainers.MIXING_VAT_CONTAINER.get(), windowID);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(this.tileEntity.getWorld(), this.tileEntity.getPos());
        this.currentProcessingTime = new FunctionalIntReferenceHolder(
                this.tileEntity::getProcessingTime, this.tileEntity::setProcessingTime
        );

        int index = 0;
        int slotWidth = 18;

        /* Input Slots = 3 */
        this.addSlot(new SlotItemHandler(
                tileEntity.getInventory(), index, 71, 18
        ));
        index++;
        this.addSlot(new SlotItemHandler(
                tileEntity.getInventory(), index, 71, 36
        ));
        index++;
        this.addSlot(new SlotItemHandler(
                tileEntity.getInventory(), index, 71, 54
        ));
        index++;

        /* Output Slots = 1 */
        this.addSlot(new NonInteractiveSlotItemHandler(
                tileEntity.getInventory(), index, 124, 18
        ));
        index++;


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

        this.trackInt(currentProcessingTime);
    }

    public MixingVatContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowID, playerInventory, getTileEntity(playerInventory, data));
    }

    private static MixingVatTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player inventory cannot be null!");
        Objects.requireNonNull(data, "Packet buffer cannot be null!");

        final TileEntity tileEntity = playerInventory.player.world.getTileEntity(data.readBlockPos());

        if (tileEntity instanceof MixingVatTileEntity) {
            return (MixingVatTileEntity) tileEntity;
        }

        throw new IllegalStateException("TileEntity is not correct " + tileEntity);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, GrowthcraftMilkBlocks.MIXING_VAT.get());
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
        return this.currentProcessingTime.get() != 0 && this.tileEntity.getMaxProcessingTime() != 0
                ? this.currentProcessingTime.get() * size / this.tileEntity.getMaxProcessingTime()
                : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.tileEntity.isHeated();
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getInputFluidTank() {
        return this.tileEntity.getFluidTank(0);
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getReagentFluidTank() {
        return this.tileEntity.getFluidTank(2);
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getOutputFluidTank() {
        return this.tileEntity.getFluidTank(1);
    }

}

package growthcraft.cellar.client.container;

import growthcraft.cellar.common.tileentity.BrewKettleTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.init.GrowthcraftCellarContainers;
import growthcraft.lib.common.handler.OutputSlotItemHandler;
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

public class BrewKettleContainer extends Container {

    private final BrewKettleTileEntity brewKettleTileEntity;
    public FunctionalIntReferenceHolder currentProcessingTime;
    private final IWorldPosCallable canInteractWithCallable;

    // Server Side Constructor
    public BrewKettleContainer(final int windowID, final PlayerInventory playerInventory, final BrewKettleTileEntity tileEntity) {
        super(GrowthcraftCellarContainers.brew_kettle_container.get(), windowID);

        this.brewKettleTileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        int index = 0;
        int slotWidth = 18;

        /* Input Slot */
        this.addSlot(new SlotItemHandler(
                brewKettleTileEntity.getInventory(),
                index, 80, 35)
        );
        index++;

        SlotItemHandler fluidInputSlot;

        /* Output Slot */
        this.addSlot(new OutputSlotItemHandler(
                brewKettleTileEntity.getInventory(),
                index,
                141, 17)
        );
        index++;

        SlotItemHandler fluidOutputSlot;

        /* Lid Slot */
        this.addSlot(new SlotItemHandler(
                brewKettleTileEntity.getInventory(),
                index,
                19, 17)
        );
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

        this.trackInt(currentProcessingTime = new FunctionalIntReferenceHolder(
                this.brewKettleTileEntity::getCurrentSmeltTime,
                this.brewKettleTileEntity::setCurrentSmeltTime
        ));
    }

    // Client Side Constructor
    public BrewKettleContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowID, playerInventory, getTileEntity(playerInventory, data));
    }

    private static BrewKettleTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player inventory cannot be null!");
        Objects.requireNonNull(data, "Packet buffer cannot be null!");

        final TileEntity tileEntity = playerInventory.player.world.getTileEntity(data.readBlockPos());

        if (tileEntity instanceof BrewKettleTileEntity) {
            return (BrewKettleTileEntity) tileEntity;
        }

        throw new IllegalStateException("TileEntity is not correct " + tileEntity);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, GrowthcraftCellarBlocks.brew_kettle.get());
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
    public int getSmeltProgressionScaled(int size) {
        boolean isProcessing = this.currentProcessingTime.get() != 0 && this.brewKettleTileEntity.maxProcessingTime != 0;

        int scaledProcessing = this.currentProcessingTime.get() * size / this.brewKettleTileEntity.maxProcessingTime;

        return isProcessing ? scaledProcessing : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.brewKettleTileEntity.isHeated();
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getInputFluidTank() {
        return this.brewKettleTileEntity.getFluidTank(0);
    }

    @OnlyIn(Dist.CLIENT)
    public FluidTank getOutputFluidTank() {
        return this.brewKettleTileEntity.getFluidTank(1);
    }

}
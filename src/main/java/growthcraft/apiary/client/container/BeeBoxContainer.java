package growthcraft.apiary.client.container;

import growthcraft.apiary.client.container.slot.BeeBoxInputSlot;
import growthcraft.apiary.client.container.slot.BeeBoxOutputSlot;
import growthcraft.apiary.common.tileentity.BeeBoxTileEntity;
import growthcraft.apiary.init.GrowthcraftApiaryContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BeeBoxContainer extends Container {

    private final BeeBoxTileEntity beeBoxTileEntity;
    private final IWorldPosCallable worldPosCallable;

    public BeeBoxContainer(final int windowID, final PlayerInventory playerInventory, final BeeBoxTileEntity tileEntity) {
        super(GrowthcraftApiaryContainers.BEE_BOX_CONTAINER.get(), windowID);

        this.beeBoxTileEntity = tileEntity;
        this.worldPosCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        int index = 0;
        int itemSlotWidth = 18;

        // Input Slot
        this.addSlot(new BeeBoxInputSlot(this.beeBoxTileEntity, index, 80, 18));
        index++;

        // Output Slots
        int outputSlotBaseY = 50;
        int outputSlotBaseX = 8;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                int slotIndex = 9 + (row * 9) + column;

                BeeBoxOutputSlot slot = new BeeBoxOutputSlot(this.beeBoxTileEntity,
                        index,
                        outputSlotBaseX + (column * itemSlotWidth),
                        outputSlotBaseY + (row * itemSlotWidth));
                this.addSlot(slot);
                index++;
            }
        }

        // Hotbar Inventory Slots
        int hotbarBaseY = 176;
        int hotbarBaseX = 8;

        for (int column = 0; column < 9; column++) {
            Slot slot = new Slot(playerInventory,
                    column, hotbarBaseX + (column * itemSlotWidth), hotbarBaseY);
            this.addSlot(slot);
            index++;
        }

        // Player Inventory Slots
        int playerInvBaseY = 118;
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

    }

    public BeeBoxContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer packetBuffer) {
        this(windowID, playerInventory, getTileEntity(playerInventory, packetBuffer));
    }

    private static BeeBoxTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        Objects.requireNonNull(playerInventory, "Player inventory cannot be null!");
        Objects.requireNonNull(packetBuffer, "Packet buffer cannot be null!");

        final TileEntity tileEntity = playerInventory.player.world.getTileEntity(packetBuffer.readBlockPos());
        if (tileEntity instanceof BeeBoxTileEntity) {
            return (BeeBoxTileEntity) tileEntity;
        }

        throw new IllegalStateException("BeeBoxContainer found invalid TileEntity: " + tileEntity);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.beeBoxTileEntity.canOpen(playerIn);
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
}

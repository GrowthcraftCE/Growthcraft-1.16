package growthcraft.trapper.lib.common.inventory;

import growthcraft.trapper.init.GrowthcraftTrapperContainers;
import growthcraft.trapper.lib.common.tileentity.TileEntityFishtrap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class ContainerFishtrap extends Container {

    public final TileEntityFishtrap tileEntityFishtrap;
    private final IWorldPosCallable canInteractWithCallable;

    public ContainerFishtrap(final int windowId, final PlayerInventory playerInventory, final TileEntityFishtrap tileEntityFishtrap) {
        super(GrowthcraftTrapperContainers.fishtrapContainer.get(), windowId);
        this.tileEntityFishtrap = tileEntityFishtrap;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntityFishtrap.getWorld(), tileEntityFishtrap.getPos());

        int index = 0;
        int slotSizePlus2 = 18;

        // Input Slot
        this.addSlot(new Slot(tileEntityFishtrap, index, 17, 20));
        index++;

        // OutPut Slots
        int startX = 44;
        int startY = 20;
        for (int i = 0; i < 6; i++) {
            Slot slot = new Slot(tileEntityFishtrap, index, startX + (i * slotSizePlus2), startY);
            this.addSlot(slot);
            index++;
        }

        // Main Player Inventory
        int startPlayerInvX = 8;
        int startPlayerInvY = 51;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                int slotIndex = 9 + (row * 9) + column;
                int slotX = startPlayerInvX + (column * slotSizePlus2);
                int slotY = startPlayerInvY + (row * slotSizePlus2);
                this.addSlot(new Slot(playerInventory, slotIndex, slotX, slotY));
            }
        }

        // Main Player Hotbar
        int startPlayerHotBarX = startPlayerInvX;
        int startPlayerHotBarY = startPlayerInvY + 58;
        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInventory, column, startPlayerHotBarX + (column * slotSizePlus2), startPlayerHotBarY));
        }
    }

    public ContainerFishtrap(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static TileEntityFishtrap getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "player inventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileEntityAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileEntityAtPos instanceof TileEntityFishtrap) {
            return (TileEntityFishtrap) tileEntityAtPos;
        }
        throw new IllegalStateException("TileEntity is not instance of TileEntityFishtrap: " + tileEntityAtPos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.tileEntityFishtrap.canOpen(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if (index < 36) {
                if (!this.mergeItemStack(itemStack1, 36, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

}

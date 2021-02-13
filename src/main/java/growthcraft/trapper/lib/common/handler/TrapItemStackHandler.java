package growthcraft.trapper.lib.common.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TrapItemStackHandler extends ItemStackHandler {
    private boolean insertingLootItem = false;

    public TrapItemStackHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        // TODO: Handle slot 0 as the bait slot only.
        //  User should not be able to insert into slot 1 through 5
        return super.isItemValid(slot, stack);
    }

    /**
     * Override of insertItem. The trap tile entities should only allow a user/pipe to insert into the bait slot.
     * the item trap or tile entity trap should call insertLootedItem to try and insert loot into the inventory.
     *
     * @param slot     The slot id to insert into
     * @param stack    The itemStack to be inserted
     * @param simulate Should we test this insert
     * @return The remaining item stack if the insert failed
     */
    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        // If we are adding to the looted item slots, iterate over the slots and try and add the item.
        if (insertingLootItem && slot > 0) {
            insertingLootItem = false;
            return super.insertItem(slot, stack, simulate);
        }

        // If the slot is the bait slot, then try and insert.
        if (slot == 0) {
            return super.insertItem(slot, stack, simulate);
        } else {
            return stack;
        }

    }

    public ItemStack insertLootedItem(int slot, @Nonnull ItemStack stack, boolean simulate, boolean insertLoot) {
        insertingLootItem = insertLoot;
        return insertItem(slot, stack, simulate);
    }

    public boolean isInventoryFull() {
        int filledSlots = 0;
        for (int i = 1; i < this.getSlots(); i++) {
            if (this.getStackInSlot(i).getCount() == this.getSlotLimit(i)) filledSlots++;
        }
        return filledSlots == this.getSlots();
    }

}

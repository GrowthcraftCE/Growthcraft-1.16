package growthcraft.apiary.client.container.slot;

import growthcraft.apiary.shared.Reference;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class BeeBoxInputSlot extends Slot {
    public BeeBoxInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return ItemTags.getCollection().get(
                new ResourceLocation(Reference.MODID,
                        Reference.TAG_BEES)).contains(stack.getItem());
    }
}

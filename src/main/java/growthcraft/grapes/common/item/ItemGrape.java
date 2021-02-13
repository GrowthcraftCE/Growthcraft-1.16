package growthcraft.grapes.common.item;

import growthcraft.core.Growthcraft;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemGrape extends Item {

    public ItemGrape(int hunger, float saturation) {
        super(new Item.Properties().group(Growthcraft.itemGroup).food(new Food.Builder().hunger(hunger).saturation(saturation).build()));
    }

}

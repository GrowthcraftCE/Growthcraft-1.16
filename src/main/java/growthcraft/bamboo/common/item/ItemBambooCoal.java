package growthcraft.bamboo.common.item;

import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.item.ItemStack;

public class ItemBambooCoal extends GrowthcraftItem {

    public ItemBambooCoal() {
        super();
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 200;
    }
}

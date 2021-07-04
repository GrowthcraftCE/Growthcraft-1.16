package growthcraft.lib.common.item;

import growthcraft.core.shared.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * GrowthcraftEnchantedItem is used to for a simple item that needs the enchanted glint.
 */
public class GrowthcraftEnchantedItem extends Item {

    public GrowthcraftEnchantedItem() {
        super(getInitProperties(64));
    }

    public GrowthcraftEnchantedItem(int maxStackSize) {
        super(getInitProperties(maxStackSize));
    }

    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Reference.growthcraftCreativeTab);
        properties.maxStackSize(maxStackSize);
        return properties;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}

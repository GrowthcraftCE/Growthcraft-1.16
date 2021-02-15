package growthcraft.lib.common.item;

import growthcraft.core.shared.Reference;
import net.minecraft.item.Item;

/**
 * GrowthcraftItem is used to for a simple item.
 */
public class GrowthcraftItem extends Item {

    public GrowthcraftItem() {
        super(getInitProperties(64));
    }

    public GrowthcraftItem(int maxStackSize) {
        super(getInitProperties(maxStackSize));
    }

    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Reference.growthraftCreativeTab);
        properties.maxStackSize(maxStackSize);
        return properties;
    }

}

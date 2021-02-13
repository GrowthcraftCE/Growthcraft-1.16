package growthcraft.lib.common.item;

import growthcraft.core.Growthcraft;
import net.minecraft.item.Item;

public class GrowthcraftItem extends Item {

    public GrowthcraftItem() {
        super(getInitProperties(64));
    }

    public GrowthcraftItem(int maxStackSize) {
        super(getInitProperties(maxStackSize));
    }

    /**
     * Setup properties that we want all Growthcraft Items to have, like the
     * creative tab.
     *
     * @return
     */
    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Growthcraft.itemGroup);
        properties.maxStackSize(maxStackSize);
        return properties;
    }

}

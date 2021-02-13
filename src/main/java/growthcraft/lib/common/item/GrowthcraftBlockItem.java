package growthcraft.lib.common.item;

import growthcraft.core.Growthcraft;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class GrowthcraftBlockItem extends BlockItem {

    public GrowthcraftBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public GrowthcraftBlockItem(Block block, int maxStackSize) {
        super(block, getInitProperties(maxStackSize));
    }

    public GrowthcraftBlockItem(Block block) {
        this(block, 64);
    }

    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Growthcraft.itemGroup);
        properties.maxStackSize(maxStackSize);
        return properties;
    }
}

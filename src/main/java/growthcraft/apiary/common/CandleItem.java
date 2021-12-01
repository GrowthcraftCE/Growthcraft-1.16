package growthcraft.apiary.common;

import growthcraft.core.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.WallOrFloorItem;

import java.awt.*;

public class CandleItem extends WallOrFloorItem {
    private final int color;

    public CandleItem(Block floorBlock, Block wallBlockIn, Color color) {
        super(floorBlock, wallBlockIn, getInitProperties(64));
        this.color = color.getRGB();
    }

    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Reference.growthcraftCreativeTab);
        properties.maxStackSize(maxStackSize);
        return properties;
    }

    public int getColor() {
        return this.color;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

}

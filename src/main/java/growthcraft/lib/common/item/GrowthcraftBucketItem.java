package growthcraft.lib.common.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;

import java.awt.*;
import java.util.function.Supplier;

public class GrowthcraftBucketItem extends BucketItem {

    private final int color;

    public GrowthcraftBucketItem(Supplier<? extends Fluid> supplier, Color color) {
        super(supplier, new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1));

        this.color = color.getRGB();
    }

    public int getColor() {
        return this.color;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

}

package growthcraft.lib.common.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class GrowthcraftBucketItem extends BucketItem {
    public GrowthcraftBucketItem(Supplier<? extends Fluid> supplier) {
        super(supplier, new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1));
    }
}

package growthcraft.hops.common.item;

import growthcraft.hops.init.GrowthcraftHopsBlocks;
import growthcraft.lib.common.item.GrowthcraftBlockItem;

public class ItemHopsSeeds extends GrowthcraftBlockItem {

    public ItemHopsSeeds() {
        super(GrowthcraftHopsBlocks.hopsBush.get());
        GrowthcraftHopsBlocks.hopsBush.get().withSeedsItem(this);
    }

}

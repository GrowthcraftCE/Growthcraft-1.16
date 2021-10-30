package growthcraft.cellar.common.item;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.lib.common.item.GrowthcraftBlockItem;

public class HopSeedsItem extends GrowthcraftBlockItem {

    public HopSeedsItem() {
        super(GrowthcraftCellarBlocks.hops_vine.get());
        GrowthcraftCellarBlocks.hops_vine.get().withSeedsItem(this);
    }
}

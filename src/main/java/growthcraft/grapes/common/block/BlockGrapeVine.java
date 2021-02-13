package growthcraft.grapes.common.block;

import growthcraft.lib.common.block.GrowthcraftVineBlock;
import growthcraft.lib.common.block.GrowthcraftVineLeavesBlock;

/**
 * The grape vine will only grow on farmland until it is mature. Once it is mature it will attempt to grow upwards
 * until it reaches a rope block to spawn a grape vine bush.
 */
public class BlockGrapeVine extends GrowthcraftVineBlock {

    public BlockGrapeVine(GrowthcraftVineLeavesBlock vineLeavesBlock) {
        super(vineLeavesBlock);
    }

}

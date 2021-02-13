package growthcraft.apples.common.tree;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class AppleTree extends Tree {

    public static final TreeFeatureConfig APPLE_TREE_CONFIG;

    static {
        APPLE_TREE_CONFIG = (
                new TreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(GrowthcraftApplesBlocks.appleWoodLog.get().getDefaultState()),
                        new SimpleBlockStateProvider(GrowthcraftApplesBlocks.appleTreeLeaves.get().getDefaultState()),
                        new BlobFoliagePlacer(1, 0)
                ))
                .baseHeight(9)
                .foliageHeight(2)
                .trunkHeight(4)
                .trunkTopOffset(4)
                .setSapling(GrowthcraftApplesBlocks.appleTreeSapling.get())
                .build();
    }

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(@Nullable Random random, boolean unknownBoolean) {
        return Feature.NORMAL_TREE.withConfiguration(APPLE_TREE_CONFIG);
    }

}

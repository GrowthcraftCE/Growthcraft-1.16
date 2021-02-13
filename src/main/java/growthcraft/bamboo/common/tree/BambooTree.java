package growthcraft.bamboo.common.tree;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class BambooTree extends Tree {

    public static final TreeFeatureConfig BAMBOO_TREE_CONFIG;

    static {
        BAMBOO_TREE_CONFIG = (
                new TreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(GrowthcraftBambooBlocks.bambooWoodLog.get().getDefaultState()),
                        new SimpleBlockStateProvider(GrowthcraftBambooBlocks.bambooTreeLeaves.get().getDefaultState()),
                        new BlobFoliagePlacer(2, 0)
                ))
                .baseHeight(8)
                .heightRandA(8)
                .foliageHeightRandom(3)
                .setSapling(GrowthcraftBambooBlocks.bambooTreeSapling.get())
                .build();
    }

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(@Nullable Random random, boolean unknownBoolean) {
        return Feature.NORMAL_TREE.withConfiguration(BAMBOO_TREE_CONFIG);
    }

}

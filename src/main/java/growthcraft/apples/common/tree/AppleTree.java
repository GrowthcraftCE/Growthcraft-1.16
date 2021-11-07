package growthcraft.apples.common.tree;

import growthcraft.apples.init.GrowthcraftApplesBiomes;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class AppleTree extends Tree {

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(@Nullable Random random, boolean unknownBoolean) {
        return GrowthcraftApplesBiomes.APPLE_TREE;
    }

}
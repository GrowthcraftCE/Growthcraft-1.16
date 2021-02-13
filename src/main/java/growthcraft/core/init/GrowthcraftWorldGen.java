package growthcraft.core.init;

import growthcraft.core.init.config.GrowthcraftConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import static growthcraft.lib.worldgen.OreGeneration.setupOreGeneration;

public class GrowthcraftWorldGen {

    public static void oreGeneration() {

        if(GrowthcraftConfig.getSaltOreGenerate()) {
            setupOreGeneration(
                    GrowthcraftBlocks.ROCK_SALT_ORE.get(),
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    GrowthcraftConfig.getSaltOreGenCount(),
                    GrowthcraftConfig.getSaltOreGenMinHeight(),
                    0,
                    GrowthcraftConfig.getSaltOreGenMaxHeight(),
                    GrowthcraftConfig.getSaltOreGenChance()
            );
        }

    }

}

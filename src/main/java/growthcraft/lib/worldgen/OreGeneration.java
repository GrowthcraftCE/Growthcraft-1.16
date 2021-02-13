package growthcraft.lib.worldgen;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

    private OreGeneration() { /* Disable default public constructor */ }

    public static void setupOreGeneration(Block oreBlock, FillerBlockType fillerBlockType, int count, int minHeight, int topOffset, int maxHeight, int chance) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            CountRangeConfig countRangeConfig = new CountRangeConfig(
                    count,
                    minHeight,
                    topOffset,
                    maxHeight
            );

            biome.addFeature(
                    GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(
                                    fillerBlockType,
                                    oreBlock.getDefaultState(),
                                    chance
                            )
                    ).withPlacement(Placement.COUNT_RANGE.configure(countRangeConfig))
            );
        }
    }

}

package growthcraft.apples.init;

import growthcraft.apples.shared.Reference;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Reference.MODID);

    private GrowthcraftApplesBiomes() { /* Prevent default public constructor */ }

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> APPLE_TREE = register(
            "apple",
            Feature.TREE.withConfiguration((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(GrowthcraftApplesBlocks.appleWood.get().getDefaultState()),
                            new SimpleBlockStateProvider(GrowthcraftApplesBlocks.appleTreeLeaves.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
                            new StraightTrunkPlacer(4, 2, 0),
                            new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()
            )
    );

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

}

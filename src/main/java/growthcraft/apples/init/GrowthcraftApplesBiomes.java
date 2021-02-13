package growthcraft.apples.init;

import growthcraft.apples.common.world.biome.OrchardBiome;
import growthcraft.apples.shared.Reference;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Reference.MODID);

    public static final RegistryObject<Biome> orchardBiome;

    static {
        orchardBiome = BIOMES.register("orchard_biome", () -> new OrchardBiome());
    }

    private GrowthcraftApplesBiomes() { /* Disable default public constructor */ }

    public static void registeryBiomes() {
        registerBiome(orchardBiome.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    }

    public static void registerBiome(Biome biome, BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }

}

package growthcraft.apples.init;

import growthcraft.cellar.shared.Reference;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Reference.MODID);

    private GrowthcraftApplesBiomes() { /* Prevent default public constructor */ }
}

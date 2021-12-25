package growthcraft.bamboo.init;

import growthcraft.bamboo.shared.Reference;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftBambooBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(
            ForgeRegistries.BIOMES, Reference.MODID
    );

}

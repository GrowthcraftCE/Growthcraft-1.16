package growthcraft.milk.init;

import growthcraft.milk.shared.Reference;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    private GrowthcraftMilkTileEntities() {
        /* Prevent generation of public constructor */
    }
}

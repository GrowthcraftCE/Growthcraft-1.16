package growthcraft.core.init;

import growthcraft.core.common.tileentity.RopeTileEntity;
import growthcraft.core.shared.Reference;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final RegistryObject<TileEntityType<RopeTileEntity>> rope_tileentity = TILE_ENTITIES.register(
            Reference.ROPE,
            () -> TileEntityType.Builder.create(
                    RopeTileEntity::new,
                    GrowthcraftBlocks.ROPE_LINEN.get()
            ).build(null)
    );

    private GrowthcraftTileEntities() { /* Prevent default public constructor */ }

}

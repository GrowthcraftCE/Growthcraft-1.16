package growthcraft.apples.init;

import growthcraft.apples.shared.Reference;
import growthcraft.apples.shared.UnlocalizedName;
import growthcraft.trapper.lib.common.tileentity.TileEntityFishtrap;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static RegistryObject<TileEntityType<TileEntityFishtrap>> appleFishtrapTileEntity;

    static {
        appleFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_APPLE,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(appleFishtrapTileEntity.get()), GrowthcraftApplesBlocks.appleFishtrap.get()
                ).build(null)
        );
    }
}

package growthcraft.bamboo.init;

import growthcraft.bamboo.shared.Reference;
import growthcraft.bamboo.shared.UnlocalizedName;
import growthcraft.trapper.lib.common.tileentity.TileEntityFishtrap;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftBambooTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static RegistryObject<TileEntityType<TileEntityFishtrap>> bambooFishtrapTileEntity;

    static {
        bambooFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_BAMBOO,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(bambooFishtrapTileEntity.get()), GrowthcraftBambooBlocks.bambooFishtrap.get()
                ).build(null)
        );
    }
}

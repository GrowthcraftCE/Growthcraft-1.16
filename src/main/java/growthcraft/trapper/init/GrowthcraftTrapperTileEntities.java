package growthcraft.trapper.init;

import growthcraft.trapper.lib.common.tileentity.TileEntityFishtrap;
import growthcraft.trapper.shared.Reference;
import growthcraft.trapper.shared.UnlocalizedName;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftTrapperTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static RegistryObject<TileEntityType<TileEntityFishtrap>> oakFishtrapTileEntity;
    public static RegistryObject<TileEntityType<TileEntityFishtrap>> acaciaFishtrapTileEntity;
    public static RegistryObject<TileEntityType<TileEntityFishtrap>> jungleFishtrapTileEntity;
    public static RegistryObject<TileEntityType<TileEntityFishtrap>> spruceFishtrapTileEntity;
    public static RegistryObject<TileEntityType<TileEntityFishtrap>> darkOakFishtrapTileEntity;
    public static RegistryObject<TileEntityType<TileEntityFishtrap>> birchFishtrapTileEntity;

    static {
        oakFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_OAK,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(oakFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.oakFishtrap.get()
                ).build(null)
        );
        acaciaFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_ACACIA,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(acaciaFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.acaciaFishtrap.get()
                ).build(null)
        );
        jungleFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_JUNGLE,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(jungleFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.jungleFishtrap.get()
                ).build(null)
        );
        spruceFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_SPRUCE,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(spruceFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.spruceFishtrap.get()
                ).build(null)
        );
        darkOakFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_DARK_OAK,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(darkOakFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.darkOakFishtrap.get()
                ).build(null)
        );
        birchFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_BIRCH,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(birchFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.birchFishtrap.get()
                ).build(null)
        );
    }

    private GrowthcraftTrapperTileEntities() { /* Disable Default Public Constructor */ }

}

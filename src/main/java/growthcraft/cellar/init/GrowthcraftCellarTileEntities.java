package growthcraft.cellar.init;

import growthcraft.cellar.common.tileentity.*;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final RegistryObject<TileEntityType<BrewKettleTileEntity>> brew_kettle_tileentity = TILE_ENTITIES.register(
            UnlocalizedName.BREW_KETTLE,
            () -> TileEntityType.Builder.create(
                    BrewKettleTileEntity::new, GrowthcraftCellarBlocks.brew_kettle.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<CultureJarTileEntity>> culture_jar_tileentity = TILE_ENTITIES.register(
            UnlocalizedName.CULTURE_JAR,
            () -> TileEntityType.Builder.create(
                    CultureJarTileEntity::new, GrowthcraftCellarBlocks.culture_jar.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<FermentBarrelTileEntity>> barrel_ferment_oak_tileentity = TILE_ENTITIES.register(
            UnlocalizedName.FERMENT_BARREL_OAK,
            () -> TileEntityType.Builder.create(
                    FermentBarrelTileEntity::new, GrowthcraftCellarBlocks.barrel_ferment_oak.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<RoasterTileEntity>> roaster_tileentity = TILE_ENTITIES.register(
            UnlocalizedName.ROASTER,
            () -> TileEntityType.Builder.create(
                    RoasterTileEntity::new, GrowthcraftCellarBlocks.roaster.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<FruitPressPistonTileEntity>> fruit_press_piston_tileentity = TILE_ENTITIES.register(
            UnlocalizedName.FRUIT_PRESS_PISTON,
            () -> TileEntityType.Builder.create(
                    FruitPressPistonTileEntity::new, GrowthcraftCellarBlocks.FRUIT_PRESS_PISTON.get()
            ).build(null)
    );

    private GrowthcraftCellarTileEntities() { /* Prevent creation of public constructor */ }
}

package growthcraft.cellar.init;

import growthcraft.cellar.common.tileentity.BrewKettleTileEntity;
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

    private GrowthcraftCellarTileEntities() { /* Prevent creation of public constructor */ }
}

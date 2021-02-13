package growthcraft.cellar.init;

import growthcraft.cellar.common.tileentity.BrewKettleTileEntity;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES_TYPES =
            new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final RegistryObject<TileEntityType<BrewKettleTileEntity>> brewKettleTileEntity;

    static {

        brewKettleTileEntity = TILE_ENTITIES_TYPES.register(
                UnlocalizedName.BREW_KETTLE,
                () -> TileEntityType.Builder.create(
                        BrewKettleTileEntity::new, GrowthcraftCellarBlocks.brewKettle.get()
                ).build(null)
        );

    }
}

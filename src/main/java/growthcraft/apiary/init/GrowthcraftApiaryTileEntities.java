package growthcraft.apiary.init;

import growthcraft.apiary.common.tileentity.BeeBoxTileEntity;
import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApiaryTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final RegistryObject<TileEntityType<BeeBoxTileEntity>> BEE_BOX_TILE_ENTITY = TILE_ENTITIES.register(
            UnlocalizedName.BEE_BOX_OAK,
            () -> TileEntityType.Builder.create(
                    BeeBoxTileEntity::new, GrowthcraftApiaryBlocks.BEE_BOX_OAK.get()
            ).build(null)
    );

}

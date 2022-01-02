package growthcraft.milk.init;

import growthcraft.milk.common.tileentity.ChurnTileEntity;
import growthcraft.milk.common.tileentity.PancheonTileEntity;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final RegistryObject<TileEntityType<ChurnTileEntity>> CHURN_TILE_ENTITY = TILE_ENTITIES.register(
            UnlocalizedName.CHURN,
            () -> TileEntityType.Builder.create(
                    ChurnTileEntity::new, GrowthcraftMilkBlocks.CHURN.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<PancheonTileEntity>> PANCHEON_TILE_ENTITY = TILE_ENTITIES.register(
            UnlocalizedName.PANCHEON,
            () -> TileEntityType.Builder.create(
                    PancheonTileEntity::new, GrowthcraftMilkBlocks.PANCHEON.get()
            ).build(null)
    );

    private GrowthcraftMilkTileEntities() {
        /* Prevent generation of public constructor */
    }
}

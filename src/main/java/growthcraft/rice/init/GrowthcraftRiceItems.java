package growthcraft.rice.init;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.common.item.GrowthcraftFoodItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.lib.util.FluidUtils;
import growthcraft.rice.common.item.CultivatorItem;
import growthcraft.rice.common.item.RiceSeedItem;
import growthcraft.rice.shared.Reference;
import growthcraft.rice.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftRiceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RICE_WATER = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WATER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftRiceFluids.RICE_WATER_FLUID_STILL,
                    Reference.FluidColor.RICE_WATER_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RICE_WINE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WINE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftRiceFluids.RICE_WINE_FLUID_STILL,
                    Reference.FluidColor.RICE_WINE_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_SAKE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.SAKE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftRiceFluids.SAKE_FLUID_STILL,
                    Reference.FluidColor.SAKE_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<CultivatorItem> CULTIVATOR = ITEMS.register(
            UnlocalizedName.CULTIVATOR, CultivatorItem::new
    );
    public static final RegistryObject<GrowthcraftItem> KNIFE = ITEMS.register(
            UnlocalizedName.KNIFE, GrowthcraftItem::new
    );
    public static final RegistryObject<RiceSeedItem> RICE = ITEMS.register(
            UnlocalizedName.RICE, RiceSeedItem::new
    );
    public static final RegistryObject<GrowthcraftFoodItem> RICE_COOKED = ITEMS.register(
            UnlocalizedName.RICE_COOKED, GrowthcraftFoodItem::new
    );
    public static final RegistryObject<GrowthcraftItem> RICE_STALK = ITEMS.register(
            UnlocalizedName.RICE_STALK, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftFoodItem> SUSHI_ROLL = ITEMS.register(
            UnlocalizedName.SUSHI_ROLL, GrowthcraftFoodItem::new
    );

}

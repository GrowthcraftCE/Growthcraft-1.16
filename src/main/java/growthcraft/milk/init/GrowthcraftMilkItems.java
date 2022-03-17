package growthcraft.milk.init;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.common.item.GrowthcraftFoodItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.common.item.ThistleSeedItem;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_BUTTER_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.BUTTER_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.BUTTER_MILK_FLUID_STILL,
                    Reference.FluidColor.BUTTER_MILK_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CONDENSED_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CONDENSED_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CONDENSED_MILK_FLUID_STILL,
                    Reference.FluidColor.CONDENSED_MILK_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CREAM = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CREAM).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CREAM_FLUID_STILL,
                    Reference.FluidColor.CREAM_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CULTURED_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CULTURED_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CULTURED_MILK_FLUID_STILL,
                    Reference.FluidColor.CULTURED_MILK_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_KUMIS = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.KUMIS).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.KUMIS_FLUID_STILL,
                    Reference.FluidColor.KUMIS_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.MILK_FLUID_STILL,
                    Reference.FluidColor.MILK_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RENNET = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RENNET).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.RENNET_FLUID_STILL,
                    Reference.FluidColor.RENNET_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_SKIM_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.SKIM_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.SKIM_MILK_FLUID_STILL,
                    Reference.FluidColor.SKIM_MILK_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WHEY = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WHEY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.WHEY_FLUID_STILL,
                    Reference.FluidColor.WHEY_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_APPLE = ITEMS.register(
            UnlocalizedName.ICE_CREAM_APPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_CHOCOLATE = ITEMS.register(
            UnlocalizedName.ICE_CREAM_CHOCOLATE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_PURPLE = ITEMS.register(
            UnlocalizedName.ICE_CREAM_GRAPE_PURPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_RED = ITEMS.register(
            UnlocalizedName.ICE_CREAM_GRAPE_RED, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_WHITE = ITEMS.register(
            UnlocalizedName.ICE_CREAM_GRAPE_WHITE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_PUMPKIN = ITEMS.register(
            UnlocalizedName.ICE_CREAM_PUMPKIN, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_WATERMELON = ITEMS.register(
            UnlocalizedName.ICE_CREAM_WATERMELON, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_HONEY = ITEMS.register(
            UnlocalizedName.ICE_CREAM_HONEY, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftItem> BUTTER = ITEMS.register(
            UnlocalizedName.BUTTER, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftItem> BUTTER_SALTED = ITEMS.register(
            UnlocalizedName.BUTTER_SALTED, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftItem> CHEESE_CLOTH = ITEMS.register(
            UnlocalizedName.CHEESE_CLOTH, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftItem> STARTER_CULTURE = ITEMS.register(
            UnlocalizedName.STARTER_CULTURE, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftItem> STOMACH = ITEMS.register(
            UnlocalizedName.STOMACH, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftItem> THISTLE = ITEMS.register(
            UnlocalizedName.THISTLE, GrowthcraftItem::new
    );
    public static final RegistryObject<ThistleSeedItem> THISTLE_SEED = ITEMS.register(
            UnlocalizedName.THISTLE_SEED, ThistleSeedItem::new
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_APPLE = ITEMS.register(
            UnlocalizedName.YOGURT_APPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_CHOCOLATE = ITEMS.register(
            UnlocalizedName.YOGURT_CHOCOLATE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_GRAPE_PURPLE = ITEMS.register(
            UnlocalizedName.YOGURT_GRAPE_PURPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_GRAPE_RED = ITEMS.register(
            UnlocalizedName.YOGURT_GRAPE_RED, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_GRAPE_WHITE = ITEMS.register(
            UnlocalizedName.YOGURT_GRAPE_WHITE, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_HONEY = ITEMS.register(
            UnlocalizedName.YOGURT_HONEY, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_PLAIN = ITEMS.register(
            UnlocalizedName.YOGURT_PLAIN, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_PUMPKIN = ITEMS.register(
            UnlocalizedName.YOGURT_PUMPKIN, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_WATERMELON = ITEMS.register(
            UnlocalizedName.YOGURT_WATERMELON, () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftItem> CHEESE_CURDS_CHEDDAR_DRAINED = ITEMS.register(
            UnlocalizedName.CHEESE_CURDS_CHEDDAR_DRAINED, GrowthcraftItem::new
    );

    private GrowthcraftMilkItems() {
        /* Prevent generation of public constructor */
    }

}

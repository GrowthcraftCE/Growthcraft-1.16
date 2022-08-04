package growthcraft.milk.init;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.common.item.GrowthcraftFoodItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.lib.util.CheeseUtils;
import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.common.item.MilkingBucketItem;
import growthcraft.milk.common.item.ThistleSeedItem;
import growthcraft.milk.lib.common.item.CheeseCurdsDrainedItem;
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

    public static final RegistryObject<MilkingBucketItem> MILKING_BUCKET_IRON = ITEMS.register(
            UnlocalizedName.MILKING_BUCKET_IRON, MilkingBucketItem::new
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_BUTTER_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.BUTTER_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.BUTTER_MILK_FLUID_STILL,
                    Reference.FluidColor.BUTTER_MILK_FLUID_COLOR.getColor()
            )
    );
    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CHEESE_BASE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CHEESE_BASE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CHEESE_BASE_FLUID_STILL,
                    Reference.FluidColor.CHEESE_BASE_FLUID_COLOR.getColor()
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
            UnlocalizedName.ICE_CREAM_APPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_CHOCOLATE = ITEMS.register(
            UnlocalizedName.ICE_CREAM_CHOCOLATE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_PURPLE = ITEMS.register(
            UnlocalizedName.ICE_CREAM_GRAPE_PURPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_RED = ITEMS.register(
            UnlocalizedName.ICE_CREAM_GRAPE_RED, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_WHITE = ITEMS.register(
            UnlocalizedName.ICE_CREAM_GRAPE_WHITE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_PUMPKIN = ITEMS.register(
            UnlocalizedName.ICE_CREAM_PUMPKIN, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_WATERMELON = ITEMS.register(
            UnlocalizedName.ICE_CREAM_WATERMELON, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );
    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_HONEY = ITEMS.register(
            UnlocalizedName.ICE_CREAM_HONEY, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
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
    public static final RegistryObject<CheeseCurdsDrainedItem> APPENZELLER_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.APPENZELLER).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.APPENZELLER_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> APPENZELLER_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.APPENZELLER).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<CheeseCurdsDrainedItem> ASIAGO_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.ASIAGO).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.ASIAGO_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> ASIAGO_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.ASIAGO).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<CheeseCurdsDrainedItem> CASU_MARZU_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CASU_MARZU).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.CASU_MARZU_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> CASU_MARZU_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CASU_MARZU).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<CheeseCurdsDrainedItem> CHEDDAR_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CHEDDAR).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.CHEDDAR_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEDDAR_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CHEDDAR).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<CheeseCurdsDrainedItem> EMMENTALER_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.EMMENTALER).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.EMMENTALER_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> EMMENTALER_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.EMMENTALER).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );
    public static final RegistryObject<CheeseCurdsDrainedItem> GORGONZOLA_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GORGONZOLA).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.GORGONZOLA_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> GORGONZOLA_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GORGONZOLA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> GOUDA_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GOUDA).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.GOUDA_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> GOUDA_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GOUDA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> MONTEREY_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.MONTEREY).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.MONTEREY_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> MONTEREY_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.MONTEREY).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> PARMESAN_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PARMESAN).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.PARMESAN_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> PARMESAN_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PARMESAN).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> PROVOLONE_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PROVOLONE).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.PROVOLONE_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> PROVOLONE_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PROVOLONE).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> RICOTTA_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.RICOTTA).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.RICOTTA_CHEESE.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> RICOTTA_CHEESE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.RICOTTA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.4F, 16)
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

    private GrowthcraftMilkItems() {
        /* Prevent generation of public constructor */
    }

}

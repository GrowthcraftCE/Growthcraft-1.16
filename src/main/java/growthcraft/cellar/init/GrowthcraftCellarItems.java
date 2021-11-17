package growthcraft.cellar.init;

import growthcraft.cellar.common.item.GrainItem;
import growthcraft.cellar.common.item.GrapeSeedsItem;
import growthcraft.cellar.common.item.HopSeedsItem;
import growthcraft.cellar.lib.item.CellarPotionItem;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.common.item.GrowthcraftEnchantedItem;
import growthcraft.lib.common.item.GrowthcraftFoodItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

import static growthcraft.cellar.shared.Reference.FluidName.*;

public class GrowthcraftCellarItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<GrowthcraftFoodItem> GRAPE_PURPLE = ITEMS.register(
            UnlocalizedName.GRAPE_PURPLE, GrowthcraftFoodItem::new
    );
    public static final RegistryObject<GrapeSeedsItem> GRAPE_PURPLE_SEEDS = ITEMS.register(
            UnlocalizedName.GRAPE_SEEDS_PURPLE, () -> new GrapeSeedsItem(GRAPE_PURPLE.get())
    );

    public static final RegistryObject<GrowthcraftFoodItem> GRAPE_RED = ITEMS.register(
            UnlocalizedName.GRAPE_RED, GrowthcraftFoodItem::new
    );

    public static final RegistryObject<GrapeSeedsItem> GRAPE_RED_SEEDS = ITEMS.register(
            UnlocalizedName.GRAPE_SEEDS_RED, () -> new GrapeSeedsItem(GRAPE_RED.get())
    );

    public static final RegistryObject<CellarPotionItem> ALE_POTION = ITEMS.register(
            UnlocalizedName.ALE_POTION,
            CellarPotionItem::new
    );
    public static final RegistryObject<GrowthcraftFoodItem> GRAPE_WHITE = ITEMS.register(
            UnlocalizedName.GRAPE_WHITE, GrowthcraftFoodItem::new
    );
    public static final RegistryObject<GrapeSeedsItem> GRAPE_WHITE_SEEDS = ITEMS.register(
            UnlocalizedName.GRAPE_SEEDS_WHITE, () -> new GrapeSeedsItem(GRAPE_WHITE.get())
    );
    public static final RegistryObject<GrowthcraftItem> HOPS = ITEMS.register(
            UnlocalizedName.HOPS, GrowthcraftItem::new
    );
    public static final RegistryObject<HopSeedsItem> HOPS_SEEDS = ITEMS.register(
            UnlocalizedName.HOPS_SEEDS, HopSeedsItem::new
    );
    public static final RegistryObject<CellarPotionItem> LAGER_POTION = ITEMS.register(
            UnlocalizedName.LAGER_POTION,
            CellarPotionItem::new
    );
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(
            ForgeRegistries.POTION_TYPES, growthcraft.core.shared.Reference.MODID
    );
    public static final RegistryObject<CellarPotionItem> WINE_POTION = ITEMS.register(
            UnlocalizedName.WINE_POTION,
            CellarPotionItem::new
    );
    public static final RegistryObject<GrowthcraftItem> YEAST_BAYANUS = ITEMS.register(
            UnlocalizedName.YEAST_BAYANUS, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftEnchantedItem> YEAST_BAYANUS_ETHEREAL = ITEMS.register(
            UnlocalizedName.YEAST_BAYANUS_ETHEREAL, GrowthcraftEnchantedItem::new
    );
    //endregion
    public static final RegistryObject<GrowthcraftItem> YEAST_BREWERS = ITEMS.register(
            UnlocalizedName.YEAST_BREWERS, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftEnchantedItem> YEAST_BREWERS_ETHEREAL = ITEMS.register(
            UnlocalizedName.YEAST_BREWERS_ETHEREAL, GrowthcraftEnchantedItem::new
    );
    public static final RegistryObject<GrowthcraftItem> YEAST_ETHEREAL = ITEMS.register(
            UnlocalizedName.YEAST_ETHEREAL, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftItem> brew_kettle_lid = ITEMS.register(
            UnlocalizedName.BREW_KETTLE_LID,
            () -> new GrowthcraftItem(1)
    );
    public static final RegistryObject<GrowthcraftItem> grain = ITEMS.register(
            UnlocalizedName.GRAIN, () -> new GrainItem(new Color(0xA4DBE8).getRGB())
    );
    public static final RegistryObject<GrainItem> grain_amber = ITEMS.register(
            UnlocalizedName.GRAIN_AMBER, () -> new GrainItem(Reference.FluidColor.AMBER_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrainItem> grain_brown = ITEMS.register(
            UnlocalizedName.GRAIN_BROWN, () -> new GrainItem(Reference.FluidColor.BROWN_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrainItem> grain_copper = ITEMS.register(
            UnlocalizedName.GRAIN_COPPER, () -> new GrainItem(Reference.FluidColor.COPPER_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrainItem> grain_dark = ITEMS.register(
            UnlocalizedName.GRAIN_DARK, () -> new GrainItem(Reference.FluidColor.DARK_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrainItem> grain_deep_amber = ITEMS.register(
            UnlocalizedName.GRAIN_DEEP_AMBER, () -> new GrainItem(Reference.FluidColor.DEEP_AMBER_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrainItem> grain_deep_copper = ITEMS.register(
            UnlocalizedName.GRAIN_DEEP_COPPER, () -> new GrainItem(Reference.FluidColor.DEEP_COPPER_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrainItem> grain_golden = ITEMS.register(
            UnlocalizedName.GRAIN_GOLDEN, () -> new GrainItem(Reference.FluidColor.GOLDEN_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrainItem> grain_pale_golden = ITEMS.register(
            UnlocalizedName.GRAIN_PALE_GOLDEN, () -> new GrainItem(Reference.FluidColor.PALE_GOLDEN_WORT_FLUID_COLOR.toIntValue())
    );
    public static final RegistryObject<GrowthcraftItem> yeast_lager = ITEMS.register(
            UnlocalizedName.YEAST_LAGER, GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftEnchantedItem> yeast_lager_ethereal = ITEMS.register(
            UnlocalizedName.YEAST_LAGER_ETHEREAL, GrowthcraftEnchantedItem::new
    );
    private static final String BUCKET = "bucket";
    /**
     * Generic wort fluid for testing.
     */
    public static final RegistryObject<BucketItem> bucket_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    //endregion
    /**
     * Ale Fluids
     */
    public static final RegistryObject<BucketItem> bucket_pale_golden_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(PALE_GOLDEN_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.PALE_GOLDEN_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_golden_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(GOLDEN_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.GOLDEN_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_hopped_golden_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(HOPPED_GOLDEN_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_amber_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(AMBER_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.AMBER_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_deep_amber_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(DEEP_AMBER_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_copper_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(COPPER_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.COPPER_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_deep_copper_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(DEEP_COPPER_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.DEEP_COPPER_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_brown_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(BROWN_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.BROWN_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_dark_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(DARK_WORT).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.DARK_WORT_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    //region Fermented Ale Buckets
    public static final RegistryObject<BucketItem> bucket_pale_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(PALE_ALE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.PALE_ALE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_ipa_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(IPA_ALE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.IPA_ALE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_amber_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(AMBER_ALE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.AMBER_ALE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_copper_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(COPPER_ALE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.COPPER_ALE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_old_port_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(OLD_PORT_ALE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.OLD_PORT_ALE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_brown_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(BROWN_ALE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.BROWN_ALE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_stout_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(STOUT_ALE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.STOUT_ALE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    //region Fermented Lager Buckets
    public static final RegistryObject<BucketItem> bucket_pale_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(PALE_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.PALE_LAGER_FLUID_STILL, Reference.FluidColor.PALE_LAGER_FLUID_COLOR.getColor())
    );
    public static final RegistryObject<BucketItem> bucket_pilsner_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(PILSNER_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.PILSNER_LAGER_FLUID_STILL, Reference.FluidColor.PILSNER_LAGER_FLUID_COLOR.getColor())
    );
    public static final RegistryObject<BucketItem> bucket_amber_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(AMBER_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.AMBER_LAGER_FLUID_STILL, Reference.FluidColor.AMBER_LAGER_FLUID_COLOR.getColor())
    );
    public static final RegistryObject<BucketItem> bucket_vienna_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(VIENNA_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.VIENNA_LAGER_FLUID_STILL, Reference.FluidColor.VIENNA_LAGER_FLUID_COLOR.getColor())
    );
    public static final RegistryObject<BucketItem> bucket_copper_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(COPPER_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.COPPER_LAGER_FLUID_STILL, Reference.FluidColor.COPPER_LAGER_FLUID_COLOR.getColor())
    );
    public static final RegistryObject<BucketItem> bucket_brown_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(BROWN_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.BROWN_LAGER_FLUID_STILL, Reference.FluidColor.BROWN_LAGER_FLUID_COLOR.getColor())
    );
    public static final RegistryObject<BucketItem> bucket_dark_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(DARK_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.DARK_LAGER_FLUID_STILL, Reference.FluidColor.DARK_LAGER_FLUID_COLOR.getColor())
    );
    public static final RegistryObject<BucketItem> bucket_red_grape_juice = ITEMS.register(
            UnlocalizedName.getFluidNames(RED_GRAPE_JUICE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.RED_GRAPE_JUICE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_white_grape_juice = ITEMS.register(
            UnlocalizedName.getFluidNames(WHITE_GRAPE_JUICE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.WHITE_GRAPE_JUICE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_purple_grape_juice = ITEMS.register(
            UnlocalizedName.getFluidNames(PURPLE_GRAPE_JUICE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );

    public static final RegistryObject<BucketItem> bucket_red_wine = ITEMS.register(
            UnlocalizedName.getFluidNames(RED_WINE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.RED_WINE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );

    public static final RegistryObject<BucketItem> bucket_purple_wine = ITEMS.register(
            UnlocalizedName.getFluidNames(PURPLE_WINE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.PURPLE_WINE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );

    public static final RegistryObject<BucketItem> bucket_white_wine = ITEMS.register(
            UnlocalizedName.getFluidNames(WHITE_WINE).get(BUCKET),
            () -> new BucketItem(
                    GrowthcraftCellarFluids.WHITE_WINE_FLUID_STILL,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );

    private GrowthcraftCellarItems() {
        /* Prevent generation of default public constructor */
    }

}

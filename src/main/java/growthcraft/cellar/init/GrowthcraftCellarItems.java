package growthcraft.cellar.init;

import growthcraft.cellar.common.item.GrainItem;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.common.item.GrowthcraftEnchantedItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

import static growthcraft.cellar.shared.Reference.FluidName.*;

public class GrowthcraftCellarItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    private static final String BUCKET = "bucket";

    public static final RegistryObject<GrowthcraftItem> brew_kettle_lid = ITEMS.register(
            UnlocalizedName.BREW_KETTLE_LID,
            () -> new GrowthcraftItem(1)
    );

    public static final RegistryObject<BucketItem> bucket_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_pale_golden_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(PALE_GOLDEN_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.PALE_GOLDEN_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_golden_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(GOLDEN_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.GOLDEN_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_amber_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(AMBER_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.AMBER_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_deep_amber_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(DEEP_AMBER_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_copper_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(COPPER_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.COPPER_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_deep_copper_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(DEEP_COPPER_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.DEEP_COPPER_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_brown_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(BROWN_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.BROWN_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_dark_wort = ITEMS.register(
            UnlocalizedName.getFluidNames(DARK_WORT).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.DARK_WORT_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_pale_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(PALE_ALE).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.PALE_ALE_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_ipa_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(IPA_ALE).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.IPA_ALE_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_amber_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(AMBER_ALE).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.AMBER_ALE_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_copper_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(COPPER_ALE).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.COPPER_ALE_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_old_port_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(OLD_PORT_ALE).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.OLD_PORT_ALE_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_brown_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(BROWN_ALE).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.BROWN_ALE_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_stout_ale = ITEMS.register(
            UnlocalizedName.getFluidNames(STOUT_ALE).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.STOUT_ALE_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_pale_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(PALE_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.PALE_LAGER_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_pilsner_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(PILSNER_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.PILSNER_LAGER_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_amber_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(AMBER_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.AMBER_LAGER_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_vienna_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(VIENNA_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.VIENNA_LAGER_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_copper_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(COPPER_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.COPPER_LAGER_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_brown_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(BROWN_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.BROWN_LAGER_FLUID_STILL)
    );

    public static final RegistryObject<BucketItem> bucket_dark_lager = ITEMS.register(
            UnlocalizedName.getFluidNames(DARK_LAGER).get(BUCKET),
            () -> new GrowthcraftBucketItem(GrowthcraftCellarFluids.DARK_LAGER_FLUID_STILL)
    );

    public static final RegistryObject<GrowthcraftItem> yeast_bayanus = ITEMS.register(
            UnlocalizedName.YEAST_BAYANUS,
            GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftEnchantedItem> yeast_bayanus_ethereal = ITEMS.register(
            UnlocalizedName.YEAST_BAYANUS_ETHEREAL,
            GrowthcraftEnchantedItem::new
    );
    public static final RegistryObject<GrowthcraftItem> yeast_brewers = ITEMS.register(
            UnlocalizedName.YEAST_BREWERS,
            GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftEnchantedItem> yeast_brewers_ethereal = ITEMS.register(
            UnlocalizedName.YEAST_BREWERS_ETHEREAL,
            GrowthcraftEnchantedItem::new
    );
    public static final RegistryObject<GrowthcraftItem> yeast_ethereal = ITEMS.register(
            UnlocalizedName.YEAST_ETHEREAL,
            GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftItem> yeast_lager = ITEMS.register(
            UnlocalizedName.YEAST_LAGER,
            GrowthcraftItem::new
    );
    public static final RegistryObject<GrowthcraftEnchantedItem> yeast_lager_ethereal = ITEMS.register(
            UnlocalizedName.YEAST_LAGER_ETHEREAL,
            GrowthcraftEnchantedItem::new
    );

    public static final RegistryObject<GrowthcraftItem> grain = ITEMS.register(
            UnlocalizedName.GRAIN,
            () -> new GrainItem(new Color(0xA4DBE8).getRGB())
    );

    public static final RegistryObject<GrainItem> grain_pale_golden = ITEMS.register(
            UnlocalizedName.GRAIN_PALE_GOLDEN,
            () -> new GrainItem(Reference.FluidColor.PALE_GOLDEN_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrainItem> grain_golden = ITEMS.register(
            UnlocalizedName.GRAIN_GOLDEN,
            () -> new GrainItem(Reference.FluidColor.GOLDEN_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrainItem> grain_amber = ITEMS.register(
            UnlocalizedName.GRAIN_AMBER,
            () -> new GrainItem(Reference.FluidColor.AMBER_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrainItem> grain_deep_amber = ITEMS.register(
            UnlocalizedName.GRAIN_DEEP_AMBER,
            () -> new GrainItem(Reference.FluidColor.DEEP_AMBER_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrainItem> grain_copper = ITEMS.register(
            UnlocalizedName.GRAIN_COPPER,
            () -> new GrainItem(Reference.FluidColor.COPPER_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrainItem> grain_deep_copper = ITEMS.register(
            UnlocalizedName.GRAIN_DEEP_COPPER,
            () -> new GrainItem(Reference.FluidColor.DEEP_COPPER_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrainItem> grain_brown = ITEMS.register(
            UnlocalizedName.GRAIN_BROWN,
            () -> new GrainItem(Reference.FluidColor.BROWN_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrainItem> grain_dark = ITEMS.register(
            UnlocalizedName.GRAIN_DARK,
            () -> new GrainItem(Reference.FluidColor.DARK_WORT_FLUID_COLOR.getRGB())
    );

    public static final RegistryObject<GrowthcraftItem> hops = ITEMS.register(
            UnlocalizedName.HOPS,
            GrowthcraftItem::new
    );

    private GrowthcraftCellarItems() {
        /* Prevent generation of default public constructor */
    }
}

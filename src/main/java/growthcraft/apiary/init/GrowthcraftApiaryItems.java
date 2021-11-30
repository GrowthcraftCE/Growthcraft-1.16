package growthcraft.apiary.init;

import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.lib.util.FluidUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApiaryItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftItem> BEE = ITEMS.register(
            UnlocalizedName.BEE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX = ITEMS.register(
            UnlocalizedName.BEES_WAX, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_BLACK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLACK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_BLACK_FLUID_STILL,
                    Reference.FluidColor.WAX_BLACK_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_BLUE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLUE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_BLUE_FLUID_STILL,
                    Reference.FluidColor.WAX_BLUE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_BROWN = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BROWN).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_BROWN_FLUID_STILL,
                    Reference.FluidColor.WAX_BROWN_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_CYAN = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_CYAN).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_CYAN_FLUID_STILL,
                    Reference.FluidColor.WAX_CYAN_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_GRAY = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GRAY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_GRAY_FLUID_STILL,
                    Reference.FluidColor.WAX_GRAY_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_GREEN = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GREEN).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_GREEN_FLUID_STILL,
                    Reference.FluidColor.WAX_GREEN_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_LIGHT_BLUE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_BLUE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_LIGHT_BLUE_FLUID_STILL,
                    Reference.FluidColor.WAX_LIGHT_BLUE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_LIGHT_GRAY = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_GRAY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_LIGHT_GRAY_FLUID_STILL,
                    Reference.FluidColor.WAX_LIGHT_GRAY_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_LIME = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIME).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_LIME_FLUID_STILL,
                    Reference.FluidColor.WAX_LIME_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_MAGENTA = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_MAGENTA).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_MAGENTA_FLUID_STILL,
                    Reference.FluidColor.WAX_MAGENTA_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_ORANGE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_ORANGE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_ORANGE_FLUID_STILL,
                    Reference.FluidColor.WAX_ORANGE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_PINK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PINK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_PINK_FLUID_STILL,
                    Reference.FluidColor.WAX_PINK_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_PURPLE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PURPLE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_PURPLE_FLUID_STILL,
                    Reference.FluidColor.WAX_PURPLE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_RED = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_RED).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_RED_FLUID_STILL,
                    Reference.FluidColor.WAX_RED_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_WHITE = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_WHITE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_WHITE_FLUID_STILL,
                    Reference.FluidColor.WAX_WHITE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_YELLOW = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_YELLOW).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_YELLOW_FLUID_STILL,
                    Reference.FluidColor.WAX_YELLOW_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_BLACK = ITEMS.register(
            UnlocalizedName.BEES_WAX_BLACK, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_BLUE = ITEMS.register(
            UnlocalizedName.BEES_WAX_BLUE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_BROWN = ITEMS.register(
            UnlocalizedName.BEES_WAX_BROWN, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_CYAN = ITEMS.register(
            UnlocalizedName.BEES_WAX_CYAN, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_GRAY = ITEMS.register(
            UnlocalizedName.BEES_WAX_GRAY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_GREEN = ITEMS.register(
            UnlocalizedName.BEES_WAX_GREEN, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_LIGHT_BLUE = ITEMS.register(
            UnlocalizedName.BEES_WAX_LIGHT_BLUE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_LIGHT_GRAY = ITEMS.register(
            UnlocalizedName.BEES_WAX_LIGHT_GRAY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_LIME = ITEMS.register(
            UnlocalizedName.BEES_WAX_LIME, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_MAGENTA = ITEMS.register(
            UnlocalizedName.BEES_WAX_MAGENTA, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_ORANGE = ITEMS.register(
            UnlocalizedName.BEES_WAX_ORANGE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_PINK = ITEMS.register(
            UnlocalizedName.BEES_WAX_PINK, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_PURPLE = ITEMS.register(
            UnlocalizedName.BEES_WAX_PURPLE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_RED = ITEMS.register(
            UnlocalizedName.BEES_WAX_RED, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_WHITE = ITEMS.register(
            UnlocalizedName.BEES_WAX_WHITE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_YELLOW = ITEMS.register(
            UnlocalizedName.BEES_WAX_YELLOW, GrowthcraftItem::new
    );

    public static final RegistryObject<WallOrFloorItem> CANDLE_BLACK = ITEMS.register(
            UnlocalizedName.CANDLE_BLACK,
            () -> new WallOrFloorItem(
                    GrowthcraftApiaryBlocks.CANDLE_BLACK.get(),
                    GrowthcraftApiaryBlocks.CANDLE_BLACK_WALL.get(),
                    (new Item.Properties()).group(ItemGroup.DECORATIONS))
    );

    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_EMPTY = ITEMS.register(
            UnlocalizedName.HONEY_COMB_EMPTY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_FULL = ITEMS.register(
            UnlocalizedName.HONEY_COMB_FULL, GrowthcraftItem::new
    );

    // TODO: Register Honey Bucket and Mead Bucket

    private GrowthcraftApiaryItems() {
        /* Prevent default public constructor */
    }

}

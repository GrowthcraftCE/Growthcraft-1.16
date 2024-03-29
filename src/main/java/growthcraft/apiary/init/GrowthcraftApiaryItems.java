package growthcraft.apiary.init;

import growthcraft.apiary.common.CandleItem;
import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.lib.util.FluidUtils;
import net.minecraft.item.Item;
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

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_HONEY = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.HONEY_FLUID_STILL,
                    Reference.FluidColor.HONEY_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_HONEY_MEAD = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY_MEAD).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.HONEY_MEAD_FLUID_STILL,
                    Reference.FluidColor.HONEY_MEAD_FLUID_COLOR.getColor()
            )
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

    public static final RegistryObject<CandleItem> CANDLE_BLACK = ITEMS.register(
            UnlocalizedName.CANDLE_BLACK,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_BLACK.get(),
                    GrowthcraftApiaryBlocks.CANDLE_BLACK_WALL.get(),
                    Reference.FluidColor.WAX_BLACK_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_BLUE = ITEMS.register(
            UnlocalizedName.CANDLE_BLUE,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_BLUE.get(),
                    GrowthcraftApiaryBlocks.CANDLE_BLUE_WALL.get(),
                    Reference.FluidColor.WAX_BLUE_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_BROWN = ITEMS.register(
            UnlocalizedName.CANDLE_BROWN,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_BROWN.get(),
                    GrowthcraftApiaryBlocks.CANDLE_BROWN_WALL.get(),
                    Reference.FluidColor.WAX_BROWN_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_CYAN = ITEMS.register(
            UnlocalizedName.CANDLE_CYAN,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_CYAN.get(),
                    GrowthcraftApiaryBlocks.CANDLE_CYAN_WALL.get(),
                    Reference.FluidColor.WAX_CYAN_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_GRAY = ITEMS.register(
            UnlocalizedName.CANDLE_GRAY,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_GRAY.get(),
                    GrowthcraftApiaryBlocks.CANDLE_GRAY_WALL.get(),
                    Reference.FluidColor.WAX_GRAY_COLOR.getColor())
    );
    public static final RegistryObject<CandleItem> CANDLE_GREEN = ITEMS.register(
            UnlocalizedName.CANDLE_GREEN,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_GREEN.get(),
                    GrowthcraftApiaryBlocks.CANDLE_GREEN_WALL.get(),
                    Reference.FluidColor.WAX_GREEN_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_LIGHT_BLUE = ITEMS.register(
            UnlocalizedName.CANDLE_LIGHT_BLUE,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_LIGHT_BLUE.get(),
                    GrowthcraftApiaryBlocks.CANDLE_LIGHT_BLUE_WALL.get(),
                    Reference.FluidColor.WAX_LIGHT_BLUE_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_LIGHT_GRAY = ITEMS.register(
            UnlocalizedName.CANDLE_LIGHT_GRAY,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_LIGHT_GRAY.get(),
                    GrowthcraftApiaryBlocks.CANDLE_LIGHT_GRAY_WALL.get(),
                    Reference.FluidColor.WAX_LIGHT_GRAY_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_LIME = ITEMS.register(
            UnlocalizedName.CANDLE_LIME,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_LIME.get(),
                    GrowthcraftApiaryBlocks.CANDLE_LIME_WALL.get(),
                    Reference.FluidColor.WAX_LIME_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_MAGENTA = ITEMS.register(
            UnlocalizedName.CANDLE_MAGENTA,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_MAGENTA.get(),
                    GrowthcraftApiaryBlocks.CANDLE_MAGENTA_WALL.get(),
                    Reference.FluidColor.WAX_MAGENTA_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_ORANGE = ITEMS.register(
            UnlocalizedName.CANDLE_ORANGE,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_ORANGE.get(),
                    GrowthcraftApiaryBlocks.CANDLE_ORANGE_WALL.get(),
                    Reference.FluidColor.WAX_ORANGE_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_PINK = ITEMS.register(
            UnlocalizedName.CANDLE_PINK,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_PINK.get(),
                    GrowthcraftApiaryBlocks.CANDLE_PINK_WALL.get(),
                    Reference.FluidColor.WAX_PINK_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_PURPLE = ITEMS.register(
            UnlocalizedName.CANDLE_PURPLE,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_PURPLE.get(),
                    GrowthcraftApiaryBlocks.CANDLE_PURPLE_WALL.get(),
                    Reference.FluidColor.WAX_PURPLE_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_RED = ITEMS.register(
            UnlocalizedName.CANDLE_RED,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_RED.get(),
                    GrowthcraftApiaryBlocks.CANDLE_RED_WALL.get(),
                    Reference.FluidColor.WAX_RED_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_WHITE = ITEMS.register(
            UnlocalizedName.CANDLE_WHITE,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_WHITE.get(),
                    GrowthcraftApiaryBlocks.CANDLE_WHITE_WALL.get(),
                    Reference.FluidColor.WAX_WHITE_COLOR.getColor())
    );

    public static final RegistryObject<CandleItem> CANDLE_YELLOW = ITEMS.register(
            UnlocalizedName.CANDLE_YELLOW,
            () -> new CandleItem(
                    GrowthcraftApiaryBlocks.CANDLE_YELLOW.get(),
                    GrowthcraftApiaryBlocks.CANDLE_YELLOW_WALL.get(),
                    Reference.FluidColor.WAX_YELLOW_COLOR.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_EMPTY = ITEMS.register(
            UnlocalizedName.HONEY_COMB_EMPTY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_FULL = ITEMS.register(
            UnlocalizedName.HONEY_COMB_FULL, GrowthcraftItem::new
    );

    private GrowthcraftApiaryItems() {
        /* Prevent default public constructor */
    }

}

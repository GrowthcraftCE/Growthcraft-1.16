package growthcraft.apiary.init;

import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftItem;
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

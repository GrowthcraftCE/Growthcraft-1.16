package growthcraft.cellar.init;

import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftEnchantedItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<GrowthcraftItem> brew_kettle_lid = ITEMS.register(
            UnlocalizedName.BREW_KETTLE_LID,
            () -> new GrowthcraftItem(1)
    );

    public static final RegistryObject<BucketItem> bucket_wort = ITEMS.register(
            UnlocalizedName.WORT_BUCKET,
            () -> new BucketItem(
                    GrowthcraftCellarFluids.WORT_FLUID,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
    );
    public static final RegistryObject<BucketItem> bucket_pale_ale = ITEMS.register(
            UnlocalizedName.PALE_ALE_BUCKET,
            () -> new BucketItem(
                    GrowthcraftCellarFluids.PALE_ALE_FLUID,
                    new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1)
            )
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

    private GrowthcraftCellarItems() {
        /* Prevent generation of default public constructor */
    }
}

package growthcraft.apiary.init;

import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftItem;
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

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_BASE = ITEMS.register(
            UnlocalizedName.BEES_WAX, GrowthcraftItem::new
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

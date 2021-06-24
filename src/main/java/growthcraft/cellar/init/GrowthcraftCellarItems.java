package growthcraft.cellar.init;

import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
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
}

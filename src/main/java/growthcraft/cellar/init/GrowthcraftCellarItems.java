package growthcraft.cellar.init;

import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.core.Growthcraft;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<BucketItem> BUCKET_WORT;

    static {
        BUCKET_WORT = ITEMS.register(
                UnlocalizedName.WORT_BUCKET,
                () -> new BucketItem(
                        () -> GrowthcraftCellarFuilds.WORT_FLUID.get(),
                        new Item.Properties().group(Growthcraft.itemGroup).maxStackSize(1)
                )
        );
    }

    private GrowthcraftCellarItems() { /* Prevent default public constructor */ }
}

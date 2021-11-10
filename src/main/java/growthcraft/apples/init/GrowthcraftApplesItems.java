package growthcraft.apples.init;

import growthcraft.apples.common.item.AppleSeedsItem;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    private GrowthcraftApplesItems() { /* Prevent default public constructor */ }

    public static final RegistryObject<AppleSeedsItem> APPLE_SEEDS = ITEMS.register(
            UnlocalizedName.APPLE_SEEDS,
            AppleSeedsItem::new
    );

}

package growthcraft.apples.init;

import growthcraft.apples.common.item.AppleSeedsItem;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.shared.UnlocalizedName;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    private GrowthcraftApplesItems() { /* Prevent default public constructor */ }

    public static final RegistryObject<AppleSeedsItem> APPLE_SEEDS = ITEMS.register(UnlocalizedName.APPLE_SEEDS, AppleSeedsItem::new);

    public static final void registerCompostables() {
        float f = 0.3F;
        float f1 = 0.5F;
        float f2 = 0.65F;
        float f3 = 0.85F;
        float f4 = 1.0F;

        ComposterBlock.CHANCES.put(GrowthcraftApplesItems.APPLE_SEEDS.get(), f1);
    }

}

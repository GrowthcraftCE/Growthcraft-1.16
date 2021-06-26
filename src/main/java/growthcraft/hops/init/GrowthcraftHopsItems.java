package growthcraft.hops.init;

import growthcraft.hops.shared.Reference;
import growthcraft.hops.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftHopsItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<GrowthcraftItem> hops = ITEMS.register(
            UnlocalizedName.HOPS,
            GrowthcraftItem::new
    );

}

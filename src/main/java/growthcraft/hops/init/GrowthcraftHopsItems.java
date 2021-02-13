package growthcraft.hops.init;

import growthcraft.hops.common.item.ItemHops;
import growthcraft.hops.shared.Reference;
import growthcraft.hops.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftBlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftHopsItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<ItemHops> hops;
    public static final RegistryObject<GrowthcraftBlockItem> hopsSeeds;

    static {
        hops = ITEMS.register(
                UnlocalizedName.HOPS,
                () -> new ItemHops());
        hopsSeeds = ITEMS.register(
                UnlocalizedName.HOPS_SEEDS,
                () -> new GrowthcraftBlockItem(GrowthcraftHopsBlocks.hopsBush.get()));
    }

    private GrowthcraftHopsItems() { /* Prevent default public constructor */ }

}

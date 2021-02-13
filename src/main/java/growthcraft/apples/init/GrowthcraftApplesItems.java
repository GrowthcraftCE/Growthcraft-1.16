package growthcraft.apples.init;

import growthcraft.apples.common.item.ItemAppleSeeds;
import growthcraft.apples.shared.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<ItemAppleSeeds> appleSeeds;

    static {
        appleSeeds = ITEMS.register("apple_seeds", () -> new ItemAppleSeeds());
    }

    private GrowthcraftApplesItems() { /* Prevent default public constructor */ }

}

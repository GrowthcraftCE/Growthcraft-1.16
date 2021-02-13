package growthcraft.milk.init;

import growthcraft.milk.shared.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    static {
        /**
         * bambooCoal = ITEMS.register("bamboo_coal", () -> new ItemBambooCoal("bamboo_coal"));
         */
    }

    /**
     * public static final RegistryObject<ItemBambooCoal> bambooCoal;
     */
    private GrowthcraftMilkItems() { /* Prevent default public constructor */ }

}

package growthcraft.bamboo.init;

import growthcraft.bamboo.common.item.ItemBambooCoal;
import growthcraft.bamboo.common.item.ItemBambooStick;
import growthcraft.bamboo.shared.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftBambooItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<ItemBambooCoal> bambooCoal;
    public static final RegistryObject<ItemBambooStick> bambooStick;

    private GrowthcraftBambooItems() { /* Disable default public constructor */ }

    static {
        bambooCoal = ITEMS.register("bamboo_coal", () -> new ItemBambooCoal());
        bambooStick = ITEMS.register("bamboo_stick", () -> new ItemBambooStick());
    }
}

package growthcraft.core.init;

import growthcraft.core.common.item.ItemCrowbar;
import growthcraft.core.shared.Reference;
import growthcraft.core.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    private GrowthcraftItems() { /* Disable Default Public Constructor */ }

    public static final RegistryObject<ItemCrowbar> CROWBAR_WHITE = ITEMS.register(
            UnlocalizedName.CROWBAR_WHITE,
            () -> new ItemCrowbar()
    );



}

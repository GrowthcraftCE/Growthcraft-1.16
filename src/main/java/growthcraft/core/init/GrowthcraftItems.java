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

    public static final RegistryObject<ItemCrowbar> CROWBAR_BLACK = ITEMS.register(
            UnlocalizedName.CROWBAR_BLACK, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_BLUE = ITEMS.register(
            UnlocalizedName.CROWBAR_BLUE, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_BROWN = ITEMS.register(
            UnlocalizedName.CROWBAR_BROWN, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_CYAN = ITEMS.register(
            UnlocalizedName.CROWBAR_CYAN, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_GRAY = ITEMS.register(
            UnlocalizedName.CROWBAR_GRAY, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_GREEN = ITEMS.register(
            UnlocalizedName.CROWBAR_GREEN, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_LIGHT_BLUE = ITEMS.register(
            UnlocalizedName.CROWBAR_LIGHT_BLUE, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_LIGHT_GRAY = ITEMS.register(
            UnlocalizedName.CROWBAR_LIGHT_GRAY, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_LIME = ITEMS.register(
            UnlocalizedName.CROWBAR_LIME, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_MAGENTA = ITEMS.register(
            UnlocalizedName.CROWBAR_MAGENTA, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_ORANGE = ITEMS.register(
            UnlocalizedName.CROWBAR_ORANGE, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_PINK = ITEMS.register(
            UnlocalizedName.CROWBAR_PINK, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_PURPLE = ITEMS.register(
            UnlocalizedName.CROWBAR_PURPLE, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_RED = ITEMS.register(
            UnlocalizedName.CROWBAR_RED, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_WHITE = ITEMS.register(
            UnlocalizedName.CROWBAR_WHITE, () -> new ItemCrowbar()
    );
    public static final RegistryObject<ItemCrowbar> CROWBAR_YELLOW = ITEMS.register(
            UnlocalizedName.CROWBAR_YELLOW, () -> new ItemCrowbar()
    );

}

package growthcraft.core.init;

import growthcraft.core.common.item.CrowbarItem;
import growthcraft.core.common.item.RopeLinenItem;
import growthcraft.core.shared.Reference;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    private GrowthcraftItems() { /* Disable Default Public Constructor */ }

    public static final RegistryObject<CrowbarItem> CROWBAR_BLACK = ITEMS.register(
            Reference.CROWBAR_BLACK, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_BLUE = ITEMS.register(
            Reference.CROWBAR_BLUE, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_BROWN = ITEMS.register(
            Reference.CROWBAR_BROWN, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_CYAN = ITEMS.register(
            Reference.CROWBAR_CYAN, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_GRAY = ITEMS.register(
            Reference.CROWBAR_GRAY, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_GREEN = ITEMS.register(
            Reference.CROWBAR_GREEN, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_LIGHT_BLUE = ITEMS.register(
            Reference.CROWBAR_LIGHT_BLUE, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_LIGHT_GRAY = ITEMS.register(
            Reference.CROWBAR_LIGHT_GRAY, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_LIME = ITEMS.register(
            Reference.CROWBAR_LIME, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_MAGENTA = ITEMS.register(
            Reference.CROWBAR_MAGENTA, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_ORANGE = ITEMS.register(
            Reference.CROWBAR_ORANGE, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_PINK = ITEMS.register(
            Reference.CROWBAR_PINK, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_PURPLE = ITEMS.register(
            Reference.CROWBAR_PURPLE, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_RED = ITEMS.register(
            Reference.CROWBAR_RED, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_WHITE = ITEMS.register(
            Reference.CROWBAR_WHITE, () -> new CrowbarItem()
    );
    public static final RegistryObject<CrowbarItem> CROWBAR_YELLOW = ITEMS.register(
            Reference.CROWBAR_YELLOW, () -> new CrowbarItem()
    );

    public static final RegistryObject<RopeLinenItem> ROPE_LINEN = ITEMS.register(
            Reference.ROPE_LINEN, () -> new RopeLinenItem(GrowthcraftBlocks.ROPE_LINEN.get())
    );

    public static final RegistryObject<GrowthcraftItem> SALT = ITEMS.register(
            Reference.SALT_ITEM, GrowthcraftItem::new
    );

}

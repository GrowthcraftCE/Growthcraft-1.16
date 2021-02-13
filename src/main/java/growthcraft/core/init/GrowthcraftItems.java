package growthcraft.core.init;

import growthcraft.core.common.item.ItemCrowbar;
import growthcraft.core.common.item.ItemSalt;
import growthcraft.core.shared.Reference;
import growthcraft.core.shared.UnlocalizedName;
import growthcraft.lib.common.item.GrowthcraftItemRope;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<ItemCrowbar>  crowbarWhite;
    public static final RegistryObject<ItemCrowbar>  crowbarOrange;
    public static final RegistryObject<ItemCrowbar>  crowbarMagenta;
    public static final RegistryObject<ItemCrowbar>  crowbarLightBlue;
    public static final RegistryObject<ItemCrowbar>  crowbarYellow;
    public static final RegistryObject<ItemCrowbar>  crowbarLime;
    public static final RegistryObject<ItemCrowbar>  crowbarPink;
    public static final RegistryObject<ItemCrowbar>  crowbarGray;
    public static final RegistryObject<ItemCrowbar> crowbarLightGray;
    public static final RegistryObject<ItemCrowbar> crowbarCyan;
    public static final RegistryObject<ItemCrowbar> crowbarPurple;
    public static final RegistryObject<ItemCrowbar> crowbarBlue;
    public static final RegistryObject<ItemCrowbar> crowbarBrown;
    public static final RegistryObject<ItemCrowbar> crowbarGreen;
    public static final RegistryObject<ItemCrowbar> crowbarRed;
    public static final RegistryObject<ItemCrowbar> crowbarBlack;
    public static final RegistryObject<ItemSalt> salt;

    public static final RegistryObject<GrowthcraftItemRope> rope_linen;

    private GrowthcraftItems() { /* Disable default public constructor */ }

    static {
        crowbarWhite = ITEMS.register("crowbar_white", () -> new ItemCrowbar());
        crowbarOrange = ITEMS.register("crowbar_orange", () -> new ItemCrowbar());
        crowbarMagenta = ITEMS.register("crowbar_magenta", () -> new ItemCrowbar());
        crowbarLightBlue = ITEMS.register("crowbar_light_blue", () -> new ItemCrowbar());
        crowbarYellow = ITEMS.register("crowbar_yellow", () -> new ItemCrowbar());
        crowbarLime = ITEMS.register("crowbar_lime", () -> new ItemCrowbar());
        crowbarPink = ITEMS.register("crowbar_pink", () -> new ItemCrowbar());
        crowbarGray = ITEMS.register("crowbar_gray", () -> new ItemCrowbar());
        crowbarLightGray = ITEMS.register("crowbar_light_gray", () -> new ItemCrowbar());
        crowbarCyan = ITEMS.register("crowbar_cyan", () -> new ItemCrowbar());
        crowbarPurple = ITEMS.register("crowbar_purple", () -> new ItemCrowbar());
        crowbarBlue = ITEMS.register("crowbar_blue", () -> new ItemCrowbar());
        crowbarBrown = ITEMS.register("crowbar_brown", () -> new ItemCrowbar());
        crowbarGreen = ITEMS.register("crowbar_green", () -> new ItemCrowbar());
        crowbarRed = ITEMS.register("crowbar_red", () -> new ItemCrowbar());
        crowbarBlack = ITEMS.register("crowbar_black", () -> new ItemCrowbar());
        salt = ITEMS.register(UnlocalizedName.SALT_ITEM, () -> new ItemSalt());

        rope_linen = ITEMS.register(UnlocalizedName.ROPE_LINEN, () -> new GrowthcraftItemRope(GrowthcraftBlocks.ROPE_LINEN.get()));
    }

}

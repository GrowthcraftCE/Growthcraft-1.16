package growthcraft.core.shared;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Reference {

    public static final String MODID = "growthcraft";
    public static final String NAME = "Growthcraft";
    public static final String VERSION = "6.1.4";

    public static final String CROWBAR_BLACK = "crowbar_black";
    public static final String CROWBAR_BLUE = "crowbar_blue";
    public static final String CROWBAR_BROWN = "crowbar_brown";
    public static final String CROWBAR_CYAN = "crowbar_cyan";
    public static final String CROWBAR_GRAY = "crowbar_gray";
    public static final String CROWBAR_GREEN = "crowbar_green";
    public static final String CROWBAR_LIGHT_BLUE = "crowbar_light_blue";
    public static final String CROWBAR_LIGHT_GRAY = "crowbar_light_gray";
    public static final String CROWBAR_LIME = "crowbar_lime";
    public static final String CROWBAR_MAGENTA = "crowbar_magenta";
    public static final String CROWBAR_ORANGE = "crowbar_orange";
    public static final String CROWBAR_PINK = "crowbar_pink";
    public static final String CROWBAR_PURPLE = "crowbar_purple";
    public static final String CROWBAR_RED = "crowbar_red";
    public static final String CROWBAR_WHITE = "crowbar_white";
    public static final String CROWBAR_YELLOW = "crowbar_yellow";

    public static final String SALT_ORE = "salt_ore";
    public static final String SALT_BLOCK = "salt_block";
    public static final String SALT_ITEM = "salt";

    public static final String ROPE = "rope";
    public static final String ROPE_LINEN = "rope_linen";

    public static final String TAG_ROPE = "rope";
    public static final String TAG_ROPE_FENCE = "rope_fence";
    public static final String TAG_HEATSOURCES = "heatsources";

    /**
     * ItemGroup used for all Growthcraft Modules.
     */
    public static final ItemGroup growthcraftCreativeTab = new ItemGroup(Reference.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GrowthcraftCellarBlocks.barrel_ferment_oak.get());
        }
    };

}

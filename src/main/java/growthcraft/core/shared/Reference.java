package growthcraft.core.shared;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Reference {

    public static final String MODID = "growthcraft";
    public static final String NAME = "Growthcraft";
    public static final String VERSION = "6.0.0.0";

    /**
     * ItemGroup used for all Growthcraft Modules.
     */
    public static final ItemGroup growthraftCreativeTab = new ItemGroup(MODID) {
        @Override
        public ItemStack createIcon() {
            // TODO: Replace CreateiveTab icon with Bamboo Plank block.
            return new ItemStack(Blocks.DIRT);
        }
    };

}

package growthcraft.apples.common.block;

import growthcraft.lib.common.block.GrowthcraftStairsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AppleWoodStairs extends GrowthcraftStairsBlock {

    public AppleWoodStairs(Block block, String unlocalizedName) {
        super(block, getInitProperties(), unlocalizedName);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.WOOD);
        properties.hardnessAndResistance(1.5F);
        properties.sound(SoundType.WOOD);
        return properties;
    }

}

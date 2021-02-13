package growthcraft.deco.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;

public class WoolStairs extends StairsBlock {

    public WoolStairs() {
        super(Blocks.WHITE_WOOL::getDefaultState, getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.WOOL);
        properties.hardnessAndResistance(0.8F);
        properties.sound(SoundType.CLOTH);
        return properties;
    }

}

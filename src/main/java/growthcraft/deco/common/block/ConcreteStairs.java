package growthcraft.deco.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;

public class ConcreteStairs extends StairsBlock {

    public ConcreteStairs() {
        super(Blocks.WHITE_CONCRETE::getDefaultState, getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.ROCK);
        properties.hardnessAndResistance(1.8F);
        properties.sound(SoundType.CLOTH);
        return properties;
    }
}

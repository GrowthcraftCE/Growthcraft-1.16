package growthcraft.deco.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;

public class TerracottaStairs extends StairsBlock {

    public TerracottaStairs() {
        super(Blocks.WHITE_TERRACOTTA::getDefaultState, getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.ROCK);
        properties.hardnessAndResistance(1.25F, 4.2F);
        properties.sound(SoundType.CLOTH);
        return properties;
    }
}

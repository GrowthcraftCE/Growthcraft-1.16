package growthcraft.apples.common.block;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;

public class AppleStairsBlock extends StairsBlock {

    public AppleStairsBlock() {
        super(GrowthcraftApplesBlocks.applePlankStairs.get()::getDefaultState, getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.ROCK);
        properties.hardnessAndResistance(1.8F);
        properties.sound(SoundType.CLOTH);
        return properties;
    }
}

package growthcraft.lib.common.block;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GrowthcraftTreeLeaves extends LeavesBlock {

    public GrowthcraftTreeLeaves() {
        this(getInitProperties());
    }

    public GrowthcraftTreeLeaves(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.LEAVES);
        properties.tickRandomly();
        properties.hardnessAndResistance(0.2F, 0.2F);
        properties.sound(SoundType.PLANT);
        properties.notSolid();
        return properties;
    }


}

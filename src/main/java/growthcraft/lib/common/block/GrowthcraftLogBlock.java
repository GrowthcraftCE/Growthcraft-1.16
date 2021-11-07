package growthcraft.lib.common.block;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GrowthcraftLogBlock extends RotatedPillarBlock {

    public GrowthcraftLogBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftLogBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.harvestTool(ToolType.AXE);
        properties.harvestLevel(1);
        properties.sound(SoundType.WOOD);
        return properties;
    }

}

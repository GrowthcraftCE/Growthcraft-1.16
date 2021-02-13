package growthcraft.lib.common.block;

import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class GrowthcraftLogBlock extends LogBlock {

    public GrowthcraftLogBlock() {
        this(MaterialColor.WOOD, getInitProperties(Material.WOOD));
    }

    public GrowthcraftLogBlock(MaterialColor verticalColorIn, Properties properties) {
        super(verticalColorIn, properties);
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

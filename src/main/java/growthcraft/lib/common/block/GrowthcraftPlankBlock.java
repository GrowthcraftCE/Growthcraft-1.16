package growthcraft.lib.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GrowthcraftPlankBlock extends Block {

    public GrowthcraftPlankBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftPlankBlock(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftPlankBlock(Properties properties) {
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

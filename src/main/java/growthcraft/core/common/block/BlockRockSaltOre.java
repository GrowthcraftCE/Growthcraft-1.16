package growthcraft.core.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockRockSaltOre extends Block {

    public BlockRockSaltOre(String unlocalizedName) {
        super(getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.ROCK);
        properties.hardnessAndResistance(3.0F, 5.0F);
        properties.harvestTool(ToolType.PICKAXE);
        properties.harvestLevel(1);
        properties.sound(SoundType.STONE);
        return properties;
    }

}

package growthcraft.lib.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;

public class GrowthcraftStairsBlock extends StairsBlock {

    private final String unlocalizedName;

    public GrowthcraftStairsBlock(Block block, Material material, String unlocalizedName) {
        super(block::getDefaultState, getInitProperties(material));
        this.unlocalizedName = unlocalizedName;
    }

    public GrowthcraftStairsBlock(Block block, Properties properties, String unlocalizedName) {
        super(block::getDefaultState, properties);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(1.0F);
        properties.sound(SoundType.STONE);
        return properties;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

}

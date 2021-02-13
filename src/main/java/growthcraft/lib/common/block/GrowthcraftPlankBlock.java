package growthcraft.lib.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GrowthcraftPlankBlock extends Block {

    private final String unlocalizedName;

    public GrowthcraftPlankBlock(String unlocalizedName) {
        this(unlocalizedName, getInitProperties(Material.WOOD));
    }

    public GrowthcraftPlankBlock(String unlocalizedName, Properties properties) {
        super(properties);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.harvestTool(ToolType.AXE);
        properties.harvestLevel(1);
        properties.sound(SoundType.WOOD);
        return properties;
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}

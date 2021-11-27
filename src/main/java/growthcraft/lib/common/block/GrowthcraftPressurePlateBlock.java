package growthcraft.lib.common.block;

import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GrowthcraftPressurePlateBlock extends PressurePlateBlock {

    public GrowthcraftPressurePlateBlock() {
        this(Sensitivity.EVERYTHING, getInitProperties(Material.WOOD));
    }

    public GrowthcraftPressurePlateBlock(Material material) {
        this(Sensitivity.EVERYTHING, getInitProperties(material));
    }

    /**
     * Constructor for a standard wooden pressure plate.
     *
     * @param unlocalizedName Unlocalized name
     */
    @Deprecated
    public GrowthcraftPressurePlateBlock(String unlocalizedName) {
        this(Sensitivity.EVERYTHING, getInitProperties(Material.WOOD));
    }

    public GrowthcraftPressurePlateBlock(Sensitivity sensitivity, Properties properties) {
        super(sensitivity, properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(1.5F);
        properties.sound(SoundType.WOOD);
        properties.harvestTool(ToolType.AXE);
        return properties;
    }

}

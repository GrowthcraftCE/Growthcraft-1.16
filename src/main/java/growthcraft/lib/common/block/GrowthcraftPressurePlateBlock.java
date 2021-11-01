package growthcraft.lib.common.block;

import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GrowthcraftPressurePlateBlock extends PressurePlateBlock {

    private final String unlocalizedName;

    /**
     * Constructor for a standar wooden pressure plate.
     *
     * @param unlocalizedName Unlocalized name
     */
    public GrowthcraftPressurePlateBlock(String unlocalizedName) {
        this(unlocalizedName, Sensitivity.EVERYTHING, getInitProperties(Material.WOOD));
    }

    /**
     * Constructor for a wooden pressure plate with custom sensitivity.
     *
     * @param unlocalizedName Unlocalized name
     * @param sensitivity     Sensitivity type.
     */
    public GrowthcraftPressurePlateBlock(String unlocalizedName, Sensitivity sensitivity) {
        this(unlocalizedName, sensitivity, getInitProperties(Material.WOOD));
    }

    public GrowthcraftPressurePlateBlock(String unlocalizedName, Sensitivity sensitivity, Properties properties) {
        super(sensitivity, properties);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(1.5F);
        properties.sound(SoundType.WOOD);
        return properties;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }
}

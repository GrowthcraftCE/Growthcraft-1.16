package growthcraft.lib.common.block;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;

public class GrowthcraftSlabBlock extends SlabBlock {

    private String unlocalizedName;

    public GrowthcraftSlabBlock(String unlocalizedName) {
        this(unlocalizedName, getInitProperties(Material.WOOD));
    }

    public GrowthcraftSlabBlock(String unlocalizedName, Properties properties) {
        super(properties);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.notSolid();
        return properties;
    }
}

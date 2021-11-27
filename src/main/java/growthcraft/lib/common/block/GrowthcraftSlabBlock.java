package growthcraft.lib.common.block;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;

public class GrowthcraftSlabBlock extends SlabBlock {

    public GrowthcraftSlabBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftSlabBlock(Material material) {
        this(getInitProperties(material));
    }

    @Deprecated
    public GrowthcraftSlabBlock(String unlocalizedName) {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftSlabBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.notSolid();
        return properties;
    }
}

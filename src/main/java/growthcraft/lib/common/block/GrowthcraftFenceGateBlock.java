package growthcraft.lib.common.block;

import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class GrowthcraftFenceGateBlock extends FenceGateBlock {

    private final String unlocalizedName;

    public GrowthcraftFenceGateBlock(String unlocalizedName) {
        super(getInitProperties(Material.WOOD));
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        return properties;
    }

}

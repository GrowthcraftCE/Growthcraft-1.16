package growthcraft.lib.common.block;

import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class GrowthcraftFenceBlock extends FenceBlock {

    private final String unlocalizedName;

    public GrowthcraftFenceBlock(String unlocalizedName) {
        super(getInitProperties(Material.WOOD));
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        properties.hardnessAndResistance(2.0F, 3.0F);
        return properties;
    }

}

package growthcraft.lib.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;

public class GrowthcraftButtonBlock extends WoodButtonBlock {

    public GrowthcraftButtonBlock() {
        super(getInitProperties(Material.WOOD));
    }

    public GrowthcraftButtonBlock(String unlocalizedName) {
        super(getInitProperties(Material.WOOD));
    }

    protected GrowthcraftButtonBlock(String unlocalizedName, Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(0.5F);
        properties.doesNotBlockMovement();
        properties.sound(SoundType.WOOD);
        return properties;
    }
}

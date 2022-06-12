package growthcraft.lib.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GrowthcraftBlock extends Block {

    public GrowthcraftBlock() {
        this(getInitProperties(Material.WOOD, SoundType.WOOD));
    }

    public GrowthcraftBlock(Material material) {
        this(getInitProperties(material, SoundType.WOOD));
    }

    public GrowthcraftBlock(Material material, SoundType soundType) {
        this(getInitProperties(material, soundType));
    }

    public GrowthcraftBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material, SoundType soundType) {
        Properties properties = Properties.create(material);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}

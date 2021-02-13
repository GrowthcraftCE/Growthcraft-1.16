package growthcraft.core.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSalt extends Block {

    public BlockSalt(String unlocalizedName) {
        super(getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.SAND);
        properties.sound(SoundType.SAND);
        return  properties;
    }

}

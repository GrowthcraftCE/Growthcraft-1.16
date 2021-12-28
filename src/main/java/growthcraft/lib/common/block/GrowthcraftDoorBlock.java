package growthcraft.lib.common.block;

import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class GrowthcraftDoorBlock extends DoorBlock {

    public GrowthcraftDoorBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftDoorBlock(Material material) {
        this(getInitProperties(material));
    }

    @Deprecated
    public GrowthcraftDoorBlock(String unlocalizedName) {
        this();
    }

    public GrowthcraftDoorBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        properties.notSolid();
        return properties;
    }

}

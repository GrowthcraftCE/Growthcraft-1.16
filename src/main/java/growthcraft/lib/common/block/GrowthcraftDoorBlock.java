package growthcraft.lib.common.block;

import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class GrowthcraftDoorBlock extends DoorBlock {

    private final String unlocalizedName;

    public GrowthcraftDoorBlock(String unlocalizedName) {
        this(unlocalizedName, getInitProperties(Material.WOOD));
    }

    public GrowthcraftDoorBlock(String unlocalizedName, Properties properties) {
        super(properties);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        properties.notSolid();
        return properties;
    }

}

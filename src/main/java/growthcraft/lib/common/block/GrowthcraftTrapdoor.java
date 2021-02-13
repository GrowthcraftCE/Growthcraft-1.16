package growthcraft.lib.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GrowthcraftTrapdoor extends TrapDoorBlock {

    private final String unlocalizedName;

    public GrowthcraftTrapdoor(String unlocalizedName, Material material) {
        this(unlocalizedName, getInitProperties(material));
    }

    public GrowthcraftTrapdoor(String unlocalizedName, Properties properties) {
        super(properties);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.harvestTool(ToolType.AXE);
        properties.harvestLevel(1);
        properties.sound(SoundType.WOOD);
        properties.notSolid();
        return properties;
    }

    public String getUnlocalizedName() { return unlocalizedName; }



}

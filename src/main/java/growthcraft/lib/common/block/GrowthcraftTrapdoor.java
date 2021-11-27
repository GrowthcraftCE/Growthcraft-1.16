package growthcraft.lib.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GrowthcraftTrapdoor extends TrapDoorBlock {

    public GrowthcraftTrapdoor() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftTrapdoor(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftTrapdoor(Properties properties) {
        super(properties);
    }

    /**
     * @param unlocalizedName
     * @param material
     * @deprecated Use {@link GrowthcraftTrapdoor#GrowthcraftTrapdoor(Material)} instead.
     */
    @Deprecated
    public GrowthcraftTrapdoor(String unlocalizedName, Material material) {
        this(unlocalizedName, getInitProperties(material));
    }

    /**
     * @param unlocalizedName
     * @param properties
     * @deprecated Use {@link GrowthcraftTrapdoor#GrowthcraftTrapdoor(Properties)} instead.
     */
    @Deprecated
    public GrowthcraftTrapdoor(String unlocalizedName, Properties properties) {
        super(properties);
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

}

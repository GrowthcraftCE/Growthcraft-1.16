package growthcraft.lib.common.block;

import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class GrowthcraftFenceGateBlock extends FenceGateBlock {

    /**
     * Basic wooden Fence Gate
     */
    public GrowthcraftFenceGateBlock() {
        this(getInitProperties(Material.WOOD));
    }

    /**
     * Fence Gate with custom material.
     *
     * @param material Material for default block properties.
     */
    public GrowthcraftFenceGateBlock(Material material) {
        this(getInitProperties(material));
    }

    /**
     * Fence Gate with custom block properties
     *
     * @param properties Block properties.
     */
    public GrowthcraftFenceGateBlock(Properties properties) {
        super(properties);
    }

    /**
     * @param unlocalizedName
     * @deprecated Use {@link GrowthcraftFenceGateBlock#GrowthcraftFenceGateBlock()} instead.
     */
    @Deprecated
    public GrowthcraftFenceGateBlock(String unlocalizedName) {
        super(getInitProperties(Material.WOOD));
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        return properties;
    }

}

package growthcraft.apiary.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;

import java.awt.*;

public class CandleBlock extends TorchBlock {
    private final int color;

    public CandleBlock(Color color) {
        super(getInitProperties(Material.MISCELLANEOUS), ParticleTypes.FLAME);
        this.color = color.getRGB();
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.zeroHardnessAndResistance();
        properties.doesNotBlockMovement();
        properties.setLightLevel(state -> {
            return 15;
        });
        properties.sound(SoundType.WOOD);
        return properties;
    }

    public int getColor() {
        return this.color;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

}

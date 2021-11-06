package growthcraft.lib.common.block;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;

import java.util.function.Supplier;

public class GrowthcraftSaplingBlock extends SaplingBlock {

    private final Supplier<Tree> tree;

    public GrowthcraftSaplingBlock(Supplier<Tree> tree) {
        this(tree, getInitProperties());
    }

    public GrowthcraftSaplingBlock(Supplier<Tree> tree, Properties properties) {
        super(tree.get(), properties);
        this.tree = tree;
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.PLANTS);
        properties.doesNotBlockMovement();
        properties.tickRandomly();
        properties.hardnessAndResistance(0.0F, 0.0F);
        properties.sound(SoundType.PLANT);
        properties.notSolid();
        return properties;
    }

}

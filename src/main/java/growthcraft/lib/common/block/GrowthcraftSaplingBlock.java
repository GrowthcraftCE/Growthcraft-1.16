package growthcraft.lib.common.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

public class GrowthcraftSaplingBlock extends BushBlock implements IGrowable {

    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(
            4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D
    );

    private final Supplier<Tree> tree;

    public GrowthcraftSaplingBlock(Supplier<Tree> tree) {
        this(tree, getInitProperties());
    }

    public GrowthcraftSaplingBlock(Supplier<Tree> tree, Properties properties) {
        super(properties);
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

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.tick(state, world, pos, rand);
        if (!world.isAreaLoaded(pos, 1)) {
            return;
        }
        // TODO[]: Create config for min-light level for apple tree growing default is 9
        if (world.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(world, pos, state, rand);
        }
    }

    public void grow(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        if (state.get(STAGE) == 0) {
            world.setBlockState(pos, state.cycle(STAGE), 4);
        } else {
            if (!ForgeEventFactory.saplingGrowTree(world, random, pos)) {
                return;
            }
            this.tree.get().place(world, world.getChunkProvider().getChunkGenerator(), pos, state, random);
        }
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.grow(world, pos, state, random);
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        // TODO[]: Create config for chance for apple sapling bonemeal grow. Default is 45%
        return worldIn.rand.nextInt(100) < 45;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}

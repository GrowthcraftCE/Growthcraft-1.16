package growthcraft.milk.lib.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.awt.*;
import java.util.Random;

public class CheeseCurdBlock extends Block {

    private final int color;

    public static final VoxelShape BASE_BOUNDING_BOX = Block.makeCuboidShape(
            4.0D, 2.0D, 4.0D,
            12.0D, 8.0D, 12.0D
    );

    private static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;

    public CheeseCurdBlock(Color color) {
        super(getInitProperties());
        this.color = color.getRGB();
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.MUSHROOM_STEM);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(AGE);
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 7;
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), Integer.valueOf(age));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }
        int i = this.getAge(state);
        worldIn.setBlockState(pos, this.withAge(i + 1), 2);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return !this.isMaxAge(state);
    }

    public boolean isMaxAge(BlockState state) {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        return !world.isAirBlock(pos.up());
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return BASE_BOUNDING_BOX;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }
}

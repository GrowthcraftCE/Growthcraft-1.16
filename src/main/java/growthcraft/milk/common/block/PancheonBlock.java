package growthcraft.milk.common.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class PancheonBlock extends HorizontalBlock {

    public static final VoxelShape BASE_BOUNDING_BOX = makeCuboidShape(
            0.0D, 0.0D, 0.0D,
            16.0F, 5.0D, 16.0D
    );
    public static final VoxelShape INSIDE_BOUNDING_BOX = makeCuboidShape(
            1.0D, 1.0D, 1.0D,
            15.0F, 5.0D, 15.0D
    );

    public PancheonBlock() {
        this(getInitProperties());
    }

    public PancheonBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.CLAY);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.combineAndSimplify(
                BASE_BOUNDING_BOX,
                INSIDE_BOUNDING_BOX
                , IBooleanFunction.ONLY_FIRST);
    }

}

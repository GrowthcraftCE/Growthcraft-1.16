package growthcraft.lib.common.block;

import growthcraft.lib.common.block.rope.IBlockRope;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Map;

public class GrowthcraftRopeFenceBlock extends Block implements IBlockRope, IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final IntegerProperty NORTH = IntegerProperty.create("north", 0, 2);
    public static final IntegerProperty EAST = IntegerProperty.create("east", 0, 2);
    public static final IntegerProperty SOUTH = IntegerProperty.create("south", 0, 2);
    public static final IntegerProperty WEST = IntegerProperty.create("west", 0, 2);

    private static final VoxelShape KNOT_BOUNDING_BOX = makeCuboidShape(5.0D, 6.0D, 5.0D, 11.0D, 14.0D, 11.0D);
    private static final VoxelShape NORTH_BOUNDING_BOX = makeCuboidShape(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 7.0D);
    private static final VoxelShape EAST_BOUNDING_BOX = makeCuboidShape(9.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);
    private static final VoxelShape SOUTH_BOUNDING_BOX = makeCuboidShape(7.0D, 7.0D, 9.0D, 9.0D, 9.0D, 16.0D);
    private static final VoxelShape WEST_BOUNDING_BOX = makeCuboidShape(0.0D, 7.0D, 7.0D, 7.0D, 9.0D, 9.0D);

    private static final VoxelShape FENCE_BOUNDING_BOX = makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private static final VoxelShape FENCE_NORTH_BOUNDING_BOX = makeCuboidShape(7.0D, 6.0D, 0.0D, 9.0D, 15.0D, 5.0D);
    private static final VoxelShape FENCE_EAST_BOUNDING_BOX = makeCuboidShape(11.0D, 6.0D, 7.0D, 16.0D, 15.0D, 9.0D);
    private static final VoxelShape FENCE_SOUTH_BOUNDING_BOX = makeCuboidShape(7.0D, 6.0D, 11.0D, 9.0D, 15.0D, 16.0D);
    private static final VoxelShape FENCE_WEST_BOUNDING_BOX = makeCuboidShape(0.0D, 6.0D, 7.0D, 5.0D, 15.0D, 9.0D);

    private final Block BASE_FENCE_BLOCK;
    private boolean SNEAK_BREAK;

    public GrowthcraftRopeFenceBlock(Block baseFenceBlock) {
        super(getInitProperties(baseFenceBlock.getDefaultState().getMaterial()));

        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, 0)
                .with(EAST, 0)
                .with(SOUTH, 0)
                .with(WEST, 0)
                .with(WATERLOGGED, Boolean.FALSE)
        );

        BASE_FENCE_BLOCK = baseFenceBlock;

    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(3, 20);
        return properties;
    }

    public Block getBaseFenceBlock() {
        return BASE_FENCE_BLOCK;
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader worldReader, BlockPos pos, BlockPos neighbor) {
        if (!worldReader.isRemote()) {
            World world = (World) worldReader;
        }

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(worldIn, pos);

        voxelShapeArrayList.add(FENCE_BOUNDING_BOX);

        if (GrowthcraftRopeBlock.class.isInstance(blockMap.get("north"))) {
            voxelShapeArrayList.add(NORTH_BOUNDING_BOX);
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("north"))) {
            voxelShapeArrayList.add(FENCE_NORTH_BOUNDING_BOX);
        }

        if (GrowthcraftRopeBlock.class.isInstance(blockMap.get("east"))) {
            voxelShapeArrayList.add(EAST_BOUNDING_BOX);
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("east"))) {
            voxelShapeArrayList.add(FENCE_EAST_BOUNDING_BOX);
        }

        if (GrowthcraftRopeBlock.class.isInstance(blockMap.get("south"))) {
            voxelShapeArrayList.add(SOUTH_BOUNDING_BOX);
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("south"))) {
            voxelShapeArrayList.add(FENCE_SOUTH_BOUNDING_BOX);
        }

        if (GrowthcraftRopeBlock.class.isInstance(blockMap.get("west"))) {
            voxelShapeArrayList.add(WEST_BOUNDING_BOX);
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("west"))) {
            voxelShapeArrayList.add(FENCE_WEST_BOUNDING_BOX);
        }

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return VoxelShapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getPos();
        return getActualBlockState(context.getWorld(), context.getPos());
    }

    public BlockState getActualBlockState(World world, BlockPos blockPos) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);
        IFluidState ifluidstate = world.getFluidState(blockPos);

        int north = 0;
        int east = 0;
        int south = 0;
        int west = 0;

        if (IBlockRope.class.isInstance(blockMap.get("north"))) {
            north = 1;
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("north")) || FenceBlock.class.isInstance(blockMap.get("north"))) {
            north = 2;
        }

        if (IBlockRope.class.isInstance(blockMap.get("east"))) {
            east = 1;
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("east")) || FenceBlock.class.isInstance(blockMap.get("east"))) {
            east = 2;
        }

        if (IBlockRope.class.isInstance(blockMap.get("south"))) {
            south = 1;
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("south")) || FenceBlock.class.isInstance(blockMap.get("south"))) {
            south = 2;
        }

        if (IBlockRope.class.isInstance(blockMap.get("west"))) {
            west = 1;
        } else if (GrowthcraftRopeFenceBlock.class.isInstance(blockMap.get("west")) || FenceBlock.class.isInstance(blockMap.get("west"))) {
            west = 2;
        }

        return this.getDefaultState()
                .with(NORTH, north)
                .with(EAST, east)
                .with(SOUTH, south)
                .with(WEST, west)
                .with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        worldIn.setBlockState(pos, getActualBlockState(worldIn, pos), 3);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        Block connectingBlock = state.getBlock();
        return connectingBlock instanceof GrowthcraftRopeBlock || connectingBlock instanceof IBlockRope;
    }

    @SuppressWarnings("deprecation")
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, WATERLOGGED);
    }

}

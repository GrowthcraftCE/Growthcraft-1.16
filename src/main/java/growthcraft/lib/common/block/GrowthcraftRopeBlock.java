package growthcraft.lib.common.block;

import growthcraft.core.shared.Reference;
import growthcraft.lib.common.block.rope.IBlockRope;
import growthcraft.lib.utils.BlockStateUtils;
import growthcraft.lib.utils.RopeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
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

public class GrowthcraftRopeBlock extends Block implements IBlockRope, IWaterLoggable {

    /*
        Types of natural fiber rope:
        - Jute (plant)
        - Cotton (cotton)
        - Wool (16 colors, made from wool blocks)
        - Linen (strings) DEFAULT
        - Hemp (plant)
        - Straw (2 Hay bails -> Straw)
        - Sisal (plant)
        - Coir (coconut fibers)
     */

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public GrowthcraftRopeBlock() {
        this(getInitProperties(Material.WOOL));
    }

    public GrowthcraftRopeBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, Boolean.valueOf(false))
                .with(EAST, Boolean.valueOf(false))
                .with(SOUTH, Boolean.valueOf(false))
                .with(WEST, Boolean.valueOf(false))
                .with(UP, Boolean.valueOf(false))
                .with(DOWN, Boolean.valueOf(false))
                .with(WATERLOGGED, Boolean.valueOf(false))
        );
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.sound(SoundType.CLOTH);
        return properties;
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

        Tag<Block> tagRope = BlockTags.getCollection().getOrCreate(Reference.TAG_ROPE);

        if (tagRope.contains(blockMap.get("north"))) voxelShapeArrayList.add(NORTH_BOUNDING_BOX);
        if (tagRope.contains(blockMap.get("east"))) voxelShapeArrayList.add(EAST_BOUNDING_BOX);
        if (tagRope.contains(blockMap.get("south"))) voxelShapeArrayList.add(SOUTH_BOUNDING_BOX);
        if (tagRope.contains(blockMap.get("west"))) voxelShapeArrayList.add(WEST_BOUNDING_BOX);
        if (tagRope.contains(blockMap.get("up"))) voxelShapeArrayList.add(UP_BOUNDING_BOX);
        if (tagRope.contains(blockMap.get("down"))) voxelShapeArrayList.add(DOWN_BOUNDING_BOX);

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return VoxelShapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }

    public BlockState getActualBlockState(World world, BlockPos blockPos) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);
        IFluidState ifluidstate = world.getFluidState(blockPos);

        return this.getDefaultState()
                .with(NORTH, RopeUtils.isRopeBlock(blockMap.get("north")))
                .with(EAST, RopeUtils.isRopeBlock(blockMap.get("east")))
                .with(SOUTH, RopeUtils.isRopeBlock(blockMap.get("south")))
                .with(WEST, RopeUtils.isRopeBlock(blockMap.get("west")))
                .with(UP, RopeUtils.isRopeBlock(blockMap.get("up")))
                .with(DOWN, RopeUtils.isRopeBlock(blockMap.get("down")))
                .with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        worldIn.setBlockState(pos, getActualBlockState(worldIn, pos), 3);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        return RopeUtils.isRopeBlock(state.getBlock());
    }

    @SuppressWarnings("deprecation")
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

}

package growthcraft.core.common.block;

import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.core.common.tileentity.RopeTileEntity;
import growthcraft.core.init.GrowthcraftTileEntities;
import growthcraft.lib.util.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;

public class RopeBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty KNOT = BooleanProperty.create("knot");

    public static final VoxelShape KNOT_BOUNDING_BOX = makeCuboidShape(5.0D, 6.0D, 5.0D, 11.0D, 14.0D, 11.0D);
    public static final VoxelShape NORTH_BOUNDING_BOX = makeCuboidShape(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 7.0D);
    public static final VoxelShape EAST_BOUNDING_BOX = makeCuboidShape(9.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);
    public static final VoxelShape SOUTH_BOUNDING_BOX = makeCuboidShape(7.0D, 7.0D, 9.0D, 9.0D, 9.0D, 16.0D);
    public static final VoxelShape WEST_BOUNDING_BOX = makeCuboidShape(0.0D, 7.0D, 7.0D, 7.0D, 9.0D, 9.0D);
    public static final VoxelShape UP_BOUNDING_BOX = makeCuboidShape(7.0D, 9.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    public static final VoxelShape DOWN_BOUNDING_BOX = makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 7.0D, 9.0D);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public RopeBlock() {
        this(getInitProperties(Material.WOOL));
    }

    public RopeBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, Boolean.valueOf(false))
                .with(EAST, Boolean.valueOf(false))
                .with(SOUTH, Boolean.valueOf(false))
                .with(WEST, Boolean.valueOf(false))
                .with(UP, Boolean.valueOf(false))
                .with(DOWN, Boolean.valueOf(false))
                .with(KNOT, Boolean.valueOf(false))
                .with(WATERLOGGED, Boolean.valueOf(false))
        );
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.sound(SoundType.CLOTH);
        return properties;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftTileEntities.rope_tileentity.get().create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        // Dump the inventory
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof RopeTileEntity && state.getBlock() != newState.getBlock()) {
            RopeTileEntity ropeTileEntity = (RopeTileEntity) tileEntity;
            ((GrowthcraftItemHandler) ropeTileEntity.getInventory()).toNonNullList().forEach(item -> {
                ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), item);
                worldIn.addEntity(itemEntity);
            });
        }

        // Remove the tile entity
        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            worldIn.removeTileEntity(pos);
        }
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader worldReader, BlockPos pos, BlockPos neighbor) {
        if (!worldReader.isRemote()) {
            World worldIn = (World) worldReader;
            worldIn.setBlockState(pos, getActualBlockState(worldIn, pos), 3);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(worldIn, pos);

        if (BlockStateUtils.isRopeBlock(blockMap.get("north"))) voxelShapeArrayList.add(NORTH_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("east"))) voxelShapeArrayList.add(EAST_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("south"))) voxelShapeArrayList.add(SOUTH_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("west"))) voxelShapeArrayList.add(WEST_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("up"))) voxelShapeArrayList.add(UP_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("down"))) voxelShapeArrayList.add(DOWN_BOUNDING_BOX);
        if (Boolean.TRUE.equals(state.get(KNOT))) voxelShapeArrayList.add(KNOT_BOUNDING_BOX);

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);


        return VoxelShapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }

    public BlockState getActualBlockState(World world, BlockPos blockPos) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);
        FluidState ifluidstate = world.getFluidState(blockPos);

        RopeTileEntity tile = (RopeTileEntity) world.getTileEntity(blockPos);

        return this.getDefaultState()
                .with(NORTH, BlockStateUtils.isRopeBlock(blockMap.get("north")))
                .with(EAST, BlockStateUtils.isRopeBlock(blockMap.get("east")))
                .with(SOUTH, BlockStateUtils.isRopeBlock(blockMap.get("south")))
                .with(WEST, BlockStateUtils.isRopeBlock(blockMap.get("west")))
                .with(UP, BlockStateUtils.isRopeBlock(blockMap.get("up")))
                .with(DOWN, BlockStateUtils.isRopeBlock(blockMap.get("down")))
                .with(KNOT, tile != null && tile.hasFenceItemStack())
                .with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return this.getActualBlockState((World) worldIn, currentPos);
    }

    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        return BlockStateUtils.isRopeBlock(state.getBlock());
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, KNOT, WATERLOGGED);
    }

}

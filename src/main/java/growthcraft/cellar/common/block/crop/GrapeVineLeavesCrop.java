package growthcraft.cellar.common.block.crop;

import growthcraft.cellar.common.tileentity.GrapeVineTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.core.common.tileentity.RopeTileEntity;
import growthcraft.lib.common.block.GrowthcraftCropsRopeBlock;
import growthcraft.lib.util.BlockStateUtils;
import growthcraft.lib.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;

/**
 * GrapeVineLeavesCrop is grape leaves blocks that grow horizontally.
 */
public class GrapeVineLeavesCrop extends GrowthcraftCropsRopeBlock {

    public static final BooleanProperty TRUNK_CONNECTED = BooleanProperty.create("trunk_connected");

    protected static final VoxelShape[] CUSTOM_SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public GrapeVineLeavesCrop() {
        super();
        super.setDefaultState(this.stateContainer.getBaseState()
                .with(AGE, 0)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false)
                .with(TRUNK_CONNECTED, false));
    }

    @Override
    public BlockState getActualBlockStateWithAge(World world, BlockPos blockPos, int age) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);

        return this.getDefaultState()
                .with(NORTH, BlockStateUtils.isRopeBlock(blockMap.get("north")))
                .with(EAST, BlockStateUtils.isRopeBlock(blockMap.get("east")))
                .with(SOUTH, BlockStateUtils.isRopeBlock(blockMap.get("south")))
                .with(WEST, BlockStateUtils.isRopeBlock(blockMap.get("west")))
                .with(UP, BlockStateUtils.isRopeBlock(blockMap.get("up")))
                .with(DOWN, BlockStateUtils.isRopeBlock(blockMap.get("down")))
                .with(AGE, age)
                .with(TRUNK_CONNECTED, blockMap.get("down").getBlock() instanceof GrapeVineCrop);
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(TRUNK_CONNECTED));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftCellarTileEntities.grape_vine_tileentity.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, BlockPos pos, BlockState state) {
        // Grape Vine Leaves grow faster, +1 from super.
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn) + 1;
        if (i > this.getMaxAge()) {
            i = this.getMaxAge();
        }

        worldIn.setBlockState(pos, getActualBlockStateWithAge(worldIn, pos, i), 2);

        if (i == this.getMaxAge()) {
            this.doMaxAge(worldIn, pos, state);
        }
    }

    @Override
    public void doMaxAge(World worldIn, BlockPos pos, BlockState state) {
        GrapeVineTileEntity grapeVineLeavesTileEntity = (GrapeVineTileEntity) worldIn.getTileEntity(pos);
        if (grapeVineLeavesTileEntity == null) return;

        // Check the four directions for RopeBlocks that are not fences.
        Map<BlockPos, BlockState> blockMap = BlockStateUtils.getHorizontalBlockPos(worldIn, pos);
        for (Map.Entry<BlockPos, BlockState> blockEntry : blockMap.entrySet()) {
            if (BlockStateUtils.isRopeBlock(blockEntry.getValue().getBlock()) && worldIn.getTileEntity(blockEntry.getKey()) instanceof RopeTileEntity) {
                RopeTileEntity ropeTileEntity = (RopeTileEntity) worldIn.getTileEntity(blockEntry.getKey());
                if (ropeTileEntity != null && !ropeTileEntity.hasFenceItemStack()) {
                    worldIn.setBlockState(blockEntry.getKey(), GrowthcraftCellarBlocks.GRAPE_VINE_LEAVES.get()
                            .getActualBlockStateWithAge(worldIn, blockEntry.getKey(), 0));
                    WorldUtils.notifyBlockUpdate(worldIn, blockEntry.getKey());

                    GrapeVineTileEntity newGrapeVineLeavesTileEntity = (GrapeVineTileEntity) worldIn.getTileEntity(blockEntry.getKey());
                    if (newGrapeVineLeavesTileEntity != null) {
                        newGrapeVineLeavesTileEntity.setFruitItemStack(grapeVineLeavesTileEntity.getFruitItemStack());
                    }
                }
            }
        }

        if (worldIn.isAirBlock(pos.down())) {
            worldIn.setBlockState(pos.down(), GrowthcraftCellarBlocks.GRAPE_VINE_CROP.get().getDefaultState());
            GrapeVineTileEntity newGrapeVineCropTileEntity = (GrapeVineTileEntity) worldIn.getTileEntity(pos.down());
            if (newGrapeVineCropTileEntity != null) {
                newGrapeVineCropTileEntity.setFruitItemStack(grapeVineLeavesTileEntity.getFruitItemStack());
            }
        }

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape ropeVoxel = super.getShape(state, worldIn, pos, context);

        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(worldIn, pos);

        voxelShapeArrayList.add(CUSTOM_SHAPE_BY_AGE[0]);

        if (BlockStateUtils.isRopeBlock(blockMap.get("north"))) voxelShapeArrayList.add(NORTH_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("east"))) voxelShapeArrayList.add(EAST_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("south"))) voxelShapeArrayList.add(SOUTH_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("west"))) voxelShapeArrayList.add(WEST_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("up"))) voxelShapeArrayList.add(UP_BOUNDING_BOX);
        if (BlockStateUtils.isRopeBlock(blockMap.get("down"))) voxelShapeArrayList.add(DOWN_BOUNDING_BOX);

        voxelShapeArrayList.add(ropeVoxel);

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return VoxelShapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }
}

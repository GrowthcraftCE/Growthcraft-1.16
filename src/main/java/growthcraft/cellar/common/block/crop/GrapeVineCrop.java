package growthcraft.cellar.common.block.crop;

import growthcraft.cellar.common.tileentity.GrapeVineTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.core.Growthcraft;
import growthcraft.lib.common.block.GrowthcraftCropsRopeBlock;
import growthcraft.lib.util.BlockStateUtils;
import growthcraft.lib.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;

public class GrapeVineCrop extends GrowthcraftCropsRopeBlock {

    protected static final VoxelShape[] CUSTOM_SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D)};

    public GrapeVineCrop() {
        super(getInitProperties());
    }

    public static Properties getInitProperties() {
        Properties properties = Properties.create(Material.PLANTS);
        properties.tickRandomly();
        properties.hardnessAndResistance(0.2F, 0.2F);
        properties.harvestTool(ToolType.HOE);
        properties.sound(SoundType.PLANT);
        properties.notSolid();
        return properties;
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
    public void doMaxAge(World worldIn, BlockPos pos, BlockState state) {
        GrapeVineTileEntity grapeVineLeavesTileEntity = (GrapeVineTileEntity) worldIn.getTileEntity(pos);
        if (grapeVineLeavesTileEntity == null) return;

        ItemStack fruitItemStack = grapeVineLeavesTileEntity.getFruitItemStack();

        // Determine which type of block needs to be created.
        if (worldIn.isAirBlock(pos.up())) {
            // Then we need to spawn another GrapeVineCrop block
            worldIn.setBlockState(pos.up(), this.getActualBlockStateWithAge(worldIn, pos.up(), 0));
        } else if (BlockStateUtils.isRopeBlock(worldIn.getBlockState(pos.up()).getBlock())) {
            // Then we need to spawn a GrapeVineLeavesCrop because we met the rope block
            worldIn.setBlockState(pos.up(), GrowthcraftCellarBlocks.GRAPE_VINE_LEAVES.get().getActualBlockStateWithAge(worldIn, pos.up(), 0));
        }

        // Pass the FruitItem to the newly crated block.
        GrapeVineTileEntity newTileEntity = (GrapeVineTileEntity) worldIn.getTileEntity(pos.up());
        if (newTileEntity != null) {
            newTileEntity.setFruitItemStack(fruitItemStack);
        } else {
            Growthcraft.LOGGER.error(String.format("BlockPos [%s] does not have a GrapeVineTileEntity!", pos.up()));
        }

        WorldUtils.notifyBlockUpdate(worldIn, pos.up());

    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        return BlockStateUtils.isRopeBlock(state.getBlock());
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape ropeVoxel = super.getShape(state, worldIn, pos, context);

        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(worldIn, pos);

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

        //return CUSTOM_SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

}

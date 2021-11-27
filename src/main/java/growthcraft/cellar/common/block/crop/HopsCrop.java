package growthcraft.cellar.common.block.crop;

import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.lib.common.block.GrowthcraftCropsRopeBlock;
import growthcraft.lib.util.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Map;

public class HopsCrop extends GrowthcraftCropsRopeBlock {

    protected static final VoxelShape[] CUSTOM_SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D)
    };

    private static final int fruitMax = GrowthcraftCellarConfig.getHopsCropMaxFruitYield();
    private static final int fruitMin = GrowthcraftCellarConfig.getHopsCropMinFruitYield();

    public HopsCrop() {
        super();
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
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(AGE) == 7) {
            int count = RANDOM.nextInt(fruitMax - fruitMin) + fruitMin;
            // Spawn the random drop count
            ItemStack itemStack = new ItemStack(GrowthcraftCellarItems.HOPS.get(), count);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);

            // Decrease age to 4
            worldIn.setBlockState(pos, this.getActualBlockStateWithAge(worldIn, pos, 4), 2);
        }
        return ActionResultType.PASS;
    }

}

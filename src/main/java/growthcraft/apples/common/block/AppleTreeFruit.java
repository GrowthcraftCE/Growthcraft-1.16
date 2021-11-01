package growthcraft.apples.common.block;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import static net.minecraft.state.properties.BlockStateProperties.PERSISTENT;

public class AppleTreeFruit extends CropsBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 14.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 14.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 14.0D, 10.0D),
            Block.makeCuboidShape(5.0D, 9.0D, 5.0D, 11.0D, 14.0D, 11.0D),
            Block.makeCuboidShape(5.0D, 9.0D, 5.0D, 11.0D, 14.0D, 11.0D),
            Block.makeCuboidShape(5.0D, 9.0D, 5.0D, 11.0D, 14.0D, 11.0D),
            Block.makeCuboidShape(5.0D, 9.0D, 5.0D, 11.0D, 14.0D, 11.0D),
            Block.makeCuboidShape(4.0D, 7.0D, 4.0D, 12.0D, 14.0D, 12.0D)};

    public AppleTreeFruit() {
        super(getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.LEAVES);
        properties.tickRandomly();
        properties.hardnessAndResistance(0.2F, 0.2F);
        properties.sound(SoundType.PLANT);
        properties.notSolid();
        return properties;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getBlock() == GrowthcraftApplesBlocks.appleTreeLeaves.get() &&
                !worldIn.getBlockState(pos).get(PERSISTENT);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return isValidGround(state, worldIn, blockPos);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return Items.APPLE;
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(AGE) == this.getMaxAge()) {
            worldIn.destroyBlock(pos, true);
        }
        return ActionResultType.PASS;
    }
}

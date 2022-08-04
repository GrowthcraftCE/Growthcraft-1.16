package growthcraft.milk.common.block;

import growthcraft.milk.init.GrowthcraftMilkItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ThistleCropBlock extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(5.0F, 0.0F, 5.0F, 11.0F, 4.0F, 11.0F),
            Block.makeCuboidShape(5.0F, 0.0F, 5.0F, 11.0F, 9.0F, 11.0F),
            Block.makeCuboidShape(5.0F, 0.0F, 5.0F, 11.0F, 9.0F, 11.0F),
            Block.makeCuboidShape(5.0F, 0.0F, 5.0F, 11.0F, 9.0F, 11.0F),
            Block.makeCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 15.0F, 12.0F),
            Block.makeCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 15.0F, 12.0F),
            Block.makeCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 15.0F, 12.0F),
            Block.makeCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F)
    };

    public ThistleCropBlock() {
        super(getInitProperties());
    }

    protected IItemProvider getSeedsItem() {
        return GrowthcraftMilkItems.THISTLE_SEED.get();
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.PLANTS);
        properties.doesNotBlockMovement();
        properties.tickRandomly();
        properties.zeroHardnessAndResistance();
        properties.sound(SoundType.CROP);
        return properties;
    }

    /*
    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.matchesBlock(Blocks.GRASS);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState blockStateDown = worldIn.getBlockState(pos.down());
        return blockStateDown.matchesBlock(Blocks.GRASS);
    }

     */

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(AGE) == 7) {
            worldIn.playEvent(player, 2001, pos, getStateId(state));

            int count = RANDOM.nextInt(3) + 1;
            // Spawn the random drop count
            ItemStack itemStack = new ItemStack(GrowthcraftMilkItems.THISTLE.get(), count);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);

            // Decrease age to 0
            worldIn.setBlockState(pos, this.withAge(0), 2);
        }
        return ActionResultType.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}

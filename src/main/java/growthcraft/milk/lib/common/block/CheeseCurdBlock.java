package growthcraft.milk.lib.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;

import java.awt.*;
import java.util.Random;

public class CheeseCurdBlock extends Block implements IForgeShearable {

    public static final VoxelShape BASE_BOUNDING_BOX = Block.makeCuboidShape(
            4.0D, 2.0D, 4.0D,
            12.0D, 8.0D, 12.0D
    );
    private static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
    private final int color;

    public CheeseCurdBlock(Color color) {
        super(getInitProperties());
        this.color = color.getRGB();
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.MUSHROOM_STEM);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        properties.harvestLevel(1);
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

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hitResult) {
        ItemStack heldItemStack = player.getHeldItem(hand);
        if (heldItemStack.getItem() == Items.SHEARS && state.get(AGE) != 7) {
            worldIn.playEvent(player, 2001, pos, getStateId(state));

            ItemStack itemStack = new ItemStack(state.getBlock(), 1);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            worldIn.removeBlock(pos, true);

            return ActionResultType.PASS;
        } else if (heldItemStack.getItem() == Items.SHEARS && state.get(AGE) == 7) {
            worldIn.destroyBlock(pos, true);
        }

        return super.onBlockActivated(state, worldIn, pos, player, hand, hitResult);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(AGE) < 7) {
            if (random.nextInt(16) <= 4) {
                BlockPos blockpos = pos.down();
                if (world.isAirBlock(blockpos)) {

                    double d0 = (double) pos.getX() + random.nextDouble() / 2.0D;
                    double d1 = (double) pos.getY() - 0.05D;
                    double d2 = (double) pos.getZ() + random.nextDouble() / 2.0D;

                    world.addParticle(ParticleTypes.FALLING_HONEY,
                            d0, d1, d2,
                            0.0D, 0.0D, 0.0D
                    );
                }
            }
        }
    }

}

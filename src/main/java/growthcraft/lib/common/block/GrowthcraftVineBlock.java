package growthcraft.lib.common.block;

import growthcraft.core.shared.Reference;
import growthcraft.grapes.init.config.GrowthcraftGrapesConfig;
import growthcraft.lib.common.block.rope.IBlockRope;
import growthcraft.lib.utils.BlockStateUtils;
import growthcraft.lib.utils.BushUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Map;
import java.util.Random;

/**
 * A BushBlock that grows upwards until it reaches a rope block. Once it reaches a rope block, it replaces the rope
 * block with a GrowthcraftVineLeavesBlock.
 *
 * @author Alatyami
 * @since 5.0.0
 */
@SuppressWarnings("java:S1874")
public class GrowthcraftVineBlock extends BushBlock implements IGrowable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;

    protected static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D)};

    private Item seedsItem;

    private GrowthcraftVineLeavesBlock vineLeavesBlock;

    public GrowthcraftVineBlock(GrowthcraftVineLeavesBlock vineLeavesBlock) {
        this(getInitProperties());
        this.vineLeavesBlock = vineLeavesBlock;
    }

    public GrowthcraftVineBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(AGE, 0));
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.PLANTS);
        properties.tickRandomly();
        properties.hardnessAndResistance(0.2F, 0.2F);
        properties.sound(SoundType.PLANT);
        properties.notSolid();
        return properties;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos, getActualBlockState(worldIn, pos), 2);
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public void withSeedsItem(Item seedsItem) {
        this.seedsItem = seedsItem;
    }

    public IItemProvider getSeedsItem() {
        assert seedsItem != null;
        return seedsItem;
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public boolean isMaxAge(BlockState state) {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    public int getMaxAge() {
        return 7;
    }

    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == Blocks.FARMLAND || state.getBlock() instanceof GrowthcraftVineBlock;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        if ( this.isMaxAge(state) ) {
            for (int h = 1; h <= GrowthcraftGrapesConfig.maxGrapeVineGrowthHeight(); h++) {
                    if ( worldIn.getBlockState(pos.up(h)).getBlock() instanceof GrowthcraftVineLeavesBlock ) {
                        return false;
                    }
            }

            for (int h = 1; h <= GrowthcraftGrapesConfig.maxGrapeVineGrowthHeight(); h++) {
                BlockState blockState = worldIn.getBlockState(pos.up(h));
                // If we find a Vine Block that is still growing, return false.
                if ( worldIn.getBlockState(pos.up(h)).getBlock() instanceof GrowthcraftVineBlock ) {
                    GrowthcraftVineBlock vineBlock = (GrowthcraftVineBlock) blockState.getBlock();
                    if ( vineBlock.getAge(blockState) < vineBlock.getMaxAge()) {
                        return false;
                    }
                }

                if ( worldIn.getBlockState(pos.up(h)).getBlock() == Blocks.AIR ) {
                    return true;
                } else if ( worldIn.getBlockState(pos.up(h)).getBlock() instanceof GrowthcraftRopeBlock ) {
                    return true;
                }
            }
        }
        return !this.isMaxAge(state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (worldIn.getLightSubtracted(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i <= this.getMaxAge()) {
                float f = BushUtils.getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)
                    || this.isMaxAge(state) ) {
                    grow(worldIn, rand, pos, state);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.grow(worldIn, pos, state);
    }

    public void grow(World worldIn, BlockPos pos, BlockState state) {

        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, getActualBlockStateWithAge(worldIn, pos, i), 2);

        // If this block is full grown and is a master block, then we need to grow the other blocks.
        if (i == this.getMaxAge() && this.isMasterBlock(worldIn, pos)) {

            Tag<Block> tagRope = BlockTags.getCollection().getOrCreate(Reference.TAG_ROPE);

            for ( int k = 1; k < GrowthcraftGrapesConfig.maxGrapeVineGrowthHeight(); k++ ) {
                // If pos.up(k) is a Rope block, spawn a leaves block.
                if ( tagRope.contains(worldIn.getBlockState(pos.up(k)).getBlock()) ) {
                    this.spawnVineLeavesBlock(worldIn, pos.up(k));
                    return;
                }
                // If pos.up(k) is a Air block, spawn a new vine.
                if ( worldIn.getBlockState(pos.up(k)).getBlock() == Blocks.AIR ) {
                    this.spawnVineBlock(worldIn, pos.up(k));
                    return;
                }

                // If pos.up(k) is a VineBlock then try and grow it.
                if ( worldIn.getBlockState(pos.up(k)).getBlock() instanceof GrowthcraftVineBlock ) {
                    ((GrowthcraftVineBlock) worldIn.getBlockState(pos.up(k)).getBlock()).grow(worldIn, pos.up(k), state);
                }
            }

        }
    }

    private boolean isMasterBlock(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND;
    }

    private void spawnVineBlock(World world, BlockPos pos) {
        world.setBlockState(pos, this.getDefaultState());
    }

    private void spawnVineLeavesBlock(World world, BlockPos pos) {
        world.setBlockState(pos, vineLeavesBlock.getDefaultState());
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 1, 3);
    }

    public BlockState getActualBlockStateWithAge(World world, BlockPos blockPos, int age) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);

        return this.getDefaultState()
                .with(AGE, age);
    }

    public BlockState getActualBlockState(World world, BlockPos blockPos) {
        return getActualBlockStateWithAge(world, blockPos, world.getBlockState(blockPos).get(this.getAgeProperty()));
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        Block connectingBlock = state.getBlock();
        return canBeConnectedTo(connectingBlock);
    }

    private boolean canBeConnectedTo(Block block) {
        Tag<Block> tagRope = BlockTags.getCollection().getOrCreate(Reference.TAG_ROPE);
        return tagRope.contains(block) || block instanceof IBlockRope;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        worldIn.setBlockState(pos, getActualBlockStateWithAge(worldIn, pos, worldIn.getBlockState(pos).get(this.getAgeProperty())), 3);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

}

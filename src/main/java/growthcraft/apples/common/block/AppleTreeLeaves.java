package growthcraft.apples.common.block;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.lib.common.block.GrowthcraftLogBlock;
import growthcraft.lib.common.block.GrowthcraftTreeLeaves;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

public class AppleTreeLeaves extends GrowthcraftTreeLeaves implements IGrowable {

    private static final int APPLE_CHECK_AREA = 3;
    private static final int MAX_APPLES_IN_AREA = 3;
    private static final int MAX_APPLE_GROW_TIMEWAIT = 240;

    private final int tickCount;

    public AppleTreeLeaves() {
        super();
        this.tickCount = 0;
    }

    @Override
    public boolean canGrow(IBlockReader blockReader, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return !state.get(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (Boolean.FALSE.equals(state.get(PERSISTENT)) && (state.get(DISTANCE) == 7)) {
            spawnDrops(state, worldIn, pos);
            worldIn.removeBlock(pos, false);
        } else if (!canSustainApple(worldIn, pos, state) || !worldIn.isAirBlock(pos.down())) {
            return;
        } else {
            tick(state, worldIn, pos, random);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        //GrowthcraftApples.LOGGER.error("Tick testing for growth..." + pos);
        if (worldIn.isAirBlock(pos.down()) && rand.nextInt(100) < 10) {
            //GrowthcraftApples.LOGGER.error("Tick time to grow!" + tickCount);
            grow(worldIn, rand, pos, state);
        }

    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (!worldIn.isRemote) {
            if (worldIn.getLightSubtracted(pos, 0) >= 9) {
                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                    if (canSpawnApple(worldIn, pos, state)) {
                        worldIn.setBlockState(pos.down(), GrowthcraftApplesBlocks.appleTreeFruit.get().getDefaultState());
                        ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                    }
                }
            }
        }
    }

    private boolean canSpawnApple(World worldIn, BlockPos pos, BlockState state) {
        if (!canSustainApple(worldIn, pos, state) || !worldIn.isAirBlock(pos.down())) {
            return false;
        }
        BlockPos posLower = pos.down(1).south(APPLE_CHECK_AREA).west(APPLE_CHECK_AREA);
        BlockPos posUpper = pos.down(1).north(APPLE_CHECK_AREA).east(APPLE_CHECK_AREA);
        Iterable<BlockPos> blocks = BlockPos.getAllInBoxMutable(posLower, posUpper);
        int applesInArea = 0;
        int logsInArea = 0;
        for (BlockPos blockPos : blocks) {
            if (worldIn.getBlockState(blockPos).getBlock() instanceof AppleTreeFruit) applesInArea++;
            if (worldIn.getBlockState(blockPos).getBlock() instanceof GrowthcraftLogBlock) logsInArea++;
        }
        return applesInArea <= MAX_APPLES_IN_AREA && logsInArea > 0;
    }

    private boolean canSustainApple(World worldIn, BlockPos pos, BlockState state) {
        return state.get(DISTANCE) < 7;
    }

}

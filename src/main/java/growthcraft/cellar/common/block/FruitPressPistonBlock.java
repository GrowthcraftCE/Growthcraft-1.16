package growthcraft.cellar.common.block;

import growthcraft.cellar.common.tileentity.FruitPressPistonTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class FruitPressPistonBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    public FruitPressPistonBlock() {
        super(getInitProperties());
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(PRESSED, false));
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.PISTON);
        properties.hardnessAndResistance(2.0F);
        properties.harvestTool(ToolType.AXE);
        properties.notSolid();
        return properties;
    }

    /* BlockState */
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING, PRESSED);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, world, pos, neighbor);
        World worldIn = (World) world;

        if (!state.get(PRESSED) && isPowered(worldIn, pos)) {
            ((FruitPressPistonTileEntity) worldIn.getTileEntity(pos)).playSound(null, SoundEvents.BLOCK_PISTON_EXTEND);
        } else if (state.get(PRESSED) && !isPowered(worldIn, pos)) {
            ((FruitPressPistonTileEntity) worldIn.getTileEntity(pos)).playSound(null, SoundEvents.BLOCK_PISTON_CONTRACT);
        }

        worldIn.setBlockState(pos, state.with(PRESSED, isPowered(worldIn, pos)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(PRESSED, this.isPowered(context.getWorld(), context.getPos()));
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        worldIn.removeBlock(pos.down(), false);
    }

    /* Redstone Power */
    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return false;
    }

    public boolean isPowered(World world, BlockPos pos) {
        return world.getRedstonePowerFromNeighbors(pos) == 15;
    }

    /* TileEntity */
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftCellarTileEntities.fruit_press_piston_tileentity.get().create();
    }
}

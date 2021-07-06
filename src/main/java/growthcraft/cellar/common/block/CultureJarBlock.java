package growthcraft.cellar.common.block;

import growthcraft.cellar.common.tileentity.CultureJarTileEntity;
import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class CultureJarBlock extends Block {

    // Bounding Box
    private static final VoxelShape VOXEL_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D);

    // Block Properties
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public CultureJarBlock() {
        super(getInitProperties());
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(LIT, false));
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.FURNACE);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    // BlockState Properties
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING, LIT);
    }

    // Tile Entity
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftCellarTileEntities.culture_jar_tileentity.get().create();
    }

    // Block Interaction
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace())
                || player.getHeldItem(handIn).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return ActionResultType.SUCCESS;
        }

        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof CultureJarTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (CultureJarTileEntity) tileEntity, pos);
            }
        }

        return ActionResultType.SUCCESS;
    }

    // Block Replacement
    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        // Dump the inventory into the world.
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof CultureJarTileEntity && state.getBlock() != newState.getBlock()) {
            CultureJarTileEntity cultureJarTileEntity = (CultureJarTileEntity) tileEntity;
            ((BrewKettleItemHandler) cultureJarTileEntity.getInventory()).toNonNullList().forEach(
                    itemStack -> {
                        ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                        worldIn.addEntity(itemEntity);
                    }
            );
        }
        // Cleanup the TileEntity Object
        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            worldIn.removeTileEntity(pos);
        }
    }

    // Directional Facing
    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    // Shape
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL_SHAPE;
    }

    // TODO[20]: Add TileEntity

}

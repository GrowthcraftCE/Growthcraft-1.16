package growthcraft.apiary.common.block;

import growthcraft.apiary.common.tileentity.BeeBoxTileEntity;
import growthcraft.apiary.init.GrowthcraftApiaryTileEntities;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BeeBoxBlock extends HorizontalBlock {

    // DirectionProperty HORIZONTAL_FACING is inherited from HorizontalBlock along with
    // the methods mirror and rotate.
    private static final VoxelShape VOXEL_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public BeeBoxBlock() {
        this(getInitProperties());
    }

    public BeeBoxBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.BEEHIVE);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName()) {
            TileEntity tile = worldIn.getTileEntity(pos);

            if (tile instanceof BeeBoxTileEntity) {
                ((BeeBoxTileEntity) tile).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftApiaryTileEntities.BEE_BOX_TILE_ENTITY.get().create();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof BeeBoxTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (BeeBoxTileEntity) tileEntity, pos);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        // Dump the inventory into the world.
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof BeeBoxTileEntity && state.getBlock() != newState.getBlock()) {
            BeeBoxTileEntity beeBoxTileEntity = (BeeBoxTileEntity) tileEntity;
            InventoryHelper.dropItems(worldIn, pos, beeBoxTileEntity.getItems());
        }
        // Cleanup the TileEntity Object
        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            worldIn.removeTileEntity(pos);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL_SHAPE;
    }
}

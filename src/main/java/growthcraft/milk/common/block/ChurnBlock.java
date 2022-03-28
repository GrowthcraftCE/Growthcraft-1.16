package growthcraft.milk.common.block;

import growthcraft.milk.common.tileentity.ChurnTileEntity;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.init.config.GrowthcraftMilkConfig;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ChurnBlock extends HorizontalBlock {

    public static final VoxelShape LAYER0_BOUNDING_BOX = makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D);
    public static final VoxelShape LAYER1_BOUNDING_BOX = makeCuboidShape(3.0D, 5.0D, 3.0D, 13.0D, 10.0D, 13.0D);
    public static final VoxelShape LAYER2_BOUNDING_BOX = makeCuboidShape(4.0D, 10.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public static final VoxelShape PLUNGER_DOWN_BOUNDING_BOX = makeCuboidShape(7.0D, 4.0D, 7.0D, 9.0D, 20.0D, 9.0D);
    public static final VoxelShape PLUNGER_UP_BOUNDING_BOX = makeCuboidShape(7.0D, 4.0D, 7.0D, 9.0D, 28.0D, 9.0D);

    public static final BooleanProperty PLUNGED = BooleanProperty.create("plunged");

    public ChurnBlock() {
        super(getInitProperties());
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(HORIZONTAL_FACING, Direction.NORTH)
                .with(PLUNGED, true));
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.OAK_PLANKS);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftMilkTileEntities.CHURN_TILE_ENTITY.get().create();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING, PLUNGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if (FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace())
                || player.getHeldItem(handIn).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return ActionResultType.SUCCESS;
        }

        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof ChurnTileEntity) {
                // If GUI is enabled, player has to sneak to toggle the GUI.
                if (player.isSneaking() && GrowthcraftMilkConfig.isChurnGuiEnabled()) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (ChurnTileEntity) tileEntity, pos);
                } else if (((ChurnTileEntity) tileEntity).getInventory().getStackInSlot(0).getCount() > 0) {
                    ItemStack itemStack = ((ChurnTileEntity) tileEntity).getStackInSlot(0);
                    if (!player.inventory.addItemStackToInventory(itemStack)) {
                        player.dropItem(itemStack, false);
                    }
                } else {
                    ((ChurnTileEntity) tileEntity).togglePlunger();
                }

                return ActionResultType.SUCCESS;
            }
        } else {
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();
        voxelShapeArrayList.add(LAYER0_BOUNDING_BOX);
        voxelShapeArrayList.add(LAYER1_BOUNDING_BOX);
        voxelShapeArrayList.add(LAYER2_BOUNDING_BOX);

        if (state.get(PLUNGED)) {
            voxelShapeArrayList.add(PLUNGER_DOWN_BOUNDING_BOX);
        } else {
            voxelShapeArrayList.add(PLUNGER_UP_BOUNDING_BOX);
        }

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return VoxelShapes.or(LAYER0_BOUNDING_BOX, voxelShapes);
    }
}

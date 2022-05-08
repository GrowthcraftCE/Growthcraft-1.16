package growthcraft.milk.common.block;

import growthcraft.milk.common.tileentity.PancheonTileEntity;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.init.config.GrowthcraftMilkConfig;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class PancheonBlock extends HorizontalBlock {

    public static final BooleanProperty LOCKED = BooleanProperty.create("is_locked");

    public static final VoxelShape BASE_BOUNDING_BOX = makeCuboidShape(
            0.0D, 0.0D, 0.0D,
            16.0F, 5.0D, 16.0D
    );
    public static final VoxelShape INSIDE_BOUNDING_BOX = makeCuboidShape(
            1.0D, 1.0D, 1.0D,
            15.0F, 5.0D, 15.0D
    );

    public PancheonBlock() {
        this(getInitProperties());
    }

    public PancheonBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LOCKED, false));
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.CLAY);
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
        return GrowthcraftMilkTileEntities.PANCHEON_TILE_ENTITY.get().create();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING, LOCKED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        PancheonTileEntity tileEntity = (PancheonTileEntity) worldIn.getTileEntity(pos);

        // Handle Vanilla Milk Bucket
        if (!worldIn.isRemote && handIn.name().equals("MAIN_HAND") && player.getHeldItemMainhand().getItem() == Items.MILK_BUCKET) {
            FluidStack milkBucketFluidStack = new FluidStack(GrowthcraftMilkFluids.MILK_FLUID_BLOCK.get().getFluid().getFluid(), 1000);
            if (tileEntity.isFluidEmpty() || tileEntity.getFluidTank(0).getFluidAmount() == 1000) {
                tileEntity.getFluidTank(0).fill(milkBucketFluidStack, IFluidHandler.FluidAction.EXECUTE);
                player.getHeldItemMainhand().shrink(1);
                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.FAIL;
            }
        }

        // GUI if enabled
        if (!worldIn.isRemote) {
            if (player.isSneaking() && GrowthcraftMilkConfig.isPancheonGuiEnabled()) {
                NetworkHooks.openGui((ServerPlayerEntity) player, tileEntity, pos);
                return ActionResultType.SUCCESS;
            }
        }

        // Handle generic Fluid capable items
        if (FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace())
                || player.getHeldItem(handIn).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.combineAndSimplify(
                BASE_BOUNDING_BOX,
                INSIDE_BOUNDING_BOX
                , IBooleanFunction.ONLY_FIRST);
    }

}

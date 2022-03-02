package growthcraft.milk.common.block;

import growthcraft.milk.common.tileentity.MixingVatTileEntity;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.init.config.GrowthcraftMilkConfig;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
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
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class MixingVatBlock extends HorizontalBlock {

    public static final VoxelShape BASE_BOUNDING_BOX = makeCuboidShape(
            1.0D, 0.0D, 1.0D,
            15.0F, 15.0D, 15.0D
    );
    public static final VoxelShape INSIDE_BOUNDING_BOX = makeCuboidShape(
            2.0D, 1.0D, 2.0D,
            14.0F, 15.0D, 14.0D
    );

    public MixingVatBlock() {
        this(getInitProperties());
    }

    public MixingVatBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
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
        return GrowthcraftMilkTileEntities.MIXING_VAT_TILE_ENTITY.get().create();
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

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if (!player.isSneaking() || FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace())
                || player.getHeldItem(handIn).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return ActionResultType.SUCCESS;
        }

        if (!worldIn.isRemote) {
            MixingVatTileEntity tileEntity = (MixingVatTileEntity) worldIn.getTileEntity(pos);
            if (player.isSneaking() && GrowthcraftMilkConfig.isMixingVatGuiEnabled()) {
                NetworkHooks.openGui((ServerPlayerEntity) player, tileEntity, pos);
            }

            if (tileEntity.getActivationTool() != null) {
                // Then we need to try and activate the recipe and consume the activation item.
                if (tileEntity.activateRecipe(player.getHeldItem(handIn))) {
                    player.getHeldItem(handIn).shrink(1);
                }
            }
        }

        return ActionResultType.PASS;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MixingVatTileEntity && !world.isRemote) {
            ((MixingVatTileEntity) tileEntity).onEntityCollision(entity);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.combineAndSimplify(
                BASE_BOUNDING_BOX,
                INSIDE_BOUNDING_BOX
                , IBooleanFunction.ONLY_FIRST);
    }
}

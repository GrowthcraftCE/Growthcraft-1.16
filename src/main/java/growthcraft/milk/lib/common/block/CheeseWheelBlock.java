package growthcraft.milk.lib.common.block;

import growthcraft.milk.common.tileentity.CheeseWheelTileEntity;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;

public class CheeseWheelBlock extends HorizontalBlock {

    public static final BooleanProperty AGED = BooleanProperty.create("aged");

    public static final IntegerProperty SLICE_COUNT_TOP = IntegerProperty.create("slicestop", 0, 4);
    public static final IntegerProperty SLICE_COUNT_BOTTOM = IntegerProperty.create("slicesbottom", 0, 4);

    public static final VoxelShape BOUNDING_BOX = Block.makeCuboidShape(
            1.00F, 0.0F, 1.0F,
            15.0F, 16.0F, 15.0F
    );

    public static final VoxelShape BOUNDING_BOX_HALF = Block.makeCuboidShape(
            1.00F, 0.0F, 1.0F,
            15.0F, 8.0F, 15.0F
    );

    private final int color;

    public CheeseWheelBlock(Color color) {
        super(getInitProperties());
        this.color = color.getRGB();

        this.setDefaultState(this.stateContainer.getBaseState()
                .with(HORIZONTAL_FACING, Direction.NORTH)
                .with(SLICE_COUNT_BOTTOM, 4)
                .with(SLICE_COUNT_TOP, 0)
                .with(AGED, false)
        );
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 10;
    }

    public static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.CAKE);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING, SLICE_COUNT_BOTTOM, SLICE_COUNT_TOP, AGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftMilkTileEntities.CHEESE_WHEEL_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn.name().equals("MAIN_HAND")) {
            CheeseWheelTileEntity tileEntity = (CheeseWheelTileEntity) worldIn.getTileEntity(pos);

            if (player.getHeldItemMainhand().getItem() == this.asItem()) {
                tileEntity.addSlice(4);
                player.getHeldItemMainhand().shrink(1);
                return ActionResultType.SUCCESS;
            }

            if (!player.isSneaking() && player.getHeldItemMainhand().isEmpty()) {
                if (tileEntity.canTakeSlice()) {
                    player.inventory.addItemStackToInventory(tileEntity.takeSlice());
                    return ActionResultType.SUCCESS;
                }
            }

            if (player.isSneaking() && (tileEntity.getSliceCount() > 4 || tileEntity.getSliceCount() == 4)) {
                tileEntity.takeSlice(4);
                player.inventory.addItemStackToInventory(new ItemStack(this.asItem()));
                return ActionResultType.SUCCESS;
            }

            if (tileEntity.getSliceCount() == 0) worldIn.destroyBlock(pos, false);

        }

        return ActionResultType.SUCCESS;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(SLICE_COUNT_TOP) > 0 ? BOUNDING_BOX : BOUNDING_BOX_HALF;
    }

    public int getColor() {
        return this.color;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

}

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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.awt.*;

public class CheeseWheelBlock extends HorizontalBlock {

    public static final BooleanProperty AGED = BooleanProperty.create("aged");

    public static final IntegerProperty SLICE_COUNT_TOP = IntegerProperty.create("slice_count_top", 0, 4);
    public static final IntegerProperty SLICE_COUNT_BOTTOM = IntegerProperty.create("slice_count_bottom", 0, 4);

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

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.CAKE);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    @Override
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
                }
            }

            if (player.isSneaking() && (tileEntity.getSliceCount() > 4 || tileEntity.getSliceCount() == 4)) {
                tileEntity.takeSlice(4);
                player.inventory.addItemStackToInventory(new ItemStack(this.asItem()));
            }

            if (tileEntity.getSliceCount() == 0) worldIn.destroyBlock(pos, false);

        }

        return ActionResultType.SUCCESS;
    }

    public int getColor() {
        return this.color;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

}

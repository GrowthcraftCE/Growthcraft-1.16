package growthcraft.milk.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ChurnBlock extends HorizontalBlock {

    // Input Fluid Tank = Cream
    // Output Fluid Tank = Butter Milk
    // Output Inventory Slot = Butter
    // Number of activations to mix the input fluid tank
    public static final BooleanProperty PLUNGED = BooleanProperty.create("plunged");

    public ChurnBlock() {
        super(getInitProperties());
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(HORIZONTAL_FACING, Direction.NORTH)
                .with(PLUNGED, false));
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.OAK_PLANKS);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
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
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote) {

        }
        return ActionResultType.SUCCESS;
    }
}

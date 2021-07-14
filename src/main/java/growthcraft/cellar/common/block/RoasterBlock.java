package growthcraft.cellar.common.block;

import growthcraft.cellar.common.tileentity.RoasterTileEntity;
import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class RoasterBlock extends Block {
    //  BlockState Properties
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    //  Constructor
    public RoasterBlock() {
        super(getInitProperties());
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(LIT, false));
    }

    private static AbstractBlock.Properties getInitProperties() {
        AbstractBlock.Properties properties = AbstractBlock.Properties.from(Blocks.FURNACE);
        properties.hardnessAndResistance(1.5F);
        properties.notSolid();
        return properties;
    }

    // Blockstate
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING, LIT);
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
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName()) {
            TileEntity tile = worldIn.getTileEntity(pos);

            if (tile instanceof RoasterTileEntity) {
                ((RoasterTileEntity) tile).setCustomName(stack.getDisplayName());
            }

        }

    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(LIT) && GrowthcraftCellarConfig.getBrewKettleLitLightLevel() >= 0
                ? GrowthcraftCellarConfig.getBrewKettleLitLightLevel()
                : super.getLightValue(state, world, pos);
    }

    // TileEntity
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftCellarTileEntities.roaster_tileentity.get().create();
    }

    // Player Interaction
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof RoasterTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (RoasterTileEntity) tileEntity, pos);
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
        if (tileEntity instanceof RoasterTileEntity && state.getBlock() != newState.getBlock()) {
            RoasterTileEntity cultureJarTileEntity = (RoasterTileEntity) tileEntity;
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

    // Redstone Activation

    // Client Side Animations

}

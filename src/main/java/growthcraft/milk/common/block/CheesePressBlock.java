package growthcraft.milk.common.block;

import growthcraft.cellar.common.tileentity.BrewKettleTileEntity;
import growthcraft.milk.common.tileentity.CheesePressTileEntity;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CheesePressBlock extends Block {

    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 7);

    public static final VoxelShape BOUNDING_BOX = Block.makeCuboidShape(
            0.01F, 0.0F, 0.01F,
            15.98F, 15.98F, 15.98
    );

    public CheesePressBlock() {
        this(getInitProperties());
    }

    public CheesePressBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = AbstractBlock.Properties.from(Blocks.CHEST);
        properties.notSolid();
        return properties;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ROTATION);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftMilkTileEntities.CHEESE_PRESS_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName()) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof BrewKettleTileEntity) {
                ((BrewKettleTileEntity) tile).setCustomName(stack.getDisplayName());
            }
        }

    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            CheesePressTileEntity tileEntity = (CheesePressTileEntity) worldIn.getTileEntity(pos);
            if (player.getHeldItem(hand).isEmpty()) {
                if (!player.isSneaking()) {
                    // Tighten the vice
                    worldIn.setBlockState(pos, state.with(ROTATION, tileEntity.doRotation(true)), 2);
                } else {
                    // Loosen the vice and
                    if (tileEntity.getRotation() > 0) {
                        worldIn.setBlockState(pos, state.with(ROTATION, tileEntity.doRotation(false)), 2);
                        // TODO: Extract the stored item.
                    }
                }
                return ActionResultType.SUCCESS;
            } else {
                if (tileEntity.isOpen()) {
                    // Place the held item into the press

                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return BOUNDING_BOX;
    }
}

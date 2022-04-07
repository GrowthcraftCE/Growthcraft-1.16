package growthcraft.milk.common.block;

import growthcraft.cellar.common.tileentity.BrewKettleTileEntity;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.common.tileentity.CheesePressTileEntity;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CheesePressBlock extends Block {

    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 7);
    public static final ResourceLocation CONTENTS = new ResourceLocation("contents");

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
            if (hand.name().equals("MAIN_HAND") && player.getHeldItem(hand).isEmpty()) {
                if (!player.isSneaking()) {
                    // Tighten the vice
                    worldIn.setBlockState(pos, state.with(ROTATION, tileEntity.doRotation(true)), 2);
                } else {
                    // Loosen the vice and
                    if (tileEntity.getRotation() > 0) {
                        worldIn.setBlockState(pos, state.with(ROTATION, tileEntity.doRotation(false)), 2);
                        // Auto-Extract the stored item.
                        GrowthcraftMilk.LOGGER.log(Level.DEBUG, "CheesePress has a {} in Slot(0).", tileEntity.getInventory().getStackInSlot(0));

                        ItemStack itemStack = tileEntity.getInventory().extractItem(0, tileEntity.getInventory().getStackInSlot(0).getCount(), false);

                        GrowthcraftMilk.LOGGER.log(Level.DEBUG, "CheesePress has a {} in Slot(0).", tileEntity.getInventory().getStackInSlot(0));

                        GrowthcraftMilk.LOGGER.log(Level.DEBUG, "Pulling {} out of tileEntity.", itemStack.toString());
                        if (!itemStack.isEmpty() && !player.inventory.addItemStackToInventory(itemStack)) {
                            // TODO: Fix itemstack not being placed in player inventory
                            player.dropItem(itemStack, false);
                            tileEntity.markDirty();
                        } else {
                            GrowthcraftMilk.LOGGER.log(Level.DEBUG, "ItemStack is empty or we were able to add it to the player inventory.");
                        }
                    }
                }
                return ActionResultType.SUCCESS;
            } else if (hand.name().equals("MAIN_HAND") && tileEntity.isOpen()) {
                    if(tileEntity.getInventory().getStackInSlot(0).isEmpty()) {
                        // insert the item
                        ItemStack itemStackToInsert = player.getHeldItem(hand).getStack().copy();
                        itemStackToInsert.setCount(1);
                        tileEntity.getInventory().insertItem(0, itemStackToInsert, false);
                        player.getHeldItem(hand).shrink(1);

                        tileEntity.markDirty();
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

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        TileEntity tileentity = builder.get(LootParameters.BLOCK_ENTITY);
        if (tileentity instanceof CheesePressTileEntity) {
            CheesePressTileEntity cheesePressTileEntity = (CheesePressTileEntity)tileentity;
            builder = builder.withDynamicDrop(CONTENTS, (context, stackConsumer) -> {
                for(int i = 0; i < cheesePressTileEntity.getSizeInventory(); ++i) {
                    stackConsumer.accept(cheesePressTileEntity.getStackInSlot(i));
                }

            });
        }

        return super.getDrops(state, builder);
    }
}

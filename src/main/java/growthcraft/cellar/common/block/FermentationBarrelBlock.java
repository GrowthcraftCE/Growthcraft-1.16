package growthcraft.cellar.common.block;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.recipe.FermentBarrelRecipe;
import growthcraft.cellar.common.tileentity.FermentBarrelTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.lib.util.BlockStateUtils;
import growthcraft.lib.util.RecipeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class FermentationBarrelBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final VoxelShape VOXEL_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public FermentationBarrelBlock() {
        this(getInitProperties());
    }

    public FermentationBarrelBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.WOOD);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.notSolid();
        properties.harvestTool(ToolType.AXE);
        properties.harvestLevel(1);
        properties.sound(SoundType.WOOD);
        return properties;
    }

    // Blockstate Properties
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftCellarTileEntities.barrel_ferment_oak_tileentity.get().create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace())
                || player.getHeldItem(handIn).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return ActionResultType.SUCCESS;
        }

        if (!worldIn.isRemote) {
            FermentBarrelTileEntity fermentBarrelTileEntity = (FermentBarrelTileEntity) worldIn.getTileEntity(pos);
            ItemStack activeItem = player.getHeldItem(handIn);

            if (activeItem.getItem() instanceof GlassBottleItem && fermentBarrelTileEntity.getFluidTank(0).getFluidAmount() > 0) {
                try {
                    FermentBarrelRecipe recipe = RecipeUtils.findFermentRecipesByResult(worldIn, fermentBarrelTileEntity.getFluidTank(0).getFluid());
                    if (recipe.getBottleItemStack().getItem() != Items.AIR) {
                        ItemStack bottleItemStack = recipe.getBottleItemStack();

                        bottleItemStack.setDisplayName(
                                bottleItemStack.getDisplayName().copyRaw()
                                        .appendString(" ")
                                        .appendSibling(new TranslationTextComponent(fermentBarrelTileEntity.getFluidTank(0).getFluid().getTranslationKey()))
                        );

                        fermentBarrelTileEntity.getFluidTank(0).drain(250, IFluidHandler.FluidAction.EXECUTE);
                        player.getHeldItem(handIn).shrink(1);

                        if (!player.inventory.addItemStackToInventory(bottleItemStack)) {
                            player.dropItem(bottleItemStack, false);
                        }
                    }
                } catch (NullPointerException npe) {
                    // Do nothing as it isn't a fermentable fluid and a bucket should be used.
                    throw npe;
                } catch (RecipeUtils.ToManyMatchingRecipes ex) {
                    GrowthcraftCellar.LOGGER.error(ex);
                }

                return ActionResultType.SUCCESS;
            }
        }

        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof FermentBarrelTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (FermentBarrelTileEntity) tileEntity, pos);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    // Directional Facing
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) {
            worldIn.setBlockState(pos, state.with(BlockStateProperties.FACING, BlockStateUtils.getFacingFromEntity(pos, placer)), 2);
        }
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    // Shape
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL_SHAPE;
    }
}

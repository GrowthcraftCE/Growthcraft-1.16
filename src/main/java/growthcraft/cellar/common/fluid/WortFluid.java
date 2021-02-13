package growthcraft.cellar.common.fluid;

import growthcraft.cellar.init.GrowthcraftCellarFuilds;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class WortFluid extends ForgeFlowingFluid {

    protected WortFluid(ForgeFlowingFluid.Properties properties) {
        super(properties);
    }

    @Override
    public Fluid getFlowingFluid() {
        return GrowthcraftCellarFuilds.WORT_FLUID_FLOWING.get();
    }

    @Override
    public Fluid getStillFluid() {
        return GrowthcraftCellarFuilds.WORT_FLUID.get();
    }

    @Override
    protected boolean canSourcesMultiply() {
        return true;
    }

    @Override
    protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {

    }

    @Override
    protected int getSlopeFindDistance(IWorldReader worldIn) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
        return 1;
    }

    @Override
    public Item getFilledBucket() {
        return GrowthcraftCellarItems.BUCKET_WORT.get();
    }

    @Override
    protected boolean canDisplace(IFluidState fluidStateIn, IBlockReader blockReader, BlockPos pos, Fluid fluid, Direction directionIn) {
        return directionIn == Direction.DOWN && !FluidTags.getCollection().get(
                new ResourceLocation(Reference.MODID,
                        UnlocalizedName.WORT)).contains(fluid);
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_) {
        return 10;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return GrowthcraftCellarFuilds.WORT_FLUID_BLOCK.get().getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluid) {
        return fluid == GrowthcraftCellarFuilds.WORT_FLUID.get() || fluid == GrowthcraftCellarFuilds.WORT_FLUID_FLOWING.get();
    }

    @Override
    protected FluidAttributes createAttributes() {
        return super.createAttributes();
    }

    public static class Flowing extends WortFluid {

        public Flowing(ForgeFlowingFluid.Properties properties) {
            super(properties);
            setDefaultState(getStateContainer().getBaseState().with(LEVEL_1_8, 7));
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }

        @Override
        public int getLevel(IFluidState state) {
            return state.get(WortFluid.LEVEL_1_8);
        }
    }

    public static class Source extends WortFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }

        @Override
        public int getLevel(IFluidState state) {
            return 8;
        }
    }
}

package growthcraft.cellar.common.fluid;

import growthcraft.cellar.init.GrowthcraftCellarFluids;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class PaleAleFluid extends ForgeFlowingFluid {

    protected PaleAleFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Fluid getFlowingFluid() {
        return GrowthcraftCellarFluids.WORT_FLUID_FLOWING.get();
    }

    @Override
    public Fluid getStillFluid() {
        return GrowthcraftCellarFluids.WORT_FLUID.get();
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
        return GrowthcraftCellarItems.bucket_wort.get();
    }

    @Override
    protected boolean canDisplace(FluidState fluidState, IBlockReader blockReader, BlockPos pos, Fluid fluid, Direction direction) {
        ITag<Fluid> fluidTags = FluidTags.getCollection().get(
                new ResourceLocation(Reference.MODID,
                        UnlocalizedName.WORT));
        return fluidTags != null && direction == Direction.DOWN && !fluid.isIn(fluidTags);
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
    protected BlockState getBlockState(FluidState state) {
        return GrowthcraftCellarFluids.WORT_FLUID_BLOCK.get().getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluid) {
        return fluid == GrowthcraftCellarFluids.WORT_FLUID.get() || fluid == GrowthcraftCellarFluids.WORT_FLUID_FLOWING.get();
    }

    @Override
    protected FluidAttributes createAttributes() {
        return super.createAttributes();
    }

    public static class Flowing extends PaleAleFluid {

        public Flowing(Properties properties) {
            super(properties);
            setDefaultState(getStateContainer().getBaseState().with(LEVEL_1_8, 7));
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(PaleAleFluid.LEVEL_1_8);
        }
    }

    public static class Source extends PaleAleFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }
}
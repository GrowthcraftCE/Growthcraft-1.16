package growthcraft.cellar.common.fluid.wort;

import growthcraft.cellar.init.GrowthcraftCellarFluids;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

import java.awt.*;

import static growthcraft.cellar.shared.Reference.FluidColor.DEEP_AMBER_WORT_FLUID_COLOR;
import static growthcraft.cellar.shared.Reference.FluidResource.*;

public abstract class DeepAmberWortFluid extends ForgeFlowingFluid {

    private static final RegistryObject<FlowingFluidBlock> block = GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_BLOCK;
    private static final Color color = DEEP_AMBER_WORT_FLUID_COLOR;
    private static final RegistryObject<BucketItem> registry_bucket = GrowthcraftCellarItems.bucket_deep_amber_wort;
    private static final RegistryObject<DeepAmberWortFluid.Flowing> registry_fluid_flowing = GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_FLOWING;
    private static final RegistryObject<DeepAmberWortFluid.Source> registry_fluid_still = GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_STILL;

    public static final Properties FLUID_PROPERTIES = new Properties(
            registry_fluid_still,
            registry_fluid_flowing,
            FluidAttributes.builder(STILL, FLOWING)
                    .color(color.getRGB())
                    .density(0).viscosity(0)
                    .sound(SoundEvents.ITEM_BUCKET_FILL, SoundEvents.ITEM_BUCKET_EMPTY)
                    .overlay(OVERLAY))
            .block(block)
            .bucket(registry_bucket);

    protected DeepAmberWortFluid(Properties properties) {
        super(properties);
    }

    public static Color getColor() {
        return color;
    }

    @Override
    public Fluid getFlowingFluid() {
        return registry_fluid_flowing.get();
    }

    @Override
    public Fluid getStillFluid() {
        return registry_fluid_still.get();
    }

    @Override
    public Item getFilledBucket() {
        return registry_bucket.get();
    }

    public static class Flowing extends DeepAmberWortFluid {

        public Flowing() {
            super(FLUID_PROPERTIES);
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        public int getLevel(FluidState state) {
            return state.get(LEVEL_1_8);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends DeepAmberWortFluid {

        public Source() {
            super(FLUID_PROPERTIES);
        }

        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }

}
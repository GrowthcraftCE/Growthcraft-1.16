package growthcraft.cellar.common.fluid.wort;

import growthcraft.cellar.init.GrowthcraftCellarFluids;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.lib.util.ColorUtils;
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

import static growthcraft.cellar.shared.Reference.FluidColor.HOPPED_GOLDEN_WORT_FLUID_COLOR;
import static growthcraft.cellar.shared.Reference.FluidResource.*;

public abstract class HoppedGoldenWortFluid extends ForgeFlowingFluid {

    private static final RegistryObject<FlowingFluidBlock> block = GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_BLOCK;
    private static final ColorUtils.GrowthcraftColor color = HOPPED_GOLDEN_WORT_FLUID_COLOR;
    private static final RegistryObject<BucketItem> registry_bucket = GrowthcraftCellarItems.bucket_hopped_golden_wort;
    private static final RegistryObject<HoppedGoldenWortFluid.Flowing> registry_fluid_flowing = GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_FLOWING;
    private static final RegistryObject<HoppedGoldenWortFluid.Source> registry_fluid_still = GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_STILL;

    public static final Properties FLUID_PROPERTIES = new Properties(
            registry_fluid_still,
            registry_fluid_flowing,
            FluidAttributes.builder(STILL, FLOWING)
                    .color(color.toIntValue())
                    .density(3000).luminosity(2).viscosity(1000)
                    .sound(SoundEvents.ITEM_BUCKET_FILL, SoundEvents.ITEM_BUCKET_EMPTY)
                    .overlay(OVERLAY))
            .block(block)
            .bucket(registry_bucket);

    protected HoppedGoldenWortFluid(Properties properties) {
        super(properties);
    }

    public static Color getColor() {
        return color.getColor();
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

    public static class Flowing extends HoppedGoldenWortFluid {

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

    public static class Source extends HoppedGoldenWortFluid {

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
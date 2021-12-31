package growthcraft.milk.common.fluid;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.util.ColorUtils;
import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.init.GrowthcraftMilkItems;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

import java.awt.*;

import static growthcraft.milk.shared.Reference.FluidColor.MILK_FLUID_COLOR;

public abstract class MilkFluid extends ForgeFlowingFluid {

    private static final RegistryObject<FlowingFluidBlock> block = GrowthcraftMilkFluids.MILK_FLUID_BLOCK;
    private static final ColorUtils.GrowthcraftColor color = MILK_FLUID_COLOR;
    private static final RegistryObject<GrowthcraftBucketItem> registry_bucket = GrowthcraftMilkItems.BUCKET_MILK;
    private static final RegistryObject<Flowing> registry_fluid_flowing = GrowthcraftMilkFluids.MILK_FLUID_FLOWING;
    private static final RegistryObject<Source> registry_fluid_still = GrowthcraftMilkFluids.MILK_FLUID_STILL;

    public static final Properties FLUID_PROPERTIES = new Properties(
            registry_fluid_still,
            registry_fluid_flowing,
            FluidAttributes.builder(FluidUtils.FluidResource.STILL, FluidUtils.FluidResource.FLOWING)
                    .color(color.toIntValue())
                    .density(3000).luminosity(2).viscosity(1000)
                    .sound(SoundEvents.ITEM_BUCKET_FILL, SoundEvents.ITEM_BUCKET_EMPTY)
                    .overlay(FluidUtils.FluidResource.OVERLAY))
            .block(block)
            .bucket(registry_bucket);

    protected MilkFluid(Properties properties) {
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

    public static class Flowing extends MilkFluid {

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

    public static class Source extends MilkFluid {

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

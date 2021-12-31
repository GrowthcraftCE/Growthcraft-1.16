package growthcraft.milk.init;

import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.common.fluid.ButterMilkFluid;
import growthcraft.milk.common.fluid.CondensedMilkFluid;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MODID);

    public static final RegistryObject<ButterMilkFluid.Source> BUTTER_MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.BUTTER_MILK).get(FluidUtils.STILL), ButterMilkFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> BUTTER_MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.BUTTER_MILK).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(BUTTER_MILK_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<ButterMilkFluid.Flowing> BUTTER_MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.BUTTER_MILK).get(FluidUtils.FLOWING), ButterMilkFluid.Flowing::new);

    public static final RegistryObject<CondensedMilkFluid.Source> CONDENSED_MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CONDENSED_MILK).get(FluidUtils.STILL), CondensedMilkFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> CONDENSED_MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.CONDENSED_MILK).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(CONDENSED_MILK_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<CondensedMilkFluid.Flowing> CONDENSED_MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CONDENSED_MILK).get(FluidUtils.FLOWING), CondensedMilkFluid.Flowing::new);

    private GrowthcraftMilkFluids() {
        /* Prevent generation of public constructor */
    }
}

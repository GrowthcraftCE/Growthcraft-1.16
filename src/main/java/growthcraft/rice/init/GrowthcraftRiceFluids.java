package growthcraft.rice.init;

import growthcraft.lib.util.FluidUtils;
import growthcraft.rice.common.fluid.RiceWaterFluid;
import growthcraft.rice.common.fluid.fermented.RiceWineFluid;
import growthcraft.rice.common.fluid.fermented.SakeFluid;
import growthcraft.rice.shared.Reference;
import growthcraft.rice.shared.UnlocalizedName;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftRiceFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MODID);

    public static final RegistryObject<RiceWaterFluid.Source> RICE_WATER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WATER).get(FluidUtils.STILL), RiceWaterFluid.Source::new);
    public static final RegistryObject<RiceWaterFluid.Flowing> RICE_WATER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WATER).get(FluidUtils.FLOWING), RiceWaterFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> RICE_WATER_FLUID_BLOCK =
            GrowthcraftRiceBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WATER).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(RICE_WATER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<RiceWineFluid.Source> RICE_WINE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WINE).get(FluidUtils.STILL), RiceWineFluid.Source::new);
    public static final RegistryObject<RiceWineFluid.Flowing> RICE_WINE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WINE).get(FluidUtils.FLOWING), RiceWineFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> RICE_WINE_FLUID_BLOCK =
            GrowthcraftRiceBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WINE).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(RICE_WINE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<SakeFluid.Source> SAKE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.SAKE).get(FluidUtils.STILL), SakeFluid.Source::new);
    public static final RegistryObject<SakeFluid.Flowing> SAKE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.SAKE).get(FluidUtils.FLOWING), SakeFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> SAKE_FLUID_BLOCK =
            GrowthcraftRiceBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.SAKE).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(SAKE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

}

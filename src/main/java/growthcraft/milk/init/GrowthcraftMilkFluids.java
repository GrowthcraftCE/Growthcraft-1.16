package growthcraft.milk.init;

import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.common.fluid.*;
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

    public static final RegistryObject<CreamFluid.Source> CREAM_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CREAM).get(FluidUtils.STILL), CreamFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> CREAM_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.CREAM).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(CREAM_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<CreamFluid.Flowing> CREAM_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CREAM).get(FluidUtils.FLOWING), CreamFluid.Flowing::new);

    public static final RegistryObject<KumisFluid.Source> KUMIS_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.KUMIS).get(FluidUtils.STILL), KumisFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> KUMIS_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.KUMIS).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(KUMIS_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<KumisFluid.Flowing> KUMIS_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.KUMIS).get(FluidUtils.FLOWING), KumisFluid.Flowing::new);

    public static final RegistryObject<MilkFluid.Source> MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.MILK).get(FluidUtils.STILL), MilkFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.MILK).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(MILK_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<MilkFluid.Flowing> MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.MILK).get(FluidUtils.FLOWING), MilkFluid.Flowing::new);

    public static final RegistryObject<RennetFluid.Source> RENNET_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RENNET).get(FluidUtils.STILL), RennetFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> RENNET_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.RENNET).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(RENNET_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<RennetFluid.Flowing> RENNET_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RENNET).get(FluidUtils.FLOWING), RennetFluid.Flowing::new);

    public static final RegistryObject<SkimMilkFluid.Source> SKIM_MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.SKIM_MILK).get(FluidUtils.STILL), SkimMilkFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> SKIM_MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.SKIM_MILK).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(SKIM_MILK_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<SkimMilkFluid.Flowing> SKIM_MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.SKIM_MILK).get(FluidUtils.FLOWING), SkimMilkFluid.Flowing::new);

    public static final RegistryObject<WheyFluid.Source> WHEY_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WHEY).get(FluidUtils.STILL), WheyFluid.Source::new);
    public static final RegistryObject<FlowingFluidBlock> WHEY_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WHEY).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WHEY_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<WheyFluid.Flowing> WHEY_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WHEY).get(FluidUtils.FLOWING), WheyFluid.Flowing::new);

    private GrowthcraftMilkFluids() {
        /* Prevent generation of public constructor */
    }
}

package growthcraft.apiary.init;

import growthcraft.apiary.common.fluid.*;
import growthcraft.apiary.common.fluid.fermented.HoneyMeadFluid;
import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import growthcraft.lib.util.FluidUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApiaryFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MODID);

    public static final RegistryObject<HoneyFluid.Source> HONEY_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY).get(FluidUtils.STILL), HoneyFluid.Source::new);
    public static final RegistryObject<HoneyFluid.Flowing> HONEY_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY).get(FluidUtils.FLOWING), HoneyFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> HONEY_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(HONEY_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<HoneyMeadFluid.Source> HONEY_MEAD_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY_MEAD).get(FluidUtils.STILL), HoneyMeadFluid.Source::new);
    public static final RegistryObject<HoneyMeadFluid.Flowing> HONEY_MEAD_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY_MEAD).get(FluidUtils.FLOWING), HoneyMeadFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> HONEY_MEAD_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.HONEY_MEAD).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(HONEY_MEAD_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxBlackFluid.Source> WAX_BLACK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLACK).get(FluidUtils.STILL), WaxBlackFluid.Source::new);
    public static final RegistryObject<WaxBlackFluid.Flowing> WAX_BLACK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLACK).get(FluidUtils.FLOWING), WaxBlackFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_BLACK_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLACK).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_BLACK_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxBlueFluid.Source> WAX_BLUE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLUE).get(FluidUtils.STILL), WaxBlueFluid.Source::new);
    public static final RegistryObject<WaxBlueFluid.Flowing> WAX_BLUE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLUE).get(FluidUtils.FLOWING), WaxBlueFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_BLUE_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLUE).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_BLUE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxBrownFluid.Source> WAX_BROWN_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BROWN).get(FluidUtils.STILL), WaxBrownFluid.Source::new);
    public static final RegistryObject<WaxBrownFluid.Flowing> WAX_BROWN_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BROWN).get(FluidUtils.FLOWING), WaxBrownFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_BROWN_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BROWN).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_BROWN_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxCyanFluid.Source> WAX_CYAN_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_CYAN).get(FluidUtils.STILL), WaxCyanFluid.Source::new);
    public static final RegistryObject<WaxCyanFluid.Flowing> WAX_CYAN_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_CYAN).get(FluidUtils.FLOWING), WaxCyanFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_CYAN_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_CYAN).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_CYAN_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxGrayFluid.Source> WAX_GRAY_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GRAY).get(FluidUtils.STILL), WaxGrayFluid.Source::new);
    public static final RegistryObject<WaxGrayFluid.Flowing> WAX_GRAY_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GRAY).get(FluidUtils.FLOWING), WaxGrayFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_GRAY_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GRAY).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_GRAY_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxGreenFluid.Source> WAX_GREEN_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GREEN).get(FluidUtils.STILL), WaxGreenFluid.Source::new);
    public static final RegistryObject<WaxGreenFluid.Flowing> WAX_GREEN_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GREEN).get(FluidUtils.FLOWING), WaxGreenFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_GREEN_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GREEN).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_GREEN_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxLightBlueFluid.Source> WAX_LIGHT_BLUE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_BLUE).get(FluidUtils.STILL), WaxLightBlueFluid.Source::new);
    public static final RegistryObject<WaxLightBlueFluid.Flowing> WAX_LIGHT_BLUE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_BLUE).get(FluidUtils.FLOWING), WaxLightBlueFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_LIGHT_BLUE_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_BLUE).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_LIGHT_BLUE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxLightGrayFluid.Source> WAX_LIGHT_GRAY_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_GRAY).get(FluidUtils.STILL), WaxLightGrayFluid.Source::new);
    public static final RegistryObject<WaxLightGrayFluid.Flowing> WAX_LIGHT_GRAY_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_GRAY).get(FluidUtils.FLOWING), WaxLightGrayFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_LIGHT_GRAY_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_GRAY).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_LIGHT_GRAY_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxLimeFluid.Source> WAX_LIME_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIME).get(FluidUtils.STILL), WaxLimeFluid.Source::new);
    public static final RegistryObject<WaxLimeFluid.Flowing> WAX_LIME_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIME).get(FluidUtils.FLOWING), WaxLimeFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_LIME_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIME).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_LIME_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxMagentaFluid.Source> WAX_MAGENTA_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_MAGENTA).get(FluidUtils.STILL), WaxMagentaFluid.Source::new);
    public static final RegistryObject<WaxMagentaFluid.Flowing> WAX_MAGENTA_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_MAGENTA).get(FluidUtils.FLOWING), WaxMagentaFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_MAGENTA_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_MAGENTA).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_MAGENTA_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxOrangeFluid.Source> WAX_ORANGE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_ORANGE).get(FluidUtils.STILL), WaxOrangeFluid.Source::new);
    public static final RegistryObject<WaxOrangeFluid.Flowing> WAX_ORANGE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_ORANGE).get(FluidUtils.FLOWING), WaxOrangeFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_ORANGE_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_ORANGE).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_ORANGE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxPinkFluid.Source> WAX_PINK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PINK).get(FluidUtils.STILL), WaxPinkFluid.Source::new);
    public static final RegistryObject<WaxPinkFluid.Flowing> WAX_PINK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PINK).get(FluidUtils.FLOWING), WaxPinkFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_PINK_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PINK).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_PINK_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxPurpleFluid.Source> WAX_PURPLE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PURPLE).get(FluidUtils.STILL), WaxPurpleFluid.Source::new);
    public static final RegistryObject<WaxPurpleFluid.Flowing> WAX_PURPLE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PURPLE).get(FluidUtils.FLOWING), WaxPurpleFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_PURPLE_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PURPLE).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_PURPLE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxRedFluid.Source> WAX_RED_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_RED).get(FluidUtils.STILL), WaxRedFluid.Source::new);
    public static final RegistryObject<WaxRedFluid.Flowing> WAX_RED_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_RED).get(FluidUtils.FLOWING), WaxRedFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_RED_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_RED).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_RED_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxWhiteFluid.Source> WAX_WHITE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_WHITE).get(FluidUtils.STILL), WaxWhiteFluid.Source::new);
    public static final RegistryObject<WaxWhiteFluid.Flowing> WAX_WHITE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_WHITE).get(FluidUtils.FLOWING), WaxWhiteFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_WHITE_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_WHITE).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_WHITE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<WaxYellowFluid.Source> WAX_YELLOW_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_YELLOW).get(FluidUtils.STILL), WaxYellowFluid.Source::new);
    public static final RegistryObject<WaxYellowFluid.Flowing> WAX_YELLOW_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_YELLOW).get(FluidUtils.FLOWING), WaxYellowFluid.Flowing::new);
    public static final RegistryObject<FlowingFluidBlock> WAX_YELLOW_FLUID_BLOCK =
            GrowthcraftApiaryBlocks.BLOCKS.register(FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_YELLOW).get(FluidUtils.BLOCK),
                    () -> new FlowingFluidBlock(WAX_YELLOW_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

    private GrowthcraftApiaryFluids() {
        /* Prevent default public constructor */
    }

}

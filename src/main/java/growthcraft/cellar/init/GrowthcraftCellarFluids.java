package growthcraft.cellar.init;

import growthcraft.cellar.common.fluid.*;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static growthcraft.cellar.shared.Reference.FluidName;

public class GrowthcraftCellarFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MODID);

	public static final String STILL = "fluid";
	public static final String FLOWING = "flowing";
	public static final String BLOCK = "block";

	private GrowthcraftCellarFluids() { /* Prevent Public Constructor */ }

	// Fluid: Amber Ale
	public static final RegistryObject<AmberAleFluid.Source> AMBER_ALE_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.AMBER_ALE).get(STILL), AmberAleFluid.Source::new);
	public static final RegistryObject<AmberAleFluid.Flowing> AMBER_ALE_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.AMBER_ALE).get(FLOWING), AmberAleFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> AMBER_ALE_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.AMBER_ALE).get(BLOCK),
					() -> new FlowingFluidBlock(AMBER_ALE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Amber Lager
	public static final RegistryObject<AmberLagerFluid.Source> AMBER_LAGER_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.AMBER_LAGER).get(STILL), AmberLagerFluid.Source::new);
	public static final RegistryObject<AmberLagerFluid.Flowing> AMBER_LAGER_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.AMBER_LAGER).get(FLOWING), AmberLagerFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> AMBER_LAGER_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.AMBER_LAGER).get(BLOCK),
					() -> new FlowingFluidBlock(AMBER_LAGER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Brown Ale
	public static final RegistryObject<BrownAleFluid.Source> BROWN_ALE_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.BROWN_ALE).get(STILL), BrownAleFluid.Source::new);
	public static final RegistryObject<BrownAleFluid.Flowing> BROWN_ALE_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.BROWN_ALE).get(FLOWING), BrownAleFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> BROWN_ALE_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.BROWN_ALE).get(BLOCK),
					() -> new FlowingFluidBlock(BROWN_ALE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Brown Lager
	public static final RegistryObject<BrownLagerFluid.Source> BROWN_LAGER_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.BROWN_LAGER).get(STILL), BrownLagerFluid.Source::new);
	public static final RegistryObject<BrownLagerFluid.Flowing> BROWN_LAGER_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.BROWN_LAGER).get(FLOWING), BrownLagerFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> BROWN_LAGER_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.BROWN_LAGER).get(BLOCK),
					() -> new FlowingFluidBlock(BROWN_LAGER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Copper Ale
	public static final RegistryObject<CopperAleFluid.Source> COPPER_ALE_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.COPPER_ALE).get(STILL), CopperAleFluid.Source::new);
	public static final RegistryObject<CopperAleFluid.Flowing> COPPER_ALE_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.COPPER_ALE).get(FLOWING), CopperAleFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> COPPER_ALE_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.COPPER_ALE).get(BLOCK),
					() -> new FlowingFluidBlock(COPPER_ALE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Copper Lager
	public static final RegistryObject<CopperLagerFluid.Source> COPPER_LAGER_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.COPPER_LAGER).get(STILL), CopperLagerFluid.Source::new);
	public static final RegistryObject<CopperLagerFluid.Flowing> COPPER_LAGER_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.COPPER_LAGER).get(FLOWING), CopperLagerFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> COPPER_LAGER_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.COPPER_LAGER).get(BLOCK),
					() -> new FlowingFluidBlock(COPPER_LAGER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Dark Lager
	public static final RegistryObject<DarkLagerFluid.Source> DARK_LAGER_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DARK_LAGER).get(STILL), DarkLagerFluid.Source::new);
	public static final RegistryObject<DarkLagerFluid.Flowing> DARK_LAGER_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DARK_LAGER).get(FLOWING), DarkLagerFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> DARK_LAGER_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.DARK_LAGER).get(BLOCK),
					() -> new FlowingFluidBlock(DARK_LAGER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: IPA Ale
	public static final RegistryObject<IpaAleFluid.Source> IPA_ALE_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.IPA_ALE).get(STILL), IpaAleFluid.Source::new);
	public static final RegistryObject<IpaAleFluid.Flowing> IPA_ALE_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.IPA_ALE).get(FLOWING), IpaAleFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> IPA_ALE_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.IPA_ALE).get(BLOCK),
					() -> new FlowingFluidBlock(IPA_ALE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Old Port Ale
	public static final RegistryObject<OldPortAleFluid.Source> OLD_PORT_ALE_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.OLD_PORT_ALE).get(STILL), OldPortAleFluid.Source::new);
	public static final RegistryObject<OldPortAleFluid.Flowing> OLD_PORT_ALE_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.OLD_PORT_ALE).get(FLOWING), OldPortAleFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> OLD_PORT_ALE_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.OLD_PORT_ALE).get(BLOCK),
					() -> new FlowingFluidBlock(OLD_PORT_ALE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Pale Ale
	public static final RegistryObject<PaleAleFluid.Source> PALE_ALE_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PALE_ALE).get(STILL), PaleAleFluid.Source::new);
	public static final RegistryObject<PaleAleFluid.Flowing> PALE_ALE_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PALE_ALE).get(FLOWING), PaleAleFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> PALE_ALE_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.PALE_ALE).get(BLOCK),
					() -> new FlowingFluidBlock(PALE_ALE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Pale Lager
	public static final RegistryObject<PaleLagerFluid.Source> PALE_LAGER_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PALE_LAGER).get(STILL), PaleLagerFluid.Source::new);
	public static final RegistryObject<PaleLagerFluid.Flowing> PALE_LAGER_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PALE_LAGER).get(FLOWING), PaleLagerFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> PALE_LAGER_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.PALE_LAGER).get(BLOCK),
					() -> new FlowingFluidBlock(PALE_LAGER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Pilsner Lager
	public static final RegistryObject<PilsnerLagerFluid.Source> PILSNER_LAGER_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PILSNER_LAGER).get(STILL), PilsnerLagerFluid.Source::new);
	public static final RegistryObject<PilsnerLagerFluid.Flowing> PILSNER_LAGER_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PILSNER_LAGER).get(FLOWING), PilsnerLagerFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> PILSNER_LAGER_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.PILSNER_LAGER).get(BLOCK),
					() -> new FlowingFluidBlock(PILSNER_LAGER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Stout Ale
	public static final RegistryObject<StoutAleFluid.Source> STOUT_ALE_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.STOUT_ALE).get(STILL), StoutAleFluid.Source::new);
	public static final RegistryObject<StoutAleFluid.Flowing> STOUT_ALE_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.STOUT_ALE).get(FLOWING), StoutAleFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> STOUT_ALE_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.STOUT_ALE).get(BLOCK),
					() -> new FlowingFluidBlock(STOUT_ALE_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Vienna Lager
	public static final RegistryObject<ViennaLagerFluid.Source> VIENNA_LAGER_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.VIENNA_LAGER).get(STILL), ViennaLagerFluid.Source::new);
	public static final RegistryObject<ViennaLagerFluid.Flowing> VIENNA_LAGER_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.VIENNA_LAGER).get(FLOWING), ViennaLagerFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> VIENNA_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.VIENNA_LAGER).get(BLOCK),
					() -> new FlowingFluidBlock(VIENNA_LAGER_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Wort
	public static final RegistryObject<WortFluid.Source> WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.WORT).get(STILL), WortFluid.Source::new);
	public static final RegistryObject<WortFluid.Flowing> WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.WORT).get(FLOWING), WortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.WORT).get(BLOCK),
					() -> new FlowingFluidBlock(WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Pale Golden Wort
	public static final RegistryObject<PaleGoldenWortFluid.Source> PALE_GOLDEN_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PALE_GOLDEN_WORT).get(STILL), PaleGoldenWortFluid.Source::new);
	public static final RegistryObject<PaleGoldenWortFluid.Flowing> PALE_GOLDEN_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.PALE_GOLDEN_WORT).get(FLOWING), PaleGoldenWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> PALE_GOLDEN_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.PALE_GOLDEN_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(PALE_GOLDEN_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Golden Wort
	public static final RegistryObject<GoldenWortFluid.Source> GOLDEN_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.GOLDEN_WORT).get(STILL), GoldenWortFluid.Source::new);
	public static final RegistryObject<GoldenWortFluid.Flowing> GOLDEN_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.GOLDEN_WORT).get(FLOWING), GoldenWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> GOLDEN_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.GOLDEN_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(GOLDEN_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Amber Wort
	public static final RegistryObject<AmberWortFluid.Source> AMBER_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.AMBER_WORT).get(STILL), AmberWortFluid.Source::new);
	public static final RegistryObject<AmberWortFluid.Flowing> AMBER_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.AMBER_WORT).get(FLOWING), AmberWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> AMBER_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.AMBER_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(AMBER_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Deep Amber Wort
	public static final RegistryObject<DeepAmberWortFluid.Source> DEEP_AMBER_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DEEP_AMBER_WORT).get(STILL), DeepAmberWortFluid.Source::new);
	public static final RegistryObject<DeepAmberWortFluid.Flowing> DEEP_AMBER_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DEEP_AMBER_WORT).get(FLOWING), DeepAmberWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> DEEP_AMBER_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.DEEP_AMBER_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(DEEP_AMBER_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Copper Wort
	public static final RegistryObject<CopperWortFluid.Source> COPPER_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.COPPER_WORT).get(STILL), CopperWortFluid.Source::new);
	public static final RegistryObject<CopperWortFluid.Flowing> COPPER_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.COPPER_WORT).get(FLOWING), CopperWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> COPPER_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.COPPER_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(COPPER_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Deep Copper Wort
	public static final RegistryObject<DeepCopperWortFluid.Source> DEEP_COPPER_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DEEP_COPPER_WORT).get(STILL), DeepCopperWortFluid.Source::new);
	public static final RegistryObject<DeepCopperWortFluid.Flowing> DEEP_COPPER_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DEEP_COPPER_WORT).get(FLOWING), DeepCopperWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> DEEP_COPPER_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.DEEP_COPPER_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(DEEP_COPPER_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Brown Wort
	public static final RegistryObject<BrownWortFluid.Source> BROWN_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.BROWN_WORT).get(STILL), BrownWortFluid.Source::new);
	public static final RegistryObject<BrownWortFluid.Flowing> BROWN_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.BROWN_WORT).get(FLOWING), BrownWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> BROWN_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.BROWN_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(BROWN_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

	// Fluid: Dark Wort
	public static final RegistryObject<DarkWortFluid.Source> DARK_WORT_FLUID_STILL = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DARK_WORT).get(STILL), DarkWortFluid.Source::new);
	public static final RegistryObject<DarkWortFluid.Flowing> DARK_WORT_FLUID_FLOWING = FLUIDS.register(
			UnlocalizedName.getFluidNames(FluidName.DARK_WORT).get(FLOWING), DarkWortFluid.Flowing::new);
	public static final RegistryObject<FlowingFluidBlock> DARK_WORT_FLUID_BLOCK =
			GrowthcraftCellarBlocks.BLOCKS.register(UnlocalizedName.getFluidNames(FluidName.DARK_WORT).get(BLOCK),
					() -> new FlowingFluidBlock(DARK_WORT_FLUID_STILL, AbstractBlock.Properties.from(Blocks.WATER)));

}

package growthcraft.cellar.init;

import growthcraft.cellar.common.fluid.WortFluid;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MODID);

    /* Resource Locations */
    public static final ResourceLocation WORT_FLUID_STILL_RESOURCE = new ResourceLocation(Reference.MODID, "block/fluid/wort_fluid_still");
    public static final ResourceLocation WORT_FLUID_FLOWING_RESOURCE = new ResourceLocation(Reference.MODID, "block/fluid/wort_fluid_flowing");
    public static final ResourceLocation WORT_FLUID_OVERLAY_RESOURCE = new ResourceLocation(Reference.MODID, "block/fluid/wort_fluid_overlay");

    // Flowing, Still, and Block
    public static final RegistryObject<WortFluid.Source> WORT_FLUID = FLUIDS.register(
            UnlocalizedName.WORT_FLUID,
            () -> new WortFluid.Source(GrowthcraftCellarFluids.WORT_FLUID_PROPERTIES));

    public static final RegistryObject<WortFluid.Flowing> WORT_FLUID_FLOWING = FLUIDS.register(
            UnlocalizedName.WORT_FLUID_FLOWING,
            () -> new WortFluid.Flowing(GrowthcraftCellarFluids.WORT_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> WORT_FLUID_BLOCK = GrowthcraftCellarBlocks.BLOCKS.register(
            UnlocalizedName.WORT,
            () -> new FlowingFluidBlock(
                    WORT_FLUID, AbstractBlock.Properties.from(Blocks.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()
            )
    );

    public static final ForgeFlowingFluid.Properties WORT_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            WORT_FLUID, WORT_FLUID_FLOWING,
            FluidAttributes.builder(WORT_FLUID_STILL_RESOURCE, WORT_FLUID_FLOWING_RESOURCE)
                    .color(0xD0AF4E)
                    .density(0)
                    .temperature(0)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.ITEM_BUCKET_FILL, SoundEvents.ITEM_BUCKET_EMPTY)
                    .overlay(WORT_FLUID_OVERLAY_RESOURCE))
            .block(WORT_FLUID_BLOCK)
            .bucket(GrowthcraftCellarItems.bucket_wort);

    public static ResourceLocation getFluidResourceLocation(String unlocalizedName, String type) {
        return new ResourceLocation(Reference.MODID, String.format("block/fluid/%s_%s", unlocalizedName, type));
    }
}

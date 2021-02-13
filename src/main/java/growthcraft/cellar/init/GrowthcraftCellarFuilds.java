package growthcraft.cellar.init;

import growthcraft.cellar.common.fluid.WortFluid;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarFuilds {
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, Reference.MODID);

    /* Resource Locations */
    public static final ResourceLocation WORT_FLUID_STILL_RESOURCE = new ResourceLocation(Reference.MODID, "block/fluid/wort_fluid_still");
    public static final ResourceLocation WORT_FLUID_FLOWING_RESOURCE = new ResourceLocation(Reference.MODID, "block/fluid/wort_fluid_flowing");
    public static final ResourceLocation WORT_FLUID_OVERLAY_RESOURCE = new ResourceLocation(Reference.MODID, "block/fluid/wort_fluid_overlay");

    // Flowing, Still, and Block
    public static final RegistryObject<WortFluid.Source> WORT_FLUID;
    public static final RegistryObject<WortFluid.Flowing> WORT_FLUID_FLOWING;
    public static final RegistryObject<FlowingFluidBlock> WORT_FLUID_BLOCK;

    static {
        WORT_FLUID = FLUIDS.register(
                UnlocalizedName.WORT_FLUID,
                () -> new WortFluid.Source(GrowthcraftCellarFuilds.WORT_FLUID_PROPERTIES));
        WORT_FLUID_FLOWING = FLUIDS.register(
                UnlocalizedName.WORT_FLUID_FLOWING,
                () -> new WortFluid.Flowing(GrowthcraftCellarFuilds.WORT_FLUID_PROPERTIES));
        WORT_FLUID_BLOCK = GrowthcraftCellarBlocks.BLOCKS.register(
                UnlocalizedName.WORT,
                () -> new FlowingFluidBlock(
                        () -> WORT_FLUID.get(), Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()
                )
        );
    }

    public static final ForgeFlowingFluid.Properties WORT_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> WORT_FLUID.get(), () -> WORT_FLUID_FLOWING.get(),
            FluidAttributes.builder(WORT_FLUID_STILL_RESOURCE, WORT_FLUID_FLOWING_RESOURCE)
                    .color(0xD0AF4E).density(0).temperature(0).luminosity(0).viscosity(0)
                    .sound(SoundEvents.BLOCK_WATER_AMBIENT)
                    .overlay(WORT_FLUID_OVERLAY_RESOURCE))
            .block(() -> WORT_FLUID_BLOCK.get()).bucket(() -> GrowthcraftCellarItems.BUCKET_WORT.get());

    public static ResourceLocation getFluidResourceLocation(String unlocalizedName, String type) {
        return new ResourceLocation(Reference.MODID, String.format("blocks/fluids/%s_%s", unlocalizedName, type));
    }
}

package growthcraft.rice.init.client;

import growthcraft.rice.init.GrowthcraftRiceBlocks;
import growthcraft.rice.init.GrowthcraftRiceFluids;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftRiceBlockRenders {
    private GrowthcraftRiceBlockRenders() {
        /* Disable default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceBlocks.RICE_CROP.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.RICE_WATER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.RICE_WATER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.RICE_WATER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.RICE_WINE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.RICE_WINE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.RICE_WINE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.SAKE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.SAKE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceFluids.SAKE_FLUID_STILL.get(), RenderType.getTranslucent());
    }

}

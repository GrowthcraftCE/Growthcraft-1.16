package growthcraft.milk.init.client;

import growthcraft.milk.init.GrowthcraftMilkFluids;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftMilkBlockRenders {

    private GrowthcraftMilkBlockRenders() {
        /* Disable default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.BUTTER_MILK_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.CONDENSED_MILK_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.CREAM_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.KUMIS_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.RENNET_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.SKIM_MILK_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.WHEY_FLUID_BLOCK.get(), RenderType.getTranslucent());
    }

}

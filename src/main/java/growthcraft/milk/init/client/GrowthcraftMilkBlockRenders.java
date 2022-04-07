package growthcraft.milk.init.client;

import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftMilkBlockRenders {

    private GrowthcraftMilkBlockRenders() {
        /* Disable default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.THISTLE_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.MIXING_VAT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.CHEESE_PRESS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.PANCHEON.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.BUTTER_MILK_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.CONDENSED_MILK_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.CREAM_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.KUMIS_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.RENNET_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.SKIM_MILK_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkFluids.WHEY_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.CHEDDAR_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.APPENZELLER_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.APPENZELLER_CHEESE_AGED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.ASIAGO_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.EMMENTALER_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.GORGONZOLA_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.GOUDA_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.MONTEREY_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.PARMESAN_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.PROVOLONE_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.CASU_MARZU_CHEESE_CURDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftMilkBlocks.RICOTTA_CHEESE_CURDS.get(), RenderType.getCutout());

    }

}

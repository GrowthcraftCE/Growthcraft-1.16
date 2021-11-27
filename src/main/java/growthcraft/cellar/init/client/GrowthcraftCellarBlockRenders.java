package growthcraft.cellar.init.client;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.init.GrowthcraftCellarFluids;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftCellarBlockRenders {
    private GrowthcraftCellarBlockRenders() {
        /* Disable default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarBlocks.brew_kettle.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarBlocks.culture_jar.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarBlocks.hops_vine.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarBlocks.GRAPE_VINE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarBlocks.GRAPE_VINE_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarBlocks.GRAPE_VINE_LEAVES.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_WINE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_WINE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_WINE_FLUID_STILL.get(), RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_ALE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_LAGER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_ALE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_LAGER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_ALE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_LAGER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DARK_LAGER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DARK_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DEEP_COPPER_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.GOLDEN_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.IPA_ALE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.OLD_PORT_ALE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_ALE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_GOLDEN_WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_LAGER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PILSNER_LAGER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_GRAPE_JUICE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.STOUT_ALE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.VIENNA_LAGER_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WHITE_GRAPE_JUICE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WORT_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_WINE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PURPLE_WINE_FLUID_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WHITE_WINE_FLUID_BLOCK.get(), RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_ALE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_LAGER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_ALE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_LAGER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_ALE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_LAGER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DARK_LAGER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DARK_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DEEP_COPPER_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.GOLDEN_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.IPA_ALE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.OLD_PORT_ALE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_ALE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_GOLDEN_WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_LAGER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PILSNER_LAGER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_GRAPE_JUICE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.STOUT_ALE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.VIENNA_LAGER_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WHITE_GRAPE_JUICE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WORT_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_WINE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PURPLE_WINE_FLUID_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WHITE_WINE_FLUID_FLOWING.get(), RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_ALE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_LAGER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.AMBER_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_ALE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_LAGER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.BROWN_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_ALE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_LAGER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.COPPER_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DARK_LAGER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DARK_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.DEEP_COPPER_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.GOLDEN_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.IPA_ALE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.OLD_PORT_ALE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_ALE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_GOLDEN_WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PALE_LAGER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PILSNER_LAGER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_GRAPE_JUICE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.STOUT_ALE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.VIENNA_LAGER_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WHITE_GRAPE_JUICE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WORT_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.RED_WINE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.PURPLE_WINE_FLUID_STILL.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarFluids.WHITE_WINE_FLUID_STILL.get(), RenderType.getTranslucent());
    }

}

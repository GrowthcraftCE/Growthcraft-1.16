package growthcraft.apiary.init.client;

import growthcraft.apiary.init.GrowthcraftApiaryBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftApiaryBlockRenders {

    private GrowthcraftApiaryBlockRenders() {
        /* Prevent default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_BLACK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_BLUE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_BROWN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_CYAN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_GRAY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_GREEN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_LIGHT_BLUE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_LIGHT_GRAY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_LIME.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_MAGENTA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_ORANGE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_PINK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_PURPLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_RED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_WHITE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_YELLOW.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_BLACK_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_BLUE_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_BROWN_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_CYAN_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_GRAY_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_GREEN_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_LIGHT_BLUE_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_LIGHT_GRAY_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_LIME_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_MAGENTA_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_ORANGE_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_PINK_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_PURPLE_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_RED_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_WHITE_WALL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_YELLOW_WALL.get(), RenderType.getCutout());
    }
}

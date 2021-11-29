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
        RenderTypeLookup.setRenderLayer(GrowthcraftApiaryBlocks.CANDLE_BLACK_WALL.get(), RenderType.getCutout());
    }
}

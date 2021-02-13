package growthcraft.hops.init.client;

import growthcraft.hops.init.GrowthcraftHopsBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftHopsBlockRenders {

    private GrowthcraftHopsBlockRenders() { /* Disable default public constructor */ }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftHopsBlocks.hopsBush.get(), RenderType.getCutout());
    }
}

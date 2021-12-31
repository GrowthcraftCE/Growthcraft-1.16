package growthcraft.rice.init.client;

import growthcraft.rice.init.GrowthcraftRiceBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftRiceBlockRenders {
    private GrowthcraftRiceBlockRenders() {
        /* Disable default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftRiceBlocks.RICE_CROP.get(), RenderType.getCutout());
    }

}

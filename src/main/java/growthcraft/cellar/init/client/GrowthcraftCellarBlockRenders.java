package growthcraft.cellar.init.client;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftCellarBlockRenders {
    private GrowthcraftCellarBlockRenders() {
        /* Disable default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftCellarBlocks.brew_kettle.get(), RenderType.getCutout());
    }

}

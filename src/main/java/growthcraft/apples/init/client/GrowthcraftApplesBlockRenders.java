package growthcraft.apples.init.client;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftApplesBlockRenders {

    private GrowthcraftApplesBlockRenders() { /* Disable default public constructor */ }

    public static void setRenderLayer() {
        RenderTypeLookup.setRenderLayer(GrowthcraftApplesBlocks.applePlankTrapdoor.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApplesBlocks.appleTreeSapling.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftApplesBlocks.appleFishtrap.get(), RenderType.getCutout());
    }
}

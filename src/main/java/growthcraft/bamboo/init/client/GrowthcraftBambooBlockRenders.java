package growthcraft.bamboo.init.client;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftBambooBlockRenders {

    private GrowthcraftBambooBlockRenders() {
        /* Prevent default public constructor */
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftBambooBlocks.BAMBOO_PLANK_DOOR.get(), RenderType.getCutout());
    }
}

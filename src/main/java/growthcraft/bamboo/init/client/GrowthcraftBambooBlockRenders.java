package growthcraft.bamboo.init.client;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftBambooBlockRenders {

    private GrowthcraftBambooBlockRenders() { /* Disable default public constructor */ }

    public static void setRenderLayer() {
        RenderTypeLookup.setRenderLayer(GrowthcraftBambooBlocks.bambooPlankDoor.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftBambooBlocks.bambooTreeSapling.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftBambooBlocks.bambooFishtrap.get(), RenderType.getCutout());
    }

}

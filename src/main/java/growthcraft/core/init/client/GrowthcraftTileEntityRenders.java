package growthcraft.core.init.client;

import growthcraft.core.client.ter.RopeTileEntityRenderer;
import growthcraft.core.init.GrowthcraftTileEntities;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class GrowthcraftTileEntityRenders {

    public static void bindTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(GrowthcraftTileEntities.rope_tileentity.get(),
                RopeTileEntityRenderer::new);
    }

    private GrowthcraftTileEntityRenders() {
        /* Prevent default public constructor */
    }
}

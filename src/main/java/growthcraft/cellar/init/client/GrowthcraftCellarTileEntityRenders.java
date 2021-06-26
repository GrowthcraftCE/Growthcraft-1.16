package growthcraft.cellar.init.client;

import growthcraft.cellar.client.ter.BrewKettleTileEntityRenderer;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class GrowthcraftCellarTileEntityRenders {

    public static void bindTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(GrowthcraftCellarTileEntities.brew_kettle_tileentity.get(), BrewKettleTileEntityRenderer::new);
    }

    private GrowthcraftCellarTileEntityRenders() {
        /* Prevent default public constructor */
    }
}

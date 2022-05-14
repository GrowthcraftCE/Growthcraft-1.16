package growthcraft.milk.init.client;

import growthcraft.milk.client.ter.MixingVatTileEntityRender;
import growthcraft.milk.client.ter.PancheonTileEntityRender;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class GrowthcraftMilkTileEntityRenders {
    public static void bindTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(GrowthcraftMilkTileEntities.MIXING_VAT_TILE_ENTITY.get(),
                MixingVatTileEntityRender::new);
        ClientRegistry.bindTileEntityRenderer(GrowthcraftMilkTileEntities.PANCHEON_TILE_ENTITY.get(), PancheonTileEntityRender::new);
    }

    private GrowthcraftMilkTileEntityRenders() {
        /* Prevent default public constructor */
    }
}

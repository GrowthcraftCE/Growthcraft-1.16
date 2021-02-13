package growthcraft.trapper.init.client;

import growthcraft.trapper.init.GrowthcraftTrapperContainers;
import growthcraft.trapper.lib.client.gui.ScreenFishtrap;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrowthcraftTrapperScreenManager {
    private GrowthcraftTrapperScreenManager() { /* Disable Default Public Constructor */ }

    public static void registerFactories() {
        ScreenManager.registerFactory(GrowthcraftTrapperContainers.fishtrapContainer.get(), ScreenFishtrap::new);
    }
}

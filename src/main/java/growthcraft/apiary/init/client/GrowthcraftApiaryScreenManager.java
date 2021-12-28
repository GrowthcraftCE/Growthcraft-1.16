package growthcraft.apiary.init.client;

import growthcraft.apiary.client.gui.BeeBoxScreen;
import growthcraft.apiary.init.GrowthcraftApiaryContainers;
import net.minecraft.client.gui.ScreenManager;

public class GrowthcraftApiaryScreenManager {

    private GrowthcraftApiaryScreenManager() {
        /* Disable default public constructor */
    }

    public static void registerFactories() {
        ScreenManager.registerFactory(
                GrowthcraftApiaryContainers.BEE_BOX_CONTAINER.get(),
                BeeBoxScreen::new);
    }
}

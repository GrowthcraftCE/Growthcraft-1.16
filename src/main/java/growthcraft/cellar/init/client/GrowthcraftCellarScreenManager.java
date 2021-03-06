package growthcraft.cellar.init.client;

import growthcraft.cellar.client.gui.BrewKettleScreen;
import growthcraft.cellar.init.GrowthcraftCellarContainers;
import net.minecraft.client.gui.ScreenManager;

public class GrowthcraftCellarScreenManager {

    private GrowthcraftCellarScreenManager() {
        /* Disable default public constructor */
    }

    public static void registerFactories() {
        ScreenManager.registerFactory(GrowthcraftCellarContainers.brew_kettle_container.get(), BrewKettleScreen::new);
    }
}
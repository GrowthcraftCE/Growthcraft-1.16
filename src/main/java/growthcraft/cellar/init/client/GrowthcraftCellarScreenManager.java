package growthcraft.cellar.init.client;

import growthcraft.cellar.client.gui.BrewKettleScreen;
import growthcraft.cellar.client.gui.CultureJarScreen;
import growthcraft.cellar.client.gui.FermentBarrelScreen;
import growthcraft.cellar.client.gui.RoasterScreen;
import growthcraft.cellar.init.GrowthcraftCellarContainers;
import net.minecraft.client.gui.ScreenManager;

public class GrowthcraftCellarScreenManager {

    private GrowthcraftCellarScreenManager() {
        /* Disable default public constructor */
    }

    public static void registerFactories() {
        ScreenManager.registerFactory(
                GrowthcraftCellarContainers.brew_kettle_container.get(), BrewKettleScreen::new);
        ScreenManager.registerFactory(
                GrowthcraftCellarContainers.culture_jar_container.get(), CultureJarScreen::new);
        ScreenManager.registerFactory(
                GrowthcraftCellarContainers.ferment_barrel_container.get(), FermentBarrelScreen::new);
        ScreenManager.registerFactory(
                GrowthcraftCellarContainers.roaster_container.get(), RoasterScreen::new);
    }
}
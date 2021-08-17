package growthcraft.cellar.init.client;

import growthcraft.cellar.client.gui.*;
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
        ScreenManager.registerFactory(
                GrowthcraftCellarContainers.fruit_press_container.get(), FruitPressScreen::new);
    }
}
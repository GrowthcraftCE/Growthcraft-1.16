package growthcraft.milk.init.client;

import growthcraft.milk.client.gui.ChurnScreen;
import growthcraft.milk.client.gui.MixingVatScreen;
import growthcraft.milk.client.gui.PancheonScreen;
import growthcraft.milk.init.GrowthcraftMilkContainers;
import net.minecraft.client.gui.ScreenManager;

public class GrowthcraftMilkScreenManager {

    private GrowthcraftMilkScreenManager() {
        /* Disable default public constructor */
    }

    public static void registerFactories() {
        ScreenManager.registerFactory(
                GrowthcraftMilkContainers.CHURN_CONTAINER.get(),
                ChurnScreen::new
        );
        ScreenManager.registerFactory(
                GrowthcraftMilkContainers.MIXING_VAT_CONTAINER.get(),
                MixingVatScreen::new
        );
        ScreenManager.registerFactory(
                GrowthcraftMilkContainers.PANCHEON_CONTAINTER.get(),
                PancheonScreen::new
        );
    }
}
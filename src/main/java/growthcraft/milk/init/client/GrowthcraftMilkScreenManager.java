package growthcraft.milk.init.client;

import growthcraft.milk.client.gui.ChurnScreen;
import growthcraft.milk.init.GrowthcraftMilkContainers;
import net.minecraft.client.gui.ScreenManager;

public class GrowthcraftMilkScreenManager {

    private GrowthcraftMilkScreenManager() {
        /* Disable default public constructor */
    }

    public static void registerFactories() {
        ScreenManager.registerFactory(
                GrowthcraftMilkContainers.CHURN_CONTAINER.get(), ChurnScreen::new);
    }
}
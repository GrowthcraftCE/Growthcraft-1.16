package growthcraft.cellar.client.event;

import growthcraft.cellar.common.item.GrainItem;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ColorRegistryEvent {

    private ColorRegistryEvent() {
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerItemColors(ColorHandlerEvent.Item event) {

        List<GrainItem> grainItems = new ArrayList<>();
        grainItems.add(GrowthcraftCellarItems.grain_amber.get());
        grainItems.add(GrowthcraftCellarItems.grain_brown.get());
        grainItems.add(GrowthcraftCellarItems.grain_copper.get());
        grainItems.add(GrowthcraftCellarItems.grain_dark.get());
        grainItems.add(GrowthcraftCellarItems.grain_golden.get());
        grainItems.add(GrowthcraftCellarItems.grain_deep_amber.get());
        grainItems.add(GrowthcraftCellarItems.grain_deep_copper.get());
        grainItems.add(GrowthcraftCellarItems.grain_pale_golden.get());

        for (GrainItem item : grainItems) {
            event.getItemColors().register(
                    (itemStack, i) -> item.getColor(),
                    item
            );
        }

        event.getItemColors().register(
                (itemStack, i) -> GrowthcraftCellarItems.ALE_POTION.get().getColor(),
                GrowthcraftCellarItems.ALE_POTION.get()
        );

    }
}

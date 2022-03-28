package growthcraft.rice.client.event;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.rice.init.GrowthcraftRiceItems;
import growthcraft.rice.shared.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColorRegistryEvent {

    private ColorRegistryEvent() {
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        // Register Block Colorizers
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        List<GrowthcraftBucketItem> buckets = new ArrayList<>();
        buckets.add(GrowthcraftRiceItems.BUCKET_RICE_WATER.get());
        buckets.add(GrowthcraftRiceItems.BUCKET_RICE_WINE.get());
        buckets.add(GrowthcraftRiceItems.BUCKET_SAKE.get());

        buckets.forEach(bucket -> event.getItemColors().register((itemStack, i) -> bucket.getColor(i), bucket));
    }
}

package growthcraft.milk.client.event;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.milk.init.GrowthcraftMilkItems;
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
        buckets.add(GrowthcraftMilkItems.BUCKET_BUTTER_MILK.get());
        buckets.add(GrowthcraftMilkItems.BUCKET_MILK.get());
        buckets.add(GrowthcraftMilkItems.BUCKET_CREAM.get());
        buckets.add(GrowthcraftMilkItems.BUCKET_CULTURED_MILK.get());
        buckets.add(GrowthcraftMilkItems.BUCKET_WHEY.get());
        buckets.add(GrowthcraftMilkItems.BUCKET_KUMIS.get());
        buckets.add(GrowthcraftMilkItems.BUCKET_CONDENSED_MILK.get());
        buckets.add(GrowthcraftMilkItems.BUCKET_RENNET.get());

        buckets.forEach(bucket -> event.getItemColors().register((itemStack, i) -> bucket.getColor(i), bucket));
    }
}

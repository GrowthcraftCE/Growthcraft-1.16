package growthcraft.apiary.client.event;

import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.apiary.shared.Reference;
import growthcraft.lib.common.item.GrowthcraftBucketItem;
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
    public static void registerItemColors(ColorHandlerEvent.Item event) {

        List<GrowthcraftBucketItem> buckets = new ArrayList<>();
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_BLACK.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_BLUE.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_BROWN.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_CYAN.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_GRAY.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_GREEN.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_LIGHT_BLUE.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_LIGHT_GRAY.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_LIME.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_MAGENTA.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_ORANGE.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_PINK.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_PURPLE.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_RED.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_WHITE.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_WAX_YELLOW.get());

        buckets.forEach(bucket -> event.getItemColors().register((itemStack, i) -> bucket.getColor(i), bucket));

    }
}

package growthcraft.apiary.client.event;

import growthcraft.apiary.common.CandleItem;
import growthcraft.apiary.common.block.CandleBlock;
import growthcraft.apiary.common.block.CandleWallBlock;
import growthcraft.apiary.init.GrowthcraftApiaryBlocks;
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
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        List<CandleBlock> candleBlocks = new ArrayList<>();
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_BLACK.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_BLUE.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_BROWN.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_CYAN.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_GRAY.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_GREEN.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_LIGHT_BLUE.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_LIGHT_GRAY.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_LIME.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_MAGENTA.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_ORANGE.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_PINK.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_PURPLE.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_RED.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_WHITE.get());
        candleBlocks.add(GrowthcraftApiaryBlocks.CANDLE_YELLOW.get());

        candleBlocks.forEach(block -> event.getBlockColors().register((blockstate, reader, pos, i) -> block.getColor(i), block));

        List<CandleWallBlock> candleWallBlocks = new ArrayList<>();
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_BLACK_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_BLUE_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_BROWN_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_CYAN_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_GRAY_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_GREEN_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_LIGHT_BLUE_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_LIGHT_GRAY_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_LIME_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_MAGENTA_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_ORANGE_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_PINK_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_PURPLE_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_RED_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_WHITE_WALL.get());
        candleWallBlocks.add(GrowthcraftApiaryBlocks.CANDLE_YELLOW_WALL.get());

        candleWallBlocks.forEach(block -> event.getBlockColors().register((blockstate, reader, pos, i) -> block.getColor(i), block));

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
        buckets.add(GrowthcraftApiaryItems.BUCKET_HONEY.get());
        buckets.add(GrowthcraftApiaryItems.BUCKET_HONEY_MEAD.get());

        buckets.forEach(bucket -> event.getItemColors().register((itemStack, i) -> bucket.getColor(i), bucket));

        List<CandleItem> candleItems = new ArrayList<>();
        candleItems.add(GrowthcraftApiaryItems.CANDLE_BLACK.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_BLUE.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_BROWN.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_CYAN.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_GRAY.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_GREEN.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_LIGHT_BLUE.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_LIGHT_GRAY.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_LIME.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_MAGENTA.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_ORANGE.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_PINK.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_PURPLE.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_RED.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_WHITE.get());
        candleItems.add(GrowthcraftApiaryItems.CANDLE_YELLOW.get());

        candleItems.forEach(candle -> event.getItemColors().register((itemStack, i) -> candle.getColor(i), candle));

    }
}

package growthcraft.milk.client.event;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkItems;
import growthcraft.milk.lib.common.block.CheeseCurdBlock;
import growthcraft.milk.lib.common.block.CheeseWheelBlock;
import growthcraft.milk.lib.common.item.CheeseCurdsDrainedItem;
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
        List<CheeseCurdBlock> cheeseCurdBlocks = new ArrayList<>();
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.APPENZELLER_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.ASIAGO_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.CHEDDAR_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.GORGONZOLA_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.GOUDA_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.CASU_MARZU_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.MONTEREY_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.PARMESAN_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.PROVOLONE_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.RICOTTA_CHEESE_CURDS.get());

        cheeseCurdBlocks.forEach(block -> event.getBlockColors().register((blockstate, reader, pos, i) -> block.getColor(i), block));

        List<CheeseWheelBlock> cheeseWheelBlocks = new ArrayList<>();

        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.APPENZELLER_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.ASIAGO_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.CHEDDAR_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.GORGONZOLA_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.GOUDA_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.CASU_MARZU_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.MONTEREY_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.PARMESAN_CHEESE.get());
        cheeseWheelBlocks.add(GrowthcraftMilkBlocks.PROVOLONE_CHEESE.get());

        cheeseWheelBlocks.forEach(block -> event.getBlockColors().register((blockstate, reader, pos, i) -> block.getColor(i), block));

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
        buckets.add(GrowthcraftMilkItems.BUCKET_CHEESE_BASE.get());

        buckets.forEach(bucket -> event.getItemColors().register((itemStack, i) -> bucket.getColor(i), bucket));

        List<CheeseCurdsDrainedItem> drainedCurds = new ArrayList<>();
        drainedCurds.add(GrowthcraftMilkItems.APPENZELLER_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.ASIAGO_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.EMMENTALER_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.GORGONZOLA_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.PARMESAN_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.PROVOLONE_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.CASU_MARZU_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.RICOTTA_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.CHEDDAR_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.MONTEREY_CHEESE_CURDS_DRAINED.get());
        drainedCurds.add(GrowthcraftMilkItems.GOUDA_CHEESE_CURDS_DRAINED.get());

        drainedCurds.forEach(drainedCurd -> event.getItemColors().register((itemStack, i) -> drainedCurd.getColor(i), drainedCurd));

        List<CheeseCurdBlock> cheeseCurdBlocks = new ArrayList<>();
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.APPENZELLER_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.ASIAGO_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.CHEDDAR_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.GORGONZOLA_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.GOUDA_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.CASU_MARZU_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.MONTEREY_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.PARMESAN_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.PROVOLONE_CHEESE_CURDS.get());
        cheeseCurdBlocks.add(GrowthcraftMilkBlocks.RICOTTA_CHEESE_CURDS.get());

        cheeseCurdBlocks.forEach(block -> event.getItemColors().register((itemStack, i) -> block.getColor(i), block.asItem()));

        List<CheeseWheelBlock> cheeseBlocks = new ArrayList<>();
        cheeseBlocks.add(GrowthcraftMilkBlocks.APPENZELLER_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.ASIAGO_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.CHEDDAR_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.GORGONZOLA_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.GOUDA_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.CASU_MARZU_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.EMMENTALER_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.MONTEREY_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.PARMESAN_CHEESE.get());
        cheeseBlocks.add(GrowthcraftMilkBlocks.PROVOLONE_CHEESE.get());

        cheeseBlocks.forEach(block -> event.getItemColors().register((itemStack, i) -> block.getColor(i), block.asItem()));

    }
}

package growthcraft.apples;

import growthcraft.apples.client.proxy.ClientProxy;
import growthcraft.apples.common.proxy.CommonProxy;
import growthcraft.apples.init.GrowthcraftApplesBiomes;
import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.apples.init.GrowthcraftApplesItems;
import growthcraft.apples.init.GrowthcraftApplesTileEntities;
import growthcraft.apples.init.client.GrowthcraftApplesBlockRenders;
import growthcraft.apples.init.config.GrowthcraftApplesConfig;
import growthcraft.core.Growthcraft;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GrowthcraftApples {

    public static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftApples() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GrowthcraftApplesItems.ITEMS.register(modEventBus);
        GrowthcraftApplesBlocks.BLOCKS.register(modEventBus);
        GrowthcraftApplesTileEntities.TILE_ENTITIES.register(modEventBus);
        GrowthcraftApplesBiomes.BIOMES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        GrowthcraftApplesBiomes.registeryBiomes();
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(Growthcraft.itemGroup);
        // Block Items cannot be deferred.
        GrowthcraftApplesBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
        GrowthcraftApplesConfig.loadConfig();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftApplesBlockRenders.setRenderLayer();
    }
}

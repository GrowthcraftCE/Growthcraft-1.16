package growthcraft.bamboo;

import growthcraft.bamboo.client.proxy.ClientProxy;
import growthcraft.bamboo.common.proxy.CommonProxy;
import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.bamboo.init.GrowthcraftBambooItems;
import growthcraft.bamboo.init.GrowthcraftBambooTileEntities;
import growthcraft.bamboo.init.client.GrowthcraftBambooBlockRenders;
import growthcraft.bamboo.init.config.GrowthcraftBambooConfig;
import growthcraft.bamboo.shared.Reference;
import growthcraft.core.Growthcraft;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftBamboo {

    public static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftBamboo() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Mod event bus context for deferred registries
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Add DeferredRegister<Item> to the mod event bus.
        GrowthcraftBambooItems.ITEMS.register(modEventBus);

        // Add DeferredRegister<Block> to the mod event bus.
        GrowthcraftBambooBlocks.BLOCKS.register(modEventBus);
        GrowthcraftBambooTileEntities.TILE_ENTITIES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Subscribe to the RegistryEvent.Register<Item> for manually registering BlockItems.
     * Items should be added to the DeferredRegister<Item> which is in the constructor.
     *
     * @param event Item registration event.
     */
    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(Growthcraft.itemGroup);
        // Block Items cannot be deferred.
        GrowthcraftBambooBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();

        // Load GrowthcraftApplesConfig.loadConfig()
        GrowthcraftBambooConfig.loadConfig();
    }

    /**
     * Do something that can only be done on the client like transparent blocks.
     *
     * @param event FMLClientSetupEvent
     */
    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftBambooBlockRenders.setRenderLayer();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo(Reference.MODID, "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}

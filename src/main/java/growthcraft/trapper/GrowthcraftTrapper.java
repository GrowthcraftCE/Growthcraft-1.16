package growthcraft.trapper;

import growthcraft.core.Growthcraft;
import growthcraft.lib.proxy.IProxy;
import growthcraft.trapper.client.proxy.ClientProxy;
import growthcraft.trapper.common.proxy.CommonProxy;
import growthcraft.trapper.init.GrowthcraftTrapperBlocks;
import growthcraft.trapper.init.GrowthcraftTrapperContainers;
import growthcraft.trapper.init.GrowthcraftTrapperTileEntities;
import growthcraft.trapper.init.client.GrowthcraftTrapperBlockRenders;
import growthcraft.trapper.init.client.GrowthcraftTrapperScreenManager;
import growthcraft.trapper.init.config.GrowthcraftTrapperConfig;
import growthcraft.trapper.shared.Reference;
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
public class GrowthcraftTrapper {

    public static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftTrapper() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GrowthcraftTrapperBlocks.BLOCKS.register(modEventBus);
        GrowthcraftTrapperTileEntities.TILE_ENTITIES.register(modEventBus);
        GrowthcraftTrapperContainers.CONTAINERS.register(modEventBus);

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
        GrowthcraftTrapperBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
        GrowthcraftTrapperConfig.loadConfig();
    }

    /**
     * Do something that can only be done on the client like transparent blocks.
     *
     * @param event FMLClientSetupEvent
     */
    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftTrapperBlockRenders.setRenderLayers();
        GrowthcraftTrapperScreenManager.registerFactories();
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

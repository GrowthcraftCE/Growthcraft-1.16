package growthcraft.apples;

import growthcraft.apples.client.proxy.ClientProxy;
import growthcraft.apples.common.proxy.CommonProxy;
import growthcraft.apples.init.GrowthcraftApplesBiomes;
import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.apples.init.GrowthcraftApplesItems;
import growthcraft.apples.init.GrowthcraftApplesTileEntities;
import growthcraft.apples.init.client.GrowthcraftApplesBlockRenders;
import growthcraft.apples.init.config.GrowthcraftApplesConfig;
import growthcraft.apples.shared.Reference;
import growthcraft.core.Growthcraft;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
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
public class GrowthcraftApples {

    public static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftApples() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Mod event bus context for deferred registries
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Add Deferred Registries
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
        GrowthcraftApplesBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();

        // Load GrowthcraftApplesConfig.loadConfig()
        GrowthcraftApplesConfig.loadConfig();
    }

    /**
     * Do something that can only be done on the client like transparent blocks.
     *
     * @param event FMLClientSetupEvent
     */
    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftApplesBlockRenders.setRenderLayer();
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

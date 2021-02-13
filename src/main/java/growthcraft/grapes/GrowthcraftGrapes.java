package growthcraft.grapes;

import growthcraft.core.Growthcraft;
import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.grapes.client.proxy.ClientProxy;
import growthcraft.grapes.common.proxy.CommonProxy;
import growthcraft.grapes.init.GrowthcraftGrapesBlocks;
import growthcraft.grapes.init.GrowthcraftGrapesItems;
import growthcraft.grapes.init.client.GrowthcraftGrapesBlockRenders;
import growthcraft.grapes.init.config.GrowthcraftGrapesConfig;
import growthcraft.grapes.shared.Reference;
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
public class GrowthcraftGrapes {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftGrapes() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        GrowthcraftGrapesConfig.loadConfig();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GrowthcraftGrapesItems.FOODS.register(modEventBus);
        GrowthcraftGrapesItems.ITEMS.register(modEventBus);
        GrowthcraftGrapesBlocks.BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(Growthcraft.itemGroup);
        GrowthcraftBlocks.registerBlockItems(itemRegistry, properties);

        // Setup the fruit yields for Grape Vines. We have to put it here because BLOCKS get registered before Items
        // in the deferred registry.
        GrowthcraftGrapesBlocks.setupVineFruitItems();
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
        GrowthcraftGrapesConfig.loadConfig();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftGrapesBlockRenders.setRenderLayers();
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

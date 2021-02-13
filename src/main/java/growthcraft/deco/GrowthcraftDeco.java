package growthcraft.deco;

import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.deco.client.proxy.ClientProxy;
import growthcraft.deco.common.proxy.CommonProxy;
import growthcraft.deco.init.GrowthcraftDecoBlocks;
import growthcraft.deco.init.config.GrowthcraftDecoConfig;
import growthcraft.deco.shared.Reference;
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

import static growthcraft.core.Growthcraft.itemGroup;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftDeco {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftDeco() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        GrowthcraftDecoConfig.loadConfig();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        if (GrowthcraftConfig.growthcraftDecoEnabled()) {
            // Add DeferredRegister<Item> to the mod event bus.

            // Add DeferredRegister<Block> to the mod event bus.
            GrowthcraftDecoBlocks.BLOCKS.register(modEventBus);
        } else {
            GrowthcraftDeco.LOGGER.info("Growthcraft Decorations is not enabled, skipping mod loading context event.");
        }

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
        final Item.Properties properties = new Item.Properties().group(itemGroup);
        if (GrowthcraftConfig.growthcraftDecoEnabled()) {
            GrowthcraftDecoBlocks.registerBlockItems(itemRegistry, properties);
        } else {
            GrowthcraftDeco.LOGGER.info("Growthcraft Decorations is not enabled, skipping item registry event.");
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
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

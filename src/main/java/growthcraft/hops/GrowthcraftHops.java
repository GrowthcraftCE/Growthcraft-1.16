package growthcraft.hops;

import growthcraft.hops.client.proxy.ClientProxy;
import growthcraft.hops.common.proxy.CommonProxy;
import growthcraft.hops.init.GrowthcraftHopsItems;
import growthcraft.hops.shared.Reference;
import growthcraft.lib.proxy.IProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftHops {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftHops() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GrowthcraftHopsItems.ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Load renders
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts

    }
}

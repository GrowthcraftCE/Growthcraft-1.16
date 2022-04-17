package growthcraft.rice;

import growthcraft.rice.init.GrowthcraftRiceBlocks;
import growthcraft.rice.init.GrowthcraftRiceFluids;
import growthcraft.rice.init.GrowthcraftRiceItems;
import growthcraft.rice.init.client.GrowthcraftRiceBlockRenders;
import growthcraft.rice.init.config.GrowthcraftRiceConfig;
import growthcraft.rice.shared.Reference;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftRice {

    public static final Logger LOGGER = LogManager.getLogger();
    //public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftRice() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GrowthcraftRiceBlocks.BLOCKS.register(modEventBus);
        GrowthcraftRiceItems.ITEMS.register(modEventBus);
        GrowthcraftRiceFluids.FLUIDS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        // OPTIONAL: Register Biomes
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab);

        GrowthcraftRiceBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        //proxy.init();
        GrowthcraftRiceConfig.loadConfig();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Set Block Render Layers
        GrowthcraftRiceBlockRenders.setRenderLayers();
        // Register Screen Manager Factories
    }
}

package growthcraft.apiary;

import growthcraft.apiary.client.proxy.ClientProxy;
import growthcraft.apiary.common.proxy.CommonProxy;
import growthcraft.apiary.init.*;
import growthcraft.apiary.init.client.GrowthcraftApiaryBlockRenders;
import growthcraft.apiary.init.client.GrowthcraftApiaryScreenManager;
import growthcraft.apiary.init.config.GrowthcraftApiaryConfig;
import growthcraft.apiary.shared.Reference;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftApiary {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftApiary() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GrowthcraftApiaryBlocks.BLOCKS.register(modEventBus);
        GrowthcraftApiaryItems.ITEMS.register(modEventBus);
        GrowthcraftApiaryFluids.FLUIDS.register(modEventBus);
        GrowthcraftApiaryLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        GrowthcraftApiaryTileEntities.TILE_ENTITIES.register(modEventBus);
        GrowthcraftApiaryContainers.CONTAINERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab);
        // Block Items cannot be deferred.
        GrowthcraftApiaryBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
        GrowthcraftApiaryConfig.loadConfig();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftApiaryBlockRenders.setRenderLayers();
        GrowthcraftApiaryScreenManager.registerFactories();
    }
}

package growthcraft.cellar;

import growthcraft.cellar.client.event.ColorRegistryEvent;
import growthcraft.cellar.client.proxy.ClientProxy;
import growthcraft.cellar.common.proxy.CommonProxy;
import growthcraft.cellar.init.*;
import growthcraft.cellar.init.client.GrowthcraftCellarBlockRenders;
import growthcraft.cellar.init.client.GrowthcraftCellarScreenManager;
import growthcraft.cellar.init.client.GrowthcraftCellarTileEntityRenders;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.cellar.shared.Reference;
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
public class GrowthcraftCellar {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftCellar() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().register(ColorRegistryEvent.class);

        GrowthcraftCellarConfig.loadConfig();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Add DeferredRegister<Item> to the mod event bus.
        GrowthcraftCellarBlocks.BLOCKS.register(modEventBus);
        GrowthcraftCellarItems.ITEMS.register(modEventBus);
        GrowthcraftCellarFluids.FLUIDS.register(modEventBus);
        GrowthcraftCellarLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        GrowthcraftCellarTileEntities.TILE_ENTITIES.register(modEventBus);
        GrowthcraftCellarContainers.CONTAINERS.register(modEventBus);
        GrowthcraftCellarRecipes.RECIPE_SERIALIZERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab);

        GrowthcraftCellarBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftCellarBlockRenders.setRenderLayers();
        GrowthcraftCellarTileEntityRenders.bindTileEntityRenderers();
        GrowthcraftCellarScreenManager.registerFactories();
    }

}

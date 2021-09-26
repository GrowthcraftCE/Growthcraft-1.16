package growthcraft.cellar;

import growthcraft.cellar.client.proxy.ClientProxy;
import growthcraft.cellar.common.item.GrainItem;
import growthcraft.cellar.common.proxy.CommonProxy;
import growthcraft.cellar.init.*;
import growthcraft.cellar.init.client.GrowthcraftCellarBlockRenders;
import growthcraft.cellar.init.client.GrowthcraftCellarScreenManager;
import growthcraft.cellar.init.client.GrowthcraftCellarTileEntityRenders;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftCellar {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftCellar() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerItemColors);

        GrowthcraftCellarConfig.loadConfig();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Add DeferredRegister<Item> to the mod event bus.
        GrowthcraftCellarItems.ITEMS.register(modEventBus);
        GrowthcraftCellarFluids.FLUIDS.register(modEventBus);
        GrowthcraftCellarBlocks.BLOCKS.register(modEventBus);
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

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void registerItemColors(ColorHandlerEvent.Item event) {
        List<GrainItem> grainItems = new ArrayList<>();
        grainItems.add(GrowthcraftCellarItems.grain_amber.get());
        grainItems.add(GrowthcraftCellarItems.grain_brown.get());
        grainItems.add(GrowthcraftCellarItems.grain_copper.get());
        grainItems.add(GrowthcraftCellarItems.grain_dark.get());
        grainItems.add(GrowthcraftCellarItems.grain_golden.get());
        grainItems.add(GrowthcraftCellarItems.grain_deep_amber.get());
        grainItems.add(GrowthcraftCellarItems.grain_deep_copper.get());
        grainItems.add(GrowthcraftCellarItems.grain_pale_golden.get());

        for (GrainItem item : grainItems) {
            event.getItemColors().register(
                    (itemStack, i) -> item.getColor(),
                    item
            );
        }

        event.getItemColors().register(
                (itemStack, i) -> GrowthcraftCellarItems.ALE_POTION.get().getColor(),
                GrowthcraftCellarItems.ALE_POTION.get()
        );

        List<GrowthcraftBucketItem> bucketItems = new ArrayList<>();
        bucketItems.add(GrowthcraftCellarItems.bucket_purple_grape_juice.get());

        for (GrowthcraftBucketItem item : bucketItems) {
            event.getItemColors().register(
                    (itemStack, i) -> item.getColor(),
                    item
            );
        }

    }
}

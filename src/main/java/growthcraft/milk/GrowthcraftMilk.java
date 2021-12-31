package growthcraft.milk;

import growthcraft.cellar.client.event.ColorRegistryEvent;
import growthcraft.cellar.client.proxy.ClientProxy;
import growthcraft.cellar.common.proxy.CommonProxy;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.lib.proxy.IProxy;
import growthcraft.milk.init.*;
import growthcraft.milk.init.client.GrowthcraftMilkBlockRenders;
import growthcraft.milk.shared.Reference;
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
public class GrowthcraftMilk {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftMilk() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().register(ColorRegistryEvent.class);

        GrowthcraftCellarConfig.loadConfig();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Add DeferredRegister<Item> to the mod event bus.
        GrowthcraftMilkBlocks.BLOCKS.register(modEventBus);
        GrowthcraftMilkItems.ITEMS.register(modEventBus);
        GrowthcraftMilkFluids.FLUIDS.register(modEventBus);
        GrowthcraftMilkTileEntities.TILE_ENTITIES.register(modEventBus);
        GrowthcraftMilkContainers.CONTAINERS.register(modEventBus);
        GrowthcraftMilkRecipes.RECIPE_SERIALIZERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab);

        GrowthcraftMilkBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        GrowthcraftMilkBlockRenders.setRenderLayers();
        GrowthcraftMilkTileEntityRenders.bindTileEntityRenderers();
        GrowthcraftMilkScreenManager.registerFactories();
    }

}

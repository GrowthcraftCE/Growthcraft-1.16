package growthcraft.bamboo;

import growthcraft.bamboo.client.proxy.ClientProxy;
import growthcraft.bamboo.common.proxy.CommonProxy;
import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.bamboo.init.client.GrowthcraftBambooBlockRenders;
import growthcraft.bamboo.shared.Reference;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
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
public class GrowthcraftBamboo {

    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
    public static final IProxy proxy = DistExecutor.safeRunForDist(
            () -> ClientProxy::new, () -> CommonProxy::new);

    public GrowthcraftBamboo() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // TODO: Register Items and Blocks
        // GrowthcraftBambooItems.ITEMS.register(modEventBus);
        GrowthcraftBambooBlocks.BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        // TODO: Add Bamboo Biome
        //GrowthcraftApplesBiomes.registeryBiomes();
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab);
        // Register Growthcraft Bamboo Block Items
        GrowthcraftBambooBlocks.registerBlockItems(itemRegistry, properties);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
        // TODO: Growthcraft Bamboo Load Configuration.
        //GrowthcraftBambooConfig.loadConfig();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Register Block Renders
        GrowthcraftBambooBlockRenders.setRenderLayers();
    }

}

package growthcraft.core;

import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.GrowthcraftItems;
import growthcraft.core.init.GrowthcraftTileEntities;
import growthcraft.core.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Growthcraft {

    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public Growthcraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // ITEMS, FLUIDS, BLOCKS, LOOT_MODIFIER_SERIALIZERS
        // TILE_ENTITIES, CONTAINERS, RECIPE_SERIALIZERS

        GrowthcraftBlocks.BLOCKS.register(modEventBus);
        GrowthcraftItems.ITEMS.register(modEventBus);
        GrowthcraftTileEntities.TILE_ENTITIES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(Reference.growthcraftCreativeTab);

        GrowthcraftBlocks.registerBlockItems(itemRegistry, properties);
    }

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        LOGGER.info("Growthcraft-Core block registration ...");
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Growthcraft Core setting up ... ");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("Server start-up ...");
    }

}

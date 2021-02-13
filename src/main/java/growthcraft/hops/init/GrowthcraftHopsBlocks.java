package growthcraft.hops.init;

import growthcraft.core.Growthcraft;
import growthcraft.hops.common.block.BlockHopsCropsRopeLinen;
import growthcraft.hops.shared.Reference;
import growthcraft.hops.shared.UnlocalizedName;
import growthcraft.lib.common.block.GrowthcraftCropsRopeBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class GrowthcraftHopsBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<GrowthcraftCropsRopeBlock> hopsBush;

    static {
        hopsBush = BLOCKS.register(
                UnlocalizedName.HOPS_VINE,
                () -> new BlockHopsCropsRopeLinen());
    }

    private GrowthcraftHopsBlocks() { /* Prevent default public constructor */ }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Hops Registering itemBlocks ...");

        BLOCKS.getEntries().stream()
                .map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(hopsBush.get().getRegistryName().toString());
        return excludeBlocks.contains(registryName.toString());
    }

}

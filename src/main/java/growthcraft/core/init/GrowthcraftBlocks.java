package growthcraft.core.init;

import growthcraft.core.Growthcraft;
import growthcraft.core.common.block.RopeBlock;
import growthcraft.core.shared.Reference;
import growthcraft.lib.common.block.GrowthcraftBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class GrowthcraftBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    private GrowthcraftBlocks() { /* Prevent default public constructor */ }

    public static final RegistryObject<RopeBlock> ROPE_LINEN = BLOCKS.register(
            Reference.ROPE_LINEN,
            RopeBlock::new
    );

    public static final RegistryObject<GrowthcraftBlock> SALT_BLOCK = BLOCKS.register(
            Reference.SALT_BLOCK, () -> new GrowthcraftBlock(Material.ROCK, SoundType.STONE)
    );

    public static final RegistryObject<GrowthcraftBlock> SALT_ORE = BLOCKS.register(
            Reference.SALT_ORE, () -> new GrowthcraftBlock(AbstractBlock.Properties.from(Blocks.IRON_ORE))
    );

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("<Growthcraft-Core> Registration of itemBlocks started ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        Growthcraft.LOGGER.debug("<Growthcraft-Core> Registration of itemBlocks finished.");
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(Reference.MODID + ":" + Reference.ROPE_LINEN);
        return excludeBlocks.contains(registryName.toString());
    }

}

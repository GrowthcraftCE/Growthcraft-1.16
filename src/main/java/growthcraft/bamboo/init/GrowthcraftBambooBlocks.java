package growthcraft.bamboo.init;

import growthcraft.bamboo.GrowthcraftBamboo;
import growthcraft.bamboo.common.block.BambooLogBlock;
import growthcraft.bamboo.shared.Reference;
import growthcraft.bamboo.shared.UnlocalizedName;
import growthcraft.lib.common.block.GrowthcraftButtonBlock;
import growthcraft.lib.common.block.GrowthcraftPlankBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class GrowthcraftBambooBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftPlankBlock> BAMBOO_PLANK = BLOCKS.register(
            UnlocalizedName.BAMBOO_PLANK, () -> new GrowthcraftPlankBlock(Material.BAMBOO)
    );

    public static final RegistryObject<GrowthcraftButtonBlock> BAMBOO_PLANK_BUTTON = BLOCKS.register(
            UnlocalizedName.BAMBOO_PLANK_BUTTON, GrowthcraftButtonBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_STAIRS = BLOCKS.register(
            UnlocalizedName.BAMBOO_PLANK_STAIRS,
            () -> new StairsBlock(
                    () -> GrowthcraftBambooBlocks.BAMBOO_PLANK.get().getDefaultState(),
                    AbstractBlock.Properties.from(GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
            )
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD = BLOCKS.register(
            UnlocalizedName.BAMBOO_WOOD, BambooLogBlock::new
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD_LOG = BLOCKS.register(
            UnlocalizedName.BAMBOO_WOOD_LOG, BambooLogBlock::new
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD_LOG_STRIPPED = BLOCKS.register(
            UnlocalizedName.BAMBOO_WOOD_LOG_STRIPPED, BambooLogBlock::new
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD_STRIPPED = BLOCKS.register(
            UnlocalizedName.BAMBOO_WOOD_STRIPPED, BambooLogBlock::new
    );

    private GrowthcraftBambooBlocks() {
        /* Prevent default public constructor */
    }

    /**
     * Register Block Items.
     *
     * @param itemRegistry Delegated forge registry.
     * @param properties   Item Properties.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        GrowthcraftBamboo.LOGGER.debug(
                String.format("<%s> Registration of itemBlocks started ...", Reference.NAME)
        );

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        GrowthcraftBamboo.LOGGER.debug(
                String.format("<%s> Registration of itemBlocks completed.", Reference.NAME)
        );
    }

    /**
     * Exclude blocks from the item registry. These blocks are not to be crafted or
     * accessible through the creative screen.
     *
     * @param registryName Resource Location of the block to query against the exclude list.
     * @return boolean If block is to be excluded from the item registry.
     */
    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        // Add any blocks that need to be excluded.
        return excludeBlocks.contains(registryName.toString());
    }

}

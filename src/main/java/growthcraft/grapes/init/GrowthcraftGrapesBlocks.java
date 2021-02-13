package growthcraft.grapes.init;

import growthcraft.core.Growthcraft;
import growthcraft.grapes.common.block.BlockGrapeVine;
import growthcraft.grapes.common.block.BlockGrapeVineLeaves;
import growthcraft.grapes.common.block.BlockGrapeVinesFruit;
import growthcraft.grapes.shared.Reference;
import growthcraft.grapes.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class GrowthcraftGrapesBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<BlockGrapeVinesFruit> GRAPE_VINE_PURPLE_FRUIT
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_PURPLE_FRUIT,
            () -> new BlockGrapeVinesFruit()
    );

    public static final RegistryObject<BlockGrapeVineLeaves> GRAPE_VINE_PURPLE_LEAVES
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_PURPLE_LEAVES,
            () -> new BlockGrapeVineLeaves(GRAPE_VINE_PURPLE_FRUIT.get())
    );

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_PURPLE
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_PURPLE,
            () -> new BlockGrapeVine(GRAPE_VINE_PURPLE_LEAVES.get())
    );

    public static final RegistryObject<BlockGrapeVinesFruit> GRAPE_VINE_RED_FRUIT
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_RED_FRUIT,
            () -> new BlockGrapeVinesFruit()
    );

    public static final RegistryObject<BlockGrapeVineLeaves> GRAPE_VINE_RED_LEAVES
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_RED_LEAVES,
            () -> new BlockGrapeVineLeaves(GRAPE_VINE_RED_FRUIT.get())
    );

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_RED
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_RED,
            () -> new BlockGrapeVine(GRAPE_VINE_RED_LEAVES.get())
    );

    public static final RegistryObject<BlockGrapeVinesFruit> GRAPE_VINE_WHITE_FRUIT
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_WHITE_FRUIT,
            () -> new BlockGrapeVinesFruit()
    );

    public static final RegistryObject<BlockGrapeVineLeaves> GRAPE_VINE_WHITE_LEAVES
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_WHITE_LEAVES,
            () -> new BlockGrapeVineLeaves(GRAPE_VINE_WHITE_FRUIT.get())
    );

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_WHITE
            = BLOCKS.register(UnlocalizedName.GRAPE_VINE_WHITE,
            () -> new BlockGrapeVine(GRAPE_VINE_WHITE_LEAVES.get())
    );

    private GrowthcraftGrapesBlocks() { /* Prevent default public constructor */ }

    public static void setupVineFruitItems() {
        GRAPE_VINE_PURPLE_FRUIT.get().setupVineFruitItem(
                GrowthcraftGrapesItems.GRAPES_PURPLE.get(), 1, 2
        );
        GRAPE_VINE_RED_FRUIT.get().setupVineFruitItem(
                GrowthcraftGrapesItems.GRAPES_RED.get(), 1,2
        );
        GRAPE_VINE_WHITE_FRUIT.get().setupVineFruitItem(
                GrowthcraftGrapesItems.GRAPES_WHITE.get(), 1,2
        );
    }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Grapes Registering itemBlocks ...");

        BLOCKS.getEntries().stream()
                .map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

    }

    private static boolean excludeBlockItemRegistry(@Nonnull ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(GRAPE_VINE_PURPLE.get().getRegistryName().toString());
        excludeBlocks.add(GRAPE_VINE_PURPLE_LEAVES.get().getRegistryName().toString());
        excludeBlocks.add(GRAPE_VINE_PURPLE_FRUIT.get().getRegistryName().toString());
        return excludeBlocks.contains(registryName.toString());
    }

}

package growthcraft.apples.init;

import growthcraft.apples.GrowthcraftApples;
import growthcraft.apples.common.block.AppleTreeFruit;
import growthcraft.apples.common.block.AppleTreeLeaves;
import growthcraft.apples.common.tree.AppleTree;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.shared.UnlocalizedName;
import growthcraft.lib.common.block.*;
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

public class GrowthcraftApplesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<GrowthcraftPlankBlock> applePlank = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK, GrowthcraftPlankBlock::new
    );

    public static final RegistryObject<Block> applePlankStairs = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_STAIRS,
            () -> new StairsBlock(
                    () -> GrowthcraftApplesBlocks.applePlank.get().getDefaultState(),
                    AbstractBlock.Properties.from(GrowthcraftApplesBlocks.applePlank.get())
            )
    );

    public static final RegistryObject<GrowthcraftButtonBlock> applePlankButton = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_BUTTON, () -> new GrowthcraftButtonBlock(UnlocalizedName.APPLE_PLANK_BUTTON)
    );
    public static final RegistryObject<GrowthcraftDoorBlock> applePlankDoor = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_DOOR, () -> new GrowthcraftDoorBlock(UnlocalizedName.APPLE_PLANK_DOOR)
    );
    public static final RegistryObject<GrowthcraftFenceBlock> applePlankFence = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_FENCE, () -> new GrowthcraftFenceBlock(UnlocalizedName.APPLE_PLANK_FENCE)
    );
    public static final RegistryObject<GrowthcraftFenceGateBlock> applePlankFenceGate = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_FENCE_GATE, () -> new GrowthcraftFenceGateBlock(UnlocalizedName.APPLE_PLANK_FENCE_GATE)
    );
    public static final RegistryObject<GrowthcraftPressurePlateBlock> applePlankPressurePlate = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE, () -> new GrowthcraftPressurePlateBlock(UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE)
    );
    public static final RegistryObject<GrowthcraftSlabBlock> applePlankSlab = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_SLAB, () -> new GrowthcraftSlabBlock(UnlocalizedName.APPLE_PLANK_SLAB)
    );
    public static final RegistryObject<GrowthcraftTrapdoor> applePlankTrapdoor = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_TRAPDOOR, () -> new GrowthcraftTrapdoor(UnlocalizedName.APPLE_PLANK_TRAPDOOR, Material.WOOD)
    );
    public static final RegistryObject<AppleTreeFruit> appleTreeFruit = BLOCKS.register(
            UnlocalizedName.APPLE_TREE_FRUIT, AppleTreeFruit::new
    );
    public static final RegistryObject<AppleTreeLeaves> appleTreeLeaves = BLOCKS.register(
            UnlocalizedName.APPLE_TREE_LEAVES, AppleTreeLeaves::new
    );
    public static final RegistryObject<GrowthcraftSaplingBlock> appleTreeSapling = BLOCKS.register(
            UnlocalizedName.APPLE_TREE_SAPLING, () -> new GrowthcraftSaplingBlock(AppleTree::new)
    );
    public static final RegistryObject<GrowthcraftLogBlock> appleWood = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD, GrowthcraftLogBlock::new
    );
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLog = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD_LOG, GrowthcraftLogBlock::new
    );
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLogStripped = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD_LOG_STRIPPED, GrowthcraftLogBlock::new
    );
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodStripped = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD_STRIPPED, GrowthcraftLogBlock::new
    );

    private GrowthcraftApplesBlocks() { /* Prevent default public constructor */ }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        GrowthcraftApples.LOGGER.debug("<Growthcraft-Apples> Registration of itemBlocks started ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        GrowthcraftApples.LOGGER.debug("<Growthcraft-Apples> Registration of itemBlocks finished.");
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + growthcraft.cellar.shared.UnlocalizedName.FRUIT_PRESS_PISTON);

        return excludeBlocks.contains(registryName.toString());
    }

}

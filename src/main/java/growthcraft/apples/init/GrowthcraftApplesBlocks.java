package growthcraft.apples.init;

import growthcraft.apples.common.block.BlockAppleFishtrap;
import growthcraft.apples.common.block.BlockAppleStairs;
import growthcraft.apples.common.block.BlockAppleTreeFruit;
import growthcraft.apples.common.block.BlockAppleTreeLeaves;
import growthcraft.apples.common.tree.AppleTree;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.shared.UnlocalizedName;
import growthcraft.core.Growthcraft;
import growthcraft.lib.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    // public static Block definitions.
    public static final RegistryObject<BlockAppleTreeFruit> appleTreeFruit;
    public static final RegistryObject<GrowthcraftButtonBlock> applePlankButton;
    public static final RegistryObject<GrowthcraftPlankBlock> applePlank;
    public static final RegistryObject<GrowthcraftFenceBlock> applePlankFence;
    public static final RegistryObject<GrowthcraftFenceGateBlock> applePlankFenceGate;
    public static final RegistryObject<GrowthcraftRopeFenceBlock> applePlankFenceRopeLinen;
    public static final RegistryObject<GrowthcraftPressurePlateBlock> applePlankPressurePlate;
    public static final RegistryObject<GrowthcraftSlabBlock> applePlankSlab;
    public static final RegistryObject<BlockAppleStairs> applePlankStairs;
    public static final RegistryObject<GrowthcraftTrapdoor> applePlankTrapdoor;
    public static final RegistryObject<GrowthcraftDoorBlock> applePlankDoor;
    public static final RegistryObject<BlockAppleTreeLeaves> appleTreeLeaves;
    public static final RegistryObject<GrowthcraftSaplingBlock> appleTreeSapling;
    public static final RegistryObject<GrowthcraftLogBlock> appleWood;
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodStripped;
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLog;
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLogStripped;

    // Growthcraft Trapper
    public static final RegistryObject<BlockAppleFishtrap> appleFishtrap;

    // Static initializer for Growthcraft Apples blocks.
    static {
        appleTreeFruit = BLOCKS.register(
                UnlocalizedName.APPLE_TREE_FRUIT,
                () -> new BlockAppleTreeFruit());
        applePlankButton = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_BUTTON,
                () -> new GrowthcraftButtonBlock(UnlocalizedName.APPLE_PLANK_BUTTON));
        applePlank = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK,
                () -> new GrowthcraftPlankBlock(UnlocalizedName.APPLE_PLANK));
        applePlankDoor = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_DOOR,
                () -> new GrowthcraftDoorBlock(UnlocalizedName.APPLE_PLANK_DOOR));
        applePlankFence = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_FENCE,
                () -> new GrowthcraftFenceBlock(UnlocalizedName.APPLE_PLANK_FENCE));
        applePlankFenceGate = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_FENCE_GATE,
                () -> new GrowthcraftFenceGateBlock(UnlocalizedName.APPLE_PLANK_FENCE_GATE));
        applePlankPressurePlate = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE,
                () -> new GrowthcraftPressurePlateBlock(UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE));
        applePlankSlab = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_SLAB,
                () -> new GrowthcraftSlabBlock(UnlocalizedName.APPLE_PLANK_SLAB));
        applePlankStairs = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_STAIRS,
                () -> new BlockAppleStairs(Blocks.OAK_PLANKS, UnlocalizedName.APPLE_PLANK_STAIRS));
        applePlankTrapdoor = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_TRAPDOOR,
                () -> new GrowthcraftTrapdoor(UnlocalizedName.APPLE_PLANK_TRAPDOOR, Material.WOOD));
        appleTreeLeaves = BLOCKS.register(
                UnlocalizedName.APPLE_TREE_LEAVES,
                () -> new BlockAppleTreeLeaves());
        appleWood = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD,
                () -> new GrowthcraftLogBlock());
        appleWoodStripped = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD_STRIPPED,
                () -> new GrowthcraftLogBlock());
        appleWoodLog = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD_LOG,
                () -> new GrowthcraftLogBlock());
        appleWoodLogStripped = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD_LOG_STRIPPED,
                () -> new GrowthcraftLogBlock());
        appleTreeSapling = BLOCKS.register(
                UnlocalizedName.APPLE_TREE_SAPLING,
                () -> new GrowthcraftSaplingBlock(() -> new AppleTree()));
        appleFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_APPLE,
                () -> new BlockAppleFishtrap());
        applePlankFenceRopeLinen = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_FENCE_ROPE_LINEN,
                () -> new GrowthcraftRopeFenceBlock(applePlankFence.get()));
    }

    private GrowthcraftApplesBlocks() { /* Prevent Default Public Constructor */ }

    /**
     * Dynamically register Growthcraft Apples BlockItems.
     *
     * @param itemRegistry IForgeRegistry<Item> reference for registering items.
     * @param properties   Item properties with item group for creative tab.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Apples Registering itemBlocks ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        Growthcraft.LOGGER.debug("Growthcraft Apples itemBlocks Registered.");

    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.APPLE_TREE_FRUIT);
        excludeBlocks.add(applePlankFenceRopeLinen.get().getRegistryName().toString());
        return excludeBlocks.contains(registryName.toString());
    }
}

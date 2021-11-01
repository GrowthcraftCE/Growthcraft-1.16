package growthcraft.apples.init;

import growthcraft.apples.common.block.AppleStairsBlock;
import growthcraft.apples.common.block.AppleTreeFruit;
import growthcraft.apples.common.block.AppleTreeLeaves;
import growthcraft.apples.shared.UnlocalizedName;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<GrowthcraftPlankBlock> applePlank = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK, () -> new GrowthcraftPlankBlock(UnlocalizedName.APPLE_PLANK));
    public static final RegistryObject<GrowthcraftButtonBlock> applePlankButton = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_BUTTON, () -> new GrowthcraftButtonBlock(UnlocalizedName.APPLE_PLANK_BUTTON));
    public static final RegistryObject<GrowthcraftDoorBlock> applePlankDoor = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_DOOR, () -> new GrowthcraftDoorBlock(UnlocalizedName.APPLE_PLANK_DOOR));
    public static final RegistryObject<GrowthcraftFenceBlock> applePlankFence = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_FENCE, () -> new GrowthcraftFenceBlock(UnlocalizedName.APPLE_PLANK_FENCE));
    public static final RegistryObject<GrowthcraftFenceGateBlock> applePlankFenceGate = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_FENCE_GATE, () -> new GrowthcraftFenceGateBlock(UnlocalizedName.APPLE_PLANK_FENCE_GATE));
    public static final RegistryObject<GrowthcraftPressurePlateBlock> applePlankPressurePlate = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE, () -> new GrowthcraftPressurePlateBlock(UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE));
    public static final RegistryObject<GrowthcraftSlabBlock> applePlankSlab = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_SLAB, () -> new GrowthcraftSlabBlock(UnlocalizedName.APPLE_PLANK_SLAB));
    public static final RegistryObject<AppleStairsBlock> applePlankStairs = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_STAIRS, AppleStairsBlock::new);
    public static final RegistryObject<GrowthcraftTrapdoor> applePlankTrapdoor = BLOCKS.register(
            UnlocalizedName.APPLE_PLANK_TRAPDOOR, () -> new GrowthcraftTrapdoor(UnlocalizedName.APPLE_PLANK_TRAPDOOR, Material.WOOD));
    public static final RegistryObject<AppleTreeFruit> appleTreeFruit = BLOCKS.register(
            UnlocalizedName.APPLE_TREE_FRUIT, () -> new AppleTreeFruit());
    public static final RegistryObject<AppleTreeLeaves> appleTreeLeaves = BLOCKS.register(
            UnlocalizedName.APPLE_TREE_LEAVES, () -> new AppleTreeLeaves());
    public static final RegistryObject<GrowthcraftSaplingBlock> appleTreeSapling = BLOCKS.register(
            UnlocalizedName.APPLE_TREE_SAPLING, () -> new GrowthcraftSaplingBlock(() -> new AppleTree()));
    public static final RegistryObject<GrowthcraftLogBlock> appleWood = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD, () -> new GrowthcraftLogBlock());
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLog = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD_LOG, () -> new GrowthcraftLogBlock());
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLogStripped = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD_LOG_STRIPPED, () -> new GrowthcraftLogBlock());
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodStripped = BLOCKS.register(
            UnlocalizedName.APPLE_WOOD_STRIPPED, () -> new GrowthcraftLogBlock());

    private GrowthcraftApplesBlocks() { /* Prevent default public constructor */ }
}

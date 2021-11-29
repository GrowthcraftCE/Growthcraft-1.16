package growthcraft.apiary.init;

import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class GrowthcraftApiaryBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<TorchBlock> CANDLE_BLACK = BLOCKS.register(
            UnlocalizedName.CANDLE_BLACK, () -> new TorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> {
                return 14;
            }).sound(SoundType.WOOD), ParticleTypes.FLAME)
    );

    public static final RegistryObject<WallTorchBlock> CANDLE_BLACK_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_BLACK_WALL, () -> new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) -> {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(CANDLE_BLACK), ParticleTypes.FLAME)
    );

    /**
     * Register Block Items.
     *
     * @param itemRegistry Delegated forge registry.
     * @param properties   Item Properties.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });
    }

    /**
     * Exclude blocks from the item registry. These blocks are not to be crafted or
     * accessible through the creative screen.
     *
     * @param registryName Resource Location of the block to query against the exclusion list.
     * @return boolean If block is to be excluded from the item registry.
     */
    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        // Add any blocks that need to be excluded.
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_BLACK);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_BLACK_WALL);
        return excludeBlocks.contains(registryName.toString());
    }

}

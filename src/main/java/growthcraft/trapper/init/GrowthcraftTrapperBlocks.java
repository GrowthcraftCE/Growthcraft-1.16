package growthcraft.trapper.init;

import growthcraft.trapper.common.block.*;
import growthcraft.trapper.shared.Reference;
import growthcraft.trapper.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftTrapperBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    /* TODO[13]: Implement oakFishtrap */
    public static final RegistryObject<BlockOakFishtrap> oakFishtrap;
    public static final RegistryObject<BlockAcaciaFishtrap> acaciaFishtrap;
    public static final RegistryObject<BlockDarkOakFishtrap> darkOakFishtrap;
    public static final RegistryObject<BlockBirchFishtrap> birchFishtrap;
    public static final RegistryObject<BlockJungleFishtrap> jungleFishtrap;
    public static final RegistryObject<BlockSpruceFishtrap> spruceFishtrap;

    static {
        oakFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_OAK,
                () -> new BlockOakFishtrap()
        );
        acaciaFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_ACACIA,
                () -> new BlockAcaciaFishtrap()
        );
        darkOakFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_DARK_OAK,
                () -> new BlockDarkOakFishtrap()
        );
        birchFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_BIRCH,
                () -> new BlockBirchFishtrap()
        );
        jungleFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_JUNGLE,
                () -> new BlockJungleFishtrap()
        );
        spruceFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_SPRUCE,
                () -> new BlockSpruceFishtrap()
        );
    }

    private GrowthcraftTrapperBlocks() { /* Prevent default public constructor */ }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        BLOCKS.getEntries().stream()
                .map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });
    }

}

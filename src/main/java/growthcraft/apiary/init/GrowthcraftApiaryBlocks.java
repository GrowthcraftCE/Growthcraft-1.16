package growthcraft.apiary.init;

import growthcraft.apiary.common.block.CandleBlock;
import growthcraft.apiary.common.block.CandleWallBlock;
import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import growthcraft.lib.util.FluidUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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

    public static final RegistryObject<CandleBlock> CANDLE_BLACK = BLOCKS.register(
            UnlocalizedName.CANDLE_BLACK, () -> new CandleBlock(
                    Reference.FluidColor.WAX_BLACK_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_BLACK_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_BLACK_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_BLACK_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_BLUE = BLOCKS.register(
            UnlocalizedName.CANDLE_BLUE, () -> new CandleBlock(
                    Reference.FluidColor.WAX_BLUE_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_BLUE_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_BLUE_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_BLUE_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_BROWN = BLOCKS.register(
            UnlocalizedName.CANDLE_BROWN, () -> new CandleBlock(
                    Reference.FluidColor.WAX_BROWN_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_BROWN_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_BROWN_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_BROWN_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_CYAN = BLOCKS.register(
            UnlocalizedName.CANDLE_CYAN, () -> new CandleBlock(
                    Reference.FluidColor.WAX_CYAN_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_CYAN_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_CYAN_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_CYAN_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_GRAY = BLOCKS.register(
            UnlocalizedName.CANDLE_GRAY, () -> new CandleBlock(
                    Reference.FluidColor.WAX_GRAY_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_GRAY_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_GRAY_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_GRAY_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_GREEN = BLOCKS.register(
            UnlocalizedName.CANDLE_GREEN, () -> new CandleBlock(
                    Reference.FluidColor.WAX_GREEN_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_GREEN_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_GREEN_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_GREEN_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_LIGHT_BLUE = BLOCKS.register(
            UnlocalizedName.CANDLE_LIGHT_BLUE, () -> new CandleBlock(
                    Reference.FluidColor.WAX_LIGHT_BLUE_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_LIGHT_BLUE_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_LIGHT_BLUE_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_LIGHT_BLUE_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_LIGHT_GRAY = BLOCKS.register(
            UnlocalizedName.CANDLE_LIGHT_GRAY, () -> new CandleBlock(
                    Reference.FluidColor.WAX_LIGHT_GRAY_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_LIGHT_GRAY_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_LIGHT_GRAY_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_LIGHT_GRAY_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_LIME = BLOCKS.register(
            UnlocalizedName.CANDLE_LIME, () -> new CandleBlock(
                    Reference.FluidColor.WAX_LIME_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_LIME_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_LIME_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_LIME_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_MAGENTA = BLOCKS.register(
            UnlocalizedName.CANDLE_MAGENTA, () -> new CandleBlock(
                    Reference.FluidColor.WAX_MAGENTA_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_MAGENTA_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_MAGENTA_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_MAGENTA_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_ORANGE = BLOCKS.register(
            UnlocalizedName.CANDLE_ORANGE, () -> new CandleBlock(
                    Reference.FluidColor.WAX_ORANGE_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_ORANGE_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_ORANGE_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_ORANGE_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_PINK = BLOCKS.register(
            UnlocalizedName.CANDLE_PINK, () -> new CandleBlock(
                    Reference.FluidColor.WAX_PINK_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_PINK_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_PINK_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_PINK_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_PURPLE = BLOCKS.register(
            UnlocalizedName.CANDLE_PURPLE, () -> new CandleBlock(
                    Reference.FluidColor.WAX_PURPLE_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_PURPLE_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_PURPLE_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_PURPLE_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_RED = BLOCKS.register(
            UnlocalizedName.CANDLE_RED, () -> new CandleBlock(
                    Reference.FluidColor.WAX_RED_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_RED_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_RED_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_RED_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_WHITE = BLOCKS.register(
            UnlocalizedName.CANDLE_WHITE, () -> new CandleBlock(
                    Reference.FluidColor.WAX_WHITE_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_WHITE_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_WHITE_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_WHITE_COLOR.getColor())
    );

    public static final RegistryObject<CandleBlock> CANDLE_YELLOW = BLOCKS.register(
            UnlocalizedName.CANDLE_YELLOW, () -> new CandleBlock(
                    Reference.FluidColor.WAX_YELLOW_COLOR.getColor())
    );

    public static final RegistryObject<CandleWallBlock> CANDLE_YELLOW_WALL = BLOCKS.register(
            UnlocalizedName.CANDLE_YELLOW_WALL, () -> new CandleWallBlock(
                    Reference.FluidColor.WAX_YELLOW_COLOR.getColor())
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
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_BLUE);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_BROWN);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_CYAN);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_GRAY);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_GREEN);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_LIGHT_BLUE);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_LIGHT_GRAY);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_LIME);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_MAGENTA);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_ORANGE);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_PINK);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_PURPLE);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_RED);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_WHITE);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_YELLOW);

        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_BLACK_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_BLUE_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_BROWN_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_CYAN_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_GRAY_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_GREEN_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_LIGHT_BLUE_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_LIGHT_GRAY_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_LIME_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_MAGENTA_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_ORANGE_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_PINK_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_PURPLE_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_RED_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_WHITE_WALL);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.CANDLE_YELLOW_WALL);

        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLACK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BLUE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_BROWN).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_CYAN).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GREEN).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_GRAY).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_BLUE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIGHT_GRAY).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_LIME).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_MAGENTA).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_ORANGE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PINK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_PURPLE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_RED).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_WHITE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WAX_YELLOW).get(FluidUtils.BLOCK));

        return excludeBlocks.contains(registryName.toString());
    }

}

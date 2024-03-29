package growthcraft.rice.init;

import growthcraft.lib.util.FluidUtils;
import growthcraft.rice.common.block.CultivatedFarmlandBlock;
import growthcraft.rice.common.block.RiceCropBlock;
import growthcraft.rice.shared.Reference;
import growthcraft.rice.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class GrowthcraftRiceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<CultivatedFarmlandBlock> CULTIVATED_FARMLAND = BLOCKS.register(
            UnlocalizedName.CULTIVATED_FARMLAND, CultivatedFarmlandBlock::new
    );

    public static final RegistryObject<RiceCropBlock> RICE_CROP = BLOCKS.register(
            UnlocalizedName.RICE_CROP, RiceCropBlock::new
    );

    // TODO[]: Rice Mat - used to roll dried seaweed, rice, and a meat to make sushi rolls.

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        // Add any blocks that need to be excluded.
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WATER).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.RICE_WINE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.SAKE).get(FluidUtils.BLOCK));

        return excludeBlocks.contains(registryName.toString());
    }
}

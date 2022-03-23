package growthcraft.milk.init;

import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.common.block.ChurnBlock;
import growthcraft.milk.common.block.MixingVatBlock;
import growthcraft.milk.common.block.PancheonBlock;
import growthcraft.milk.common.block.ThistleCropBlock;
import growthcraft.milk.lib.common.block.CheeseCurdBlock;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class GrowthcraftMilkBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<MixingVatBlock> MIXING_VAT = BLOCKS.register(
            UnlocalizedName.MIXING_VAT, MixingVatBlock::new
    );
    public static final RegistryObject<ChurnBlock> CHURN = BLOCKS.register(
            UnlocalizedName.CHURN, ChurnBlock::new
    );
    public static final RegistryObject<ThistleCropBlock> THISTLE_CROP = BLOCKS.register(
            UnlocalizedName.THISTLE_CROP, ThistleCropBlock::new
    );
    public static final RegistryObject<PancheonBlock> PANCHEON = BLOCKS.register(
            UnlocalizedName.PANCHEON, PancheonBlock::new
    );
    public static final RegistryObject<CheeseCurdBlock> CHEESE_CURD_CHEDDAR = BLOCKS.register(
            UnlocalizedName.CHEESE_CURDS_CHEDDAR, CheeseCurdBlock::new
    );

    private GrowthcraftMilkBlocks() {
        /* Prevent generation of public constructor */
    }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        GrowthcraftMilk.LOGGER.debug("<Growthcraft-Milk> Registration of itemBlocks started ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        GrowthcraftMilk.LOGGER.debug("<Growthcraft-Milk> Registration of itemBlocks finished.");
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.BUTTER_MILK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.CHEESE_BASE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.CONDENSED_MILK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.CREAM).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.CULTURED_MILK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.KUMIS).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.MILK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.RENNET).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.SKIM_MILK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(UnlocalizedName.FluidName.WHEY).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.THISTLE_CROP);

        return excludeBlocks.contains(registryName.toString());
    }
}

package growthcraft.milk.init;

import growthcraft.lib.util.CheeseUtils;
import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.common.block.*;
import growthcraft.milk.lib.common.block.CheeseCurdBlock;
import growthcraft.milk.lib.common.block.CheeseWheelBlock;
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
import org.apache.logging.log4j.Level;

import java.util.ArrayList;

public class GrowthcraftMilkBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<CheesePressBlock> CHEESE_PRESS = BLOCKS.register(
            UnlocalizedName.CHEESE_PRESS,
            CheesePressBlock::new
    );
    public static final RegistryObject<CheeseWheelBlock> APPENZELLER_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.APPENZELLER).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.APPENZELLER_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> APPENZELLER_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.APPENZELLER).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.APPENZELLER_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseWheelBlock> ASIAGO_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.ASIAGO).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.ASIAGO_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> ASIAGO_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.ASIAGO).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.ASIAGO_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseWheelBlock> CASU_MARZU_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CASU_MARZU).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.CASU_MARZU_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> CASU_MARZU_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CASU_MARZU).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.CASU_MARZU_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseWheelBlock> CHEDDAR_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CHEDDAR).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.CHEDDAR_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> CHEDDAR_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.CHEDDAR).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.CHEDDAR_CHEESE.getColor())
    );
    public static final RegistryObject<ChurnBlock> CHURN = BLOCKS.register(
            UnlocalizedName.CHURN, ChurnBlock::new
    );
    public static final RegistryObject<CheeseWheelBlock> EMMENTALER_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.EMMENTALER).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.EMMENTALER_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> EMMENTALER_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.EMMENTALER).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.EMMENTALER_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseWheelBlock> GORGONZOLA_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GORGONZOLA).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.GORGONZOLA_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> GORGONZOLA_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GORGONZOLA).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.GORGONZOLA_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseWheelBlock> GOUDA_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GOUDA).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.GOUDA_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> GOUDA_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.GOUDA).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.GOUDA_CHEESE.getColor())
    );
    public static final RegistryObject<MixingVatBlock> MIXING_VAT = BLOCKS.register(
            UnlocalizedName.MIXING_VAT, MixingVatBlock::new
    );
    public static final RegistryObject<CheeseWheelBlock> MONTEREY_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.MONTEREY).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.MONTEREY_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> MONTEREY_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.MONTEREY).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.MONTEREY_CHEESE.getColor())
    );
    public static final RegistryObject<PancheonBlock> PANCHEON = BLOCKS.register(
            UnlocalizedName.PANCHEON, PancheonBlock::new
    );
    public static final RegistryObject<CheeseWheelBlock> PARMESAN_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PARMESAN).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.PARMESAN_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> PARMESAN_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PARMESAN).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.PARMESAN_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> RICOTTA_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.RICOTTA).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.RICOTTA_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseWheelBlock> PROVOLONE_CHEESE = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PROVOLONE).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.PROVOLONE_CHEESE.getColor())
    );
    public static final RegistryObject<CheeseCurdBlock> PROVOLONE_CHEESE_CURDS = BLOCKS.register(
            CheeseUtils.getCheeseNames(UnlocalizedName.CheeseName.PROVOLONE).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.PROVOLONE_CHEESE.getColor())
    );
    public static final RegistryObject<ThistleCropBlock> THISTLE_CROP = BLOCKS.register(
            UnlocalizedName.THISTLE_CROP, ThistleCropBlock::new
    );

    private GrowthcraftMilkBlocks() {
        /* Prevent generation of public constructor */
    }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        GrowthcraftMilk.LOGGER.log(Level.DEBUG,
                "<{}> Registration of itemBlocks started ...",
                Reference.MODID);
        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        GrowthcraftMilk.LOGGER.log(Level.DEBUG,
                "<{}> Registration of itemBlocks finished.",
                Reference.MODID);
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

package growthcraft.cellar.init;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.block.*;
import growthcraft.cellar.common.block.crop.HopsCrop;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class GrowthcraftCellarBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
    public static final RegistryObject<FruitPressBlock> FRUIT_PRESS = BLOCKS.register(
            UnlocalizedName.FRUIT_PRESS,
            FruitPressBlock::new
    );
    public static final RegistryObject<FruitPressPistonBlock> FRUIT_PRESS_PISTON = BLOCKS.register(
            UnlocalizedName.FRUIT_PRESS_PISTON,
            FruitPressPistonBlock::new
    );
    public static final RegistryObject<FermentationBarrelBlock> barrel_ferment_oak = BLOCKS.register(
            UnlocalizedName.FERMENT_BARREL_OAK,
            FermentationBarrelBlock::new
    );
    public static final RegistryObject<BrewKettleBlock> brew_kettle = BLOCKS.register(
            UnlocalizedName.BREW_KETTLE,
            BrewKettleBlock::new
    );
    public static final RegistryObject<CultureJarBlock> culture_jar = BLOCKS.register(
            UnlocalizedName.CULTURE_JAR,
            CultureJarBlock::new
    );
    public static final RegistryObject<RoasterBlock> roaster = BLOCKS.register(
            UnlocalizedName.ROASTER,
            RoasterBlock::new
    );

    public static final RegistryObject<HopsCrop> hops_vine = BLOCKS.register(
            UnlocalizedName.HOPS_VINE,
            HopsCrop::new
    );

    private GrowthcraftCellarBlocks() { /* Prevent Default Public Constructor */ }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        GrowthcraftCellar.LOGGER.debug("<Growthcraft-Cellar> Registration of itemBlocks started ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        GrowthcraftCellar.LOGGER.debug("<Growthcraft-Cellar> Registration of itemBlocks finished.");
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.FRUIT_PRESS_PISTON);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.AMBER_ALE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.AMBER_LAGER).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.BROWN_ALE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.COPPER_ALE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.PALE_ALE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.OLD_PORT_ALE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.STOUT_ALE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.PILSNER_LAGER).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.DARK_LAGER).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.IPA_ALE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.VIENNA_LAGER).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.PALE_LAGER).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.BROWN_LAGER).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.COPPER_LAGER).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.PALE_GOLDEN_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.GOLDEN_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.AMBER_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.DEEP_AMBER_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.COPPER_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.DEEP_COPPER_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.BROWN_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.DARK_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.RED_GRAPE_JUICE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.WHITE_GRAPE_JUICE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.PURPLE_GRAPE_JUICE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.HOPPED_GOLDEN_WORT).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.RED_WINE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.PURPLE_WINE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.WHITE_WINE).get(GrowthcraftCellarFluids.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.HOPS_VINE);

        return excludeBlocks.contains(registryName.toString());
    }

}

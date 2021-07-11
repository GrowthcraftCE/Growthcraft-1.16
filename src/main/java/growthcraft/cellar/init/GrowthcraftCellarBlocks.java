package growthcraft.cellar.init;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.block.BrewKettleBlock;
import growthcraft.cellar.common.block.CultureJarBlock;
import growthcraft.cellar.common.block.FermentationBarrelBlock;
import growthcraft.cellar.common.block.RoasterBlock;
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

    public static final RegistryObject<BrewKettleBlock> brew_kettle = BLOCKS.register(
            UnlocalizedName.BREW_KETTLE,
            BrewKettleBlock::new
    );

    public static final RegistryObject<CultureJarBlock> culture_jar = BLOCKS.register(
            UnlocalizedName.CULTURE_JAR,
            CultureJarBlock::new
    );

    public static final RegistryObject<FermentationBarrelBlock> barrel_ferment_oak = BLOCKS.register(
            UnlocalizedName.FERMENT_BARREL_OAK,
            FermentationBarrelBlock::new
    );

    public static final RegistryObject<RoasterBlock> roaster = BLOCKS.register(
            UnlocalizedName.ROASTER,
            RoasterBlock::new
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
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.WORT).get("fluid"));
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.getFluidNames(Reference.FluidName.WORT).get("flowing"));
        return excludeBlocks.contains(registryName.toString());
    }

}

package growthcraft.cellar.init;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.block.BrewKettleBlock;
import growthcraft.cellar.common.block.CultureJarBlock;
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
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.WORT);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.WORT_FLUID);
        excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.WORT_FLUID_FLOWING);
        return excludeBlocks.contains(registryName.toString());
    }

}

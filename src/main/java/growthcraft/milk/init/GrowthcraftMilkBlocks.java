package growthcraft.milk.init;

import growthcraft.milk.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class GrowthcraftMilkBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    private GrowthcraftMilkBlocks() {
        /* Prevent generation of public constructor */
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + UnlocalizedName.FRUIT_PRESS_PISTON);
        return excludeBlocks.contains(registryName.toString());
    }
}

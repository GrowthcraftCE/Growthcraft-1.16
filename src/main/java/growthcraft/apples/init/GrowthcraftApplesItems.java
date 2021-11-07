package growthcraft.apples.init;

import growthcraft.cellar.shared.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    private GrowthcraftApplesItems() { /* Prevent default public constructor */ }

}

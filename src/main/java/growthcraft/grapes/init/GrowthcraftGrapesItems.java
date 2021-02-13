package growthcraft.grapes.init;

import growthcraft.grapes.common.item.ItemGrape;
import growthcraft.grapes.common.item.ItemGrapeSeeds;
import growthcraft.grapes.shared.Reference;
import growthcraft.grapes.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftGrapesItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(
            ForgeRegistries.ITEMS, Reference.MODID
    );
    /**
     * Food items are special as you have to pass the item group when you instantiate it. We cannot
     * append it later.
     */
    public static final DeferredRegister<Item> FOODS = new DeferredRegister<>(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<ItemGrape> GRAPES_RED = FOODS.register(
            UnlocalizedName.GRAPES_RED,
            () -> new ItemGrape(1, 1.0F));

    public static final RegistryObject<ItemGrape> GRAPES_WHITE = FOODS.register(
            UnlocalizedName.GRAPES_WHITE,
            () -> new ItemGrape(1, 1.0F));

    public static final RegistryObject<ItemGrape> GRAPES_PURPLE = FOODS.register(
            UnlocalizedName.GRAPES_PURPLE,
            () -> new ItemGrape(1, 1.0F));

    public static final RegistryObject<ItemGrapeSeeds> GRAPE_SEEDS_PURPLE = ITEMS.register(
            UnlocalizedName.GRAPE_SEEDS_PURPLE,
            () -> new ItemGrapeSeeds(GrowthcraftGrapesBlocks.GRAPE_VINE_PURPLE.get())
    );

    public static final RegistryObject<ItemGrapeSeeds> GRAPE_SEEDS_RED = ITEMS.register(
            UnlocalizedName.GRAPE_SEEDS_RED,
            () -> new ItemGrapeSeeds(GrowthcraftGrapesBlocks.GRAPE_VINE_RED.get())
    );

    public static final RegistryObject<ItemGrapeSeeds> GRAPE_SEEDS_WHITE = ITEMS.register(
            UnlocalizedName.GRAPE_SEEDS_WHITE,
            () -> new ItemGrapeSeeds(GrowthcraftGrapesBlocks.GRAPE_VINE_WHITE.get())
    );

    private GrowthcraftGrapesItems() { /* Prevent default public constructor */ }

}

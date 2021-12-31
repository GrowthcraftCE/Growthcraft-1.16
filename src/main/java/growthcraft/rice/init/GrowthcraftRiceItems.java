package growthcraft.rice.init;

import growthcraft.lib.common.item.GrowthcraftFoodItem;
import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.rice.common.item.CultivatorItem;
import growthcraft.rice.common.item.RiceSeedItem;
import growthcraft.rice.shared.Reference;
import growthcraft.rice.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftRiceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<CultivatorItem> CULTIVATOR = ITEMS.register(
            UnlocalizedName.CULTIVATOR, CultivatorItem::new
    );

    // TODO[]: Rice Stalk - Planted rice stalk/seed
    public static final RegistryObject<GrowthcraftItem> RICE_STALK = ITEMS.register(
            UnlocalizedName.RICE_STALK, GrowthcraftItem::new
    );

    // TODO[]: Rice - Rice Stalk split into rice via the stone cutter
    public static final RegistryObject<RiceSeedItem> RICE = ITEMS.register(
            UnlocalizedName.RICE, RiceSeedItem::new
    );

    // TODO[]: Cooked Rice - Water and Rice in the Brew Kettle
    public static final RegistryObject<GrowthcraftFoodItem> RICE_COOKED = ITEMS.register(
            UnlocalizedName.RICE_COOKED, GrowthcraftFoodItem::new
    );

    // TODO[]: Sushi - Food Item
    public static final RegistryObject<GrowthcraftFoodItem> SUSHI_ROLL = ITEMS.register(
            UnlocalizedName.SUSHI_ROLL, GrowthcraftFoodItem::new
    );

    // TODO[]: Dried Seaweed - Used to make sushi rolls.

    // TODO[]: Knife - Used to cut sushi rolls on the Rice Mat.
    public static final RegistryObject<GrowthcraftItem> KNIFE = ITEMS.register(
            UnlocalizedName.KNIFE, GrowthcraftItem::new
    );

}

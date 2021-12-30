package growthcraft.rice.init;

import growthcraft.rice.common.item.CultivatorItem;
import growthcraft.rice.shared.Reference;
import growthcraft.rice.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftRiceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    // TODO[]: Cultivator - Used to make farmland and Culavated Farmland
    public static final RegistryObject<CultivatorItem> CULTIVATOR = ITEMS.register(
            UnlocalizedName.CULTIVATOR, CultivatorItem::new
    );

    // TODO[]: Rice Stalk - Planted rice stalk/seed

    // TODO[]: Rice - Rice Stalk split into rice via the stone cutter

    // TODO[]: Cooked Rice - Water and Rice in the Brew Kettle

    // TODO[]: Sushi - Food Item

    // TODO[]: Dried Seaweed - Used to make sushi rolls.

    // TODO[]: Knife - Used to cut sushi rolls on the Rice Mat.

}

package growthcraft.cellar.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class GrowthcraftCellarLootModifier extends LootModifier {

    private final Item item;

    protected GrowthcraftCellarLootModifier(ILootCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(item, 1));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<GrowthcraftCellarLootModifier> {

        @Override
        public GrowthcraftCellarLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getString(object, "item"))));
            return new GrowthcraftCellarLootModifier(lootConditions, item);
        }

        @Override
        public JsonObject write(GrowthcraftCellarLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.item).toString());
            return json;
        }
    }
}

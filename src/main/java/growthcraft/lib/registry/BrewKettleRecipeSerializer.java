package growthcraft.lib.registry;

import com.google.gson.JsonObject;
import growthcraft.cellar.lib.recipe.BrewKettleRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class BrewKettleRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BrewKettleRecipe> {

    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack result = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
        return new BrewKettleRecipe(recipeId, ingredient, result);
    }

    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack result = buffer.readItemStack();
        Ingredient ingredient = Ingredient.read(buffer);
        return new BrewKettleRecipe(recipeId, ingredient, result);
    }

    @Override
    public void write(PacketBuffer buffer, BrewKettleRecipe recipe) {
        Ingredient ingredient = recipe.getIngredients().get(0);
        ingredient.write(buffer);
        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }
}

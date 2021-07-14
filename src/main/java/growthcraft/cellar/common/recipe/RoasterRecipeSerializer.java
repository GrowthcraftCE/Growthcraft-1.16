package growthcraft.cellar.common.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class RoasterRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<RoasterRecipe> {

    @Override
    public RoasterRecipe read(ResourceLocation recipeId, JsonObject json) {
        // Processing Time
        int processingTime = JSONUtils.getInt(json, "processint_time");

        // Input ItemStack
        ItemStack inputItemStack = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "input_item"), false);

        // Resulting ItemStack
        ItemStack outputItemStack = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result"), false);

        return new RoasterRecipe(recipeId, inputItemStack, outputItemStack, processingTime);
    }

    @Nullable
    @Override
    public RoasterRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return null;
    }

    @Override
    public void write(PacketBuffer buffer, RoasterRecipe recipe) {

    }
}

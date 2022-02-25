package growthcraft.milk.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import growthcraft.lib.util.CraftingUtils;
import growthcraft.milk.GrowthcraftMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MixingVatRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MixingVatRecipe> {

    private static final int maxIngredients = 3;

    /**
     * Server read from the json file.
     *
     * @param recipeId ResourceLocation of the recipe to read.
     * @param json     Contents of the JSON file found in the data pack.
     * @return MixingVatRecipe built from the JSON data.
     */
    @Override
    public MixingVatRecipe read(ResourceLocation recipeId, JsonObject json) {

        MixingVatRecipe.MixingVatRecipeCategory category
                = MixingVatRecipe.MixingVatRecipeCategory.with(JSONUtils.getString(json, "result_type"));

        int processingTime = JSONUtils.getInt(json, "processing_time", 1200);

        FluidStack inputFluid = CraftingUtils.getFluidStack(
                JSONUtils.getJsonObject(json, "input_fluid"));

        ItemStack activationTool = CraftingHelper.getItemStack(
                JSONUtils.getJsonObject(json, "result_activation_tool"), false);

        List<ItemStack> ingredients = new ArrayList<>();
        JsonArray jsonIngredients = JSONUtils.getJsonArray(json, "ingredients");

        if (jsonIngredients.size() <= maxIngredients) {
            for (int i = 0; i < jsonIngredients.size(); i++) {
                ItemStack itemStack = CraftingHelper.getItemStack(jsonIngredients.get(i).getAsJsonObject(), false);
                ingredients.add(itemStack);
            }
        }

        if (category == MixingVatRecipe.MixingVatRecipeCategory.ITEM) {
            ItemStack resultItemStack = CraftingHelper.getItemStack(
                    JSONUtils.getJsonObject(json, "result_item"), false);
            return new MixingVatItemRecipe(recipeId, MixingVatRecipe.MixingVatRecipeCategory.ITEM,
                    inputFluid, ingredients, processingTime, resultItemStack, activationTool);
        }

        if (category == MixingVatRecipe.MixingVatRecipeCategory.FLUID) {
            FluidStack reagentFluid = CraftingUtils.getFluidStack(
                    JSONUtils.getJsonObject(json, "reagent_fluid"));
            FluidStack resultFluid = CraftingUtils.getFluidStack(
                    JSONUtils.getJsonObject(json, "result_fluid"));
            FluidStack wasteFluid = CraftingUtils.getFluidStack(
                    JSONUtils.getJsonObject(json, "result_fluid_waste"));
            return new MixingVatFluidRecipe(recipeId, MixingVatRecipe.MixingVatRecipeCategory.FLUID,
                    inputFluid, reagentFluid, ingredients, processingTime, resultFluid, wasteFluid, activationTool);
        }

        return null;
    }

    /**
     * Client read from the buffer sent from the server.
     *
     * @param recipeId ResourceLocation of the recipe to read.
     * @param buffer   Buffer from the server to be read.
     * @return PancheonRecipe extracted from the buffer.
     */
    @Nullable
    @Override
    public MixingVatRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        try {
            MixingVatRecipe.MixingVatRecipeCategory category = MixingVatRecipe.MixingVatRecipeCategory.valueOf(buffer.readString());

            int processingTime = buffer.readVarInt();
            FluidStack inputFluidStack = buffer.readFluidStack();
            ItemStack activationTool = buffer.readItemStack();

            int ingredientSize = buffer.readVarInt();
            List<ItemStack> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientSize; i++) {
                ingredients.add(buffer.readItemStack());
            }

            if (category == MixingVatRecipe.MixingVatRecipeCategory.ITEM) {
                ItemStack resultingItemStack = buffer.readItemStack();

                return new MixingVatItemRecipe(recipeId, category, inputFluidStack, ingredients,
                        processingTime, resultingItemStack, activationTool);

            }

            if (category == MixingVatRecipe.MixingVatRecipeCategory.FLUID) {
                FluidStack reagentFluidStack = buffer.readFluidStack();
                FluidStack outputFluidStack = buffer.readFluidStack();
                FluidStack wasteFluidStack = buffer.readFluidStack();

                return new MixingVatFluidRecipe(recipeId, category, inputFluidStack, reagentFluidStack,
                        ingredients, processingTime, outputFluidStack, wasteFluidStack, activationTool);
            }

        } catch (Exception ex) {
            String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
            GrowthcraftMilk.LOGGER.error(message);
            throw ex;
        }

        return null;
    }

    @Override
    public void write(PacketBuffer buffer, MixingVatRecipe recipe) {

        buffer.writeString(recipe.getCategory().toString());
        buffer.writeVarInt(recipe.getProcessingTime());
        buffer.writeFluidStack(recipe.getInputFluidStack());
        buffer.writeItemStack(recipe.getActivationTool());
        buffer.writeVarInt(recipe.getIngredientList().size());
        for (int i = 0; i < recipe.getIngredientList().size(); i++) {
            buffer.writeItemStack(recipe.getIngredientList().get(i));
        }
        // Depending on recipe category, we have to build the buffer differently.
        if (recipe.getCategory() == MixingVatRecipe.MixingVatRecipeCategory.ITEM) {
            MixingVatItemRecipe itemRecipe = (MixingVatItemRecipe) recipe;
            buffer.writeItemStack(itemRecipe.getResultItemStack());
        }
        if (recipe.getCategory() == MixingVatRecipe.MixingVatRecipeCategory.FLUID) {
            MixingVatFluidRecipe fluidRecipe = (MixingVatFluidRecipe) recipe;
            buffer.writeFluidStack(fluidRecipe.getReagentFluidStack());
            buffer.writeFluidStack(fluidRecipe.getOutputFluidStack());
            buffer.writeFluidStack(fluidRecipe.getWasteFluidStack());
        }
    }
}

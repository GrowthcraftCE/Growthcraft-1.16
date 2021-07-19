package growthcraft.cellar.common.recipe;

import com.google.gson.JsonObject;
import growthcraft.lib.util.CraftingUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class FermentBarrelRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FermentBarrelRecipe> {

    @Override
    public FermentBarrelRecipe read(ResourceLocation recipeId, JsonObject json) {
        // Processing Time
        int processingTime = JSONUtils.getInt(json, "processing_time");

        // Inputs
        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "ingredient_fluid"));
        ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "ingredient_item"), false);

        // Outputs
        FluidStack outputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "result"));

        return new FermentBarrelRecipe(recipeId, inputFluid, inputItem, outputFluid, processingTime);
    }

    @Nullable
    @Override
    public FermentBarrelRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return null;
    }

    @Override
    public void write(PacketBuffer buffer, FermentBarrelRecipe recipe) {

    }

}

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

public class CultureJarRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CultureJarRecipe> {

    @Override
    public CultureJarRecipe read(ResourceLocation recipeId, JsonObject json) {
        // Processing Time
        int processingTime = JSONUtils.getInt(json, "processing_time");

        // Inputs
        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "input_fluid"));
        ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "input_item"), false);

        return new CultureJarRecipe(recipeId, inputFluid, inputItem, processingTime);
    }

    @Nullable
    @Override
    public CultureJarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return null;
    }

    @Override
    public void write(PacketBuffer buffer, CultureJarRecipe recipe) {

    }

}

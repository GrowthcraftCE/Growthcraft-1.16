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

public class BrewKettleRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BrewKettleRecipe> {

    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, JsonObject json) {
        // Requirements
        boolean requireLid = JSONUtils.getBoolean(json,"requires_lid");
        // Input
        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "input_fluid"));
        ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "input_item"), false);
        // Output
        FluidStack outputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "output_fluid"));
        ItemStack byProduct = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "by_product"), false);

        return new BrewKettleRecipe(recipeId, inputFluid, inputItem, outputFluid, byProduct, requireLid);
    }

    @Nullable
    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return null;
    }

    @Override
    public void write(PacketBuffer buffer, BrewKettleRecipe recipe) {

    }
}

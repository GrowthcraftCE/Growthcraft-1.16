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

public class FruitPressRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FruitPressRecipe> {

    @Override
    public FruitPressRecipe read(ResourceLocation recipeId, JsonObject json) {
        // Processing Time
        int processingTime = JSONUtils.getInt(json, "processing_time");

        // Input
        ItemStack inputItemStack = CraftingHelper.getItemStack(
                JSONUtils.getJsonObject(json, "input_item"),
                false
        );

        // Output
        FluidStack outputFluid = CraftingUtils.getFluidStack(
                JSONUtils.getJsonObject(json, "output_fluid")
        );

        // By Product
        ItemStack itemByProduct = CraftingHelper.getItemStack(
                JSONUtils.getJsonObject(json, "by_product"),
                false
        );

        return new FruitPressRecipe(recipeId, inputItemStack, outputFluid, itemByProduct, processingTime);
    }

    @Nullable
    @Override
    public FruitPressRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return null;
    }

    @Override
    public void write(PacketBuffer buffer, FruitPressRecipe recipe) {

    }
}

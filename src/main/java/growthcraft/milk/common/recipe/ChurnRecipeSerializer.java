package growthcraft.milk.common.recipe;

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

public class ChurnRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ChurnRecipe> {

    @Override
    public ChurnRecipe read(ResourceLocation recipeId, JsonObject json) {

        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "input_fluid"));
        FluidStack outputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "output_fluid"));
        ItemStack byProduct = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "by_product"), false);

        int plunges = JSONUtils.getInt(json, "plunges", 7);
        int byProductChance = JSONUtils.getInt(json, "by_product_chance", 100);

        return new ChurnRecipe(recipeId, inputFluid, outputFluid, byProduct, byProductChance, plunges);
    }

    @Nullable
    @Override
    public ChurnRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        try {
            FluidStack a = buffer.readFluidStack();
            FluidStack b = buffer.readFluidStack();
            ItemStack c = buffer.readItemStack();
            int d = buffer.readVarInt();
            int e = buffer.readVarInt();

            return new ChurnRecipe(recipeId, a, b, c, d, e);
        } catch (Exception ex) {
            GrowthcraftMilk.LOGGER.error("[Growthcraft-Milk] Unable to read recipe from buffer!");
            throw ex;
        }
    }

    @Override
    public void write(PacketBuffer buffer, ChurnRecipe recipe) {
        try {
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeFluidStack(recipe.getOutputFluidStack());
            buffer.writeItemStack(recipe.getRecipeOutput());
            buffer.writeVarInt(recipe.getByProductChance());
            buffer.writeVarInt(recipe.getPlungesNeeded());
        } catch (Exception ex) {
            GrowthcraftMilk.LOGGER.error("[Growthcraft-Milk] Unable to write recipe to buffer!");
            throw ex;
        }
    }
}

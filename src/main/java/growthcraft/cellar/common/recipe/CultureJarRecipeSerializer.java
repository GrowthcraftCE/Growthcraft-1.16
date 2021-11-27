package growthcraft.cellar.common.recipe;

import com.google.gson.JsonObject;
import growthcraft.cellar.GrowthcraftCellar;
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
        // Needs Heat Source?
        boolean needsHeat = JSONUtils.getBoolean(json, "requires_heat_source");

        // Inputs
        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "input_fluid"));
        ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "input_item"), false);

        return new CultureJarRecipe(recipeId, inputFluid, inputItem, processingTime, needsHeat);
    }

    @Nullable
    @Override
    public CultureJarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        try {
            ItemStack inputItem = buffer.readItemStack();
            FluidStack inputFluid = buffer.readFluidStack();
            int processingTime = buffer.readVarInt();
            boolean heat = buffer.readBoolean();
            return new CultureJarRecipe(recipeId, inputFluid, inputItem, processingTime, heat);
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Unable to read recipe from network buffer!");
            throw ex;
        }
    }

    @Override
    public void write(PacketBuffer buffer, CultureJarRecipe recipe) {
        try {
            buffer.writeItemStack(recipe.getInputItem());
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeVarInt(recipe.getProcessingTime());
            buffer.writeBoolean(recipe.isRequiresHeatSource());
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Unable to write recipe from network buffer!");
            throw ex;
        }
    }

}

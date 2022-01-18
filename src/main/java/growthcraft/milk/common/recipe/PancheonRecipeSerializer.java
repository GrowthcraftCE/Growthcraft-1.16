package growthcraft.milk.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import growthcraft.lib.util.CraftingUtils;
import growthcraft.milk.GrowthcraftMilk;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class PancheonRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PancheonRecipe> {

    @Override
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public PancheonRecipe read(ResourceLocation recipeId, JsonObject json) {

        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "input_fluid"));
        FluidStack outputFluid1;
        FluidStack outputFluid2;

        JsonArray outputFluids = JSONUtils.getJsonArray(json, "output_fluids");

        outputFluid1 = CraftingUtils.getFluidStack(outputFluids.get(0).getAsJsonObject());
        outputFluid2 = CraftingUtils.getFluidStack(outputFluids.get(1).getAsJsonObject());

        int processingTime = JSONUtils.getInt(json, "processing_time", 1200);

        return new PancheonRecipe(recipeId, inputFluid, outputFluid1, outputFluid2, processingTime);
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
    public PancheonRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        try {
            FluidStack inputFluid = buffer.readFluidStack();
            FluidStack outputFluid1 = buffer.readFluidStack();
            FluidStack outputFluid2 = buffer.readFluidStack();
            int processingTime = buffer.readVarInt();
            return new PancheonRecipe(recipeId, inputFluid, outputFluid1, outputFluid2, processingTime);
        } catch (Exception ex) {
            String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
            GrowthcraftMilk.LOGGER.error(message);
            throw ex;
        }
    }

    /**
     * Write the recipe to the buffer to be sent to the client.
     *
     * @param buffer Buffer object holder.
     * @param recipe Recipe to be written to the buffer.
     */
    @Override
    public void write(PacketBuffer buffer, PancheonRecipe recipe) {
        buffer.writeFluidStack(recipe.getInputFluidStack());
        buffer.writeFluidStack(recipe.getOutputFluidStack(0));
        buffer.writeFluidStack(recipe.getOutputFluidStack(1));
        buffer.writeVarInt(recipe.getProcessingTime());
    }

}

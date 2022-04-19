package growthcraft.milk.common.recipe;

import com.google.gson.JsonObject;
import growthcraft.milk.GrowthcraftMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

public class CheesePressRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CheesePressRecipe> {

    /**
     * Server side registry read from JSON files.
     *
     * @param recipeId Resource location of json recipe file.
     * @param json     JSON contents of the recipe file.
     * @return CheesePressRecipe a new instance of a CheesePressRecipe.
     */
    @Override
    public CheesePressRecipe read(ResourceLocation recipeId, JsonObject json) {
        int processingTime = JSONUtils.getInt(json, "processing_time", 6000);

        ItemStack inputItemStack = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "ingredient"), true);

        ItemStack resultItemStack = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result_item"), true);

        return new CheesePressRecipe(recipeId, inputItemStack, resultItemStack, processingTime);
    }

    /**
     * Client side read from network buffer written from the serer.
     *
     * @param recipeId
     * @param buffer
     * @return
     */
    @Nullable
    @Override
    public CheesePressRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        try {
            ItemStack a = buffer.readItemStack();
            ItemStack b = buffer.readItemStack();
            int c = buffer.readVarInt();

            return new CheesePressRecipe(recipeId, a, b, c);
        } catch (Exception ex) {
            GrowthcraftMilk.LOGGER.log(Level.ERROR,
                    "{} threw an exception while trying to read {} from the network buffer.",
                    this.getClass().getName(), recipeId);
        }
        return null;
    }

    /**
     * Server side write the recipe data to a buffer to be sent to the client side.
     *
     * @param buffer
     * @param recipe
     */
    @Override
    public void write(PacketBuffer buffer, CheesePressRecipe recipe) {
        try {
            buffer.writeItemStack(recipe.getRecipeInput());
            buffer.writeItemStack(recipe.getRecipeOutput());
            buffer.writeVarInt(recipe.getProcessingTime());
        } catch (Exception ex) {
            GrowthcraftMilk.LOGGER.log(Level.ERROR,
                    "{} threw an exception while trying to write {} to the network buffer.",
                    this.getClass().getName(), recipe.getId());
        }
    }
}

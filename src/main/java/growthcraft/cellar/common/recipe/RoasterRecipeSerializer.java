package growthcraft.cellar.common.recipe;

import com.google.gson.JsonObject;
import growthcraft.cellar.GrowthcraftCellar;
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
        // Input ItemStack
        ItemStack inputItemStack = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "input"), false);

        // Resulting ItemStack
        ItemStack outputItemStack = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result"), false);

        // Redstone Timer ItemStack
        ItemStack redstoneTimerItemStack = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "redstone_timer"), false);

        return new RoasterRecipe(recipeId, inputItemStack, outputItemStack, redstoneTimerItemStack);
    }

    @Nullable
    @Override
    public RoasterRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        try {
            ItemStack inputItemStack = buffer.readItemStack();
            ItemStack outputItemStack = buffer.readItemStack();
            ItemStack redstoneTimerItem = buffer.readItemStack();
            return new RoasterRecipe(recipeId, inputItemStack, outputItemStack, redstoneTimerItem);
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Unable to read recipe from network buffer!");
            throw ex;
        }
    }

    @Override
    public void write(PacketBuffer buffer, RoasterRecipe recipe) {
        try {
            buffer.writeItemStack(recipe.getInputItemStack());
            buffer.writeItemStack(recipe.getRecipeOutput());
            buffer.writeItemStack(recipe.getRedstoneTimerItemStack());
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Unable to write recipe to network buffer!");
            throw ex;
        }
    }
}

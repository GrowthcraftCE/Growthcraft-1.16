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

public class BrewKettleRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BrewKettleRecipe> {

    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, JsonObject json) {
        // Requirements
        boolean requireLid = JSONUtils.getBoolean(json, "requires_lid", false);
        // Input
        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "input_fluid"));
        ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "input_item"), false);

        // Output
        FluidStack outputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "output_fluid"));
        ItemStack byProduct = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "by_product"), false);
        // ByProduct Chance
        int byProductChance = JSONUtils.getInt(json, "by_product_chance", 25);

        return new BrewKettleRecipe(recipeId, inputFluid, inputItem, outputFluid, byProduct, requireLid, byProductChance);
    }

    @Nullable
    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        try {
            ItemStack inputItem = buffer.readItemStack();
            FluidStack inputFluid = buffer.readFluidStack();
            FluidStack outputFluid = buffer.readFluidStack();
            ItemStack byProduct = buffer.readItemStack();
            boolean lid = buffer.readBoolean();
            int byProductChance = buffer.readVarInt();

            return new BrewKettleRecipe(recipeId, inputFluid, inputItem, outputFluid, byProduct, lid, byProductChance);
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Unable to read recipe from network buffer!");
            throw ex;
        }
    }

    @Override
    public void write(PacketBuffer buffer, BrewKettleRecipe recipe) {
        try {
            buffer.writeItemStack(recipe.getInputItemStack());
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeFluidStack(recipe.getOutputFluidStack());
            buffer.writeItemStack(recipe.getByProduct());
            buffer.writeBoolean(recipe.getLidRequired());
            buffer.writeVarInt(recipe.getByProductChance());
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Unable to read recipe from network buffer!");
            throw ex;
        }
    }
}

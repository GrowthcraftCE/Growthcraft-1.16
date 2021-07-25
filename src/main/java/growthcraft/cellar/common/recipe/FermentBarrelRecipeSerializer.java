package growthcraft.cellar.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import growthcraft.cellar.lib.effect.CellarPotionEffect;
import growthcraft.lib.util.CraftingUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effect;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FermentBarrelRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FermentBarrelRecipe> {

    @Override
    public FermentBarrelRecipe read(ResourceLocation recipeId, JsonObject json) {
        // Processing Time
        int processingTime = JSONUtils.getInt(json, "processing_time");

        // Color
        Color color = new Color(Integer.decode(JSONUtils.getString(json, "color")));

        // Inputs
        FluidStack inputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "ingredient_fluid"));
        ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "ingredient_item"), false);

        // Outputs
        FluidStack outputFluid = CraftingUtils.getFluidStack(JSONUtils.getJsonObject(json, "result"));

        List<CellarPotionEffect> effects = new ArrayList<>();

        //Effects
        JsonArray recipeEffects = JSONUtils.getJsonArray(json, "effects");
        for (int i = 0; i < recipeEffects.size(); i++) {
            String effectRegistryName = JSONUtils.getString(recipeEffects.get(i).getAsJsonObject(), "effect");
            int effectDuration = JSONUtils.getInt(recipeEffects.get(i).getAsJsonObject(), "duration", 200);
            int effectAmplifier = JSONUtils.getInt(recipeEffects.get(i).getAsJsonObject(), "amplifier", 0);

            Effect effect = ForgeRegistries.POTIONS.getValue(new ResourceLocation(effectRegistryName));

            effects.add(new CellarPotionEffect(effect, effectDuration, effectAmplifier));

        }

        return new FermentBarrelRecipe(recipeId, inputFluid, inputItem, outputFluid, processingTime, effects, color);
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

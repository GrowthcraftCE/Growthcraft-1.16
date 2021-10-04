package growthcraft.lib.util;

import growthcraft.cellar.common.recipe.FermentBarrelRecipe;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeUtils {

    public static FermentBarrelRecipe findFermentRecipesByResult(World world, FluidStack fluidStack) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(world, GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE);
        for (IRecipe<?> recipe : recipes) {
            FermentBarrelRecipe fermentBarrelRecipe = (FermentBarrelRecipe) recipe;
            if (fermentBarrelRecipe.matches(fluidStack)) {
                return fermentBarrelRecipe;
            }
        }
        return null;
    }

    public static Set<IRecipe<?>> findRecipesByType(World world, IRecipeType<?> recipeType) {
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(recipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> recipeType) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(recipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

}

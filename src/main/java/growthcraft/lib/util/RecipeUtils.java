package growthcraft.lib.util;

import growthcraft.cellar.common.recipe.FermentBarrelRecipe;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeUtils {

    /**
     * Search for recipes that result in the given fluid.
     *
     * @param world      World object
     * @param fluidStack FluidStack with expected recipe result
     * @return FermentBarrelRecipe that produces the given fluid.
     * @throws ToManyMatchingRecipes
     */
    public static FermentBarrelRecipe findFermentRecipeByResult(World world, FluidStack fluidStack) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(world, GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE);
        List<FermentBarrelRecipe> matchingRecipes = new ArrayList<>();

        for (IRecipe<?> recipe : recipes) {
            FermentBarrelRecipe fermentBarrelRecipe = (FermentBarrelRecipe) recipe;
            if (fermentBarrelRecipe.matches(fluidStack)) {
                matchingRecipes.add(fermentBarrelRecipe);
            }
        }
        return matchingRecipes.size() > 0 ? matchingRecipes.get(0) : null;
    }

    public static List<FermentBarrelRecipe> findFermentRecipesByResult(World world, FluidStack fluidStack) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(world, GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE);
        List<FermentBarrelRecipe> matchingRecipes = new ArrayList<>();

        for (IRecipe<?> recipe : recipes) {
            FermentBarrelRecipe fermentBarrelRecipe = (FermentBarrelRecipe) recipe;
            if (fermentBarrelRecipe.matches(fluidStack)) {
                matchingRecipes.add(fermentBarrelRecipe);
            }
        }

        return matchingRecipes;
    }

    public static List<FermentBarrelRecipe> findFermentRecipes() {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(Minecraft.getInstance().world, GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE);
        List<FermentBarrelRecipe> matchingRecipes = new ArrayList<>();

        for (IRecipe<?> recipe : recipes) {
            if (recipe instanceof FermentBarrelRecipe) matchingRecipes.add((FermentBarrelRecipe) recipe);
        }

        return matchingRecipes;
    }

    public static Set<IRecipe<?>> findRecipesByType(World world, IRecipeType<?> recipeType) {
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(recipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> recipeType) {


        


        World world = Minecraft.getInstance().world;
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(recipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    public static class ToManyMatchingRecipes extends Exception {

        public ToManyMatchingRecipes(String message) {
            super(message);
        }
    }

    public static class InvalidRecipeFile extends Exception {
        public InvalidRecipeFile(String message) {
            super(message);
        }
    }

}

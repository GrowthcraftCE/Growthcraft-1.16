package growthcraft.cellar.compat.jei;

import growthcraft.cellar.compat.jei.category.*;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.util.RecipeUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class GrowthcraftCellarJeiPlugin implements IModPlugin {

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        IModPlugin.super.registerIngredients(registry);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        // Brew Kettle Recipes
        registry.addRecipes(RecipeUtils.findRecipesByType(Minecraft.getInstance().world,
                GrowthcraftCellarRecipes.BREW_KETTLE_RECIPE_TYPE), BrewKettleRecipeCategory.ID);
        // Culture Jar Recipes
        registry.addRecipes(RecipeUtils.findRecipesByType(Minecraft.getInstance().world,
                GrowthcraftCellarRecipes.CULTURE_JAR_RECIPE_TYPE), CultureJarRecipeCategory.ID);
        // Ferment Barrel Recipes
        registry.addRecipes(RecipeUtils.findRecipesByType(Minecraft.getInstance().world,
                GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE), FermentBarrelRecipeCategory.ID);
        // Fruit Press Recipes
        registry.addRecipes(RecipeUtils.findRecipesByType(Minecraft.getInstance().world,
                GrowthcraftCellarRecipes.FRUIT_PRESS_RECIPE_TYPE), FruitPressRecipeCategory.ID);
        // Roaster Recipes
        registry.addRecipes(RecipeUtils.findRecipesByType(Minecraft.getInstance().world,
                GrowthcraftCellarRecipes.ROASTER_RECIPE_TYPE), RoasterRecipeCategory.ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        // Register the recipe categories with JEI
        registry.addRecipeCategories(
                new BrewKettleRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new FermentBarrelRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new FruitPressRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new CultureJarRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new RoasterRecipeCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "jei");
    }

}

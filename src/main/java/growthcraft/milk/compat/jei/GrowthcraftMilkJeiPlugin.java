package growthcraft.milk.compat.jei;

import growthcraft.lib.util.RecipeUtils;
import growthcraft.milk.compat.jei.category.CheesePressRecipeCategory;
import growthcraft.milk.compat.jei.category.ChurnRecipeCategory;
import growthcraft.milk.compat.jei.category.MixingVatRecipeCategory;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.shared.Reference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
public class GrowthcraftMilkJeiPlugin implements IModPlugin {

    @Override
    @ParametersAreNonnullByDefault
    public void registerIngredients(IModIngredientRegistration registry) {
        IModPlugin.super.registerIngredients(registry);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        registry.addRecipes(RecipeUtils.findRecipesByType(
                GrowthcraftMilkRecipes.CHURN_RECIPE_TYPE), ChurnRecipeCategory.ID);
        registry.addRecipes(RecipeUtils.findRecipesByType(
                GrowthcraftMilkRecipes.CHEESE_PRESS_RECIPE_TYPE), CheesePressRecipeCategory.ID);
        registry.addRecipes(RecipeUtils.findRecipesByType(
                GrowthcraftMilkRecipes.MIXING_VAT_ITEM_RECIPE_TYPE), MixingVatRecipeCategory.ID);
        registry.addRecipes(RecipeUtils.findRecipesByType(
                GrowthcraftMilkRecipes.MIXING_VAT_FLUID_RECIPE_TYPE), MixingVatRecipeCategory.ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        // Register the recipe categories with JEI
        registry.addRecipeCategories(
                new ChurnRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new CheesePressRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
                new MixingVatRecipeCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "jei");
    }

}

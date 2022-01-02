package growthcraft.milk.compat.jei;

import growthcraft.cellar.shared.Reference;
import growthcraft.lib.util.RecipeUtils;
import growthcraft.milk.compat.jei.category.ChurnRecipeCategory;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class GrowthcraftMilkJeiPlugin implements IModPlugin {

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        IModPlugin.super.registerIngredients(registry);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        // Brew Kettle Recipes
        registry.addRecipes(RecipeUtils.findRecipesByType(Minecraft.getInstance().world,
                GrowthcraftMilkRecipes.CHURN_RECIPE_TYPE), ChurnRecipeCategory.ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        // Register the recipe categories with JEI
        registry.addRecipeCategories(
                new ChurnRecipeCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "jei");
    }

}
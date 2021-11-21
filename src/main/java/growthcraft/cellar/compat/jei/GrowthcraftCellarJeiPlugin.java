package growthcraft.cellar.compat.jei;

import growthcraft.cellar.compat.jei.category.RoasterRecipeCategory;
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
    public void registerIngredients(IModIngredientRegistration registration) {
        IModPlugin.super.registerIngredients(registration);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(RecipeUtils.findRecipesByType(Minecraft.getInstance().world,
                GrowthcraftCellarRecipes.ROASTER_RECIPE_TYPE), RoasterRecipeCategory.ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new RoasterRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "main");
    }

}

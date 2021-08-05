package growthcraft.cellar.compat.jei;

import growthcraft.cellar.common.recipe.BrewKettleRecipe;
import growthcraft.cellar.shared.Reference;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class BrewKettleRecipeCategory implements IRecipeCategory<BrewKettleRecipe> {

    private final IDrawableStatic background;

    public BrewKettleRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/brew_kettle.png");
        background = guiHelper.createDrawable(location, 0, 0, 176, 166);
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(Reference.MODID, "brew_kettle_recipe");
    }

    @Override
    public Class<? extends BrewKettleRecipe> getRecipeClass() {
        return BrewKettleRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Brew Kettle";
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setIngredients(BrewKettleRecipe brewKettleRecipe, IIngredients iIngredients) {

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, BrewKettleRecipe brewKettleRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup guiItemStackGroup = iRecipeLayout.getItemStacks();
        // Input ItemStack slot
        guiItemStackGroup.init(0, true, 24, 7);
        // Output FluidStank slot
        guiItemStackGroup.init(1, false, 132, 7);

        //List<ItemStack> inputItemStacks = iIngredients.getInputs()
    }
}

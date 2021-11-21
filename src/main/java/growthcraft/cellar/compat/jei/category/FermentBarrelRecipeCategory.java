package growthcraft.cellar.compat.jei.category;

import growthcraft.cellar.common.recipe.FermentBarrelRecipe;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.TextureHelper;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class FermentBarrelRecipeCategory implements IRecipeCategory<FermentBarrelRecipe> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.FERMENT_BARREL);

    private final IDrawable background;
    private final IDrawable icon;

    public FermentBarrelRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(
                TEXTURE, 0, 0, 176, 166
        ).build();
        icon = guiHelper.createDrawableIngredient(new ItemStack(GrowthcraftCellarBlocks.barrel_ferment_oak.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(Reference.MODID, UnlocalizedName.FERMENT_BARREL);
    }

    @Override
    public Class<? extends FermentBarrelRecipe> getRecipeClass() {
        return FermentBarrelRecipe.class;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(FermentBarrelRecipe fermentBarrelRecipe, IIngredients iIngredients) {

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, FermentBarrelRecipe fermentBarrelRecipe, IIngredients iIngredients) {

    }
}

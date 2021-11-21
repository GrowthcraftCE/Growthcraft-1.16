package growthcraft.cellar.compat.jei.category;

import growthcraft.cellar.common.recipe.CultureJarRecipe;
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

public class CultureJarRecipeCategory implements IRecipeCategory<CultureJarRecipe> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.FERMENT_BARREL);

    private final IDrawable background;
    private final IDrawable icon;

    public CultureJarRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(
                TEXTURE, 0, 0, 176, 166
        ).build();
        icon = guiHelper.createDrawableIngredient(new ItemStack(GrowthcraftCellarBlocks.culture_jar.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(Reference.MODID, UnlocalizedName.CULTURE_JAR);
    }

    @Override
    public Class<? extends CultureJarRecipe> getRecipeClass() {
        return CultureJarRecipe.class;
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
    public void setIngredients(CultureJarRecipe fermentBarrelRecipe, IIngredients iIngredients) {

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, CultureJarRecipe fermentBarrelRecipe, IIngredients iIngredients) {

    }
}

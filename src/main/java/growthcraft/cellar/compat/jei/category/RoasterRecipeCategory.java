package growthcraft.cellar.compat.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.recipe.RoasterRecipe;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.TextureHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoasterRecipeCategory implements IRecipeCategory<RoasterRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.ROASTER);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.ROASTER);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayHeated;
    private final String title;

    public RoasterRecipeCategory(IGuiHelper guiHelper) {
        Block processingBlock = GrowthcraftCellarBlocks.roaster.get();

        background = guiHelper.drawableBuilder(
                TEXTURE, 10, 10, 160, 70
        ).build();
        // Heated Overlay
        overlayHeated = guiHelper.createDrawable(
                TEXTURE, 176, 28, 12, 13
        );

        title = I18n.format(processingBlock.getTranslationKey());
        icon = guiHelper.createDrawableIngredient(new ItemStack(processingBlock));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends RoasterRecipe> getRecipeClass() {
        return RoasterRecipe.class;
    }

    @Override
    public String getTitle() {
        return title;
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
    public void setIngredients(RoasterRecipe recipe, IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.asList(ingredient.getMatchingStacks()));
        }
        ingredients.setInputs(VanillaTypes.ITEM, list);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, RoasterRecipe recipe, IIngredients ingredients) {
        layout.getItemStacks().init(0, true, 42, 31);
        layout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        layout.getItemStacks().init(1, true, 69, 14);
        layout.getItemStacks().set(1, ingredients.getInputs(VanillaTypes.ITEM).get(1));

        layout.getItemStacks().init(2, true, 95, 31);
        layout.getItemStacks().set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }

    @Override
    public void draw(RoasterRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        try {
            overlayHeated.draw(matrixStack, 71, 47);
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Failure to draw heat texture for Roaster recipe.");
        }
    }

}

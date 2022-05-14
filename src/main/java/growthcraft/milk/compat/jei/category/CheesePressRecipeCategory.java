package growthcraft.milk.compat.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import growthcraft.lib.util.TextureHelper;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.common.recipe.CheesePressRecipe;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
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
import net.minecraft.util.ResourceLocation;

public class CheesePressRecipeCategory implements IRecipeCategory<CheesePressRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.CHEESE_PRESS);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.CHEESE_PRESS);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayTransferArrow;
    private final String title;

    public CheesePressRecipeCategory(IGuiHelper guiHelper) {
        Block block = GrowthcraftMilkBlocks.CHEESE_PRESS.get();

        // Background GUI
        background = guiHelper.drawableBuilder(
                TEXTURE, 10, 10, 160, 70
        ).build();

        // Transfer overlay
        overlayTransferArrow = guiHelper.createDrawable(
                TEXTURE, 51, 170, 12, 7
        );

        title = I18n.format(block.getTranslationKey());
        icon = guiHelper.createDrawableIngredient(new ItemStack(block));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends CheesePressRecipe> getRecipeClass() {
        return CheesePressRecipe.class;
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
    public void setIngredients(CheesePressRecipe recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, recipe.getRecipeInput());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, CheesePressRecipe recipe, IIngredients ingredients) {
        layout.getItemStacks().init(0, true, 42, 24);
        layout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        layout.getItemStacks().init(1, true, 95, 24);
        layout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

    }

    @Override
    public void draw(CheesePressRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        try {
            overlayTransferArrow.draw(matrixStack, 72, 29);
        } catch (Exception ex) {
            GrowthcraftMilk.LOGGER.error("Failure to draw progress texture for Cheese Press recipe.");
        }
    }
}

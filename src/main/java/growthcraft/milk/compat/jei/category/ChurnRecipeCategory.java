package growthcraft.milk.compat.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.lib.util.TextureHelper;
import growthcraft.milk.common.recipe.ChurnRecipe;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class ChurnRecipeCategory implements IRecipeCategory<ChurnRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.CHURN);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.CHURN);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayTransferArrow;
    private final IDrawableStatic overlayTank;
    private final String title;

    public ChurnRecipeCategory(IGuiHelper guiHelper) {
        Block processingBlock = GrowthcraftMilkBlocks.CHURN.get();

        // Background GUI
        background = guiHelper.drawableBuilder(
                TEXTURE, 10, 10, 160, 70
        ).build();

        // Transfer overlay
        overlayTransferArrow = guiHelper.createDrawable(
                TEXTURE, 51, 170, 12, 7
        );

        // Tank gauge overlay
        overlayTank = guiHelper.createDrawable(
                TEXTURE, 176, 64, 12, 13
        );

        title = I18n.format(processingBlock.getTranslationKey());
        icon = guiHelper.createDrawableIngredient(new ItemStack(processingBlock));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends ChurnRecipe> getRecipeClass() {
        return ChurnRecipe.class;
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
    public void setIngredients(ChurnRecipe recipe, IIngredients ingredients) {
        // Set FluidStack Ingredients
        List<FluidStack> fluidStackIngredients = new ArrayList<>();
        fluidStackIngredients.add(recipe.getInputFluidStack());
        ingredients.setInputs(VanillaTypes.FLUID, fluidStackIngredients);

        // Set Output ItemStack/FluidStack
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getOutputFluidStack());

        // Set Output ItemStack/FluidStack
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());

    }

    @Override
    public void setRecipe(IRecipeLayout layout, ChurnRecipe recipe, IIngredients ingredients) {
        // Set Input ItemStack GUI
        layout.getItemStacks().init(0, true, 83, 24);
        layout.getItemStacks().set(0, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

        // Set Input FluidStack GUI
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();
        guiFluidStacks.init(0, true, 55, 8, 8, 52, 1000, false, overlayTank);
        guiFluidStacks.set(0, ingredients.getInputs(VanillaTypes.FLUID).get(0));

        guiFluidStacks.init(1, true, 63, 8, 8, 52, 1000, false, overlayTank);
        guiFluidStacks.set(1, ingredients.getOutputs(VanillaTypes.FLUID).get(0));

    }

    @Override
    public void draw(ChurnRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        try {
            overlayTransferArrow.draw(matrixStack, 57, 29);
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Failure to draw heat texture for Culture Jar recipe.");
        }
    }

}

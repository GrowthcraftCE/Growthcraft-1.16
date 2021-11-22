package growthcraft.cellar.compat.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.recipe.CultureJarRecipe;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.TextureHelper;
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
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CultureJarRecipeCategory implements IRecipeCategory<CultureJarRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.CULTURE_JAR);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.CULTURE_JAR);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayHeated;
    private final IDrawableStatic overlayTank;
    private final String title;

    public CultureJarRecipeCategory(IGuiHelper guiHelper) {
        Block processingBlock = GrowthcraftCellarBlocks.culture_jar.get();

        // Background GUI
        background = guiHelper.drawableBuilder(
                TEXTURE, 10, 10, 160, 70
        ).build();

        // Heated overlay
        overlayHeated = guiHelper.createDrawable(
                TEXTURE, 176, 28, 12, 13
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
    public Class<? extends CultureJarRecipe> getRecipeClass() {
        return CultureJarRecipe.class;
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
    public void setIngredients(CultureJarRecipe recipe, IIngredients ingredients) {
        // Set ItemStack Ingredients
        List<ItemStack> itemStackIngredients = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            itemStackIngredients.addAll(Arrays.asList(ingredient.getMatchingStacks()));
        }
        ingredients.setInputs(VanillaTypes.ITEM, itemStackIngredients);

        // Set FluidStack Ingredients
        List<FluidStack> fluidStackIngredients = new ArrayList<>();
        fluidStackIngredients.add(recipe.getInputFluidStack());
        ingredients.setInputs(VanillaTypes.FLUID, fluidStackIngredients);

        // Set Output ItemStack/FluidStack
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());

    }

    @Override
    public void setRecipe(IRecipeLayout layout, CultureJarRecipe recipe, IIngredients ingredients) {
        // Set Input ItemStack GUI
        layout.getItemStacks().init(0, true, 83, 24);
        layout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        // Set Input FluidStack GUI
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();
        guiFluidStacks.init(0, true, 55, 8, 16, 52, 1000, false, overlayTank);
        guiFluidStacks.set(0, ingredients.getInputs(VanillaTypes.FLUID).get(0));

    }

    @Override
    public void draw(CultureJarRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        try {
            overlayHeated.draw(matrixStack, 86, 47);
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Failure to draw heat texture for Culture Jar recipe.");
        }
    }

}

package growthcraft.cellar.compat.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.recipe.BrewKettleRecipe;
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

public class BrewKettleRecipeCategory implements IRecipeCategory<BrewKettleRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.BREW_KETTLE);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.BREW_KETTLE);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayHeated;
    private final IDrawableStatic overlayTank;
    private final String title;

    public BrewKettleRecipeCategory(IGuiHelper guiHelper) {
        Block processingBlock = GrowthcraftCellarBlocks.brew_kettle.get();

        // Base Background
        background = guiHelper.drawableBuilder(
                TEXTURE, 10, 10, 160, 70
        ).build();

        // Tank gauge overlay
        overlayTank = guiHelper.createDrawable(
                TEXTURE, 176, 64, 12, 13
        );

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
    public Class<? extends BrewKettleRecipe> getRecipeClass() {
        return BrewKettleRecipe.class;
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
    public void setIngredients(BrewKettleRecipe recipe, IIngredients ingredients) {
        // Set ItemStack Ingredients
        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.asList(ingredient.getMatchingStacks()));
        }
        ingredients.setInputs(VanillaTypes.ITEM, list);

        // Set FluidStack Ingredients
        List<FluidStack> fluidStackIngredients = new ArrayList<>();
        fluidStackIngredients.add(recipe.getInputFluidStack());
        ingredients.setInputs(VanillaTypes.FLUID, fluidStackIngredients);

        // Set Output ItemStack and ByProduct ItemStack
        List<ItemStack> outputItemStacks = new ArrayList<>();
        outputItemStacks.add(recipe.getByProduct());
        ingredients.setOutputs(VanillaTypes.ITEM, outputItemStacks);

        ingredients.setOutput(VanillaTypes.FLUID, recipe.getOutputFluidStack());

    }

    @Override
    public void setRecipe(IRecipeLayout layout, BrewKettleRecipe recipe, IIngredients ingredients) {
        try {
            // Set Input ItemStack GUI
            layout.getItemStacks().init(0, true, 69, 24);
            layout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

            IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

            // Set Input FluidStack GUI
            if (!ingredients.getInputs(VanillaTypes.FLUID).get(0).get(0).isEmpty()) {
                guiFluidStacks.init(0, true, 36, 7, 16, 52, 4000, false, overlayTank);
                guiFluidStacks.set(0, ingredients.getInputs(VanillaTypes.FLUID).get(0));
            }

            // Set Output FluidStack GUI
            if (!ingredients.getOutputs(VanillaTypes.FLUID).get(0).get(0).isEmpty()) {
                guiFluidStacks.init(1, true, 104, 7, 16, 52, 4000, false, overlayTank);
                guiFluidStacks.set(1, ingredients.getOutputs(VanillaTypes.FLUID).get(0));
            }

            // set Output ItemStack ByProduct GUI
            layout.getItemStacks().init(1, true, 130, 6);
            layout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

            // TODO: Set the Brew Kettle Lid GUI if needed

        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Failure to set recipe mapping for Brew Kettle recipe.");
        }
    }

    @Override
    public void draw(BrewKettleRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        try {
            if (recipe.isHeatRequired()) overlayHeated.draw(matrixStack, 59, 44);
        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Failure to draw heat texture for Brew Kettle recipe.");
        }
    }
}

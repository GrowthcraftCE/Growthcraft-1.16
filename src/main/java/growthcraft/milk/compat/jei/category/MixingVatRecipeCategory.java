package growthcraft.milk.compat.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import growthcraft.lib.util.TextureHelper;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.common.recipe.MixingVatFluidRecipe;
import growthcraft.milk.common.recipe.MixingVatItemRecipe;
import growthcraft.milk.common.recipe.MixingVatRecipe;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

public class MixingVatRecipeCategory implements IRecipeCategory<MixingVatRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.MIXING_VAT);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.MIXING_VAT);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayHeated;
    private final String title;

    public MixingVatRecipeCategory(IGuiHelper guiHelper) {
        Block block = GrowthcraftMilkBlocks.MIXING_VAT.get();

        // Background GUI
        background = guiHelper.drawableBuilder(
                TEXTURE, 10, 10, 160, 70
        ).build();

        // Heated Overlay
        overlayHeated = guiHelper.createDrawable(
                TEXTURE, 176, 28, 12, 13
        );

        title = I18n.format(block.getTranslationKey());
        icon = guiHelper.createDrawableIngredient(new ItemStack(block));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends MixingVatRecipe> getRecipeClass() {
        return MixingVatRecipe.class;
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
    public void setIngredients(MixingVatRecipe recipe, IIngredients ingredients) {
        switch (recipe.getCategory()) {
            case FLUID:
                MixingVatFluidRecipe fluidRecipe = (MixingVatFluidRecipe) recipe;

                // Set FluidStack Inputs
                List<FluidStack> fluidStackInputs = new ArrayList<>();
                fluidStackInputs.add(fluidRecipe.getInputFluidStack());
                fluidStackInputs.add(fluidRecipe.getReagentFluidStack());
                ingredients.setInputs(VanillaTypes.FLUID, fluidStackInputs);

                // Set ItemStack Inputs
                ingredients.setInputs(VanillaTypes.ITEM, recipe.getIngredientList());

                List<FluidStack> fluidStackOutputs = new ArrayList<>();
                fluidStackOutputs.add(0, fluidRecipe.getOutputFluidStack());
                fluidStackOutputs.add(1, fluidRecipe.getWasteFluidStack());
                ingredients.setOutputs(VanillaTypes.FLUID, fluidStackOutputs);

                break;
            case ITEM:
                MixingVatItemRecipe itemRecipe = (MixingVatItemRecipe) recipe;

                // Set ItemStack Inputs
                ingredients.setInputs(VanillaTypes.ITEM, itemRecipe.getIngredientList());

                // Set FluidStack Inputs
                List<FluidStack> fluidStackIngredients = new ArrayList<>();
                fluidStackIngredients.add(recipe.getInputFluidStack());
                ingredients.setInputs(VanillaTypes.FLUID, fluidStackIngredients);

                // Set ItemStack Outputs
                List<ItemStack> itemStackOutputs = new ArrayList<>();
                itemStackOutputs.add(recipe.getRecipeOutput());
                ingredients.setOutputs(VanillaTypes.ITEM, itemStackOutputs);
                break;
            default:
                // Do nothing because something isn't right.
        }
    }

    @Override
    public void setRecipe(IRecipeLayout layout, MixingVatRecipe recipe, IIngredients ingredients) {
        IGuiFluidStackGroup guiFluidStackGroup = layout.getFluidStacks();
        IGuiItemStackGroup guiItemStackGroup = layout.getItemStacks();

        switch (recipe.getCategory()) {
            case FLUID:
                MixingVatFluidRecipe fluidRecipe = (MixingVatFluidRecipe) recipe;

                // Set FluidStack Input
                guiFluidStackGroup.init(0, true, 39, 22, 16, 38, 4000, false, null);
                guiFluidStackGroup.set(0, ingredients.getInputs(VanillaTypes.FLUID).get(0).get(0));

                // Set FluidStack Reagent
                guiFluidStackGroup.init(1, true, 39, 8, 16, 10, 1000, false, null);
                guiFluidStackGroup.set(1, ingredients.getInputs(VanillaTypes.FLUID).get(1).get(0));

                // Set ItemStack Ingredients
                guiItemStackGroup.init(0, true, 60, 7);
                guiItemStackGroup.init(1, true, 60, 24);
                guiItemStackGroup.init(2, true, 60, 40);
                for (int i = 0; i < ingredients.getInputs(VanillaTypes.ITEM).size(); i++) {
                    guiItemStackGroup.set(i, ingredients.getInputs(VanillaTypes.ITEM).get(i));
                }

                // Set FluidStack Result
                guiFluidStackGroup.init(2, false, 114, 30, 16, 30, 4000, false, null);
                guiFluidStackGroup.set(2, ingredients.getOutputs(VanillaTypes.FLUID).get(0));

                // Set FluidStack Waste
                guiFluidStackGroup.init(3, false, 114, 8, 16, 16, 1000, false, null);
                guiFluidStackGroup.set(3, ingredients.getOutputs(VanillaTypes.FLUID).get(1));

                break;
            case ITEM:
                GrowthcraftMilk.LOGGER.log(Level.WARN,
                        "<MixingVatRecipeCategory> Setting Ingredients for {} which is a {}",
                        recipe.getId(), "item");

                MixingVatItemRecipe itemRecipe = (MixingVatItemRecipe) recipe;

                // Set FluidStack Input
                guiFluidStackGroup.init(0, true, 39, 22, 16, 38, 4000, false, null);
                guiFluidStackGroup.set(0, ingredients.getInputs(VanillaTypes.FLUID).get(0).get(0));

                // Set ItemStack Ingredients
                guiItemStackGroup.init(0, true, 60, 7);
                guiItemStackGroup.init(1, true, 60, 25);
                guiItemStackGroup.init(2, true, 60, 43);
                for (int i = 0; i < ingredients.getInputs(VanillaTypes.ITEM).size(); i++) {
                    guiItemStackGroup.set(i, ingredients.getInputs(VanillaTypes.ITEM).get(i));
                }

                // Set ItemStack Result Item
                guiItemStackGroup.init(3, false, 113, 7);
                guiItemStackGroup.set(3, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

                break;
            default:
                // Do nothing because something isn't right.
        }
    }

    @Override
    public void draw(MixingVatRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        try {
            overlayHeated.draw(matrixStack, 89, 47);
        } catch (Exception ex) {
            GrowthcraftMilk.LOGGER.log(Level.ERROR,
                    "Failure to draw heat texture for Mxing Vat recipe.");
        }
    }
}

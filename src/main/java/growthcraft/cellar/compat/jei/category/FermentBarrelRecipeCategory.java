package growthcraft.cellar.compat.jei.category;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.recipe.FermentBarrelRecipe;
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
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FermentBarrelRecipeCategory implements IRecipeCategory<FermentBarrelRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.FERMENT_BARREL);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.FERMENT_BARREL);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayTank;
    private final String title;

    public FermentBarrelRecipeCategory(IGuiHelper guiHelper) {
        Block processingBlock = GrowthcraftCellarBlocks.barrel_ferment_oak.get();

        // Background GUI
        background = guiHelper.drawableBuilder(
                TEXTURE, 10, 10, 160, 70
        ).build();

        // Tank gauge overlay
        overlayTank = guiHelper.createDrawable(
                TEXTURE, 176, 64, 12, 13
        );

        title = I18n.format(processingBlock.getTranslationKey());
        icon = guiHelper.createDrawableIngredient(new ItemStack(processingBlock));
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
    public void setIngredients(FermentBarrelRecipe recipe, IIngredients ingredients) {
        // Set ItemStack Ingredients
        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.asList(ingredient.getMatchingStacks()));
        }
        ingredients.setInputs(VanillaTypes.ITEM, list);

        // Set FluidStack Ingredients
        List<FluidStack> fluidStackIngredients = new ArrayList<>();
        fluidStackIngredients.add(recipe.getIngredientFluidStack());
        ingredients.setInputs(VanillaTypes.FLUID, fluidStackIngredients);

        // Set Output ItemStack/FluidStack
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getResultingFluid());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, FermentBarrelRecipe recipe, IIngredients ingredients) {
        try {

        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Failure to set recipe mapping for Ferment Barrel recipe.");
        }
    }
}

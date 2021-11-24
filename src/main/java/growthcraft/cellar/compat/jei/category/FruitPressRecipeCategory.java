package growthcraft.cellar.compat.jei.category;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.recipe.FruitPressRecipe;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FruitPressRecipeCategory implements IRecipeCategory<FruitPressRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, UnlocalizedName.FRUIT_PRESS);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.FRUIT_PRESS);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic overlayTank;
    private final String title;

    public FruitPressRecipeCategory(IGuiHelper guiHelper) {
        Block processingBlock = GrowthcraftCellarBlocks.FRUIT_PRESS.get();

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
        return ID;
    }

    @Override
    public Class<? extends FruitPressRecipe> getRecipeClass() {
        return FruitPressRecipe.class;
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
    public void setIngredients(FruitPressRecipe recipe, IIngredients ingredients) {
        // Set ItemStack Ingredients
        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.asList(ingredient.getMatchingStacks()));
        }
        ingredients.setInputs(VanillaTypes.ITEM, list);

        // Set Output ItemStack and ByProduct ItemStack
        List<ItemStack> outputItemStacks = new ArrayList<>();
        outputItemStacks.add(recipe.getInputItemStack());
        outputItemStacks.add(recipe.getByProduct());
        ingredients.setOutputs(VanillaTypes.ITEM, outputItemStacks);

        ingredients.setOutput(VanillaTypes.FLUID, recipe.getOutputFluidStack());

    }

    @Override
    public void setRecipe(IRecipeLayout layout, FruitPressRecipe recipe, IIngredients ingredients) {
        try {
// Set Input ItemStack GUI
            layout.getItemStacks().init(0, true, 41, 42);
            layout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

            // Set Input FluidStack GUI
            IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();
            guiFluidStacks.init(0, true, 62, 7, 50, 52, 4000, false, overlayTank);
            guiFluidStacks.set(0, ingredients.getOutputs(VanillaTypes.FLUID).get(0));

        } catch (Exception ex) {
            GrowthcraftCellar.LOGGER.error("Failure to set recipe mapping for Fruit Press recipe.");
        }
    }
}

package growthcraft.milk.common.recipe;

import growthcraft.milk.init.GrowthcraftMilkRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class MixingVatItemRecipe extends MixingVatRecipe {
    private final ItemStack resultItemStack;
    private final ItemStack resultActivationTool;

    public MixingVatItemRecipe(ResourceLocation recipeId, MixingVatRecipeCategory category,
                               FluidStack inputFluidStack, List<ItemStack> ingredients, int processingTime,
                               ItemStack resultItemStack, ItemStack activationTool,
                               ItemStack resultActivationTool) {
        super(recipeId, category, inputFluidStack, ingredients, processingTime, activationTool);
        this.resultItemStack = resultItemStack;
        this.resultActivationTool = resultActivationTool;

    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftMilkRecipes.MIXING_VAT_ITEM_RECIPE_TYPE;
    }

    /**
     * Determine if the provided FluidStack and List<ItemStack> matches the recipes input
     * FluidStack and List<ItemStack> ingredients. If there is a non-match, this method will
     * fail fast and return false.
     *
     * @param testFluidStack  Input FluidStack to check.
     * @param testIngredients List of ItemStack ingredients to check.
     * @return Returns true by default.
     */
    public boolean matches(FluidStack testFluidStack, List<ItemStack> testIngredients) {
        if (testFluidStack.getFluid() != this.getInputFluidStack().getFluid()
                && testFluidStack.getAmount() <= this.getInputFluidStack().getAmount()) {
            return false;
        }

        if (this.getIngredientList().size() == testIngredients.size()) {
            for (int i = 0; i < testIngredients.size(); i++) {
                if (!this.getIngredientList().contains(testIngredients.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    public ItemStack getResultItemStack() {
        return this.resultItemStack;
    }

    public ItemStack getResultActivationTool() {
        return this.resultActivationTool;
    }

}

package growthcraft.milk.common.recipe;

import growthcraft.milk.init.GrowthcraftMilkRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class MixingVatFluidRecipe extends MixingVatRecipe {
    private final FluidStack reagentFluidStack;
    private final FluidStack outputFluidStack;
    private final FluidStack wasteFluidStack;

    public MixingVatFluidRecipe(ResourceLocation recipeId, MixingVatRecipeCategory category,
                                FluidStack inputFluidStack, FluidStack reagentFluidStack,
                                List<ItemStack> ingredients, int processingTime,
                                FluidStack outputFluidStack, FluidStack wasteFluidStack,
                                ItemStack activationTool) {
        super(recipeId, category, inputFluidStack, ingredients, processingTime, activationTool);
        this.reagentFluidStack = reagentFluidStack;
        this.outputFluidStack = outputFluidStack;
        this.wasteFluidStack = wasteFluidStack;

    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftMilkRecipes.MIXING_VAT_FLUID_RECIPE_TYPE;
    }

    public boolean matches(FluidStack testBaseFluidStack, FluidStack testReagentFluidStack,
                           List<ItemStack> testIngredients) {
        if (testBaseFluidStack.getFluid() != this.getInputFluidStack().getFluid()
                && testBaseFluidStack.getAmount() <= this.getInputFluidStack().getAmount()) {
            return false;
        }

        if (testReagentFluidStack.getFluid() != this.getReagentFluidStack().getFluid()
                && testReagentFluidStack.getAmount() <= this.getReagentFluidStack().getAmount()) {
            return false;
        }

        if (this.getIngredientList().size() == testIngredients.size()) {
            for (int i = 0; i < testIngredients.size(); i++) {
                if (!this.getIngredientItems().contains(testIngredients.get(i).getItem())) {
                    return false;
                }
            }
        }

        return true;
    }

    public FluidStack getReagentFluidStack() {
        return this.reagentFluidStack;
    }

    public FluidStack getOutputFluidStack() {
        return this.outputFluidStack;
    }

    public FluidStack getWasteFluidStack() {
        return this.wasteFluidStack;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.getOutputFluidStack().getFluid().getFilledBucket().getDefaultInstance();
    }
}

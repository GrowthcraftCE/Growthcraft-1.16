package growthcraft.milk.common.recipe;

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
        // TODO: Set to sub-class specific recipe type.
        return super.getType();
    }

    public boolean matches(FluidStack testInputFluidStack, FluidStack testReagentFluidStack,
                           List<ItemStack> testIngredients) {
        // TODO: Complete matches method for MixingVatFluidRecipe
        return false;
    }
}

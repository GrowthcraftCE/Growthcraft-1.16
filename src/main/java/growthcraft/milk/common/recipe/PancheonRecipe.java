package growthcraft.milk.common.recipe;

import growthcraft.milk.init.GrowthcraftMilkRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class PancheonRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final FluidStack outputFluidStack1;
    private final FluidStack outputFluidStack2;
    private final int processingTime;

    public PancheonRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, FluidStack outputFluidStack1, FluidStack outputFluidStack2, int processingTime) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.outputFluidStack1 = outputFluidStack1;
        this.outputFluidStack2 = outputFluidStack2;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    /**
     * Does this recipe match the give input FluidStack?
     *
     * @param fluidStack FluidStack to compare.
     * @return Boolean is matching or not.
     */
    public boolean matches(FluidStack fluidStack) {
        return this.inputFluidStack.getFluid() == fluidStack.getFluid();
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.getOutputFluidStack(0).getFluid().getFilledBucket().getDefaultInstance();

    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GrowthcraftMilkRecipes.PANCHEON_RECIPE_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftMilkRecipes.PANCHEON_RECIPE_TYPE;
    }

    public FluidStack getInputFluidStack() {
        return this.inputFluidStack;
    }

    public FluidStack getOutputFluidStack(int i) {
        switch (i) {
            case 1:
                return this.outputFluidStack2;
            default:
                return this.outputFluidStack1;
        }
    }

    public List<FluidStack> getOutputFluidStacks() {
        List<FluidStack> outputFluidStacks = new ArrayList<>();
        outputFluidStacks.add(outputFluidStack1);
        outputFluidStacks.add(outputFluidStack2);
        return outputFluidStacks;
    }

    public int getProcessingTime() {
        return processingTime;
    }

}

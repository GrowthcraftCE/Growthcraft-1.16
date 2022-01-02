package growthcraft.milk.common.recipe;

import growthcraft.milk.init.GrowthcraftMilkRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class ChurnRecipe implements IRecipe<IInventory> {

    private final FluidStack inputFluidStack;
    private final FluidStack outputFluidStack;
    private final ItemStack resultItemStack;
    private final int byProductChance;
    private final int plunges;
    private final ResourceLocation recipeId;

    public ChurnRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, FluidStack outputFluidStack, ItemStack result, int byProductChance, int plunges) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.outputFluidStack = outputFluidStack;
        this.resultItemStack = result;
        this.plunges = plunges;
        this.byProductChance = byProductChance;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(FluidStack fluidStack) {
        return this.inputFluidStack.getFluid() == fluidStack.getFluid()
                && this.inputFluidStack.getAmount() <= fluidStack.getAmount();
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }

    public FluidStack getOutputFluidStack() {
        return this.outputFluidStack;
    }

    public ItemStack getResultItemStack() {
        return this.resultItemStack;
    }

    public int getPlungesNeeded() {
        return this.plunges;
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
        return this.getResultItemStack();
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GrowthcraftMilkRecipes.CHURN_RECIPE_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftMilkRecipes.CHURN_RECIPE_TYPE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(this.resultItemStack));
    }

    public int getByProductChance() {
        return this.byProductChance;
    }
}

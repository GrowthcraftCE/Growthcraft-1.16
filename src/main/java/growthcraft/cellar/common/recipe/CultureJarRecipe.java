package growthcraft.cellar.common.recipe;

import growthcraft.cellar.init.GrowthcraftCellarRecipes;
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

public class CultureJarRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final ItemStack inputItem;
    private final int processingTime;
    private final boolean requiresHeatSource;

    public CultureJarRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, int processingTime, boolean requiresHeatSource) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.inputItem = inputItem;
        this.processingTime = processingTime;
        this.requiresHeatSource = requiresHeatSource;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack, FluidStack fluidStack, boolean requiresHeatSource) {
        return this.inputItem.getItem() == itemStack.getItem() && this.inputItem.getCount() <= itemStack.getCount()
                && this.inputFluidStack.getFluid() == fluidStack.getFluid() && this.inputFluidStack.getAmount() <= fluidStack.getAmount()
                && this.requiresHeatSource == requiresHeatSource;
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }

    public ItemStack getInputItem() {
        return inputItem;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public boolean isRequiresHeatSource() {
        return requiresHeatSource;
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
        return inputItem;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GrowthcraftCellarRecipes.CULTURE_JAR_RECIPE_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftCellarRecipes.CULTURE_JAR_RECIPE_TYPE;
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(this.inputItem));
    }
}

package growthcraft.cellar.common.recipe;

import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

public class FermentBarrelRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final FluidStack outputFluidStack;
    private final ItemStack inputItemStack;
    private final int processingTime;
    private final Color color;
    private final ItemStack potionItemStack;

    public FermentBarrelRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItemStack, FluidStack outputFluidStack, int processingTime, ItemStack bottle, Color color) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.outputFluidStack = outputFluidStack;
        this.inputItemStack = inputItemStack;
        this.processingTime = processingTime;
        this.potionItemStack = bottle;
        this.color = color;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack inputItemStack, FluidStack inputFluidStack) {
        boolean inputItemMatches = this.inputItemStack.getItem() == inputItemStack.getItem()
                && this.inputItemStack.getCount() <= inputItemStack.getCount();

        boolean inputFluidMatches = this.inputFluidStack.getFluid() == inputFluidStack.getFluid()
                && this.inputFluidStack.getAmount() <= inputFluidStack.getAmount();

        return inputItemMatches && inputFluidMatches;
    }

    public boolean matches(FluidStack fluidStack) {
        return this.outputFluidStack.getFluid() == fluidStack.getFluid();
    }

    /**
     * Check if input and output matches the recipes.
     *
     * @param inputItemStack
     * @param inputFluidStack
     * @param outputFluidStack
     * @return boolean inputs match the recipe
     */
    public boolean matches(ItemStack inputItemStack, FluidStack inputFluidStack, FluidStack outputFluidStack) {
        boolean inputMatches = matches(inputItemStack, inputFluidStack);
        boolean outputMatches = this.outputFluidStack.getFluid() == outputFluidStack.getFluid();
        return inputMatches && outputMatches;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.outputFluidStack.getFluid().getFilledBucket().getDefaultInstance();
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.outputFluidStack.getFluid().getFilledBucket().getDefaultInstance();
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GrowthcraftCellarRecipes.FERMENT_BARREL_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE;
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }

    public FluidStack getIngredientFluidStack() {
        return this.inputFluidStack;
    }

    public ItemStack getIngredientItemStack() {
        return this.inputItemStack;
    }

    public FluidStack getResultingFluid() {
        return this.outputFluidStack;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public boolean hasEffects() {
        return this.potionItemStack.getItem().hasEffect(potionItemStack);
    }

    public Item getBottle() {
        return this.potionItemStack.getItem();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(inputItemStack));
    }

    public ItemStack getBottleItemStack() {
        return this.potionItemStack;
    }

}

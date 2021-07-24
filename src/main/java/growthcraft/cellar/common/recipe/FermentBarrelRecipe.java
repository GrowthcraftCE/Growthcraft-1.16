package growthcraft.cellar.common.recipe;

import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import growthcraft.cellar.lib.effect.CellarPotionEffect;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class FermentBarrelRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final FluidStack outputFluidStack;
    private final ItemStack inputItem;
    private final List<CellarPotionEffect> potionEffects;
    private final int processingTime;

    public FermentBarrelRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, FluidStack outputFluidStack, int processingTime, List<CellarPotionEffect> potionEffects) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.outputFluidStack = outputFluidStack;
        this.inputItem = inputItem;
        this.processingTime = processingTime;
        this.potionEffects = potionEffects;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack inputItemStack, FluidStack inputFluidStack) {
        boolean inputItemMatches = this.inputItem.getItem() == inputItemStack.getItem()
                && this.inputItem.getCount() <= inputItemStack.getCount();

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
        return this.inputItem;
    }

    public FluidStack getResultingFluid() {
        return this.outputFluidStack;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public boolean hasEffects() {
        return potionEffects != null;
    }

    public List<CellarPotionEffect> getEffects() {
        return this.potionEffects;
    }
}

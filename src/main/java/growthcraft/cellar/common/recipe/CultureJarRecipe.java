package growthcraft.cellar.common.recipe;

import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class CultureJarRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final ItemStack inputItem;
    private final int processingTime;

    public CultureJarRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, int processingTime) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.inputItem = inputItem;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack, FluidStack fluidStack) {
        return this.inputItem.getItem() == itemStack.getItem() && this.inputItem.getCount() <= itemStack.getCount()
                && this.inputFluidStack.getFluid() == fluidStack.getFluid() && this.inputFluidStack.getAmount() <= fluidStack.getAmount();
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
        return GrowthcraftCellarRecipes.BREW_KETTLE_RECIPE_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return new CultureJarRecipeType();
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }

}

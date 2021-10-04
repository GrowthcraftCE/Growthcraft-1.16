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

public class FruitPressRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack outputFluidStack;
    private final ItemStack inputItemStack;
    private final ItemStack itemByProduct;

    private final int processingTime;

    public FruitPressRecipe(
            ResourceLocation recipeId, ItemStack inputItemStack, FluidStack outputFluidStack,
            ItemStack itemByProduct, int processingTime
    ) {
        this.recipeId = recipeId;
        this.inputItemStack = inputItemStack;
        this.outputFluidStack = outputFluidStack;
        this.itemByProduct = itemByProduct;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack inputItemStack) {
        return this.inputItemStack.getItem() == inputItemStack.getItem();
    }

    public boolean matches(ItemStack inputItemStack, FluidStack outputFluidStack) {
        return this.inputItemStack == inputItemStack
                && this.outputFluidStack.getFluid() == outputFluidStack.getFluid();
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
        return GrowthcraftCellarRecipes.FRUIT_PRESS_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return new FruitPressRecipeType();
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public ItemStack getInputItemStack() {
        return inputItemStack;
    }

    public FluidStack getOutputFluidStack() {
        return outputFluidStack;
    }

    public ItemStack getItemByProduct() {
        return itemByProduct;
    }

    public FluidStack getResultingFluid() {
        return this.outputFluidStack;
    }
}

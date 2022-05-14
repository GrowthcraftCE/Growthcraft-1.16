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

public class CheesePressRecipe implements IRecipe<IInventory> {

    private final ItemStack inputItemStack;
    private final ItemStack outputItemStack;
    private final ItemStack sliceItemStack;
    private final int processingTime;
    private final ResourceLocation recipeId;

    public CheesePressRecipe(ResourceLocation recipeId, ItemStack input, ItemStack result, ItemStack slice, int ticks) {
        this.recipeId = recipeId;
        this.inputItemStack = input;
        this.outputItemStack = result;
        this.sliceItemStack = slice;
        this.processingTime = ticks;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack) {
        return this.inputItemStack.getItem() == itemStack.getItem()
                && this.inputItemStack.getCount() == itemStack.getCount();
    }

    public boolean matchesOutput(ItemStack itemStack) {
        return this.outputItemStack.getItem() == itemStack.getItem()
                && this.outputItemStack.getCount() == itemStack.getCount();
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
        return this.outputItemStack;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GrowthcraftMilkRecipes.CHEESE_PRESS_RECIPE_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftMilkRecipes.CHEESE_PRESS_RECIPE_TYPE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(this.inputItemStack));
    }

    public ItemStack getRecipeInput() {
        return this.inputItemStack;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public ItemStack getSliceItem() {
        return this.sliceItemStack.copy();
    }
}

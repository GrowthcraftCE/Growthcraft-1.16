package growthcraft.cellar.common.recipe;

import growthcraft.cellar.GrowthcraftCellar;
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

import java.util.Objects;

public class RoasterRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final ItemStack inputItemStack;
    private final ItemStack outputItemStack;
    private final ItemStack redstoneTimerItemStack;

    private final int processingTime;

    public RoasterRecipe(ResourceLocation recipeId, ItemStack inputItemStack, ItemStack outputItemStack, ItemStack redstoneTimerItemStack) {
        this.recipeId = recipeId;
        this.inputItemStack = inputItemStack;
        this.outputItemStack = outputItemStack;
        this.redstoneTimerItemStack = redstoneTimerItemStack;
        this.processingTime = (redstoneTimerItemStack.getCount() * 200) + 200;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack, ItemStack redstoneTimerItemStack) {
        boolean inputValid = this.inputItemStack.getItem() == itemStack.getItem() && this.inputItemStack.getCount() <= itemStack.getCount();
        boolean redstoneValid = this.redstoneTimerItemStack.getItem() == redstoneTimerItemStack.getItem() && this.redstoneTimerItemStack.getCount() == redstoneTimerItemStack.getCount();

        if (inputValid && redstoneValid) {
            if (this.outputItemStack.getCount() > 1) {
                this.outputItemStack.setCount(1);

                GrowthcraftCellar.LOGGER.info(
                        String.format("Recipe matches(%s): %s (%d)", this.recipeId.toString(), this.outputItemStack.getItem().getRegistryName(), this.outputItemStack.getCount())
                );
            }
        }
        return inputValid && redstoneValid;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(inputItemStack, redstoneTimerItemStack));
    }

    public ItemStack getInputItemStack() {
        Objects.requireNonNull(this.inputItemStack,
                String.format("Recipe input cannot be null! Check recipe (%s) json file.", recipeId));
        return this.inputItemStack;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        Objects.requireNonNull(this.outputItemStack,
                String.format("Recipe output cannot be null. Check recipe (%s) json file.", recipeId));
        return this.outputItemStack;
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
        return GrowthcraftCellarRecipes.ROASTER_RECIPE_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return new RoasterRecipeType();
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public ItemStack getOutputItem() {
        return this.outputItemStack;
    }

    public ResourceLocation getRecipeID() {
        return this.recipeId;
    }
}

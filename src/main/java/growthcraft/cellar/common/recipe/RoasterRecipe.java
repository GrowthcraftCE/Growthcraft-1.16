package growthcraft.cellar.common.recipe;

import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
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
        this.processingTime = redstoneTimerItemStack.getCount() * 40;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack, ItemStack redstoneTimerItemStack) {
        return this.inputItemStack.getItem() == itemStack.getItem() && this.inputItemStack.getCount() <= itemStack.getCount()
                && this.redstoneTimerItemStack.getItem() == redstoneTimerItemStack.getItem() && this.redstoneTimerItemStack.getCount() == redstoneTimerItemStack.getCount();
    }

    public ItemStack getInputItemStack() {
        Objects.requireNonNull(inputItemStack,
                String.format("Recipe input cannot be null! Check recipe (%s) json file.", recipeId));
        return inputItemStack;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        Objects.requireNonNull(outputItemStack,
                String.format("Recipe output cannot be null. Check recipe (%s) json file.", recipeId));
        return outputItemStack;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return outputItemStack;
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
}

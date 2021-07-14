package growthcraft.cellar.common.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RoasterRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final ItemStack inputItemStack;
    private final ItemStack outputItemStack;
    private final int processingTime;

    public RoasterRecipe(ResourceLocation recipeId, ItemStack inputItemStack, ItemStack outputItemStack, int processingTime) {
        this.recipeId = recipeId;
        this.inputItemStack = inputItemStack;
        this.outputItemStack = outputItemStack;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
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
        return null;
    }

    @Override
    public IRecipeType<?> getType() {
        return null;
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }
}

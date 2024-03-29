package growthcraft.milk.common.recipe;

import growthcraft.milk.init.GrowthcraftMilkRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class MixingVatRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final MixingVatRecipeCategory category;
    private final ItemStack activationTool;
    private final List<ItemStack> ingredients;
    private final FluidStack inputFluidStack;
    private final int processingTime;

    public MixingVatRecipe(ResourceLocation recipeId, MixingVatRecipeCategory category,
                           FluidStack inputFluidStack, List<ItemStack> ingredients,
                           int processingTime, ItemStack activationTool) {
        this.recipeId = recipeId;
        this.category = category;
        this.inputFluidStack = inputFluidStack;
        this.ingredients = ingredients;
        this.processingTime = processingTime;
        this.activationTool = activationTool;

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
        return new ItemStack(Items.AIR);
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GrowthcraftMilkRecipes.MIXING_VAT_RECIPE_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return GrowthcraftMilkRecipes.MIXING_VAT_RECIPE_TYPE;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        // Matches is delegated to the sub-class.
        return false;
    }

    public MixingVatRecipeCategory getCategory() {
        return this.category;
    }

    public ItemStack getActivationTool() {
        return this.activationTool;
    }

    public boolean activationToolValid(ItemStack tool) {
        return this.activationTool.getItem() == tool.getItem();
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }

    public List<ItemStack> getIngredientList() {
        return ingredients;
    }

    public List<Item> getIngredientItems() {
        List<Item> ingredientItems = new ArrayList<>();
        this.getIngredientList().forEach(
                itemStack -> ingredientItems.add(itemStack.getItem())
        );
        return ingredientItems;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public enum MixingVatRecipeCategory {
        FLUID("fluid"), ITEM("item");

        private final String value;

        MixingVatRecipeCategory(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        public static MixingVatRecipeCategory with(String value) {
            switch (value) {
                case "fluid":
                    return FLUID;
                case "item":
                    return ITEM;
                default:
                    return null;
            }
        }

    }
}

package growthcraft.cellar.common.recipe;

import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import mcp.MethodsReturnNonnullByDefault;
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
import net.minecraftforge.fluids.FluidUtil;

@MethodsReturnNonnullByDefault
public class BrewKettleRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final ItemStack inputItemStack;
    private final FluidStack outputFluidStack;
    private final ItemStack byProduct ;
    private final boolean requiresLid;
    private final boolean requiresHeat;
    private final int byProductChance;

    public BrewKettleRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, FluidStack outputFluidStack, ItemStack byProduct, boolean requiresLid, boolean requiresHeat, int byProductChance) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.inputItemStack = inputItem;
        this.outputFluidStack = outputFluidStack;
        this.byProduct = byProduct;
        this.requiresLid = requiresLid;
        this.requiresHeat = requiresHeat;
        this.byProductChance = Math.min(byProductChance, 100);
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack, FluidStack fluidStack, boolean needsLid, boolean needsHeat) {
        return this.inputItemStack.getItem() == itemStack.getItem() && this.inputItemStack.getCount() <= itemStack.getCount()
                && this.inputFluidStack.getFluid() == fluidStack.getFluid() && this.inputFluidStack.getAmount() <= fluidStack.getAmount()
                && this.requiresLid == needsLid && this.requiresHeat == needsHeat;
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }

    public FluidStack getOutputFluidStack() {
        return outputFluidStack;
    }

    public ItemStack getInputItemStack() {
        return inputItemStack;
    }

    public ItemStack getByProduct() {
        return byProduct;
    }

    public boolean getLidRequired() {
        return requiresLid;
    }

    public boolean isHeatRequired() {
        return this.requiresHeat;
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
        return FluidUtil.getFilledBucket(outputFluidStack);
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
        return GrowthcraftCellarRecipes.BREW_KETTLE_RECIPE_TYPE;
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(
                Ingredient.EMPTY,
                this.requiresLid ?
                        Ingredient.fromStacks(inputItemStack, new ItemStack(GrowthcraftCellarItems.brew_kettle_lid.get()))
                        : Ingredient.fromStacks(inputItemStack));
    }

    public int getByProductChance() {
        return this.byProductChance;
    }

}

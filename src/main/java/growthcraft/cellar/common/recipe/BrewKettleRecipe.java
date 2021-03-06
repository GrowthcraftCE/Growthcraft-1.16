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
import net.minecraftforge.fluids.FluidUtil;

public class BrewKettleRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final ItemStack inputItem;
    private final FluidStack outputFluidStack;
    private final ItemStack byProduct ;
    private final boolean requiresLid;

    public BrewKettleRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, FluidStack outputFluidStack, ItemStack byProduct, boolean requiresLid) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.inputItem = inputItem;
        this.outputFluidStack = outputFluidStack;
        this.byProduct = byProduct;
        this.requiresLid = requiresLid;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack, FluidStack fluidStack, boolean needsLid) {
        return this.inputItem.getItem() == itemStack.getItem() && this.inputItem.getCount() <= itemStack.getCount()
                && this.inputFluidStack.getFluid() == fluidStack.getFluid() && this.inputFluidStack.getAmount() <= fluidStack.getAmount()
                && this.requiresLid == needsLid;
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }

    public FluidStack getOutputFluidStack() {
        return outputFluidStack;
    }

    public ItemStack getInputItem() {
        return inputItem;
    }

    public ItemStack getByProduct() {
        return byProduct;
    }

    public boolean getLidRequired() {
        return requiresLid;
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
        return new BrewKettleRecipeType();
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }

}

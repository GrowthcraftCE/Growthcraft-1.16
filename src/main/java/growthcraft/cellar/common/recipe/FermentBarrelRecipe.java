package growthcraft.cellar.common.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class FermentBarrelRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final ItemStack inputItem;
    private final int processingTime;

    public FermentBarrelRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, int processingTime) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.inputItem = inputItem;
        this.processingTime = processingTime;
        // TODO[19]: Get potion effect from recipe
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    public boolean matches(ItemStack itemStack, FluidStack fluidStack) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        // TODO[19]: Return a fluid bucket of the resulting FluidStack
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        // TODO[19]: Return a fluid bucket of the resulting FluidStack
        return null;
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
        return new FermentBarrelRecipeType();
    }

    @Override
    public String getGroup() {
        return "growthcraft";
    }
}

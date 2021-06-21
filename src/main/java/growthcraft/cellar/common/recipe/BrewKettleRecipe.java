package growthcraft.cellar.common.recipe;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

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

        GrowthcraftCellar.LOGGER.warn("============================================================================");
        GrowthcraftCellar.LOGGER.warn(this.toString());
    }

    @Override
    public String toString() {
        return "BrewKettleRecipe{" +
                "recipeId=" + recipeId +
                ", inputFluidStack=" + inputFluidStack +
                ", inputItem=" + inputItem +
                ", outputFluidStack=" + outputFluidStack +
                ", byProduct=" + byProduct +
                ", requiresLid=" + requiresLid +
                '}';
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
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
        return null;
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
}

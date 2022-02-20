package growthcraft.milk.common.tileentity;

import growthcraft.lib.common.tank.handler.FluidTankHandler;
import growthcraft.lib.common.tank.handler.FluidTankOutputHandler;
import growthcraft.milk.common.recipe.MixingVatRecipe;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class VatTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int currentProcessingTime;
    private MixingVatRecipe currentRecipe;
    private ITextComponent customName;
    private FluidTankHandler inputFluidTankHandler;
    private int maxProcessingTime;
    private FluidTankOutputHandler outputFluidTankHandler;

    public VatTileEntity() {
        this(GrowthcraftMilkTileEntities.MIXING_VAT_TILE_ENTITY.get());
    }

    public VatTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    @Override
    public void tick() {

    }

    // TODO: getRecipe
    /*
    @Nullable
    @ParametersAreNonnullByDefault
    private MixingVatFluidRecipe getRecipe(FluidStack fluidStack) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(this.world, GrowthcraftMilkRecipes.MIXING_VAT_RECIPE_TYPE);
        for (IRecipe<?> recipe : recipes) {
            MixingVatFluidRecipe mixingVatRecipe = (MixingVatFluidRecipe) recipe;
            if (mixingVatRecipe.matches(fluidStack)) return mixingVatRecipe;
        }
        return null;
    }
    */

    @Override
    protected ITextComponent getDefaultName() {
        return this.getBlockState().getBlock().getTranslatedName();
    }

    @Override
    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    // TODO: Create GUI
    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        // Do nothing as there isn't an Item inventory.
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    public FluidTank getInputFluidTank(int tankId) {
        return null;
    }

    public FluidTank getOutputFluidTank(int tankId) {
        return null;
    }

    public ItemStack getResultActivationTool() {
        return this.currentRecipe.getActivationTool();
    }

}

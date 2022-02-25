package growthcraft.milk.common.tileentity;

import growthcraft.lib.common.tank.handler.FluidTankHandler;
import growthcraft.lib.common.tank.handler.FluidTankOutputHandler;
import growthcraft.lib.util.RecipeUtils;
import growthcraft.milk.common.recipe.MixingVatFluidRecipe;
import growthcraft.milk.common.recipe.MixingVatItemRecipe;
import growthcraft.milk.common.recipe.MixingVatRecipe;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MixingVatTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int currentProcessingTime;
    private MixingVatRecipe currentRecipe;
    private ITextComponent customName;
    private FluidTankHandler inputFluidTankHandler;
    private int maxProcessingTime;
    private FluidTankOutputHandler outputFluidTankHandler;

    private final int inputInventorySlots = 3;

    private final IItemHandlerModifiable items = createHandler();
    private final LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    private final NonNullList<ItemStack> inventoryItemStacks = NonNullList.withSize(4, ItemStack.EMPTY);

    public MixingVatTileEntity() {
        this(GrowthcraftMilkTileEntities.MIXING_VAT_TILE_ENTITY.get());
    }

    public MixingVatTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    @Override
    public void tick() {
        boolean dirty = false;

        MixingVatRecipe.MixingVatRecipeCategory category;

        // Determine if there is a need to check for a recipe and if so, which type of recipe
        // should we try and process.

        if (!inputFluidTankHandler.getTank(0).isEmpty()) {

            List<ItemStack> inventory = new ArrayList<>();
            for (int i = 0; i < inputInventorySlots; i++) {
                if (!items.getStackInSlot(i).isEmpty()) inventory.add(items.getStackInSlot(i));
            }

            MixingVatRecipe recipe = null;

            if (inventory.size() > 0) {
                if (!inputFluidTankHandler.getTank(1).isEmpty()) {
                    category = MixingVatRecipe.MixingVatRecipeCategory.FLUID;
                } else {
                    category = MixingVatRecipe.MixingVatRecipeCategory.ITEM;
                }

                // Then we need to check of there is a valid recipe that needs to be activated.
                if (category == MixingVatRecipe.MixingVatRecipeCategory.ITEM) {
                    recipe = this.getItemRecipe(inputFluidTankHandler.getTank(0).getFluid(), inventory);
                } else if (category == MixingVatRecipe.MixingVatRecipeCategory.FLUID) {
                    recipe = this.getFluidRecipe(inputFluidTankHandler.getTank(0).getFluid(),
                            inputFluidTankHandler.getTank(1).getFluid(), inventory);
                }

                if (recipe != null) {

                }
            }

        } else {
            currentRecipe = null;
            currentProcessingTime = 0;
        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private MixingVatItemRecipe getItemRecipe(FluidStack fluidStack, List<ItemStack> ingredients) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(this.world, GrowthcraftMilkRecipes.MIXING_VAT_ITEM_RECIPE_TYPE);
        for (IRecipe<?> recipe : recipes) {
            MixingVatItemRecipe mixingVatRecipe = (MixingVatItemRecipe) recipe;
            if (mixingVatRecipe.matches(fluidStack, ingredients)) return mixingVatRecipe;
        }
        return null;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private MixingVatFluidRecipe getFluidRecipe(FluidStack baseFluidStack, FluidStack reagentFluidStack, List<ItemStack> ingredients) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(this.world, GrowthcraftMilkRecipes.MIXING_VAT_FLUID_RECIPE_TYPE);
        for (IRecipe<?> recipe : recipes) {
            MixingVatFluidRecipe mixingVatRecipe = (MixingVatFluidRecipe) recipe;
            if (mixingVatRecipe.matches(baseFluidStack, reagentFluidStack, ingredients)) return mixingVatRecipe;
        }

        return null;
    }

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

package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.FermentBarrelContainer;
import growthcraft.cellar.common.recipe.FermentBarrelRecipe;
import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.common.tank.handler.FluidTankHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class FermentBarrelTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final GrowthcraftItemHandler inventory;
    private final FluidTankHandler fluidTankHandler;
    private int currentProcessingTime;
    private FermentBarrelRecipe currentRecipe;
    private ITextComponent customName;
    private int maxProcessingTime;

    public FermentBarrelTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new GrowthcraftItemHandler(1);
        this.fluidTankHandler = new FluidTankHandler(1, 4000);
        this.currentProcessingTime = 0;
    }

    public FermentBarrelTileEntity() {
        this(GrowthcraftCellarTileEntities.barrel_ferment_oak_tileentity.get());
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory.toNonNullList();
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventory.setNonNullList(itemsIn);
    }

    @Override
    public void tick() {
        boolean dirty = false;
        if (this.world != null && !this.world.isRemote) {
            if (!this.inventory.getStackInSlot(0).isEmpty() && !this.getFluidTank(0).isEmpty()) {

                FermentBarrelRecipe recipe = this.getRecipe(
                        this.inventory.getStackInSlot(0),
                        this.getFluidTank(0).getFluid()
                );

                if (recipe != null) {
                    this.maxProcessingTime = recipe.getProcessingTime();

                    if (recipe != this.currentRecipe) {
                        this.currentRecipe = recipe;
                        // wait until next cycle to start processing
                    } else if (this.currentProcessingTime >= this.maxProcessingTime) {
                        processRecipeResult();
                        // reset counters
                        this.currentProcessingTime = 0;
                    } else {
                        this.currentProcessingTime++;
                    }
                    dirty = true;
                } else {
                    // Check for the potion only recipe.
                    if (currentProcessingTime > 0) {
                        currentProcessingTime = 0;
                    }
                }
            } else {
                /*
                // Inventory is insufficient to process a recipe, check for Potion Recipe
                FermentBarrelRecipe recipe = this.getRecipe(
                        this.getFluidTank(0).getFluid()
                );

                if (recipe != null && recipe != currentRecipe) {
                    this.currentRecipe = recipe;
                }

                 */
            }
        }

        if (dirty) {
            this.markUpdate();
        }
    }

    private void markUpdate() {
        this.markDirty();
        this.world.notifyBlockUpdate(
                this.getPos(), this.getBlockState(), this.getBlockState(),
                Constants.BlockFlags.BLOCK_UPDATE);
    }

    /**
     * Process the results from the current recipe.
     */
    private void processRecipeResult() {
        // Determine if the current ingredients is a multiple of the recipe.
        Float fluidMultiplier = this.getFluidTank(0).getFluidAmount() / (float) this.currentRecipe.getResultingFluid().getAmount();

        // We have to have a multiple of the fluid in order to replace all of it at one time.
        if (fluidMultiplier % 1 == 0) {
            int itemStackShrinkCount = currentRecipe.getIngredientItemStack().getCount() * fluidMultiplier.intValue();

            // Check that there are enough input items
            if (this.inventory.getStackInSlot(0).getCount() >= itemStackShrinkCount) {
                this.inventory.setStackInSlot(0, ItemStack.EMPTY);
                this.fluidTankHandler.getTank(0).setFluid(FluidStack.EMPTY);

                FluidStack resultFluidStack = currentRecipe.getResultingFluid();
                resultFluidStack.setAmount(currentRecipe.getResultingFluid().getAmount() * fluidMultiplier.intValue());

                this.fluidTankHandler.getTank(0).setFluid(resultFluidStack);

                // Trigger and update as we have an update.
                this.markUpdate();
            }
        } else {
            // Something isn't right we do not have ingredients for a whole recipe.
        }
    }

    // Custom Name Handling
    @Override
    public ITextComponent getDisplayName() {
        return this.getName() != null ? this.getName() : this.getDefaultName();
    }

    @Override
    protected ITextComponent getDefaultName() {
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.FERMENT_BARREL);
        return new TranslationTextComponent(translationKey);
    }

    @Override
    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    // Interactive GUI
    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new FermentBarrelContainer(id, player, this);
    }

    // NBT Data Handling
    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        NonNullList<ItemStack> inv = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);

        this.currentProcessingTime = compound.getInt("CurrentProcessingTime");
        this.maxProcessingTime = compound.getInt("MaxProcessingTime");

        // Read the tank data from NBT
        this.fluidTankHandler.getTank(0).readFromNBT(compound.getCompound("tank0"));

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentProcessingTime", this.currentProcessingTime);
        compound.putInt("MaxProcessingTime", this.maxProcessingTime);

        // Save the tank to the NBTTag
        compound.put("tank0", this.fluidTankHandler.getTank(0).writeToNBT(new CompoundNBT()));
        return super.write(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();
        this.write(nbt);
        return nbt;
    }

    // Inventory
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidTankHandler.getFluidTankHandler(0).cast();
        }
        return super.getCapability(cap, side);
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.getSlots();
    }

    // Recipe Handling
    @Nullable
    public FermentBarrelRecipe getRecipe(ItemStack inputItemStack, FluidStack inputFluidStack) {
        List<FermentBarrelRecipe> recipes = this.world.getRecipeManager().getRecipesForType(GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE);

        for (FermentBarrelRecipe recipe : recipes) {
            if (recipe.matches(inputItemStack, inputFluidStack)
                    || recipe.matches(inputFluidStack)) return recipe;
        }
        return null;
    }

    @Nullable
    public FermentBarrelRecipe getRecipe(FluidStack inputFluidStack) {
        List<FermentBarrelRecipe> recipes = this.world.getRecipeManager().getRecipesForType(GrowthcraftCellarRecipes.FERMENT_BARREL_RECIPE_TYPE);
        for (FermentBarrelRecipe recipe : recipes) {
            if (recipe.matches(inputFluidStack)) return recipe;
        }
        return null;
    }

    // Getters and Setters
    public int getCurrentProcessingTime() {
        return this.currentProcessingTime;
    }

    public void setCurrentProcessingTime(int ticks) {
        this.currentProcessingTime = ticks;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime > 0 ? this.maxProcessingTime : 9600;
    }

    public FluidTankHandler getFluidTankHandler() {
        return this.fluidTankHandler;
    }

    public FluidTank getFluidTank(int tank) {
        return this.getFluidTankHandler().getTank(tank);
    }

    public void drainTank(int tank, int amount) {
        this.getFluidTank(tank).drain(amount, IFluidHandler.FluidAction.EXECUTE);
        markUpdate();
    }

    public ItemStack getCurrentPotionItemStack() {
        return this.currentRecipe.getBottleItemStack();
    }

}

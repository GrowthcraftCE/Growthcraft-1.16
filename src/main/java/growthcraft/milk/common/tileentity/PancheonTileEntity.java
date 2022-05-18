package growthcraft.milk.common.tileentity;

import growthcraft.lib.common.tank.handler.FluidTankHandler;
import growthcraft.lib.common.tank.handler.FluidTankOutputHandler;
import growthcraft.lib.util.RecipeUtils;
import growthcraft.milk.client.container.PancheonContainer;
import growthcraft.milk.common.block.PancheonBlock;
import growthcraft.milk.common.recipe.PancheonRecipe;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

public class PancheonTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int currentProcessingTime;
    // Innput Tank 2,000 mb top accessible
    // Output Tanks 1,000 mb each draw from the side or bottom only.
    private ITextComponent customName;
    private FluidTankHandler inputFluidTankHandler;
    private boolean locked;
    private int maxProcessingTime;
    private FluidTankOutputHandler outputFluidTankHandler;
    private PancheonRecipe currentRecipe;

    public PancheonTileEntity() {
        this(GrowthcraftMilkTileEntities.PANCHEON_TILE_ENTITY.get());
    }

    public PancheonTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.locked = false;
        this.createFluidTanks();
    }

    @Override
    public void tick() {
        boolean dirty = false;
        if(world != null && !world.isRemote()) {
            // TODO: While processing lock the fluid interaction.
            if (this.currentProcessingTime > 0) {
                this.world.setBlockState(this.getPos(), this.getBlockState().with(PancheonBlock.LOCKED, true));
            } else {
                this.world.setBlockState(this.getPos(), this.getBlockState().with(PancheonBlock.LOCKED, false));
            }

            if (!this.inputFluidTankHandler.getTank(0).isEmpty()) {
                PancheonRecipe recipe = this.getRecipe(this.inputFluidTankHandler.getTank(0).getFluid());

                if (recipe != null) {
                    if (recipe != this.currentRecipe && this.inputFluidTankHandler.getTank(0).getFluidAmount() >= recipe.getInputFluidStack().getAmount()) {
                        this.currentRecipe = recipe;
                        currentProcessingTime = 0;
                        maxProcessingTime = recipe.getProcessingTime();
                        dirty = true;
                    } else {
                        if (this.inputFluidTankHandler.getTank(0).getFluidAmount() >= recipe.getInputFluidStack().getAmount()) {
                            this.currentProcessingTime++;
                            dirty = true;
                        }
                    }
                    if (currentProcessingTime > maxProcessingTime) {
                        this.inputFluidTankHandler.getTank(0).drain(recipe.getInputFluidStack().getAmount(), IFluidHandler.FluidAction.EXECUTE);
                        this.outputFluidTankHandler.getTank(0).forceFill(recipe.getOutputFluidStack(0), IFluidHandler.FluidAction.EXECUTE);
                        this.outputFluidTankHandler.getTank(1).forceFill(recipe.getOutputFluidStack(1), IFluidHandler.FluidAction.EXECUTE);

                        currentRecipe = null;
                        currentProcessingTime = 0;
                        dirty = true;
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

    }

    @Nullable
    @ParametersAreNonnullByDefault
    private PancheonRecipe getRecipe(FluidStack fluidStack) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(GrowthcraftMilkRecipes.PANCHEON_RECIPE_TYPE);
        for (IRecipe<?> recipe : recipes) {
            PancheonRecipe pancheonRecipe = (PancheonRecipe) recipe;
            if (pancheonRecipe.matches(fluidStack)) return pancheonRecipe;
        }
        return null;
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Override
    @Nonnull
    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    @Nonnull
    protected ITextComponent getDefaultName() {
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.PANCHEON);
        return new TranslationTextComponent(translationKey);
    }

    @Override
    @Nonnull
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new PancheonContainer(windowId, playerInventory, this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        // Do nothing as there isn't an Item inventory.
    }

    private void createFluidTanks() {
        this.inputFluidTankHandler = new FluidTankHandler(1, 2000);
        this.outputFluidTankHandler = new FluidTankOutputHandler(2, 1000);
    }

    public FluidTank getFluidTank(int slot) {
        switch (slot) {
            case 0:
                return inputFluidTankHandler.getTank(0);
            case 1:
                return outputFluidTankHandler.getTank(0);
            case 2:
                return outputFluidTankHandler.getTank(1);
            default:
                return null;
        }
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        this.currentProcessingTime = compound.getInt("CurrentProcessingTime");
        this.maxProcessingTime = compound.getInt("MaxProcessingTime");
        this.locked = compound.getBoolean("IsLocked");

        this.inputFluidTankHandler.getTank(0).readFromNBT(compound.getCompound("tank0"));
        this.outputFluidTankHandler.getTank(0).readFromNBT(compound.getCompound("tank1"));
        this.outputFluidTankHandler.getTank(1).readFromNBT(compound.getCompound("tank2"));

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        compound.putInt("CurrentProcessingTime", this.currentProcessingTime);
        compound.putInt("MaxProcessingTime", this.maxProcessingTime);
        compound.putBoolean("IsLocked", this.isLocked());

        compound.put("tank0", this.inputFluidTankHandler.getTank(0).writeToNBT(new CompoundNBT()));
        compound.put("tank1", this.outputFluidTankHandler.getTank(0).writeToNBT(new CompoundNBT()));
        compound.put("tank2", this.outputFluidTankHandler.getTank(1).writeToNBT(new CompoundNBT()));

        return super.write(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return nbt;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.isLocked()) {
            if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
                int inputFluidAmount = this.inputFluidTankHandler.getTank(0).getFluidAmount();
                int outputFluidAmount0 = this.outputFluidTankHandler.getTank(0).getFluidAmount();
                int outputFluidAmount1 = this.outputFluidTankHandler.getTank(1).getFluidAmount();

                // If inputTank has fluid or all tanks or empty, return the input tank.
                if (inputFluidAmount > 0 || inputFluidAmount + outputFluidAmount0 + outputFluidAmount1 == 0) {
                    return this.inputFluidTankHandler.getFluidTankHandler(0).cast();
                }
                // If input is empty and output0 is not empty, return output0
                if (inputFluidAmount == 0 && outputFluidAmount0 > 0) {
                    return outputFluidTankHandler.getFluidTankHandler(0).cast();
                }
                // If input and output0 are empty but output1 is not empty, return Output1
                if (inputFluidAmount == 0 && outputFluidAmount0 == 0 && outputFluidAmount1 > 0) {
                    return outputFluidTankHandler.getFluidTankHandler(1).cast();
                }

            }
        }
        return super.getCapability(cap, side);
    }

    public boolean isLocked() {
        return this.locked;
    }

    public int getCurrentProcessingTime() {
        return this.currentProcessingTime;
    }

    public void setCurrentProcessingTime(int processingTime) {
        this.currentProcessingTime = processingTime;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    public boolean isFluidEmpty() {
        return this.inputFluidTankHandler.getTank(0).isEmpty() && this.outputFluidTankHandler.getTank(0).isEmpty() && this.outputFluidTankHandler.getTank(1).isEmpty();
    }
}

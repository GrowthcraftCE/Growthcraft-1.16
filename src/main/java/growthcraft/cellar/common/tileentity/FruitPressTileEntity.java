package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.FruitPressContainer;
import growthcraft.cellar.common.recipe.FruitPressRecipe;
import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
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

public class FruitPressTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int maxProcessingTime;
    private int currentProcessingTime;
    private ITextComponent customName;

    private final GrowthcraftItemHandler inventory;

    private FluidTank outputFluidTank;
    private final LazyOptional<IFluidHandler> outputFluidHandler = LazyOptional.of(
            () -> outputFluidTank
    );

    private FruitPressRecipe currentRecipe;

    protected FruitPressTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
        this.inventory = new GrowthcraftItemHandler(1);
        this.createFluidTanks();
        this.maxProcessingTime = 0;
        this.currentProcessingTime = 0;
    }

    public FruitPressTileEntity() {
        this(GrowthcraftCellarTileEntities.fruit_press_tileentity.get());
    }

    // Interactive GUI
    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new FruitPressContainer(windowId, playerInventory, this);
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new FruitPressContainer(windowId, playerInventory, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;

        // Check if the FruitPressPiston is powered and the input slot is not empty
        if (!this.world.isRemote && this.world.isBlockPowered(this.getPos().up()) && !this.inventory.getStackInSlot(0).isEmpty()) {

            FruitPressRecipe fruitPressRecipe = this.getRecipe(
                    this.inventory.getStackInSlot(0)
            );

            if (fruitPressRecipe != null) {
                this.setMaxProcessingTime(fruitPressRecipe.getProcessingTime());

                if (currentRecipe != null && this.isTankFull(0)) {
                    // Do nothing as we cannot process the output.
                } else if (fruitPressRecipe != currentRecipe) {
                    currentRecipe = fruitPressRecipe;
                } else if (currentProcessingTime >= maxProcessingTime) {
                    this.processRecipeResult();
                    currentProcessingTime = 0;
                } else {
                    this.currentProcessingTime++;
                }

                dirty = true;

            } else {
                // No matching recipe found.
            }
        } else {
            // FruitPressPiston isn't power and/or the input inventory is empty.
        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(
                    this.getPos(), this.getBlockState(), this.getBlockState(),
                    Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    public boolean isTankFull(int index) {
        try {
            int tankAmount = this.getFluidTank(index).getFluidAmount();
            int outputAmount = this.currentRecipe.getOutputFluidStack().getAmount();
            int tankCapacity = this.getFluidTank(index).getCapacity();

            return this.getFluidTank(index).getFluidAmount() + currentRecipe.getOutputFluidStack().getAmount() > this.getFluidTank(0).getCapacity();
        } catch (Exception ex) {
            // Then there isn't a current recipe, and we need to return true to prevent processing.
            return true;
        }
    }

    private void processRecipeResult() {
        if (!this.isTankFull(0)) {
            // Process the inputs.
            this.inventory.getStackInSlot(0).shrink(currentRecipe.getInputItemStack().getCount());
            // Process the outputs
            FluidStack resultFluidStack = currentRecipe.getOutputFluidStack();
            resultFluidStack.setAmount(currentRecipe.getOutputFluidStack().getAmount());
            this.getFluidTank(0).fill(resultFluidStack, IFluidHandler.FluidAction.EXECUTE);
        }
    }

    public int getCurrentProcessingTime() {
        return this.currentProcessingTime;
    }

    public void setCurrentProcessingTime(int time) {
        this.currentProcessingTime = time;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    public void setMaxProcessingTime(int maxTime) {
        this.maxProcessingTime = maxTime;
    }

    public int getPercentageCompleted() {
        int percentage = 0;

        if (this.maxProcessingTime > 0) {
            percentage = currentProcessingTime / maxProcessingTime * 100;
        }
        return percentage;
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
    public int getSizeInventory() {
        return this.inventory.getSlots();
    }

    @Nullable
    public FruitPressRecipe getRecipe(ItemStack inputStack) {
        List<FruitPressRecipe> recipes = this.world.getRecipeManager().getRecipesForType(GrowthcraftCellarRecipes.FRUIT_PRESS_RECIPE_TYPE);

        for (FruitPressRecipe recipe : recipes) {
            if (recipe.matches(inputStack)) {
                return recipe;
            }
        }

        return null;
    }

    // Inventory
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return outputFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    // Fluid Tanks
    private void createFluidTanks() {
        this.outputFluidTank = new FluidTank(4000);
    }

    public FluidTank getFluidTank(int slot) {
        return outputFluidTank;
    }

    // Custom Name Handling
    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Override
    public void setCustomName(ITextComponent name) {
        this.customName = name;
    }

    @Override
    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    @Override
    public ITextComponent getDefaultName() {
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.FRUIT_PRESS);
        return new TranslationTextComponent(translationKey);
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

        outputFluidTank.readFromNBT(compound.getCompound("outputTank"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentProcessingTime", this.currentProcessingTime);

        CompoundNBT outputTankNBT = outputFluidTank.writeToNBT(new CompoundNBT());
        compound.put("outputTank", outputTankNBT);

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

}

package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
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
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public class FruitPressTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int maxProcessingTime = 0;
    private int currentProcessingTime = 0;
    private ITextComponent customName;

    private final BrewKettleItemHandler inventory;

    private FluidTank outputFluidTank;
    private final LazyOptional<IFluidHandler> outputFluidHandler = LazyOptional.of(
            () -> outputFluidTank
    );

    protected FruitPressTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
        this.inventory = new BrewKettleItemHandler(1);
        this.createFluidTanks();
    }

    public FruitPressTileEntity() {
        this(GrowthcraftCellarTileEntities.fruit_press_tileentity.get());
    }

    @Override
    public void tick() {
        // TODO: Check if the FruitPressPiston is powered.

        // TODO: Lock the inventory if FruitPressPistion is powered.

        //World world = this.world;
        //BlockState state = world.getBlockState(this.getPos());
        //int powerLevel = world.getRedstonePowerFromNeighbors(this.getPos());
        //world.setBlockState(this.pos, state.with(FruitPressPistonBlock.PRESSED, powerLevel == 15));
    }

    /**
     * Based on the recipe and the current item inventory, determine how long it
     * will take to press the entire ItemStack.
     *
     * @return
     */
    private void setCurrentProcessingTime(int time) {
        this.currentProcessingTime = time;
    }

    private int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    private void setMaxProcessingTime(int maxTime) {
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
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.getSlots();
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

    public FluidTank getInputFluidTank(int slot) {
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

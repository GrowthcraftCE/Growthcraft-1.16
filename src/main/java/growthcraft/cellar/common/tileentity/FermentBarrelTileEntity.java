package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.FermentBarrelContainer;
import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public class FermentBarrelTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final int maxProcessingTime = 0;
    private int currentProcessingTime = 0;
    private ITextComponent customName;

    private final BrewKettleItemHandler inventory;

    private final FluidTankHandler fluidTankHandler;

    // private FermentBarrelRecipe currrentRecipe;

    public FermentBarrelTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new BrewKettleItemHandler(1);
        fluidTankHandler = new FluidTankHandler(1, 4000);
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

    }

    // TileEntityRender Distance - Not Needed

    // Custom Name Handling
    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Override
    protected ITextComponent getDefaultName() {
        return this.getBlockState().getBlock().getTranslatedName();
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

        for (int i = 0; i < fluidTankHandler.getNumberTanks(); i++) {
            fluidTankHandler.getTank(i).readFromNBT(compound.getCompound("tank" + i));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentProcessingTime", this.currentProcessingTime);
        for (int i = 0; i < fluidTankHandler.getNumberTanks(); i++) {
            CompoundNBT tankNBT = fluidTankHandler.getTank(i).writeToNBT(new CompoundNBT());
            compound.put("tank" + i, tankNBT);
        }

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

    // Heat Sources Handling - Not needed

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

    // Recipes Handling
    // TODO[19]: Add recipe handling to FermentBarrel - Note can only ferment whole buckets as one bucket takes 1 yeast

    // Getters and Setters
    public int getCurrentProcessingTime() {
        return this.currentProcessingTime;
    }

    public void setCurrentProcessingTime(int ticks) {
        this.currentProcessingTime = ticks;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    public FluidTankHandler getFluidTankHandler() {
        return fluidTankHandler;
    }

    public FluidTank getFluidTank(int tank) {
        return fluidTankHandler.getTank(tank);
    }

}

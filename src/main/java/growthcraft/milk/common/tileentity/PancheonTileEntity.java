package growthcraft.milk.common.tileentity;

import growthcraft.lib.common.tank.handler.FluidTankHandler;
import growthcraft.lib.common.tank.handler.FluidTankOutputHandler;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PancheonTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    // Innput Tank 2,000 mb top accessible
    // Output Tanks 1,000 mb each draw from the side or bottom only.
    private ITextComponent customName;
    private int currentProcessingTime;
    private int maxProcessingTime;
    private FluidTankHandler inputFluidTankHandler;
    private FluidTankOutputHandler outputFluidTankHandler;

    public PancheonTileEntity() {
        this(GrowthcraftMilkTileEntities.PANCHEON_TILE_ENTITY.get());
    }

    public PancheonTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.createFluidTanks();
    }

    @Override
    public void tick() {

    }

    // TODO: Implement PancheonRecipe and the getRecipe() method.

    @Override
    protected ITextComponent getDefaultName() {
        return this.getBlockState().getBlock().getTranslatedName();
    }

    @Override
    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        // TODO: Return a new PancheonContainer(...)
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

    private void createFluidTanks() {
        this.inputFluidTankHandler = new FluidTankHandler(1, 2000);
        this.outputFluidTankHandler = new FluidTankOutputHandler(2, 1000);
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
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && side == Direction.UP) {
            return this.inputFluidTankHandler.getFluidTankHandler(0).cast();
        } else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return this.outputFluidTankHandler.getTank(0).getFluidAmount() > 0
                    ? this.outputFluidTankHandler.getFluidTankHandler(0).cast()
                    : this.outputFluidTankHandler.getFluidTankHandler(1).cast();
        }
        return super.getCapability(cap, side);
    }
}

package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.BlockStateUtils;
import net.minecraft.block.Block;
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
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
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
import java.util.Map;

public class CultureJarTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final int maxProcessingTicks = 250;
    private int currentProcessingTicks;
    // TODO: Create CultureJazrRecipe serialization
    private ITextComponent customName;

    private final BrewKettleItemHandler inventory;

    private FluidTank inputFluidTank;
    private final LazyOptional<IFluidHandler> inputFluidHandler = LazyOptional.of(
            () -> inputFluidTank
    );

    public CultureJarTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.inventory = new BrewKettleItemHandler(1);
        this.createFluidTanks();
    }

    public CultureJarTileEntity() {
        this(GrowthcraftCellarTileEntities.culture_jar_tileentity.get());
    }

    // Custom Name Handling
    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    public void setCustomName(ITextComponent name) {
        this.customName = name;
    }

    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    private ITextComponent getDefaultName() {
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.BREW_KETTLE);
        return new TranslationTextComponent(translationKey);
    }

    // Interactive GUI
    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        // TODO: return Container
        return null;
    }

    @Override
    public void tick() {
        // TODO[20]: implement culture jar processing. Requires recipe serialization.
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

        this.currentProcessingTicks = compound.getInt("CurrentProcessingTicks");

        inputFluidTank.readFromNBT(compound.getCompound("inputTank"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentProcessingTicks", this.currentProcessingTicks);

        CompoundNBT inputTankNBT = inputFluidTank.writeToNBT(new CompoundNBT());
        compound.put("inputTank", inputTankNBT);

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

    // Heat Sources
    public boolean isHeated() {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, pos);
        return BlockTags.getCollection().get(
                new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                        growthcraft.core.shared.Reference.TAG_HEATSOURCES)).contains(blockMap.get("down"));
    }

    // Inventory
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return inputFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    // Fluid Tanks
    private void createFluidTanks() {
        this.inputFluidTank = new FluidTank(4000);
    }

    public FluidTank getInputFluidTank(int slot) {
        return inputFluidTank;
    }

    // Recipe Handling
    // TODO[20]: getRecipes

    // Getters and Setters
    public int getCurrentProcessingTicks() {
        return currentProcessingTicks;
    }

    public void setCurrentProcessingTicks(int ticks) {
        this.currentProcessingTicks = ticks;
    }
}

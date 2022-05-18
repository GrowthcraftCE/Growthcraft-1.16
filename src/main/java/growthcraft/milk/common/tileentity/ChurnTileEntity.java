package growthcraft.milk.common.tileentity;

import growthcraft.lib.common.tank.handler.FluidTankHandler;
import growthcraft.lib.util.RecipeUtils;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.client.container.ChurnContainer;
import growthcraft.milk.common.block.ChurnBlock;
import growthcraft.milk.common.recipe.ChurnRecipe;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
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
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.security.SecureRandom;
import java.util.Set;

public class ChurnTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final IItemHandlerModifiable items = createHandler();
    private final LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    private int currentNumberPlunges;
    private ITextComponent customName;
    private FluidTankHandler fluidTankHandler;
    private NonNullList<ItemStack> inventoryItemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
    private int maxNumberPlunges;
    private boolean plunged;
    private boolean plungerLocked;
    private ChurnRecipe currentRecipe;

    public ChurnTileEntity() {
        this(GrowthcraftMilkTileEntities.CHURN_TILE_ENTITY.get());
    }

    protected ChurnTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.createFluidTanks();
        this.plunged = false;
        this.plungerLocked = true;
    }

    private void createFluidTanks() {
        this.fluidTankHandler = new FluidTankHandler(1, 1000);
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    public void togglePlunger() {
        if (!world.isRemote()) {
            if (!this.plungerLocked) {
                if (this.plunged) {
                    this.playSound(null, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF);
                    this.plunged = false;
                } else {
                    this.playSound(null, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
                    this.plunged = true;
                    this.incrementPlunges(1);
                }
            }
            this.world.setBlockState(this.getPos(), this.getBlockState().with(ChurnBlock.PLUNGED, isPlunged()));
        }
    }

    public void incrementPlunges(int i) {
        boolean dirty = false;

        // Ensure that we have a consistent initial plunge state for the parent block.
        this.world.setBlockState(this.getPos(), this.getBlockState().with(ChurnBlock.PLUNGED, isPlunged()));

        if (!world.isRemote()) {
            if (!this.fluidTankHandler.getTank(0).isEmpty() && this.items.getStackInSlot(0).isEmpty()) {
                if (this.currentRecipe != null) {

                    if (this.currentNumberPlunges > this.maxNumberPlunges) {
                        // Process the recipe results.
                        this.fluidTankHandler.getTank(0).setFluid(currentRecipe.getOutputFluidStack());
                        if (new SecureRandom().nextInt(100) <= currentRecipe.getByProductChance()) {
                            this.items.insertItem(0, currentRecipe.getResultItemStack(), false);
                        }

                        this.currentRecipe = null;
                        this.currentNumberPlunges = 0;
                        this.plungerLocked = true;

                        dirty = true;
                    } else {
                        this.currentNumberPlunges += i;
                        dirty = true;
                    }
                } else {
                    GrowthcraftMilk.LOGGER.error("No recipe found");
                    this.plungerLocked = true;
                }
            } else {
                this.currentRecipe = null;
                this.currentNumberPlunges = 0;
                this.plungerLocked = true;
            }
        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    public void playSound(@Nullable PlayerEntity player, SoundEvent sound) {
        double dx = (double) this.pos.getX() + 0.5D;
        double dy = (double) this.pos.getY() + 0.5D;
        double dz = (double) this.pos.getZ() + 0.5D;

        assert this.world != null;
        this.world.playSound(player, dx, dy, dz, sound, SoundCategory.BLOCKS, 0.5F,
                this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void tick() {
        if(world != null && !world.isRemote()) {
            boolean dirty = false;
            if (!this.fluidTankHandler.getTank(0).isEmpty() && this.items.getStackInSlot(0).isEmpty()) {
                // Then check for a valid new recipe.
                ChurnRecipe recipe = this.getRecipe(this.fluidTankHandler.getTank(0).getFluid());

                if (recipe != null) {
                    if (recipe != this.currentRecipe) {
                        this.currentRecipe = recipe;
                        this.maxNumberPlunges = recipe.getPlungesNeeded();
                        this.plungerLocked = false;
                        dirty = true;
                    }
                }
            }

            if (dirty) {
                this.markDirty();
                this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            }
        }
    }

    public FluidTank getFluidTank(int slot) {
        switch (slot) {
            case 0:
                return this.fluidTankHandler.getTank(0);
            default:
                return null;
        }
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private ChurnRecipe getRecipe(FluidStack fluidStack) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(GrowthcraftMilkRecipes.CHURN_RECIPE_TYPE);
        for (IRecipe<?> recipe : recipes) {
            ChurnRecipe churnRecipe = (ChurnRecipe) recipe;
            if (churnRecipe.matches(fluidStack)) return churnRecipe;
        }
        return null;
    }

    public IItemHandler getInventory() {
        return this.items;
    }

    @Override
    public int getSizeInventory() {
        return this.inventoryItemStacks.size();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventoryItemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventoryItemStacks = itemsIn;
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
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.CHURN);
        return new TranslationTextComponent(translationKey);
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new ChurnContainer(windowId, playerInventory, this);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        this.inventoryItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.inventoryItemStacks);
        }

        this.currentNumberPlunges = compound.getInt("CurrentProcessingTicks");
        this.maxNumberPlunges = compound.getInt("MaxNumberPlunges");
        this.plunged = compound.getBoolean("Plunged");

        this.getFluidTankHandler().getTank(0).readFromNBT(compound.getCompound("tank0"));

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventoryItemStacks);

        compound.putInt("CurrentNumberPlunges", this.currentNumberPlunges);
        compound.putInt("MaxNumberPlunges", this.maxNumberPlunges);
        compound.putBoolean("Plunged", this.plunged);

        compound.put("tank0", this.getFluidTankHandler().getTank(0).writeToNBT(new CompoundNBT()));

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

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return this.fluidTankHandler.getFluidTankHandler(0).cast();
        } else if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public boolean isPlunged() {
        return this.plunged;
    }

    public int getCurrentNumberPlunges() {
        return this.currentNumberPlunges;
    }

    public int getMaxNumberPlunges() {
        return this.maxNumberPlunges;
    }

    public FluidTankHandler getFluidTankHandler() {
        return this.fluidTankHandler;
    }

}

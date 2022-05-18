package growthcraft.milk.common.tileentity;

import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.milk.common.recipe.CheesePressRecipe;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CheesePressTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private static final int INPUT_INVENTORY_SLOTS = 1;
    private final GrowthcraftItemHandler inventory;
    private boolean open = true;
    private int rotation;
    private ITextComponent customName;

    private CheesePressRecipe currentRecipe;
    private int currentProcessingTicks;
    private int maxProcessingTicks;

    public CheesePressTileEntity() {
        this(GrowthcraftMilkTileEntities.CHEESE_PRESS_TILE_ENTITY.get());
    }

    public CheesePressTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.inventory = new GrowthcraftItemHandler(INPUT_INVENTORY_SLOTS);
        this.rotation = 0;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory.toNonNullList();
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.inventory.setNonNullList(nonNullList);
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
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.CHEESE_PRESS);
        return new TranslationTextComponent(translationKey);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public void tick() {
        if (world != null && !world.isRemote() && !this.inventory.getStackInSlot(0).isEmpty() && this.getRotation() == 7) {
            if (isRecipeCurrent()) {
                if (this.currentProcessingTicks < this.maxProcessingTicks) {
                    this.currentProcessingTicks++;
                    this.markDirty();
                } else {
                    this.processCurrentRecipe();
                }
            }
        }
    }

    private boolean isRecipeCurrent() {
        List<CheesePressRecipe> cheesePressRecipes = this.world.getRecipeManager().getRecipesForType(GrowthcraftMilkRecipes.CHEESE_PRESS_RECIPE_TYPE);

        for (CheesePressRecipe cheesePressRecipe : cheesePressRecipes) {
            if (cheesePressRecipe.matches(this.inventory.getStackInSlot(0))) {
                if (this.currentRecipe != null && this.currentRecipe == cheesePressRecipe) {
                    return true;
                } else {
                    this.currentRecipe = cheesePressRecipe;
                    this.currentProcessingTicks = 0;
                    this.maxProcessingTicks = cheesePressRecipe.getProcessingTime();
                    return true;
                }
            }
        }

        return false;
    }

    public void processCurrentRecipe() {
        if (currentRecipe != null) {
            this.getInventory().setStackInSlot(0, ItemStack.EMPTY);
            this.getInventory().insertItem(0, this.currentRecipe.getRecipeOutput(), false);
            this.resetProcessing();
        }
    }

    public void resetProcessing() {
        this.currentRecipe = null;
        this.currentProcessingTicks = 0;
        this.maxProcessingTicks = 0;
        this.markDirty();
    }

    public int doRotation(boolean increase) {
        // Can't increase, already at max.
        if (this.rotation == 7 && increase) return this.getRotation();
        // Can't decrease, already at min.
        if (this.rotation == 0 && !increase) return this.getRotation();

        if (increase) {
            this.rotation++;
        } else {
            // Fast track to 0
            this.rotation = 0;
        }
        this.open = this.rotation == 0;

        return this.getRotation();
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        this.currentProcessingTicks = compound.getInt("CurrentProcessingTicks");
        this.open = compound.getBoolean("IsOpen");
        this.rotation = compound.getInt("rotation");
        NonNullList<ItemStack> inv = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        compound.putInt("CurrentProcessingTicks", this.currentProcessingTicks);
        compound.putBoolean("IsOpen", this.isOpen());
        compound.putInt("rotation", this.getRotation());
        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());

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

    private boolean tryPlaceItemInventory(ItemStack itemStack) {
        for (int i = 0; i < INPUT_INVENTORY_SLOTS; i++) {
            if (this.getInventory().getStackInSlot(i).isEmpty()) {
                this.getInventory().insertItem(i, itemStack, false);
                if (!this.getInventory().getStackInSlot(i).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    public GrowthcraftItemHandler getInventory() {
        return inventory;
    }

    public int getRotation() {
        return this.rotation;
    }

    public boolean isOpen() {
        return this.open;
    }
}

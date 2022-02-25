package growthcraft.milk.common.tileentity;

import growthcraft.lib.common.tank.handler.FluidTankHandler;
import growthcraft.lib.common.tank.handler.FluidTankOutputHandler;
import growthcraft.lib.util.BlockStateUtils;
import growthcraft.lib.util.RecipeUtils;
import growthcraft.milk.common.recipe.MixingVatFluidRecipe;
import growthcraft.milk.common.recipe.MixingVatItemRecipe;
import growthcraft.milk.common.recipe.MixingVatRecipe;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
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
import java.util.Map;
import java.util.Set;

public class MixingVatTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int currentProcessingTime;
    private MixingVatRecipe currentRecipe;
    private ITextComponent customName;
    private FluidTankHandler inputFluidTankHandler;
    private int maxProcessingTime;
    private FluidTankOutputHandler outputFluidTankHandler;
    private boolean heated = false;
    private boolean activated = false;

    private final int inputInventorySlots = 3;

    private IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    private NonNullList<ItemStack> inventoryItemStacks = NonNullList.withSize(4, ItemStack.EMPTY);

    public MixingVatTileEntity() {
        this(GrowthcraftMilkTileEntities.MIXING_VAT_TILE_ENTITY.get());
    }

    public MixingVatTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.createFluidTanks();
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    private void createFluidTanks() {
        this.inputFluidTankHandler = new FluidTankHandler(2, 4000);
        this.outputFluidTankHandler = new FluidTankOutputHandler(1, 4000);
    }

    public FluidTank getFluidTank(int slot) {
        switch (slot) {
            case 0:
                return inputFluidTankHandler.getTank(0);
            case 1:
                return inputFluidTankHandler.getTank(1);
            case 2:
                return outputFluidTankHandler.getTank(0);
            default:
                return null;
        }
    }

    @Override
    public void tick() {
        boolean dirty = false;

        // Determine if there is a need to check for a recipe and if so, which type of recipe
        // should we try and process.
        try {
            if (!inputFluidTankHandler.getTank(0).isEmpty() && this.isHeated() && outputFluidTankHandler.getTank(0).isEmpty()
                    && items.getStackInSlot(3).isEmpty()) {
                if (checkCurrentRecipe()) {
                    // Then continue processing the recipe inventory.
                    currentProcessingTime++;
                    if (currentProcessingTime >= maxProcessingTime) {
                        processCurrentRecipe();
                    }
                }
            } else {
                currentRecipe = null;
                currentProcessingTime = 0;
            }
        } catch (Exception ex) {

        }
        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private void vacuumDroppedItems() {
    }

    private boolean checkCurrentRecipe() {
        MixingVatRecipe.MixingVatRecipeCategory category;

        List<ItemStack> inventory = new ArrayList<>();
        for (int i = 0; i < inputInventorySlots; i++) {
            if (!items.getStackInSlot(i).isEmpty()) inventory.add(items.getStackInSlot(i));
        }

        MixingVatRecipe recipe = null;

        if (!inventory.isEmpty()) {
            if (!inputFluidTankHandler.getTank(1).isEmpty()) {
                category = MixingVatRecipe.MixingVatRecipeCategory.FLUID;
            } else {
                category = MixingVatRecipe.MixingVatRecipeCategory.ITEM;
            }

            // Then we need to check of there is a valid recipe that needs to be activated.
            if (category == MixingVatRecipe.MixingVatRecipeCategory.ITEM) {
                recipe = this.getItemRecipe(inputFluidTankHandler.getTank(0).getFluid(), inventory);
            }

            if (category == MixingVatRecipe.MixingVatRecipeCategory.FLUID) {
                recipe = this.getFluidRecipe(inputFluidTankHandler.getTank(0).getFluid(),
                        inputFluidTankHandler.getTank(1).getFluid(), inventory);
            }

            if (recipe != null) {
                if (recipe.getId() != currentRecipe.getId()) {
                    this.currentRecipe = recipe;
                    this.currentProcessingTime = 0;
                    this.maxProcessingTime = recipe.getProcessingTime();
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void processCurrentRecipe() {

    }

    public boolean isHeated() {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, pos);
        return BlockTags.getCollection().get(
                new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                        growthcraft.core.shared.Reference.TAG_HEATSOURCES)).contains(blockMap.get("down"));
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
        return this.inventoryItemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.inventoryItemStacks = nonNullList;
    }

    @Override
    public int getSizeInventory() {
        return this.inventoryItemStacks.size();
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

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        this.currentProcessingTime = compound.getInt("CurrentProcessingTime");
        this.maxProcessingTime = compound.getInt("MaxProcessingTime");

        this.inputFluidTankHandler.getTank(0).readFromNBT(compound.getCompound("tank0"));
        this.inputFluidTankHandler.getTank(1).readFromNBT(compound.getCompound("tank1"));
        this.outputFluidTankHandler.getTank(0).readFromNBT(compound.getCompound("tank2"));

        this.inventoryItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.inventoryItemStacks);
        }
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
        compound.put("tank1", this.inputFluidTankHandler.getTank(1).writeToNBT(new CompoundNBT()));
        compound.put("tank2", this.outputFluidTankHandler.getTank(0).writeToNBT(new CompoundNBT()));

        ItemStackHelper.saveAllItems(compound, this.inventoryItemStacks);

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
        for (int i = 0; i < inputInventorySlots; i++) {
            if (items.getStackInSlot(i).isEmpty()) {
                items.setStackInSlot(i, itemStack);
                if (!items.getStackInSlot(i).isEmpty()) return true;
            }
        }

        return false;
    }

    public void onEntityCollision(Entity entity) {
        if (entity instanceof ItemEntity && entity.getPosition().getX() == this.getPos().getX() && entity.getPosition().getZ() == this.getPos().getZ()) {
            ItemStack collisionItemStack = ((ItemEntity) entity).getItem();
            if (collisionItemStack.getCount() >= 1) {
                if (this.tryPlaceItemInventory(collisionItemStack)) {
                    ((ItemEntity) entity).getItem().shrink(1);
                }
            }
        }
    }
}

package growthcraft.milk.common.tileentity;

import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.lib.common.tank.handler.FluidTankHandler;
import growthcraft.lib.util.BlockStateUtils;
import growthcraft.lib.util.RecipeUtils;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.client.container.MixingVatContainer;
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
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

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
    private ItemStack activationTool;
    private final GrowthcraftItemHandler inventory;
    private boolean activated = false;
    private boolean heated = false;
    private ItemStack resultActivationTool;

    private final int inputInventorySlots = 3;
    private FluidTankHandler outputFluidTankHandler;

    public MixingVatTileEntity() {
        this(GrowthcraftMilkTileEntities.MIXING_VAT_TILE_ENTITY.get());
    }

    public MixingVatTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.inventory = new GrowthcraftItemHandler(4);
        this.createFluidTanks();
    }

    private void createFluidTanks() {
        this.inputFluidTankHandler = new FluidTankHandler(2, 4000);
        this.outputFluidTankHandler = new FluidTankHandler(1, 1000);
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
            if (!inputFluidTankHandler.getTank(0).isEmpty()
                    && this.isHeated()
                    && !outputFluidTankHandler.getTank(0).isEmpty()
                    && inventory.getStackInSlot(3).isEmpty()
            ) {
                // Then we should check for a MixingVatRecipe.MixingVatRecipeCategory.FLUID
                if (checkCurrentRecipe()) {
                    // Then continue processing the recipe inventory.
                    currentProcessingTime++;
                    if (currentProcessingTime >= maxProcessingTime) {
                        processCurrentRecipe();
                    }
                }
            } else if (!inputFluidTankHandler.getTank(0).isEmpty()
                    && outputFluidTankHandler.getTank(0).isEmpty()
                    && !inventory.getStackInSlot(0).isEmpty()) {
                // Then we need to process MixingVatRecipe.MixingVatRecipeCategory.ITEM
                if (checkCurrentRecipe()) {
                    // Then continue processing the recipe inventory.
                    currentProcessingTime++;
                    if (currentProcessingTime >= maxProcessingTime) {
                        processCurrentRecipe();
                    }
                }
            } else if (inputFluidTankHandler.getTank(0).isEmpty()
                    && outputFluidTankHandler.getTank(0).isEmpty()
                    && !inventory.getStackInSlot(3).isEmpty()) {
                // Then we need to wait for resultActivation

            } else {
                this.resetAllProcessing();
            }
        } catch (Exception ex) {

        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private boolean checkCurrentRecipe() {
        MixingVatRecipe.MixingVatRecipeCategory category;

        List<ItemStack> currentItems = new ArrayList<>();
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) currentItems.add(inventory.getStackInSlot(i));
        }

        MixingVatRecipe recipe = null;

        if (!currentItems.isEmpty()) {
            if (!outputFluidTankHandler.getTank(0).isEmpty()) {
                category = MixingVatRecipe.MixingVatRecipeCategory.FLUID;
            } else {
                category = MixingVatRecipe.MixingVatRecipeCategory.ITEM;
            }

            // Then we need to check of there is a valid recipe that needs to be activated.
            if (category == MixingVatRecipe.MixingVatRecipeCategory.ITEM) {
                GrowthcraftMilk.LOGGER.warn("We should still be checking for an Item based recipe.");
                recipe = this.getItemRecipe(inputFluidTankHandler.getTank(0).getFluid(), currentItems);
                GrowthcraftMilk.LOGGER.warn("We have a item recipe!" + recipe.getId().toString());
            }

            if (category == MixingVatRecipe.MixingVatRecipeCategory.FLUID) {
                recipe = this.getFluidRecipe(inputFluidTankHandler.getTank(0).getFluid(),
                        outputFluidTankHandler.getTank(0).getFluid(), currentItems);
            }

            if (recipe != null) {
                if (recipe != this.currentRecipe) {
                    this.currentRecipe = recipe;
                    this.currentProcessingTime = 0;
                    this.maxProcessingTime = recipe.getProcessingTime();
                    this.activationTool = recipe.getActivationTool();
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void processCurrentRecipe() {
        MixingVatRecipe.MixingVatRecipeCategory category = this.currentRecipe.getCategory();

        if (category == MixingVatRecipe.MixingVatRecipeCategory.FLUID) {
            // Cast to a MixingVatFluidRecipe
            MixingVatFluidRecipe fluidRecipe = (MixingVatFluidRecipe) this.currentRecipe;

            // Drain recipe input fluids
            this.inputFluidTankHandler.getTank(0).drain(
                    fluidRecipe.getInputFluidStack().getAmount(), IFluidHandler.FluidAction.EXECUTE
            );
            this.outputFluidTankHandler.getTank(0).drain(
                    fluidRecipe.getReagentFluidStack().getAmount(), IFluidHandler.FluidAction.EXECUTE
            );
            // Process the ingredient items
            List<ItemStack> ingredients = fluidRecipe.getIngredientList();
            for (ItemStack itemStack : ingredients) {
                for (int i = 0; i < this.getInventory().getSlots(); i++) {
                    if (this.getInventory().getStackInSlot(i).getItem() == itemStack.getItem()) {
                        this.getInventory().getStackInSlot(i).shrink(1);
                    }
                }
            }

            // Insert the output fluid and the wast fluid.
            this.inputFluidTankHandler.getTank(0).setFluid(fluidRecipe.getOutputFluidStack());
            this.outputFluidTankHandler.getTank(0).setFluid((fluidRecipe.getWasteFluidStack()));

        } else if (category == MixingVatRecipe.MixingVatRecipeCategory.ITEM) {
            MixingVatItemRecipe itemRecipe = (MixingVatItemRecipe) this.currentRecipe;

            // Drain recipe input fluids.
            this.inputFluidTankHandler.getTank(0).drain(
                    itemRecipe.getInputFluidStack().getAmount(), IFluidHandler.FluidAction.EXECUTE
            );
            // Process the ingredient items.
            List<ItemStack> ingredients = itemRecipe.getIngredientList();
            for (ItemStack itemStack : ingredients) {
                for (int i = 0; i < this.getInventory().getSlots(); i++) {
                    if (this.getInventory().getStackInSlot(i).getItem() == itemStack.getItem()) {
                        this.getInventory().getStackInSlot(i).shrink(1);
                    }
                }
            }

            // Insert the resulting item
            this.getInventory().setStackInSlot(3, itemRecipe.getResultItemStack());

            // Set the Result Activation Item.
            this.resultActivationTool = itemRecipe.getResultActivationTool();
            this.activationTool = null;
        }

        this.resetAllProcessing();

    }

    public boolean isHeated() {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, pos);
        this.heated = BlockTags.getCollection().get(
                new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                        growthcraft.core.shared.Reference.TAG_HEATSOURCES)).contains(blockMap.get("down"));
        return this.heated;
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

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new MixingVatContainer(windowId, playerInventory, this);
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
    public int getSizeInventory() {
        return this.inventory.getSlots();
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    public FluidTank getInputFluidTank(int tankId) {
        return null;
    }

    public FluidTank getOutputFluidTank(int tankId) {
        return null;
    }

    public ItemStack getResultActivationTool() {
        return this.resultActivationTool;
    }

    private void resetAllProcessing() {
        this.currentRecipe = null;
        this.currentProcessingTime = 0;
        this.activated = false;
        this.markDirty();
    }

    public int getProcessingTime() {
        return this.currentProcessingTime;
    }

    public void setProcessingTime(int time) {
        this.currentProcessingTime = time;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        this.activated = compound.getBoolean("IsActivated");
        this.currentProcessingTime = compound.getInt("CurrentProcessingTime");
        this.maxProcessingTime = compound.getInt("MaxProcessingTime");

        this.inputFluidTankHandler.getTank(0).readFromNBT(compound.getCompound("tank0"));
        this.inputFluidTankHandler.getTank(1).readFromNBT(compound.getCompound("tank1"));
        this.outputFluidTankHandler.getTank(0).readFromNBT(compound.getCompound("tank2"));

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

        compound.putBoolean("IsActivated", this.activated);
        compound.putInt("CurrentProcessingTime", this.currentProcessingTime);
        compound.putInt("MaxProcessingTime", this.maxProcessingTime);

        compound.put("tank0", this.inputFluidTankHandler.getTank(0).writeToNBT(new CompoundNBT()));
        compound.put("tank1", this.inputFluidTankHandler.getTank(1).writeToNBT(new CompoundNBT()));
        compound.put("tank2", this.outputFluidTankHandler.getTank(0).writeToNBT(new CompoundNBT()));

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
        for (int i = 0; i < inputInventorySlots; i++) {
            if (this.getInventory().getStackInSlot(i).isEmpty()) {
                this.getInventory().insertItem(i, itemStack, false);
                if (!this.getInventory().getStackInSlot(i).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    public void onEntityCollision(Entity entity) {
        // If this tileentity is activated then do not pull in items.
        if (!this.activated && entity instanceof ItemEntity && entity.getPosition().getX() == this.getPos().getX() && entity.getPosition().getZ() == this.getPos().getZ()) {
            ItemStack collisionItemStack = ((ItemEntity) entity).getItem();
            if (collisionItemStack.getCount() >= 1) {
                if (this.tryPlaceItemInventory(collisionItemStack.copy())) {
                    ((ItemEntity) entity).getItem().shrink(1);
                }
            }
            this.markDirty();
        }
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {

        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && side == Direction.UP) {
            GrowthcraftMilk.LOGGER.warn(cap + " requesting cap from " + side);
            return this.outputFluidTankHandler.getFluidTankHandler(0).cast();
        }

        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && side == Direction.NORTH) {
            GrowthcraftMilk.LOGGER.warn(cap + " requesting cap from " + side);

            GrowthcraftMilk.LOGGER.warn("Interacting with input tank.");
            return this.inputFluidTankHandler.getFluidTankHandler(0).cast();
        }

        return super.getCapability(cap, side);
    }

    @Nullable
    public ItemStack getActivationTool() {
        return this.activationTool;
    }

    public boolean activateRecipe(ItemStack itemStack) {
        this.activated = this.getActivationTool().getItem() == itemStack.getItem();
        GrowthcraftMilk.LOGGER.error("TileEntity Actived");
        return this.activated;
    }

    public boolean hasResultActivationTool() {
        return this.resultActivationTool != null;
    }

    public boolean hasActivationTool() {
        return this.activationTool != null;
    }

    public boolean activateResult(ItemStack resultActivationTool) {

        return this.getResultActivationTool().getItem() == resultActivationTool.getItem();
    }

    public ItemStack getRecipeResultingItem() {
        return this.currentRecipe.getCategory() == MixingVatRecipe.MixingVatRecipeCategory.ITEM ? ((MixingVatItemRecipe) this.currentRecipe).getResultItemStack() : null;
    }

    public Boolean hasValidRecipe() {
        return this.currentRecipe != null;
    }
}

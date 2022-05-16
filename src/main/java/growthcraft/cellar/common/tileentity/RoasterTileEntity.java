package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.RoasterContainer;
import growthcraft.cellar.common.block.RoasterBlock;
import growthcraft.cellar.common.recipe.RoasterRecipe;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.BlockStateUtils;
import growthcraft.lib.util.RecipeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

public class RoasterTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int maxProcessingTime;
    private int currentProcessingTicks = 0;
    private ITextComponent customName;
    private ItemStack currentRecipeOutput;

    //private final GrowthcraftItemHandler inventory;

    private final IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    private NonNullList<ItemStack> inventoryItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);

    private RoasterRecipe currentRecipe;

    public RoasterTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        //this.inventory = new GrowthcraftItemHandler(3);
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.itemHandler != null) {
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }

    public IItemHandler getInventory() {
        return this.items;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.inventoryItemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventoryItemStacks = itemsIn;
    }

    public RoasterTileEntity() {
        this(GrowthcraftCellarTileEntities.roaster_tileentity.get());
    }

    @Override
    public void tick() {
        boolean dirty = false;

        if (world != null && !world.isRemote) {
            if (!this.items.getStackInSlot(0).isEmpty() && this.isHeated()) {

                RoasterRecipe recipe = this.getRecipe(
                        this.items.getStackInSlot(0),
                        this.items.getStackInSlot(1)
                );

                if (recipe != null && !recipe.equals(this.currentRecipe)) {
                    this.currentRecipe = recipe;
                    this.currentRecipeOutput = recipe.getOutputItem();
                }

                if (recipe != null && recipe.equals(this.currentRecipe) && this.items.getStackInSlot(2).getCount() < 64 && this.items.isItemValid(2, recipe.getOutputItem())) {
                    // Continue to process the current recipe.
                    this.maxProcessingTime = recipe.getProcessingTime();
                    this.currentProcessingTicks++;
                    dirty = true;
                } else if (this.currentRecipe == null && recipe != null) {
                    // Set the current recipe and start processing next tick.
                    this.maxProcessingTime = recipe.getProcessingTime();
                    this.currentProcessingTicks = 0;
                    this.currentRecipe = recipe;
                    dirty = true;
                }

                if (this.currentRecipe != null && this.currentProcessingTicks > this.maxProcessingTime) {
                    // Current recipe is still valid and it is time to produce the result.
                    if (this.items.getStackInSlot(2).getItem().equals(recipe.getOutputItem().getItem())
                            || this.items.getStackInSlot(2).getItem().equals(Items.AIR)) {

                        this.items.getStackInSlot(0).shrink(recipe.getInputItemStack().getCount());
                        this.items.insertItem(2, recipe.getOutputItem(), false);

                        this.currentProcessingTicks = 0;
                        this.maxProcessingTime = 0;
                        dirty = true;
                    }
                }

            } else {
                this.currentProcessingTicks = 0;
                this.maxProcessingTime = 0;
            }

        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    // Custom Name Handling
    @Override
    public ITextComponent getDisplayName() {
        return this.getName() != null ? this.getName() : this.getDefaultName();
    }

    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    protected ITextComponent getDefaultName() {
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.ROASTER);
        return new TranslationTextComponent(translationKey);
    }

    // Interactive GUI
    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new RoasterContainer(windowId, playerInventory, this);
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new RoasterContainer(windowId, playerInventory, this);
    }

    // NBT Data Handling
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

        this.currentProcessingTicks = compound.getInt("CurrentProcessingTicks");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventoryItemStacks);

        compound.putInt("CurrentProcessingTicks", this.currentProcessingTicks);

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

        boolean heated = BlockTags.getCollection().get(
                new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                        growthcraft.core.shared.Reference.TAG_HEATSOURCES)).contains(blockMap.get("down"));

        this.world.setBlockState(this.getPos(), this.getBlockState().with(RoasterBlock.LIT, heated));

        return heated;
    }

    // Inventory
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return super.getCapability(cap, side);
    }

    @Override
    public int getSizeInventory() {
        return this.inventoryItemStacks.size();
    }

    // Recipe Handling
    @Nullable
    private RoasterRecipe getRecipe(ItemStack inputItemStack, ItemStack redstoneTimerItemStack) {
        Set<IRecipe<?>> recipes = RecipeUtils.findRecipesByType(GrowthcraftCellarRecipes.ROASTER_RECIPE_TYPE);

        for (IRecipe<?> recipe : recipes) {
            RoasterRecipe roasterRecipe = (RoasterRecipe) recipe;
            if (roasterRecipe.matches(inputItemStack, redstoneTimerItemStack)) {
                return roasterRecipe;
            }
        }
        return null;
    }

    // Getters and Setters
    public int getCurrentProcessingTime() {
        return this.currentProcessingTicks;
    }

    public void setCurrentProcessingTime(int ticks) {
        this.currentProcessingTicks = ticks;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    public void setMaxProcessingTime(int i) {
        this.maxProcessingTime = i;
    }

}

package growthcraft.apiary.common.tileentity;

import growthcraft.apiary.client.container.BeeBoxContainer;
import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.apiary.init.GrowthcraftApiaryTileEntities;
import growthcraft.apiary.init.config.GrowthcraftApiaryConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class BeeBoxTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final IItemHandlerModifiable items = createHandler();
    private int currentProcessingTime;
    private ITextComponent customName;
    private NonNullList<ItemStack> inventoryItemStacks = NonNullList.withSize(28, ItemStack.EMPTY);
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    private int maxProcessingTime;

    public BeeBoxTileEntity() {
        this(GrowthcraftApiaryTileEntities.BEE_BOX_TILE_ENTITY.get());
    }

    protected BeeBoxTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
        this.maxProcessingTime = GrowthcraftApiaryConfig.getBeeBoxMaxProcessingTime();
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

    @Override
    public void tick() {
        boolean dirty = false;

        if (world != null && !world.isRemote && !this.items.getStackInSlot(0).isEmpty()) {
            SecureRandom random = new SecureRandom();

            if (currentProcessingTime >= maxProcessingTime) {
                int workers = this.items.getStackInSlot(0).getCount();

                // Try and increase the bee population.
                if (workers < 64 && random.nextInt(100) <= GrowthcraftApiaryConfig.getChanceToIncreaseBees()) {
                    this.items.getStackInSlot(0).grow(1);
                }

                // For each worker bee try to do a task
                for (int i = 0; i < workers; i++) {
                    int jobID = random.nextInt(3);

                    switch (jobID) {
                        case 1:
                            // Check for comb conversion
                            int slotNeedsCombConversion = getSlotWithVanillaHoneyComb();

                            if (slotNeedsCombConversion < 0) {
                                slotNeedsCombConversion = getSlotWithEmptyHoneyComb();
                            }

                            // Try and fill an empty honey comb.
                            if (slotNeedsCombConversion > 0) {
                                this.inventoryItemStacks.set(slotNeedsCombConversion,
                                        new ItemStack(GrowthcraftApiaryItems.HONEY_COMB_FULL.get()));
                            }
                            break;
                        case 2:
                            // Try and duplicate any FlowerBlock
                            if (GrowthcraftApiaryConfig.shouldReplicateFlowers()) {
                                // search the surrounding area for FlowerBlock.
                                tryReplicateFlower(this.getPos());
                            }
                            break;
                        default:
                            // Add new empty honey comb.
                            int emptySlotID = getEmptySlot();
                            if (emptySlotID > 0) {
                                this.inventoryItemStacks.set(emptySlotID,
                                        new ItemStack(GrowthcraftApiaryItems.HONEY_COMB_EMPTY.get()));
                            }

                    }
                }

                currentProcessingTime = 0;
                dirty = true;
            } else {
                currentProcessingTime++;
            }
        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private int getSlotWithVanillaHoneyComb() {
        for (int slotID = 0; slotID < this.items.getSlots(); slotID++) {
            if (this.inventoryItemStacks.get(slotID).getItem() == Items.HONEYCOMB) return slotID;
        }
        return -1;
    }

    private int getSlotWithEmptyHoneyComb() {
        for (int slotID = 0; slotID < this.items.getSlots(); slotID++) {
            if (this.inventoryItemStacks.get(slotID).getItem() == GrowthcraftApiaryItems.HONEY_COMB_EMPTY.get())
                return slotID;
        }
        return -1;
    }

    private int getEmptySlot() {
        // First output slot is slotID == 1
        for (int slotID = 1; slotID < this.items.getSlots(); slotID++) {
            if (this.inventoryItemStacks.get(slotID).isEmpty()) return slotID;
        }
        return -1;
    }

    private void tryReplicateFlower(BlockPos pos) {
        SecureRandom random = new SecureRandom();

        int maxFlowerRange = GrowthcraftApiaryConfig.getBeeBoxFlowerRange();
        BlockPos posLowerBound = pos.south(maxFlowerRange).west(maxFlowerRange);
        BlockPos posUpperBound = pos.north(maxFlowerRange).east(maxFlowerRange);

        List<BlockPos> flowerBlocks = new ArrayList<>();
        List<BlockPos> emptyBlocks = new ArrayList<>();

        Iterable<BlockPos> blocksWithinRange = BlockPos.getAllInBoxMutable(posLowerBound, posUpperBound);
        for (BlockPos blockPos : blocksWithinRange) {
            if (BlockTags.getCollection().get(
                    new ResourceLocation("minecraft",
                            "flowers")).contains(world.getBlockState(blockPos).getBlock())) {
                flowerBlocks.add(blockPos.toImmutable());
            } else if (world.getBlockState(blockPos).getBlock() == Blocks.AIR &&
                    world.getBlockState(blockPos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                emptyBlocks.add(blockPos.toImmutable());
            }
        }

        if (random.nextInt(100) <= GrowthcraftApiaryConfig.getChanceToReplicateFlowers()) {
            try {
                if (!flowerBlocks.isEmpty() && !emptyBlocks.isEmpty()) {
                    int randomSourceID = random.nextInt(flowerBlocks.size());
                    int randomTargetID = random.nextInt(emptyBlocks.size());

                    BlockPos randomSourceBlockPos = flowerBlocks.get(randomSourceID);
                    BlockPos randomEmptyBlockPos = emptyBlocks.get(randomTargetID);

                    BlockState sourceBlockState = world.getBlockState(randomSourceBlockPos);
                    world.setBlockState(randomEmptyBlockPos, sourceBlockState, Constants.BlockFlags.DEFAULT);
                }
            } catch (Exception ex) {
                // Do nothing, just ignore if there's an exception thrown.
            }
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

    @Override
    public ITextComponent getDisplayName() {
        return this.getName() != null ? this.getName() : this.getDefaultName();
    }

    @Override
    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    protected ITextComponent getDefaultName() {
        return this.getBlockState().getBlock().getTranslatedName();
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BeeBoxContainer(windowId, playerInventory, this);
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new BeeBoxContainer(windowId, playerInventory, this);
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

        this.currentProcessingTime = compound.getInt("CurrentProcessingTicks");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventoryItemStacks);

        compound.putInt("CurrentProcessingTicks", this.currentProcessingTime);

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
        return super.getCapability(cap, side);
    }

    @Override
    public int getSizeInventory() {
        return this.inventoryItemStacks.size();
    }

    public int getCurrentProcessingTime() {
        return this.currentProcessingTime;
    }

    public void setCurrentProcessingTime(int ticks) {
        this.currentProcessingTime = ticks;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    public void setMaxProcessingTime(int i) {
        this.maxProcessingTime = i;
    }
}

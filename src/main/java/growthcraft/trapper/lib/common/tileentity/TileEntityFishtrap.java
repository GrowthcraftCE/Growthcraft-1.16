package growthcraft.trapper.lib.common.tileentity;

import growthcraft.lib.utils.BlockStateUtils;
import growthcraft.lib.utils.TickUtils;
import growthcraft.trapper.lib.common.block.FishtrapBlock;
import growthcraft.trapper.lib.common.inventory.ContainerFishtrap;
import growthcraft.trapper.shared.Reference;
import growthcraft.trapper.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TileEntityFishtrap extends LockableLootTileEntity implements ISidedInventory, ITickableTileEntity {

    private final IItemHandlerModifiable items = createHandler();
    private final int minTickFishing = TickUtils.toTicks(10, "seconds");
    private final int maxTickFishing = TickUtils.toTicks(1, "minutes");
    protected int numPlayersUsing;
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    private final LazyOptional<IItemHandlerModifiable>[] itemHandlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    private NonNullList<ItemStack> inventoryItemStacks = NonNullList.withSize(7, ItemStack.EMPTY);
    private LootTable lootTable;
    private int tickCounter = 0;
    private int randomTickCooldown = 0;

    public TileEntityFishtrap(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        randomTickCooldown = getRandomTickCooldown();
    }

    public static void swapContents(TileEntityFishtrap tileEntityFishtrapOne, TileEntityFishtrap tileEntityFishtrapTwo) {
        NonNullList<ItemStack> list = tileEntityFishtrapOne.getItems();
        tileEntityFishtrapOne.setItems(tileEntityFishtrapTwo.getItems());
        tileEntityFishtrapTwo.setItems(list);
    }

    private int getRandomTickCooldown() {
        return new Random().nextInt(maxTickFishing - minTickFishing) + minTickFishing;
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.itemHandler != null) {
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nonnull Direction side) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.UP) {
                return itemHandlers[0].cast();
            } else if (side == Direction.DOWN) {
                return itemHandlers[1].cast();
            } else {
                return itemHandlers[2].cast();
            }
            //return itemHandler.cast();
        }
        return super.getCapability(capability, side);
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    @Override
    public void remove() {
        super.remove();
        if (itemHandler != null) {
            itemHandler.invalidate();
        }
    }

    @Override
    public int getSizeInventory() {
        return 7;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventoryItemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventoryItemStacks = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.growthcraft_trapper.fishtrap");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ContainerFishtrap(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.inventoryItemStacks);
        }
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.inventoryItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.inventoryItemStacks);
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
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            } else {
                ++this.numPlayersUsing;
                this.onOpenOrClose();
            }
        }
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof FishtrapBlock) {
            assert this.world != null;
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

    @Override
    public void tick() {
        assert world != null;
        if (world.isRemote()) {
            return;
        }

        tickCounter++;
        if (tickCounter >= randomTickCooldown && canDoFishing()) {
            doFishing();
            tickCounter = 0;
            randomTickCooldown = getRandomTickCooldown();
        }
    }

    private LootTable getLootTable(boolean fortune) {
        LootTableManager lootTableManager = ServerLifecycleHooks.getCurrentServer().getLootTableManager();

        return fortune ? lootTableManager.getLootTableFromLocation(LootTables.GAMEPLAY_FISHING_TREASURE)
                : lootTableManager.getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
    }

    private boolean canDoFishing() {
        World world = this.getWorld();
        assert world != null;

        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, pos);

        // Scenario 1 - BlockUp and BlockDown are water.
        if (blockMap.get("down") instanceof FlowingFluidBlock
                && blockMap.get("up") instanceof FlowingFluidBlock) {
            return true;
        }

        // Scenario 2 - BlockNorth, BlockEast, BlockSouth, and BlockWest are water.
        if (blockMap.get("north") instanceof FlowingFluidBlock
                && blockMap.get("east") instanceof FlowingFluidBlock
                && blockMap.get("south") instanceof FlowingFluidBlock
                && blockMap.get("west") instanceof FlowingFluidBlock) {
            return true;
        }

        // Scenario 3 - Horizontal blocks are Water and Block is WATERLOGGED.
        boolean eastWest = blockMap.get("east") instanceof FlowingFluidBlock
                && blockMap.get("west") instanceof FlowingFluidBlock;
        boolean northSouth = blockMap.get("north") instanceof FlowingFluidBlock
                && blockMap.get("south") instanceof FlowingFluidBlock;

        return (eastWest || northSouth) && this.getBlockState().get(BlockStateProperties.WATERLOGGED).equals(Boolean.TRUE);
    }

    private void doFishing() {
        // Check for bait in slot 0.
        ItemStack bait = inventoryItemStacks.get(0);
        boolean isBait = ItemTags.getCollection().get(new ResourceLocation(Reference.MODID, UnlocalizedName.TAG_FISHING_BAIT)).contains(bait.getItem());
        lootTable = isBait ? getLootTable(true) : getLootTable(false);

        List<ItemStack> lootedItems = getLootItemStack();
        for (ItemStack itemStack : lootedItems) {
            if (!isInventoryFull()) {
                addStackToInventory(itemStack, false);
            }
        }
        inventoryItemStacks.get(0).shrink(1);

    }

    private ItemStack addStackToInventory(ItemStack stack, boolean simulate) {
        ItemStack remainder = stack;
        for (int slot = 1; slot < items.getSlots(); slot++) {
            remainder = items.insertItem(slot, stack, simulate);
            if (remainder == ItemStack.EMPTY) break;
        }
        return remainder;
    }

    private boolean isInventoryFull() {
        int filledSlots = 0;
        for (int slotIndex = 1; slotIndex < items.getSlots(); slotIndex++) {
            if (items.getStackInSlot(slotIndex).getCount() == items.getSlotLimit(slotIndex))
                filledSlots++;
        }
        return filledSlots == items.getSlots() - 1;
    }

    private List<ItemStack> getLootItemStack() {
        assert world != null;
        return lootTable.generate(new LootContext.Builder((ServerWorld) world).withRandom(new Random()).build(LootParameterSets.EMPTY));
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return side.equals(Direction.UP) ? new int[]{0} : new int[]{1, 2, 3, 4, 5, 6};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        boolean isBait = ItemTags.getCollection().get(new ResourceLocation(Reference.MODID, UnlocalizedName.TAG_FISHING_BAIT)).contains(itemStackIn.getItem());
        return index == 0 && isBait;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return !(index == 0 && direction.equals(Direction.DOWN));
    }
}

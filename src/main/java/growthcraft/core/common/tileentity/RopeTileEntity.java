package growthcraft.core.common.tileentity;

import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.core.init.GrowthcraftTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public class RopeTileEntity extends TileEntity {

    private final GrowthcraftItemHandler inventory;

    public RopeTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new GrowthcraftItemHandler(1);
    }

    public RopeTileEntity() {
        this(GrowthcraftTileEntities.rope_tileentity.get());
    }

    public void setFenceItemStack(ItemStack stack) {
        this.inventory.insertItem(0, stack, false);
    }

    public ItemStack getFenceItemStack() {
        return this.inventory.getStackInSlot(0);
    }

    public boolean hasFenceItemStack() {
        return this.inventory.getStackInSlot(0).getCount() > 0;
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Override
    public void read(BlockState blockState, CompoundNBT compound) {
        super.read(blockState, compound);
        NonNullList<ItemStack> inv = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
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

}

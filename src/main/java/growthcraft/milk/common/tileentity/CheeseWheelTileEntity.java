package growthcraft.milk.common.tileentity;

import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.lib.common.block.CheeseWheelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public class CheeseWheelTileEntity extends TileEntity implements ITickableTileEntity {

    private boolean aged;
    private int sliceCountTop;
    private int sliceCountBottom;
    private int currentAging;
    private final int maxAging = 24000 * 3;

    public CheeseWheelTileEntity() {
        this(GrowthcraftMilkTileEntities.CHEESE_WHEEL_TILE_ENTITY.get());
    }
    public CheeseWheelTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.currentAging = 0;
        // TODO: Implement aging process
        this.aged = true;
        this.sliceCountBottom = 4;
        this.sliceCountTop = 0;
    }

    @Override
    public void tick() {
        if (world != null && !world.isRemote() && Boolean.FALSE.equals(world.getBlockState(pos).get(CheeseWheelBlock.AGED))) {
            if (this.currentAging < this.maxAging) {
                this.currentAging++;
            } else {
                BlockState state = this.world.getBlockState(pos);
                this.world.setBlockState(pos, state.with(CheeseWheelBlock.AGED, true));
                this.currentAging = 0;
            }
            this.markDirty();
        } else {
            // The cheese is aged, so there's nothing to do.
        }
    }

    @Override
    public void markDirty() {
        this.setBlockState();
        super.markDirty();
    }

    private void setBlockState() {
        this.world.setBlockState(this.getPos(), this.getBlockState()
                .with(CheeseWheelBlock.SLICE_COUNT_TOP, this.sliceCountTop)
                .with(CheeseWheelBlock.SLICE_COUNT_BOTTOM, this.sliceCountBottom)
        );
    }

    public boolean canTakeSlice() {
        return this.aged && this.sliceCountBottom > 0;
    }

    public void takeSlice() {
        this.takeSlice(1);
    }

    public void takeSlice(int count) {
        if (this.sliceCountTop > 0) {
            this.sliceCountTop -= count;
        } else if (this.sliceCountBottom > 0) {
            this.sliceCountBottom -= count;
        }
        this.markDirty();
    }

    public void addSlice(int count) {
        if (this.sliceCountTop == 0) {
            this.sliceCountTop = 4;
        }
        this.markDirty();
    }


    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);

        this.sliceCountTop = compound.getInt("SlicesTop");
        this.sliceCountTop = compound.getInt("SlicesBottom");
        this.aged = compound.getBoolean("Aged");
        this.currentAging = compound.getInt("CurrentAging");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        compound.putInt("SlicesTop", this.sliceCountTop);
        compound.putInt("SlicesBottom", this.sliceCountBottom);
        compound.putBoolean("Aged", this.aged);
        compound.putInt("CurrentAging", this.currentAging);

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

    public int getSliceCount() {
        return this.sliceCountBottom + this.sliceCountTop;
    }
}

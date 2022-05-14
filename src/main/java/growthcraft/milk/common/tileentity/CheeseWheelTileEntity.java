package growthcraft.milk.common.tileentity;

import growthcraft.milk.common.recipe.CheesePressRecipe;
import growthcraft.milk.init.GrowthcraftMilkRecipes;
import growthcraft.milk.init.GrowthcraftMilkTileEntities;
import growthcraft.milk.lib.common.block.CheeseWheelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;
import java.util.List;

public class CheeseWheelTileEntity extends TileEntity implements ITickableTileEntity {

    private boolean aged;
    private int sliceCountTop;
    private int sliceCountBottom;
    private int currentAging;
    private static final int MAX_AGING = 24000 * 3;

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
            if (this.currentAging < MAX_AGING) {
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
        super.markDirty();
    }

    private void setBlockState(int bottomSlices, int topSlices) {
        this.world.setBlockState(this.getPos(), this.getBlockState()
                .with(CheeseWheelBlock.SLICE_COUNT_TOP, topSlices)
                .with(CheeseWheelBlock.SLICE_COUNT_BOTTOM, bottomSlices)
        );
    }

    public boolean canTakeSlice() {
        return this.aged && this.getSliceCount() > 0;
    }

    public ItemStack takeSlice() {
        List<CheesePressRecipe> cheesePressRecipes = this.world.getRecipeManager().getRecipesForType(GrowthcraftMilkRecipes.CHEESE_PRESS_RECIPE_TYPE);

        for (CheesePressRecipe cheesePressRecipe : cheesePressRecipes) {
            if (cheesePressRecipe.matchesOutput(new ItemStack(this.world.getBlockState(this.pos).getBlock().asItem()))) {
                this.takeSlice(1);
                return cheesePressRecipe.getSliceItem();
            }
        }

        return null;
    }

    public void takeSlice(int count) {
        if (this.sliceCountTop > 0) {
            this.sliceCountTop -= count;
        } else if (this.sliceCountBottom > 0) {
            this.sliceCountBottom -= count;
        }
        this.setBlockState(this.sliceCountBottom, this.sliceCountTop);
    }

    public void addSlice(int count) {
        int newTotal = this.sliceCountBottom + this.sliceCountTop + count;

        if (newTotal > 4) {
            // Then we have enough room to add the slice to the stack.
            this.sliceCountBottom = 4;
            this.sliceCountTop = newTotal - this.sliceCountBottom;
        } else {
            this.sliceCountBottom = newTotal;
            this.sliceCountTop = 0;
        }

        this.setBlockState(this.sliceCountBottom, this.sliceCountTop);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);

        this.sliceCountTop = compound.getInt("slicestop");
        this.sliceCountBottom = compound.getInt("slicesbottom");
        this.aged = compound.getBoolean("aged");
        this.currentAging = compound.getInt("CurrentAging");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        compound.putInt("slicestop", this.sliceCountTop);
        compound.putInt("slicesbottom", this.sliceCountBottom);
        compound.putBoolean("aged", this.aged);
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

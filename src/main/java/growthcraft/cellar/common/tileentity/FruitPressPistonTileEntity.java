package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.block.FruitPressPistonBlock;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class FruitPressPistonTileEntity extends LockableLootTileEntity implements ITickableTileEntity {

    protected FruitPressPistonTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public FruitPressPistonTileEntity() {
        this(GrowthcraftCellarTileEntities.fruit_press_piston_tileentity.get());
    }

    @Override
    public void tick() {
        World world = this.world;
        BlockState state = world.getBlockState(this.getPos());
        int powerLevel = world.getRedstonePowerFromNeighbors(this.getPos());
        world.setBlockState(this.pos, state.with(FruitPressPistonBlock.PRESSED, powerLevel == 15));
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {

    }

    @Override
    protected ITextComponent getDefaultName() {
        return null;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

}
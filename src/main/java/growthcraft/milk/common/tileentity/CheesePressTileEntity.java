package growthcraft.milk.common.tileentity;

import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class CheesePressTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {

    private static final int INPUT_INVENTORY_SLOTS = 3;
    private final GrowthcraftItemHandler inventory;
    private final boolean open = true;

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {

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

    @Override
    public void tick() {

    }
}

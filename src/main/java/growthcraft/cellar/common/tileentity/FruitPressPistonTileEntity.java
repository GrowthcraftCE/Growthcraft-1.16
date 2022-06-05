package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.block.FruitPressPistonBlock;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class FruitPressPistonTileEntity extends LockableLootTileEntity implements ITickableTileEntity {

    protected FruitPressPistonTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public FruitPressPistonTileEntity() {
        this(GrowthcraftCellarTileEntities.fruit_press_piston_tileentity.get());
    }

    @Override
    public void tick() {
        if(world != null && !world.isRemote()) {
            BlockState state = world.getBlockState(this.getPos());
            int powerLevel = world.getRedstonePowerFromNeighbors(this.getPos());
            world.setBlockState(this.pos, state.with(FruitPressPistonBlock.PRESSED, powerLevel == 15));
        }
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
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.FRUIT_PRESS);
        return new TranslationTextComponent(translationKey);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    public void playSound(@Nullable PlayerEntity player, SoundEvent sound) {
        double dx = (double) this.pos.getX() + 0.5D;
        double dy = (double) this.pos.getY() + 0.5D;
        double dz = (double) this.pos.getZ() + 0.5D;

        assert this.world != null;
        this.world.playSound(player, dx, dy, dz, sound, SoundCategory.BLOCKS, 0.5F,
                this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

}

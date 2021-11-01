package growthcraft.cellar.common.item;

import growthcraft.cellar.common.tileentity.GrapeVineTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.core.Growthcraft;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class GrapeSeedsItem extends GrowthcraftItem {

    private Item fruitItem;

    public GrapeSeedsItem(Item item) {
        super();
        this.fruitItem = item;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        Block block = world.getBlockState(blockpos).getBlock();

        if (block == Blocks.FARMLAND) {
            // Place a GrapeVineCrop
            world.setBlockState(blockpos.up(), GrowthcraftCellarBlocks.GRAPE_VINE.get().getDefaultState());
            world.notifyBlockUpdate(blockpos.up(), world.getBlockState(blockpos.up()), world.getBlockState(blockpos.up()), Constants.BlockFlags.BLOCK_UPDATE);

            GrapeVineTileEntity grapeVineTileEntity = (GrapeVineTileEntity) world.getTileEntity(blockpos.up());

            if (grapeVineTileEntity != null) {
                grapeVineTileEntity.setFruitItemStack(new ItemStack(this.fruitItem, 1));
            } else {
                Growthcraft.LOGGER.error("BlockPos [" + blockpos.toString() + "] does not have a GrapeVineTileEntity!");
            }

            // Notify blocks of update
            world.notifyBlockUpdate(blockpos.up(), world.getBlockState(blockpos.up()), world.getBlockState(blockpos.up()), Constants.BlockFlags.BLOCK_UPDATE);

            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            return ActionResultType.SUCCESS;
        } else {
            return super.onItemUse(context);
        }
    }
}

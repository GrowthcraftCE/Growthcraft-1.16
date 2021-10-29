package growthcraft.core.common.item;

import growthcraft.core.Growthcraft;
import growthcraft.core.common.block.RopeBlock;
import growthcraft.core.common.tileentity.RopeTileEntity;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class RopeLinenItem extends GrowthcraftItem {

    private final RopeBlock ropeBlock;

    public RopeLinenItem(RopeBlock block) {
        this.ropeBlock = block;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        Block block = world.getBlockState(blockpos).getBlock();

        if (block.isIn(BlockTags.FENCES)) {
            // Place a rope block
            world.setBlockState(blockpos, ropeBlock.getDefaultState());
            // Set the inventory of the rope block
            RopeTileEntity ropeTileEntity = (RopeTileEntity) world.getTileEntity(blockpos);

            if (ropeTileEntity != null) {
                ropeTileEntity.setFenceItemStack(new ItemStack(block, 1));
            } else {
                Growthcraft.LOGGER.error("BlockPos [" + blockpos.toString() + "] does not have a RopeTileEntity!");
            }

            world.notifyBlockUpdate(blockpos, world.getBlockState(blockpos), world.getBlockState(blockpos), Constants.BlockFlags.BLOCK_UPDATE);

            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            return ActionResultType.SUCCESS;
        } else if (world.isAirBlock(blockpos.offset(context.getFace()))) {
            world.setBlockState(blockpos.offset(context.getFace()), ropeBlock.getDefaultState());
            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            return ActionResultType.SUCCESS;
        } else {
            return super.onItemUse(context);
        }
    }
}

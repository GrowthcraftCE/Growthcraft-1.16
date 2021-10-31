package growthcraft.cellar.common.item;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.lib.common.item.GrowthcraftBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class HopSeedsItem extends GrowthcraftBlockItem {

    public HopSeedsItem() {
        super(GrowthcraftCellarBlocks.hops_vine.get());
        GrowthcraftCellarBlocks.hops_vine.get().withSeedsItem(this);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        Block block = world.getBlockState(blockpos).getBlock();

        if (block == Blocks.FARMLAND) {
            // Place a rope block
            world.setBlockState(blockpos.up(), GrowthcraftCellarBlocks.hops_vine.get().getDefaultState());
            // Notify blocks of update
            world.notifyBlockUpdate(blockpos.up(), world.getBlockState(blockpos), world.getBlockState(blockpos), Constants.BlockFlags.BLOCK_UPDATE);

            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            return ActionResultType.SUCCESS;
        } else {
            return super.onItemUse(context);
        }
    }

}

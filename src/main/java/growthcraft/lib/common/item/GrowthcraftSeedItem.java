package growthcraft.lib.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class GrowthcraftSeedItem extends GrowthcraftItem {

    private final Block cropBlock;

    public GrowthcraftSeedItem(Block block) {
        super();
        this.cropBlock = block;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        Block block = world.getBlockState(blockpos).getBlock();

        if (block == Blocks.FARMLAND) {
            // Place a GrapeVineCrop
            world.setBlockState(blockpos.up(), cropBlock.getDefaultState());
            world.notifyBlockUpdate(blockpos.up(), world.getBlockState(blockpos.up()), world.getBlockState(blockpos.up()), Constants.BlockFlags.BLOCK_UPDATE);

            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            return ActionResultType.SUCCESS;
        } else {
            return super.onItemUse(context);
        }
    }
}

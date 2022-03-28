package growthcraft.rice.common.item;

import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.rice.init.GrowthcraftRiceBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class RiceSeedItem extends GrowthcraftItem {

    public RiceSeedItem() {
        super();
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        Block block = world.getBlockState(blockpos).getBlock();

        if (block == GrowthcraftRiceBlocks.CULTIVATED_FARMLAND.get()) {
            // Place a GrapeVineCrop
            world.setBlockState(blockpos.up(), GrowthcraftRiceBlocks.RICE_CROP.get().getDefaultState());
            world.notifyBlockUpdate(blockpos.up(), world.getBlockState(blockpos.up()), world.getBlockState(blockpos.up()), Constants.BlockFlags.BLOCK_UPDATE);

            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            return ActionResultType.SUCCESS;
        } else {
            return super.onItemUse(context);
        }
    }
}

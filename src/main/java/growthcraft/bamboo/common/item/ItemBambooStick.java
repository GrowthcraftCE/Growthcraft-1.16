package growthcraft.bamboo.common.item;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBambooStick extends GrowthcraftItem {

    public ItemBambooStick() {
        super();
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        Block targetBlock = blockstate.getBlock();
        if (targetBlock != null && targetBlock instanceof GrassBlock) {
            PlayerEntity playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote) {
                world.setBlockState(blockpos.up(), GrowthcraftBambooBlocks.bambooTreeSapling.get().getDefaultState(), 11);
                if (playerentity != null) {
                    context.getItem().shrink(1);
                }
            }
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }
}

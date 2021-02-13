package growthcraft.grapes.common.item;

import growthcraft.grapes.common.block.BlockGrapeVine;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGrapeSeeds extends GrowthcraftItem {

    private final BlockGrapeVine grapeVine;

    public ItemGrapeSeeds(BlockGrapeVine grapeVine) {
        super();
        this.grapeVine = grapeVine;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        Block targetBlock = blockstate.getBlock();
        if (targetBlock instanceof FarmlandBlock) {
            PlayerEntity playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote) {
                world.setBlockState(blockpos.up(), grapeVine.getDefaultState(), 11);
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

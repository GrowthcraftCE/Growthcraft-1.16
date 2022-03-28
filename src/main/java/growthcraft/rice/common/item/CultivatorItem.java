package growthcraft.rice.common.item;

import growthcraft.core.shared.Reference;
import growthcraft.rice.init.GrowthcraftRiceBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class CultivatorItem extends HoeItem {

    public CultivatorItem() {
        super(ItemTier.IRON, 6, 0.0F, getInitProperties());
    }

    private static Item.Properties getInitProperties() {
        Item.Properties properties = new Item.Properties();
        properties.addToolType(ToolType.HOE, ItemTier.IRON.getHarvestLevel());
        properties.group(Reference.growthcraftCreativeTab);
        properties.maxStackSize(1);
        return properties;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
        if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
            BlockState blockstate = world.getBlockState(blockpos).getToolModifiedState(world, blockpos, context.getPlayer(), context.getItem(), net.minecraftforge.common.ToolType.HOE);
            if (blockstate != null) {
                PlayerEntity playerentity = context.getPlayer();

                world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote) {
                    if (blockstate.getBlock() == Blocks.FARMLAND) {
                        world.setBlockState(blockpos, blockstate, 11);
                    }
                }

                return ActionResultType.func_233537_a_(world.isRemote);
            } else if (world.getBlockState(blockpos).getBlock() == Blocks.FARMLAND) {
                world.setBlockState(blockpos, GrowthcraftRiceBlocks.CULTIVATED_FARMLAND.get().getDefaultState(), 11);
            }
        }

        return ActionResultType.PASS;
    }
}

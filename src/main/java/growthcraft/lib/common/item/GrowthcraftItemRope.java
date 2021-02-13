package growthcraft.lib.common.item;

import growthcraft.core.shared.Reference;
import growthcraft.lib.common.block.GrowthcraftRopeBlock;
import growthcraft.lib.common.block.GrowthcraftRopeFenceBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

public class GrowthcraftItemRope extends GrowthcraftItem {

    GrowthcraftRopeBlock ropeBlock;

    public GrowthcraftItemRope(GrowthcraftRopeBlock ropeBlock) {
        this.ropeBlock = ropeBlock;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();

        Block block = world.getBlockState(pos).getBlock();
        Collection<Block> tagRope = BlockTags.getCollection().getOrCreate(Reference.TAG_ROPE_FENCE).getAllElements();

        // Try and replace the block if it is able to be converted to a rope fence block.
        for (Block tagRopeBlock : tagRope) {
            GrowthcraftRopeFenceBlock ropeFenceBlock = (GrowthcraftRopeFenceBlock) ForgeRegistries.BLOCKS.getValue(tagRopeBlock.getRegistryName());
            if (block == ropeFenceBlock.getBaseFenceBlock()) {
                world.setBlockState(pos, ropeFenceBlock.getDefaultState());
                context.getPlayer().getHeldItem(context.getHand()).shrink(1);
                return ActionResultType.SUCCESS;
            }
        }

        if (world.isAirBlock(pos.offset(context.getFace()))) {
            world.setBlockState(pos.offset(context.getFace()), ropeBlock.getDefaultState());
            context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            return ActionResultType.SUCCESS;
        }

        return super.onItemUse(context);
    }
}

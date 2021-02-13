package growthcraft.lib.utils;

import growthcraft.core.shared.Reference;
import growthcraft.lib.common.block.rope.IBlockRope;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;

public class RopeUtils {

    /**
     * Is the given block an instance of IBlockRope or in the Tag group.
     *
     * @param block Block to check.
     * @return
     */
    public static boolean isRopeBlock(Block block) {
        Tag<Block> tagRope = BlockTags.getCollection().getOrCreate(Reference.TAG_ROPE);
        return tagRope.contains(block) || block instanceof IBlockRope;
    }

}

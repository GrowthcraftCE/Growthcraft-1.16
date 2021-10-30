package growthcraft.lib.util;

import growthcraft.core.common.block.RopeBlock;
import growthcraft.core.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class BlockStateUtils {

    public static Map<String, Block> getSurroundingBlocks(IBlockReader world, BlockPos pos) {
        Map<String, Block> blockMap = new HashMap<>();
        blockMap.put("north", world.getBlockState(pos.north()).getBlock());
        blockMap.put("east", world.getBlockState(pos.east()).getBlock());
        blockMap.put("south", world.getBlockState(pos.south()).getBlock());
        blockMap.put("west", world.getBlockState(pos.west()).getBlock());
        blockMap.put("up", world.getBlockState(pos.up()).getBlock());
        blockMap.put("down", world.getBlockState(pos.down()).getBlock());
        return blockMap;
    }

    public static Map<String, Block> getSurroundingBlocks(World world, BlockPos pos) {
        Map<String, Block> blockMap = new HashMap<>();
        blockMap.put("north", world.getBlockState(pos.north()).getBlock());
        blockMap.put("east", world.getBlockState(pos.east()).getBlock());
        blockMap.put("south", world.getBlockState(pos.south()).getBlock());
        blockMap.put("west", world.getBlockState(pos.west()).getBlock());
        blockMap.put("up", world.getBlockState(pos.up()).getBlock());
        blockMap.put("down", world.getBlockState(pos.down()).getBlock());
        return blockMap;
    }

    public static Map<BlockPos, BlockState> getSurroundingBlockPos(World world, BlockPos pos ) {
        Map<BlockPos, BlockState> blockMap = new HashMap<>();
        blockMap.put(pos.north(), world.getBlockState(pos.north()));
        blockMap.put(pos.east(), world.getBlockState(pos.east()));
        blockMap.put(pos.south(), world.getBlockState(pos.south()));
        blockMap.put(pos.west(), world.getBlockState(pos.west()));
        blockMap.put(pos.up(), world.getBlockState(pos.up()));
        blockMap.put(pos.down(), world.getBlockState(pos.down()));
        return blockMap;
    }

    public static Map<String, Block> getHorizontalBlocks(World world, BlockPos pos ) {
        Map<String, Block> blockMap = new HashMap<>();
        blockMap.put("north", world.getBlockState(pos.north()).getBlock());
        blockMap.put("east", world.getBlockState(pos.east()).getBlock());
        blockMap.put("south", world.getBlockState(pos.south()).getBlock());
        blockMap.put("west", world.getBlockState(pos.west()).getBlock());
        return blockMap;
    }

    public static Map<BlockPos, BlockState> getHorizontalBlockPos(World world, BlockPos pos ) {
        Map<BlockPos, BlockState> blockMap = new HashMap<>();
        blockMap.put(pos.north(), world.getBlockState(pos.north()));
        blockMap.put(pos.east(), world.getBlockState(pos.east()));
        blockMap.put(pos.south(), world.getBlockState(pos.south()));
        blockMap.put(pos.west(), world.getBlockState(pos.west()));
        return blockMap;
    }

    public static Map<BlockPos, BlockState> getVerticalBlockPos(World world, BlockPos pos ) {
        Map<BlockPos, BlockState> blockMap = new HashMap<>();
        blockMap.put(pos.up(), world.getBlockState(pos.up()));
        blockMap.put(pos.down(), world.getBlockState(pos.down()));
        return blockMap;
    }

    public static Map<String, BlockState> getSurroundingBlockStates(World world, BlockPos pos) {
        Map<String, BlockState> blockMap = new HashMap<>();
        blockMap.put("north", world.getBlockState(pos.north()));
        blockMap.put("east", world.getBlockState(pos.east()));
        blockMap.put("south", world.getBlockState(pos.south()));
        blockMap.put("west", world.getBlockState(pos.west()));
        blockMap.put("up", world.getBlockState(pos.up()));
        blockMap.put("down", world.getBlockState(pos.down()));
        return blockMap;
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity placer) {
        return Direction.getFacingFromVector(
                (float) (placer.getPosX() - clickedBlock.getX()),
                (float) (placer.getPosY() - clickedBlock.getY()),
                (float) (placer.getPosZ() - clickedBlock.getZ())
        );
    }

    public static boolean isRopeBlock(Block block) {
        try {
            ITag<Block> tagRope = BlockTags.makeWrapperTag(new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                    growthcraft.core.shared.Reference.TAG_ROPE).toString());
            return block.isIn(tagRope) || block instanceof RopeBlock;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isRopeFenceBlock(Block block) {
        try {
            ITag<Block> tagRope = BlockTags.makeWrapperTag(new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                    Reference.TAG_ROPE_FENCE).toString());
            return block.isIn(tagRope) || block instanceof RopeBlock;
        } catch (Exception ex) {
            return false;
        }
    }

}

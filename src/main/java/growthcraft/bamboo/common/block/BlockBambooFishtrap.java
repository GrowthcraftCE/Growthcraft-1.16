package growthcraft.bamboo.common.block;

import growthcraft.bamboo.init.GrowthcraftBambooTileEntities;
import growthcraft.trapper.lib.common.block.FishtrapBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockBambooFishtrap extends FishtrapBlock {
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftBambooTileEntities.bambooFishtrapTileEntity.get().create();
    }
}

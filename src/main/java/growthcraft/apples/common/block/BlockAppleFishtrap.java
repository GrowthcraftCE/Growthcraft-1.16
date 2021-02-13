package growthcraft.apples.common.block;

import growthcraft.apples.init.GrowthcraftApplesTileEntities;
import growthcraft.trapper.lib.common.block.FishtrapBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockAppleFishtrap extends FishtrapBlock {
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftApplesTileEntities.appleFishtrapTileEntity.get().create();
    }
}

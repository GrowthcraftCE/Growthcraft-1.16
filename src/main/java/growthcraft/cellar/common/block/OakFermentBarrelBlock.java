package growthcraft.cellar.common.block;

import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.lib.block.FermentationBarrelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class OakFermentBarrelBlock extends FermentationBarrelBlock {

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftCellarTileEntities.barrel_ferment_oak_tileentity.get().create();
    }
}

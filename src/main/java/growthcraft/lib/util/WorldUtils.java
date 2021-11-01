package growthcraft.lib.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class WorldUtils {

    public static void notifyBlockUpdate(World worldIn, BlockPos pos) {
        worldIn.notifyBlockUpdate(pos.up(), worldIn.getBlockState(pos.up()), worldIn.getBlockState(pos.up()), Constants.BlockFlags.BLOCK_UPDATE);
    }

}

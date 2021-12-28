package growthcraft.core.common.item;

import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class CrowbarItem extends GrowthcraftItem {

    private static final int MAX_STACK_SIZE = 1;

    public CrowbarItem() {
        super(MAX_STACK_SIZE);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getPos();
        World world = context.getWorld();

        if (player.isSneaking()) {
            world.setBlockState(pos, world.getBlockState(pos).getBlock().rotate(world.getBlockState(pos), world, pos, Rotation.CLOCKWISE_90), Constants.BlockFlags.BLOCK_UPDATE);
            return ActionResultType.SUCCESS;
        }
        return super.onItemUseFirst(stack, context);
    }

}

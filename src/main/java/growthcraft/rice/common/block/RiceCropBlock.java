package growthcraft.rice.common.block;

import growthcraft.rice.init.GrowthcraftRiceItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class RiceCropBlock extends CropsBlock {

    public RiceCropBlock() {
        super(getInitProperties());
    }

    protected IItemProvider getSeedsItem() {
        return GrowthcraftRiceItems.RICE.get();
    }

    private static AbstractBlock.Properties getInitProperties() {
        AbstractBlock.Properties properties = AbstractBlock.Properties.create(Material.PLANTS);
        properties.doesNotBlockMovement();
        properties.tickRandomly();
        properties.zeroHardnessAndResistance();
        properties.sound(SoundType.CROP);
        return properties;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(AGE) == 7) {
            int count = RANDOM.nextInt(3) + 1;
            // Spawn the random drop count
            ItemStack itemStack = new ItemStack(GrowthcraftRiceItems.RICE_STALK.get(), count);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);

            // Decrease age to 4
            worldIn.setBlockState(pos, this.withAge(0), 2);
        }
        return ActionResultType.PASS;
    }

}

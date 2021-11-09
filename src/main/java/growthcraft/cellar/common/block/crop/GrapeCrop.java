package growthcraft.cellar.common.block.crop;

import growthcraft.cellar.common.tileentity.GrapeVineTileEntity;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.lib.common.block.GrowthcraftCropsRopeBlock;
import growthcraft.lib.util.BlockStateUtils;
import growthcraft.lib.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Map;

public class GrapeCrop extends GrowthcraftCropsRopeBlock {

    protected static VoxelShape[] CUSTOM_SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D)};

    // TODO[63]: Set grapes fruit min/max via Config
    private static final int fruitMax = 3;
    private static final int fruitMin = 1;

    public GrapeCrop() {
        super(getInitProperties());
    }

    public static Properties getInitProperties() {
        Properties properties = Properties.create(Material.PLANTS);
        properties.hardnessAndResistance(0.2F, 0.2F);
        properties.harvestTool(ToolType.HOE);
        properties.tickRandomly();
        properties.sound(SoundType.PLANT);
        properties.doesNotBlockMovement();
        properties.notSolid();
        return properties;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftCellarTileEntities.grape_vine_tileentity.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public BlockState getActualBlockStateWithAge(World world, BlockPos blockPos, int age) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);

        return this.getDefaultState()
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false)
                .with(AGE, age);
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return CUSTOM_SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(AGE) == this.getMaxAge()) {
            GrapeVineTileEntity grapeVineTileEntity = (GrapeVineTileEntity) worldIn.getTileEntity(pos);
            ItemStack itemStack = grapeVineTileEntity.getInventory().getStackInSlot(1);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            // Empty out the loot inventory
            grapeVineTileEntity.getInventory().setStackInSlot(1, ItemStack.EMPTY);
            // Decrease age to 4
            worldIn.setBlockState(pos, this.getActualBlockStateWithAge(worldIn, pos, 4), 2);
        }
        return ActionResultType.PASS;
    }

    @Override
    public void doMaxAge(World worldIn, BlockPos pos, BlockState state) {
        GrapeVineTileEntity grapeVineTileEntity = (GrapeVineTileEntity) worldIn.getTileEntity(pos);

        ItemStack itemStack = grapeVineTileEntity.getFruitItemStack();
        itemStack.setCount(RANDOM.nextInt(fruitMax - fruitMin) + fruitMin);
        grapeVineTileEntity.getInventory().setStackInSlot(1, itemStack);
        WorldUtils.notifyBlockUpdate(worldIn, pos);
    }
}

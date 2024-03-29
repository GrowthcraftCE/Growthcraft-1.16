package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.CultureJarContainer;
import growthcraft.cellar.common.block.CultureJarBlock;
import growthcraft.cellar.common.recipe.CultureJarRecipe;
import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarRecipes;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CultureJarTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int maxProcessingTime;
    private int currentProcessingTicks;
    private ITextComponent customName;

    private final GrowthcraftItemHandler inventory;

    private FluidTank inputFluidTank;
    private final LazyOptional<IFluidHandler> inputFluidHandler = LazyOptional.of(
            () -> inputFluidTank
    );

    private CultureJarRecipe currentRecipe;

    public CultureJarTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        this.inventory = new GrowthcraftItemHandler(1);
        this.createFluidTanks();
        // Defaults
        this.maxProcessingTime = 600;
        this.currentProcessingTicks = 0;
    }

    public CultureJarTileEntity() {
        this(GrowthcraftCellarTileEntities.culture_jar_tileentity.get());
    }

    @Override
    public void tick() {
        boolean dirty = false;

        if (world != null && !world.isRemote) {
            if (!inputFluidTank.isEmpty()) {

                CultureJarRecipe recipe = this.getRecipe(
                        this.inventory.getStackInSlot(0),
                        inputFluidTank.getFluid(),
                        isHeated()
                );

                if (recipe != null) {
                    this.maxProcessingTime = recipe.getProcessingTime();
                    if (recipe != this.currentRecipe) {
                        this.currentRecipe = recipe;
                        // wait until next cycle to start processing
                    } else if (this.currentProcessingTicks >= this.maxProcessingTime) {
                        processRecipeResult();
                        // reset counters
                        this.currentRecipe = null;
                        this.currentProcessingTicks = 0;
                    } else {
                        this.currentProcessingTicks++;
                    }
                    dirty = true;
                } else {
                    // Do nothing we do not have a valid recipe.
                }
            }
        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }

    }

    private void processRecipeResult() {
        this.inputFluidTank.drain(currentRecipe.getInputFluidStack().getAmount(), IFluidHandler.FluidAction.EXECUTE);
        this.inventory.getStackInSlot(0).grow(currentRecipe.getInputItem().getCount());
    }

    // TileEntityRenderer Distance
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return 256.0D;
    }

    // Custom Name Handling
    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    public void setCustomName(ITextComponent name) {
        this.customName = name;
    }

    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    private ITextComponent getDefaultName() {
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.CULTURE_JAR);
        return new TranslationTextComponent(translationKey);
    }

    // Interactive GUI
    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new CultureJarContainer(windowId, playerInventory, this);
    }

    // NBT Data Handling
    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        NonNullList<ItemStack> inv = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);

        this.currentProcessingTicks = compound.getInt("CurrentProcessingTicks");

        inputFluidTank.readFromNBT(compound.getCompound("inputTank"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentProcessingTicks", this.currentProcessingTicks);

        CompoundNBT inputTankNBT = inputFluidTank.writeToNBT(new CompoundNBT());
        compound.put("inputTank", inputTankNBT);

        return super.write(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return nbt;
    }

    // Heat Sources
    public boolean isHeated() {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, pos);

        boolean heated = BlockTags.getCollection().get(
                new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                        growthcraft.core.shared.Reference.TAG_HEATSOURCES)).contains(blockMap.get("down"));

        this.world.setBlockState(this.getPos(), this.getBlockState().with(CultureJarBlock.LIT, heated));

        return heated;
    }

    // Inventory
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return inputFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    // Fluid Tanks
    private void createFluidTanks() {
        this.inputFluidTank = new FluidTank(1000);
    }

    public FluidTank getInputFluidTank(int slot) {
        return inputFluidTank;
    }

    // Recipe Handling
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> cultureJarRecipeType, World world) {
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(cultureJarRecipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> cultureJarRecipeType) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(cultureJarRecipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @Nullable
    private CultureJarRecipe getRecipe(ItemStack itemStack, FluidStack fluidStack, boolean requiresHeatSource) {
        List<CultureJarRecipe> recipes = this.world.getRecipeManager().getRecipesForType(GrowthcraftCellarRecipes.CULTURE_JAR_RECIPE_TYPE);

        for (CultureJarRecipe recipe : recipes) {
            if (recipe.matches(itemStack, fluidStack, requiresHeatSource)) return recipe;
        }
        return null;
    }

    // Getters and Setters
    public int getCurrentProcessingTime() {
        return this.currentProcessingTicks;
    }

    public void setCurrentProcessingTime(int ticks) {
        this.currentProcessingTicks = ticks;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }
}

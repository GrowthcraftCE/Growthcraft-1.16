package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.BrewKettleContainer;
import growthcraft.cellar.common.block.BrewKettleBlock;
import growthcraft.cellar.common.recipe.BrewKettleRecipe;
import growthcraft.cellar.common.recipe.BrewKettleRecipeType;
import growthcraft.cellar.common.tileentity.handler.GrowthcraftItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.common.tank.NonInteractiveTank;
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
import net.minecraft.item.Items;
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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static growthcraft.cellar.init.GrowthcraftCellarItems.brew_kettle_lid;

public class BrewKettleTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public final int maxSmeltTime = GrowthcraftCellarConfig.getDefaultProcessingTime();
    private final GrowthcraftItemHandler inventory;
    private int currentSmeltTime;
    private BrewKettleRecipe currentRecipe;
    private ITextComponent customName;
    private FluidTank inputFluidTank;
    private final LazyOptional<IFluidHandler> inputFluidHandler = LazyOptional.of(() -> inputFluidTank);

    private NonInteractiveTank outputFluidTank;
    private final LazyOptional<IFluidHandler> outputFluidHandler = LazyOptional.of(() -> outputFluidTank);

    public BrewKettleTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new GrowthcraftItemHandler(3);
        this.createFluidTanks();
    }

    public BrewKettleTileEntity() {
        this(GrowthcraftCellarTileEntities.brew_kettle_tileentity.get());
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> brewKettleRecipeType, World world) {
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(brewKettleRecipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> brewKettleRecipeType) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(brewKettleRecipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    private void createFluidTanks() {
        this.inputFluidTank = new FluidTank(4000);
        this.outputFluidTank = new NonInteractiveTank(4000);

    }

    public FluidTank getFluidTank(int slot) {
        switch (slot) {
            case 0:
                return inputFluidTank;
            case 1:
                return outputFluidTank;
            default:
                return null;
        }
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BrewKettleContainer(windowId, playerInventory, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;

        this.world.setBlockState(this.getPos(), this.getBlockState().with(BrewKettleBlock.HAS_LID, hasLid()));

        if (world != null && !world.isRemote) {
            // Do a check for a heat source
            if (isHeated()) {
                this.world.setBlockState(this.getPos(), this.getBlockState().with(BrewKettleBlock.LIT, true));
                // Check for valid slots before looking for recipe.
                if (this.inventory.getStackInSlot(0).getItem() != Items.AIR && !inputFluidTank.isEmpty() && outputFluidTank.getFluidAmount() < outputFluidTank.getCapacity()) {

                    BrewKettleRecipe recipe = this.getRecipe(
                            this.inventory.getStackInSlot(0),
                            inputFluidTank.getFluid(),
                            this.inventory.getStackInSlot(2).getItem() == brew_kettle_lid.get());
                    if (currentRecipe != null && currentRecipe == recipe) {
                        // If the current recipe is not null and it equals the new recipe,
                        // then increment the smelting counter.
                        this.currentSmeltTime++;
                        dirty = true;
                    } else if (currentRecipe == null && recipe != null) {
                        // If the current recipe is null and the new recipe is not null,
                        // then assign the current recipe and we will start processing at
                        // the next tick.
                        currentSmeltTime = 0;
                        currentRecipe = recipe;
                        dirty = true;
                    }

                    if (currentSmeltTime > maxSmeltTime) {
                        // If the currentSmeltTime is greater than the max, then we need to
                        // move some items and fluids around.
                        this.inputFluidTank.drain(recipe.getInputFluidStack().getAmount(), IFluidHandler.FluidAction.EXECUTE);
                        this.inventory.getStackInSlot(0).shrink(recipe.getInputItemStack().getCount());
                        this.outputFluidTank.forceFill(recipe.getOutputFluidStack(), IFluidHandler.FluidAction.EXECUTE);
                        if (new Random().nextInt(4) == 1) {
                            this.inventory.insertItem(1, recipe.getByProduct(), false);
                        }

                        currentRecipe = null;
                        currentSmeltTime = 0;
                        dirty = true;
                    }
                } else {
                    currentRecipe = null;
                    currentSmeltTime = 0;
                }
            } else {
                this.world.setBlockState(this.getPos(), this.getBlockState().with(BrewKettleBlock.LIT, false));
            }
        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    /* Heat Source */
    public boolean isHeated() {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, pos);
        return BlockTags.getCollection().get(
                new ResourceLocation(growthcraft.core.shared.Reference.MODID,
                        growthcraft.core.shared.Reference.TAG_HEATSOURCES)).contains(blockMap.get("down"));
    }

    public boolean hasLid() {
        return this.inventory.getStackInSlot(2).getItem() == brew_kettle_lid.get();
    }

    /* Custom Name Handling */
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
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.BREW_KETTLE);
        return new TranslationTextComponent(translationKey);
    }

    /* NBT Data */
    @Override
    public void read(BlockState blockState, CompoundNBT compound) {
        super.read(blockState, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        NonNullList<ItemStack> inv = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);

        this.currentSmeltTime = compound.getInt("CurrentSmeltTime");

        inputFluidTank.readFromNBT(compound.getCompound("inputTank"));
        outputFluidTank.readFromNBT(compound.getCompound("outputTank"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentSmeltTime", this.currentSmeltTime);

        CompoundNBT inputTankNBT = inputFluidTank.writeToNBT(new CompoundNBT());
        compound.put("inputTank", inputTankNBT);

        CompoundNBT outputTankNBT = outputFluidTank.writeToNBT(new CompoundNBT());
        compound.put("outputTank", outputTankNBT);

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

    /* Recipe Handling */
    @Nullable
    private BrewKettleRecipe getRecipe(ItemStack itemStack, FluidStack fluidStack, boolean requiresLid) {
        Set<IRecipe<?>> recipes = findRecipesByType(new BrewKettleRecipeType(), this.world);
        for (IRecipe<?> recipe : recipes) {
            BrewKettleRecipe brewKettleRecipe = (BrewKettleRecipe) recipe;
            if (brewKettleRecipe.matches(itemStack, fluidStack, requiresLid)) return brewKettleRecipe;
        }
        return null;
    }

    /* Inventory */
    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && side == Direction.UP) {
            return inputFluidHandler.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return outputFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public int getCurrentSmeltTime() {
        return currentSmeltTime;
    }

    public void setCurrentSmeltTime(int smeltTime) {
        this.currentSmeltTime = smeltTime;
    }
}

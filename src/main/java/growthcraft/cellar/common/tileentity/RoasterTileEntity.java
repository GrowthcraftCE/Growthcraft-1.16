package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.RoasterContainer;
import growthcraft.cellar.common.block.RoasterBlock;
import growthcraft.cellar.common.recipe.RoasterRecipe;
import growthcraft.cellar.common.recipe.RoasterRecipeType;
import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RoasterTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int maxProcessingTime;
    private int currentProcessingTicks = 0;
    private ITextComponent customName;

    private final BrewKettleItemHandler inventory;

    private RoasterRecipe currentRecipe;

    public RoasterTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new BrewKettleItemHandler(3);
    }

    public RoasterTileEntity() {
        this(GrowthcraftCellarTileEntities.roaster_tileentity.get());
    }

    @Override
    public void tick() {
        boolean dirty = false;

        if (world != null && !world.isRemote) {
            if (!this.inventory.getStackInSlot(0).isEmpty() && this.isHeated()) {

                RoasterRecipe recipe = this.getRecipe(
                        this.inventory.getStackInSlot(0),
                        this.inventory.getStackInSlot(1)
                );

                if (currentRecipe != null && currentRecipe == recipe && this.inventory.getStackInSlot(2).getCount() < 64) {
                    // Continue to process the current recipe.
                    this.maxProcessingTime = recipe.getProcessingTime();
                    this.currentProcessingTicks++;
                    dirty = true;
                } else if (currentRecipe == null && recipe != null) {
                    // Set the current recipe and start processing next tick.
                    this.maxProcessingTime = recipe.getProcessingTime();
                    this.currentProcessingTicks = 0;
                    this.currentRecipe = recipe;
                    dirty = true;
                }

                if (currentRecipe != null && currentProcessingTicks > maxProcessingTime) {
                    // Current recipe is still valid and it is time to produce the result.
                    this.inventory.getStackInSlot(0).shrink(currentRecipe.getInputItemStack().getCount());
                    this.inventory.insertItem(2, currentRecipe.getRecipeOutput(), false);

                    this.currentRecipe = null;
                    this.currentProcessingTicks = 0;
                    this.maxProcessingTime = 0;
                    dirty = true;
                }

            } else {
                this.currentRecipe = null;
                this.currentProcessingTicks = 0;
                this.maxProcessingTime = 0;
            }

        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
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
        String translationKey = String.format("container.%s.%s", Reference.MODID, UnlocalizedName.ROASTER);
        return new TranslationTextComponent(translationKey);
    }

    // Interactive GUI
    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new RoasterContainer(windowId, playerInventory, this);
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
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentProcessingTicks", this.currentProcessingTicks);

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

        this.world.setBlockState(this.getPos(), this.getBlockState().with(RoasterBlock.LIT, heated));

        return heated;
    }

    // Inventory
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return super.getCapability(cap, side);
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    // Recipe Handling
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> recipeType, World world) {
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(recipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> recipeType) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(recipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @Nullable
    private RoasterRecipe getRecipe(ItemStack inputItemStack, ItemStack redstoneTimerItemStack) {
        Set<IRecipe<?>> recipes = findRecipesByType(new RoasterRecipeType(), this.world);
        for (IRecipe<?> recipe : recipes) {
            RoasterRecipe roasterRecipe = (RoasterRecipe) recipe;
            if (roasterRecipe.matches(inputItemStack, redstoneTimerItemStack)) return roasterRecipe;
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

    public void setMaxProcessingTime(int i) {
        this.maxProcessingTime = i;
    }
}

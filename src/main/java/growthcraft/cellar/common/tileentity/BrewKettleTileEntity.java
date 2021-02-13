package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.client.container.BrewKettleContainer;
import growthcraft.cellar.common.block.BrewKettle;
import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarRecipeSerializers;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.cellar.lib.recipe.BrewKettleRecipe;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.block.Block;
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
import net.minecraft.item.crafting.Ingredient;
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
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BrewKettleTileEntity extends TileEntity implements IFluidHandler, ITickableTileEntity, INamedContainerProvider {

    public final int maxSmeltTime = 100;
    public int currentSmeltTime;
    private ITextComponent customName;
    private BrewKettleItemHandler inventory;

    // Top = FluidInput, ItemInput
    // Sides = FluidOutput, ItemOutput

    // Need two tanks.

    public BrewKettleTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new BrewKettleItemHandler(3);
    }

    public BrewKettleTileEntity() {
        this(GrowthcraftCellarTileEntities.brewKettleTileEntity.get());
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> brewKettleRecipeType, World world) {
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType() == brewKettleRecipeType).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> brewKettleRecipeType) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType() == brewKettleRecipeType).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> iRecipeType, World world) {
        Set<ItemStack> inputs = new HashSet<ItemStack>();
        Set<IRecipe<?>> recipes = findRecipesByType(iRecipeType, world);
        for (IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            ingredients.forEach(ingredient -> {
                for (ItemStack itemStack : ingredient.getMatchingStacks()) {
                    inputs.add(itemStack);
                }
            });
        }
        return inputs;
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BrewKettleContainer(windowId, playerInventory, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;

        if (world != null && !world.isRemote) {
            // Do a check for redstone power
            if (this.world.isBlockPowered(pos)) {
                if (this.getRecipe(this.inventory.getStackInSlot(0)) != null) {
                    if (this.currentSmeltTime != this.maxSmeltTime) {
                        this.world.setBlockState(this.getPos(), this.getBlockState().with(BrewKettle.LIT, true));
                        this.currentSmeltTime++;
                        dirty = true;
                    } else {
                        this.currentSmeltTime = 0;
                        this.world.setBlockState(this.getPos(), this.getBlockState().with(BrewKettle.LIT, false));
                        ItemStack output = this.getRecipe(this.inventory.getStackInSlot(0)).getRecipeOutput();
                        this.inventory.insertItem(1, output.copy(), false);
                        this.inventory.decrStackSize(0, 1);
                        dirty = true;
                    }
                }
            }

            // Do a check for a heat source
            if (isHeated()) {
                this.world.setBlockState(this.getPos(), this.getBlockState().with(BrewKettle.LIT, true));
            } else {
                this.world.setBlockState(this.getPos(), this.getBlockState().with(BrewKettle.LIT, false));
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
                        growthcraft.core.shared.UnlocalizedName.TAG_HEATSOURCES)).contains(blockMap.get("down"));
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
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
        }

        NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);

        this.currentSmeltTime = compound.getInt("CurrentSmeltTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentSmeltTime", this.currentSmeltTime);

        return super.write(compound);
    }

    /* Recipe Handling */
    @Nullable
    private BrewKettleRecipe getRecipe(ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(GrowthcraftCellarRecipeSerializers.BREW_KETTLE_RECIPE_TYPE, this.world);
        for (IRecipe<?> iRecipe : recipes) {
            BrewKettleRecipe recipe = (BrewKettleRecipe) iRecipe;
            if (recipe.matches(new RecipeWrapper(this.getInventory()), world)) {
                return recipe;
            }
        }
        return null;
    }

    /* Inventory */
    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if ( cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == Direction.UP ) {
            
        }
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
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
        this.read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return nbt;
    }

    /* Fluid Handler */
    @Override
    public void handleUpdateTag(CompoundNBT nbt) {
        this.read(nbt);
    }

    @Override
    public int getTanks() {
        return 0;
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {
        return null;
    }

    @Override
    public int getTankCapacity(int tank) {
        return 0;
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        return false;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return 0;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return null;
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return null;
    }
}

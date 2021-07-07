package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.tileentity.handler.BrewKettleItemHandler;
import growthcraft.cellar.init.GrowthcraftCellarTileEntities;
import growthcraft.lib.common.tank.handler.FluidTankHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class FermentBarrelTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final int maxProcessingTime = 0;
    private int currentProcessingTime = 0;
    private ITextComponent customName;

    private final BrewKettleItemHandler inventory;

    private final FluidTankHandler fluidTankHandler;

    // private FermentBarrelRecipe currrentRecipe;

    public FermentBarrelTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new BrewKettleItemHandler(1);
        fluidTankHandler = new FluidTankHandler(1, 4000);
    }

    public FermentBarrelTileEntity() {
        this(GrowthcraftCellarTileEntities.barrel_ferment_oak_tileentity.get());
    }

    @Override
    public void tick() {

    }

    // TileEntityRender Distance - Not Needed

    // Custom Name Handling
    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    // Interactive GUI
    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }

    // NBT Data Handling

    // Heat Sources Handling - Not needed

    // Inventory

    // Recipes Handling

    // Getters and Setters
    public int getCurrentProcessingTime() {
        return this.currentProcessingTime;
    }

    public void setCurrentProcessingTime(int ticks) {
        this.currentProcessingTime = ticks;
    }

    public int getMaxProcessingTime() {
        return this.maxProcessingTime;
    }

    public FluidTankHandler getFluidTankHandler() {
        return fluidTankHandler;
    }

    public FluidTank getFluidTank(int tank) {
        return
    }
}

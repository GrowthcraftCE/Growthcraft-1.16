package growthcraft.lib.common.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.function.Supplier;

public class GrowthcraftBucketItem extends BucketItem {

    private final int color;

    public GrowthcraftBucketItem(Supplier<? extends Fluid> supplier, Color color) {
        super(supplier,
                new Item.Properties().group(growthcraft.core.shared.Reference.growthcraftCreativeTab).maxStackSize(1));

        this.color = color.getRGB();
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @Nullable net.minecraft.nbt.CompoundNBT nbt) {
        if (this instanceof BucketItem)
            return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
        else
            return super.initCapabilities(stack, nbt);
    }

    public int getColor() {
        return this.color;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(Items.BUCKET);
    }
}

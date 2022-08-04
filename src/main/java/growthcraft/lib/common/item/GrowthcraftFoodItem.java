package growthcraft.lib.common.item;

import growthcraft.core.shared.Reference;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GrowthcraftFoodItem extends Item {

    private boolean hasBowl = false;

    public GrowthcraftFoodItem() {
        this(1, 0.2F, 64);
    }

    public GrowthcraftFoodItem(int maxStackSize) {
        this(1, 0.2F, maxStackSize);
    }

    public GrowthcraftFoodItem(int hunger, float saturation, int maxStackSize) {
        super(getInitProperties(hunger, saturation, maxStackSize));
    }

    public GrowthcraftFoodItem(int hunger, float saturation, int maxStackSize, boolean hasBowl) {
        this(hunger, saturation, maxStackSize);
        this.hasBowl = hasBowl;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return this.hasBowl;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return this.hasBowl ? new ItemStack(Items.BOWL) : null;
    }

    private static Properties getInitProperties(int hunger, float saturation, int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Reference.growthcraftCreativeTab);
        properties.maxStackSize(maxStackSize);
        properties.food(new Food.Builder().hunger(hunger).saturation(saturation).build());
        return properties;
    }

}

package growthcraft.lib.common.item;

import growthcraft.core.shared.Reference;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class GrowthcraftFoodItem extends Item {

    public GrowthcraftFoodItem() {
        this(1, 0.2F, 64);
    }

    public GrowthcraftFoodItem(int maxStackSize) {
        this(1, 0.2F, maxStackSize);
    }

    public GrowthcraftFoodItem(int hunger, float saturation, int maxStackSize) {
        super(getInitProperties(hunger, saturation, maxStackSize));
    }

    private static Properties getInitProperties(int hunger, float saturation, int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Reference.growthcraftCreativeTab);
        properties.maxStackSize(maxStackSize);
        properties.food(new Food.Builder().hunger(hunger).saturation(saturation).build());
        return properties;
    }

}

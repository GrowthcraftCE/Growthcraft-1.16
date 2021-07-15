package growthcraft.cellar.common.item;

import growthcraft.lib.common.item.GrowthcraftItem;

import java.awt.*;

public class GrainItem extends GrowthcraftItem {

    private final Color color;

    public GrainItem(int intColor) {
        color = new Color(intColor);
    }

    public int getColor() {
        return this.color.getRGB();
    }
}

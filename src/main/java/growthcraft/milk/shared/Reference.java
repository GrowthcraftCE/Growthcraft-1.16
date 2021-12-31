package growthcraft.milk.shared;

import growthcraft.lib.util.ColorUtils;

public class Reference {

    public static final String MODID = "growthcraft_milk";
    public static final String NAME = "Growthcraft Milk";
    public static final String NAME_SHORT = "milk";

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    private Reference() {
        /* Prevent generation of public constructor. */
    }

    public static class FluidColor {
        // Set Fluid Colors
        public static final ColorUtils.GrowthcraftColor BUTTER_MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor CONDENSED_MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor CREAM_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor KUMIS_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor RENNET_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor WHEY_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
    }
}

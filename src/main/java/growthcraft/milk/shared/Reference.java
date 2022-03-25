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

    public static class ItemColor {
        // TODO: Determine Cheese Colors
        public static final ColorUtils.GrowthcraftColor APPENZELLER_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor ASIAGO_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor CASU_MAZU_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor CHEDDAR_CHEESE = new ColorUtils.GrowthcraftColor(0xF2BE6F);
        public static final ColorUtils.GrowthcraftColor EMMENTALER_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor GORGONZOLA_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor GOUDA_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor MONTEREY_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor PARMESAN_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor PROVOLONE_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
        public static final ColorUtils.GrowthcraftColor RICOTTA_CHEESE = new ColorUtils.GrowthcraftColor(0x0);
    }

    public static class FluidColor {
        // Set Fluid Colors
        public static final ColorUtils.GrowthcraftColor BUTTER_MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFEF1B5);
        public static final ColorUtils.GrowthcraftColor CHEESE_BASE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFDD0);
        public static final ColorUtils.GrowthcraftColor CONDENSED_MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFFFFA);
        public static final ColorUtils.GrowthcraftColor CREAM_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFFDD0);
        public static final ColorUtils.GrowthcraftColor CULTURED_MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xF7D99E);
        public static final ColorUtils.GrowthcraftColor KUMIS_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF9F9F9);
        public static final ColorUtils.GrowthcraftColor MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor RENNET_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF877243);
        public static final ColorUtils.GrowthcraftColor SKIM_MILK_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFFFFA);
        public static final ColorUtils.GrowthcraftColor WHEY_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF94a860);
    }
}

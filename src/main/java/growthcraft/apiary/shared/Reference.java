package growthcraft.apiary.shared;

import growthcraft.lib.util.ColorUtils;

public class Reference {
    public static final String MODID = "growthcraft_apiary";
    public static final String NAME = "Growthcraft Apiary";
    public static final String NAME_SHORT = "apiary";
    public static final String TAG_BEES = "bees";
    public static final String TAG_HONEY_COMBS = "honey_comb";

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    public static class FluidColor {
        public static final ColorUtils.GrowthcraftColor HONEY_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFBF00);
        public static final ColorUtils.GrowthcraftColor HONEY_MEAD_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFD79334);
        public static final ColorUtils.GrowthcraftColor WAX_BLACK_COLOR = new ColorUtils.GrowthcraftColor(0xFF232323);
        public static final ColorUtils.GrowthcraftColor WAX_BLUE_COLOR = new ColorUtils.GrowthcraftColor(0xFF0000FF);
        public static final ColorUtils.GrowthcraftColor WAX_BROWN_COLOR = new ColorUtils.GrowthcraftColor(0xFF964B00);
        public static final ColorUtils.GrowthcraftColor WAX_CYAN_COLOR = new ColorUtils.GrowthcraftColor(0xFF00B7EB);
        public static final ColorUtils.GrowthcraftColor WAX_GRAY_COLOR = new ColorUtils.GrowthcraftColor(0xFF808080);
        public static final ColorUtils.GrowthcraftColor WAX_GREEN_COLOR = new ColorUtils.GrowthcraftColor(0xFF00FF00);
        public static final ColorUtils.GrowthcraftColor WAX_LIGHT_BLUE_COLOR = new ColorUtils.GrowthcraftColor(0xFFADD8E6);
        public static final ColorUtils.GrowthcraftColor WAX_LIGHT_GRAY_COLOR = new ColorUtils.GrowthcraftColor(0xFFD3D3D3);
        public static final ColorUtils.GrowthcraftColor WAX_LIME_COLOR = new ColorUtils.GrowthcraftColor(0xFF32CD32);
        public static final ColorUtils.GrowthcraftColor WAX_MAGENTA_COLOR = new ColorUtils.GrowthcraftColor(0xFFFF00FF);
        public static final ColorUtils.GrowthcraftColor WAX_ORANGE_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFA500);
        public static final ColorUtils.GrowthcraftColor WAX_PINK_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFC0CB);
        public static final ColorUtils.GrowthcraftColor WAX_PURPLE_COLOR = new ColorUtils.GrowthcraftColor(0xFF6A0DAD);
        public static final ColorUtils.GrowthcraftColor WAX_RED_COLOR = new ColorUtils.GrowthcraftColor(0xFFFF0000);
        public static final ColorUtils.GrowthcraftColor WAX_WHITE_COLOR = new ColorUtils.GrowthcraftColor(0xFFF8F0E3);
        public static final ColorUtils.GrowthcraftColor WAX_YELLOW_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFFF00);

    }

    private Reference() { /* Prevent default public constructor */ }

}

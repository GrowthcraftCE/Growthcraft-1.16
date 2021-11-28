package growthcraft.apiary.shared;

import growthcraft.lib.util.ColorUtils;

public class Reference {
    public static final String MODID = "growthcraft_apiary";
    public static final String NAME = "Growthcraft Apiary";
    public static final String NAME_SHORT = "apiary";

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    public static class FluidColor {
        public static final ColorUtils.GrowthcraftColor HONEY_FLUID_COLOR_ = new ColorUtils.GrowthcraftColor(0xFFFFBF00);
        public static final ColorUtils.GrowthcraftColor HONEY_MEAD_FLUID_COLOR_ = new ColorUtils.GrowthcraftColor(0xFFD79334);
    }

    private Reference() { /* Prevent default public constructor */ }

}

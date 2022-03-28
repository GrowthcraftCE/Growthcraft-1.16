package growthcraft.rice.shared;

import growthcraft.lib.util.ColorUtils;

public class Reference {
    public static final String MODID = "growthcraft_rice";
    public static final String NAME = "Growthcraft Rice";
    public static final String NAME_SHORT = "rice";

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    private Reference() {
        /* Prevent generation of public constructor. */
    }

    public static class FluidColor {
        public static final ColorUtils.GrowthcraftColor RICE_WATER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor RICE_WINE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFD9DADB);
        public static final ColorUtils.GrowthcraftColor SAKE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFEAECEC);
    }
}

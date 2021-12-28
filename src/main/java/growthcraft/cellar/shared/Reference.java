package growthcraft.cellar.shared;

import growthcraft.lib.util.ColorUtils;
import net.minecraft.util.ResourceLocation;

public class Reference {

    public static final String MODID = "growthcraft_cellar";
    public static final String MODID_SHORT = "cellar";
    public static final String NAME = "Growthcraft Cellar";
    public static final String VERSION = "6.0.9";

    private Reference() { /* Prevent Public Constructor */ }

    public static class FluidResource {
        public static final ResourceLocation FLOWING = new ResourceLocation("block/water_flow");
        public static final ResourceLocation OVERLAY = new ResourceLocation("block/water_overlay");
        public static final ResourceLocation STILL = new ResourceLocation("block/water_still");

        private FluidResource() { /* Prevent Public Constructor */ }
    }

    public static class TagResource {
        public static final ResourceLocation TAG_ALES = new ResourceLocation(Reference.MODID, "ale");
        public static final ResourceLocation TAG_LAGERS = new ResourceLocation(Reference.MODID, "lager");
        public static final ResourceLocation TAG_WINES = new ResourceLocation(Reference.MODID, "wine");
    }

    public static class FluidColor {
        public static final ColorUtils.GrowthcraftColor AMBER_ALE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFBC6633);
        public static final ColorUtils.GrowthcraftColor AMBER_LAGER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFAB5E2F);
        public static final ColorUtils.GrowthcraftColor AMBER_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFE5C7A2);
        public static final ColorUtils.GrowthcraftColor BROWN_ALE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF5C311A);
        public static final ColorUtils.GrowthcraftColor BROWN_LAGER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF401F1C);
        public static final ColorUtils.GrowthcraftColor BROWN_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF594732);
        public static final ColorUtils.GrowthcraftColor COPPER_ALE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF8E4B31);
        public static final ColorUtils.GrowthcraftColor COPPER_LAGER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF6F3D1F);
        public static final ColorUtils.GrowthcraftColor COPPER_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF936B53);
        public static final ColorUtils.GrowthcraftColor DARK_LAGER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF211515);
        public static final ColorUtils.GrowthcraftColor DARK_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF452A19);
        public static final ColorUtils.GrowthcraftColor DEEP_AMBER_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFCFA26F);
        public static final ColorUtils.GrowthcraftColor DEEP_COPPER_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF805C2F);
        public static final ColorUtils.GrowthcraftColor GOLDEN_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6D02E);
        public static final ColorUtils.GrowthcraftColor HOPPED_GOLDEN_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6FE2E);
        public static final ColorUtils.GrowthcraftColor IPA_ALE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFD2BD2C);
        public static final ColorUtils.GrowthcraftColor OLD_PORT_ALE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF8E3616);
        public static final ColorUtils.GrowthcraftColor PALE_ALE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFBF855);
        public static final ColorUtils.GrowthcraftColor PALE_GOLDEN_WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFBF855);
        public static final ColorUtils.GrowthcraftColor PALE_LAGER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF3F33F);
        public static final ColorUtils.GrowthcraftColor PILSNER_LAGER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6D02E);
        public static final ColorUtils.GrowthcraftColor PURPLE_GRAPE_JUICE_COLOR = new ColorUtils.GrowthcraftColor(0xFF682961);
        public static final ColorUtils.GrowthcraftColor PURPLE_WINE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF562251);
        public static final ColorUtils.GrowthcraftColor RED_GRAPE_JUICE_COLOR = new ColorUtils.GrowthcraftColor(0xFFA63F4A);
        public static final ColorUtils.GrowthcraftColor RED_WINE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF8A343D);
        public static final ColorUtils.GrowthcraftColor STOUT_ALE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF0E0A07);
        public static final ColorUtils.GrowthcraftColor VIENNA_LAGER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF904730);
        public static final ColorUtils.GrowthcraftColor WHITE_GRAPE_JUICE_COLOR = new ColorUtils.GrowthcraftColor(0xFFB4C91C);
        public static final ColorUtils.GrowthcraftColor WHITE_WINE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFF96A817);
        public static final ColorUtils.GrowthcraftColor WORT_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFD0AF4E);

        private FluidColor() { /* Prevent Public Constructor */ }
    }

    public static class FluidName {
        public static final String AMBER_ALE = "amber_ale";
        public static final String AMBER_LAGER = "amber_lager";
        public static final String AMBER_WORT = "amber_wort";
        public static final String BROWN_ALE = "brown_ale";
        public static final String BROWN_LAGER = "brown_lager";
        public static final String BROWN_WORT = "brown_wort";
        public static final String COPPER_ALE = "copper_ale";
        public static final String COPPER_LAGER = "copper_lager";
        public static final String COPPER_WORT = "copper_wort";
        public static final String DARK_LAGER = "dark_lager";
        public static final String DARK_WORT = "dark_wort";
        public static final String DEEP_AMBER_WORT = "deep_amber_wort";
        public static final String DEEP_COPPER_WORT = "deep_copper_wort";
        public static final String GOLDEN_WORT = "golden_wort";
        public static final String HOPPED_GOLDEN_WORT = "hopped_golden_wort";
        public static final String IPA_ALE = "ipa_ale";
        public static final String OLD_PORT_ALE = "old_port_ale";
        public static final String PALE_ALE = "pale_ale";
        public static final String PALE_GOLDEN_WORT = "pale_golden_wort";
        public static final String PALE_LAGER = "pale_lager";
        public static final String PILSNER_LAGER = "pilsner_lager";
        public static final String PURPLE_GRAPE_JUICE = "purple_grape_juice";
        public static final String RED_GRAPE_JUICE = "red_grape_juice";
        public static final String STOUT_ALE = "stout_ale";
        public static final String VIENNA_LAGER = "vienna_lager";
        public static final String WHITE_GRAPE_JUICE = "white_grape_juice";
        public static final String WORT = "wort";
        public static final String RED_WINE = "red_wine";
        public static final String PURPLE_WINE = "purple_wine";
        public static final String WHITE_WINE = "white_wine";

        private FluidName() { /* Prevent Public Constructor */ }
    }

}

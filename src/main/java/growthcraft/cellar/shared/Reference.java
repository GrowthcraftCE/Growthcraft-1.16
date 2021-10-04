package growthcraft.cellar.shared;

import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class Reference {

    public static final String MODID = "growthcraft_cellar";
    public static final String MODID_SHORT = "cellar";
    public static final String NAME = "Growthcraft Cellar";
    public static final String VERSION = "6.0.4";

    private Reference() { /* Prevent Public Constructor */ }

    public static class FluidResource {
        public static final ResourceLocation FLOWING = new ResourceLocation(Reference.MODID, "block/fluid/fluid_flowing");
        public static final ResourceLocation OVERLAY = new ResourceLocation(Reference.MODID, "block/fluid/fluid_overlay");
        public static final ResourceLocation STILL = new ResourceLocation(Reference.MODID, "block/fluid/fluid_still");

        private FluidResource() { /* Prevent Public Constructor */ }
    }

    public static class TagResource {
        public static final ResourceLocation TAG_ALES = new ResourceLocation(Reference.MODID, "ale");
        public static final ResourceLocation TAG_LAGERS = new ResourceLocation(Reference.MODID, "lager");
        public static final ResourceLocation TAG_WINES = new ResourceLocation(Reference.MODID, "wine");
    }

    public static class FluidColor {
        public static final Color AMBER_ALE_FLUID_COLOR = new Color(0xBC6633);
        public static final Color AMBER_LAGER_FLUID_COLOR = new Color(0xAB5E2F);
        public static final Color AMBER_WORT_FLUID_COLOR = new Color(0xE5C7A2);
        public static final Color BROWN_ALE_FLUID_COLOR = new Color(0x5C311A);
        public static final Color BROWN_LAGER_FLUID_COLOR = new Color(0x401F1C);
        public static final Color BROWN_WORT_FLUID_COLOR = new Color(0x594732);
        public static final Color COPPER_ALE_FLUID_COLOR = new Color(0x8E4B31);
        public static final Color COPPER_LAGER_FLUID_COLOR = new Color(0x6F3D1F);
        public static final Color COPPER_WORT_FLUID_COLOR = new Color(0x936B53);
        public static final Color DARK_LAGER_FLUID_COLOR = new Color(0x211515);
        public static final Color DARK_WORT_FLUID_COLOR = new Color(0x452A19);
        public static final Color DEEP_AMBER_WORT_FLUID_COLOR = new Color(0xCFA26F);
        public static final Color DEEP_COPPER_WORT_FLUID_COLOR = new Color(0x805C2F);
        public static final Color GOLDEN_WORT_FLUID_COLOR = new Color(0xF6D02E);
        public static final Color HOPPED_GOLDEN_WORT_FLUID_COLOR = new Color(0xF6FE2E);
        public static final Color IPA_ALE_FLUID_COLOR = new Color(0xD2BD2C);
        public static final Color OLD_PORT_ALE_FLUID_COLOR = new Color(0x8E3616);
        public static final Color PALE_ALE_FLUID_COLOR = new Color(0xFBF855);
        public static final Color PALE_GOLDEN_WORT_FLUID_COLOR = new Color(0xFBF855);
        public static final Color PALE_LAGER_FLUID_COLOR = new Color(0xF3F33F);
        public static final Color PILSNER_LAGER_FLUID_COLOR = new Color(0xF6D02E);
        public static final Color PURPLE_GRAPE_JUICE_COLOR = new Color(0x682961);
        public static final Color PURPLE_WINE_FLUID_COLOR = new Color(0x562251);
        public static final Color RED_GRAPE_JUICE_COLOR = new Color(0xA63F4A);
        public static final Color RED_WINE_FLUID_COLOR = new Color(0x8A343D);
        public static final Color STOUT_ALE_FLUID_COLOR = new Color(0x0E0A07);
        public static final Color VIENNA_LAGER_FLUID_COLOR = new Color(0x904730);
        public static final Color WHITE_GRAPE_JUICE_COLOR = new Color(0xB4C91C);
        public static final Color WHITE_WINE_FLUID_COLOR = new Color(0x96A817);
        public static final Color WORT_FLUID_COLOR = new Color(0xD0AF4E);

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

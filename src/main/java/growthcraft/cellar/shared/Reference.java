package growthcraft.cellar.shared;

import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class Reference {

    public static final String MODID = "growthcraft_cellar";
    public static final String MODID_SHORT = "cellar";
    public static final String NAME = "Growthcraft Cellar";
    public static final String VERSION = "6.0.1.0";

    private Reference() { /* Prevent Public Constructor */ }

    public static class FluidResource {
        public static final ResourceLocation FLOWING = new ResourceLocation(Reference.MODID, "block/fluid/fluid_flowing");
        public static final ResourceLocation OVERLAY = new ResourceLocation(Reference.MODID, "block/fluid/fluid_overlay");
        public static final ResourceLocation STILL = new ResourceLocation(Reference.MODID, "block/fluid/fluid_still");

        private FluidResource() { /* Prevent Public Constructor */ }
    }

    public static class FluidColor {
        public static final Color AMBER_ALE_FLUID_COLOR = new Color(0xBC6633);
        public static final Color AMBER_LAGER_FLUID_COLOR = new Color(0xAB5E2F);
        public static final Color BROWN_ALE_FLUID_COLOR = new Color(0x5c311A);
        public static final Color BROWN_LAGER_FLUID_COLOR = new Color(0x401F1C);
        public static final Color COPPER_ALE_FLUID_COLOR = new Color(0x8E4B31);
        public static final Color COPPER_LAGER_FLUID_COLOR = new Color(0x6F3D1F);
        public static final Color DARK_LAGER_FLUID_COLOR = new Color(0x211515);
        public static final Color IPA_ALE_FLUID_COLOR = new Color(0xD2BD2C);
        public static final Color OLD_PORT_ALE_FLUID_COLOR = new Color(0x8E3616);
        public static final Color PALE_ALE_FLUID_COLOR = new Color(0xFBF855);
        public static final Color PALE_LAGER_FLUID_COLOR = new Color(0xF3F33F);
        public static final Color PILSNER_LAGER_FLUID_COLOR = new Color(0xF6D02E);
        public static final Color STOUT_ALE_FLUID_COLOR = new Color(0x0E0A07);
        public static final Color VIENNA_LAGER_FLUID_COLOR = new Color(0x904730);
        public static final Color WORT_FLUID_COLOR = new Color(0xD0AF4E);

        private FluidColor() { /* Prevent Public Constructor */ }
    }

    public static class FluidName {
        public static final String AMBER_ALE = "amber_ale";
        public static final String AMBER_LAGER = "amber_lager";
        public static final String BROWN_ALE = "brown_ale";
        public static final String BROWN_LAGER = "brown_lager";
        public static final String COPPER_ALE = "copper_ale";
        public static final String COPPER_LAGER = "copper_lager";
        public static final String DARK_LAGER = "dark_lager";
        public static final String IPA_ALE = "ipa_ale";
        public static final String OLD_PORT_ALE = "old_port_ale";
        public static final String PALE_ALE = "pale_ale";
        public static final String PALE_LAGER = "pale_lager";
        public static final String PILSNER_LAGER = "pilsner_lager";
        public static final String STOUT_ALE = "stout_ale";
        public static final String VIENNA_LAGER = "vienna_lager";
        public static final String WORT = "wort";

        private FluidName() { /* Prevent Public Constructor */ }
    }

}

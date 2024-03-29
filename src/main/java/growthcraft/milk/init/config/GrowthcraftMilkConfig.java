package growthcraft.milk.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import growthcraft.milk.shared.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftMilkConfig {

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final String SERVER_CONFIG = String.format("growthcraft-%s-server.toml", Reference.NAME_SHORT);

    private static final String CATEGORY_CHURN = "churn";
    private static final String CATEGORY_MIXING_VAT = "mixing_vat";
    private static final String CATEGORY_PANCHEON = "pancheon";

    private static ForgeConfigSpec.BooleanValue churnGuiEnabled;
    private static ForgeConfigSpec.BooleanValue mixingVatGuiEnabled;
    private static ForgeConfigSpec.BooleanValue mixingVatConsumeActivationItem;
    private static ForgeConfigSpec.BooleanValue pancheonGuiEnabled;

    static {
        initServerConfig(SERVER_BUILDER);
        SERVER = SERVER_BUILDER.build();
    }

    private GrowthcraftMilkConfig() {
        /* Prevent generation of public constructor */
    }

    public static void loadConfig() {
        loadConfig(SERVER, FMLPaths.CONFIGDIR.get().resolve(SERVER_CONFIG).toString());
    }

    public static void loadConfig(ForgeConfigSpec configSpec, String path) {
        final CommentedFileConfig fileConfig = CommentedFileConfig.builder(
                new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();

        fileConfig.load();
        configSpec.setConfig(fileConfig);
    }

    public static void initServerConfig(ForgeConfigSpec.Builder specBuilder) {
        // Init Server Side Configuration
        churnGuiEnabled = specBuilder
                .comment("Set to true to allow users to access the Churn GUI.")
                .define(String.format("%s.%s", CATEGORY_CHURN, "guiEnabled"), true);
        mixingVatGuiEnabled = specBuilder
                .comment("Set to true to allow users to access the Mixing Vat GUI.")
                .define(String.format("%s.%s", CATEGORY_MIXING_VAT, "guiEnabled"), true);
        mixingVatConsumeActivationItem = specBuilder
                .comment("Set to true to allow users to access the Mixing Vat GUI.")
                .define(String.format("%s.%s", CATEGORY_MIXING_VAT, "consumeMixingVatActivator"), false);
        pancheonGuiEnabled = specBuilder
                .comment("Set to true to allow users to access the Pancheon GUI.")
                .define(String.format("%s.%s", CATEGORY_PANCHEON, "guiEnabled"), true);
    }

    public static boolean isChurnGuiEnabled() {
        return churnGuiEnabled.get();
    }

    public static boolean isPancheonGuiEnabled() {
        return pancheonGuiEnabled.get();
    }

    public static boolean isMixingVatGuiEnabled() {
        return mixingVatGuiEnabled.get();
    }

    public static boolean isConsumeMixingVatActivator() {
        return mixingVatConsumeActivationItem.get();
    }
}
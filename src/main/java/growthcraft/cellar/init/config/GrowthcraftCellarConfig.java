package growthcraft.cellar.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import growthcraft.cellar.shared.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftCellarConfig {

    public static final String SERVER_CONFIG = String.format("growthcraft-%s-server.toml", Reference.MODID_SHORT);
    public static final String CLIENT_CONFIG = String.format("growthcraft-%s-client.toml", Reference.MODID_SHORT);

    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec CLIENT;

    private static final String CATEGORY_BREW_KETTLE = "brew_kettle";
    private static final String CATEGORY_GRAPE_VINES = "grape_vines";

    private static ForgeConfigSpec.IntValue brew_kettle_lit_light_level;
    private static ForgeConfigSpec.IntValue default_brewing_ticks;

    private static ForgeConfigSpec.DoubleValue grape_vine_fruit_scale;
    private static ForgeConfigSpec.IntValue grape_vine_min_fruit;
    private static ForgeConfigSpec.IntValue grape_vine_max_fruit;

    private static ForgeConfigSpec.IntValue hops_min_fruit;
    private static ForgeConfigSpec.IntValue hops_max_fruit;

    static {
        initBrewKettleConfig(SERVER_BUILDER);
        initGrapeVineConfig(SERVER_BUILDER);
        initHopsCropConfig(SERVER_BUILDER);
        //initWorldGenConfig(SERVER_BUILDER);

        SERVER = SERVER_BUILDER.build();
        CLIENT = CLIENT_BUILDER.build();
    }

    private GrowthcraftCellarConfig() { /* Disable default public constructor */ }

    public static void loadConfig() {
        loadConfig(SERVER, FMLPaths.CONFIGDIR.get().resolve(SERVER_CONFIG).toString());
        loadConfig(CLIENT, FMLPaths.CONFIGDIR.get().resolve(CLIENT_CONFIG).toString());
    }

    public static void loadConfig(ForgeConfigSpec configSpec, String path) {
        final CommentedFileConfig fileConfig = CommentedFileConfig.builder(
                new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();

        fileConfig.load();
        configSpec.setConfig(fileConfig);
    }

    public static void initBrewKettleConfig(ForgeConfigSpec.Builder server) {
        brew_kettle_lit_light_level = server
                .comment("Set the light level for the brew kettle when it is lit. Setting to 0 uses neighbor light level.")
                .defineInRange("brewKettle.LitLightLevel", 15, 0, 15);
        default_brewing_ticks = server
                .comment("Set the Brew Kettle processing time in ticks.")
                .defineInRange("brewKettle.DefaultProcessingTime", 600, 20, 24000);
    }

    public static void initGrapeVineConfig(ForgeConfigSpec.Builder server) {
        grape_vine_fruit_scale = server
                .comment("Determines the scale of the rendering of grape vine fruit.")
                .defineInRange("grape_vine.render_fruit_scale", 2.0D, 0.1D, 3.0D);
        grape_vine_min_fruit = server
                .comment("Set to the minimum amount of fruit dropped by grape vines.")
                .defineInRange("grape_vine.min_fruit_yield", 1, 1, 100);
        grape_vine_max_fruit = server
                .comment("Set to the maximum amount of fruit dropped by grape vines.")
                .defineInRange("grape_vine.max_fruit_yield", 4, 1, 100);
    }

    public static void initHopsCropConfig(ForgeConfigSpec.Builder server) {
        hops_min_fruit = server
                .comment("Set to the minimum amount of hops dropped by Hope crops.")
                .defineInRange("hops_crop.min_fruit_yield", 1, 1, 100);
        hops_max_fruit = server
                .comment("Set to the maximum amount of hops dropped by Hope crops.")
                .defineInRange("hops_crop.max_fruit_yield", 3, 1, 100);

    }

    /**
     * World generation settings for Growthcraft Apples. World generation is server side only.
     *
     * @param server Server side ForgeConfigSpec builder
     */
    public static void initWorldGenConfig(ForgeConfigSpec.Builder server) {
        server.push("general");
        server.comment(String.format("General configuration for %s %s.", Reference.NAME, Reference.VERSION));
        server.pop();
        /**
         * Placeholder for setting configuration values.
         * <pre>
         *     enableAppleTreeGen = server
         *                 .comment("Enable/Disable the world generation of Apple Trees.")
         *                 .define("worldgen.apple_trees.enabled", true);
         * </pre>
         */

    }

    public static int getBrewKettleLitLightLevel() {
        return brew_kettle_lit_light_level.get();
    }

    public static int getDefaultProcessingTime() {
        return default_brewing_ticks.get();
    }

    public static int getGrapeVineMinFruitYield() {
        return grape_vine_min_fruit.get();
    }

    public static int getGrapeVineMaxFruitYield() {
        return grape_vine_max_fruit.get();
    }

    public static int getHopsCropMinFruitYield() {
        return hops_min_fruit.get();
    }

    public static int getHopsCropMaxFruitYield() {
        return hops_max_fruit.get();
    }

}

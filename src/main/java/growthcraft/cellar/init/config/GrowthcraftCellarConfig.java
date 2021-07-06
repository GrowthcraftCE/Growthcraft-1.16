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

    private static ForgeConfigSpec.IntValue brew_kettle_lit_light_level;
    private static ForgeConfigSpec.IntValue default_brewing_ticks;

    static {
        initBrewKettleConfig(SERVER_BUILDER);
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
}

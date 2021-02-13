package growthcraft.trapper.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import growthcraft.trapper.shared.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftTrapperConfig {

    public static final String SERVER_CONFIG = String.format("growthcraft-%s-server.toml", Reference.NAME_SHORT);
    public static final String CLIENT_CONFIG = String.format("growthcraft-%s-client.toml", Reference.NAME_SHORT);

    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec CLIENT;

    // Private ForgeConfigSpec Definitions

    /**
     * Placeholder for ForgeConfigSpec definitions.
     * <pre>
     *     private static ForgeConfigSpec.BooleanValue enableAppleTreeGen;
     * </pre>
     */

    static {
        initWorldGenConfig(SERVER_BUILDER);

        SERVER = SERVER_BUILDER.build();
        CLIENT = CLIENT_BUILDER.build();
    }

    private GrowthcraftTrapperConfig() { /* Disable default public constructor */ }

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

    /**
     * World generation settings for Growthcraft Apples. World generation is server side only.
     *
     * @param server Server side ForgeConfigSpec builder
     */
    public static void initWorldGenConfig(ForgeConfigSpec.Builder server) {
        server.push("general");
        server.comment(String.format("General configuration for %s.", Reference.NAME)).define("general.version", Reference.VERSION);
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

    // region Getters
    /**
     * Placeholder for configuration getters.
     * <pre>
     *     public boolean appleTreeGenEnabled() {
     *         return enableAppleTreeGen.get();
     *     }
     * </pre>
     */
    // endregion
}

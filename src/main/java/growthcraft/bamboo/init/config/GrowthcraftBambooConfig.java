package growthcraft.bamboo.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import growthcraft.bamboo.shared.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftBambooConfig {

    public static final String SERVER_CONFIG = String.format("growthcraft-%s-server.toml", Reference.NAME_SHORT);
    public static final String CLIENT_CONFIG = String.format("growthcraft-%s-client.toml", Reference.NAME_SHORT);

    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec CLIENT;

    private static ForgeConfigSpec.BooleanValue enableBambooTrees;
    private static ForgeConfigSpec.BooleanValue enableBambooTreeSpread;
    private static ForgeConfigSpec.IntValue maxBambooTreesHeight;

    static {
        initWorldGenConfig(SERVER_BUILDER);

        SERVER = SERVER_BUILDER.build();
        CLIENT = CLIENT_BUILDER.build();
    }

    private GrowthcraftBambooConfig() { /* Prevent Default Public Constructor */ }

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
     * World generation settings for Growthcraft Bamboo. World generation is server side only.
     *
     * @param server Server side ForgeConfigSpec builder
     */
    public static void initWorldGenConfig(ForgeConfigSpec.Builder server) {
        server.push("_general");
        server.comment(String.format("General configuration for %s.", Reference.NAME)).define("_general.version", Reference.VERSION);
        server.pop();

        server.push("worldgen");
        enableBambooTrees = server
                .comment("Configure Growthcraft Bamboo Trees")
                .define("worldgen.bamboo_trees.enable", true);
        enableBambooTreeSpread = server
                .comment("Can bamboo trees automatically spread and grow other trees?")
                .define("worldgen.bamboo_trees.enable_spread", true);
        maxBambooTreesHeight = server
                .comment("Set the maximum height that bamboo trees will grow.")
                .defineInRange("worldgen.bamboo_trees.maxHeight", 20, 5, 250);

        server.pop();
    }

    public boolean bambooTreesEnabled() {
        return enableBambooTrees.get();
    }

}

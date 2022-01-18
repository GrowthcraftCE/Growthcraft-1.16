package growthcraft.core.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftConfig {

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final String SERVER_CONFIG = "growthcraft-server.toml";

    private static final String CATEGORY_WORLDGEN = "worldgen";

    private static ForgeConfigSpec.BooleanValue saltOreGenEnabled;
    private static ForgeConfigSpec.IntValue saltOreGenVeinSize;
    private static ForgeConfigSpec.IntValue saltOreGenHeightMin;
    private static ForgeConfigSpec.IntValue saltOreGenHeightMax;
    private static ForgeConfigSpec.IntValue saltOreGenSpreadAmount;

    static {
        initServerConfig(SERVER_BUILDER);
        SERVER = SERVER_BUILDER.build();
    }

    private GrowthcraftConfig() {
        /* Prevent generation of publc constructor */
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
        saltOreGenEnabled = specBuilder
                .comment("Set to false to disable Growthcraft Salt Ore Generation.")
                .define(String.format("%s.%s", CATEGORY_WORLDGEN, "saltOreGenEnabled"), true);
        saltOreGenVeinSize = specBuilder
                .comment("Set to the max number of ores to generate in the vein.")
                .defineInRange(String.format("%s.%s", CATEGORY_WORLDGEN, "saltOreGenVeinSize"), 5, 1, 10);
        saltOreGenHeightMin = specBuilder
                .comment("Set to the minimum Y value height to generate Salt ore.")
                .defineInRange(String.format("%s.%s", CATEGORY_WORLDGEN, "saltOreGenHeightMin"), 15, -64, 255);
        saltOreGenHeightMax = specBuilder
                .comment("Set to the maximum Y value height to generate Salt ore. This value must be higher than saltOreGenHeightMin.")
                .defineInRange(String.format("%s.%s", CATEGORY_WORLDGEN, "saltOreGenHeightMax"), 64, -64, 255);
        saltOreGenSpreadAmount = specBuilder
                .comment("Set to the satutration spread for salt ore generation within a chunk.")
                .defineInRange(String.format("%s.%s", CATEGORY_WORLDGEN, "saltOreGenSpreadAmount"), 10, 1, 20);
    }

    public static boolean isSaltOreGenEnabled() {
        return saltOreGenEnabled.get();
    }

    public static int getSaltOreGenVeinSize() {
        return saltOreGenVeinSize.get();
    }

    public static int getSaltOreGenHeightMin() {
        return saltOreGenHeightMin.get();
    }

    public static int getSaltOreGenHeightMax() {
        return saltOreGenHeightMax.get();
    }

    public static int getSaltOreGenSpreadAmount() {
        return saltOreGenSpreadAmount.get();
    }
}

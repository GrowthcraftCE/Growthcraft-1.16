package growthcraft.apiary.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import growthcraft.apiary.shared.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftApiaryConfig {

    public static final String SERVER_CONFIG = String.format("growthcraft-%s-server.toml", Reference.NAME_SHORT);
    public static final String CLIENT_CONFIG = String.format("growthcraft-%s-client.toml", Reference.NAME_SHORT);

    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec CLIENT;

    private static final String CATEGORY_BEE_BOX = "beeBox";

    private static ForgeConfigSpec.IntValue bee_box_flower_range;
    private static ForgeConfigSpec.IntValue bee_box_max_processing_time;
    private static ForgeConfigSpec.BooleanValue replicate_flowers;
    private static ForgeConfigSpec.IntValue bee_box_chance_bee_increment;
    private static ForgeConfigSpec.IntValue bee_box_chance_chance_replicate_flower;

    static {
        initBeeBoxConfig(SERVER_BUILDER);

        SERVER = SERVER_BUILDER.build();
        CLIENT = CLIENT_BUILDER.build();
    }

    private GrowthcraftApiaryConfig() { /* Disable default public constructor */ }

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

    public static void initBeeBoxConfig(ForgeConfigSpec.Builder specBuilder) {
        bee_box_flower_range = specBuilder
                .comment("Set the range for the bee box to look for flowers.")
                .defineInRange(String.format("%s.%s", CATEGORY_BEE_BOX, "flowerRange"), 9, 0, 18);
        bee_box_max_processing_time = specBuilder
                .comment("Set the process time for the bee box to update. Default is once a minute.")
                .defineInRange(String.format("%s.%s", CATEGORY_BEE_BOX, "maxProcessingTime"), 1200, 200, 1728000);
        bee_box_chance_bee_increment = specBuilder
                .comment("Set the percentage chance to increment bee population in the Bee Box.")
                .defineInRange(String.format("%s.%s", CATEGORY_BEE_BOX, "chanceBeeIncrement"), 10, 1, 100);
        bee_box_chance_chance_replicate_flower = specBuilder
                .comment("Set the percentage chance to replicate a flower near by.")
                .defineInRange(String.format("%s.%s", CATEGORY_BEE_BOX, "chanceReplicateFlower"), 10, 1, 100);
        replicate_flowers = specBuilder
                .comment("Set to false to disable flower replication by the bee box")
                .define(String.format("%s.%s", CATEGORY_BEE_BOX, "replicateFlowers"), true);
    }

    public static int getBeeBoxFlowerRange() {
        return bee_box_flower_range.get();
    }

    public static int getBeeBoxMaxProcessingTime() {
        return bee_box_max_processing_time.get();
    }

    public static boolean shouldReplicateFlowers() {
        return replicate_flowers.get();
    }

    public static int getChanceToIncreaseBees() {
        return bee_box_chance_bee_increment.get();
    }

    public static int getChanceToReplicateFlowers() {
        return bee_box_chance_chance_replicate_flower.get();
    }

}

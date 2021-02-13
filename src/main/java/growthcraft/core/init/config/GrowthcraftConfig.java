package growthcraft.core.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import growthcraft.core.shared.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

/**
 * Core Configuration For Growthcraft. This configuration will also load the sub-mod configurations as well.
 */
public class GrowthcraftConfig {

    public static final String SERVER_CONFIG = "growthcraft-server.toml";
    public static final String CLIENT_CONFIG = "growthcraft-client.toml";

    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec CLIENT;

    private static ForgeConfigSpec.IntValue saltOreGenChance;
    private static ForgeConfigSpec.BooleanValue saltOreGenerate;
    private static ForgeConfigSpec.IntValue saltOreGenCount;
    private static ForgeConfigSpec.IntValue saltOreGenMinHeight;
    private static ForgeConfigSpec.IntValue saltOreGenMaxHeight;

    private static ForgeConfigSpec.BooleanValue enableGrowthcraftApples;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftBamboo;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftBees;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftCellar;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftDeco;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftTrapper;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftGrapes;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftHops;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftMilk;
    private static ForgeConfigSpec.BooleanValue enableGrowthcraftRice;

    static {
        initSubModules(SERVER_BUILDER, CLIENT_BUILDER);
        initWorldGenConfig(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER = SERVER_BUILDER.build();
        CLIENT = CLIENT_BUILDER.build();
    }

    private GrowthcraftConfig() { /* Prevent Default Public Constructor */ }

    public static void loadConfig() {

        // Load Growthcraft Core Config
        loadConfig(
                SERVER, FMLPaths.CONFIGDIR.get().
                        resolve(SERVER_CONFIG).toString());
        loadConfig(
                CLIENT, FMLPaths.CONFIGDIR.get().
                        resolve(CLIENT_CONFIG).toString());
    }

    public static void loadConfig(ForgeConfigSpec configSpec, String path) {
        final CommentedFileConfig fileConfig = CommentedFileConfig.builder(
                new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();

        fileConfig.load();
        configSpec.setConfig(fileConfig);
    }

    public static void initSubModules(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
        server.comment("Growthcraft Sub-Mod Initialization");
        enableGrowthcraftApples = server.comment("Enable/Disable Growthcraft Apples").define("growthcraft.apples.enable", true);
        enableGrowthcraftBamboo = server.comment("Enable/Disable Growthcraft Bamboo").define("growthcraft.bamboo.enable", true);
        enableGrowthcraftBees = server.comment("Enable/Disable Growthcraft Bees").define("growthcraft.bees.enable", true);
        enableGrowthcraftCellar = server.comment("Enable/Disable Growthcraft Cellar").define("growthcraft.cellar.enable", true);
        enableGrowthcraftDeco = server.comment("Enable/Disable Growthcraft Decorations").define("growthcraft.deco.enable", true);
        enableGrowthcraftGrapes = server.comment("Enable/Disable Growthcraft Grapes - Requires Cellar for Wine").define("growthcraft.grapes.enable", true);
        enableGrowthcraftHops = server.comment("Enable/Disable Growthcraft Hops - Requires Cellar for Ale").define("growthcraft.hops.enable", true);
        enableGrowthcraftMilk = server.comment("Enable/Disable Growthcraft Milk - Requires Cellar").define("growthcraft.milk.enable", true);
        enableGrowthcraftRice = server.comment("Enable/Disable Growthcraft Rice - Requires Cellar").define("growthcraft.rice.enable", true);
        enableGrowthcraftTrapper = server.comment("Enable/Disable Growthcraft Trapper").define("growthcraft.trapper.enable", true);
    }

    public static void initWorldGenConfig(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {

        server.comment(String.format("General configuration for %s.", Reference.NAME)).define("general.version", Reference.VERSION);

        saltOreGenerate = server
                .comment("Generate Growthcraft custom ores within the world.")
                .define("worldgen.saltOre.enable", true);
        saltOreGenChance = server
                .comment("Maximum number of ore veins generated in a chunk.")
                .defineInRange("worldgen.saltOre.chance", 20, 1, 1000000);
        saltOreGenCount = server
                .comment("Max number of SaltOre per cluster.")
                .defineInRange("worldgen.saltOre.count", 5, 1, 10);
        saltOreGenMinHeight = server
                .comment("Minimum height to generate SaltOre within a chunk.")
                .defineInRange("worldgen.saltOre.minHeight", 10, 1, 128);
        saltOreGenMaxHeight = server
                .comment("Maximum height to generate SaltOre within a chunk.")
                .defineInRange("worldgen.saltOre.maxHeight", 100, 1, 128);

    }

    // region Getters
    public static boolean getSaltOreGenerate() {
        return saltOreGenerate.get();
    }

    public static int getSaltOreGenChance() {
        return saltOreGenChance.get();
    }

    public static int getSaltOreGenCount() {
        return saltOreGenCount.get();
    }

    public static int getSaltOreGenMinHeight() {
        return saltOreGenMinHeight.get();
    }

    public static int getSaltOreGenMaxHeight() {
        return saltOreGenMaxHeight.get();
    }

    public static boolean growthcraftApplesEnabled() {
        return enableGrowthcraftApples.get();
    }

    public static boolean growthcraftBambooEnabled() {
        return enableGrowthcraftBamboo.get();
    }

    public static boolean growthcrafBeesEnabled() {
        return enableGrowthcraftBees.get();
    }

    public static boolean growthcraftCellarEnabled() {
        return enableGrowthcraftCellar.get();
    }

    public static boolean growthcraftDecoEnabled() {
        return enableGrowthcraftDeco.get();
    }

    public static boolean growthcraftTrapperEnabled() {
        return enableGrowthcraftTrapper.get();
    }

    public static boolean growthcraftGrapesEnabled() {
        return enableGrowthcraftGrapes.get();
    }

    public static boolean growthcraftHopsEnabled() {
        return enableGrowthcraftHops.get();
    }

    public static boolean growthcraftMilkEnabled() {
        return enableGrowthcraftMilk.get();
    }

    public static boolean growthcraftRiceEnabled() {
        return enableGrowthcraftRice.get();
    }

    // endregion
}

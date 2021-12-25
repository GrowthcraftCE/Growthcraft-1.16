package growthcraft.cellar.shared;

import java.util.HashMap;
import java.util.Map;

public class UnlocalizedName {

    public static final String POTION_ALE = "potion_ale";
    public static final String BREW_KETTLE = "brew_kettle";
    public static final String BREW_KETTLE_LID = "brew_kettle_lid";
    public static final String BREW_KETTLE_RECIPE = "brew_kettle_recipe";
    public static final String CULTURE_JAR = "culture_jar";
    public static final String CULTURE_JAR_RECIPE = "culture_jar_recipe";
    public static final String FERMENT_BARREL = "barrel_ferment";
    public static final String FERMENT_BARREL_OAK = "barrel_ferment_oak";
    public static final String FERMENT_BARREL_RECIPE = "ferment_barrel_recipe";
    public static final String FRUIT_PRESS = "fruit_press";
    public static final String FRUIT_PRESS_PISTON = "fruit_press_piston";
    public static final String FRUIT_PRESS_RECIPE = "fruit_press_recipe";
    public static final String GRAIN = "grain";
    public static final String GRAIN_AMBER = "grain_amber";
    public static final String GRAIN_BROWN = "grain_brown";
    public static final String GRAIN_COPPER = "grain_copper";
    public static final String GRAIN_DARK = "grain_dark";
    public static final String GRAIN_DEEP_AMBER = "grain_deep_amber";
    public static final String GRAIN_DEEP_COPPER = "grain_deep_copper";
    public static final String GRAIN_GOLDEN = "grain_golden";
    public static final String GRAIN_PALE_GOLDEN = "grain_pale_golden";
    public static final String GRAPE_PURPLE = "grape_purple";
    public static final String GRAPE_RED = "grape_red";
    public static final String GRAPE_SEEDS_PURPLE = "grape_seeds_purple";
    public static final String GRAPE_SEEDS_RED = "grape_seeds_red";
    public static final String GRAPE_SEEDS_WHITE = "grape_seeds_white";
    public static final String GRAPE_VINE = "grape_vine";
    public static final String GRAPE_VINE_CROP = "grape_vine_crop";
    public static final String GRAPE_VINE_LEAVES = "grape_vine_leaves";
    public static final String GRAPE_WHITE = "grape_white";
    public static final String HOPS = "hops";
    public static final String HOPS_SEEDS = "hops_seeds";
    public static final String HOPS_VINE = "hops_vine";
    public static final String POTION_LAGER = "potion_lager";
    public static final String LOOT_SERIALIZER_BLOCK = "global_block_loot_modifier";
    public static final String ROASTER = "roaster";
    public static final String ROASTER_RECIPE = "roaster_recipe";
    public static final String POTION_WINE = "potion_wine";
    public static final String YEAST_BAYANUS = "yeast_bayanus";
    public static final String YEAST_BAYANUS_ETHEREAL = "yeast_bayanus_ethereal";
    public static final String YEAST_BREWERS = "yeast_brewers";
    public static final String YEAST_BREWERS_ETHEREAL = "yeast_brewers_ethereal";
    public static final String YEAST_ETHEREAL = "yeast_ethereal";
    public static final String YEAST_LAGER = "yeast_lager";
    public static final String YEAST_LAGER_ETHEREAL = "yeast_lager_ethereal";

    private UnlocalizedName() {
        /* Prevent generation of default public constructor */
    }

    public static Map<String, String> getFluidNames(String baseName) {
        Map<String, String> names = new HashMap<>();
        names.put("block", baseName);
        names.put("flowing", baseName + "_fluid_flowing");
        names.put("fluid", baseName + "_fluid");
        names.put("bucket", baseName + "_bucket");
        return names;
    }

}

package growthcraft.cellar.shared;

import java.util.HashMap;
import java.util.Map;

public class UnlocalizedName {

    public static final String BREW_KETTLE = "brew_kettle";
    public static final String BREW_KETTLE_LID = "brew_kettle_lid";
    public static final String CULTURE_JAR = "culture_jar";
    public static final String CULTURE_JAR_RECIPE = "culture_jar_recipe";
    public static final String BREW_KETTLE_RECIPE = "brew_kettle_recipe";
    public static final String FERMENT_BARREL = "barrel_ferment";
    public static final String FERMENT_BARREL_OAK = "barrel_ferment_oak";
    public static final String FERMENT_BARREL_RECIPE = "ferment_barrel_recipe";
    public static final String GRAIN = "grain";
    public static final String GRAIN_PALE_GOLDEN = "grain_pale_golden";
    public static final String GRAIN_GOLDEN = "grain_golden";
    public static final String GRAIN_AMBER = "grain_amber";
    public static final String GRAIN_DARK_AMBER = "grain_dark_amber";
    public static final String GRAIN_COPPER = "grain_copper";
    public static final String GRAIN_DEEP_COPPER = "grain_deep_copper";
    public static final String GRAIN_BROWN = "grain_brown";
    public static final String GRAIN_DARK = "grain_dark";

    public static final String YEAST_BAYANUS = "yeast_bayanus";
    public static final String YEAST_BAYANUS_ETHEREAL = "yeast_bayanus_ethereal";
    public static final String YEAST_BREWERS = "yeast_brewers";
    public static final String YEAST_BREWERS_ETHEREAL = "yeast_brewers_ethereal";
    public static final String YEAST_ETHEREAL = "yeast_ethereal";
    public static final String YEAST_LAGER = "yeast_lager";
    public static final String YEAST_LAGER_ETHEREAL = "yeast_lager_ethereal";
    public static final String LOOT_SERIALIZER_BLOCK = "global_block_loot_modifier";

    public static final String ROASTER = "roaster";
    public static final String ROASTER_RECIPE = "roaster_recipe";

    public static Map<String, String> getFluidNames(String baseName) {
        Map<String, String> names = new HashMap<>();
        names.put("block", baseName);
        names.put("flowing", baseName + "_fluid_flowing");
        names.put("fluid", baseName + "_fluid");
        names.put("bucket", baseName + "_bucket");
        return names;
    }

    private UnlocalizedName() {
        /* Prevent generation of default public constructor */
    }

}

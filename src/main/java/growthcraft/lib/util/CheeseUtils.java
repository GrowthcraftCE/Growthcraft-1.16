package growthcraft.lib.util;

import java.util.HashMap;
import java.util.Map;

public class CheeseUtils {

    public static final String AGED = "aged";
    public static final String WAXED = "waxed";
    public static final String CURDS = "curds";
    public static final String SLICE = "slice";

    private CheeseUtils() {
        /* Prevent generation of public constructor */
    }

    public static Map<String, String> getCheeseNames(String baseName) {
        Map<String, String> names = new HashMap<>();
        names.put("aged", baseName + "_" + AGED);
        names.put("waxed", baseName + "_" + WAXED);
        names.put("curds", baseName + "_" + CURDS);
        names.put("slice", baseName + "_" + SLICE);
        return names;
    }

}

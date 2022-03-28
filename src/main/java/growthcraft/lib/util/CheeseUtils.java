package growthcraft.lib.util;

import java.util.HashMap;
import java.util.Map;

public class CheeseUtils {

    public static final String AGED = "aged";
    public static final String WAXED = "waxed";
    public static final String CURDS = "curds";
    public static final String SLICE = "slice";
    public static final String CHEESE = "cheese";
    public static final String DRAINED = "drained";

    private CheeseUtils() {
        /* Prevent generation of public constructor */
    }

    public static Map<String, String> getCheeseNames(String cheeseName) {
        Map<String, String> names = new HashMap<>();
        String baseName = String.format("%s_%s", cheeseName, CHEESE);
        names.put(CHEESE, baseName);
        names.put(AGED, baseName + "_" + AGED);
        names.put(WAXED, baseName + "_" + WAXED);
        names.put(CURDS, baseName + "_" + CURDS);
        names.put(SLICE, baseName + "_" + SLICE);
        names.put(DRAINED, baseName + "_" + CURDS + "_" + DRAINED);
        return names;
    }

}

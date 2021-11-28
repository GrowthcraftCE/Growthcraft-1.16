package growthcraft.lib.util;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class FluidUtils {

    public static class FluidResource {
        public static final ResourceLocation FLOWING = new ResourceLocation("block/water_flow");
        public static final ResourceLocation OVERLAY = new ResourceLocation("block/water_overlay");
        public static final ResourceLocation STILL = new ResourceLocation("block/water_still");

        private FluidResource() { /* Prevent Public Constructor */ }
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

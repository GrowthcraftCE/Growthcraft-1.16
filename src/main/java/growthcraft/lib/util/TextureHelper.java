package growthcraft.lib.util;

import net.minecraft.util.ResourceLocation;

public class TextureHelper {

    public static ResourceLocation getTextureGui(String modid, String name) {
        return new ResourceLocation(modid, String.format("textures/gui/%s.png", name));
    }

}

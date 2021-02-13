package growthcraft.core.shared;

import net.minecraft.util.ResourceLocation;

public class Reference {
    public static final String MODID = "growthcraft";
    public static final String NAME = "Growthcraft";
    public static final String VERSION = "5.0.0";

    public static final ResourceLocation TAG_ROPE = new ResourceLocation(MODID, UnlocalizedName.TAG_ROPE);
    public static final ResourceLocation TAG_ROPE_FENCE = new ResourceLocation(MODID, UnlocalizedName.TAG_ROPE_FENCE);

    private Reference() { /* Prevent default public constructor */ }

}

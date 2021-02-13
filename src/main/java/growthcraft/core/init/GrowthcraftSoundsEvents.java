package growthcraft.core.init;

import growthcraft.core.shared.Reference;
import growthcraft.core.shared.UnlocalizedName;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftSoundsEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Reference.MODID);

    public static final RegistryObject<SoundEvent> fishtrapOpen;

    static {
        fishtrapOpen = SOUND_EVENTS.register(
                UnlocalizedName.SOUND_FISHTRAP_OPEN,
                () -> new SoundEvent(new ResourceLocation(Reference.MODID, UnlocalizedName.SOUND_FISHTRAP_OPEN))
        );
    }
}

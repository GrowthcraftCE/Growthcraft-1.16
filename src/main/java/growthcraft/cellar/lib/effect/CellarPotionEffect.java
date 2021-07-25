package growthcraft.cellar.lib.effect;

import net.minecraft.potion.Effect;

public class CellarPotionEffect {
    private final Effect effect;
    private final int duration;
    private final int amplifier;

    public CellarPotionEffect(Effect effect, int duration, int amplifier) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public int getDuration() {
        return duration;
    }
}

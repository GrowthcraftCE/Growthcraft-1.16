package growthcraft.lib.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;
import java.util.List;

public class EffectUtils {

    public static List<EffectInstance> getEffectsList(ItemStack stack) {
        List<EffectInstance> effects = new ArrayList<>();
        ListNBT customPotionEffects = getEffects(stack);

        for (int i = 0; i < customPotionEffects.size(); ++i) {
            CompoundNBT compoundPotionEffect = customPotionEffects.getCompound(i);
            EffectInstance effectinstance = EffectInstance.read(compoundPotionEffect);
            effects.add(effectinstance);
        }

        return effects;
    }

    public static ListNBT getEffects(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getTag();
        return compoundnbt.getList("CustomPotionEffects", 9);
    }

    public static void addEffect(ItemStack stack, Effect effect, int duration, int level) {
        boolean flag = true;

        CompoundNBT compoundNBT = stack.getOrCreateTag();
        ListNBT listnbt = getEffects(stack);

        EffectInstance effectInstance = new EffectInstance(
                effect, duration, level
        );

        listnbt.add(effectInstance.write(new CompoundNBT()));
        compoundNBT.put("CustomPotionEffects", listnbt);

    }
}

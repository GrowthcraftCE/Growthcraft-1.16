package growthcraft.cellar.lib.item;

import growthcraft.cellar.lib.effect.CellarPotionEffect;
import growthcraft.lib.common.item.GrowthcraftItem;
import growthcraft.lib.util.TranslationUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CellarPotionItem extends GrowthcraftItem {

    private List<CellarPotionEffect> potionEffects = new ArrayList<>();
    private Color color = new Color(0xCFA26F);

    public CellarPotionItem() {
        super();
    }


    public void setEffects(List<CellarPotionEffect> effects) {
        this.potionEffects = effects;
    }

    public void addEffect(CellarPotionEffect effect) {
        potionEffects.add(effect);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return !potionEffects.isEmpty();
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity) entityLiving : null;

        if (playerentity instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) playerentity, stack);
        }

        if (!worldIn.isRemote) {
            ListNBT effectsNBT = getEffects(stack);
            for (int i = 0; i < effectsNBT.size(); ++i) {
                CompoundNBT storedCompoundNBT = effectsNBT.getCompound(i);

                ResourceLocation storedEffectResourceLocation = ResourceLocation.tryCreate(storedCompoundNBT.getString("id"));
                Effect effect = ForgeRegistries.POTIONS.getValue(storedEffectResourceLocation);
                int effectAmplifier = storedCompoundNBT.getInt("lvl");
                int effectDuration = storedCompoundNBT.getInt("duration");

                EffectInstance effectInstance = new EffectInstance(
                        effect, effectDuration, effectAmplifier
                );

                entityLiving.addPotionEffect(effectInstance);
            }

        }

        if (playerentity != null) {
            if (!playerentity.abilities.isCreativeMode) {
                stack.shrink(1);
            }
        }

        if (playerentity == null || !playerentity.abilities.isCreativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerentity != null) {
                playerentity.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

    public static ListNBT getEffects(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getTag();
        return compoundnbt != null ? compoundnbt.getList("StoredEffects", 10) : new ListNBT();
    }

    // TODO: Pull Color from the ItemStack CompoundNBT
    public int getColor() {
        return this.color.getRGB();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static void addEffect(ItemStack stack, Effect effect, int duration, int level) {
        ListNBT listnbt = getEffects(stack);
        boolean flag = true;
        ResourceLocation effectResourceLocation = Registry.EFFECTS.getKey(effect);

        for (int i = 0; i < listnbt.size(); ++i) {
            CompoundNBT storedCompoundNBT = listnbt.getCompound(i);
            ResourceLocation storedEffectResourceLocation = ResourceLocation.tryCreate(storedCompoundNBT.getString("id"));
            if (storedEffectResourceLocation != null && storedEffectResourceLocation.equals(effectResourceLocation)) {
                if (storedCompoundNBT.getInt("lvl") < level) {
                    storedCompoundNBT.putShort("lvl", (short) level);
                }
                if (storedCompoundNBT.getInt("duration") < duration) {
                    storedCompoundNBT.putShort("duration", (short) duration);
                }
                flag = false;
                break;
            }
        }

        if (flag) {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("id", String.valueOf(effectResourceLocation));
            compoundNBT.putShort("lvl", (short) level);
            compoundNBT.putShort("duration", (short) duration);
            listnbt.add(compoundNBT);
        }

        stack.getOrCreateTag().put("StoredEffects", listnbt);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            ListNBT effects = getEffects(stack);
            for (int i = 0; i < effects.size(); ++i) {
                CompoundNBT storedCompoundNBT = effects.getCompound(i);

                ResourceLocation storedEffectResourceLocation = ResourceLocation.tryCreate(storedCompoundNBT.getString("id"));
                Effect effect = ForgeRegistries.POTIONS.getValue(storedEffectResourceLocation);

                int lvl = storedCompoundNBT.getInt("lvl");
                int duration = storedCompoundNBT.getInt("duration");

                IFormattableTextComponent textComponent = new TranslationTextComponent(effect.getName());
                long minutes = TimeUnit.SECONDS.toMinutes(duration / 20L);
                long seconds = (duration - (minutes * 1200)) / 20;
                String attributes = String.format(" %s (%d:%02d)", TranslationUtils.intToRomanNumeral(lvl), minutes, seconds);
                textComponent.appendString(attributes);

                tooltip.add(textComponent);
            }
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
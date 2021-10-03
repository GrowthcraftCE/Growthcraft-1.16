package growthcraft.cellar.lib.item;

import growthcraft.cellar.lib.effect.CellarPotionEffect;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.advancements.CriteriaTriggers;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellarPotionItem extends GrowthcraftItem {

    private List<CellarPotionEffect> potionEffects = new ArrayList<>();
    private Color color = new Color(0xCFA26F);

    public CellarPotionItem() {
        super();
    }

    public void setColor(Color color) {
        this.color = color;
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

        // TODO: Process potion effects
        if (!worldIn.isRemote) {
            for (CellarPotionEffect effect : potionEffects) {
                EffectInstance effectInstance = new EffectInstance(
                        effect.getEffect(), effect.getDuration(), effect.getAmplifier());
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

    public static ListNBT getEnchantments(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getTag();
        return compoundnbt != null ? compoundnbt.getList("StoredEffects", 10) : new ListNBT();
    }

    public static void addEnchantment(ItemStack stack, Effect effect) {
        ListNBT listnbt = getEnchantments(stack);
        boolean flag = true;
        ResourceLocation resourcelocation = Registry.ENCHANTMENT.getKey(stackIn.enchantment);

        for (int i = 0; i < listnbt.size(); ++i) {
            CompoundNBT compoundnbt = listnbt.getCompound(i);
            ResourceLocation resourcelocation1 = ResourceLocation.tryCreate(compoundnbt.getString("id"));
            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                if (compoundnbt.getInt("lvl") < stackIn.enchantmentLevel) {
                    compoundnbt.putShort("lvl", (short) stackIn.enchantmentLevel);
                }

                flag = false;
                break;
            }
        }

        if (flag) {
            CompoundNBT compoundnbt1 = new CompoundNBT();
            compoundnbt1.putString("id", String.valueOf(resourcelocation));
            compoundnbt1.putShort("lvl", (short) stackIn.enchantmentLevel);
            listnbt.add(compoundnbt1);
        }

        stack.getOrCreateTag().put("StoredEffects", listnbt);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemStack.addEnchantmentTooltips(tooltip, this.potionEffects);
    }

    public int getColor() {
        return this.color.getRGB();
    }
}
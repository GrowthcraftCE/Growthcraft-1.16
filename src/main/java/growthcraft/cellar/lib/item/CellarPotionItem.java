package growthcraft.cellar.lib.item;

import growthcraft.cellar.lib.effect.CellarPotionEffect;
import growthcraft.lib.common.item.GrowthcraftItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CellarPotionItem extends GrowthcraftItem {

    private List<CellarPotionEffect> potionEffects = new ArrayList<>();

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

}

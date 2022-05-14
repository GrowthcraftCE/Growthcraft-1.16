package growthcraft.milk.common.item;

import growthcraft.core.shared.Reference;
import growthcraft.milk.init.GrowthcraftMilkItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;

/**
 * The Milking Bucket Item is designed to be able to "milk" entities.
 */
public class MilkingBucketItem extends Item {

    public MilkingBucketItem() {
        super(getInitProperties());
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if (target.world.isRemote) return net.minecraft.util.ActionResultType.PASS;

        // Add a milk bucket to the player inventory.
        EntityTypeTags.createOptional(new ResourceLocation(growthcraft.milk.shared.Reference.MODID, "milkable"), null);

        ITag.INamedTag<EntityType<?>> tagMilkable = EntityTypeTags.getTagById(
                new ResourceLocation(growthcraft.milk.shared.Reference.MODID, "milkable").toString()
        );

        if (target.getType().isContained(tagMilkable)) {
            playerIn.addItemStackToInventory(new ItemStack(GrowthcraftMilkItems.BUCKET_MILK.get()));
            playerIn.getHeldItem(hand).shrink(1);
        }

        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    private static Properties getInitProperties() {
        Properties properties = new Properties();
        properties.group(Reference.growthcraftCreativeTab);
        properties.maxStackSize(16);
        return properties;
    }
}

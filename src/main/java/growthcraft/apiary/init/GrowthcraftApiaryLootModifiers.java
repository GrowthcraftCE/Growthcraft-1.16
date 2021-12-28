package growthcraft.apiary.init;

import growthcraft.apiary.common.loot.GrowthcraftApiaryLootModifier;
import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApiaryLootModifiers {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS
            = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Reference.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<GrowthcraftApiaryLootModifier>> global_block_loot_modifier
            = LOOT_MODIFIER_SERIALIZERS.register(
            UnlocalizedName.LOOT_SERIALIZER_BLOCK,
            GrowthcraftApiaryLootModifier.Serializer::new
    );

    private GrowthcraftApiaryLootModifiers() {
        /* Prevent generation of default public constructor */
    }
}

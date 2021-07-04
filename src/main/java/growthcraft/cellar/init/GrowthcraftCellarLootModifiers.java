package growthcraft.cellar.init;

import growthcraft.cellar.common.loot.GrowthcraftCellarLootModifier;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarLootModifiers {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS
            = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Reference.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<GrowthcraftCellarLootModifier>> global_block_loot_modifier
            = LOOT_MODIFIER_SERIALIZERS.register(
            UnlocalizedName.LOOT_SERIALIZER_BLOCK,
            GrowthcraftCellarLootModifier.Serializer::new
    );

    private GrowthcraftCellarLootModifiers() {
        /* Prevent generation of default public constructor */
    }
}

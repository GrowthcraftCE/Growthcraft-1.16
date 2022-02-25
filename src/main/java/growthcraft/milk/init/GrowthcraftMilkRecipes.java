package growthcraft.milk.init;

import growthcraft.milk.common.recipe.*;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);

    public static final IRecipeType<ChurnRecipe> CHURN_RECIPE_TYPE = IRecipeType.register(
            new ResourceLocation(Reference.MODID, UnlocalizedName.CHURN_RECIPE).toString()
    );
    public static final RegistryObject<IRecipeSerializer<?>> CHURN_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.CHURN_RECIPE, ChurnRecipeSerializer::new
    );
    public static final IRecipeType<MixingVatRecipe> MIXING_VAT_FLUID_RECIPE_TYPE = IRecipeType.register(
            new ResourceLocation(Reference.MODID, UnlocalizedName.MIXING_VAT_FLUID_RECIPE).toString()
    );
    public static final IRecipeType<MixingVatRecipe> MIXING_VAT_ITEM_RECIPE_TYPE = IRecipeType.register(
            new ResourceLocation(Reference.MODID, UnlocalizedName.MIXING_VAT_ITEM_RECIPE).toString()
    );
    public static final IRecipeType<PancheonRecipe> PANCHEON_RECIPE_TYPE = IRecipeType.register(
            new ResourceLocation(Reference.MODID, UnlocalizedName.PANCHEON_RECIPE).toString()
    );
    public static final RegistryObject<IRecipeSerializer<?>> MIXING_VAT_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.MIXING_VAT_RECIPE,
            MixingVatRecipeSerializer::new
    );
    public static final IRecipeType<MixingVatRecipe> MIXING_VAT_RECIPE_TYPE = IRecipeType.register(
            new ResourceLocation(Reference.MODID, UnlocalizedName.MIXING_VAT_RECIPE).toString()
    );
    public static final RegistryObject<IRecipeSerializer<?>> PANCHEON_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.PANCHEON_RECIPE, PancheonRecipeSerializer::new
    );

    private GrowthcraftMilkRecipes() {
        /* Prevent generation of public constructor */
    }
}

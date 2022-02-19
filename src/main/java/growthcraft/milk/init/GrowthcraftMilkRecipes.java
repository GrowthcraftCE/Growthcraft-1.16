package growthcraft.milk.init;

import growthcraft.milk.common.recipe.ChurnRecipe;
import growthcraft.milk.common.recipe.ChurnRecipeSerializer;
import growthcraft.milk.common.recipe.PancheonRecipe;
import growthcraft.milk.common.recipe.PancheonRecipeSerializer;
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

    // Recipe Serializers
    public static final RegistryObject<IRecipeSerializer<?>> CHURN_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.CHURN_RECIPE, ChurnRecipeSerializer::new
    );
    public static final RegistryObject<IRecipeSerializer<?>> PANCHEON_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.PANCHEON_RECIPE, PancheonRecipeSerializer::new
    );

    // Recipe Types
    public static final IRecipeType<ChurnRecipe> CHURN_RECIPE_TYPE = IRecipeType.register(
            new ResourceLocation(Reference.MODID, UnlocalizedName.CHURN_RECIPE).toString()
    );
    public static final IRecipeType<PancheonRecipe> PANCHEON_RECIPE_TYPE = IRecipeType.register(
            new ResourceLocation(Reference.MODID, UnlocalizedName.PANCHEON_RECIPE).toString()
    );

    private GrowthcraftMilkRecipes() {
        /* Prevent generation of public constructor */
    }
}

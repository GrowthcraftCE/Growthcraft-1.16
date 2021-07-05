package growthcraft.cellar.init;

import growthcraft.cellar.common.recipe.*;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);

    public static final RegistryObject<IRecipeSerializer<?>> BREW_KETTLE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.BREW_KETTLE_RECIPE,
            BrewKettleRecipeSerializer::new);

    public static final RegistryObject<IRecipeSerializer<?>> CULTURE_JAR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.CULTURE_JAR_RECIPE,
            CultureJarRecipeSerializer::new);

    public static final IRecipeType<BrewKettleRecipe> BREW_KETTLE_RECIPE_TYPE = new BrewKettleRecipeType();
    public static final IRecipeType<CultureJarRecipe> CULTURE_JAR_RECIPE_TYPE = new CultureJarRecipeType();

    private GrowthcraftCellarRecipes() {
        /* Prevent default public constructor */
    }
}

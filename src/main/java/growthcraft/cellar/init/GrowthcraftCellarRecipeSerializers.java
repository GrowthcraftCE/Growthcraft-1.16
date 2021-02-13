package growthcraft.cellar.init;

import growthcraft.cellar.lib.recipe.BrewKettleRecipe;
import growthcraft.cellar.lib.recipe.IBrewKettleRecipe;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.registry.BrewKettleRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarRecipeSerializers {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS =
            new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);

    public static final IRecipeSerializer<BrewKettleRecipe> BREW_KETTLE_RECIPE_SERIALIZER =
            new BrewKettleRecipeSerializer();

    public static final RegistryObject<IRecipeSerializer<?>> BREW_KETTLE_SERIALIZER = RECIPE_SERIALIZERS.register(
            UnlocalizedName.BREW_KETTLE,
            () -> BREW_KETTLE_RECIPE_SERIALIZER);

    public static final IRecipeType<IBrewKettleRecipe> BREW_KETTLE_RECIPE_TYPE = registerType(IBrewKettleRecipe.RECIPE_TYPE_ID);

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }
}

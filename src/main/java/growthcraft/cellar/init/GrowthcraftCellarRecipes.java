package growthcraft.cellar.init;

import growthcraft.cellar.common.recipe.*;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);

    //region Recipe Serializers
    public static final RegistryObject<IRecipeSerializer<?>> BREW_KETTLE_RECIPE_SERIALIZER
            = RECIPE_SERIALIZERS.register(
            UnlocalizedName.BREW_KETTLE_RECIPE,
            BrewKettleRecipeSerializer::new);

    public static final RegistryObject<IRecipeSerializer<?>> CULTURE_JAR_RECIPE_SERIALIZER
            = RECIPE_SERIALIZERS.register(
            UnlocalizedName.CULTURE_JAR_RECIPE,
            CultureJarRecipeSerializer::new);

    public static final RegistryObject<IRecipeSerializer<?>> FERMENT_BARREL_SERIALIZER
            = RECIPE_SERIALIZERS.register(
            UnlocalizedName.FERMENT_BARREL_RECIPE,
            FermentBarrelRecipeSerializer::new
    );

    public static final RegistryObject<IRecipeSerializer<?>> ROASTER_RECIPE_SERIALIZER
            = RECIPE_SERIALIZERS.register(
            UnlocalizedName.ROASTER_RECIPE,
            RoasterRecipeSerializer::new
    );

    public static final RegistryObject<IRecipeSerializer<?>> FRUIT_PRESS_SERIALIZER
            = RECIPE_SERIALIZERS.register(
            UnlocalizedName.FRUIT_PRESS_RECIPE,
            FruitPressRecipeSerializer::new
    );
    //endregion

    //region Recipe Types
    public static final IRecipeType<BrewKettleRecipe> BREW_KETTLE_RECIPE_TYPE
            = IRecipeType.register(new ResourceLocation(Reference.MODID, UnlocalizedName.BREW_KETTLE_RECIPE).toString());
    public static final IRecipeType<CultureJarRecipe> CULTURE_JAR_RECIPE_TYPE
            = IRecipeType.register(new ResourceLocation(Reference.MODID, UnlocalizedName.CULTURE_JAR_RECIPE).toString());
    public static final IRecipeType<FermentBarrelRecipe> FERMENT_BARREL_RECIPE_TYPE
            = IRecipeType.register(new ResourceLocation(Reference.MODID, UnlocalizedName.FERMENT_BARREL_RECIPE).toString());
    public static final IRecipeType<RoasterRecipe> ROASTER_RECIPE_TYPE
            = IRecipeType.register(new ResourceLocation(Reference.MODID, UnlocalizedName.ROASTER_RECIPE).toString());
    public static final IRecipeType<FruitPressRecipe> FRUIT_PRESS_RECIPE_TYPE
            = IRecipeType.register(new ResourceLocation(Reference.MODID, UnlocalizedName.FRUIT_PRESS_RECIPE).toString());
    //endregion

    private GrowthcraftCellarRecipes() {
        /* Prevent default public constructor */
    }
}

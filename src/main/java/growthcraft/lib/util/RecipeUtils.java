package growthcraft.lib.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeUtils {

    public static Set<IRecipe<?>> findRecipesByType(World world, IRecipeType<?> recipeType) {
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(recipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> brewKettleRecipeType) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ?
                world.getRecipeManager().getRecipes().stream()
                        .filter(recipe -> recipe.getType().toString().equals(brewKettleRecipeType.toString())).collect(Collectors.toSet())
                : Collections.emptySet();
    }

}

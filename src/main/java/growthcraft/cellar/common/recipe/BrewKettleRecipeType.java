package growthcraft.cellar.common.recipe;

import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

public class BrewKettleRecipeType implements IRecipeType<BrewKettleRecipe> {

    @Override
    public String toString() {
        return new ResourceLocation(Reference.MODID, UnlocalizedName.BREW_KETTLE_RECIPE).toString();
    }
}

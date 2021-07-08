package growthcraft.cellar.common.recipe;

import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

public class FermentBarrelRecipeType implements IRecipeType<FermentBarrelRecipe> {
    @Override
    public String toString() {
        return new ResourceLocation(Reference.MODID, UnlocalizedName.FERMENT_BARREL_RECIPE).toString();
    }
}

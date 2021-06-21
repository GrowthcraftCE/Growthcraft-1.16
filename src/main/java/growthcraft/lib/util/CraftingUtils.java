package growthcraft.lib.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class CraftingUtils {

    public static FluidStack getFluidStack(JsonObject json) {
        String fluidName = JSONUtils.getString(json, "fluid");
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName));

        if(fluid == null) {
            throw new JsonSyntaxException("Unknown fluid '" + fluidName + "' in Json.");
        }

        int fluidAmount = JSONUtils.getInt(json, "amount", 1000);
        return new FluidStack(fluid, fluidAmount);
    }

    private CraftingUtils() { /* Prevent automatic public constructor */ }
}

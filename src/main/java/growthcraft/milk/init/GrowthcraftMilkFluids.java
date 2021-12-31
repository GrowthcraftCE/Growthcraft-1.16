package growthcraft.milk.init;

import growthcraft.milk.shared.Reference;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MODID);

    private GrowthcraftMilkFluids() {
        /* Prevent generation of public constructor */
    }
}

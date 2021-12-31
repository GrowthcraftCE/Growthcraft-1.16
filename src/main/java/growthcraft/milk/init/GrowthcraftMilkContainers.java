package growthcraft.milk.init;

import growthcraft.milk.shared.Reference;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);

    private GrowthcraftMilkContainers() {
        /* Prevent generation of public constructor */
    }
}

package growthcraft.trapper.init;

import growthcraft.trapper.lib.common.inventory.ContainerFishtrap;
import growthcraft.trapper.shared.Reference;
import growthcraft.trapper.shared.UnlocalizedName;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftTrapperContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Reference.MODID);

    public static final RegistryObject<ContainerType<ContainerFishtrap>> fishtrapContainer;

    static {
        fishtrapContainer = CONTAINERS.register(
                UnlocalizedName.FISHTRAP_CONTAINER,
                () -> IForgeContainerType.create(ContainerFishtrap::new)
        );
    }

    private GrowthcraftTrapperContainers() { /* Disable default public constructor */ }

}

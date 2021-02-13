package growthcraft.cellar.init;

import growthcraft.cellar.client.container.BrewKettleContainer;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftCellarContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(
            ForgeRegistries.CONTAINERS, Reference.MODID
    );

    public static final RegistryObject<ContainerType<BrewKettleContainer>> BREW_KETTLE;

    static {
        BREW_KETTLE = CONTAINER_TYPES.register(
                UnlocalizedName.BREW_KETTLE,
                () -> IForgeContainerType.create(BrewKettleContainer::new)
        );
    }

    private GrowthcraftCellarContainers() { /* Disable default public constructor */ }
}

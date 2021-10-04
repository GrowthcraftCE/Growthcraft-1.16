package growthcraft.cellar.init;

import growthcraft.cellar.client.container.*;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;

public class GrowthcraftCellarContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);

    public static final RegistryObject<ContainerType<BrewKettleContainer>> brew_kettle_container = CONTAINERS.register(
            UnlocalizedName.BREW_KETTLE,
            () -> IForgeContainerType.create(BrewKettleContainer::new)
    );

    public static final RegistryObject<ContainerType<CultureJarContainer>> culture_jar_container = CONTAINERS.register(
            UnlocalizedName.CULTURE_JAR,
            () -> IForgeContainerType.create(CultureJarContainer::new)
    );

    public static final RegistryObject<ContainerType<FermentBarrelContainer>> ferment_barrel_container = CONTAINERS.register(
            UnlocalizedName.FERMENT_BARREL,
            () -> IForgeContainerType.create(FermentBarrelContainer::new)
    );

    public static final RegistryObject<ContainerType<RoasterContainer>> roaster_container = CONTAINERS.register(
            UnlocalizedName.ROASTER,
            () -> IForgeContainerType.create(RoasterContainer::new)
    );

    public static final RegistryObject<ContainerType<FruitPressContainer>> fruit_press_container = CONTAINERS.register(
            UnlocalizedName.FRUIT_PRESS,
            () -> IForgeContainerType.create(FruitPressContainer::new)
    );

    private GrowthcraftCellarContainers() { /* Disable default public constructor */ }
}

package growthcraft.cellar.init;

import growthcraft.cellar.client.container.BrewKettleContainer;
import growthcraft.cellar.client.container.CultureJarContainer;
import growthcraft.cellar.client.container.FermentBarrelContainer;
import growthcraft.cellar.client.container.RoasterContainer;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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

    private GrowthcraftCellarContainers() { /* Disable default public constructor */ }
}

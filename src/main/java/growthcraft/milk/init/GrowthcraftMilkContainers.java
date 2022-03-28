package growthcraft.milk.init;

import growthcraft.milk.client.container.ChurnContainer;
import growthcraft.milk.client.container.MixingVatContainer;
import growthcraft.milk.client.container.PancheonContainer;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);

    public static final RegistryObject<ContainerType<ChurnContainer>> CHURN_CONTAINER = CONTAINERS.register(
            UnlocalizedName.CHURN, () -> IForgeContainerType.create(ChurnContainer::new)
    );

    public static final RegistryObject<ContainerType<MixingVatContainer>> MIXING_VAT_CONTAINER = CONTAINERS.register(
            UnlocalizedName.MIXING_VAT, () -> IForgeContainerType.create(MixingVatContainer::new)
    );

    public static final RegistryObject<ContainerType<PancheonContainer>> PANCHEON_CONTAINTER = CONTAINERS.register(
            UnlocalizedName.PANCHEON, () -> IForgeContainerType.create(PancheonContainer::new)
    );

    private GrowthcraftMilkContainers() {
        /* Prevent generation of public constructor */
    }
}

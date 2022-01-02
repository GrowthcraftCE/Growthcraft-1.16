package growthcraft.milk.init;

import growthcraft.milk.client.container.ChurnContainer;
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

    private GrowthcraftMilkContainers() {
        /* Prevent generation of public constructor */
    }
}

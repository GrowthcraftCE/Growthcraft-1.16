package growthcraft.apiary.init;

import growthcraft.apiary.client.container.BeeBoxContainer;
import growthcraft.apiary.shared.Reference;
import growthcraft.apiary.shared.UnlocalizedName;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApiaryContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);

    public static final RegistryObject<ContainerType<BeeBoxContainer>> BEE_BOX_CONTAINER = CONTAINERS.register(
            UnlocalizedName.BEE_BOX, () -> IForgeContainerType.create(BeeBoxContainer::new)
    );

}

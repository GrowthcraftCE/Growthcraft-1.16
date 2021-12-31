package growthcraft.milk.init;

import growthcraft.lib.common.item.GrowthcraftBucketItem;
import growthcraft.lib.util.FluidUtils;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftMilkItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_BUTTER_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.BUTTER_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.BUTTER_MILK_FLUID_STILL,
                    Reference.FluidColor.BUTTER_MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CONDENSED_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CONDENSED_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CONDENSED_MILK_FLUID_STILL,
                    Reference.FluidColor.CONDENSED_MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CREAM = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.CREAM).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CREAM_FLUID_STILL,
                    Reference.FluidColor.CREAM_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_KUMIS = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.KUMIS).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.KUMIS_FLUID_STILL,
                    Reference.FluidColor.KUMIS_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.MILK_FLUID_STILL,
                    Reference.FluidColor.MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RENNET = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.RENNET).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.RENNET_FLUID_STILL,
                    Reference.FluidColor.RENNET_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_SKIM_MILK = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.SKIM_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.SKIM_MILK_FLUID_STILL,
                    Reference.FluidColor.SKIM_MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WHEY = ITEMS.register(
            FluidUtils.getFluidNames(UnlocalizedName.FluidName.WHEY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.WHEY_FLUID_STILL,
                    Reference.FluidColor.WHEY_FLUID_COLOR.getColor()
            )
    );

    private GrowthcraftMilkItems() {
        /* Prevent generation of public constructor */
    }

}

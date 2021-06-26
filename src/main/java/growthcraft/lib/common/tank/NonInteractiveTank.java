package growthcraft.lib.common.tank;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class NonInteractiveTank extends FluidTank {
    public NonInteractiveTank(int capacity) {
        super(capacity);
    }

    public int forceFill(FluidStack resource, FluidAction action) {
        return super.fill(resource, action);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return 0;
    }
}

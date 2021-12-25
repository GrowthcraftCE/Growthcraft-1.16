package growthcraft.lib.common.tank.handler;

import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.HashMap;
import java.util.Map;

public class FluidTankHandler {

    private final Map<Integer, FluidTank> fluidTanks;

    public FluidTankHandler(int tanks, int capacity) {
        fluidTanks = new HashMap<>();
        createFluidTanks(tanks, capacity);
    }

    private void createFluidTanks(int tanks, int capacity) {
        for (int i = 0; i < tanks; i++) {
            fluidTanks.put(i, new FluidTank(capacity));
        }
    }

    public FluidTank getTank(int tank) {
        return fluidTanks.get(tank);
    }

    public LazyOptional<IFluidHandler> getFluidTankHandler(int tank) {
        return LazyOptional.of(() -> getTank(tank));
    }

    public int getNumberTanks() {
        return fluidTanks.size();
    }

    public void updateFluidTank(int tank, FluidTank fluidTank) {
        fluidTanks.put(tank, fluidTank);
    }

    public void drain(int index, int amount) {
        this.getTank(index).drain(amount, IFluidHandler.FluidAction.EXECUTE);
    }
}

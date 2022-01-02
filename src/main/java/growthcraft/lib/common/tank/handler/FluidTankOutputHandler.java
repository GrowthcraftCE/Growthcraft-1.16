package growthcraft.lib.common.tank.handler;

import growthcraft.lib.common.tank.NonInteractiveTank;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.HashMap;
import java.util.Map;

public class FluidTankOutputHandler {

    private final Map<Integer, NonInteractiveTank> fluidTanks;

    public FluidTankOutputHandler(int tanks, int capacity) {
        fluidTanks = new HashMap<>();
        createFluidTanks(tanks, capacity);
    }

    private void createFluidTanks(int tanks, int capacity) {
        for (int i = 0; i < tanks; i++) {
            fluidTanks.put(i, new NonInteractiveTank(capacity));
        }
    }

    public NonInteractiveTank getTank(int tank) {
        return fluidTanks.get(tank);
    }

    public LazyOptional<IFluidHandler> getFluidTankHandler(int tank) {
        return LazyOptional.of(() -> getTank(tank));
    }

    public int getNumberTanks() {
        return fluidTanks.size();
    }

    public void updateFluidTank(int tank, NonInteractiveTank fluidTank) {
        fluidTanks.put(tank, fluidTank);
    }

    public void drain(int index, int amount) {
        this.getTank(index).drain(amount, IFluidHandler.FluidAction.EXECUTE);
    }
}

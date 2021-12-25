package growthcraft.apples.common.proxy;

import growthcraft.lib.proxy.IProxy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CommonProxy implements IProxy {

    @Override
    public void init() {
        // Server side proxy initialization
    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("Only run this on the client!");
    }
}
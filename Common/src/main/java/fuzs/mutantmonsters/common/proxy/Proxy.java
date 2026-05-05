package fuzs.mutantmonsters.common.proxy;

import fuzs.mutantmonsters.common.world.entity.CreeperMinion;
import fuzs.puzzleslib.common.api.core.v1.ModLoaderEnvironment;

public interface Proxy {
    Proxy INSTANCE = ModLoaderEnvironment.INSTANCE.isClient() ? new ClientProxy() : new ServerProxy();

    void displayCreeperMinionTrackerGUI(CreeperMinion creeperMinion);
}

package fuzs.mutantmonsters.common.proxy;

import fuzs.mutantmonsters.common.client.gui.screens.CreeperMinionTrackerScreen;
import fuzs.mutantmonsters.common.world.entity.CreeperMinion;
import net.minecraft.client.Minecraft;

public class ClientProxy extends ServerProxy {
    @Override
    public void displayCreeperMinionTrackerGUI(CreeperMinion creeperMinion) {
        Minecraft.getInstance().setScreen(new CreeperMinionTrackerScreen(creeperMinion));
    }
}

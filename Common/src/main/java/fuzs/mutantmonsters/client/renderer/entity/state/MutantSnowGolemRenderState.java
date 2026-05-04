package fuzs.mutantmonsters.client.renderer.entity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;

public class MutantSnowGolemRenderState extends ArmedEntityRenderState {
    public final BlockModelRenderState throwingBlock = new BlockModelRenderState();
    public float throwingTime;
    public boolean hasJackOLantern;

    public boolean isThrowing() {
        return !this.throwingBlock.isEmpty();
    }
}

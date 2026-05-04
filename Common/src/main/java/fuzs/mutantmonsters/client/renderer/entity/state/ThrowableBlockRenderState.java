package fuzs.mutantmonsters.client.renderer.entity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class ThrowableBlockRenderState extends EntityRenderState {
    public final BlockModelRenderState blockModel = new BlockModelRenderState();
    public boolean isLarge;
    public float yRot;
}

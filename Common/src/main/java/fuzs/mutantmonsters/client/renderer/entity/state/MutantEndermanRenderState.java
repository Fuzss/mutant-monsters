package fuzs.mutantmonsters.client.renderer.entity.state;

import fuzs.mutantmonsters.world.entity.animation.EntityAnimation;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.stream.Stream;

public class MutantEndermanRenderState extends EndermanRenderState {
    public float animationTime;
    public EntityAnimation animation;
    public float armScale;
    public boolean isClone;
    public BlockModelRenderState[] heldBlocks = Stream.generate(BlockModelRenderState::new)
            .limit(4)
            .toArray(BlockModelRenderState[]::new);
    public int activeArm;
    public float[] heldBlockTicks = new float[4];
    @Nullable
    public BlockPos teleportPosition;
    @Nullable
    public Vec3 renderOffset;
}

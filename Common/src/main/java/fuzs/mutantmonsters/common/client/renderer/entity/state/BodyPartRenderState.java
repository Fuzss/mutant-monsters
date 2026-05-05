package fuzs.mutantmonsters.common.client.renderer.entity.state;

import fuzs.mutantmonsters.common.world.entity.MutantSkeletonBodyPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class BodyPartRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
    public MutantSkeletonBodyPart.BodyPart bodyPart;
}

package fuzs.mutantmonsters.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.mutantmonsters.common.client.model.MutantSnowGolemModel;
import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantSnowGolemRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;

public class MutantSnowGolemHeldBlockLayer extends RenderLayer<MutantSnowGolemRenderState, MutantSnowGolemModel> {

    public MutantSnowGolemHeldBlockLayer(RenderLayerParent<MutantSnowGolemRenderState, MutantSnowGolemModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, MutantSnowGolemRenderState state, float yRot, float xRot) {
        if (state.isThrowing() && state.throwingTime < 7.0F) {
            poseStack.pushPose();
            boolean isLeftHanded = state.mainArm == HumanoidArm.LEFT;
            float scale = Math.min(0.8F, state.throwingTime / 7.0F);
            poseStack.translate(isLeftHanded ? -0.4 : 0.4, 0.0, 0.0);
            this.getParentModel().translateArm(poseStack, isLeftHanded);
            poseStack.translate(0.0, 0.9, 0.0);
            poseStack.scale(-scale, -scale, scale);
            poseStack.translate(-0.5, -0.5, 0.5);
            poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            state.throwingBlock.submit(poseStack,
                    submitNodeCollector,
                    lightCoords,
                    OverlayTexture.NO_OVERLAY,
                    state.outlineColor);
            poseStack.popPose();
        }
    }
}

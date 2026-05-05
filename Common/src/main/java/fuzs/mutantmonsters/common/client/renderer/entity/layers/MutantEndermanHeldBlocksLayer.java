package fuzs.mutantmonsters.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.mutantmonsters.common.client.model.MutantEndermanModel;
import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantEndermanRenderState;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantEnderman;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;

public class MutantEndermanHeldBlocksLayer extends RenderLayer<MutantEndermanRenderState, MutantEndermanModel> {

    public MutantEndermanHeldBlocksLayer(RenderLayerParent<MutantEndermanRenderState, MutantEndermanModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, MutantEndermanRenderState state, float yRot, float xRot) {
        if (state.animation != MutantEnderman.CLONE_ANIMATION) {
            for (int i = 0; i < state.heldBlocks.length; ++i) {
                BlockModelRenderState blockModel = state.heldBlocks[i];
                if (!blockModel.isEmpty()) {
                    poseStack.pushPose();
                    this.getParentModel().translateRotateArm(poseStack, i);
                    poseStack.translate(0.0, 1.2, 0.0);
                    float rotationAmount = state.ageInTicks + (i + 1) * 2.0F * Mth.PI;
                    poseStack.mulPose(Axis.XP.rotationDegrees(rotationAmount * 10.0F));
                    poseStack.mulPose(Axis.YP.rotationDegrees(rotationAmount * 8.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(rotationAmount * 6.0F));
                    poseStack.scale(-0.75F, -0.75F, 0.75F);
                    poseStack.translate(-0.5, -0.5, 0.5);
                    poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
                    blockModel.submit(poseStack,
                            submitNodeCollector,
                            lightCoords,
                            OverlayTexture.NO_OVERLAY,
                            state.outlineColor);
                    poseStack.popPose();
                }
            }
        }
    }
}

package fuzs.mutantmonsters.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.mutantmonsters.common.MutantMonsters;
import fuzs.mutantmonsters.common.client.model.MutantEndermanModel;
import fuzs.mutantmonsters.common.client.renderer.entity.MutantEndermanRenderer;
import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantEndermanRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ARGB;

public class MutantEndermanEyesLayer extends RenderLayer<MutantEndermanRenderState, MutantEndermanModel> {
    private static final RenderType EYES_RENDER_TYPE = RenderTypes.eyes(MutantMonsters.id(
            "textures/entity/mutant_enderman/mutant_enderman_eyes.png"));

    public MutantEndermanEyesLayer(RenderLayerParent<MutantEndermanRenderState, MutantEndermanModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, MutantEndermanRenderState state, float yRot, float xRot) {
        if (!state.isClone) {
            float alpha = state.deathTime > 80.0F ? MutantEndermanRenderer.getDeathProgress(state) : 1.0F;
            int color = ARGB.colorFromFloat(alpha, 1.0F, 1.0F, 1.0F);
            submitNodeCollector.submitModel(this.getParentModel(),
                    state,
                    poseStack,
                    EYES_RENDER_TYPE,
                    0xF00000,
                    OverlayTexture.NO_OVERLAY,
                    color,
                    null,
                    state.outlineColor,
                    null);
        }
    }
}

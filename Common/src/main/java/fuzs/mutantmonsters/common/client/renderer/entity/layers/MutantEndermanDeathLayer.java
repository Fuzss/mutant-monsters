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
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public class MutantEndermanDeathLayer extends RenderLayer<MutantEndermanRenderState, MutantEndermanModel> {
    private static final Identifier DEATH_TEXTURE_LOCATION = MutantMonsters.id(
            "textures/entity/mutant_enderman/mutant_enderman_death.png");
    private static final RenderType DYING_RENDER_TYPE = RenderTypes.entityCutoutDissolve(MutantEndermanRenderer.TEXTURE_LOCATION,
            DEATH_TEXTURE_LOCATION);

    public MutantEndermanDeathLayer(RenderLayerParent<MutantEndermanRenderState, MutantEndermanModel> renderer) {
        super(renderer);
    }

    /**
     * Parent model is not rendered at the same time.
     *
     * @see MutantEndermanRenderer#getRenderType(MutantEndermanRenderState, boolean, boolean, boolean)
     */
    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, MutantEndermanRenderState state, float yRot, float xRot) {
        if (state.deathTime > 80.0F) {
            int color = ARGB.white(MutantEndermanRenderer.getDeathProgress(state));
            submitNodeCollector.submitModel(this.getParentModel(),
                    state,
                    poseStack,
                    DYING_RENDER_TYPE,
                    state.lightCoords,
                    OverlayTexture.NO_OVERLAY,
                    color,
                    null,
                    state.outlineColor,
                    null);
        }
    }
}

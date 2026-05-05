package fuzs.mutantmonsters.common.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.mutantmonsters.common.client.renderer.entity.state.ThrowableBlockRenderState;
import fuzs.mutantmonsters.common.world.entity.projectile.ThrowableBlock;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class ThrowableBlockRenderer extends EntityRenderer<ThrowableBlock, ThrowableBlockRenderState> {
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    private final BlockModelResolver blockModelResolver;

    public ThrowableBlockRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.blockModelResolver = context.getBlockModelResolver();
        this.shadowRadius = 0.6F;
    }

    @Override
    public void extractRenderState(ThrowableBlock entity, ThrowableBlockRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        this.blockModelResolver.update(state.blockModel, entity.getBlockState(), BLOCK_DISPLAY_CONTEXT);
        state.isLarge = entity.isLarge();
        state.yRot = entity.getYRot(partialTick);
    }

    @Override
    public ThrowableBlockRenderState createRenderState() {
        return new ThrowableBlockRenderState();
    }

    @Override
    public void submit(ThrowableBlockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        poseStack.pushPose();
        poseStack.translate(0.0, 0.5, 0.0);
        if (state.isLarge) {
            poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot));
        } else {
            poseStack.scale(-0.75F, -0.75F, 0.75F);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(state.ageInTicks * 20.0F));
        poseStack.mulPose(Axis.ZN.rotationDegrees(state.ageInTicks * 12.0F));
        poseStack.translate(-0.5F, -0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        state.blockModel.submit(poseStack,
                submitNodeCollector,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                state.outlineColor);
        poseStack.popPose();
    }
}

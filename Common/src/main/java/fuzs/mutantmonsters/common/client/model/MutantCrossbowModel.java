package fuzs.mutantmonsters.common.client.model;

import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantSkeletonRenderState;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantSkeleton;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;

public class MutantCrossbowModel extends Model<MutantSkeletonRenderState> {
    private static final String ARM_WEAR = "arm_wear";
    private static final String MIDDLE = "middle";
    private static final String MIDDLE_1 = "middle1";
    private static final String MIDDLE_2 = "middle2";
    private static final String SIDE_1 = "side1";
    private static final String SIDE_2 = "side2";
    private static final String SIDE_3 = "side3";
    private static final String SIDE_4 = "side4";
    private static final String ROPE_1 = "rope1";
    private static final String ROPE_2 = "rope2";

    private final ModelPart middle1;
    private final ModelPart middle2;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart side3;
    private final ModelPart side4;
    private final ModelPart rope1;
    private final ModelPart rope2;

    public MutantCrossbowModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        ModelPart armWear = root.getChild(ARM_WEAR);
        ModelPart middle = armWear.getChild(MIDDLE);
        this.middle1 = middle.getChild(MIDDLE_1);
        this.middle2 = middle.getChild(MIDDLE_2);
        this.side1 = this.middle1.getChild(SIDE_1);
        this.side2 = this.middle2.getChild(SIDE_2);
        this.side3 = this.side1.getChild(SIDE_3);
        this.side4 = this.side2.getChild(SIDE_4);
        this.rope1 = this.side3.getChild(ROPE_1);
        this.rope2 = this.side4.getChild(ROPE_2);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition armWear = root.addOrReplaceChild(ARM_WEAR,
                CubeListBuilder.create()
                        .texOffs(0, 64)
                        .addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.3F)),
                PartPose.offset(0.0F, 8.0F, 0.0F));
        PartDefinition middle = armWear.addOrReplaceChild(MIDDLE,
                CubeListBuilder.create().texOffs(16, 64).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F),
                PartPose.offset(-3.5F, 0.0F, 0.0F));
        PartDefinition middle1 = middle.addOrReplaceChild(MIDDLE_1,
                CubeListBuilder.create().texOffs(36, 64).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, 0.6F, -4.0F, Mth.PI / 8.0F, 0.0F, 0.0F));
        PartDefinition middle2 = middle.addOrReplaceChild(MIDDLE_2,
                CubeListBuilder.create().texOffs(36, 64).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, 0.6F, 4.0F, -Mth.PI / 8.0F, 0.0F, 0.0F));
        PartDefinition side1 = middle1.addOrReplaceChild(SIDE_1,
                CubeListBuilder.create().texOffs(0, 74).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -Mth.PI / 5.0F, 0.0F, 0.0F));
        PartDefinition side2 = middle2.addOrReplaceChild(SIDE_2,
                CubeListBuilder.create().texOffs(0, 74).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, Mth.PI / 5.0F, 0.0F, 0.0F));
        PartDefinition side3 = side1.addOrReplaceChild(SIDE_3,
                CubeListBuilder.create().texOffs(20, 74).addBox(-0.5F, -0.5F, -8.0F, 1.0F, 1.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, -Mth.PI / 4.0F, 0.0F, 0.0F));
        PartDefinition side4 = side2.addOrReplaceChild(SIDE_4,
                CubeListBuilder.create().texOffs(20, 74).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, Mth.PI / 4.0F, 0.0F, 0.0F));
        side3.addOrReplaceChild(ROPE_1,
                CubeListBuilder.create()
                        .texOffs(0, 84)
                        .addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(-0.4F)),
                PartPose.offset(0.0F, 0.0F, -6.0F));
        side4.addOrReplaceChild(ROPE_2,
                CubeListBuilder.create()
                        .texOffs(0, 84)
                        .addBox(-0.5F, -0.5F, -15.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(-0.4F)),
                PartPose.offset(0.0F, 0.0F, 6.0F));
        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(MutantSkeletonRenderState state) {
        super.setupAnim(state);
        this.animate(state);
    }

    private void animate(MutantSkeletonRenderState state) {
        if (state.animation == MutantSkeleton.SHOOT_ANIMATION) {
            this.animShoot(state);
        } else if (state.animation == MutantSkeleton.MULTI_SHOT_ANIMATION) {
            this.animMultiShoot(state);
        } else {
            this.animRope();
        }
    }

    private void animRope() {
        this.rope1.xRot = -(this.middle1.xRot + this.side1.xRot + this.side3.xRot);
        this.rope2.xRot = -(this.middle2.xRot + this.side2.xRot + this.side4.xRot);
    }

    private void animShoot(MutantSkeletonRenderState state) {
        if (state.animationTime < 5.0F) {
            this.animRope();
        } else if (state.animationTime < 12.0F) {
            float time = (state.animationTime - 5.0F) / 7.0F;
            this.animShoot(Mth.sin(time * Mth.PI / 2.0F * 0.4F));
        } else if (state.animationTime < 26.0F) {
            float time = Mth.clamp(state.animationTime - 25.0F, 0.0F, 1.0F);
            this.animShoot(Mth.cos(time * Mth.PI / 2.0F));
        } else if (state.animationTime < 30.0F) {
            this.animRope();
        }
    }

    private void animMultiShoot(MutantSkeletonRenderState state) {
        if (state.animationTime < 17.0F) {
            this.animRope();
        } else if (state.animationTime < 20.0F) {
            float time = (state.animationTime - 17.0F) / 3.0F;
            this.animShoot(Mth.sin(time * Mth.PI / 2.0F * 0.4F));
        } else if (state.animationTime < 24.0F) {
            float time = Mth.clamp(state.animationTime - 25.0F, 0.0F, 1.0F);
            this.animShoot(Mth.cos(time * Mth.PI / 2.0F));
        } else if (state.animationTime < 28.0F) {
            this.animRope();
        }
    }

    private void animShoot(float amount) {
        this.middle1.xRot += -amount * Mth.PI / 16.0F;
        this.side1.xRot += -amount * Mth.PI / 24.0F;
        this.middle2.xRot += amount * Mth.PI / 16.0F;
        this.side2.xRot += amount * Mth.PI / 24.0F;
        this.animRope();
        this.rope1.xRot += amount * Mth.PI / 6.0F;
        this.rope2.xRot += -amount * Mth.PI / 6.0F;
    }
}

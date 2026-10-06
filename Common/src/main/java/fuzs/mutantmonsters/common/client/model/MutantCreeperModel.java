package fuzs.mutantmonsters.common.client.model;

import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantCreeperRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MutantCreeperModel extends EntityModel<MutantCreeperRenderState> {
    private static final String PELVIS = "pelvis";
    private static final String RIGHT_FRONT_LEG_LOWER = "right_front_leg_lower";
    private static final String LEFT_FRONT_LEG_LOWER = "left_front_leg_lower";
    private static final String RIGHT_HIND_LEG_LOWER = "right_hind_leg_lower";
    private static final String LEFT_HIND_LEG_LOWER = "left_hind_leg_lower";

    private final ModelPart pelvis;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;

    public MutantCreeperModel(ModelPart root) {
        super(root);
        this.pelvis = root.getChild(PELVIS);
        this.body = this.pelvis.getChild(PartNames.BODY);
        this.neck = this.body.getChild(PartNames.NECK);
        this.head = this.neck.getChild(PartNames.HEAD);
        this.rightFrontLeg = this.pelvis.getChild(PartNames.RIGHT_FRONT_LEG);
        this.leftFrontLeg = this.pelvis.getChild(PartNames.LEFT_FRONT_LEG);
        this.rightHindLeg = this.pelvis.getChild(PartNames.RIGHT_HIND_LEG);
        this.leftHindLeg = this.pelvis.getChild(PartNames.LEFT_HIND_LEG);
    }

    public static LayerDefinition createBodyLayer(CubeDeformation g) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition pelvis = root.addOrReplaceChild(PELVIS,
                CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -14.0F, -4.0F, 10.0F, 14.0F, 8.0F, g),
                PartPose.offsetAndRotation(0.0F, 14.0F, -3.0F, -Mth.PI / 4.0F, 0.0F, 0.0F));

        PartDefinition body = pelvis.addOrReplaceChild(PartNames.BODY,
                CubeListBuilder.create().texOffs(36, 0).addBox(-4.5F, -14.0F, -3.5F, 9.0F, 16.0F, 7.0F, g),
                PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, Mth.PI * 3.0F / 10.0F, 0.0F, 0.0F));

        PartDefinition neck = body.addOrReplaceChild(PartNames.NECK,
                CubeListBuilder.create().texOffs(68, 0).addBox(-4.0F, -14.0F, -3.0F, 8.0F, 14.0F, 6.0F, g),
                PartPose.offsetAndRotation(0.0F, -11.0F, 1.0F, Mth.PI / 3.0F, 0.0F, 0.0F));

        neck.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create().texOffs(0, 22).addBox(-5.0F, -12.0F, -5.0F, 10.0F, 12.0F, 10.0F, g),
                PartPose.offsetAndRotation(0.0F, -12.0F, 1.0F, Mth.PI / 6.0F, 0.0F, 0.0F));

        PartDefinition rightFrontLeg = pelvis.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG,
                CubeListBuilder.create().texOffs(40, 24).mirror().addBox(-3.0F, -4.0F, -14.0F, 6.0F, 4.0F, 14.0F, g),
                PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, Mth.PI / 10.0F, Mth.PI / 4.0F, 0.0F));

        rightFrontLeg.addOrReplaceChild(RIGHT_FRONT_LEG_LOWER,
                CubeListBuilder.create().texOffs(96, 0).mirror().addBox(-3.5F, 0.0F, -4.0F, 7.0F, 20.0F, 8.0F, g),
                PartPose.offsetAndRotation(0.0F, -4.0F, -14.0F, -Mth.PI / 15.0F, -Mth.PI / 8.0F, 0.0F));

        PartDefinition leftFrontLeg = pelvis.addOrReplaceChild(PartNames.LEFT_FRONT_LEG,
                CubeListBuilder.create().texOffs(40, 24).addBox(-3.0F, -4.0F, -14.0F, 6.0F, 4.0F, 14.0F, g),
                PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, Mth.PI / 10.0F, -Mth.PI / 4.0F, 0.0F));

        leftFrontLeg.addOrReplaceChild(LEFT_FRONT_LEG_LOWER,
                CubeListBuilder.create().texOffs(96, 0).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 20.0F, 8.0F, g),
                PartPose.offsetAndRotation(0.0F, -4.0F, -14.0F, -Mth.PI / 15.0F, Mth.PI / 8.0F, 0.0F));

        PartDefinition rightHindLeg = pelvis.addOrReplaceChild(PartNames.RIGHT_HIND_LEG,
                CubeListBuilder.create().texOffs(0, 44).mirror().addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 14.0F, g),
                PartPose.offsetAndRotation(-2.0F, -2.0F, 4.0F, Mth.PI / 3.0F, -Mth.PI / 5.0F, 0.0F));

        rightHindLeg.addOrReplaceChild(RIGHT_HIND_LEG_LOWER,
                CubeListBuilder.create().texOffs(80, 28).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, g),
                PartPose.offsetAndRotation(0.0F, -4.0F, 14.0F, 2.0F * Mth.PI / 13.0F, 0.0F, 0.0F));

        PartDefinition leftHindLeg = pelvis.addOrReplaceChild(PartNames.LEFT_HIND_LEG,
                CubeListBuilder.create().texOffs(0, 44).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 14.0F, g),
                PartPose.offsetAndRotation(2.0F, -2.0F, 4.0F, Mth.PI / 3.0F, Mth.PI / 5.0F, 0.0F));

        leftHindLeg.addOrReplaceChild(LEFT_HIND_LEG_LOWER,
                CubeListBuilder.create().texOffs(80, 28).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, g),
                PartPose.offsetAndRotation(0.0F, -4.0F, 14.0F, 2.0F * Mth.PI / 13.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(MutantCreeperRenderState state) {
        super.setupAnim(state);
        float animationPos = state.walkAnimationPos;
        float animationSpeed = state.walkAnimationSpeed;
        float breatheAnim = Mth.sin(state.ageInTicks * 0.1F);
        float leftFrontLegWalk = (Mth.sin(animationPos * Mth.PI / 4.0F) + 0.4F) * animationSpeed;
        float rightFrontLegWalk = (Mth.sin(animationPos * Mth.PI / 4.0F + Mth.PI) + 0.4F) * animationSpeed;
        leftFrontLegWalk = Math.max(0.0F, leftFrontLegWalk);
        rightFrontLegWalk = Math.max(0.0F, rightFrontLegWalk);
        float frontLegSway = Mth.sin(animationPos * Mth.PI / 8.0F) * animationSpeed;
        float rightHindLegWalk = (Mth.sin(animationPos * Mth.PI / 4.0F + Mth.HALF_PI) + 0.4F) * animationSpeed;
        float leftHindLegWalk = (Mth.sin(animationPos * Mth.PI / 4.0F + Mth.PI * 1.5F) + 0.4F) * animationSpeed;
        rightHindLegWalk = Math.max(0.0F, rightHindLegWalk);
        leftHindLegWalk = Math.max(0.0F, leftHindLegWalk);
        float hindLegSway = Mth.sin(animationPos * Mth.PI / 8.0F + Mth.HALF_PI) * animationSpeed;
        float faceYaw = state.yRot / Mth.RAD_TO_DEG;
        float facePitch = state.xRot / Mth.RAD_TO_DEG;
        this.pelvis.y += Mth.sin(animationPos * Mth.PI / 4.0F) * animationSpeed * 0.5F;
        this.body.xRot += breatheAnim * 0.02F;
        this.body.xRot += facePitch / 3.0F;
        this.body.yRot += faceYaw / 3.0F;
        this.neck.xRot += breatheAnim * 0.02F;
        this.neck.xRot += facePitch / 3.0F;
        this.neck.yRot = faceYaw / 3.0F;
        this.head.xRot += breatheAnim * 0.02F;
        this.head.xRot += facePitch / 3.0F;
        this.head.yRot = faceYaw / 3.0F;
        this.leftFrontLeg.xRot -= leftFrontLegWalk * 0.3F;
        this.leftFrontLeg.yRot += frontLegSway * 0.2F;
        this.leftFrontLeg.zRot += frontLegSway * 0.2F;
        this.rightFrontLeg.xRot -= rightFrontLegWalk * 0.3F;
        this.rightFrontLeg.yRot -= frontLegSway * 0.2F;
        this.rightFrontLeg.zRot -= frontLegSway * 0.2F;
        this.leftHindLeg.xRot += leftHindLegWalk * 0.3F;
        this.leftHindLeg.yRot -= hindLegSway * 0.2F;
        this.leftHindLeg.zRot -= hindLegSway * 0.2F;
        this.rightHindLeg.xRot += rightHindLegWalk * 0.3F;
        this.rightHindLeg.yRot += hindLegSway * 0.2F;
        this.rightHindLeg.zRot += hindLegSway * 0.2F;
        if (state.attackTime > 0.0F) {
            float swingAnim = Mth.sin(state.attackTime * Mth.PI);
            this.body.xRot += swingAnim * Mth.PI / 3.0F;
            this.neck.xRot -= swingAnim * Mth.PI / 4.0F;
        }
    }
}

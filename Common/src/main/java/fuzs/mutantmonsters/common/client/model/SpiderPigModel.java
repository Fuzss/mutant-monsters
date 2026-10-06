package fuzs.mutantmonsters.common.client.model;

import fuzs.mutantmonsters.common.client.renderer.entity.state.SpiderPigRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SpiderPigModel extends EntityModel<SpiderPigRenderState> {
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);
    private static final String BASE = "base";
    private static final String INNER_HEAD = "inner_head";
    private static final String REAR_BODY = "rear_body";
    private static final String RIGHT_FRONT_LEG_INNER = "right_front_leg_inner";
    private static final String RIGHT_FRONT_LEG_LOWER = "right_front_leg_lower";
    private static final String LEFT_FRONT_LEG_INNER = "left_front_leg_inner";
    private static final String LEFT_FRONT_LEG_LOWER = "left_front_leg_lower";
    private static final String RIGHT_MID_LEG_INNER = "right_mid_leg_inner";
    private static final String RIGHT_MID_LEG_LOWER = "right_mid_leg_lower";
    private static final String LEFT_MID_LEG_INNER = "left_mid_leg_inner";
    private static final String LEFT_MID_LEG_LOWER = "left_mid_leg_lower";
    private static final String RIGHT_HIND_LEG_LOWER = "right_hind_leg_lower";
    private static final String LEFT_HIND_LEG_LOWER = "left_hind_leg_lower";

    private final ModelPart head;
    private final ModelPart innerHead;
    private final ModelPart body;
    private final ModelPart upperBody;
    private final ModelPart rearBody;
    private final ModelPart rightFrontLegInner;
    private final ModelPart rightFrontLegLower;
    private final ModelPart leftFrontLegInner;
    private final ModelPart leftFrontLegLower;
    private final ModelPart rightMidLegInner;
    private final ModelPart rightMidLegLower;
    private final ModelPart leftMidLegInner;
    private final ModelPart leftMidLegLower;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;

    public SpiderPigModel(ModelPart root) {
        super(root);
        ModelPart base = root.getChild(BASE);
        this.body = base.getChild(PartNames.BODY);
        this.upperBody = this.body.getChild(PartNames.UPPER_BODY);
        this.rearBody = this.body.getChild(REAR_BODY);
        this.head = this.upperBody.getChild(PartNames.HEAD);
        this.innerHead = this.head.getChild(INNER_HEAD);
        ModelPart rightFrontLeg = this.upperBody.getChild(PartNames.RIGHT_FRONT_LEG);
        this.rightFrontLegInner = rightFrontLeg.getChild(RIGHT_FRONT_LEG_INNER);
        this.rightFrontLegLower = this.rightFrontLegInner.getChild(RIGHT_FRONT_LEG_LOWER);
        ModelPart leftFrontLeg = this.upperBody.getChild(PartNames.LEFT_FRONT_LEG);
        this.leftFrontLegInner = leftFrontLeg.getChild(LEFT_FRONT_LEG_INNER);
        this.leftFrontLegLower = this.leftFrontLegInner.getChild(LEFT_FRONT_LEG_LOWER);
        ModelPart rightMidLeg = this.upperBody.getChild(PartNames.RIGHT_MID_LEG);
        this.rightMidLegInner = rightMidLeg.getChild(RIGHT_MID_LEG_INNER);
        this.rightMidLegLower = this.rightMidLegInner.getChild(RIGHT_MID_LEG_LOWER);
        ModelPart leftMidLeg = this.upperBody.getChild(PartNames.LEFT_MID_LEG);
        this.leftMidLegInner = leftMidLeg.getChild(LEFT_MID_LEG_INNER);
        this.leftMidLegLower = this.leftMidLegInner.getChild(LEFT_MID_LEG_LOWER);
        this.rightHindLeg = this.body.getChild(PartNames.RIGHT_HIND_LEG);
        this.leftHindLeg = this.body.getChild(PartNames.LEFT_HIND_LEG);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition base = root.addOrReplaceChild(BASE,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offset(0.0F, 14.5F, -2.0F));
        PartDefinition body = base.addOrReplaceChild(PartNames.BODY,
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 10.0F)
                        .texOffs(44, 16)
                        .addBox(-5.0F, -5.0F, -4.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(-0.6F)),
                PartPose.rotation(-Mth.PI / 60.0F, 0.0F, 0.0F));
        PartDefinition upperBody = body.addOrReplaceChild(PartNames.UPPER_BODY,
                CubeListBuilder.create().texOffs(64, 0),
                PartPose.offsetAndRotation(0.0F, -1.0F, 1.5F, Mth.PI / 8.0F, 0.0F, 0.0F));
        upperBody.addOrReplaceChild(PartNames.INNER_BODY,
                CubeListBuilder.create().texOffs(64, 0).addBox(-3.5F, -3.5F, -9.0F, 7.0F, 7.0F, 9.0F),
                PartPose.ZERO);
        body.addOrReplaceChild(REAR_BODY,
                CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -4.5F, 0.0F, 10.0F, 9.0F, 12.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, Mth.TWO_PI / 11.0F, 0.0F, 0.0F));
        PartDefinition head = upperBody.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offsetAndRotation(0.0F, 0.0F, -8.0F, -Mth.PI / 8.0F, 0.0F, 0.0F));
        PartDefinition innerHead = head.addOrReplaceChild(INNER_HEAD,
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F),
                PartPose.ZERO);
        innerHead.addOrReplaceChild(PartNames.NOSE,
                CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F),
                PartPose.ZERO);
        PartDefinition rightFrontLeg = upperBody.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG,
                CubeListBuilder.create().texOffs(0, 37),
                PartPose.offsetAndRotation(-3.5F, 0.0F, -5.0F, -0.34033922F, -Mth.PI / 3.0F, 0.0F));
        PartDefinition rightFrontLegInner = rightFrontLeg.addOrReplaceChild(RIGHT_FRONT_LEG_INNER,
                CubeListBuilder.create().texOffs(0, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.rotation(0.0F, 0.0F, Mth.TWO_PI / 3.0F));
        rightFrontLegInner.addOrReplaceChild(RIGHT_FRONT_LEG_LOWER,
                CubeListBuilder.create().texOffs(8, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F),
                PartPose.offsetAndRotation(-0.0F, 12.0F, -0.1F, 0.0F, 0.0F, -1.6534699F));
        PartDefinition leftFrontLeg = upperBody.addOrReplaceChild(PartNames.LEFT_FRONT_LEG,
                CubeListBuilder.create().texOffs(0, 37).mirror(),
                PartPose.offsetAndRotation(3.5F, 0.0F, -5.0F, -0.34033922F, Mth.PI / 3.0F, 0.0F));
        PartDefinition leftFrontLegInner = leftFrontLeg.addOrReplaceChild(LEFT_FRONT_LEG_INNER,
                CubeListBuilder.create().texOffs(0, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.rotation(0.0F, 0.0F, -Mth.TWO_PI / 3.0F));
        leftFrontLegInner.addOrReplaceChild(LEFT_FRONT_LEG_LOWER,
                CubeListBuilder.create().texOffs(8, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 12.0F, 0.1F, 0.0F, 0.0F, 1.6534699F));
        PartDefinition rightMidLeg = upperBody.addOrReplaceChild(PartNames.RIGHT_MID_LEG,
                CubeListBuilder.create().texOffs(0, 37),
                PartPose.offsetAndRotation(-3.5F, 0.0F, -3.0F, -0.34033922F, -Mth.PI / 10.0F, 0.0F));
        PartDefinition rightMidLegInner = rightMidLeg.addOrReplaceChild(RIGHT_MID_LEG_INNER,
                CubeListBuilder.create().texOffs(0, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.rotation(0.0F, 0.0F, 2.0399954F));
        rightMidLegInner.addOrReplaceChild(RIGHT_MID_LEG_LOWER,
                CubeListBuilder.create().texOffs(8, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 12.0F, -0.1F, 0.0F, 0.0F, -1.6534699F));
        PartDefinition leftMidLeg = upperBody.addOrReplaceChild(PartNames.LEFT_MID_LEG,
                CubeListBuilder.create().texOffs(0, 37).mirror(),
                PartPose.offsetAndRotation(3.5F, 0.0F, -3.0F, -0.34033922F, Mth.PI / 10.0F, 0.0F));
        PartDefinition leftMidLegInner = leftMidLeg.addOrReplaceChild(LEFT_MID_LEG_INNER,
                CubeListBuilder.create().texOffs(0, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.rotation(0.0F, 0.0F, -2.0399954F));
        leftMidLegInner.addOrReplaceChild(LEFT_MID_LEG_LOWER,
                CubeListBuilder.create().texOffs(8, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 12.0F, 0.1F, 0.0F, 0.0F, 1.6534699F));

        PartDefinition rightHindLeg = body.addOrReplaceChild(PartNames.RIGHT_HIND_LEG,
                CubeListBuilder.create()
                        .texOffs(16, 37)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-2.5F, 2.0F, 7.0F, -0.3654898F, 0.1469752F, 0.3654898F));

        rightHindLeg.addOrReplaceChild(RIGHT_HIND_LEG_LOWER,
                CubeListBuilder.create()
                        .texOffs(16, 45)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, Mth.TWO_PI / 11.0F, 0.0F, -Mth.PI / 8.0F));

        PartDefinition leftHindLeg = body.addOrReplaceChild(PartNames.LEFT_HIND_LEG,
                CubeListBuilder.create()
                        .texOffs(32, 37)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(2.5F, 2.0F, 7.0F, -0.3654898F, -0.1469752F, -0.3654898F));

        leftHindLeg.addOrReplaceChild(LEFT_HIND_LEG_LOWER,
                CubeListBuilder.create()
                        .texOffs(16, 45)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, Mth.TWO_PI / 11.0F, 0.0F, Mth.PI / 8.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(SpiderPigRenderState state) {
        super.setupAnim(state);
        float animationPos = state.walkAnimationPos;
        float animationSpeed = state.walkAnimationSpeed;
        float walkAnim = Mth.sin(animationPos * 0.9F) * animationSpeed;
        float rightFrontLegSwing = Mth.sin(animationPos * 0.9F + 0.3F) * animationSpeed;
        float rightFrontLegStep = Mth.sin(animationPos * 0.9F + 0.3F + 0.5F) * animationSpeed;
        float leftFrontLegSwing = Mth.sin(animationPos * 0.9F + 0.9F) * animationSpeed;
        float leftFrontLegStep = Mth.sin(animationPos * 0.9F + 0.9F + 0.5F) * animationSpeed;
        float rightMidLegSwing = Mth.sin(animationPos * 0.9F - 0.3F) * animationSpeed;
        float rightMidLegStep = Mth.sin(animationPos * 0.9F - 0.3F + 0.5F) * animationSpeed;
        float leftMidLegSwing = Mth.sin(animationPos * 0.9F - 0.9F) * animationSpeed;
        float leftMidLegStep = Mth.sin(animationPos * 0.9F - 0.9F + 0.5F) * animationSpeed;
        float breatheAnim = Mth.sin(state.ageInTicks * 0.2F);
        this.head.xRot += breatheAnim * 0.02F;
        this.upperBody.xRot += breatheAnim * 0.005F;
        this.rearBody.xRot += -breatheAnim * 0.015F;
        this.innerHead.xRot += state.xRot * Mth.DEG_TO_RAD;
        this.innerHead.yRot += state.yRot * Mth.DEG_TO_RAD;
        this.rightFrontLegInner.zRot += -rightFrontLegSwing * Mth.PI / 6.0F;
        this.rightFrontLegInner.xRot += -Mth.PI / 8.0F * animationSpeed;
        this.rightFrontLegLower.zRot += rightFrontLegStep * Mth.PI / 6.0F + Mth.PI / 12.0F * animationSpeed;
        this.leftFrontLegInner.zRot += leftFrontLegSwing * Mth.PI / 6.0F;
        this.leftFrontLegInner.xRot += -Mth.PI / 8.0F * animationSpeed;
        this.leftFrontLegLower.zRot += -(leftFrontLegStep * Mth.PI / 6.0F + Mth.PI / 12.0F * animationSpeed);
        this.rightMidLegInner.zRot += -rightMidLegSwing * Mth.PI / 6.0F;
        this.rightMidLegInner.xRot += -Mth.TWO_PI / 7.0F * animationSpeed;
        this.rightMidLegLower.zRot += rightMidLegStep * Mth.PI / 6.0F + Mth.PI / 8.0F * animationSpeed;
        this.leftMidLegInner.zRot += leftMidLegSwing * Mth.PI / 6.0F;
        this.leftMidLegInner.xRot += -Mth.TWO_PI / 7.0F * animationSpeed;
        this.leftMidLegLower.zRot += -(leftMidLegStep * Mth.PI / 6.0F + Mth.PI / 8.0F * animationSpeed);
        this.rightHindLeg.xRot += -leftMidLegSwing * Mth.PI / 5.0F + Mth.PI / 12.0F * animationSpeed;
        this.leftHindLeg.xRot += -rightFrontLegSwing * Mth.PI / 5.0F + Mth.PI / 12.0F * animationSpeed;
        this.body.xRot += -walkAnim * Mth.PI / 20.0F;
        this.head.xRot += walkAnim * Mth.PI / 20.0F;
        if (state.attackTime > 0.0F) {
            this.animateAttack(Mth.sin(state.attackTime * Mth.PI));
        }
    }

    private void animateAttack(float swingAmount) {
        this.upperBody.xRot -= swingAmount * Mth.PI / 2.5F;
        this.rightFrontLegInner.zRot += swingAmount * Mth.PI / 5.0F;
        this.leftFrontLegInner.zRot -= swingAmount * Mth.PI / 5.0F;
        this.rightMidLegInner.yRot -= swingAmount * Mth.PI / 2.5F;
        this.leftMidLegInner.yRot += swingAmount * Mth.PI / 2.5F;
        this.head.xRot += swingAmount * Mth.PI / 3.0F;
        this.rearBody.xRot += -swingAmount;
    }
}

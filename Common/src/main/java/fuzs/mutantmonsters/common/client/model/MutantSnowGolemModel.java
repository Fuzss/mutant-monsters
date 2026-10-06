package fuzs.mutantmonsters.common.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantSnowGolemRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import java.util.Set;

public class MutantSnowGolemModel extends EntityModel<MutantSnowGolemRenderState> {
    private static final String PELVIS = "pelvis";
    private static final String ABDOMEN = "abdomen";
    private static final String CHEST = "chest";
    private static final String INNER_HEAD = "inner_head";
    private static final String HEAD_CORE = "head_core";
    private static final String RIGHT_ARM_INNER = "right_arm_inner";
    private static final String LEFT_ARM_INNER = "left_arm_inner";
    private static final String RIGHT_ARM_LOWER = "right_arm_lower";
    private static final String LEFT_ARM_LOWER = "left_arm_lower";
    private static final String RIGHT_ARM_LOWER_INNER = "right_arm_lower_inner";
    private static final String LEFT_ARM_LOWER_INNER = "left_arm_lower_inner";
    private static final String RIGHT_LEG_INNER = "right_leg_inner";
    private static final String LEFT_LEG_INNER = "left_leg_inner";
    private static final String RIGHT_LEG_LOWER = "right_leg_lower";
    private static final String LEFT_LEG_LOWER = "left_leg_lower";
    private static final String RIGHT_LEG_LOWER_INNER = "right_leg_lower_inner";
    private static final String LEFT_LEG_LOWER_INNER = "left_leg_lower_inner";

    private final ModelPart pelvis;
    private final ModelPart abdomen;
    private final ModelPart chest;
    private final ModelPart head;
    private final ModelPart innerHead;
    private final ModelPart rightArm;
    private final ModelPart rightArmInner;
    private final ModelPart leftArm;
    private final ModelPart leftArmInner;
    private final ModelPart rightArmLower;
    private final ModelPart rightArmLowerInner;
    private final ModelPart leftArmLower;
    private final ModelPart leftArmLowerInner;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightLegLowerInner;
    private final ModelPart leftLegLowerInner;

    public MutantSnowGolemModel(ModelPart root) {
        super(root);
        this.pelvis = root.getChild(PELVIS);
        this.abdomen = this.pelvis.getChild(ABDOMEN);
        this.chest = this.abdomen.getChild(CHEST);
        this.head = this.chest.getChild(PartNames.HEAD);
        this.innerHead = this.head.getChild(INNER_HEAD);
        this.rightArm = this.chest.getChild(PartNames.RIGHT_ARM);
        this.rightArmInner = this.rightArm.getChild(RIGHT_ARM_INNER);
        this.leftArm = this.chest.getChild(PartNames.LEFT_ARM);
        this.leftArmInner = this.leftArm.getChild(LEFT_ARM_INNER);
        this.rightArmLower = this.rightArmInner.getChild(RIGHT_ARM_LOWER);
        this.rightArmLowerInner = this.rightArmLower.getChild(RIGHT_ARM_LOWER_INNER);
        this.leftArmLower = this.leftArmInner.getChild(LEFT_ARM_LOWER);
        this.leftArmLowerInner = this.leftArmLower.getChild(LEFT_ARM_LOWER_INNER);
        this.rightLeg = this.pelvis.getChild(PartNames.RIGHT_LEG);
        ModelPart rightLegInner = this.rightLeg.getChild(RIGHT_LEG_INNER);
        this.leftLeg = this.pelvis.getChild(PartNames.LEFT_LEG);
        ModelPart leftLegInner = this.leftLeg.getChild(LEFT_LEG_INNER);
        ModelPart rightLegLower = rightLegInner.getChild(RIGHT_LEG_LOWER);
        this.rightLegLowerInner = rightLegLower.getChild(RIGHT_LEG_LOWER_INNER);
        ModelPart leftLegLower = leftLegInner.getChild(LEFT_LEG_LOWER);
        this.leftLegLowerInner = leftLegLower.getChild(LEFT_LEG_LOWER_INNER);
    }

    public static LayerDefinition createHeadLayer() {
        return LayerDefinition.create(createBodyMesh(), 64, 32).apply((MeshDefinition meshDefinition) -> {
            meshDefinition.getRoot().retainPartsAndChildren(Set.of(PartNames.HEAD));
            return meshDefinition;
        });
    }

    public static LayerDefinition createBodyLayer() {
        return LayerDefinition.create(createBodyMesh(), 128, 64);
    }

    private static MeshDefinition createBodyMesh() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition root = meshDefinition.getRoot();

        PartDefinition pelvis = root.addOrReplaceChild(PELVIS,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offset(0.0F, 13.5F, 5.0F));

        PartDefinition abdomen = pelvis.addOrReplaceChild(ABDOMEN,
                CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F),
                PartPose.rotation(Mth.PI / 24.0F, 0.0F, 0.0F));

        PartDefinition chest = abdomen.addOrReplaceChild(CHEST,
                CubeListBuilder.create().texOffs(24, 36).addBox(-8.0F, -12.0F, -6.0F, 16.0F, 12.0F, 12.0F),
                PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, Mth.PI / 24.0F, 0.0F, 0.0F));

        PartDefinition head = chest.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offsetAndRotation(0.0F, -12.0F, -2.0F, -Mth.PI / 12.0F, 0.0F, 0.0F));

        PartDefinition innerHead = head.addOrReplaceChild(INNER_HEAD,
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO);

        innerHead.addOrReplaceChild(HEAD_CORE,
                CubeListBuilder.create()
                        .texOffs(64, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                        .texOffs(80, 46)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)),
                PartPose.ZERO);

        PartDefinition rightArm = chest.addOrReplaceChild(PartNames.RIGHT_ARM,
                CubeListBuilder.create().texOffs(68, 16),
                PartPose.offsetAndRotation(-9.0F, -11.0F, 0.0F, -Mth.PI / 10.0F, 0.0F, 0.0F));

        PartDefinition rightArmInner = rightArm.addOrReplaceChild(RIGHT_ARM_INNER,
                CubeListBuilder.create().texOffs(68, 16).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F),
                PartPose.rotation(0.0F, Mth.PI / 6.0F, Mth.PI / 6.0F));

        PartDefinition rightArmLower = rightArmInner.addOrReplaceChild(RIGHT_ARM_LOWER,
                CubeListBuilder.create().texOffs(96, 0),
                PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, -Mth.PI / 6.0F, -Mth.PI / 12.0F));

        rightArmLower.addOrReplaceChild(RIGHT_ARM_LOWER_INNER,
                CubeListBuilder.create().texOffs(96, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F),
                PartPose.rotation(-Mth.PI / 6.0F, 0.0F, 0.0F));

        PartDefinition leftArm = chest.addOrReplaceChild(PartNames.LEFT_ARM,
                CubeListBuilder.create().texOffs(68, 16).mirror(),
                PartPose.offsetAndRotation(9.0F, -11.0F, 0.0F, -Mth.PI / 10.0F, 0.0F, 0.0F));

        PartDefinition leftArmInner = leftArm.addOrReplaceChild(LEFT_ARM_INNER,
                CubeListBuilder.create().texOffs(68, 16).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F),
                PartPose.rotation(0.0F, -Mth.PI / 6.0F, -Mth.PI / 6.0F));

        PartDefinition leftArmLower = leftArmInner.addOrReplaceChild(LEFT_ARM_LOWER,
                CubeListBuilder.create().texOffs(96, 0).mirror(),
                PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, Mth.PI / 6.0F, Mth.PI / 12.0F));

        leftArmLower.addOrReplaceChild(LEFT_ARM_LOWER_INNER,
                CubeListBuilder.create().texOffs(96, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F),
                PartPose.rotation(-Mth.PI / 6.0F, 0.0F, 0.0F));

        PartDefinition rightLeg = pelvis.addOrReplaceChild(PartNames.RIGHT_LEG,
                CubeListBuilder.create().texOffs(88, 18),
                PartPose.offsetAndRotation(-4.0F, -1.0F, -3.0F, -Mth.PI / 5.0F, 0.0F, 0.0F));

        PartDefinition rightLegInner = rightLeg.addOrReplaceChild(RIGHT_LEG_INNER,
                CubeListBuilder.create().texOffs(88, 18).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F),
                PartPose.rotation(0.0F, 0.0F, Mth.PI / 6.0F));

        PartDefinition rightLegLower = rightLegInner.addOrReplaceChild(RIGHT_LEG_LOWER,
                CubeListBuilder.create().texOffs(88, 32),
                PartPose.offsetAndRotation(-1.0F, 6.0F, 0.0F, 0.0F, 0.0F, -Mth.PI / 6.0F));

        rightLegLower.addOrReplaceChild(RIGHT_LEG_LOWER_INNER,
                CubeListBuilder.create().texOffs(88, 32).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F),
                PartPose.rotation(2.0F * Mth.PI / 9.0F, 0.0F, 0.0F));

        PartDefinition leftLeg = pelvis.addOrReplaceChild(PartNames.LEFT_LEG,
                CubeListBuilder.create().texOffs(88, 18).mirror(),
                PartPose.offsetAndRotation(4.0F, -1.0F, -3.0F, -Mth.PI / 5.0F, 0.0F, 0.0F));

        PartDefinition leftLegInner = leftLeg.addOrReplaceChild(LEFT_LEG_INNER,
                CubeListBuilder.create().texOffs(88, 18).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F),
                PartPose.rotation(0.0F, 0.0F, -Mth.PI / 6.0F));

        PartDefinition leftLegLower = leftLegInner.addOrReplaceChild(LEFT_LEG_LOWER,
                CubeListBuilder.create().texOffs(88, 32).mirror(),
                PartPose.offsetAndRotation(1.0F, 6.0F, 0.0F, 0.0F, 0.0F, Mth.PI / 6.0F));

        leftLegLower.addOrReplaceChild(LEFT_LEG_LOWER_INNER,
                CubeListBuilder.create().texOffs(88, 32).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F),
                PartPose.rotation(2.0F * Mth.PI / 9.0F, 0.0F, 0.0F));

        return meshDefinition;
    }

    @Override
    public void setupAnim(MutantSnowGolemRenderState state) {
        super.setupAnim(state);
        float walkAnim = Mth.sin(state.walkAnimationPos * 0.45F) * state.walkAnimationSpeed;
        float rightLegWalk =
                (Mth.cos((state.walkAnimationPos - 0.5F) * 0.45F) + 0.5F) * state.walkAnimationSpeed;
        float leftLegWalk = (Mth.cos((state.walkAnimationPos - 0.5F + Mth.TWO_PI) * 0.45F) + 0.5F)
                * state.walkAnimationSpeed;
        float breatheAnim = Mth.sin(state.ageInTicks * 0.11F);
        float faceYaw = state.yRot * Mth.PI / 180.0F;
        float facePitch = state.xRot * Mth.PI / 180.0F;
        if (state.isThrowing()) {
            this.animateThrow(state);
            float scale = 1.0F - Mth.clamp(state.throwingTime / 4.0F, 0.0F, 1.0F);
            walkAnim *= scale;
        }

        this.innerHead.xRot -= breatheAnim * 0.01F;
        this.chest.xRot -= breatheAnim * 0.01F;
        this.rightArm.zRot += breatheAnim * 0.03F;
        this.leftArm.zRot -= breatheAnim * 0.03F;
        this.innerHead.xRot += facePitch;
        this.innerHead.yRot += faceYaw;
        this.pelvis.y += Math.abs(walkAnim) * 1.5F;
        this.abdomen.xRot += state.walkAnimationSpeed * 0.2F;
        this.chest.yRot -= walkAnim * 0.1F;
        this.head.xRot -= state.walkAnimationSpeed * 0.2F;
        this.rightArm.xRot -= walkAnim * 0.6F;
        this.leftArm.xRot += walkAnim * 0.6F;
        this.rightArmLowerInner.xRot -= walkAnim * 0.2F;
        this.leftArmLowerInner.xRot += walkAnim * 0.2F;
        this.rightLeg.xRot += rightLegWalk * 1.1F;
        this.leftLeg.xRot += leftLegWalk * 1.1F;
        this.rightLegLowerInner.xRot += walkAnim * 0.2F;
        this.leftLegLowerInner.xRot -= walkAnim * 0.2F;
    }

    private void animateThrow(MutantSnowGolemRenderState state) {
        if (state.throwingTime < 7.0F) {
            float animationProgress = state.throwingTime / 7.0F;
            float rotationAmount = Mth.sin(animationProgress * Mth.PI / 2.0F);
            this.abdomen.xRot += -rotationAmount * 0.2F;
            this.chest.xRot += -rotationAmount * 0.4F;
            this.rightArm.xRot += -rotationAmount * 1.6F;
            this.rightArm.zRot += rotationAmount * 0.8F;
            this.leftArm.xRot += -rotationAmount * 1.6F;
            this.leftArm.zRot += -rotationAmount * 0.8F;
        } else if (state.throwingTime < 10.0F) {
            float animationProgress = (state.throwingTime - 7.0F) / 3.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.PI / 2.0F);
            this.abdomen.xRot += -rotationAmount * 0.4F + 0.2F;
            this.chest.xRot += -rotationAmount * 0.6F + 0.2F;
            this.rightArm.xRot += -rotationAmount * 0.8F - 0.8F;
            this.rightArm.zRot += 0.8F;
            this.leftArm.xRot += -rotationAmount * 0.8F - 0.8F;
            this.leftArm.zRot += -0.8F;
        } else if (state.throwingTime < 14.0F) {
            this.abdomen.xRot += 0.2F;
            this.chest.xRot += 0.2F;
            this.rightArm.xRot += -0.8F;
            this.rightArm.zRot += 0.8F;
            this.leftArm.xRot += -0.8F;
            this.leftArm.zRot += -0.8F;
        } else if (state.throwingTime < 20.0F) {
            float animationProgress = (state.throwingTime - 14.0F) / 6.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.PI / 2.0F);
            this.abdomen.xRot += rotationAmount * 0.2F;
            this.chest.xRot += rotationAmount * 0.2F;
            this.rightArm.xRot += -rotationAmount * 0.8F;
            this.rightArm.zRot += rotationAmount * 0.8F;
            this.leftArm.xRot += -rotationAmount * 0.8F;
            this.leftArm.zRot += -rotationAmount * 0.8F;
        }
    }

    public void translateArm(PoseStack poseStack, boolean leftHanded) {
        this.pelvis.translateAndRotate(poseStack);
        this.abdomen.translateAndRotate(poseStack);
        this.chest.translateAndRotate(poseStack);
        if (leftHanded) {
            this.leftArm.translateAndRotate(poseStack);
            this.leftArmInner.translateAndRotate(poseStack);
            this.leftArmLower.translateAndRotate(poseStack);
            this.leftArmLowerInner.translateAndRotate(poseStack);
        } else {
            this.rightArm.translateAndRotate(poseStack);
            this.rightArmInner.translateAndRotate(poseStack);
            this.rightArmLower.translateAndRotate(poseStack);
            this.rightArmLowerInner.translateAndRotate(poseStack);
        }
    }
}

package fuzs.mutantmonsters.common.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.mutantmonsters.common.client.animation.Animator;
import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantEndermanRenderState;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantEnderman;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;

import java.util.Arrays;

public class MutantEndermanModel extends EntityModel<MutantEndermanRenderState> {
    private static final String PELVIS = "pelvis";
    private static final String ABDOMEN = "abdomen";
    private static final String CHEST = "chest";
    private static final String RIGHT_LEG_JOINT = "right_leg_joint";
    private static final String LEFT_LEG_JOINT = "left_leg_joint";
    private static final String RIGHT_LEG_LOWER = "right_leg_lower";
    private static final String LEFT_LEG_LOWER = "left_leg_lower";
    private static final String RIGHT_LOWER_ARM_PREFIX = "right_lower_";
    private static final String LEFT_LOWER_ARM_PREFIX = "left_lower_";

    private final ModelPart pelvis;
    private final ModelPart abdomen;
    private final ModelPart chest;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart mouth;
    private final Arm rightArm;
    private final Arm leftArm;
    private final Arm lowerRightArm;
    private final Arm lowerLeftArm;
    private final ModelPart rightLegJoint;
    private final ModelPart leftLegJoint;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightLegLower;
    private final ModelPart leftLegLower;

    public MutantEndermanModel(ModelPart root) {
        super(root);
        this.pelvis = root.getChild(PELVIS);
        this.abdomen = this.pelvis.getChild(ABDOMEN);
        this.chest = this.abdomen.getChild(CHEST);
        this.neck = this.chest.getChild(PartNames.NECK);
        this.head = this.neck.getChild(PartNames.HEAD);
        this.mouth = this.head.getChild(PartNames.MOUTH);
        this.rightArm = new Arm(this.chest, "right_");
        this.leftArm = new Arm(this.chest, "left_");
        this.lowerRightArm = new Arm(this.chest, RIGHT_LOWER_ARM_PREFIX);
        this.lowerLeftArm = new Arm(this.chest, LEFT_LOWER_ARM_PREFIX);
        this.rightLegJoint = this.abdomen.getChild(RIGHT_LEG_JOINT);
        this.leftLegJoint = this.abdomen.getChild(LEFT_LEG_JOINT);
        this.rightLeg = this.rightLegJoint.getChild(PartNames.RIGHT_LEG);
        this.leftLeg = this.leftLegJoint.getChild(PartNames.LEFT_LEG);
        this.rightLegLower = this.rightLeg.getChild(RIGHT_LEG_LOWER);
        this.leftLegLower = this.leftLeg.getChild(LEFT_LEG_LOWER);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition pelvis = root.addOrReplaceChild(PELVIS,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offset(0.0F, -15.5F, 8.0F));
        PartDefinition abdomen = pelvis.addOrReplaceChild(ABDOMEN,
                CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -2.0F, 8.0F, 10.0F, 4.0F),
                PartPose.rotation(Mth.PI / 10.0F, 0.0F, 0.0F));
        PartDefinition chest = abdomen.addOrReplaceChild(CHEST,
                CubeListBuilder.create().texOffs(50, 8).addBox(-5.0F, -16.0F, -3.0F, 10.0F, 16.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, Mth.PI / 8.0F, 0.0F, 0.0F));
        PartDefinition neck = chest.addOrReplaceChild(PartNames.NECK,
                CubeListBuilder.create().texOffs(32, 14).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, Mth.PI / 16.0F, 0.0F, 0.0F));
        PartDefinition head = neck.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -8.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.5F))
                        .texOffs(0, 14)
                        .addBox(-4.0F, 3.0F, -8.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offsetAndRotation(0.0F, -5.0F, 3.0F, -Mth.PI / 4.0F, 0.0F, 0.0F));
        head.addOrReplaceChild(PartNames.MOUTH,
                CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, 3.0F, -8.0F, 8.0F, 2.0F, 8.0F),
                PartPose.ZERO);
        Arm.createArmLayer(chest, "right_", true, false);
        Arm.createArmLayer(chest, "left_", false, false);
        Arm.createArmLayer(chest, RIGHT_LOWER_ARM_PREFIX, true, true);
        Arm.createArmLayer(chest, LEFT_LOWER_ARM_PREFIX, false, true);
        PartDefinition rightLegJoint = abdomen.addOrReplaceChild(RIGHT_LEG_JOINT,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offset(-1.5F, 0.0F, 0.75F));
        PartDefinition leftLegJoint = abdomen.addOrReplaceChild(LEFT_LEG_JOINT,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offset(1.5F, 0.0F, 0.75F));
        PartDefinition rightLeg = rightLegJoint.addOrReplaceChild(PartNames.RIGHT_LEG,
                CubeListBuilder.create()
                        .texOffs(0, 34)
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.5F)),
                PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -Mth.TWO_PI / 7.0F, 0.0F, Mth.PI / 12.0F));
        PartDefinition leftLeg = leftLegJoint.addOrReplaceChild(PartNames.LEFT_LEG,
                CubeListBuilder.create()
                        .texOffs(0, 34)
                        .mirror()
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.5F)),
                PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -Mth.TWO_PI / 7.0F, 0.0F, -Mth.PI / 12.0F));
        rightLeg.addOrReplaceChild(RIGHT_LEG_LOWER,
                CubeListBuilder.create()
                        .texOffs(12, 34)
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.5F)),
                PartPose.offsetAndRotation(0.0F, 23.0F, 0.0F, Mth.PI / 4.0F, 0.0F, -Mth.PI / 24.0F));
        leftLeg.addOrReplaceChild(LEFT_LEG_LOWER,
                CubeListBuilder.create()
                        .texOffs(12, 34)
                        .mirror()
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.5F)),
                PartPose.offsetAndRotation(0.0F, 23.0F, 0.0F, Mth.PI / 4.0F, 0.0F, Mth.PI / 24.0F));
        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(MutantEndermanRenderState state) {
        super.setupAnim(state);
        float animationPos = state.walkAnimationPos;
        float animationSpeed = state.walkAnimationSpeed;
        float walkSpeed = 0.3F;
        float rightLegJointWalk = (Mth.sin((animationPos - 0.8F) * walkSpeed) + 0.8F) * animationSpeed;
        float leftLegJointWalk = -(Mth.sin((animationPos + 0.8F) * walkSpeed) - 0.8F) * animationSpeed;
        float rightLegWalk = (Mth.sin((animationPos + 0.8F) * walkSpeed) - 0.8F) * animationSpeed;
        float leftLegWalk = -(Mth.sin((animationPos - 0.8F) * walkSpeed) + 0.8F) * animationSpeed;
        float[] walkAnim = new float[5];
        Arrays.fill(walkAnim, Mth.sin(animationPos * walkSpeed) * animationSpeed);
        float breatheAnim = Mth.sin(state.ageInTicks * 0.15F);
        float faceYaw = state.yRot * Mth.PI / 180.0F;
        float facePitch = state.xRot * Mth.PI / 180.0F;
        for (int i = 0; i < state.heldBlocks.length; ++i) {
            if (!state.heldBlocks[i].isEmpty()) {
                this.animateHoldBlock(state, i);
                walkAnim[i] *= 0.4F;
            }
        }

        if (state.animation == MutantEnderman.MELEE_ANIMATION) {
            this.animateMelee(state, state.activeArm);
            walkAnim[state.activeArm] = 0.0F;
        }

        if (state.animation == MutantEnderman.THROW_ANIMATION) {
            this.animateThrowBlock(state, state.activeArm);
        }

        if (state.animation == MutantEnderman.SCREAM_ANIMATION) {
            this.animateScream(state);
            float scale = 1.0F - Mth.clamp(state.animationTime / 6.0F, 0.0F, 1.0F);
            faceYaw *= scale;
            facePitch *= scale;
            rightLegJointWalk *= scale;
            leftLegJointWalk *= scale;
            rightLegWalk *= scale;
            leftLegWalk *= scale;
            Arrays.fill(walkAnim, 0.0F);
        }

        if (state.animation == MutantEnderman.TELESMASH_ANIMATION) {
            this.animateTeleSmash(state);
        }

        if (state.animation == MutantEnderman.DEATH_ANIMATION) {
            this.animateDeath(state);
            float scale = 1.0F - Mth.clamp(state.deathTime / 6.0F, 0.0F, 1.0F);
            faceYaw *= scale;
            facePitch *= scale;
            rightLegJointWalk *= scale;
            leftLegJointWalk *= scale;
            rightLegWalk *= scale;
            leftLegWalk *= scale;
            Arrays.fill(walkAnim, 0.0F);
        }

        this.head.xRot += facePitch * 0.5F;
        this.head.yRot += faceYaw * 0.7F;
        this.head.zRot -= faceYaw * 0.7F;
        this.neck.xRot += facePitch * 0.3F;
        this.chest.xRot += facePitch * 0.2F;
        this.mouth.xRot += breatheAnim * 0.02F + 0.02F;
        this.neck.xRot -= breatheAnim * 0.02F;
        this.rightArm.arm.zRot += breatheAnim * 0.004F;
        this.leftArm.arm.zRot -= breatheAnim * 0.004F;
        for (ModelPart modelPart : this.rightArm.finger) {
            modelPart.zRot += breatheAnim * 0.05F;
        }

        this.rightArm.thumb.zRot -= breatheAnim * 0.05F;
        for (ModelPart modelPart : this.leftArm.finger) {
            modelPart.zRot -= breatheAnim * 0.05F;
        }

        this.leftArm.thumb.zRot += breatheAnim * 0.05F;
        this.lowerRightArm.arm.zRot += breatheAnim * 0.002F;
        this.lowerLeftArm.arm.zRot -= breatheAnim * 0.002F;
        for (ModelPart modelPart : this.lowerRightArm.finger) {
            modelPart.zRot += breatheAnim * 0.02F;
        }

        this.lowerRightArm.thumb.zRot -= breatheAnim * 0.02F;
        for (ModelPart modelPart : this.lowerLeftArm.finger) {
            modelPart.zRot -= breatheAnim * 0.02F;
        }

        this.lowerLeftArm.thumb.zRot += breatheAnim * 0.02F;
        this.pelvis.y -= Math.abs(walkAnim[4]);
        this.chest.yRot -= walkAnim[4] * 0.06F;
        this.rightArm.arm.xRot -= walkAnim[0] * 0.6F;
        this.leftArm.arm.xRot += walkAnim[1] * 0.6F;
        this.rightArm.foreArm.xRot -= walkAnim[0] * 0.2F;
        this.leftArm.foreArm.xRot += walkAnim[1] * 0.2F;
        this.lowerRightArm.arm.xRot -= walkAnim[2] * 0.3F;
        this.lowerLeftArm.arm.xRot += walkAnim[3] * 0.3F;
        this.lowerRightArm.foreArm.xRot -= walkAnim[2] * 0.1F;
        this.lowerLeftArm.foreArm.xRot += walkAnim[3] * 0.1F;
        this.rightLegJoint.xRot += rightLegJointWalk * 0.6F;
        this.leftLegJoint.xRot += leftLegJointWalk * 0.6F;
        this.rightLegLower.xRot += rightLegWalk * 0.3F;
        this.leftLegLower.xRot += leftLegWalk * 0.3F;
        Animator.setScale(this.lowerRightArm.arm, state.armScale);
        Animator.setScale(this.lowerLeftArm.arm, state.armScale);
    }

    private void animateHoldBlock(MutantEndermanRenderState state, int armId) {
        float animationProgress = state.heldBlockTicks[armId] / 10.0F;
        float rotationAmount = Mth.sin(animationProgress * Mth.HALF_PI);
        if (armId == 0) {
            this.rightArm.arm.zRot += rotationAmount * 0.8F;
            this.rightArm.foreArm.zRot += rotationAmount * 0.6F;
            this.rightArm.hand.yRot += rotationAmount * 0.8F;
            this.rightArm.finger[0].xRot += -rotationAmount * 0.2F;
            this.rightArm.finger[2].xRot += rotationAmount * 0.2F;
            for (ModelPart modelPart : this.rightArm.finger) {
                modelPart.zRot += rotationAmount * 0.6F;
            }

            this.rightArm.thumb.zRot += -rotationAmount * 0.4F;
        } else if (armId == 1) {
            this.leftArm.arm.zRot += -rotationAmount * 0.8F;
            this.leftArm.foreArm.zRot += -rotationAmount * 0.6F;
            this.leftArm.hand.yRot += -rotationAmount * 0.8F;
            this.leftArm.finger[0].xRot += -rotationAmount * 0.2F;
            this.leftArm.finger[2].xRot += rotationAmount * 0.2F;
            for (ModelPart modelPart : this.leftArm.finger) {
                modelPart.zRot += -rotationAmount * 0.6F;
            }

            this.leftArm.thumb.zRot += rotationAmount * 0.4F;
        } else if (armId == 2) {
            this.lowerRightArm.arm.zRot += rotationAmount * 0.5F;
            this.lowerRightArm.foreArm.zRot += rotationAmount * 0.4F;
            this.lowerRightArm.hand.yRot += rotationAmount * 0.4F;
            this.lowerRightArm.finger[0].xRot += -rotationAmount * 0.2F;
            this.lowerRightArm.finger[2].xRot += rotationAmount * 0.2F;
            for (ModelPart modelPart : this.lowerRightArm.finger) {
                modelPart.zRot += rotationAmount * 0.6F;
            }

            this.lowerRightArm.thumb.zRot += -rotationAmount * 0.4F;
        } else if (armId == 3) {
            this.lowerLeftArm.arm.zRot += -rotationAmount * 0.5F;
            this.lowerLeftArm.foreArm.zRot += -rotationAmount * 0.4F;
            this.lowerLeftArm.hand.yRot += -rotationAmount * 0.4F;
            this.lowerLeftArm.finger[0].xRot += -rotationAmount * 0.2F;
            this.lowerLeftArm.finger[2].xRot += rotationAmount * 0.2F;
            for (ModelPart modelPart : this.lowerLeftArm.finger) {
                modelPart.zRot += -rotationAmount * 0.6F;
            }

            this.lowerLeftArm.thumb.zRot += rotationAmount * 0.4F;
        }
    }

    private void animateMelee(MutantEndermanRenderState state, int armId) {
        float sideSign = (armId & 1) == 0 ? 1.0F : -1.0F;
        Arm arm = this.getArmFromId(armId);
        if (state.animationTime < 2.0F) {
            float animationProgress = state.animationTime / 2.0F;
            float rotationAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            arm.arm.xRot += rotationAmount * 0.2F;
            arm.finger[0].zRot += rotationAmount * 0.3F * sideSign;
            arm.finger[1].zRot += rotationAmount * 0.3F * sideSign;
            arm.finger[2].zRot += rotationAmount * 0.3F * sideSign;
            arm.foreFinger[0].zRot += -rotationAmount * 0.5F * sideSign;
            arm.foreFinger[1].zRot += -rotationAmount * 0.5F * sideSign;
            arm.foreFinger[2].zRot += -rotationAmount * 0.5F * sideSign;
        } else if (state.animationTime < 5.0F) {
            float animationProgress = (state.animationTime - 2.0F) / 3.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            float swayAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            this.chest.yRot += -swayAmount * 0.1F * sideSign;
            arm.arm.xRot += rotationAmount * 1.1F - 1.1F;
            arm.foreArm.xRot += -rotationAmount * 0.4F;
            arm.finger[0].zRot += 0.3F * sideSign;
            arm.finger[1].zRot += 0.3F * sideSign;
            arm.finger[2].zRot += 0.3F * sideSign;
            arm.foreFinger[0].zRot += -0.5F * sideSign;
            arm.foreFinger[1].zRot += -0.5F * sideSign;
            arm.foreFinger[2].zRot += -0.5F * sideSign;
        } else if (state.animationTime < 6.0F) {
            this.chest.yRot += -0.1F * sideSign;
            arm.arm.xRot += -1.1F;
            arm.foreArm.xRot += -0.4F;
            arm.finger[0].zRot += 0.3F * sideSign;
            arm.finger[1].zRot += 0.3F * sideSign;
            arm.finger[2].zRot += 0.3F * sideSign;
            arm.foreFinger[0].zRot += -0.5F * sideSign;
            arm.foreFinger[1].zRot += -0.5F * sideSign;
            arm.foreFinger[2].zRot += -0.5F * sideSign;
        } else if (state.animationTime < 10.0F) {
            float animationProgress = (state.animationTime - 6.0F) / 4.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            this.chest.yRot += -rotationAmount * 0.1F * sideSign;
            arm.arm.xRot += -rotationAmount * 1.1F;
            arm.foreArm.xRot += -rotationAmount * 0.4F;
            arm.finger[0].zRot += rotationAmount * 0.3F * sideSign;
            arm.finger[1].zRot += rotationAmount * 0.3F * sideSign;
            arm.finger[2].zRot += rotationAmount * 0.3F * sideSign;
            arm.foreFinger[0].zRot += -rotationAmount * 0.5F * sideSign;
            arm.foreFinger[1].zRot += -rotationAmount * 0.5F * sideSign;
            arm.foreFinger[2].zRot += -rotationAmount * 0.5F * sideSign;
        }
    }

    private void animateThrowBlock(MutantEndermanRenderState state, int armId) {
        switch (armId) {
            case 0 -> {
                if (state.animationTime < 4.0F) {
                    float animationProgress = state.animationTime / 4.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    float sinSwingAmount = Mth.sin(animationProgress * Mth.HALF_PI);
                    this.rightArm.arm.xRot += -sinSwingAmount * 1.5F;
                    this.rightArm.arm.zRot += cosSwingAmount * 0.8F;
                    this.rightArm.foreArm.zRot += cosSwingAmount * 0.6F;
                    this.rightArm.hand.yRot += cosSwingAmount * 0.8F;
                    this.rightArm.finger[0].xRot += -cosSwingAmount * 0.2F;
                    this.rightArm.finger[2].xRot += cosSwingAmount * 0.2F;

                    for (ModelPart finger : this.rightArm.finger) {
                        finger.zRot += cosSwingAmount * 0.6F;
                    }

                    this.rightArm.thumb.zRot += -cosSwingAmount * 0.4F;
                } else if (state.animationTime < 7.0F) {
                    this.rightArm.arm.xRot += -1.5F;
                } else if (state.animationTime < 14.0F) {
                    float animationProgress = (state.animationTime - 7.0F) / 7.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    this.rightArm.arm.xRot += -cosSwingAmount * 1.5F;
                }
            }
            case 1 -> {
                if (state.animationTime < 4.0F) {
                    float animationProgress = state.animationTime / 4.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    float sinSwingAmount = Mth.sin(animationProgress * Mth.HALF_PI);
                    this.leftArm.arm.xRot += -sinSwingAmount * 1.5F;
                    this.leftArm.arm.zRot += -cosSwingAmount * 0.8F;
                    this.leftArm.foreArm.zRot += -cosSwingAmount * 0.6F;
                    this.leftArm.hand.yRot += -cosSwingAmount * 0.8F;
                    this.leftArm.finger[0].xRot += -cosSwingAmount * 0.2F;
                    this.leftArm.finger[2].xRot += cosSwingAmount * 0.2F;

                    for (ModelPart finger : this.leftArm.finger) {
                        finger.zRot += -cosSwingAmount * 0.6F;
                    }

                    this.leftArm.thumb.zRot += cosSwingAmount * 0.4F;
                } else if (state.animationTime < 7.0F) {
                    this.leftArm.arm.xRot += -1.5F;
                } else if (state.animationTime < 14.0F) {
                    float animationProgress = (state.animationTime - 7.0F) / 7.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    this.leftArm.arm.xRot += -cosSwingAmount * 1.5F;
                }
            }
            case 2 -> {
                if (state.animationTime < 4.0F) {
                    float animationProgress = state.animationTime / 4.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    float sinSwingAmount = Mth.sin(animationProgress * Mth.HALF_PI);
                    this.lowerRightArm.arm.xRot += -sinSwingAmount * 1.5F;
                    this.lowerRightArm.arm.zRot += cosSwingAmount * 0.5F;
                    this.lowerRightArm.foreArm.zRot += cosSwingAmount * 0.4F;
                    this.lowerRightArm.hand.yRot += cosSwingAmount * 0.4F;
                    this.lowerRightArm.finger[0].xRot += -cosSwingAmount * 0.2F;
                    this.lowerRightArm.finger[2].xRot += cosSwingAmount * 0.2F;

                    for (ModelPart finger : this.lowerRightArm.finger) {
                        finger.zRot += cosSwingAmount * 0.6F;
                    }

                    this.lowerRightArm.thumb.zRot += -cosSwingAmount * 0.4F;
                } else if (state.animationTime < 7.0F) {
                    this.lowerRightArm.arm.xRot += -1.5F;
                } else if (state.animationTime < 14.0F) {
                    float animationProgress = (state.animationTime - 7.0F) / 7.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    this.lowerRightArm.arm.xRot += -cosSwingAmount * 1.5F;
                }
            }
            case 3 -> {
                if (state.animationTime < 4.0F) {
                    float animationProgress = state.animationTime / 4.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    float sinSwingAmount = Mth.sin(animationProgress * Mth.HALF_PI);
                    this.lowerLeftArm.arm.xRot += -sinSwingAmount * 1.5F;
                    this.lowerLeftArm.arm.zRot += -cosSwingAmount * 0.5F;
                    this.lowerLeftArm.foreArm.zRot += -cosSwingAmount * 0.4F;
                    this.lowerLeftArm.hand.yRot += -cosSwingAmount * 0.4F;
                    this.lowerLeftArm.finger[0].xRot += -cosSwingAmount * 0.2F;
                    this.lowerLeftArm.finger[2].xRot += cosSwingAmount * 0.2F;

                    for (ModelPart finger : this.lowerLeftArm.finger) {
                        finger.zRot += -cosSwingAmount * 0.6F;
                    }

                    this.lowerLeftArm.thumb.zRot += cosSwingAmount * 0.4F;
                } else if (state.animationTime < 7.0F) {
                    this.lowerLeftArm.arm.xRot += -1.5F;
                } else if (state.animationTime < 14.0F) {
                    float animationProgress = (state.animationTime - 7.0F) / 7.0F;
                    float cosSwingAmount = Mth.cos(animationProgress * Mth.HALF_PI);
                    this.lowerLeftArm.arm.xRot += -cosSwingAmount * 1.5F;
                }
            }
        }
    }

    private void animateScream(MutantEndermanRenderState state) {
        if (state.animationTime < 35.0F) {
            float animationProgress = state.animationTime / 35.0F;
            float rotationAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            this.abdomen.xRot += rotationAmount * 0.3F;
            this.chest.xRot += rotationAmount * 0.4F;
            this.neck.xRot += rotationAmount * 0.2F;
            this.head.xRot += rotationAmount * 0.3F;
            this.rightArm.arm.xRot += -rotationAmount * 0.6F;
            this.rightArm.arm.yRot += rotationAmount * 0.4F;
            this.rightArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.rightArm.hand.zRot += -rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.rightArm.finger[i].zRot += rotationAmount * 0.3F;
                this.rightArm.foreFinger[i].zRot += -rotationAmount * 0.5F;
            }

            this.leftArm.arm.xRot += -rotationAmount * 0.6F;
            this.leftArm.arm.yRot += -rotationAmount * 0.4F;
            this.leftArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.leftArm.hand.zRot += rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.leftArm.finger[i].zRot += -rotationAmount * 0.3F;
                this.leftArm.foreFinger[i].zRot += rotationAmount * 0.5F;
            }

            this.lowerRightArm.arm.xRot += -rotationAmount * 0.4F;
            this.lowerRightArm.arm.yRot += rotationAmount * 0.2F;
            this.lowerRightArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.lowerRightArm.hand.zRot += -rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.lowerRightArm.finger[i].zRot += rotationAmount * 0.3F;
                this.lowerRightArm.foreFinger[i].zRot += -rotationAmount * 0.5F;
            }

            this.lowerLeftArm.arm.xRot += -rotationAmount * 0.4F;
            this.lowerLeftArm.arm.yRot += -rotationAmount * 0.2F;
            this.lowerLeftArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.lowerLeftArm.hand.zRot += rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.lowerLeftArm.finger[i].zRot += -rotationAmount * 0.3F;
                this.lowerLeftArm.foreFinger[i].zRot += rotationAmount * 0.5F;
            }
        } else if (state.animationTime < 40.0F) {
            this.abdomen.xRot += 0.3F;
            this.chest.xRot += 0.4F;
            this.neck.xRot += 0.2F;
            this.head.xRot += 0.3F;
            this.rightArm.arm.xRot += -0.6F;
            this.rightArm.arm.yRot += 0.4F;
            this.rightArm.foreArm.xRot += -0.8F;
            this.rightArm.hand.zRot += -0.4F;

            for (int i = 0; i < 3; ++i) {
                this.rightArm.finger[i].zRot += 0.3F;
                this.rightArm.foreFinger[i].zRot += -0.5F;
            }

            this.leftArm.arm.xRot += -0.6F;
            this.leftArm.arm.yRot += -0.4F;
            this.leftArm.foreArm.xRot += -0.8F;
            this.leftArm.hand.zRot += 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.leftArm.finger[i].zRot += -0.3F;
                this.leftArm.foreFinger[i].zRot += 0.5F;
            }

            this.lowerRightArm.arm.xRot += -0.4F;
            this.lowerRightArm.arm.yRot += 0.2F;
            this.lowerRightArm.foreArm.xRot += -0.8F;
            this.lowerRightArm.hand.zRot += -0.4F;

            for (int i = 0; i < 3; ++i) {
                this.lowerRightArm.finger[i].zRot += 0.3F;
                this.lowerRightArm.foreFinger[i].zRot += -0.5F;
            }

            this.lowerLeftArm.arm.xRot += -0.4F;
            this.lowerLeftArm.arm.yRot += -0.2F;
            this.lowerLeftArm.foreArm.xRot += -0.8F;
            this.lowerLeftArm.hand.zRot += 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.lowerLeftArm.finger[i].zRot += -0.3F;
                this.lowerLeftArm.foreFinger[i].zRot += 0.5F;
            }
        } else if (state.animationTime < 44.0F) {
            float animationProgress = (state.animationTime - 40.0F) / 4.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            float swayAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            this.abdomen.xRot += -rotationAmount * 0.1F + 0.4F;
            this.chest.xRot += rotationAmount * 0.1F + 0.3F;
            this.chest.zRot += swayAmount * 0.5F;
            this.neck.xRot += rotationAmount * 0.2F;
            this.neck.zRot += swayAmount * 0.2F;
            this.head.xRot += rotationAmount * 1.2F - 0.8F;
            this.head.zRot += swayAmount * 0.4F;
            this.mouth.xRot += swayAmount * 0.6F;
            this.rightArm.arm.xRot += -rotationAmount * 0.6F;
            this.rightArm.arm.yRot += 0.4F;
            this.rightArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.rightArm.hand.zRot += -rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.rightArm.finger[i].zRot += rotationAmount * 0.3F;
                this.rightArm.foreFinger[i].zRot += -rotationAmount * 0.5F;
            }

            this.leftArm.arm.xRot += -rotationAmount * 0.6F;
            this.leftArm.arm.yRot += -0.4F;
            this.leftArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.leftArm.hand.zRot += rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.leftArm.finger[i].zRot += -rotationAmount * 0.3F;
                this.leftArm.foreFinger[i].zRot += rotationAmount * 0.5F;
            }

            this.lowerRightArm.arm.xRot += -rotationAmount * 0.4F;
            this.lowerRightArm.arm.yRot += -rotationAmount * 0.1F + 0.3F;
            this.lowerRightArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.lowerRightArm.hand.zRot += -rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.lowerRightArm.finger[i].zRot += rotationAmount * 0.3F;
                this.lowerRightArm.foreFinger[i].zRot += -rotationAmount * 0.5F;
            }

            this.lowerLeftArm.arm.xRot += -rotationAmount * 0.4F;
            this.lowerLeftArm.arm.yRot += rotationAmount * 0.1F - 0.3F;
            this.lowerLeftArm.foreArm.xRot += -rotationAmount * 0.8F;
            this.lowerLeftArm.hand.zRot += rotationAmount * 0.4F;

            for (int i = 0; i < 3; ++i) {
                this.lowerLeftArm.finger[i].zRot += -rotationAmount * 0.3F;
                this.lowerLeftArm.foreFinger[i].zRot += rotationAmount * 0.5F;
            }

            this.rightLeg.zRot += swayAmount * 0.1F;
            this.leftLeg.zRot += -swayAmount * 0.1F;
        } else if (state.animationTime < 155.0F) {
            float animationProgress = (state.animationTime - 44.0F) / 111.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            this.abdomen.xRot += 0.4F;
            this.chest.xRot += 0.3F;
            this.chest.zRot += rotationAmount - 0.5F;
            this.neck.zRot += rotationAmount * 0.4F - 0.2F;
            this.head.xRot += -0.8F;
            this.head.zRot += rotationAmount * 0.8F - 0.4F;
            this.mouth.xRot += 0.6F;
            this.rightArm.arm.yRot += 0.4F;
            this.leftArm.arm.yRot += -0.4F;
            this.lowerRightArm.arm.yRot += 0.3F;
            this.lowerLeftArm.arm.yRot += -0.3F;
            this.rightLeg.zRot += 0.1F;
            this.leftLeg.zRot += -0.1F;
        } else if (state.animationTime < 160.0F) {
            float animationProgress = (state.animationTime - 155.0F) / 5.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            this.abdomen.xRot += rotationAmount * 0.4F;
            this.chest.xRot += rotationAmount * 0.3F;
            this.chest.zRot += -rotationAmount * 0.5F;
            this.neck.zRot += -rotationAmount * 0.2F;
            this.head.xRot += -rotationAmount * 0.8F;
            this.head.zRot += -rotationAmount * 0.4F;
            this.mouth.xRot += rotationAmount * 0.6F;
            this.rightArm.arm.yRot += rotationAmount * 0.4F;
            this.leftArm.arm.yRot += -rotationAmount * 0.4F;
            this.lowerRightArm.arm.yRot += rotationAmount * 0.3F;
            this.lowerLeftArm.arm.yRot += -rotationAmount * 0.3F;
            this.rightLeg.zRot += rotationAmount * 0.1F;
            this.leftLeg.zRot += -rotationAmount * 0.1F;
        }
    }

    private void animateTeleSmash(MutantEndermanRenderState state) {
        if (state.animationTime < 18.0F) {
            float animationProgress = state.animationTime / 18.0F;
            float rotationAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            this.chest.xRot += -rotationAmount * 0.3F;
            this.rightArm.arm.yRot += rotationAmount * 0.2F;
            this.rightArm.arm.zRot += rotationAmount * 0.8F;
            this.rightArm.hand.yRot += rotationAmount * 1.7F;
            this.leftArm.arm.yRot += -rotationAmount * 0.2F;
            this.leftArm.arm.zRot += -rotationAmount * 0.8F;
            this.leftArm.hand.yRot += -rotationAmount * 1.7F;
            this.lowerRightArm.arm.yRot += rotationAmount * 0.2F;
            this.lowerRightArm.arm.zRot += rotationAmount * 0.6F;
            this.lowerRightArm.hand.yRot += rotationAmount * 1.7F;
            this.lowerLeftArm.arm.yRot += -rotationAmount * 0.2F;
            this.lowerLeftArm.arm.zRot += -rotationAmount * 0.6F;
            this.lowerLeftArm.hand.yRot += -rotationAmount * 1.7F;
        } else if (state.animationTime < 20.0F) {
            float animationProgress = (state.animationTime - 18.0F) / 2.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            float liftAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            this.chest.xRot += -rotationAmount * 0.3F;
            this.rightArm.arm.xRot += -liftAmount * 0.8F;
            this.rightArm.arm.yRot += 0.2F;
            this.rightArm.arm.zRot += 0.8F;
            ++this.rightArm.hand.yRot;
            this.leftArm.arm.xRot += -liftAmount * 0.8F;
            this.leftArm.arm.yRot += -0.2F;
            this.leftArm.arm.zRot += -0.8F;
            this.leftArm.hand.yRot += -1.7F;
            this.lowerRightArm.arm.xRot += -liftAmount * 0.9F;
            this.lowerRightArm.arm.yRot += 0.2F;
            this.lowerRightArm.arm.zRot += 0.6F;
            ++this.lowerRightArm.hand.yRot;
            this.lowerLeftArm.arm.xRot += -liftAmount * 0.9F;
            this.lowerLeftArm.arm.yRot += -0.2F;
            this.lowerLeftArm.arm.zRot += -0.6F;
            this.lowerLeftArm.hand.yRot += -1.7F;
        } else if (state.animationTime < 24.0F) {
            this.rightArm.arm.xRot += -0.8F;
            this.rightArm.arm.yRot += 0.2F;
            this.rightArm.arm.zRot += 0.8F;
            ++this.rightArm.hand.yRot;
            this.leftArm.arm.xRot += -0.8F;
            this.leftArm.arm.yRot += -0.2F;
            this.leftArm.arm.zRot += -0.8F;
            this.leftArm.hand.yRot += -1.7F;
            this.lowerRightArm.arm.xRot += -0.9F;
            this.lowerRightArm.arm.yRot += 0.2F;
            this.lowerRightArm.arm.zRot += 0.6F;
            ++this.lowerRightArm.hand.yRot;
            this.lowerLeftArm.arm.xRot += -0.9F;
            this.lowerLeftArm.arm.yRot += -0.2F;
            this.lowerLeftArm.arm.zRot += -0.6F;
            this.lowerLeftArm.hand.yRot += -1.7F;
        } else if (state.animationTime < 30.0F) {
            float animationProgress = (state.animationTime - 24.0F) / 6.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            this.rightArm.arm.xRot += -rotationAmount * 0.8F;
            this.rightArm.arm.yRot += rotationAmount * 0.2F;
            this.rightArm.arm.zRot += rotationAmount * 0.8F;
            this.rightArm.hand.yRot += rotationAmount * 1.7F;
            this.leftArm.arm.xRot += -rotationAmount * 0.8F;
            this.leftArm.arm.yRot += -rotationAmount * 0.2F;
            this.leftArm.arm.zRot += -rotationAmount * 0.8F;
            this.leftArm.hand.yRot += -rotationAmount * 1.7F;
            this.lowerRightArm.arm.xRot += -rotationAmount * 0.9F;
            this.lowerRightArm.arm.yRot += rotationAmount * 0.2F;
            this.lowerRightArm.arm.zRot += rotationAmount * 0.6F;
            this.lowerRightArm.hand.yRot += rotationAmount * 1.7F;
            this.lowerLeftArm.arm.xRot += -rotationAmount * 0.9F;
            this.lowerLeftArm.arm.yRot += -rotationAmount * 0.2F;
            this.lowerLeftArm.arm.zRot += -rotationAmount * 0.6F;
            this.lowerLeftArm.hand.yRot += -rotationAmount * 1.7F;
        }
    }

    private void animateDeath(MutantEndermanRenderState state) {
        if (state.deathTime < 80.0F) {
            float animationProgress = state.deathTime / 80.0F;
            float rotationAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            this.head.xRot += rotationAmount * 0.4F;
            this.neck.xRot += rotationAmount * 0.3F;
            this.pelvis.y += -rotationAmount * 12.0F;
            this.rightArm.arm.xRot += -rotationAmount * 0.4F;
            this.rightArm.arm.yRot += rotationAmount * 0.4F;
            this.rightArm.arm.zRot += rotationAmount * 0.6F;
            this.rightArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.leftArm.arm.xRot += -rotationAmount * 0.4F;
            this.leftArm.arm.yRot += -rotationAmount * 0.2F;
            this.leftArm.arm.zRot += -rotationAmount * 0.6F;
            this.leftArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.lowerRightArm.arm.xRot += -rotationAmount * 0.4F;
            this.lowerRightArm.arm.yRot += rotationAmount * 0.4F;
            this.lowerRightArm.arm.zRot += rotationAmount * 0.6F;
            this.lowerRightArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.lowerLeftArm.arm.xRot += -rotationAmount * 0.4F;
            this.lowerLeftArm.arm.yRot += -rotationAmount * 0.2F;
            this.lowerLeftArm.arm.zRot += -rotationAmount * 0.6F;
            this.lowerLeftArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.rightLeg.xRot += -rotationAmount * 0.9F;
            this.rightLeg.yRot += rotationAmount * 0.3F;
            this.leftLeg.xRot += -rotationAmount * 0.9F;
            this.leftLeg.yRot += -rotationAmount * 0.3F;
            this.rightLegLower.xRot += rotationAmount * 1.6F;
            this.leftLegLower.xRot += rotationAmount * 1.6F;
        } else if (state.deathTime < 84.0F) {
            float animationProgress = (state.deathTime - 80.0F) / 4.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.HALF_PI);
            float deathAmount = Mth.sin(animationProgress * Mth.HALF_PI);
            this.head.xRot += rotationAmount * 0.4F;
            this.mouth.xRot += deathAmount * 0.6F;
            this.neck.xRot += rotationAmount * 0.4F - 0.1F;
            this.chest.xRot += -deathAmount * 0.8F;
            this.abdomen.xRot += -deathAmount * 0.2F;
            this.pelvis.y += -12.0F;
            this.rightArm.arm.xRot += -rotationAmount * 0.4F;
            this.rightArm.arm.yRot += -rotationAmount * 1.4F + 1.8F;
            this.rightArm.arm.zRot += rotationAmount * 0.6F;
            this.rightArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.leftArm.arm.xRot += -rotationAmount * 0.4F;
            this.leftArm.arm.yRot += rotationAmount * 1.6F - 1.8F;
            this.leftArm.arm.zRot += -rotationAmount * 0.6F;
            this.leftArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.lowerRightArm.arm.xRot += -rotationAmount * 0.5F + 0.1F;
            this.lowerRightArm.arm.yRot += -rotationAmount * 1.1F + 1.5F;
            this.lowerRightArm.arm.zRot += rotationAmount * 0.6F;
            this.lowerRightArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.lowerLeftArm.arm.xRot += -rotationAmount * 0.5F + 0.1F;
            this.lowerLeftArm.arm.yRot += rotationAmount * 1.1F - 1.5F;
            this.lowerLeftArm.arm.zRot += -rotationAmount * 0.6F;
            this.lowerLeftArm.foreArm.xRot += -rotationAmount * 1.2F;
            this.rightLeg.xRot += -rotationAmount * 1.7F + 0.8F;
            this.rightLeg.yRot += rotationAmount * 0.3F;
            this.rightLeg.zRot += deathAmount * 0.2F;
            this.leftLeg.xRot += -rotationAmount * 1.7F + 0.8F;
            this.leftLeg.yRot += -rotationAmount * 0.3F;
            this.leftLeg.zRot += -deathAmount * 0.2F;
            this.rightLegLower.xRot += rotationAmount * 1.6F;
            this.leftLegLower.xRot += rotationAmount * 1.6F;
        } else {
            this.mouth.xRot += 0.6F;
            this.neck.xRot += -0.1F;
            this.chest.xRot += -0.8F;
            this.abdomen.xRot += -0.2F;
            this.pelvis.y += -12.0F;
            ++this.rightArm.arm.yRot;
            this.leftArm.arm.yRot += -1.8F;
            this.lowerRightArm.arm.xRot += 0.1F;
            ++this.lowerRightArm.arm.yRot;
            this.lowerLeftArm.arm.xRot += 0.1F;
            this.lowerLeftArm.arm.yRot += -1.5F;
            this.rightLeg.xRot += 0.8F;
            this.rightLeg.zRot += 0.2F;
            this.leftLeg.xRot += 0.8F;
            this.leftLeg.zRot += -0.2F;
        }
    }

    private Arm getArmFromId(int id) {
        return id == 0 ? this.rightArm : (id == 1 ? this.leftArm : (id == 2 ? this.lowerRightArm : this.lowerLeftArm));
    }

    public void translateRotateArm(PoseStack poseStack, int id) {
        this.pelvis.translateAndRotate(poseStack);
        this.abdomen.translateAndRotate(poseStack);
        this.chest.translateAndRotate(poseStack);
        this.getArmFromId(id).translateRotate(poseStack);
    }

    static class Arm extends Model<Unit> {
        private final ModelPart arm;
        private final ModelPart foreArm;
        private final ModelPart hand;
        private final ModelPart[] finger = new ModelPart[3];
        private final ModelPart[] foreFinger = new ModelPart[3];
        private final ModelPart thumb;

        public Arm(ModelPart modelPart, String prefix) {
            super(modelPart.getChild(prefix + "arm"), RenderTypes::entityCutout);
            this.arm = this.root;
            this.foreArm = this.root.getChild("fore_arm");
            this.hand = this.foreArm.getChild("hand");
            for (int i = 0; i < 3; i++) {
                this.finger[i] = this.hand.getChild("finger" + i);
                this.foreFinger[i] = this.finger[i].getChild("fore_finger" + i);
            }

            this.thumb = this.hand.getChild("thumb");
        }

        public static void createArmLayer(PartDefinition root, String prefix, boolean right, boolean lower) {
            float rootXRot = -Mth.PI / 6.0F + (lower ? 0.1F : 0.0F);
            float rootZRot = (right ? Mth.PI / 6.0F : -Mth.PI / 6.0F) + (lower ? (right ? -0.2F : 0.2F) : 0.0F);
            PartDefinition arm = root.addOrReplaceChild(prefix + "arm",
                    CubeListBuilder.create()
                            .texOffs(92, 0)
                            .addBox(-1.5F, lower ? 6.0F : 0.0F, -1.5F, 3.0F, 22.0F, 3.0F, new CubeDeformation(0.1F))
                            .mirror(!right),
                    PartPose.offsetAndRotation(right ? -4.0F : 4.0F, -14.0F, 0.0F, rootXRot, 0.0F, rootZRot));
            PartDefinition foreArm = arm.addOrReplaceChild("fore_arm",
                    CubeListBuilder.create()
                            .texOffs(104, 0)
                            .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 18.0F, 3.0F)
                            .mirror(!right),
                    PartPose.offsetAndRotation(0.0F, 21.0F, 1.0F, -Mth.PI / 5.0F, 0.0F, 0.0F));
            PartDefinition hand = foreArm.addOrReplaceChild("hand",
                    CubeListBuilder.create().texOffs(0, 0),
                    PartPose.offsetAndRotation(0.0F, 17.5F, 0.0F, 0.0F, right ? -Mth.PI / 8.0F : Mth.PI / 8.0F, 0.0F));
            for (int i = 0; i < 3; ++i) {
                PartPose partPose;
                if (i == 0) {
                    partPose = PartPose.offsetAndRotation(right ? -0.5F : 0.5F,
                            0.0F,
                            -1.0F,
                            -Mth.PI / 12.0F,
                            0.0F,
                            0.0F);
                } else if (i == 1) {
                    partPose = PartPose.offsetAndRotation(right ? -0.5F : 0.5F,
                            0.0F,
                            0.0F,
                            0.0F,
                            0.0F,
                            right ? Mth.PI / 18.0F : -Mth.PI / 18.0F);
                } else {
                    partPose = PartPose.offsetAndRotation(right ? -0.5F : 0.5F, 0.0F, 1.0F, Mth.PI / 12.0F, 0.0F, 0.0F);
                }
                PartDefinition finger = hand.addOrReplaceChild("finger" + i,
                        CubeListBuilder.create()
                                .texOffs(76, 0)
                                .mirror(!right)
                                .addBox(-0.5F,
                                        0.0F,
                                        -0.5F,
                                        1.0F,
                                        i == 1 ? 6.0F : 5.0F,
                                        1.0F,
                                        new CubeDeformation(0.6F)),
                        partPose);
                float foreFingerZRot =
                        i == 1 ? (right ? -Mth.PI / 8.0F : Mth.PI / 8.0F) : (right ? -Mth.PI / 12.0F : Mth.PI / 12.0F);
                finger.addOrReplaceChild("fore_finger" + i,
                        CubeListBuilder.create()
                                .texOffs(76, 0)
                                .mirror(!right)
                                .addBox(-0.5F,
                                        0.0F,
                                        -0.5F,
                                        1.0F,
                                        i == 1 ? 6.0F : 5.0F,
                                        1.0F,
                                        new CubeDeformation(0.6F - 0.01F)),
                        PartPose.offsetAndRotation(0.0F,
                                0.5F + (float) (i == 1 ? 6 : 5),
                                0.0F,
                                0.0F,
                                0.0F,
                                foreFingerZRot));
            }
            hand.addOrReplaceChild("thumb",
                    CubeListBuilder.create()
                            .texOffs(76, 0)
                            .mirror(right)
                            .addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.6F)),
                    PartPose.offsetAndRotation(right ? 0.5F : -0.5F,
                            0.0F,
                            -0.5F,
                            -Mth.PI / 5.0F,
                            0.0F,
                            right ? -Mth.PI / 8.0F : Mth.PI / 8.0F));
        }

        private void translateRotate(PoseStack matrixStackIn) {
            this.arm.translateAndRotate(matrixStackIn);
            this.foreArm.translateAndRotate(matrixStackIn);
            this.hand.translateAndRotate(matrixStackIn);
        }
    }
}

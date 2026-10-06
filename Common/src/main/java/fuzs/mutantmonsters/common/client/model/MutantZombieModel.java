package fuzs.mutantmonsters.common.client.model;

import fuzs.mutantmonsters.common.client.animation.Animator;
import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantZombieRenderState;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantZombie;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MutantZombieModel extends EntityModel<MutantZombieRenderState> {
    private static final String PELVIS = "pelvis";
    private static final String CHEST = "chest";
    private static final String RIGHT_ARM_LOWER = "right_arm_lower";
    private static final String LEFT_ARM_LOWER = "left_arm_lower";
    private static final String RIGHT_LEG_LOWER = "right_leg_lower";
    private static final String LEFT_LEG_LOWER = "left_leg_lower";

    private final ModelPart pelvis;
    private final ModelPart waist;
    private final ModelPart chest;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightArmLower;
    private final ModelPart leftArmLower;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public MutantZombieModel(ModelPart root) {
        super(root);
        this.pelvis = root.getChild(PELVIS);
        this.waist = this.pelvis.getChild(PartNames.WAIST);
        this.chest = this.waist.getChild(CHEST);
        this.head = this.chest.getChild(PartNames.HEAD);
        this.rightArm = this.chest.getChild(PartNames.RIGHT_ARM);
        this.leftArm = this.chest.getChild(PartNames.LEFT_ARM);
        this.rightArmLower = this.rightArm.getChild(RIGHT_ARM_LOWER);
        this.leftArmLower = this.leftArm.getChild(LEFT_ARM_LOWER);
        this.rightLeg = this.pelvis.getChild(PartNames.RIGHT_LEG);
        this.leftLeg = this.pelvis.getChild(PartNames.LEFT_LEG);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition pelvis = root.addOrReplaceChild(PELVIS,
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 10.0F, 6.0F));

        PartDefinition waist = pelvis.addOrReplaceChild(PartNames.WAIST,
                CubeListBuilder.create().texOffs(0, 44).addBox(-7.0F, -16.0F, -6.0F, 14.0F, 16.0F, 12.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, Mth.PI / 16.0F, 0.0F, 0.0F));

        PartDefinition chest = waist.addOrReplaceChild(CHEST,
                CubeListBuilder.create().texOffs(0, 16).addBox(-12.0F, -12.0F, -8.0F, 24.0F, 12.0F, 16.0F),
                PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, Mth.PI / 6.0F, 0.0F, 0.0F));

        chest.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, -11.0F, -4.0F, -11.0F * Mth.PI / 48.0F, 0.0F, 0.0F));

        PartDefinition rightArm = chest.addOrReplaceChild(PartNames.RIGHT_ARM,
                CubeListBuilder.create().texOffs(104, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F),
                PartPose.offsetAndRotation(-11.0F, -8.0F, 2.0F, -5.0F * Mth.PI / 48.0F, 0.0F, Mth.PI / 8.0F));

        PartDefinition leftArm = chest.addOrReplaceChild(PartNames.LEFT_ARM,
                CubeListBuilder.create().texOffs(104, 0).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F),
                PartPose.offsetAndRotation(11.0F, -8.0F, 2.0F, -5.0F * Mth.PI / 48.0F, 0.0F, -Mth.PI / 8.0F));

        rightArm.addOrReplaceChild(RIGHT_ARM_LOWER,
                CubeListBuilder.create()
                        .texOffs(104, 22)
                        .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -Mth.PI / 3.0F, 0.0F, 0.0F));

        leftArm.addOrReplaceChild(LEFT_ARM_LOWER,
                CubeListBuilder.create()
                        .texOffs(104, 22)
                        .mirror()
                        .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -Mth.PI / 3.0F, 0.0F, 0.0F));

        PartDefinition rightLeg = pelvis.addOrReplaceChild(PartNames.RIGHT_LEG,
                CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 11.0F, 6.0F),
                PartPose.offsetAndRotation(-5.0F, -2.0F, 0.0F, -Mth.PI / 4.0F, 0.0F, 0.0F));

        PartDefinition leftLeg = pelvis.addOrReplaceChild(PartNames.LEFT_LEG,
                CubeListBuilder.create().texOffs(80, 0).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 11.0F, 6.0F),
                PartPose.offsetAndRotation(5.0F, -2.0F, 0.0F, -Mth.PI / 4.0F, 0.0F, 0.0F));

        rightLeg.addOrReplaceChild(RIGHT_LEG_LOWER,
                CubeListBuilder.create()
                        .texOffs(80, 17)
                        .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(0.0F, 9.5F, 0.0F, Mth.PI / 4.0F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild(LEFT_LEG_LOWER,
                CubeListBuilder.create()
                        .texOffs(80, 17)
                        .mirror()
                        .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(0.0F, 9.5F, 0.0F, Mth.PI / 4.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(MutantZombieRenderState state) {
        super.setupAnim(state);
        float animationPos = state.walkAnimationPos;
        float animationSpeed = state.walkAnimationSpeed;
        float rightLegWalk = (Mth.sin((animationPos - 0.7F) * 0.4F) + 0.7F) * animationSpeed;
        float leftLegWalk = -(Mth.sin((animationPos + 0.7F) * 0.4F) - 0.7F) * animationSpeed;
        float walkAnim = Mth.sin(animationPos * 0.4F) * animationSpeed;
        float breatheAnim = Mth.sin(state.ageInTicks * 0.1F);
        float faceYaw = state.yRot * Mth.PI / 180.0F;
        float facePitch = state.xRot * Mth.PI / 180.0F;
        if (state.deathTime <= 0) {
            if (state.animation == MutantZombie.SLAM_GROUND_ANIMATION) {
                this.animateMelee(state);
            }

            if (state.animation == MutantZombie.ROAR_ANIMATION) {
                this.animateRoar(state);
                float scale = 1.0F - Mth.clamp(state.animationTime / 6.0F, 0.0F, 1.0F);
                rightLegWalk *= scale;
                leftLegWalk *= scale;
                walkAnim *= scale;
                facePitch *= scale;
            }

            if (state.animation == MutantZombie.THROW_ANIMATION) {
                this.animateThrow(state);
                float scale = 1.0F - Mth.clamp(state.animationTime / 3.0F, 0.0F, 1.0F);
                rightLegWalk *= scale;
                leftLegWalk *= scale;
                walkAnim *= scale;
                facePitch *= scale;
            }
        } else {
            this.animateDeath(state);
            float scale = 1.0F - Mth.clamp(state.deathTime / 6.0F, 0.0F, 1.0F);
            rightLegWalk *= scale;
            leftLegWalk *= scale;
            walkAnim *= scale;
            breatheAnim *= scale;
            faceYaw *= scale;
            facePitch *= scale;
        }

        this.chest.xRot += breatheAnim * 0.02F;
        this.rightArm.zRot -= breatheAnim * 0.05F;
        this.leftArm.zRot += breatheAnim * 0.05F;
        this.head.xRot += facePitch * 0.6F;
        this.head.yRot += faceYaw * 0.8F;
        this.head.zRot -= faceYaw * 0.2F;
        this.chest.xRot += facePitch * 0.4F;
        this.chest.yRot += faceYaw * 0.2F;
        this.pelvis.y += Mth.sin(animationPos * 0.8F) * animationSpeed * 0.5F;
        this.chest.yRot -= walkAnim * 0.1F;
        this.rightArm.xRot -= walkAnim * 0.6F;
        this.leftArm.xRot += walkAnim * 0.6F;
        this.rightLeg.xRot += rightLegWalk * 0.9F;
        this.leftLeg.xRot += leftLegWalk * 0.9F;
    }

    private void animateMelee(MutantZombieRenderState state) {
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        if (state.animationTime < 8.0F) {
            float progress = state.animationTime / 8.0F;
            float swingAmount = -Mth.sin(progress * Mth.PI / 2.0F);
            float swayAmount = Mth.cos(progress * Mth.PI / 2.0F);
            this.waist.xRot += swingAmount * 0.2F;
            this.chest.xRot += swingAmount * 0.2F;
            this.rightArm.xRot += swingAmount * 2.3F;
            this.rightArm.zRot += swayAmount * Mth.PI / 8.0F;
            this.leftArm.xRot += swingAmount * 2.3F;
            this.leftArm.zRot -= swayAmount * Mth.PI / 8.0F;
            this.rightArmLower.xRot += swingAmount * 0.8F;
            this.leftArmLower.xRot += swingAmount * 0.8F;
        } else if (state.animationTime < 12.0F) {
            float progress = (state.animationTime - 8.0F) / 4.0F;
            float swingAmount = -Mth.cos(progress * Mth.PI / 2.0F);
            float swayAmount = Mth.sin(progress * Mth.PI / 2.0F);
            this.waist.xRot += swingAmount * 0.9F + 0.7F;
            this.chest.xRot += swingAmount * 0.9F + 0.7F;
            this.rightArm.xRot += swingAmount * 0.2F - 2.1F;
            this.rightArm.zRot += swayAmount * 0.3F;
            this.leftArm.xRot += swingAmount * 0.2F - 2.1F;
            this.leftArm.zRot -= swayAmount * 0.3F;
            this.rightArmLower.xRot += swingAmount + 0.2F;
            this.leftArmLower.xRot += swingAmount + 0.2F;
        } else if (state.animationTime < 16.0F) {
            this.waist.xRot += 0.7F;
            this.chest.xRot += 0.7F;
            this.rightArm.xRot -= 2.1F;
            this.rightArm.zRot += 0.3F;
            this.leftArm.xRot -= 2.1F;
            this.leftArm.zRot -= 0.3F;
            this.rightArmLower.xRot += 0.2F;
            this.leftArmLower.xRot += 0.2F;
        } else if (state.animationTime < 24.0F) {
            float progress = (state.animationTime - 16.0F) / 8.0F;
            float swingAmount = Mth.cos(progress * Mth.PI / 2.0F);
            this.waist.xRot += swingAmount * 0.7F;
            this.chest.xRot += swingAmount * 0.7F;
            this.rightArm.xRot -= swingAmount * 2.1F;
            this.rightArm.zRot += swingAmount * -0.09269908F + Mth.PI / 8.0F;
            this.leftArm.xRot -= swingAmount * 2.1F;
            this.leftArm.zRot -= swingAmount * -0.09269908F + Mth.PI / 8.0F;
            this.rightArmLower.xRot += swingAmount * 0.2F;
            this.leftArmLower.xRot += swingAmount * 0.2F;
        } else {
            this.rightArm.zRot += Mth.PI / 8.0F;
            this.leftArm.zRot += -Mth.PI / 8.0F;
        }
    }

    private void animateRoar(MutantZombieRenderState state) {
        if (state.animationTime < 10.0F) {
            float progress = state.animationTime / 10.0F;
            float roarAmount = Mth.sin(progress * Mth.PI / 2.0F);
            float swayAmount = Mth.sin(progress * Mth.PI * Mth.PI / 8.0F);
            this.waist.xRot += roarAmount * 0.2F;
            this.chest.xRot += roarAmount * 0.4F;
            this.chest.yRot += swayAmount * 0.06F;
            this.head.xRot += roarAmount * 0.8F;
            this.rightArm.xRot -= roarAmount * 1.2F;
            this.rightArm.zRot += roarAmount * 0.6F;
            this.leftArm.xRot -= roarAmount * 1.2F;
            this.leftArm.zRot -= roarAmount * 0.6F;
            this.rightArmLower.xRot -= roarAmount * 0.8F;
            this.leftArmLower.xRot -= roarAmount * 0.8F;
        } else if (state.animationTime < 15.0F) {
            float progress = (state.animationTime - 10.0F) / 5.0F;
            float roarAmount = Mth.cos(progress * Mth.PI / 2.0F);
            float swayAmount = Mth.sin(progress * Mth.PI / 2.0F);
            this.waist.xRot += roarAmount * 0.39634955F - Mth.PI / 16.0F;
            this.chest.xRot += roarAmount * 0.6F - 0.2F;
            this.head.xRot += roarAmount - 0.2F;
            this.rightArm.xRot -= roarAmount * 2.2F - 1.0F;
            this.rightArm.yRot += swayAmount * 0.4F;
            this.rightArm.zRot += 0.6F;
            this.leftArm.xRot -= roarAmount * 2.2F - 1.0F;
            this.leftArm.yRot -= swayAmount * 0.4F;
            this.leftArm.zRot -= 0.6F;
            this.rightArmLower.xRot -= roarAmount - 0.2F;
            this.leftArmLower.xRot -= roarAmount - 0.2F;
            this.rightLeg.yRot += swayAmount * 0.3F;
            this.leftLeg.yRot -= swayAmount * 0.3F;
        } else if (state.animationTime < 75.0F) {
            this.waist.xRot -= Mth.PI / 16.0F;
            this.chest.xRot -= 0.2F;
            this.head.xRot -= 0.2F;
            Animator.addRotationAngle(this.rightArm, 1.0F, 0.4F, 0.6F);
            Animator.addRotationAngle(this.leftArm, 1.0F, -0.4F, -0.6F);
            this.rightArmLower.xRot += 0.2F;
            this.leftArmLower.xRot += 0.2F;
            this.rightLeg.yRot += 0.3F;
            this.leftLeg.yRot -= 0.3F;
        } else if (state.animationTime < 90.0F) {
            float progress = (state.animationTime - 75.0F) / 15.0F;
            float roarAmount = Mth.cos(progress * Mth.PI / 2.0F);
            this.waist.xRot -= roarAmount * 0.69634956F - 0.5F;
            this.chest.xRot -= roarAmount * 0.7F - 0.5F;
            this.head.xRot -= roarAmount * 0.6F - 0.4F;
            Animator.addRotationAngle(this.rightArm, roarAmount * 2.6F - 1.6F, roarAmount * 0.4F, roarAmount * 0.99269915F - Mth.PI / 8.0F);
            Animator.addRotationAngle(this.leftArm, roarAmount * 2.6F - 1.6F, -roarAmount * 0.4F, -roarAmount * 0.99269915F + Mth.PI / 8.0F);
            this.rightArmLower.xRot += roarAmount * -0.6F + 0.8F;
            this.leftArmLower.xRot += roarAmount * -0.6F + 0.8F;
            this.rightLeg.yRot += roarAmount * 0.3F;
            this.leftLeg.yRot -= roarAmount * 0.3F;
        } else if (state.animationTime < 110.0F) {
            this.waist.xRot += 0.5F;
            this.chest.xRot += 0.5F;
            this.head.xRot += 0.4F;
            Animator.addRotationAngle(this.rightArm, -1.6F, 0.0F, -Mth.PI / 8.0F);
            Animator.addRotationAngle(this.leftArm, -1.6F, 0.0F, Mth.PI / 8.0F);
            this.rightArmLower.xRot += 0.8F;
            this.leftArmLower.xRot += 0.8F;
        } else {
            float progress = (state.animationTime - 110.0F) / 10.0F;
            float roarAmount = Mth.cos(progress * Mth.PI / 2.0F);
            this.waist.xRot += roarAmount * 0.5F;
            this.chest.xRot += roarAmount * 0.5F;
            this.head.xRot += roarAmount * 0.4F;
            Animator.addRotationAngle(this.rightArm, roarAmount * -1.6F, 0.0F, roarAmount * -Mth.PI / 8.0F);
            Animator.addRotationAngle(this.leftArm, roarAmount * -1.6F, 0.0F, roarAmount * Mth.PI / 8.0F);
            this.rightArmLower.xRot += roarAmount * 0.8F;
            this.leftArmLower.xRot += roarAmount * 0.8F;
        }

        if (state.animationTime >= 10.0F && state.animationTime < 75.0F) {
            float progress = (state.animationTime - 10.0F) / 65.0F;
            float roarAmount = Mth.sin(progress * Mth.PI * 8.0F);
            float swayAmount = Mth.sin(progress * Mth.PI * 8.0F + Mth.PI / 4.0F);
            this.head.yRot += roarAmount * 0.5F - swayAmount * 0.2F;
            this.head.zRot -= roarAmount * 0.5F;
            this.chest.yRot += swayAmount * 0.06F;
        }
    }

    private void animateThrow(MutantZombieRenderState state) {
        if (state.animationTime < 3.0F) {
            float progress = state.animationTime / 3.0F;
            float throwAmount = Mth.sin(progress * Mth.PI / 2.0F);
            this.chest.xRot -= throwAmount * 0.4F;
            this.rightArm.xRot -= throwAmount * 1.8F;
            this.rightArm.zRot -= throwAmount * Mth.PI / 8.0F;
            this.leftArm.xRot -= throwAmount * 1.8F;
            this.leftArm.zRot += throwAmount * Mth.PI / 8.0F;
        } else if (state.animationTime < 5.0F) {
            this.chest.xRot -= 0.4F;
            --this.rightArm.xRot;
            this.rightArm.zRot = 0.0F;
            --this.leftArm.xRot;
            this.leftArm.zRot = 0.0F;
        } else {
            if (state.animationTime < 8.0F) {
                float progress = (state.animationTime - 5.0F) / 3.0F;
                float throwAmount = Mth.cos(progress * Mth.PI / 2.0F);
                float liftAmount = Mth.sin(progress * Mth.PI / 2.0F);
                this.waist.xRot += liftAmount * 0.2F;
                this.chest.xRot -= throwAmount * 0.6F - 0.2F;
                this.rightArm.xRot -= throwAmount * 2.2F - 0.4F;
                this.rightArm.zRot -= throwAmount * Mth.PI / 8.0F;
                this.leftArm.xRot -= throwAmount * 2.2F - 0.4F;
                this.leftArm.zRot += throwAmount * Mth.PI / 8.0F;
                this.rightArmLower.xRot -= liftAmount * 0.4F;
                this.leftArmLower.xRot -= liftAmount * 0.4F;
            } else if (state.animationTime < 10.0F) {
                this.waist.xRot += 0.2F;
                this.chest.xRot += 0.2F;
                this.rightArm.xRot += 0.4F;
                this.leftArm.xRot += 0.4F;
                this.rightArmLower.xRot -= 0.4F;
                this.leftArmLower.xRot -= 0.4F;
            } else if (state.animationTime < 15.0F) {
                float progress = (state.animationTime - 10.0F) / 5.0F;
                float throwAmount = Mth.cos(progress * Mth.PI / 2.0F);
                float liftAmount = Mth.sin(progress * Mth.PI / 2.0F);
                this.waist.xRot += throwAmount * 0.39634955F - Mth.PI / 16.0F;
                this.chest.xRot += throwAmount * 0.8F - 0.6F;
                this.rightArm.xRot += throwAmount * 3.0F - 2.6F;
                this.leftArm.xRot += throwAmount * 3.0F - 2.6F;
                this.rightArmLower.xRot -= throwAmount * 0.4F;
                this.leftArmLower.xRot -= throwAmount * 0.4F;
                this.rightLeg.xRot += liftAmount * 0.6F;
                this.leftLeg.xRot += liftAmount * 0.6F;
            } else if (state.throwHitTime == -1.0F) {
                this.waist.xRot -= Mth.PI / 16.0F;
                this.chest.xRot -= 0.6F;
                this.rightArm.xRot -= 2.6F;
                this.leftArm.xRot -= 2.6F;
                this.rightLeg.xRot += 0.6F;
                this.leftLeg.xRot += 0.6F;
            } else if (state.throwHitTime < 5.0F) {
                float progress = state.throwHitTime / 3.0F;
                float throwAmount = Mth.cos(progress * Mth.PI / 2.0F);
                float liftAmount = Mth.sin(progress * Mth.PI / 2.0F);
                this.waist.xRot -= throwAmount * 0.39634955F - 0.2F;
                this.chest.xRot -= throwAmount * 0.8F - 0.2F;
                Animator.addRotationAngle(this.rightArm, -(throwAmount * 2.2F + 0.4F), -liftAmount * Mth.PI / 8.0F, liftAmount * 0.4F);
                Animator.addRotationAngle(this.leftArm, -(throwAmount * 2.2F + 0.4F), liftAmount * Mth.PI / 8.0F, -liftAmount * 0.4F);
                this.rightArmLower.xRot += liftAmount * 0.2F;
                this.leftArmLower.xRot += liftAmount * 0.2F;
                this.rightLeg.xRot += throwAmount * 0.8F - 0.2F;
                this.leftLeg.xRot += throwAmount * 0.8F - 0.2F;
            } else if (state.throwFinishTime == -1.0F) {
                this.waist.xRot += 0.2F;
                this.chest.xRot += 0.2F;
                Animator.addRotationAngle(this.rightArm, -0.4F, -Mth.PI / 8.0F, 0.4F);
                Animator.addRotationAngle(this.leftArm, -0.4F, Mth.PI / 8.0F, -0.4F);
                this.rightArmLower.xRot += 0.2F;
                this.leftArmLower.xRot += 0.2F;
                this.rightLeg.xRot -= 0.2F;
                this.leftLeg.xRot -= 0.2F;
            } else if (state.throwFinishTime < 10.0F) {
                float progress = state.throwFinishTime / 10.0F;
                float throwAmount = Mth.cos(progress * Mth.PI / 2.0F);
                this.waist.xRot += throwAmount * 0.2F;
                this.chest.xRot += throwAmount * 0.2F;
                Animator.addRotationAngle(this.rightArm, -throwAmount * 0.4F, -throwAmount * Mth.PI / 8.0F, throwAmount * 0.4F);
                Animator.addRotationAngle(this.rightArm, -throwAmount * 0.4F, throwAmount * Mth.PI / 8.0F, -throwAmount * 0.4F);
                this.rightArmLower.xRot += throwAmount * 0.2F;
                this.leftArmLower.xRot += throwAmount * 0.2F;
                this.rightLeg.xRot -= throwAmount * 0.2F;
                this.leftLeg.xRot -= throwAmount * 0.2F;
            }
        }
    }

    private void animateDeath(MutantZombieRenderState state) {
        if (state.deathTime <= 20.0F) {
            float progress = (state.deathTime - 1.0F) / 20.0F;
            float deathAmount = Mth.sin(progress * Mth.PI / 2.0F);
            this.pelvis.y += deathAmount * 28.0F;
            this.head.xRot -= deathAmount * Mth.PI / 10.0F;
            this.head.yRot += deathAmount * Mth.PI / 5.0F;
            this.chest.xRot -= deathAmount * Mth.PI / 12.0F;
            this.waist.xRot -= deathAmount * Mth.PI / 10.0F;
            this.rightArm.xRot -= deathAmount * Mth.PI / 2.0F;
            this.rightArm.yRot += deathAmount * Mth.PI / 2.8F;
            this.leftArm.xRot -= deathAmount * Mth.PI / 2.0F;
            this.leftArm.yRot -= deathAmount * Mth.PI / 2.8F;
            this.rightLeg.xRot += deathAmount * Mth.PI / 6.0F;
            this.rightLeg.zRot += deathAmount * Mth.PI / 12.0F;
            this.leftLeg.xRot += deathAmount * Mth.PI / 6.0F;
            this.leftLeg.zRot -= deathAmount * Mth.PI / 12.0F;
        } else if (state.deathTime <= 100.0F) {
            this.pelvis.y += 28.0F;
            this.head.xRot -= Mth.PI / 10.0F;
            this.head.yRot += Mth.PI / 5.0F;
            this.chest.xRot -= Mth.PI / 12.0F;
            this.waist.xRot -= Mth.PI / 10.0F;
            --this.rightArm.xRot;
            ++this.rightArm.yRot;
            --this.leftArm.xRot;
            --this.leftArm.yRot;
            this.rightLeg.xRot += Mth.PI / 6.0F;
            this.rightLeg.zRot += Mth.PI / 12.0F;
            this.leftLeg.xRot += Mth.PI / 6.0F;
            this.leftLeg.zRot -= Mth.PI / 12.0F;
        } else {
            float progress = (40.0F - (140.0F - state.deathTime)) / 40.0F;
            float deathAmount = Mth.cos(progress * Mth.PI / 2.0F);
            this.pelvis.y += deathAmount * 28.0F;
            this.head.xRot -= deathAmount * Mth.PI / 10.0F;
            this.head.yRot += deathAmount * Mth.PI / 5.0F;
            this.chest.xRot -= deathAmount * Mth.PI / 12.0F;
            this.waist.xRot -= deathAmount * Mth.PI / 10.0F;
            this.rightArm.xRot -= deathAmount * Mth.PI / 2.0F;
            this.rightArm.yRot += deathAmount * Mth.PI / 2.8F;
            this.leftArm.xRot -= deathAmount * Mth.PI / 2.0F;
            this.leftArm.yRot -= deathAmount * Mth.PI / 2.8F;
            this.rightLeg.xRot += deathAmount * Mth.PI / 6.0F;
            this.rightLeg.zRot += deathAmount * Mth.PI / 12.0F;
            this.leftLeg.xRot += deathAmount * Mth.PI / 6.0F;
            this.leftLeg.zRot -= deathAmount * Mth.PI / 12.0F;
        }
    }
}

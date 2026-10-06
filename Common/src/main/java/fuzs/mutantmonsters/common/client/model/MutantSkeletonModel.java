package fuzs.mutantmonsters.common.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.mutantmonsters.common.client.animation.Animator;
import fuzs.mutantmonsters.common.client.renderer.entity.state.MutantSkeletonRenderState;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantSkeleton;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class MutantSkeletonModel extends EntityModel<MutantSkeletonRenderState> {
    private static final String BASE = "base";
    public static final String PELVIS = "pelvis";
    private static final String INNER_HEAD = "inner_head";
    public static final String RIGHT_SHOULDER = "right_shoulder";
    public static final String LEFT_SHOULDER = "left_shoulder";
    private static final String RIGHT_ARM_INNER = "right_arm_inner";
    private static final String LEFT_ARM_INNER = "left_arm_inner";
    public static final String RIGHT_ARM_LOWER = "right_arm_lower";
    public static final String LEFT_ARM_LOWER = "left_arm_lower";
    private static final String RIGHT_ARM_LOWER_INNER = "right_arm_lower_inner";
    private static final String LEFT_ARM_LOWER_INNER = "left_arm_lower_inner";
    private static final String RIGHT_LEG_INNER = "right_leg_inner";
    private static final String LEFT_LEG_INNER = "left_leg_inner";
    public static final String RIGHT_LEG_LOWER = "right_leg_lower";
    public static final String LEFT_LEG_LOWER = "left_leg_lower";
    private static final String RIGHT_LEG_LOWER_INNER = "right_leg_lower_inner";
    private static final String LEFT_LEG_LOWER_INNER = "left_leg_lower_inner";

    private final Animator animator = new Animator();
    private final ModelPart base;
    private final ModelPart pelvis;
    private final ModelPart waist;
    private final Spine[] spine = new Spine[3];
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart innerHead;
    private final ModelPart jaw;
    private final ModelPart rightShoulder;
    private final ModelPart leftShoulder;
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
    private final ModelPart rightLegLower;
    private final ModelPart rightLegLowerInner;
    private final ModelPart leftLegLower;
    private final ModelPart leftLegLowerInner;

    public MutantSkeletonModel(ModelPart root) {
        super(root);
        this.base = root.getChild(BASE);
        this.pelvis = this.base.getChild(PELVIS);
        this.waist = this.pelvis.getChild(PartNames.WAIST);
        ModelPart middle = this.waist;
        for (int i = 0; i < 3; i++) {
            this.spine[i] = new Spine(middle, "" + (i + 1));
            middle = this.spine[i].middle;
        }

        this.neck = middle.getChild(PartNames.NECK);
        this.head = this.neck.getChild(PartNames.HEAD);
        this.innerHead = this.head.getChild(INNER_HEAD);
        this.jaw = this.innerHead.getChild(PartNames.JAW);
        this.rightShoulder = middle.getChild(RIGHT_SHOULDER);
        this.leftShoulder = middle.getChild(LEFT_SHOULDER);
        this.rightArm = this.rightShoulder.getChild(PartNames.RIGHT_ARM);
        this.rightArmInner = this.rightArm.getChild(RIGHT_ARM_INNER);
        this.leftArm = this.leftShoulder.getChild(PartNames.LEFT_ARM);
        this.leftArmInner = this.leftArm.getChild(LEFT_ARM_INNER);
        this.rightArmLower = this.rightArmInner.getChild(RIGHT_ARM_LOWER);
        this.rightArmLowerInner = this.rightArmLower.getChild(RIGHT_ARM_LOWER_INNER);
        this.leftArmLower = this.leftArmInner.getChild(LEFT_ARM_LOWER);
        this.leftArmLowerInner = this.leftArmLower.getChild(LEFT_ARM_LOWER_INNER);
        this.rightLeg = this.pelvis.getChild(PartNames.RIGHT_LEG);
        ModelPart rightLegInner = this.rightLeg.getChild(RIGHT_LEG_INNER);
        this.leftLeg = this.pelvis.getChild(PartNames.LEFT_LEG);
        ModelPart leftLegInner = this.leftLeg.getChild(LEFT_LEG_INNER);
        this.rightLegLower = rightLegInner.getChild(RIGHT_LEG_LOWER);
        this.rightLegLowerInner = this.rightLegLower.getChild(RIGHT_LEG_LOWER_INNER);
        this.leftLegLower = leftLegInner.getChild(LEFT_LEG_LOWER);
        this.leftLegLowerInner = this.leftLegLower.getChild(LEFT_LEG_LOWER_INNER);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition base = root.addOrReplaceChild(BASE,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offset(0.0F, 3.0F, 0.0F));
        PartDefinition pelvis = base.addOrReplaceChild(PELVIS,
                CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -6.0F, -3.0F, 8.0F, 6.0F, 6.0F),
                PartPose.rotation(-Mth.PI / 10.0F, 0.0F, 0.0F));
        PartDefinition middle = pelvis.addOrReplaceChild(PartNames.WAIST,
                CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -8.0F, -2.0F, 5.0F, 8.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, Mth.PI / 14.0F, 0.0F, 0.0F));
        for (int i = 0; i < 3; i++) {
            Spine.createSpineLayer(middle, i);
            middle = middle.getChild("middle" + (i + 1));
        }

        PartDefinition neck = middle.addOrReplaceChild(PartNames.NECK,
                CubeListBuilder.create().texOffs(64, 0).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -Mth.PI / 24.0F, 0.0F, 0.0F));
        PartDefinition head = neck.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offsetAndRotation(0.0F, -4.0F, -1.0F, -Mth.PI / 24.0F, 0.0F, 0.0F));
        PartDefinition innerHead = head.addOrReplaceChild(INNER_HEAD,
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.4F)),
                PartPose.ZERO);
        innerHead.addOrReplaceChild(PartNames.JAW,
                CubeListBuilder.create()
                        .texOffs(72, 0)
                        .addBox(-4.0F, -3.0F, -8.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.7F)),
                PartPose.offsetAndRotation(0.0F, -0.2F, 3.5F, Mth.PI / 32.0F, 0.0F, 0.0F));

        PartDefinition rightShoulder = middle.addOrReplaceChild(RIGHT_SHOULDER,
                CubeListBuilder.create().texOffs(28, 16).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 3.0F, 6.0F),
                PartPose.offsetAndRotation(-7.0F, -3.0F, -1.0F, -Mth.PI / 4.0F, 0.0F, 0.0F));
        PartDefinition leftShoulder = middle.addOrReplaceChild(LEFT_SHOULDER,
                CubeListBuilder.create().texOffs(28, 16).mirror().addBox(-4.0F, -3.0F, -3.0F, 8.0F, 3.0F, 6.0F),
                PartPose.offsetAndRotation(7.0F, -3.0F, -1.0F, -Mth.PI / 4.0F, 0.0F, 0.0F));
        PartDefinition rightArm = rightShoulder.addOrReplaceChild(PartNames.RIGHT_ARM,
                CubeListBuilder.create().texOffs(0, 28),
                PartPose.offset(-1.0F, -1.0F, 0.0F));
        PartDefinition rightArmInner = rightArm.addOrReplaceChild(RIGHT_ARM_INNER,
                CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.rotation(Mth.PI / 6.0F, 0.0F, Mth.PI / 10.0F));
        PartDefinition leftArm = leftShoulder.addOrReplaceChild(PartNames.LEFT_ARM,
                CubeListBuilder.create().texOffs(0, 28).mirror(),
                PartPose.offset(1.0F, -1.0F, 0.0F));
        PartDefinition leftArmInner = leftArm.addOrReplaceChild(LEFT_ARM_INNER,
                CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.rotation(Mth.PI / 6.0F, 0.0F, -Mth.PI / 10.0F));
        PartDefinition rightArmLower = rightArmInner.addOrReplaceChild(RIGHT_ARM_LOWER,
                CubeListBuilder.create().texOffs(16, 28),
                PartPose.offset(0.0F, 11.0F, 0.0F));
        rightArmLower.addOrReplaceChild(RIGHT_ARM_LOWER_INNER,
                CubeListBuilder.create()
                        .texOffs(16, 28)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.01F)),
                PartPose.rotation(-Mth.PI / 6.0F, 0.0F, 0.0F));
        PartDefinition leftArmLower = leftArmInner.addOrReplaceChild(LEFT_ARM_LOWER,
                CubeListBuilder.create().texOffs(16, 28).mirror(),
                PartPose.offset(0.0F, 11.0F, 0.0F));
        leftArmLower.addOrReplaceChild(LEFT_ARM_LOWER_INNER,
                CubeListBuilder.create()
                        .texOffs(16, 28)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.01F)),
                PartPose.rotation(-Mth.PI / 6.0F, 0.0F, 0.0F));

        PartDefinition rightLeg = pelvis.addOrReplaceChild(PartNames.RIGHT_LEG,
                CubeListBuilder.create().texOffs(0, 28),
                PartPose.offsetAndRotation(-2.5F, -2.5F, 0.0F, Mth.PI / 60.0F, 0.0F, Mth.PI / 16.0F));
        PartDefinition rightLegInner = rightLeg.addOrReplaceChild(RIGHT_LEG_INNER,
                CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        PartDefinition leftLeg = pelvis.addOrReplaceChild(PartNames.LEFT_LEG,
                CubeListBuilder.create().texOffs(0, 28).mirror(),
                PartPose.offsetAndRotation(2.5F, -2.5F, 0.0F, Mth.PI / 60.0F, 0.0F, -Mth.PI / 16.0F));
        PartDefinition leftLegInner = leftLeg.addOrReplaceChild(LEFT_LEG_INNER,
                CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        PartDefinition rightLegLower = rightLegInner.addOrReplaceChild(RIGHT_LEG_LOWER,
                CubeListBuilder.create().texOffs(32, 28),
                PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -Mth.PI / 24.0F));
        rightLegLower.addOrReplaceChild(RIGHT_LEG_LOWER_INNER,
                CubeListBuilder.create().texOffs(32, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.rotation(Mth.PI / 10.0F, 0.0F, 0.0F));
        PartDefinition leftLegLower = leftLegInner.addOrReplaceChild(LEFT_LEG_LOWER,
                CubeListBuilder.create().texOffs(32, 28).mirror(),
                PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, Mth.PI / 24.0F));
        leftLegLower.addOrReplaceChild(LEFT_LEG_LOWER_INNER,
                CubeListBuilder.create().texOffs(32, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.rotation(Mth.PI / 10.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(MutantSkeletonRenderState state) {
        super.setupAnim(state);
        this.animator.update(state);
        float animationPos = state.walkAnimationPos;
        float animationSpeed = state.walkAnimationSpeed;
        float walkAnim = Mth.sin(animationPos * 0.5F);
        float lowerLegWalk = Mth.sin(animationPos * 0.5F - 1.1F);
        float breatheAnim = Mth.sin(state.ageInTicks * 0.1F);
        float faceYaw = state.yRot * Mth.PI / 180.0F;
        float facePitch = state.xRot * Mth.PI / 180.0F;
        if (state.animation == MutantSkeleton.MELEE_ANIMATION) {
            this.animateMelee(state);
            float scale = 1.0F - Mth.clamp(state.animationTime / 4.0F, 0.0F, 1.0F);
            walkAnim *= scale;
            lowerLegWalk *= scale;
        } else if (state.animation == MutantSkeleton.SHOOT_ANIMATION) {
            this.animateShoot(state, facePitch, faceYaw);
            float scale = 1.0F - Mth.clamp(state.animationTime / 4.0F, 0.0F, 1.0F);
            walkAnim *= scale;
            lowerLegWalk *= scale;
            facePitch *= scale;
            faceYaw *= scale;
        } else if (state.animation == MutantSkeleton.MULTI_SHOT_ANIMATION) {
            this.animateMultiShoot(state, facePitch, faceYaw);
            float scale = 1.0F - Mth.clamp(state.animationTime / 4.0F, 0.0F, 1.0F);
            walkAnim *= scale;
            lowerLegWalk *= scale;
            facePitch *= scale;
            faceYaw *= scale;
        } else if (this.animator.setAnimation(MutantSkeleton.CONSTRICT_RIBS_ANIMATION)) {
            this.animateConstrict(state);
            float scale = 1.0F - Mth.clamp(state.animationTime / 6.0F, 0.0F, 1.0F);
            facePitch *= scale;
            faceYaw *= scale;
        }

        this.base.y -= (-0.5F + Math.abs(walkAnim)) * animationSpeed;
        this.spine[0].middle.yRot -= walkAnim * 0.06F * animationSpeed;
        this.rightArm.xRot -= walkAnim * 0.9F * animationSpeed;
        this.leftArm.xRot += walkAnim * 0.9F * animationSpeed;
        this.rightLeg.xRot += (0.2F + walkAnim) * 1.0F * animationSpeed;
        this.leftLeg.xRot -= (-0.2F + walkAnim) * 1.0F * animationSpeed;
        this.rightLegLowerInner.xRot += (0.6F + lowerLegWalk) * 0.6F * animationSpeed;
        this.leftLegLowerInner.xRot -= (-0.6F + lowerLegWalk) * 0.6F * animationSpeed;
        for (Spine spine : this.spine) {
            spine.animate(breatheAnim);
        }

        this.head.xRot -= breatheAnim * 0.02F;
        this.jaw.xRot += breatheAnim * 0.04F + 0.04F;
        this.rightArm.zRot += breatheAnim * 0.025F;
        this.leftArm.zRot -= breatheAnim * 0.025F;
        this.innerHead.xRot += facePitch;
        this.innerHead.yRot += faceYaw;
    }

    private void animateMelee(MutantSkeletonRenderState state) {
        boolean leftHanded = state.mainArm == HumanoidArm.LEFT;
        ModelPart meleeArm = leftHanded ? this.leftArm : this.rightArm;
        ModelPart offArm = leftHanded ? this.rightArm : this.leftArm;
        int offsetMultiplier = leftHanded ? -1 : 1;
        if (state.animationTime < 3.0F) {
            float animationProgress = state.animationTime / 3.0F;
            float rotationAmount = Mth.sin(animationProgress * Mth.PI / 2.0F);
            for (Spine spine : this.spine) {
                spine.middle.yRot += rotationAmount * Mth.PI / 16.0F * (float) offsetMultiplier;
            }

            meleeArm.yRot += rotationAmount * Mth.PI / 10.0F * (float) offsetMultiplier;
            meleeArm.zRot += rotationAmount * Mth.PI / 4.0F * (float) offsetMultiplier;
            offArm.zRot += rotationAmount * -Mth.PI / 16.0F * (float) offsetMultiplier;
        } else if (state.animationTime < 5.0F) {
            float animationProgress = (state.animationTime - 3.0F) / 2.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.PI / 2.0F);
            for (Spine spine : this.spine) {
                spine.middle.yRot +=
                        (rotationAmount * 3.0F * Mth.PI / 16.0F - Mth.PI / 8.0F) * (float) offsetMultiplier;
            }

            meleeArm.yRot += (rotationAmount * 2.7307692F - 2.41661F) * (float) offsetMultiplier;
            meleeArm.zRot += (rotationAmount * 3.0F * Mth.PI / 8.0F - Mth.PI / 8.0F) * (float) offsetMultiplier;
            offArm.zRot += -Mth.PI / 16.0F * (float) offsetMultiplier;
        } else if (state.animationTime < 8.0F) {
            for (Spine spine : this.spine) {
                spine.middle.yRot += -Mth.PI / 8.0F * (float) offsetMultiplier;
            }

            meleeArm.yRot += -2.41661F * (float) offsetMultiplier;
            meleeArm.zRot += -Mth.PI / 8.0F * (float) offsetMultiplier;
            offArm.zRot += -Mth.PI / 16.0F * (float) offsetMultiplier;
        } else if (state.animationTime < 14.0F) {
            float animationProgress = (state.animationTime - 8.0F) / 6.0F;
            float rotationAmount = Mth.cos(animationProgress * Mth.PI / 2.0F);
            for (Spine spine : this.spine) {
                spine.middle.yRot += rotationAmount * -Mth.PI / 8.0F * (float) offsetMultiplier;
            }

            meleeArm.yRot += rotationAmount * -Mth.PI / 1.3F * (float) offsetMultiplier;
            meleeArm.zRot += rotationAmount * -Mth.PI / 8.0F * (float) offsetMultiplier;
            offArm.zRot += rotationAmount * -Mth.PI / 16.0F * (float) offsetMultiplier;
        }
    }

    private void animateShoot(MutantSkeletonRenderState state, float facePitch, float faceYaw) {
        boolean leftHanded = state.mainArm == HumanoidArm.LEFT;
        ModelPart drawingArm = leftHanded ? this.leftArm : this.rightArm;
        ModelPart holdingArm = leftHanded ? this.rightArm : this.leftArm;
        ModelPart innerDrawingArm = leftHanded ? this.leftArmInner : this.rightArmInner;
        ModelPart innerHoldingArm = leftHanded ? this.rightArmInner : this.leftArmInner;
        ModelPart drawingForearm = leftHanded ? this.leftArmLower : this.rightArmLower;
        ModelPart holdingforearm = leftHanded ? this.rightArmLower : this.leftArmLower;
        int offset = leftHanded ? -1 : 1;
        if (state.animationTime < 5.0F) {
            float progress = state.animationTime / 5.0F;
            float drawAmount = Mth.sin(progress * Mth.PI / 2.0F);
            innerDrawingArm.xRot += -drawAmount * Mth.PI / 4.0F;
            drawingArm.yRot += -drawAmount * Mth.PI / 2.0F * (float) offset;
            drawingArm.zRot += drawAmount * Mth.PI / 16.0F * (float) offset;
            drawingForearm.xRot += drawAmount * Mth.PI / 7.0F;
            innerHoldingArm.xRot += -drawAmount * Mth.PI / 4.0F;
            holdingArm.yRot += drawAmount * Mth.PI / 2.0F * (float) offset;
            holdingArm.zRot += -drawAmount * Mth.PI / 16.0F * (float) offset;
            innerHoldingArm.zRot += -drawAmount * Mth.PI / 8.0F * (float) offset;
            holdingforearm.xRot += -drawAmount * Mth.PI / 6.0F;
        } else if (state.animationTime < 12.0F) {
            float progress = (state.animationTime - 5.0F) / 7.0F;
            float drawAmount = Mth.cos(progress * Mth.PI / 2.0F);
            float turnAmount = Mth.sin(progress * Mth.PI / 2.0F);
            this.innerHead.yRot += turnAmount * Mth.PI / 4.0F * (float) offset;
            for (Spine spine : this.spine) {
                spine.middle.yRot += -turnAmount * Mth.PI / 12.0F * (float) offset;
                spine.middle.xRot += turnAmount * facePitch / 3.0F;
                spine.middle.yRot += turnAmount * faceYaw / 3.0F;
            }

            innerDrawingArm.xRot += drawAmount * Mth.PI / 12.0F - Mth.PI / 3.0F;
            drawingArm.yRot += (drawAmount * -3.0F * Mth.PI / 10.0F - Mth.PI / 5.0F) * (float) offset;
            drawingArm.zRot += (drawAmount * -0.850848F + Mth.PI / 3.0F) * (float) offset;
            drawingForearm.xRot += Mth.PI / 7.0F;
            innerHoldingArm.xRot += drawAmount * 7.0F * Mth.PI / 12.0F - 5.0F * Mth.PI / 6.0F;
            holdingArm.yRot += (drawAmount * 3.0F * Mth.PI / 10.0F + Mth.PI / 5.0F) * (float) offset;
            holdingArm.zRot += (drawAmount * 0.850848F - Mth.PI / 3.0F) * (float) offset;
            innerHoldingArm.zRot += -drawAmount * Mth.PI / 8.0F * (float) offset;
            holdingforearm.xRot += drawAmount * Mth.PI / 30.0F - Mth.PI / 5.0F;
        } else if (state.animationTime < 26.0F) {
            this.innerHead.yRot += Mth.PI / 4.0F * (float) offset;
            for (Spine spine : this.spine) {
                spine.middle.yRot += -Mth.PI / 12.0F * (float) offset;
                spine.middle.xRot += facePitch / 3.0F;
                spine.middle.yRot += faceYaw / 3.0F;
            }

            innerDrawingArm.xRot += -Mth.PI / 3.0F;
            drawingArm.yRot += -Mth.PI / 5.0F * (float) offset;
            drawingArm.zRot += Mth.PI / 3.0F * (float) offset;
            drawingForearm.xRot += Mth.PI / 7.0F;
            innerHoldingArm.xRot += -5.0F * Mth.PI / 6.0F;
            holdingArm.yRot += Mth.PI / 5.0F * (float) offset;
            holdingArm.zRot += -Mth.PI / 3.0F * (float) offset;
            holdingforearm.xRot += -Mth.PI / 5.0F;
        } else if (state.animationTime < 30.0F) {
            float progress = (state.animationTime - 26.0F) / 4.0F;
            float drawAmount = Mth.cos(progress * Mth.PI / 2.0F);
            this.innerHead.yRot += drawAmount * Mth.PI / 4.0F * (float) offset;

            for (Spine spine : this.spine) {
                spine.middle.yRot += -drawAmount * Mth.PI / 12.0F * (float) offset;
                spine.middle.xRot += drawAmount * facePitch / 3.0F;
                spine.middle.yRot += drawAmount * faceYaw / 3.0F;
            }

            innerDrawingArm.xRot += -drawAmount * Mth.PI / 3.0F;
            drawingArm.yRot += -drawAmount * Mth.PI / 5.0F * (float) offset;
            drawingArm.zRot += drawAmount * Mth.PI / 3.0F * (float) offset;
            drawingForearm.xRot += drawAmount * Mth.PI / 7.0F;
            innerHoldingArm.xRot += -drawAmount * Mth.PI / 1.2F;
            holdingArm.yRot += drawAmount * Mth.PI / 5.0F * (float) offset;
            holdingArm.zRot += -drawAmount * Mth.PI / 3.0F * (float) offset;
            holdingforearm.xRot += -drawAmount * Mth.PI / 5.0F;
        }
    }

    private void animateMultiShoot(MutantSkeletonRenderState state, float facePitch, float faceYaw) {
        boolean leftHanded = state.mainArm == HumanoidArm.LEFT;
        if (state.animationTime < 10.0F) {
            float progress = state.animationTime / 10.0F;
            float drawAmount = Mth.sin(progress * Mth.PI / 2.0F);
            this.base.y += drawAmount * 3.5F;
            this.spine[0].middle.xRot += drawAmount * Mth.PI / 6.0F;
            this.head.xRot += -drawAmount * Mth.PI / 4.0F;
            this.rightArm.xRot += drawAmount * Mth.PI / 6.0F;
            this.rightArm.zRot += drawAmount * Mth.PI / 16.0F;
            this.leftArm.xRot += drawAmount * Mth.PI / 6.0F;
            this.leftArm.zRot += -drawAmount * Mth.PI / 16.0F;
            this.rightLeg.xRot += -drawAmount * Mth.PI / 8.0F;
            this.leftLeg.xRot += -drawAmount * Mth.PI / 8.0F;
            this.rightLegLowerInner.xRot += drawAmount * Mth.PI / 4.0F;
            this.leftLegLowerInner.xRot += drawAmount * Mth.PI / 4.0F;
        } else {
            ModelPart drawingArm = leftHanded ? this.leftArm : this.rightArm;
            ModelPart holdingArm = leftHanded ? this.rightArm : this.leftArm;
            ModelPart innerDrawingArm = leftHanded ? this.leftArmInner : this.rightArmInner;
            ModelPart innerHoldingArm = leftHanded ? this.rightArmInner : this.leftArmInner;
            ModelPart drawingForearm = leftHanded ? this.leftArmLower : this.rightArmLower;
            ModelPart holdingforearm = leftHanded ? this.rightArmLower : this.leftArmLower;
            int offset = leftHanded ? -1 : 1;
            if (state.animationTime < 12.0F) {
                float progress = (state.animationTime - 10.0F) / 2.0F;
                float drawAmount = Mth.cos(progress * Mth.PI / 2.0F);
                float turnAmount = Mth.sin(progress * Mth.PI / 2.0F);
                this.base.y += drawAmount * 3.5F;
                this.spine[0].middle.xRot += drawAmount * Mth.PI / 6.0F;
                this.head.xRot += -drawAmount * Mth.PI / 4.0F;
                drawingArm.xRot += drawAmount * Mth.PI / 6.0F;
                drawingArm.zRot += drawAmount * Mth.PI / 16.0F * (float) offset;
                holdingArm.xRot += drawAmount * Mth.PI / 6.0F;
                holdingArm.zRot += -drawAmount * Mth.PI / 16.0F * (float) offset;
                this.rightLeg.xRot += -drawAmount * Mth.PI / 8.0F;
                this.leftLeg.xRot += -drawAmount * Mth.PI / 8.0F;
                this.rightLegLowerInner.xRot += drawAmount * Mth.PI / 4.0F;
                this.leftLegLowerInner.xRot += drawAmount * Mth.PI / 4.0F;
                drawingArm.zRot += -turnAmount * Mth.PI / 14.0F * (float) offset;
                holdingArm.zRot += turnAmount * Mth.PI / 14.0F * (float) offset;
                this.rightLeg.zRot += -turnAmount * Mth.PI / 24.0F;
                this.leftLeg.zRot += turnAmount * Mth.PI / 24.0F;
                this.rightLegLower.zRot += turnAmount * Mth.PI / 64.0F;
                this.leftLegLower.zRot += -turnAmount * Mth.PI / 64.0F;
            } else if (state.animationTime < 14.0F) {
                drawingArm.zRot += -Mth.PI / 14.0F * (float) offset;
                holdingArm.zRot += Mth.PI / 14.0F * (float) offset;
                this.rightLeg.zRot += -Mth.PI / 24.0F;
                this.leftLeg.zRot += Mth.PI / 24.0F;
                this.rightLegLower.zRot += Mth.PI / 64.0F;
                this.leftLegLower.zRot += -Mth.PI / 64.0F;
            } else if (state.animationTime < 17.0F) {
                float progress = (state.animationTime - 14.0F) / 3.0F;
                float drawAmount = Mth.sin(progress * Mth.PI / 2.0F);
                float turnAmount = Mth.cos(progress * Mth.PI / 2.0F);
                drawingArm.zRot += -turnAmount * Mth.PI / 14.0F * (float) offset;
                holdingArm.zRot += turnAmount * Mth.PI / 14.0F * (float) offset;
                this.rightLeg.zRot += -turnAmount * Mth.PI / 24.0F;
                this.leftLeg.zRot += turnAmount * Mth.PI / 24.0F;
                this.rightLegLower.zRot += turnAmount * Mth.PI / 64.0F;
                this.leftLegLower.zRot += -turnAmount * Mth.PI / 64.0F;
                innerDrawingArm.xRot += -drawAmount * Mth.PI / 4.0F;
                drawingArm.yRot += -drawAmount * Mth.PI / 2.0F * (float) offset;
                drawingArm.zRot += drawAmount * Mth.PI / 16.0F * (float) offset;
                drawingForearm.xRot += drawAmount * Mth.PI / 7.0F;
                innerHoldingArm.xRot += -drawAmount * Mth.PI / 4.0F;
                holdingArm.yRot += drawAmount * Mth.PI / 2.0F * (float) offset;
                holdingArm.zRot += -drawAmount * Mth.PI / 16.0F * (float) offset;
                innerHoldingArm.zRot += -drawAmount * Mth.PI / 8.0F * (float) offset;
                holdingforearm.xRot += -drawAmount * Mth.PI / 6.0F;
            } else if (state.animationTime < 20.0F) {
                float progress = (state.animationTime - 17.0F) / 3.0F;
                float drawAmount = Mth.cos(progress * Mth.PI / 2.0F);
                float turnAmount = Mth.sin(progress * Mth.PI / 2.0F);
                this.innerHead.yRot += turnAmount * Mth.PI / 4.0F * (float) offset;

                for (Spine spine : this.spine) {
                    spine.middle.yRot += -turnAmount * Mth.PI / 12.0F * (float) offset;
                    spine.middle.xRot += turnAmount * facePitch / 3.0F;
                    spine.middle.yRot += turnAmount * faceYaw / 3.0F;
                }

                innerDrawingArm.xRot += drawAmount * Mth.PI / 12.0F - Mth.PI / 3.0F;
                drawingArm.yRot += (drawAmount * -3.0F * Mth.PI / 10.0F - Mth.PI / 5.0F) * (float) offset;
                drawingArm.zRot += (drawAmount * -0.850848F + Mth.PI / 3.0F) * (float) offset;
                drawingForearm.xRot += Mth.PI / 7.0F;
                innerHoldingArm.xRot += drawAmount * 7.0F * Mth.PI / 12.0F - 5.0F * Mth.PI / 6.0F;
                holdingArm.yRot += (drawAmount * 3.0F * Mth.PI / 10.0F + Mth.PI / 5.0F) * (float) offset;
                holdingArm.zRot += (drawAmount * 0.850848F - Mth.PI / 3.0F) * (float) offset;
                innerHoldingArm.zRot += -drawAmount * Mth.PI / 8.0F * (float) offset;
                holdingforearm.xRot += drawAmount * Mth.PI / 30.0F - Mth.PI / 5.0F;
            } else if (state.animationTime < 24.0F) {
                this.innerHead.yRot += Mth.PI / 4.0F * (float) offset;

                for (Spine spine : this.spine) {
                    spine.middle.yRot += -Mth.PI / 12.0F * (float) offset;
                    spine.middle.xRot += facePitch / 3.0F;
                    spine.middle.yRot += faceYaw / 3.0F;
                }

                innerDrawingArm.xRot += -Mth.PI / 3.0F;
                drawingArm.yRot += -Mth.PI / 5.0F * (float) offset;
                drawingArm.zRot += Mth.PI / 3.0F * (float) offset;
                drawingForearm.xRot += Mth.PI / 7.0F;
                innerHoldingArm.xRot += -5.0F * Mth.PI / 6.0F;
                holdingArm.yRot += Mth.PI / 5.0F * (float) offset;
                holdingArm.zRot += -Mth.PI / 3.0F * (float) offset;
                holdingforearm.xRot += -Mth.PI / 5.0F;
            } else if (state.animationTime < 28.0F) {
                float progress = (state.animationTime - 24.0F) / 4.0F;
                float drawAmount = Mth.cos(progress * Mth.PI / 2.0F);
                this.innerHead.yRot += drawAmount * Mth.PI / 4.0F * (float) offset;

                for (Spine spine : this.spine) {
                    spine.middle.yRot += -drawAmount * Mth.PI / 12.0F * (float) offset;
                    spine.middle.xRot += drawAmount * facePitch / 3.0F;
                    spine.middle.yRot += drawAmount * faceYaw / 3.0F;
                }

                innerDrawingArm.xRot += -drawAmount * Mth.PI / 3.0F;
                drawingArm.yRot += -drawAmount * Mth.PI / 5.0F * (float) offset;
                drawingArm.zRot += drawAmount * Mth.PI / 3.0F * (float) offset;
                drawingForearm.xRot += drawAmount * Mth.PI / 7.0F;
                innerHoldingArm.xRot += -drawAmount * Mth.PI / 1.2F;
                holdingArm.yRot += drawAmount * Mth.PI / 5.0F * (float) offset;
                holdingArm.zRot += -drawAmount * Mth.PI / 3.0F * (float) offset;
                holdingforearm.xRot += -drawAmount * Mth.PI / 5.0F;
            }
        }
    }

    private void animateConstrict(MutantSkeletonRenderState state) {
        this.animator.startPhase(5);
        this.animator.rotate(this.waist, Mth.PI / 24.0F, 0.0F, 0.0F);

        for (int i = 0; i < this.spine.length; ++i) {
            float ribXRot = i == 0 ? Mth.PI / 8.0F : (i == 2 ? -Mth.PI / 8.0F : 0.0F);
            float ribYRot = i == 1 ? Mth.PI / 8.0F : Mth.PI / 10.0F;
            this.animator.rotate(this.spine[i].side1[0], ribXRot, ribYRot, 0.0F);
            this.animator.rotate(this.spine[i].side1[1], 0.0F, Mth.PI / 20.0F, 0.0F);
            this.animator.rotate(this.spine[i].side1[2], 0.0F, Mth.PI / 12.0F, 0.0F);
            this.animator.rotate(this.spine[i].side2[0], ribXRot, -ribYRot, 0.0F);
            this.animator.rotate(this.spine[i].side2[1], 0.0F, -Mth.PI / 20.0F, 0.0F);
            this.animator.rotate(this.spine[i].side2[2], 0.0F, -Mth.PI / 12.0F, 0.0F);
        }

        this.animator.rotate(this.rightArm, 0.0F, 0.0F, Mth.TWO_PI / 7.0F);
        this.animator.rotate(this.leftArm, 0.0F, 0.0F, -Mth.TWO_PI / 7.0F);
        this.animator.move(this.base, 0.0F, 1.0F, 0.0F);
        this.animator.rotate(this.rightLeg, -Mth.PI / 7.0F, 0.0F, 0.0F);
        this.animator.rotate(this.leftLeg, -Mth.PI / 7.0F, 0.0F, 0.0F);
        this.animator.rotate(this.rightLegLowerInner, Mth.PI / 6.0F, 0.0F, 0.0F);
        this.animator.rotate(this.leftLegLowerInner, Mth.PI / 6.0F, 0.0F, 0.0F);
        this.animator.endPhase();
        this.animator.setStationaryPhase(2);
        this.animator.startPhase(1);
        this.animator.rotate(this.neck, Mth.PI / 16.0F, 0.0F, 0.0F);
        this.animator.rotate(this.head, Mth.PI / 20.0F, 0.0F, 0.0F);
        this.animator.rotate(this.waist, Mth.PI / 10.0F, 0.0F, 0.0F);
        this.animator.rotate(this.spine[0].middle, Mth.PI / 12.0F, 0.0F, 0.0F);

        for (int i = 0; i < this.spine.length; ++i) {
            float ribXRot = i == 0 ? Mth.PI / 24.0F : (i == 2 ? -Mth.PI / 24.0F : 0.0F);
            float ribYRot = i == 1 ? -Mth.PI / 18.0F : -Mth.PI / 14.0F;
            this.animator.rotate(this.spine[i].side1[0], ribXRot - 0.08F, ribYRot, 0.0F);
            this.animator.rotate(this.spine[i].side1[1], 0.0F, Mth.PI / 20.0F, 0.0F);
            this.animator.rotate(this.spine[i].side1[2], 0.0F, Mth.PI / 12.0F, 0.0F);
            this.animator.rotate(this.spine[i].side2[0], ribXRot + 0.08F, -ribYRot, 0.0F);
            this.animator.rotate(this.spine[i].side2[1], 0.0F, -Mth.PI / 20.0F, 0.0F);
            this.animator.rotate(this.spine[i].side2[2], 0.0F, -Mth.PI / 12.0F, 0.0F);
        }

        this.animator.move(this.base, 0.0F, 1.0F, 0.0F);
        this.animator.rotate(this.rightLeg, -Mth.PI / 7.0F, 0.0F, 0.0F);
        this.animator.rotate(this.leftLeg, -Mth.PI / 7.0F, 0.0F, 0.0F);
        this.animator.rotate(this.rightLegLowerInner, Mth.PI / 6.0F, 0.0F, 0.0F);
        this.animator.rotate(this.leftLegLowerInner, Mth.PI / 6.0F, 0.0F, 0.0F);
        this.animator.endPhase();
        this.animator.setStationaryPhase(4);
        this.animator.resetPhase(8);

        if (state.animationTime < 5.0F) {
            float progress = state.animationTime / 5.0F;
            float swellAmount = Mth.sin(progress * Mth.PI / 2.0F);

            for (Spine spine : this.spine) {
                Animator.setScale(spine.side1[0], 1.0F + swellAmount * 0.6F);
                Animator.setScale(spine.side2[0], 1.0F + swellAmount * 0.6F);
            }
        } else if (state.animationTime < 12.0F) {

            for (Spine spine : this.spine) {
                Animator.setScale(spine.side1[0], 1.6F);
                Animator.setScale(spine.side2[0], 1.6F);
            }
        } else if (state.animationTime < 20) {
            float progress = (state.animationTime - 12.0F) / 8.0F;
            float swellAmount = Mth.cos(progress * Mth.PI / 2.0F);

            for (Spine spine : this.spine) {
                Animator.setScale(spine.side1[0], 1.0F + swellAmount * 0.6F);
                Animator.setScale(spine.side2[0], 1.0F + swellAmount * 0.6F);
            }
        }
    }

    public void translateHand(boolean leftHanded, PoseStack poseStack) {
        this.base.translateAndRotate(poseStack);
        this.pelvis.translateAndRotate(poseStack);
        this.waist.translateAndRotate(poseStack);

        for (Spine spine : this.spine) {
            spine.middle.translateAndRotate(poseStack);
        }

        if (leftHanded) {
            this.leftShoulder.translateAndRotate(poseStack);
            this.leftArm.translateAndRotate(poseStack);
            this.leftArmInner.translateAndRotate(poseStack);
            this.leftArmLower.translateAndRotate(poseStack);
            this.leftArmLowerInner.translateAndRotate(poseStack);
        } else {
            this.rightShoulder.translateAndRotate(poseStack);
            this.rightArm.translateAndRotate(poseStack);
            this.rightArmInner.translateAndRotate(poseStack);
            this.rightArmLower.translateAndRotate(poseStack);
            this.rightArmLowerInner.translateAndRotate(poseStack);
        }
    }

    public static class Spine extends Model<Boolean> {
        public final ModelPart middle;
        public final ModelPart[] side1 = new ModelPart[3];
        public final ModelPart[] side2 = new ModelPart[3];

        public Spine(ModelPart modelPart) {
            this(modelPart, "");
        }

        public Spine(ModelPart modelPart, String index) {
            super(modelPart.getChild("middle" + index), RenderTypes::entityCutout);
            this.middle = this.root;
            modelPart = this.root;
            for (int i = 0; i < 3; i++) {
                modelPart = this.side1[i] = modelPart.getChild("side1" + (i + 1) + index);
            }

            modelPart = this.root;
            for (int i = 0; i < 3; i++) {
                modelPart = this.side2[i] = modelPart.getChild("side2" + (i + 1) + index);
            }
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDefinition = new MeshDefinition();
            PartDefinition root = meshDefinition.getRoot();
            MutantSkeletonModel.Spine.createSpineLayer(root, -1);
            return LayerDefinition.create(meshDefinition, 128, 128);
        }

        public static void createSpineLayer(PartDefinition root, int index) {
            float spineScale = index == 1 ? 0.98F : 1.0F;
            PartPose partPose = PartPose.rotation(Mth.PI / 18.0F, 0.0F, 0.0F);
            if (index == 0) {
                partPose = PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, Mth.PI / 18.0F, 0.0F, 0.0F);
            } else if (index > 0) {
                partPose = PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, Mth.PI / 18.0F, 0.0F, 0.0F);
            }

            boolean skeletonPart = index < 0;
            String indexString = skeletonPart ? "" : "" + (index + 1);
            PartDefinition middle = root.addOrReplaceChild("middle" + indexString,
                    CubeListBuilder.create()
                            .texOffs(50, 0)
                            .addBox(-2.5F, -4.0F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)),
                    partPose);
            partPose = !skeletonPart ?
                    PartPose.offsetAndRotation(-3.0F, -1.0F, 1.75F, 0.0F, -Mth.PI / 4.5F * spineScale, 0.0F) :
                    PartPose.rotation(0.0F, -Mth.PI / 4.5F * spineScale, 0.0F);
            PartDefinition side11 = middle.addOrReplaceChild("side11" + indexString,
                    CubeListBuilder.create()
                            .texOffs(32, 12)
                            .addBox(skeletonPart ? 0.0F : -6.0F,
                                    -2.0F,
                                    -2.0F,
                                    6.0F,
                                    2.0F,
                                    2.0F,
                                    new CubeDeformation(0.25F)),
                    partPose);
            PartDefinition side12 = side11.addOrReplaceChild("side12" + indexString,
                    CubeListBuilder.create()
                            .texOffs(32, 12)
                            .mirror()
                            .addBox(-6.0F, -2.0F, -2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.2F)),
                    PartPose.offsetAndRotation(skeletonPart ? -0.5F : -6.5F,
                            0.0F,
                            0.0F,
                            0.0F,
                            -Mth.PI / 3.0F * spineScale,
                            0.0F));
            side12.addOrReplaceChild("side13" + indexString,
                    CubeListBuilder.create()
                            .texOffs(32, 12)
                            .addBox(-6.0F, -2.0F, -2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.15F)),
                    PartPose.offsetAndRotation(-6.4F, 0.0F, 0.0F, 0.0F, -Mth.PI / 3.5F * spineScale, 0.0F));
            partPose = !skeletonPart ?
                    PartPose.offsetAndRotation(3.0F, -1.0F, 1.75F, 0.0F, Mth.PI / 4.5F * spineScale, 0.0F) :
                    PartPose.rotation(0.0F, Mth.PI / 4.5F * spineScale, 0.0F);
            PartDefinition side21 = middle.addOrReplaceChild("side21" + indexString,
                    CubeListBuilder.create()
                            .texOffs(32, 12)
                            .mirror()
                            .addBox(skeletonPart ? -6.0F : 0.0F,
                                    -2.0F,
                                    -2.0F,
                                    6.0F,
                                    2.0F,
                                    2.0F,
                                    new CubeDeformation(0.25F)),
                    partPose);
            PartDefinition side22 = side21.addOrReplaceChild("side22" + indexString,
                    CubeListBuilder.create()
                            .texOffs(32, 12)
                            .addBox(0.0F, -2.0F, -2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.2F)),
                    PartPose.offsetAndRotation(skeletonPart ? 0.5F : 6.5F,
                            0.0F,
                            0.0F,
                            0.0F,
                            Mth.PI / 3.0F * spineScale,
                            0.0F));
            side22.addOrReplaceChild("side23" + indexString,
                    CubeListBuilder.create()
                            .texOffs(32, 12)
                            .mirror()
                            .addBox(0.0F, -2.0F, -2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.15F)),
                    PartPose.offsetAndRotation(6.4F, 0.0F, 0.0F, 0.0F, Mth.PI / 3.5F * spineScale, 0.0F));
        }

        public void animate(float breatheAnim) {
            this.side1[1].yRot += breatheAnim * 0.02F;
            this.side2[1].yRot -= breatheAnim * 0.02F;
        }
    }
}

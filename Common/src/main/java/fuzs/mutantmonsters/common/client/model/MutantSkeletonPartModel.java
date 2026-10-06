package fuzs.mutantmonsters.common.client.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import fuzs.mutantmonsters.common.world.entity.MutantSkeletonBodyPart;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;

import java.util.Map;

public class MutantSkeletonPartModel extends Model<Unit> {
    private final Map<MutantSkeletonBodyPart.BodyPart, ModelPart> bodyParts;

    public MutantSkeletonPartModel(ModelPart modelPart, ModelPart spineModelPart) {
        super(modelPart, RenderTypes::entityCutout);
        ImmutableMap.Builder<MutantSkeletonBodyPart.BodyPart, ModelPart> builder = ImmutableMap.builder();
        builder.put(MutantSkeletonBodyPart.BodyPart.PELVIS, modelPart.getChild(MutantSkeletonModel.PELVIS));
        MutantSkeletonModel.Spine spine = new MutantSkeletonModel.Spine(spineModelPart);
        spine.resetPose();
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_UPPER_RIB, spine.side1[0]);
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_UPPER_RIB, spine.side2[0]);
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_MIDDLE_RIB, spine.side1[0]);
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_MIDDLE_RIB, spine.side2[0]);
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_LOWER_RIB, spine.side1[0]);
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_LOWER_RIB, spine.side2[0]);
        builder.put(MutantSkeletonBodyPart.BodyPart.HEAD, modelPart.getChild(PartNames.HEAD));
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_ARM, modelPart.getChild(PartNames.RIGHT_ARM));
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_ARM, modelPart.getChild(PartNames.LEFT_ARM));
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_FORE_ARM,
                modelPart.getChild(MutantSkeletonModel.RIGHT_ARM_LOWER));
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_FORE_ARM,
                modelPart.getChild(MutantSkeletonModel.LEFT_ARM_LOWER));
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_LEG, modelPart.getChild(PartNames.RIGHT_LEG));
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_LEG, modelPart.getChild(PartNames.LEFT_LEG));
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_FORE_LEG,
                modelPart.getChild(MutantSkeletonModel.RIGHT_LEG_LOWER));
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_FORE_LEG,
                modelPart.getChild(MutantSkeletonModel.LEFT_LEG_LOWER));
        builder.put(MutantSkeletonBodyPart.BodyPart.LEFT_SHOULDER,
                modelPart.getChild(MutantSkeletonModel.RIGHT_SHOULDER));
        builder.put(MutantSkeletonBodyPart.BodyPart.RIGHT_SHOULDER,
                modelPart.getChild(MutantSkeletonModel.LEFT_SHOULDER));
        this.bodyParts = Maps.immutableEnumMap(builder.build());
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(MutantSkeletonModel.PELVIS,
                CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 6.0F, 6.0F),
                PartPose.ZERO);
        PartDefinition head = root.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.4F)),
                PartPose.ZERO);
        head.addOrReplaceChild(PartNames.JAW,
                CubeListBuilder.create()
                        .texOffs(72, 0)
                        .addBox(-4.0F, -3.0F, -8.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.7F)),
                PartPose.offsetAndRotation(0.0F, 3.8F, 3.7F, Mth.PI / 32.0F, 0.0F, 0.0F));
        root.addOrReplaceChild(PartNames.RIGHT_ARM,
                CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        root.addOrReplaceChild(PartNames.LEFT_ARM,
                CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        root.addOrReplaceChild(MutantSkeletonModel.RIGHT_ARM_LOWER,
                CubeListBuilder.create()
                        .texOffs(16, 28)
                        .addBox(-2.0F, -7.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.01F)),
                PartPose.ZERO);
        root.addOrReplaceChild(MutantSkeletonModel.LEFT_ARM_LOWER,
                CubeListBuilder.create()
                        .texOffs(16, 28)
                        .mirror()
                        .addBox(-2.0F, -7.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.01F)),
                PartPose.ZERO);
        root.addOrReplaceChild(PartNames.RIGHT_LEG,
                CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        root.addOrReplaceChild(PartNames.LEFT_LEG,
                CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        root.addOrReplaceChild(MutantSkeletonModel.RIGHT_LEG_LOWER,
                CubeListBuilder.create().texOffs(32, 28).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        root.addOrReplaceChild(MutantSkeletonModel.LEFT_LEG_LOWER,
                CubeListBuilder.create().texOffs(32, 28).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.ZERO);
        root.addOrReplaceChild(MutantSkeletonModel.RIGHT_SHOULDER,
                CubeListBuilder.create().texOffs(28, 16).addBox(-4.0F, -1.5F, -3.0F, 8.0F, 3.0F, 6.0F),
                PartPose.ZERO);
        root.addOrReplaceChild(MutantSkeletonModel.LEFT_SHOULDER,
                CubeListBuilder.create().texOffs(28, 16).mirror().addBox(-4.0F, -1.5F, -3.0F, 8.0F, 3.0F, 6.0F),
                PartPose.ZERO);
        return LayerDefinition.create(mesh, 128, 128);
    }

    public ModelPart getBodyPart(MutantSkeletonBodyPart.BodyPart bodyPart) {
        return this.bodyParts.get(bodyPart);
    }
}

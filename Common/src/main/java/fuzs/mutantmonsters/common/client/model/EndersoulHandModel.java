package fuzs.mutantmonsters.common.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;

public class EndersoulHandModel extends Model<Unit> {
    private static final String HAND = "hand";
    private static final String FINGER = "finger";
    private static final String FORE_FINGER = "fore_finger";
    private static final String THUMB = "thumb";

    public EndersoulHandModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
    }

    public static LayerDefinition createBodyLayer(boolean right) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition hand = root.addOrReplaceChild(HAND,
                CubeListBuilder.create().texOffs(0, 0),
                PartPose.offsetAndRotation(0.0F, 17.5F, 0.0F, 0.0F, right ? -Mth.PI / 8.0F : Mth.PI / 8.0F, 0.0F));
        for (int i = 0; i < 3; ++i) {
            CubeListBuilder cubeListBuilder = CubeListBuilder.create()
                    .texOffs(i * 4, 0)
                    .addBox(-0.5F, 0.0F, -0.5F, 1.0F, i == 1 ? 6.0F : 5.0F, 1.0F, new CubeDeformation(0.6F))
                    .mirror(!right);
            PartPose partPose;
            if (i == 0) {
                partPose = PartPose.offsetAndRotation(right ? -0.5F : 0.5F, 0.0F, -1.0F, -Mth.PI / 12.0F, 0.0F, 0.0F);
            } else if (i == 1) {
                partPose = PartPose.offsetAndRotation(right ? -0.5F : 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, right ? Mth.PI / 18.0F : -Mth.PI / 18.0F);
            } else {
                partPose = PartPose.offsetAndRotation(right ? -0.5F : 0.5F, 0.0F, 1.0F, Mth.PI / 12.0F, 0.0F, 0.0F);
            }
            hand.addOrReplaceChild(FINGER + i, cubeListBuilder, partPose);
        }

        for (int i = 0; i < 3; ++i) {
            float foreFingerZRot = i == 1
                    ? (right ? -Mth.PI / 8.0F : Mth.PI / 8.0F)
                    : (right ? -Mth.PI / 12.0F : Mth.PI / 12.0F);
            hand.getChild(FINGER + i)
                    .addOrReplaceChild(FORE_FINGER + i,
                            CubeListBuilder.create()
                                    .texOffs(1 + i * 5, 0)
                                    .addBox(-0.5F,
                                            0.0F,
                                            -0.5F,
                                            1.0F,
                                            i == 1 ? 6.0F : 5.0F,
                                            1.0F,
                                            new CubeDeformation(0.6F - 0.01F))
                                    .mirror(!right),
                            PartPose.offsetAndRotation(0.0F, 0.5F + (float) (i == 1 ? 6 : 5), 0.0F, 0.0F, 0.0F, foreFingerZRot));
        }

        hand.addOrReplaceChild(THUMB,
                CubeListBuilder.create()
                        .texOffs(14, 0)
                        .addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.6F)),
                PartPose.offsetAndRotation(right ? 0.5F : -0.5F, 0.0F, -0.5F, -Mth.PI / 5.0F, 0.0F, right ? -Mth.PI / 8.0F : Mth.PI / 8.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }
}

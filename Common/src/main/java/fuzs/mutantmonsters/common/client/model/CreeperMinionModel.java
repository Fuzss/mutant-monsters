package fuzs.mutantmonsters.common.client.model;

import fuzs.mutantmonsters.common.client.renderer.entity.state.CreeperMinionRenderState;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.util.Mth;

import java.util.Set;

public class CreeperMinionModel extends CreeperModel {
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false, 9.0F, 0.0F, Set.of(PartNames.HEAD));

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public CreeperMinionModel(ModelPart root) {
        super(root);
        this.head = root.getChild(PartNames.HEAD);
        this.body = root.getChild(PartNames.BODY);
        this.rightHindLeg = root.getChild(PartNames.RIGHT_HIND_LEG);
        this.leftHindLeg = root.getChild(PartNames.LEFT_HIND_LEG);
        this.rightFrontLeg = root.getChild(PartNames.RIGHT_FRONT_LEG);
        this.leftFrontLeg = root.getChild(PartNames.LEFT_FRONT_LEG);
    }

    @Override
    public void setupAnim(CreeperRenderState state) {
        super.setupAnim(state);
        if (((CreeperMinionRenderState) state).inSittingPose) {
            this.head.y += 3.0F;
            this.body.y += 3.0F;
            this.rightHindLeg.y += 2.0F;
            this.rightHindLeg.z -= 1.0F;
            this.leftHindLeg.y += 2.0F;
            this.leftHindLeg.z -= 1.0F;
            this.rightFrontLeg.y += 2.0F;
            this.rightFrontLeg.z += 1.0F;
            this.leftFrontLeg.y += 2.0F;
            this.leftFrontLeg.z += 1.0F;
            this.rightHindLeg.xRot = Mth.HALF_PI;
            this.leftHindLeg.xRot = Mth.HALF_PI;
            this.rightFrontLeg.xRot = -Mth.HALF_PI;
            this.leftFrontLeg.xRot = -Mth.HALF_PI;
        }
    }
}

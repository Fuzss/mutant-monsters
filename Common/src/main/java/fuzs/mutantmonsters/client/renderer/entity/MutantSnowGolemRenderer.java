package fuzs.mutantmonsters.client.renderer.entity;

import fuzs.mutantmonsters.MutantMonsters;
import fuzs.mutantmonsters.client.model.MutantSnowGolemModel;
import fuzs.mutantmonsters.client.model.geom.ModModelLayers;
import fuzs.mutantmonsters.client.renderer.entity.layers.MutantSnowGolemHeldBlockLayer;
import fuzs.mutantmonsters.client.renderer.entity.layers.MutantSnowGolemJackOLanternLayer;
import fuzs.mutantmonsters.client.renderer.entity.state.MutantSnowGolemRenderState;
import fuzs.mutantmonsters.world.entity.mutant.MutantSnowGolem;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

public class MutantSnowGolemRenderer extends MobRenderer<MutantSnowGolem, MutantSnowGolemRenderState, MutantSnowGolemModel> {
    public static final Identifier TEXTURE_LOCATION = MutantMonsters.id(
            "textures/entity/mutant_snow_golem/mutant_snow_golem.png");
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    private final BlockModelResolver blockModelResolver;

    public MutantSnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new MutantSnowGolemModel(context.bakeLayer(ModModelLayers.MUTANT_SNOW_GOLEM)), 0.7F);
        this.addLayer(new MutantSnowGolemJackOLanternLayer(this, context.getModelSet()));
        this.addLayer(new MutantSnowGolemHeldBlockLayer(this));
        this.blockModelResolver = context.getBlockModelResolver();
    }

    @Override
    public void extractRenderState(MutantSnowGolem entity, MutantSnowGolemRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        ArmedEntityRenderState.extractArmedEntityRenderState(entity, state, this.itemModelResolver, partialTick);
        if (entity.isThrowing()) {
            this.blockModelResolver.update(state.throwingBlock, Blocks.ICE.defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
        } else {
            state.throwingBlock.clear();
        }

        state.throwingTime = entity.getThrowingTick() > 0 ? entity.getThrowingTick() + partialTick : 0;
        state.hasJackOLantern = entity.hasJackOLantern();
    }

    @Override
    public MutantSnowGolemRenderState createRenderState() {
        return new MutantSnowGolemRenderState();
    }

    @Override
    public Identifier getTextureLocation(MutantSnowGolemRenderState state) {
        return TEXTURE_LOCATION;
    }
}

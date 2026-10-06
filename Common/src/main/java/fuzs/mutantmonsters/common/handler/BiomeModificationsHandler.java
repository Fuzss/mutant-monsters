package fuzs.mutantmonsters.common.handler;

import fuzs.mutantmonsters.common.MutantMonsters;
import fuzs.mutantmonsters.common.config.CommonConfig;
import fuzs.mutantmonsters.common.init.ModEntityTypes;
import fuzs.mutantmonsters.common.init.ModTags;
import fuzs.puzzleslib.common.api.biome.v2.BiomeLoadingPhase;
import fuzs.puzzleslib.common.api.biome.v2.BiomeTransformer;
import fuzs.puzzleslib.common.api.biome.v2.SpawnerDataBuilder;
import fuzs.puzzleslib.common.api.core.v1.context.BiomeTransformationsContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import org.apache.commons.lang3.math.Fraction;

import java.util.function.Supplier;

public final class BiomeModificationsHandler {

    private BiomeModificationsHandler() {
        // NO-OP
    }

    public static void onRegisterBiomeTransformations(BiomeTransformationsContext context) {
        registerMutantSpawn(context,
                ModTags.WITHOUT_MUTANT_CREEPER_SPAWNS_BIOME_TAG,
                () -> MutantMonsters.CONFIG.get(CommonConfig.class).mutantCreeperSpawnWeight,
                EntityTypes.CREEPER.builtInRegistryHolder(),
                ModEntityTypes.MUTANT_CREEPER_ENTITY_TYPE);
        registerMutantSpawn(context,
                ModTags.WITHOUT_MUTANT_ENDERMAN_SPAWNS_BIOME_TAG,
                () -> MutantMonsters.CONFIG.get(CommonConfig.class).mutantEndermanSpawnWeight,
                EntityTypes.ENDERMAN.builtInRegistryHolder(),
                ModEntityTypes.MUTANT_ENDERMAN_ENTITY_TYPE);
        registerMutantSpawn(context,
                ModTags.WITHOUT_MUTANT_SKELETON_SPAWNS_BIOME_TAG,
                () -> MutantMonsters.CONFIG.get(CommonConfig.class).mutantSkeletonSpawnWeight,
                EntityTypes.SKELETON.builtInRegistryHolder(),
                ModEntityTypes.MUTANT_SKELETON_ENTITY_TYPE);
        registerMutantSpawn(context,
                ModTags.WITHOUT_MUTANT_ZOMBIE_SPAWNS_BIOME_TAG,
                () -> MutantMonsters.CONFIG.get(CommonConfig.class).mutantZombieSpawnWeight,
                EntityTypes.ZOMBIE.builtInRegistryHolder(),
                ModEntityTypes.MUTANT_ZOMBIE_ENTITY_TYPE);
    }

    private static void registerMutantSpawn(BiomeTransformationsContext context, TagKey<Biome> withoutSpawnsTag, Supplier<Double> spawnWeightSupplier, Holder.Reference<? extends EntityType<?>> vanillaEntityType, Holder.Reference<? extends EntityType<?>> mutantEntityType) {
        context.registerBiomeTransformation(BiomeLoadingPhase.ADD,
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome) -> {
                    return !biome.is(withoutSpawnsTag);
                },
                (HolderGetter.Provider lookupProvider, Holder<Biome> biome, BiomeTransformer.Context transformation) -> {
                    Fraction spawnWeight = getSpawnWeight(spawnWeightSupplier);
                    SpawnerDataBuilder.create(transformation.mobSpawns(), vanillaEntityType.value())
                            .setWeight(spawnWeight)
                            .setMinCount(1)
                            .setMaxCount(1)
                            .apply(mutantEntityType.value());
                });
    }

    private static Fraction getSpawnWeight(Supplier<Double> spawnWeightSupplier) {
        float spawnWeight = spawnWeightSupplier.get().floatValue();
        if (spawnWeight == 0.0F) {
            return Fraction.ZERO;
        } else {
            return Fraction.getFraction(spawnWeight);
        }
    }
}

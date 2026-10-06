package fuzs.mutantmonsters.neoforge;

import fuzs.mutantmonsters.common.MutantMonsters;
import fuzs.mutantmonsters.common.data.ModAdvancementProvider;
import fuzs.mutantmonsters.common.data.ModRecipeProvider;
import fuzs.mutantmonsters.common.data.loot.ModBlockLootProvider;
import fuzs.mutantmonsters.common.data.loot.ModBodyPartLootProvider;
import fuzs.mutantmonsters.common.data.loot.ModEntityLootProvider;
import fuzs.mutantmonsters.common.data.loot.ModEntityTypeLootProvider;
import fuzs.mutantmonsters.common.data.tags.*;
import fuzs.mutantmonsters.common.init.ModEntityTypes;
import fuzs.mutantmonsters.common.init.ModRegistry;
import fuzs.mutantmonsters.neoforge.init.NeoForgeModRegistry;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantSkeleton;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantZombie;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(MutantMonsters.MOD_ID)
public class MutantMonstersNeoForge {

    public MutantMonstersNeoForge(ModContainer modContainer) {
        NeoForgeModRegistry.bootstrap();
        ModConstructor.construct(MutantMonsters.MOD_ID, MutantMonsters::new);
        registerLoadingHandlers(modContainer.getEventBus());
        DataProviderBuilder.of(MutantMonsters.MOD_ID)
                .addWorldBootstrap(Registries.DAMAGE_TYPE, ModRegistry::bootstrapDamageTypes)
                .addLootProvider(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                .addLootProvider(ModBodyPartLootProvider::new, ModRegistry.BODY_PART_LOOT_CONTEXT_PARAM_SET)
                .addLootProvider(ModEntityLootProvider::new, LootContextParamSets.ENTITY)
                .addLootProvider(ModEntityTypeLootProvider::new, LootContextParamSets.ENTITY)
                .addProvider(ModBiomeTagsProvider::new,
                        ModBlockTagsProvider::new,
                        ModDamageTypeTagsProvider::new,
                        ModEntityTypeTagsProvider::new,
                        ModItemTagsProvider::new)
                .addRecipeProvider(ModRecipeProvider::new)
                .addAdvancementProvider(ModAdvancementProvider::new);
    }

    private static void registerLoadingHandlers(IEventBus eventBus) {
        eventBus.addListener((final EntityAttributeCreationEvent evt) -> {
            evt.put(ModEntityTypes.MUTANT_SKELETON_ENTITY_TYPE.value(),
                    MutantSkeleton.createAttributes().add(NeoForgeMod.SWIM_SPEED, 5.0).build());
            evt.put(ModEntityTypes.MUTANT_ZOMBIE_ENTITY_TYPE.value(),
                    MutantZombie.createAttributes().add(NeoForgeMod.SWIM_SPEED, 4.0).build());
        });
    }
}

package fuzs.mutantmonsters.common.data.client;

import fuzs.mutantmonsters.common.MutantMonsters;
import fuzs.mutantmonsters.common.client.gui.screens.CreeperMinionTrackerScreen;
import fuzs.mutantmonsters.common.data.advancements.ModAdvancementProvider;
import fuzs.mutantmonsters.common.init.ModEntityTypes;
import fuzs.mutantmonsters.common.init.ModItems;
import fuzs.mutantmonsters.common.init.ModRegistry;
import fuzs.mutantmonsters.common.init.ModSoundEvents;
import fuzs.mutantmonsters.common.world.item.ArmorBlockItem;
import fuzs.mutantmonsters.common.world.item.SkeletonArmorItem;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), MutantMonsters.MOD_NAME);
        this.add(ModRegistry.MUTANT_SKELETON_SKULL_BLOCK.value(), "Mutant Skeleton Skull");
        this.add(ModEntityTypes.BODY_PART_ENTITY_TYPE.value(), "Body Part");
        this.add(ModEntityTypes.CREEPER_MINION_ENTITY_TYPE.value(), "Creeper Minion");
        this.add(ModEntityTypes.CREEPER_MINION_EGG_ENTITY_TYPE.value(), "Creeper Minion Egg");
        this.add(ModEntityTypes.ENDERSOUL_CLONE_ENTITY_TYPE.value(), "Endersoul Clone");
        this.add(ModEntityTypes.ENDERSOUL_FRAGMENT_ENTITY_TYPE.value(), "Endersoul Fragment");
        this.add(ModEntityTypes.MUTANT_ARROW_ENTITY_TYPE.value(), "Mutant Arrow");
        this.add(ModEntityTypes.MUTANT_CREEPER_ENTITY_TYPE.value(), "Mutant Creeper");
        this.add(ModEntityTypes.MUTANT_ENDERMAN_ENTITY_TYPE.value(), "Mutant Enderman");
        this.add(ModEntityTypes.MUTANT_SKELETON_ENTITY_TYPE.value(), "Mutant Skeleton");
        this.add(ModEntityTypes.MUTANT_SNOW_GOLEM_ENTITY_TYPE.value(), "Mutant Snow Golem");
        this.add(ModEntityTypes.MUTANT_ZOMBIE_ENTITY_TYPE.value(), "Mutant Zombie");
        this.add(ModEntityTypes.SKULL_SPIRIT_ENTITY_TYPE.value(), "Skull Spirit");
        this.add(ModEntityTypes.SPIDER_PIG_ENTITY_TYPE.value(), "Spider Pig");
        this.add(ModEntityTypes.THROWABLE_BLOCK_ENTITY_TYPE.value(), "Throwable Block");
        this.add(CreeperMinionTrackerScreen.HEALTH_COMPONENT, "Health");
        this.add(CreeperMinionTrackerScreen.EXPLOSION_COMPONENT, "Explosion");
        this.add(CreeperMinionTrackerScreen.CONTINUOUS_EXPLOSION_COMPONENT, "Continuous");
        this.add(CreeperMinionTrackerScreen.ONE_TIME_EXPLOSION_COMPONENT, "One-Time");
        this.add(CreeperMinionTrackerScreen.BLAST_RADIUS_COMPONENT, "Blast Radius");
        this.add(CreeperMinionTrackerScreen.SHOW_NAME_COMPONENT, "Always Show Name");
        this.add(CreeperMinionTrackerScreen.DESTROY_BLOCKS_COMPONENT, "Destroy Blocks");
        this.add(CreeperMinionTrackerScreen.RIDE_ON_SHOULDER_COMPONENT, "Ride On Shoulder");
        this.addSpawnEgg(ModItems.CREEPER_MINION_SPAWN_EGG_ITEM.value(), "Creeper Minion");
        this.addSpawnEgg(ModItems.MUTANT_CREEPER_SPAWN_EGG_ITEM.value(), "Mutant Creeper");
        this.addSpawnEgg(ModItems.MUTANT_ENDERMAN_SPAWN_EGG_ITEM.value(), "Mutant Enderman");
        this.addSpawnEgg(ModItems.MUTANT_SKELETON_SPAWN_EGG_ITEM.value(), "Mutant Skeleton");
        this.addSpawnEgg(ModItems.MUTANT_SNOW_GOLEM_SPAWN_EGG_ITEM.value(), "Mutant Snow Golem");
        this.addSpawnEgg(ModItems.MUTANT_ZOMBIE_SPAWN_EGG_ITEM.value(), "Mutant Zombie");
        this.addSpawnEgg(ModItems.SPIDER_PIG_SPAWN_EGG_ITEM.value(), "Spider Pig");
        this.add(ModItems.CREEPER_MINION_TRACKER_ITEM.value(), "Creeper Minion Tracker");
        this.add(ModItems.CREEPER_MINION_TRACKER_ITEM.value(), "tame_success", "%1$s was tamed by %2$s");
        this.add(ModItems.CREEPER_SHARD_ITEM.value(), "Creeper Shard");
        this.add(ModItems.HULK_HAMMER_ITEM.value(), "Hulk Hammer");
        this.add(ModItems.ENDERSOUL_HAND_ITEM.value(), "Endersoul Hand");
        this.add(ModItems.ENDERSOUL_HAND_ITEM.value(), "teleport_failed", "Unable to teleport to location");
        this.add(ModItems.MUTANT_SKELETON_ARMS_ITEM.value(), "Mutant Skeleton Arms");
        this.add(ModItems.MUTANT_SKELETON_LIMB_ITEM.value(), "Mutant Skeleton Limb");
        this.add(ModItems.MUTANT_SKELETON_PELVIS_ITEM.value(), "Mutant Skeleton Pelvis");
        this.add(ModItems.MUTANT_SKELETON_RIB_CAGE_ITEM.value(), "Mutant Skeleton Rib Cage");
        this.add(ModItems.MUTANT_SKELETON_RIB_ITEM.value(), "Mutant Skeleton Rib");
        this.add(ModItems.MUTANT_SKELETON_SHOULDER_PAD_ITEM.value(), "Mutant Skeleton Shoulder Pad");
        this.add(ModItems.MUTANT_SKELETON_CHESTPLATE_ITEM.value(), "Mutant Skeleton Chestplate");
        this.add(ModItems.MUTANT_SKELETON_LEGGINGS_ITEM.value(), "Mutant Skeleton Leggings");
        this.add(ModItems.MUTANT_SKELETON_BOOTS_ITEM.value(), "Mutant Skeleton Boots");
        this.add(ModItems.CHEMICAL_X_ITEM.value(), "Chemical X");
        this.add(((ArmorBlockItem) ModItems.MUTANT_SKELETON_SKULL_ITEM.value()).getDescriptionComponent(),
                "Unlocks multishot for bows.");
        this.add(((SkeletonArmorItem) ModItems.MUTANT_SKELETON_CHESTPLATE_ITEM.value()).getDescriptionComponent(),
                "Unlocks quick draw for bows.");
        this.add(((SkeletonArmorItem) ModItems.MUTANT_SKELETON_LEGGINGS_ITEM.value()).getDescriptionComponent(),
                "Grants increased speed to the wearer.");
        this.add(((SkeletonArmorItem) ModItems.MUTANT_SKELETON_BOOTS_ITEM.value()).getDescriptionComponent(),
                "Grants increased jump height to the wearer.");
        this.add(ModRegistry.CHEMICAL_X_MOB_EFFECT.value(), "Chemical X");
        this.add(ModSoundEvents.ENTITY_CREEPER_MINION_AMBIENT_SOUND_EVENT.value(), "Creeper Minion hisses");
        this.add(ModSoundEvents.ENTITY_CREEPER_MINION_DEATH_SOUND_EVENT.value(), "Creeper Minion dies");
        this.add(ModSoundEvents.ENTITY_CREEPER_MINION_HURT_SOUND_EVENT.value(), "Creeper Minion hurts");
        this.add(ModSoundEvents.ENTITY_CREEPER_MINION_PRIMED_SOUND_EVENT.value(), "Creeper Minion primes");
        this.add(ModSoundEvents.ENTITY_CREEPER_MINION_EGG_HATCH_SOUND_EVENT.value(), "Creeper Minion Egg hatches");
        this.add(ModSoundEvents.ENTITY_ENDERSOUL_CLONE_DEATH_SOUND_EVENT.value(), "Endersoul Clone dies");
        this.add(ModSoundEvents.ENTITY_ENDERSOUL_CLONE_TELEPORT_SOUND_EVENT.value(), "Endersoul Clone teleports");
        this.add(ModSoundEvents.ENTITY_ENDERSOUL_FRAGMENT_EXPLODE_SOUND_EVENT.value(),
                "Endersoul Fragment explodes");
        this.add(ModSoundEvents.ENTITY_MUTANT_CREEPER_AMBIENT_SOUND_EVENT.value(), "Mutant Creeper hisses");
        this.add(ModSoundEvents.ENTITY_MUTANT_CREEPER_CHARGE_SOUND_EVENT.value(), "Mutant Creeper charges");
        this.add(ModSoundEvents.ENTITY_MUTANT_CREEPER_DEATH_SOUND_EVENT.value(), "Mutant Creeper dies");
        this.add(ModSoundEvents.ENTITY_MUTANT_CREEPER_HURT_SOUND_EVENT.value(), "Mutant Creeper hurts");
        this.add(ModSoundEvents.ENTITY_MUTANT_CREEPER_PRIMED_SOUND_EVENT.value(), "Mutant Creeper primes");
        this.add(ModSoundEvents.ENTITY_MUTANT_ENDERMAN_AMBIENT_SOUND_EVENT.value(), "Mutant Enderman breathes");
        this.add(ModSoundEvents.ENTITY_MUTANT_ENDERMAN_DEATH_SOUND_EVENT.value(), "Mutant Enderman dies");
        this.add(ModSoundEvents.ENTITY_MUTANT_ENDERMAN_HURT_SOUND_EVENT.value(), "Mutant Enderman hurts");
        this.add(ModSoundEvents.ENTITY_MUTANT_ENDERMAN_MORPH_SOUND_EVENT.value(), "Mutant Enderman morphs");
        this.add(ModSoundEvents.ENTITY_MUTANT_ENDERMAN_SCREAM_SOUND_EVENT.value(), "Mutant Enderman screams");
        this.add(ModSoundEvents.ENTITY_MUTANT_ENDERMAN_STARE_SOUND_EVENT.value(), "Mutant Enderman cries out");
        this.add(ModSoundEvents.ENTITY_MUTANT_ENDERMAN_TELEPORT_SOUND_EVENT.value(), "Mutant Enderman teleports");
        this.add(ModSoundEvents.ENTITY_MUTANT_SKELETON_AMBIENT_SOUND_EVENT.value(), "Mutant Skeleton rattles");
        this.add(ModSoundEvents.ENTITY_MUTANT_SKELETON_DEATH_SOUND_EVENT.value(), "Mutant Skeleton dies");
        this.add(ModSoundEvents.ENTITY_MUTANT_SKELETON_HURT_SOUND_EVENT.value(), "Mutant Skeleton hurts");
        this.add(ModSoundEvents.ENTITY_MUTANT_SKELETON_STEP_SOUND_EVENT.value(), "Footsteps");
        this.add(ModSoundEvents.ENTITY_MUTANT_SNOW_GOLEM_DEATH_SOUND_EVENT.value(), "Mutant Snow Golem dies");
        this.add(ModSoundEvents.ENTITY_MUTANT_SNOW_GOLEM_HURT_SOUND_EVENT.value(), "Mutant Snow Golem hurts");
        this.add(ModSoundEvents.ENTITY_MUTANT_ZOMBIE_AMBIENT_SOUND_EVENT.value(), "Mutant Zombie grumbles");
        this.add(ModSoundEvents.ENTITY_MUTANT_ZOMBIE_ATTACK_SOUND_EVENT.value(), "Mutant Zombie attacks");
        this.add(ModSoundEvents.ENTITY_MUTANT_ZOMBIE_DEATH_SOUND_EVENT.value(), "Mutant Zombie dies");
        this.add(ModSoundEvents.ENTITY_MUTANT_ZOMBIE_GRUNT_SOUND_EVENT.value(), "Mutant Zombie grunts");
        this.add(ModSoundEvents.ENTITY_MUTANT_ZOMBIE_HURT_SOUND_EVENT.value(), "Mutant Zombie hurts");
        this.add(ModSoundEvents.ENTITY_MUTANT_ZOMBIE_ROAR_SOUND_EVENT.value(), "Mutant Zombie roars");
        this.add(ModSoundEvents.ENTITY_SPIDER_PIG_AMBIENT_SOUND_EVENT.value(), "Spider Pig oinks");
        this.add(ModSoundEvents.ENTITY_SPIDER_PIG_DEATH_SOUND_EVENT.value(), "Spider Pig dies");
        this.add(ModSoundEvents.ENTITY_SPIDER_PIG_HURT_SOUND_EVENT.value(), "Spider Pig hurts");
        this.add(ModAdvancementProvider.ROOT_ADVANCEMENT.title(), MutantMonsters.MOD_NAME);
        this.add(ModAdvancementProvider.ROOT_ADVANCEMENT.description(),
                "Mutant creatures and beasts to face off against. Will you triumph, or will you perish?");
        this.add(ModAdvancementProvider.BURN_ZOMBIE_BURN_ADVANCEMENT.title(), "Burn Zombie Burn");
        this.add(ModAdvancementProvider.BURN_ZOMBIE_BURN_ADVANCEMENT.description(),
                "Use a flint and steel to light a mutant zombie on fire when it's down");
        this.add(ModAdvancementProvider.FROSTY_THE_SNOW_GOLEM_ADVANCEMENT.title(), "Frosty the Snow Golem");
        this.add(ModAdvancementProvider.FROSTY_THE_SNOW_GOLEM_ADVANCEMENT.description(),
                "Create a Mutant Snow Golem using Chemical X");
        this.add(ModAdvancementProvider.GUNPOWDER_SPICE_ADVANCEMENT.title(), "Gunpowder, spice...");
        this.add(ModAdvancementProvider.GUNPOWDER_SPICE_ADVANCEMENT.description(),
                "...and everything nice. Obtain a potion of Chemical X.");
        this.add(ModAdvancementProvider.HULK_SMASH_ADVANCEMENT.title(), "Hulk Smash!");
        this.add(ModAdvancementProvider.HULK_SMASH_ADVANCEMENT.description(),
                "Kill a Mutant Zombie using a Hulk Hammer");
        this.add(ModAdvancementProvider.NO_BONES_ABOUT_IT_ADVANCEMENT.title(), "No Bones About It");
        this.add(ModAdvancementProvider.NO_BONES_ABOUT_IT_ADVANCEMENT.description(),
                "Kill a Mutant Skeleton using a crossbow while wearing a full set of Mutant Skeleton armor");
        this.add(ModAdvancementProvider.SPIDER_PIG_SPIDER_PIG_ADVANCEMENT.title(), "Spider-Pig, Spider-Pig...");
        this.add(ModAdvancementProvider.SPIDER_PIG_SPIDER_PIG_ADVANCEMENT.description(),
                "Feed a Pig a Fermented Spider Eye and throw Chemical X at it");
        this.add(ModAdvancementProvider.YOU_DA_BOMBY_ADVANCEMENT.title(), "You da Bomby");
        this.add(ModAdvancementProvider.YOU_DA_BOMBY_ADVANCEMENT.description(),
                "Hatch a Creeper Minion from its egg");
    }
}

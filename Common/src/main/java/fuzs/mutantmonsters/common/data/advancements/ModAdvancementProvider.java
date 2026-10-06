package fuzs.mutantmonsters.common.data.advancements;

import fuzs.mutantmonsters.common.MutantMonsters;
import fuzs.mutantmonsters.common.init.ModEntityTypes;
import fuzs.mutantmonsters.common.init.ModItems;
import fuzs.mutantmonsters.common.init.ModTags;
import fuzs.puzzleslib.common.api.data.v3.advancements.AbstractAdvancementProvider;
import fuzs.puzzleslib.common.api.data.v3.advancements.AdvancementToken;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.DamageSourcePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.NbtPredicate;
import net.minecraft.advancements.predicates.TagPredicate;
import net.minecraft.advancements.predicates.entity.EntityEquipmentPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class ModAdvancementProvider extends AbstractAdvancementProvider {
    public static final AdvancementToken ROOT_ADVANCEMENT = new AdvancementToken(MutantMonsters.id("root"));
    public static final AdvancementToken BURN_ZOMBIE_BURN_ADVANCEMENT = new AdvancementToken(MutantMonsters.id(
            "burn_zombie_burn"));
    public static final AdvancementToken FROSTY_THE_SNOW_GOLEM_ADVANCEMENT = new AdvancementToken(MutantMonsters.id(
            "frosty_the_snow_golem"));
    public static final AdvancementToken GUNPOWDER_SPICE_ADVANCEMENT = new AdvancementToken(MutantMonsters.id(
            "gunpowder_spice"));
    public static final AdvancementToken HULK_SMASH_ADVANCEMENT = new AdvancementToken(MutantMonsters.id("hulk_smash"));
    public static final AdvancementToken NO_BONES_ABOUT_IT_ADVANCEMENT = new AdvancementToken(MutantMonsters.id(
            "no_bones_about_it"));
    public static final AdvancementToken SPIDER_PIG_SPIDER_PIG_ADVANCEMENT = new AdvancementToken(MutantMonsters.id(
            "spider_pig_spider_pig"));
    public static final AdvancementToken YOU_DA_BOMBY_ADVANCEMENT = new AdvancementToken(MutantMonsters.id(
            "you_da_bomby"));

    public ModAdvancementProvider(BootstrapContext<Advancement> output) {
        super(output);
    }

    @Override
    public void generate() {
        HolderGetter<Item> itemLookup = this.output.lookup(Registries.ITEM);
        HolderGetter<EntityType<?>> entityTypeLookup = this.output.lookup(Registries.ENTITY_TYPE);
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(ModItems.ENDERSOUL_HAND_ITEM.value()),
                        ROOT_ADVANCEMENT.id())
                        .setBackground(Identifier.withDefaultNamespace("gui/advancements/backgrounds/stone"))
                        .setType(AdvancementType.TASK)
                        .setHidden(false)
                        .build())
                .addCriterion("killed_something",
                        KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity()
                                .of(entityTypeLookup, ModTags.MUTANTS_ENTITY_TYPE_TAG)))
                .addCriterion("killed_by_something",
                        KilledTrigger.TriggerInstance.entityKilledPlayer(EntityPredicate.Builder.entity()
                                .of(entityTypeLookup, ModTags.MUTANTS_ENTITY_TYPE_TAG)))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(this.output, ROOT_ADVANCEMENT.name());
        AdvancementHolder burnZombieBurn = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(Items.FLINT_AND_STEEL),
                        BURN_ZOMBIE_BURN_ADVANCEMENT.id()).build())
                .parent(root)
                .addCriterion("used_flint_and_steel",
                        PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(ItemPredicate.Builder.item()
                                        .of(itemLookup, Items.FLINT_AND_STEEL, Items.FIRE_CHARGE),
                                Optional.of(EntityPredicate.wrap(EntityPredicate.Builder.entity()
                                        .of(entityTypeLookup, ModEntityTypes.MUTANT_ZOMBIE_ENTITY_TYPE.value())))))
                .save(this.output, BURN_ZOMBIE_BURN_ADVANCEMENT.name());
        AdvancementHolder gunpowderSpice = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(Items.GUNPOWDER), GUNPOWDER_SPICE_ADVANCEMENT.id()).build())
                .parent(root)
                .addCriterion("obtained_chemical_x",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(itemLookup, ModItems.CHEMICAL_X_ITEM.value())))
                .save(this.output, GUNPOWDER_SPICE_ADVANCEMENT.name());
        AdvancementHolder frostyTheSnowGolem = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(Items.JACK_O_LANTERN),
                        FROSTY_THE_SNOW_GOLEM_ADVANCEMENT.id()).build())
                .parent(gunpowderSpice)
                .addCriterion("created_mutant_snow_golem",
                        SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity()
                                .of(entityTypeLookup, ModEntityTypes.MUTANT_SNOW_GOLEM_ENTITY_TYPE.value())))
                .save(this.output, FROSTY_THE_SNOW_GOLEM_ADVANCEMENT.name());
        AdvancementHolder hulkSmash = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(ModItems.HULK_HAMMER_ITEM.value()),
                        HULK_SMASH_ADVANCEMENT.id()).setType(AdvancementType.GOAL).build())
                .parent(burnZombieBurn)
                .addCriterion("killed_mutant_zombie",
                        KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity()
                                        .of(entityTypeLookup, ModEntityTypes.MUTANT_ZOMBIE_ENTITY_TYPE.value()),
                                DamageSourcePredicate.Builder.damageType()
                                        .direct(EntityPredicate.Builder.entity()
                                                .equipment(EntityEquipmentPredicate.Builder.equipment()
                                                        .mainhand(ItemPredicate.Builder.item()
                                                                .of(itemLookup, ModItems.HULK_HAMMER_ITEM.value()))))))
                .save(this.output, HULK_SMASH_ADVANCEMENT.name());
        AdvancementHolder noBonesAboutIt = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(ModItems.MUTANT_SKELETON_SKULL_ITEM.value()),
                        NO_BONES_ABOUT_IT_ADVANCEMENT.id()).setType(AdvancementType.GOAL).build())
                .parent(root)
                .addCriterion("killed_mutant_skeleton",
                        KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity()
                                        .of(entityTypeLookup, ModEntityTypes.MUTANT_SKELETON_ENTITY_TYPE.value()),
                                DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(this.damageTypes, DamageTypeTags.IS_PROJECTILE))
                                        .direct(EntityPredicate.Builder.entity()
                                                .of(entityTypeLookup, EntityTypeTags.ARROWS)
                                                .nbt(new NbtPredicate(Util.make(new CompoundTag(),
                                                        tag -> tag.putBoolean("ShotFromCrossbow", true)))))
                                        .source(EntityPredicate.Builder.entity()
                                                .equipment(EntityEquipmentPredicate.Builder.equipment()
                                                        .head(ItemPredicate.Builder.item()
                                                                .of(itemLookup,
                                                                        ModItems.MUTANT_SKELETON_SKULL_ITEM.value()))
                                                        .chest(ItemPredicate.Builder.item()
                                                                .of(itemLookup,
                                                                        ModItems.MUTANT_SKELETON_CHESTPLATE_ITEM.value()))
                                                        .legs(ItemPredicate.Builder.item()
                                                                .of(itemLookup,
                                                                        ModItems.MUTANT_SKELETON_LEGGINGS_ITEM.value()))
                                                        .feet(ItemPredicate.Builder.item()
                                                                .of(itemLookup,
                                                                        ModItems.MUTANT_SKELETON_BOOTS_ITEM.value()))))))
                .save(this.output, NO_BONES_ABOUT_IT_ADVANCEMENT.name());
        AdvancementHolder spiderPigSpiderPig = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(Items.COBWEB), SPIDER_PIG_SPIDER_PIG_ADVANCEMENT.id()).build())
                .parent(gunpowderSpice)
                .addCriterion("created_spider_pig",
                        SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity()
                                .of(entityTypeLookup, ModEntityTypes.SPIDER_PIG_ENTITY_TYPE.value())))
                .save(this.output, SPIDER_PIG_SPIDER_PIG_ADVANCEMENT.name());
        AdvancementHolder youDaBomby = Advancement.Builder.advancement()
                .display(display(new ItemStackTemplate(Items.CREEPER_HEAD), YOU_DA_BOMBY_ADVANCEMENT.id()).build())
                .parent(root)
                .addCriterion("tamed_creeper_minion",
                        TameAnimalTrigger.TriggerInstance.tamedAnimal(EntityPredicate.Builder.entity()
                                .of(entityTypeLookup, ModEntityTypes.CREEPER_MINION_ENTITY_TYPE.value())))
                .save(this.output, YOU_DA_BOMBY_ADVANCEMENT.name());
    }
}

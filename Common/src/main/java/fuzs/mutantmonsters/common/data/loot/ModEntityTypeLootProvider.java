package fuzs.mutantmonsters.common.data.loot;

import fuzs.mutantmonsters.common.init.ModEntityTypes;
import fuzs.mutantmonsters.common.init.ModItems;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractEntityLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModEntityTypeLootProvider extends AbstractEntityLootSubProvider {

    public ModEntityTypeLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.add(ModEntityTypes.CREEPER_MINION_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModEntityTypes.ENDERSOUL_CLONE_ENTITY_TYPE.value(), LootTable.lootTable());
        this.add(ModEntityTypes.MUTANT_CREEPER_ENTITY_TYPE.value(), LootTable.lootTable());
        this.add(ModEntityTypes.MUTANT_ENDERMAN_ENTITY_TYPE.value(), LootTable.lootTable());
        this.add(ModEntityTypes.MUTANT_SKELETON_ENTITY_TYPE.value(), LootTable.lootTable());
        this.add(ModEntityTypes.MUTANT_SNOW_GOLEM_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.SNOWBALL)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(32, 48))))));
        this.add(ModEntityTypes.MUTANT_ZOMBIE_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.HULK_HAMMER_ITEM.value()))));
        this.add(ModEntityTypes.SPIDER_PIG_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(NestedLootTable.lootTableReference(this.lootTables.getOrThrow(EntityTypes.PIG.getDefaultLootTable()
                                        .orElseThrow())))
                                .add(NestedLootTable.lootTableReference(this.lootTables.getOrThrow(EntityTypes.SPIDER.getDefaultLootTable()
                                        .orElseThrow())))));
    }
}

package fuzs.mutantmonsters.common.data.loot;

import fuzs.mutantmonsters.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractLootSubProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class ModEntityLootProvider extends AbstractLootSubProvider {

    public ModEntityLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.output.accept(ModRegistry.MUTANT_ENDERMAN_CONTINUOUS_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL))
                                .add(LootItem.lootTableItem(Items.ENDER_EYE))));
        this.output.accept(ModRegistry.CHARGED_MUTANT_CREEPER_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(NestedLootTable.lootTableReference(this.output.lookup(Registries.LOOT_TABLE)
                                        .getOrThrow(BuiltInLootTables.CHARGED_CREEPER)))));
        this.output.accept(ModRegistry.CHARGED_CREEPER_MINION_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(NestedLootTable.lootTableReference(this.output.lookup(Registries.LOOT_TABLE)
                                        .getOrThrow(BuiltInLootTables.CHARGED_CREEPER)))));
    }
}

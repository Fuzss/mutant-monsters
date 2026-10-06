package fuzs.mutantmonsters.common.data.loot;

import fuzs.mutantmonsters.common.init.ModItems;
import fuzs.mutantmonsters.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModBodyPartLootProvider extends AbstractLootSubProvider {

    public ModBodyPartLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.output.accept(ModRegistry.MUTANT_SKELETON_PELVIS_LOOT_TABLE,
                getBodyPartBuilder(ModItems.MUTANT_SKELETON_PELVIS_ITEM.value())
        );
        this.output.accept(ModRegistry.MUTANT_SKELETON_RIB_LOOT_TABLE,
                getBodyPartBuilder(ModItems.MUTANT_SKELETON_RIB_ITEM.value())
        );
        this.output.accept(ModRegistry.MUTANT_SKELETON_SKULL_LOOT_TABLE,
                getBodyPartBuilder(ModItems.MUTANT_SKELETON_SKULL_ITEM.value())
        );
        this.output.accept(ModRegistry.MUTANT_SKELETON_LIMB_LOOT_TABLE,
                getBodyPartBuilder(ModItems.MUTANT_SKELETON_LIMB_ITEM.value())
        );
        this.output.accept(ModRegistry.MUTANT_SKELETON_SHOULDER_PAD_LOOT_TABLE,
                getBodyPartBuilder(ModItems.MUTANT_SKELETON_SHOULDER_PAD_ITEM.value())
        );
    }

    private static LootTable.Builder getBodyPartBuilder(Item item) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ContextIntProviders.exactly(1))
                .add(LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))));
    }
}

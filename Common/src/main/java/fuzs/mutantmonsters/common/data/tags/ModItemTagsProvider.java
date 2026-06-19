package fuzs.mutantmonsters.common.data.tags;

import fuzs.mutantmonsters.common.init.ModItems;
import fuzs.mutantmonsters.common.init.ModTags;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

public class ModItemTagsProvider extends AbstractTagProvider<Item> {

    public ModItemTagsProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.HEAD_ARMOR).add(ModItems.MUTANT_SKELETON_SKULL_ITEM);
        this.tag(ItemTags.CHEST_ARMOR).add(ModItems.MUTANT_SKELETON_CHESTPLATE_ITEM);
        this.tag(ItemTags.LEG_ARMOR).add(ModItems.MUTANT_SKELETON_LEGGINGS_ITEM);
        this.tag(ItemTags.FOOT_ARMOR).add(ModItems.MUTANT_SKELETON_BOOTS_ITEM);
        this.tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.CREEPER_SHARD_ITEM,
                        ModItems.HULK_HAMMER_ITEM,
                        ModItems.ENDERSOUL_HAND_ITEM,
                        ModItems.MUTANT_SKELETON_SKULL_ITEM,
                        ModItems.ENDERSOUL_HAND_ITEM);
        this.tag(ItemTags.SKULLS).add(ModItems.MUTANT_SKELETON_SKULL_ITEM);
        this.tag(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(ModItems.HULK_HAMMER_ITEM);
        this.tag(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(ModItems.HULK_HAMMER_ITEM);
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .remove(ModItems.MUTANT_SKELETON_SKULL_ITEM,
                        ModItems.MUTANT_SKELETON_CHESTPLATE_ITEM,
                        ModItems.MUTANT_SKELETON_LEGGINGS_ITEM,
                        ModItems.MUTANT_SKELETON_BOOTS_ITEM);
        this.tag(ModTags.REPAIRS_SKELETON_ARMOR_ITEM_TAG).add(BlockItemIds.BONE_BLOCK.item());
        this.tag(ModTags.SPIDER_PIG_FOOD_ITEM_TAG)
                .add(BlockItemIds.CARROT_CROP.item(),
                        BlockItemIds.POTATO_CROP.item(),
                        ItemIds.BEETROOT,
                        ItemIds.PORKCHOP,
                        ItemIds.SPIDER_EYE);
    }
}

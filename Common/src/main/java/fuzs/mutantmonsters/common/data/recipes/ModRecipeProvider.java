package fuzs.mutantmonsters.common.data.recipes;

import fuzs.mutantmonsters.common.init.ModItems;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        this.shaped(RecipeCategory.TOOLS, ModItems.CREEPER_MINION_TRACKER_ITEM.value())
                .define('I', Items.IRON_INGOT)
                .define('S', ModItems.CREEPER_SHARD_ITEM.value())
                .pattern(" I ")
                .pattern("ISI")
                .pattern(" I ")
                .unlockedBy(getHasName(ModItems.CREEPER_SHARD_ITEM.value()),
                        this.has(ModItems.CREEPER_SHARD_ITEM.value()))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ModItems.MUTANT_SKELETON_ARMS_ITEM.value())
                .define('S', ModItems.MUTANT_SKELETON_SHOULDER_PAD_ITEM.value())
                .define('L', ModItems.MUTANT_SKELETON_LIMB_ITEM.value())
                .pattern("S S")
                .pattern("L L")
                .pattern("L L")
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_SHOULDER_PAD_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_SHOULDER_PAD_ITEM.value()))
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_LIMB_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_LIMB_ITEM.value()))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ModItems.MUTANT_SKELETON_BOOTS_ITEM.value())
                .define('L', ModItems.MUTANT_SKELETON_LIMB_ITEM.value())
                .pattern("L L")
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_LIMB_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_LIMB_ITEM.value()))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ModItems.MUTANT_SKELETON_CHESTPLATE_ITEM.value())
                .define('A', ModItems.MUTANT_SKELETON_ARMS_ITEM.value())
                .define('R', ModItems.MUTANT_SKELETON_RIB_CAGE_ITEM.value())
                .pattern("A")
                .pattern("R")
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_ARMS_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_ARMS_ITEM.value()))
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_RIB_CAGE_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_RIB_CAGE_ITEM.value()))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ModItems.MUTANT_SKELETON_LEGGINGS_ITEM.value())
                .define('P', ModItems.MUTANT_SKELETON_PELVIS_ITEM.value())
                .define('L', ModItems.MUTANT_SKELETON_LIMB_ITEM.value())
                .pattern(" P ")
                .pattern("L L")
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_PELVIS_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_PELVIS_ITEM.value()))
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_LIMB_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_LIMB_ITEM.value()))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ModItems.MUTANT_SKELETON_RIB_CAGE_ITEM.value())
                .define('R', ModItems.MUTANT_SKELETON_RIB_ITEM.value())
                .pattern("R R")
                .pattern("R R")
                .pattern("R R")
                .unlockedBy(getHasName(ModItems.MUTANT_SKELETON_RIB_ITEM.value()),
                        this.has(ModItems.MUTANT_SKELETON_RIB_ITEM.value()))
                .save(this.output);
    }
}

package fuzs.mutantmonsters.common.data.recipes;

import fuzs.mutantmonsters.common.init.ModItems;
import fuzs.mutantmonsters.common.world.item.ChemicalXItem;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractBrewingProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.recipes.BrewingRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.PotionIngredient;
import net.minecraft.world.item.crafting.Recipe;

public class ModBrewingProvider extends AbstractBrewingProvider {

    public ModBrewingProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void addContainers() {
        this.addContainer(Items.SPLASH_POTION);
    }

    @Override
    protected void addContainerTransformations() {
        // NO-OP
    }

    @Override
    protected void buildMixes() {
        this.buildChemicalXMix(ModItems.ENDERSOUL_HAND_ITEM.value());
        this.buildChemicalXMix(ModItems.HULK_HAMMER_ITEM.value());
        this.buildChemicalXMix(ModItems.CREEPER_SHARD_ITEM.value());
        this.buildChemicalXMix(ModItems.MUTANT_SKELETON_SKULL_ITEM.value());
    }

    protected void buildChemicalXMix(Item reagent) {
        for (Item container : this.containers) {
            this.save(chemicalXMix(container, Potions.THICK, reagent));
        }
    }

    /**
     * @see net.minecraft.data.recipes.BrewingRecipeBuilder#brewingMix(Item, Holder, Item, Holder)
     */
    public static BrewingRecipeBuilder chemicalXMix(Item container, Holder<Potion> inputPotion, Item reagentItem) {
        PotionIngredient input = BrewingRecipeBuilder.potionIngredient(container, inputPotion);
        PotionIngredient reagent = PotionIngredient.of(reagentItem);
        ItemStackTemplate output = chemicalXOutput();
        return new BrewingRecipeBuilder(input, reagent, output);
    }

    private static ItemStackTemplate chemicalXOutput() {
        return new ItemStackTemplate(ModItems.CHEMICAL_X_ITEM,
                1,
                DataComponentPatch.builder()
                        .set(DataComponents.POTION_CONTENTS, ChemicalXItem.getPotionContents())
                        .build());
    }
}

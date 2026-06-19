package fuzs.mutantmonsters.common.data.tags;

import fuzs.mutantmonsters.common.init.ModTags;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class ModBiomeTagsProvider extends AbstractTagProvider<Biome> {

    public ModBiomeTagsProvider(DataProviderContext context) {
        super(Registries.BIOME, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.WITHOUT_MUTANT_CREEPER_SPAWNS_BIOME_TAG);
        // Prevent spawning on the main end island as it makes the dragon fight needlessly harder.
        // Outer end islands are totally fine, however.
        this.tag(ModTags.WITHOUT_MUTANT_ENDERMAN_SPAWNS_BIOME_TAG).add(Biomes.THE_END);
        this.tag(ModTags.WITHOUT_MUTANT_SKELETON_SPAWNS_BIOME_TAG);
        this.tag(ModTags.WITHOUT_MUTANT_ZOMBIE_SPAWNS_BIOME_TAG);
    }
}

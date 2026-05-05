package fuzs.mutantmonsters.neoforge.client;

import fuzs.mutantmonsters.common.MutantMonsters;
import fuzs.mutantmonsters.common.client.MutantMonstersClient;
import fuzs.mutantmonsters.common.data.client.ModLanguageProvider;
import fuzs.mutantmonsters.common.data.client.ModModelProvider;
import fuzs.mutantmonsters.common.data.client.ModEquipmentProvider;
import fuzs.mutantmonsters.common.data.client.ModParticleProvider;
import fuzs.mutantmonsters.neoforge.data.client.ModSoundProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = MutantMonsters.MOD_ID, dist = Dist.CLIENT)
public class MutantMonstersNeoForgeClient {

    public MutantMonstersNeoForgeClient() {
        ClientModConstructor.construct(MutantMonsters.MOD_ID, MutantMonstersClient::new);
        DataProviderHelper.registerDataProviders(MutantMonsters.MOD_ID,
                ModLanguageProvider::new,
                ModParticleProvider::new,
                ModModelProvider::new,
                ModSoundProvider::new,
                ModEquipmentProvider::new);
    }
}

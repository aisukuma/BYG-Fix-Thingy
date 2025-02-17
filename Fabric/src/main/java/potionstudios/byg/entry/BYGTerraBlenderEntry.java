package potionstudios.byg.entry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.SurfaceRules;
import potionstudios.byg.BYG;
import potionstudios.byg.BYGFabric;
import potionstudios.byg.common.world.surfacerules.SurfaceRulesConfig;
import potionstudios.byg.config.SettingsConfig;
import potionstudios.byg.config.json.OverworldBiomeConfig;
import potionstudios.byg.world.biome.BYGTerraBlenderRegion;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import java.util.Map;

public class BYGTerraBlenderEntry implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        BYGFabric.initializeBYG("TerraBlender Initializer");

        OverworldBiomeConfig config = OverworldBiomeConfig.getConfig(true);
        if (config.generateOverworld()) {
            config.values().forEach(biomeProviderData -> Regions.register(new BYGTerraBlenderRegion(biomeProviderData.value())));
        } else {
            BYG.LOGGER.info("BYG overworld biomes disabled.");
        }
    }

    public static void readOverworldSurfaceRules() {
        OverworldBiomeConfig config = OverworldBiomeConfig.getConfig(true);
        if (config.generateOverworld() && SettingsConfig.getConfig().useBYGWorldGen()) {
            Map<ResourceKey<LevelStem>, SurfaceRules.RuleSource> surfaceRulesConfig = SurfaceRulesConfig.getConfig(true);
            if (surfaceRulesConfig.containsKey(LevelStem.OVERWORLD) && surfaceRulesConfig.get(LevelStem.OVERWORLD) != null) {
                SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, BYG.MOD_ID, surfaceRulesConfig.get(LevelStem.OVERWORLD));
            } else {
                throw new IllegalStateException(String.format("Surface rules for \"%s\" are required to load. Fix the config file found at: \"%s\"", LevelStem.OVERWORLD.location().toString(), SurfaceRulesConfig.CONFIG_PATHS.get().get(LevelStem.OVERWORLD)));
            }
        }
    }
}
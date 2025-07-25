package me.steven.indrev.config;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IRConfig {
    private static final ModConfigSpec.Builder OREGEN = new ModConfigSpec.Builder();

    public static void readConfigs(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, Oregen.SPEC, "indrev/oregen");
    }

    public static class Oregen {
        private static final ModConfigSpec.BooleanValue TIN = OREGEN.define("tin", true);
        private static final ModConfigSpec.BooleanValue NIKOLITE = OREGEN.define("nikolite", true);
        private static final ModConfigSpec.BooleanValue LEAD = OREGEN.define("lead", true);
        private static final ModConfigSpec.BooleanValue SILVER = OREGEN.define("silver", true);
        private static final ModConfigSpec.BooleanValue TUNGSTEN = OREGEN.define("tungsten", true);
        private static final ModConfigSpec.BooleanValue SULFURIC_ACID_LAKE = OREGEN.define("sulfuric_acid_lake", true);
        private static final ModConfigSpec.BooleanValue SULFUR_CRYSTALS = OREGEN.define("sulfur_crystals", true);

        public static final ModConfigSpec SPEC = OREGEN.build();
    }
}

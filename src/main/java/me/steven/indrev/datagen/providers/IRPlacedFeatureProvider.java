package me.steven.indrev.datagen.providers;

import me.steven.indrev.IndustrialRevolution;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

public class IRPlacedFeatureProvider {
    public static final ResourceKey<PlacedFeature> NIKOLITE_ORES = register("nikolite_ores");
    public static final ResourceKey<PlacedFeature> TIN_ORES = register("tin_ores");
    public static final ResourceKey<PlacedFeature> LEAD_ORES = register("lead_ores");
    public static final ResourceKey<PlacedFeature> SILVER_ORES = register("silver_ores");
    public static final ResourceKey<PlacedFeature> TUNGSTEN_ORES = register("tungsten_ores");
    public static final ResourceKey<PlacedFeature> SULFUR_CRYSTAL_FEATURE = register("sulfur_crystal_feature");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, NIKOLITE_ORES, holderGetter.getOrThrow(IRConfiguredFeatureProvider.NIKOLITE_ORES),
                CountPlacement.of(8),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(16)),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        PlacementUtils.register(context, TIN_ORES, holderGetter.getOrThrow(IRConfiguredFeatureProvider.TIN_ORES),
                CountPlacement.of(14),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(48)),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        PlacementUtils.register(context, LEAD_ORES, holderGetter.getOrThrow(IRConfiguredFeatureProvider.LEAD_ORES),
                CountPlacement.of(11),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        PlacementUtils.register(context, SILVER_ORES, holderGetter.getOrThrow(IRConfiguredFeatureProvider.SILVER_ORES),
                CountPlacement.of(9),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        PlacementUtils.register(context, TUNGSTEN_ORES, holderGetter.getOrThrow(IRConfiguredFeatureProvider.TUNGSTEN_ORES),
                CountPlacement.of(8),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(16)),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        PlacementUtils.register(context, SULFUR_CRYSTAL_FEATURE, holderGetter.getOrThrow(IRConfiguredFeatureProvider.SULFUR_CRYSTAL_CLUSTER),
                CountPlacement.of(12),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))
        );
    }

    private static ResourceKey<PlacedFeature> register(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(IndustrialRevolution.MODID, id));
    }
}

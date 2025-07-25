package me.steven.indrev.datagen.providers;

import me.steven.indrev.IndustrialRevolution;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class IRBiomeModifiersProvider {
    public static final ResourceKey<BiomeModifier> NIKOLITE_ORES = key("nikolite_ores");
    public static final ResourceKey<BiomeModifier> TIN_ORES = key("tin_ores");
    public static final ResourceKey<BiomeModifier> LEAD_ORES = key("lead_ores");
    public static final ResourceKey<BiomeModifier> SILVER_ORES = key("silver_ores");
    public static final ResourceKey<BiomeModifier> TUNGSTEN_ORES = key("tungsten_ores");
    public static final ResourceKey<BiomeModifier> SULFUR_CRYSTAL_CLUSTER = key("sulfur_crystal_overworld");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        context.register(NIKOLITE_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(IRPlacedFeatureProvider.NIKOLITE_ORES)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(TIN_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(IRPlacedFeatureProvider.TIN_ORES)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(LEAD_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(IRPlacedFeatureProvider.LEAD_ORES)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(SILVER_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(IRPlacedFeatureProvider.SILVER_ORES)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(TUNGSTEN_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(IRPlacedFeatureProvider.TUNGSTEN_ORES)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(SULFUR_CRYSTAL_CLUSTER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(IRPlacedFeatureProvider.SULFUR_CRYSTAL_FEATURE)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> key(String id) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(IndustrialRevolution.MODID, id));
    }
}

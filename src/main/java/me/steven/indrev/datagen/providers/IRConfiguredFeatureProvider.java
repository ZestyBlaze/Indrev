package me.steven.indrev.datagen.providers;

import com.google.common.collect.ImmutableList;
import me.steven.indrev.IndustrialRevolution;
import me.steven.indrev.registry.IRBlockRegistry;
import me.steven.indrev.registry.IRFeatureRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class IRConfiguredFeatureProvider {
    public static final ResourceKey<ConfiguredFeature<?, ?>> NIKOLITE_ORES = registerKey("nikolite_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORES = registerKey("tin_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORES = registerKey("lead_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORES = registerKey("silver_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORES = registerKey("tungsten_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_CRYSTAL_CLUSTER = registerKey("sulfur_crystal_cluster");

    private static final List<OreConfiguration.TargetBlockState> nikoliteTargets = ImmutableList.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), IRBlockRegistry.NIKOLITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), IRBlockRegistry.DEEPSLATE_NIKOLITE_ORE.get().defaultBlockState())
    );

    private static final List<OreConfiguration.TargetBlockState> tinTargets = ImmutableList.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), IRBlockRegistry.TIN_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), IRBlockRegistry.DEEPSLATE_TIN_ORE.get().defaultBlockState())
    );

    private static final List<OreConfiguration.TargetBlockState> leadTargets = ImmutableList.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), IRBlockRegistry.LEAD_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), IRBlockRegistry.DEEPSLATE_LEAD_ORE.get().defaultBlockState())
    );

    private static final List<OreConfiguration.TargetBlockState> silverTargets = ImmutableList.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), IRBlockRegistry.SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), IRBlockRegistry.DEEPSLATE_SILVER_ORE.get().defaultBlockState())
    );

    private static final List<OreConfiguration.TargetBlockState> tungstenTargets = ImmutableList.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), IRBlockRegistry.TUNGSTEN_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), IRBlockRegistry.DEEPSLATE_TUNGSTEN_ORE.get().defaultBlockState())
    );

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, NIKOLITE_ORES, Feature.ORE, new OreConfiguration(nikoliteTargets, 7));
        FeatureUtils.register(context, TIN_ORES, Feature.ORE, new OreConfiguration(tinTargets, 10));
        FeatureUtils.register(context, LEAD_ORES, Feature.ORE, new OreConfiguration(leadTargets, 6));
        FeatureUtils.register(context, SILVER_ORES, Feature.ORE, new OreConfiguration(silverTargets, 8));
        FeatureUtils.register(context, TUNGSTEN_ORES, Feature.ORE, new OreConfiguration(tungstenTargets, 5));
        FeatureUtils.register(context, SULFUR_CRYSTAL_CLUSTER, IRFeatureRegistry.SULFUR_CRYSTAL_FEATURE.get(), NoneFeatureConfiguration.NONE);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(IndustrialRevolution.MODID, name));
    }
}

package me.steven.indrev.registry;

import me.steven.indrev.IndustrialRevolution;
import me.steven.indrev.world.features.SulfurCrystalFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class IRFeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, IndustrialRevolution.MODID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SULFUR_CRYSTAL_FEATURE = FEATURES.register("sulfur_crystal_overworld",
            () -> new SulfurCrystalFeature(NoneFeatureConfiguration.CODEC)
    );
}

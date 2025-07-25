package me.steven.indrev.datagen.providers;

import me.steven.indrev.IndustrialRevolution;
import me.steven.indrev.registry.IRBlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class IRBlockTagsProvider extends BlockTagsProvider {
    public IRBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IndustrialRevolution.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(IRBlockRegistry.NIKOLITE_ORE.get(), IRBlockRegistry.TIN_ORE.get(), IRBlockRegistry.LEAD_ORE.get(),
                IRBlockRegistry.SILVER_ORE.get(), IRBlockRegistry.TUNGSTEN_ORE.get(), IRBlockRegistry.DEEPSLATE_NIKOLITE_ORE.get(), IRBlockRegistry.DEEPSLATE_TIN_ORE.get(),
                IRBlockRegistry.DEEPSLATE_LEAD_ORE.get(), IRBlockRegistry.DEEPSLATE_SILVER_ORE.get(), IRBlockRegistry.DEEPSLATE_TUNGSTEN_ORE.get(),
                IRBlockRegistry.SULFUR_CRYSTAL_CLUSTER.get()
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(IRBlockRegistry.PLANK_BLOCK.get(), IRBlockRegistry.PLANKS.get());
    }
}

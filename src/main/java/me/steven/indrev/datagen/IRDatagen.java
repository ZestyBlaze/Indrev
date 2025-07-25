package me.steven.indrev.datagen;

import me.steven.indrev.IndustrialRevolution;
import me.steven.indrev.datagen.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = IndustrialRevolution.MODID)
public class IRDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

        gen.addProvider(event.includeClient(), new IREnUsProvider(output));

        IRBlockTagsProvider blockTags = new IRBlockTagsProvider(output, lookup, fileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new IRItemTagsProvider(output, lookup, blockTags.contentsGetter()));
        gen.addProvider(event.includeServer(), new IRDatapackProvider(output, lookup));
        gen.addProvider(event.includeServer(), new IRBlockLootProvider(output, lookup));
    }
}

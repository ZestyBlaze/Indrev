package me.steven.indrev.datagen.providers;

import me.steven.indrev.IndustrialRevolution;
import me.steven.indrev.registry.IRBlockRegistry;
import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class IREnUsProvider extends LanguageProvider {
    public IREnUsProvider(PackOutput output) {
        super(output, IndustrialRevolution.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        Set<DeferredHolder<Block, ? extends Block>> blocks = new HashSet<>(IRBlockRegistry.BLOCKS.getEntries());
        Set<DeferredHolder<Item, ? extends Item>> items = new HashSet<>(IRItemRegistry.ITEMS.getEntries());

        add("itemGroup.indrev", "Industrial Revolution");

        blocks.forEach(i -> {
            String name = i.get().getDescriptionId().replaceFirst("block\\.indrev\\.", "");
            name = toTitleCase(name, "_");
            add(i.get().getDescriptionId(), name);
        });

        takeAll(items, i -> i.get() instanceof BlockItem);
        items.forEach(i -> {
            String name = i.get().getDescriptionId().replaceFirst("item\\.indrev\\.", "");
            name = fixStrings(toTitleCase(name, "_"));
            add(i.get().getDescriptionId(), name);
        });
    }

    public static String toTitleCase(String givenString, String regex) {
        String[] stringArray = givenString.split(regex);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stringArray) {
            stringBuilder.append(Character.toUpperCase(string.charAt(0))).append(string.substring(1)).append(regex);
        }
        return stringBuilder.toString().trim().replaceAll(regex, " ").substring(0, stringBuilder.length() - 1);
    }

    public static String fixStrings(String givenString) {
        return givenString.replaceAll(
                "Guide Book", "Revolutionary Guide"
        );
    }

    public static <T> void takeAll(Collection<T> src, Predicate<T> pred) {
        src.removeIf(pred);
    }

}

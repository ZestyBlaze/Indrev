package me.steven.indrev.registry;

import me.steven.indrev.Indrev;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.List;

public class IRCreativeModeTab {
    public static final CreativeModeTab TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(BuiltInRegistries.ITEM.getValue(Indrev.id("tin_ingot"))))
            .title(Component.literal("Industrial Revolution"))
            .displayItems((parameters, output) -> {
                List<Item> list = BuiltInRegistries.ITEM.stream()
                        .filter(item -> item.getDescriptionId().contains("indrev"))
                        .sorted(Comparator.comparing(IRCreativeModeTab::getMaterialType)
                                .thenComparing(IRCreativeModeTab::getItemTypeOrder)
                                .thenComparing(Item::getDescriptionId))
                        .toList();
                for (Item item : list) {
                    output.accept(item);
                }
            })
            .build();

    public static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Indrev.id("tab"), TAB);
    }

    private static String getMaterialType(Item item) {
        String descriptionId = item.getDescriptionId();
        int indrevIndex = descriptionId.indexOf("indrev.");

        if (indrevIndex < 0) {
            return descriptionId;
        }
        String path = descriptionId.substring(indrevIndex + 7);

        if (path.startsWith("deepslate_")) {
            path = path.substring(10);
        } else if (path.startsWith("raw_")) {
            path = path.substring(4);
        }

        int underscoreIndex = path.indexOf("_");
        if (underscoreIndex >= 0) {
            return path.substring(0, underscoreIndex);
        }
        return path;
    }

    private static int getItemTypeOrder(Item item) {
        String descriptionId = item.getDescriptionId();
        int indrevIndex = descriptionId.indexOf("indrev.");

        if (indrevIndex < 0) {
            return 999;
        }

        String path = descriptionId.substring(indrevIndex + 7);
        String lowerPath = path.toLowerCase();

        if (lowerPath.contains("purified_ore") || lowerPath.contains("purifiedore")) return 8;
        if (lowerPath.contains("deepslate") || lowerPath.startsWith("deepslate_")) return 2;
        if (lowerPath.contains("_ore") || lowerPath.endsWith("ore")) return 1;
        if (lowerPath.contains("raw_") && !lowerPath.endsWith("_block")) return 3;
        if (lowerPath.contains("raw_") || lowerPath.startsWith("raw_")) return 4;
        if (lowerPath.contains("_dust") || lowerPath.endsWith("dust")) return 4;
        if (lowerPath.contains("_ingot") || lowerPath.endsWith("ingot")) return 5;
        if (lowerPath.contains("_nugget") || lowerPath.endsWith("nugget")) return 6;
        if (lowerPath.contains("_chunk") || lowerPath.endsWith("chunk")) return 7;
        if (lowerPath.contains("_plate") || lowerPath.endsWith("plate")) return 9;
        return 999;
    }
}

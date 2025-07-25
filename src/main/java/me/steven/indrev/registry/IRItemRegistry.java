package me.steven.indrev.registry;

import me.steven.indrev.IndustrialRevolution;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class IRItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IndustrialRevolution.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, IndustrialRevolution.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> INDREV_TAB = CREATIVE_MODE_TABS.register("indrev", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.indrev"))
            .icon(Items.ACACIA_BOAT::getDefaultInstance)
            .displayItems((parameters, output) -> ITEMS.getEntries().forEach(item -> {
                output.accept(item.get());
            }))
            .build());

    public static final DeferredItem<Item> GUIDE_BOOK = ITEMS.registerSimpleItem("guide_book");
    public static final DeferredItem<Item> SAWDUST = ITEMS.registerSimpleItem("sawdust");

    public static final DeferredItem<Item> SULFUR_CRYSTAL_ITEM = ITEMS.registerSimpleItem("sulfur_crystal");
    public static final DeferredItem<BlockItem> PLANKS = ITEMS.registerSimpleBlockItem(IRBlockRegistry.PLANKS);
    public static final DeferredItem<BlockItem> PLANK_BLOCK = ITEMS.registerSimpleBlockItem(IRBlockRegistry.PLANK_BLOCK);
    
    public static final DeferredItem<BlockItem> NIKOLITE_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.NIKOLITE_ORE);
    public static final DeferredItem<BlockItem> TIN_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.TIN_ORE);
    public static final DeferredItem<BlockItem> LEAD_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.LEAD_ORE);
    public static final DeferredItem<BlockItem> SILVER_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.SILVER_ORE);
    public static final DeferredItem<BlockItem> TUNGSTEN_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.TUNGSTEN_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_NIKOLITE_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.DEEPSLATE_NIKOLITE_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_TIN_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.DEEPSLATE_TIN_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_LEAD_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.DEEPSLATE_LEAD_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_SILVER_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.DEEPSLATE_SILVER_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_TUNGSTEN_ORE = ITEMS.registerSimpleBlockItem(IRBlockRegistry.DEEPSLATE_TUNGSTEN_ORE);
}

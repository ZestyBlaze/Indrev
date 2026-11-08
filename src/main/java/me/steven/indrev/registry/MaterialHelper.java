package me.steven.indrev.registry;

import me.steven.indrev.Indrev;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MaterialHelper {
    private final String id;

    public MaterialHelper(String id) {
        this.id = id;
    }

    public static MaterialHelper start(String id) {
        return new MaterialHelper(id);
    }

    public MaterialHelper withItems(String... variants) {
        for (String variant : variants) {
            Identifier identifier = identifier(id + "_" + variant);
            MAP.put(identifier, () -> Registry.register(BuiltInRegistries.ITEM, identifier,
                    new Item(itemSettings().setId(itemId(identifier)))));
        }
        return this;
    }

    public MaterialHelper withItem() {
        Identifier identifier = identifier(id);
        Registry.register(BuiltInRegistries.ITEM, identifier, new Item(itemSettings().setId(itemId(identifier))));
        return this;
    }

    public MaterialHelper withOre(boolean rawOre) {
        return withOre(rawOre, Block::new);
    }

    public MaterialHelper withOre(boolean rawOre, Function<BlockBehaviour.Properties, Block> supplier) {
        Identifier identifier = identifier(id + "_ore");
        Block ore = supplier.apply(oreSettings(identifier));
        MAP.put(identifier, () -> {
            Registry.register(BuiltInRegistries.BLOCK, identifier, ore);
            Registry.register(BuiltInRegistries.ITEM, identifier, new BlockItem(ore, itemSettings().useBlockDescriptionPrefix().setId(itemId(identifier))));
        });

        Identifier deepslateId = identifier("deepslate_" + id + "_ore");
        Block deepslateOre = supplier.apply(oreSettings(deepslateId));
        MAP.put(deepslateId, () -> {
            Registry.register(BuiltInRegistries.BLOCK, deepslateId, deepslateOre);
            Registry.register(BuiltInRegistries.ITEM, deepslateId, new BlockItem(deepslateOre, itemSettings().useBlockDescriptionPrefix().setId(itemId(deepslateId))));
        });

        if (rawOre) {
            Identifier rawOreId = identifier("raw_" + id);
            Identifier rawOreBlockId = identifier("raw_" + id + "_block");
            Block rawOreBlock = supplier.apply(oreSettings(rawOreBlockId));
            MAP.put(rawOreId, () -> {
                Registry.register(BuiltInRegistries.BLOCK, rawOreBlockId, rawOreBlock);
                Registry.register(BuiltInRegistries.ITEM, rawOreBlockId, new BlockItem(rawOreBlock, itemSettings().useBlockDescriptionPrefix().setId(itemId(rawOreBlockId))));
                Registry.register(BuiltInRegistries.ITEM, rawOreId, new Item(itemSettings().setId(itemId(rawOreId))));
            });
        }

        return this;
    }


    public MaterialHelper withTools(Item pickaxe, Item axe, Item shovel, Item sword, Item hoe) {
        MAP.put(identifier(id + "_pickaxe"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_pickaxe"), pickaxe));
        MAP.put(identifier(id + "_axe"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_axe"), axe));
        MAP.put(identifier(id + "_shovel"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_shovel"), shovel));
        MAP.put(identifier(id + "_sword"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_sword"), sword));
        MAP.put(identifier(id + "_hoe"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_hoe"), hoe));
        return this;
    }

    /*
    public MaterialHelper withArmor(ArmorMaterial material) {
        MAP.put(identifier(id + "_helmet"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_helmet"), new ArmorItem(material, EquipmentSlot.HEAD, itemSettings())));
        MAP.put(identifier(id + "_chestplate"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_chestplate"), new ArmorItem(material, EquipmentSlot.CHEST, itemSettings())));
        MAP.put(identifier(id + "_leggings"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_leggings"), new ArmorItem(material, EquipmentSlot.LEGS, itemSettings())));
        MAP.put(identifier(id + "_boots"), () -> Registry.register(BuiltInRegistries.ITEM, identifier(id + "_boots"), new ArmorItem(material, EquipmentSlot.FEET, itemSettings())));
        return this;
    }
     */

    public MaterialHelper withBlock() {
        final Block[] blockRef = new Block[1];
        Identifier blockId = identifier(id + "_block");
        MAP.put(blockId, () -> {
            blockRef[0] = new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
                    .strength(5f, 6f)
                    .setId(blockId(blockId)));
            Registry.register(BuiltInRegistries.BLOCK, blockId, blockRef[0]);
            Registry.register(BuiltInRegistries.ITEM, blockId, new BlockItem(blockRef[0], itemSettings().useBlockDescriptionPrefix().setId(itemId(blockId))));
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                //BlockRenderLayerMap.INSTANCE.putBlock(blockRef[0], RenderLayer.getCutout());
            }
        });
        return this;
    }

    private Identifier identifier(String path) {
        return Indrev.id(path);
    }

    private ResourceKey<Item> itemId(Identifier id) {
        return ResourceKey.create(Registries.ITEM, id);
    }

    private ResourceKey<Block> blockId(Identifier id) {
        return ResourceKey.create(Registries.BLOCK, id);
    }

    private Item.Properties itemSettings() {
        return new Item.Properties();
    }

    private BlockBehaviour.Properties oreSettings(Identifier identifier) {
        return BlockBehaviour.Properties.of()
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .strength(3f, 3f)
                .setId(blockId(identifier));
    }

    public static void registerAll() {
        List<Map.Entry<Identifier, Runnable>> entries = new ArrayList<>(MAP.entrySet());

        entries.sort(
                Comparator.<Map.Entry<Identifier, Runnable>>comparingInt(e -> e.getKey().getPath().contains("ore") && !e.getKey().getPath().contains("purified") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("raw") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("block") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("ingot") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("chunk") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("dust") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("purified") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("plate") && !e.getKey().getPath().contains("chestplate") ? 1 : 0)
                        .thenComparingInt(e -> e.getKey().getPath().contains("nugget") ? 1 : 0)
                        .thenComparing(Comparator.<Map.Entry<Identifier, Runnable>, String>comparing(e -> {
                                    int underscoreIndex = e.getKey().getPath().indexOf("_");
                                    return underscoreIndex >= 0 ? e.getKey().getPath().substring(0, underscoreIndex) : e.getKey().getPath();
                                })
                                .thenComparingInt(e -> e.getKey().getPath().contains("sword") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("pickaxe") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("axe") && !e.getKey().getPath().contains("pickaxe") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("shovel") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("hoe") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("helmet") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("chestplate") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("leggings") ? 1 : 0)
                                .thenComparingInt(e -> e.getKey().getPath().contains("boots") ? 1 : 0)
                        )
        );

        List<Map.Entry<Identifier, Runnable>> ab = entries.reversed();
        
        for (Map.Entry<Identifier, Runnable> entry : ab) {
            entry.getValue().run();
        }
    }

    private static final Map<Identifier, Runnable> MAP = new HashMap<>();
}

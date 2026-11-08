package me.steven.indrev.registry;

import me.steven.indrev.Indrev;
import me.steven.indrev.tools.IRToolMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class IRItemRegistry {
    public static void register() {
        MaterialHelper.start("tin").withItems("dust", "ingot", "plate", "nugget", "chunk", "purified_ore")
                .withBlock().withOre(true)
                .withTools(
                        new Item(new Item.Properties().pickaxe(IRToolMaterial.TIN, 1, -1.0f)
                                .setId(setId("tin_sword"))),
                        new Item(new Item.Properties().axe(IRToolMaterial.TIN, 4.5f, -2.0f)
                                .setId(setId("tin_sword"))),
                        new Item(new Item.Properties().shovel(IRToolMaterial.TIN, 1.5f, -0.7f)
                                .setId(setId("tin_sword"))),
                        new Item(new Item.Properties().sword(IRToolMaterial.TIN, 3, -1.5f)
                                .setId(setId("tin_sword"))),
                        new Item(new Item.Properties().hoe(IRToolMaterial.TIN, 2, -0.5f)
                                .setId(setId("tin_sword")))
                );

        MaterialHelper.start("copper").withItems("dust", "plate", "nugget", "chunk", "purified_ore");

        MaterialHelper.start("steel").withItems("dust", "ingot", "plate", "nugget")
                .withBlock();

        MaterialHelper.start("iron").withItems("dust", "plate", "chunk", "purified_ore");

        MaterialHelper.start("netherite_scrap").withItems("dust", "chunk", "purified_ore");

        MaterialHelper.start("enriched_nikolite").withItems("dust", "ingot");

        MaterialHelper.start("diamond").withItems("dust");

        MaterialHelper.start("nikolite").withItems("dust", "ingot")
                .withOre(false);
    }

    private static ResourceKey<Item> setId(String id) {
        return ResourceKey.create(Registries.ITEM, Indrev.id(id));
    }
}

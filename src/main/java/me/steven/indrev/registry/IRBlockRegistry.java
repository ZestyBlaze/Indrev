package me.steven.indrev.registry;

import me.steven.indrev.Indrev;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class IRBlockRegistry {

    public static void init() {
    }

    public static ResourceKey<Block> key(String id) {
        return ResourceKey.create(Registries.BLOCK, Indrev.id(id));
    }
}

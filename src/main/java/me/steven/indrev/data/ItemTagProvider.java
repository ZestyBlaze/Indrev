package me.steven.indrev.data;

import me.steven.indrev.Indrev;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    protected ItemAppender builder(TagKey<Item> tag) {
        return new ItemAppender(super.builder(tag));
    }

    private static ResourceKey<Item> key(ItemLike item) {
        return item.asItem().builtInRegistryHolder().key();
    }

    private static final class ItemAppender extends BlockItemTagAppender<Item> {
        public ItemAppender(BlockItemTagAppender<Item> original) {
            super(original);
        }

        @Override
        protected ResourceKey<Item> convertElement(net.minecraft.references.BlockItemId element) {
            return element.item();
        }

        public ItemAppender add(ItemLike... items) {
            for (ItemLike it : items) {
                add(key(it));
            }
            return this;
        }

        public ItemAppender forceAddTag(TagKey<Item> tag) {
            addOptionalTag(tag);
            return this;
        }
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(Indrev.TIN_TOOL_MATERIAL).add(ResourceKey.create(Registries.ITEM, Indrev.id("tin_ingot")));
    }
}

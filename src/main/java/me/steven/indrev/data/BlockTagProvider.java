package me.steven.indrev.data;

import me.steven.indrev.Indrev;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    protected BlockAppender builder(TagKey<Block> tag) {
        return new BlockAppender(super.builder(tag));
    }

    private static ResourceKey<Block> key(Block block) {
        return block.builtInRegistryHolder().key();
    }

    private static final class BlockAppender extends BlockItemTagAppender<Block> {
        BlockAppender(BlockItemTagAppender<Block> original) {
            super(original);
        }

        @Override
        protected ResourceKey<Block> convertElement(net.minecraft.references.BlockItemId element) {
            return element.block();
        }

        public BlockAppender add(Block... blocks) {
            for (Block it : blocks) {
                add(key(it));
            }
            return this;
        }

        public BlockAppender addOptional(Block block) {
            addOptional(key(block));
            return this;
        }

        public BlockAppender forceAddTag(TagKey<Block> tag) {
            addOptionalTag(tag);
            return this;
        }
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(Indrev.INCORRECT_FOR_TIN_TOOL).forceAddTag(BlockTags.NEEDS_IRON_TOOL);
    }
}

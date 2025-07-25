package me.steven.indrev.datagen.providers;

import me.steven.indrev.registry.IRBlockRegistry;
import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import me.steven.indrev.blocks.misc.PlankBlock;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class IRBlockLootProvider extends LootTableProvider {
    public IRBlockLootProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(new SubProviderEntry(IRSubLootProvider::new, LootContextParamSets.BLOCK)), registries);
    }

    private static class IRSubLootProvider extends BlockLootSubProvider {
        public IRSubLootProvider(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return IRBlockRegistry.BLOCKS.getEntries()
                    .stream()
                    .map(e -> (Block) e.value())
                    .toList();
        }

        @Override
        protected void generate() {
            HolderLookup.RegistryLookup<Enchantment> lookup = registries.lookupOrThrow(Registries.ENCHANTMENT);

            add(IRBlockRegistry.NIKOLITE_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.TIN_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.LEAD_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.SILVER_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.TUNGSTEN_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.DEEPSLATE_NIKOLITE_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.DEEPSLATE_TIN_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.DEEPSLATE_LEAD_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.DEEPSLATE_SILVER_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));
            add(IRBlockRegistry.DEEPSLATE_TUNGSTEN_ORE.get(), block -> createOreDrop(block, IRItemRegistry.SAWDUST.get()));

            add(IRBlockRegistry.SULFUR_CRYSTAL_CLUSTER.get(), block -> LootTable.lootTable().withPool(
                    LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(
                            applyExplosionDecay(block, LootItem.lootTableItem(IRItemRegistry.SULFUR_CRYSTAL_ITEM.get()).apply(
                                    SetItemCountFunction.setCount(UniformGenerator.between(1, 3))
                            ).apply(
                                    ApplyBonusCount.addUniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE), 2)
                            ))
                    ))
            );
            add(IRBlockRegistry.PLANKS.get(), (block) -> LootTable.lootTable().withPool(
                    LootPool.lootPool().when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS))
                            .add(AlternativesEntry.alternatives(PlankBlock.LAYERS.getPossibleValues(), (layer) -> layer == 8 ?
                                            LootItem.lootTableItem(IRBlockRegistry.PLANK_BLOCK) :
                                            LootItem.lootTableItem(IRBlockRegistry.PLANKS)
                                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly((float)layer)))
                                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                    .hasProperty(PlankBlock.LAYERS, layer)))))));
            dropSelf(IRBlockRegistry.PLANK_BLOCK.get());

            dropSelf(IRBlockRegistry.MACHINE_BLOCK.get());
        }
    }
}

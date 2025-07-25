package me.steven.indrev.registry;

import me.steven.indrev.IndustrialRevolution;
import me.steven.indrev.blocks.misc.PlankBlock;
import me.steven.indrev.blocks.misc.SulfurCrystalBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("deprecation")
public class IRBlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(IndustrialRevolution.MODID);

    public static final DeferredBlock<Block> SULFUR_CRYSTAL_CLUSTER = BLOCKS.register("sulfur_crystal", () -> new SulfurCrystalBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().strength(3f, 3f)));
    public static final DeferredBlock<Block> NIKOLITE_ORE = BLOCKS.register("nikolite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 5), BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops().strength(3f, 3f)));
    public static final DeferredBlock<Block> TIN_ORE = BLOCKS.registerSimpleBlock("tin_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> LEAD_ORE = BLOCKS.registerSimpleBlock("lead_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> SILVER_ORE = BLOCKS.registerSimpleBlock("silver_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> TUNGSTEN_ORE = BLOCKS.registerSimpleBlock("tungsten_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> DEEPSLATE_NIKOLITE_ORE = BLOCKS.register("deepslate_nikolite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 5), BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops().strength(3f, 3f)));
    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = BLOCKS.registerSimpleBlock("deepslate_tin_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> DEEPSLATE_LEAD_ORE = BLOCKS.registerSimpleBlock("deepslate_lead_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = BLOCKS.registerSimpleBlock("deepslate_silver_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> DEEPSLATE_TUNGSTEN_ORE = BLOCKS.registerSimpleBlock("deepslate_tungsten_ore", BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> MACHINE_BLOCK = BLOCKS.registerSimpleBlock("machine_block", BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().strength(3f, 3f));
    public static final DeferredBlock<Block> PLANKS = BLOCKS.register("planks", () -> new PlankBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_BROWN).strength(2f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> PLANK_BLOCK = BLOCKS.registerSimpleBlock("plank_block", BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_BROWN).strength(3f, 6f).sound(SoundType.WOOD));

    public static final DeferredBlock<Block> COOLANT = BLOCKS.register("coolant" ,() -> new LiquidBlock(
            IRFluidRegistry.COOLANT_FLOWING.get(), BlockBehaviour.Properties.ofLegacyCopy(Blocks.LAVA)));
}

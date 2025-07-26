package me.steven.indrev;

import me.steven.indrev.config.IRConfig;
import me.steven.indrev.registry.IRBlockRegistry;
import me.steven.indrev.registry.IRFeatureRegistry;
import me.steven.indrev.registry.IRFluidRegistry;
import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IndustrialRevolution.MODID)
public class IndustrialRevolution {
    public static final String MODID = "indrev";
    public static final Logger LOGGER = LogManager.getLogger("Industrial Revolution");

    public IndustrialRevolution(IEventBus bus, ModContainer modContainer) {
        IRConfig.readConfigs(modContainer);
        IRBlockRegistry.BLOCKS.register(bus);
        IRItemRegistry.ITEMS.register(bus);
        IRItemRegistry.CREATIVE_MODE_TABS.register(bus);
        IRFeatureRegistry.FEATURES.register(bus);
        IRFluidRegistry.FLUIDS.register(bus);
        IRFluidRegistry.FLUID_TYPES.register(bus);
        bus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(IRBlockRegistry.PLANKS.get(), 10, 40);
        fireBlock.setFlammable(IRBlockRegistry.PLANK_BLOCK.get(), 10, 40);
    }
}

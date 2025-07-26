package me.steven.indrev.registry;

import me.steven.indrev.IndustrialRevolution;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class IRFluidRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, IndustrialRevolution.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, IndustrialRevolution.MODID);

    public static final DeferredHolder<FluidType, FluidType> COOLANT_FLUID_TYPE = FLUID_TYPES.register("coolant", () -> new FluidType(
            FluidType.Properties.create()
                    .descriptionId("fluid.indrev.coolant")
                    .adjacentPathType(PathType.WATER)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .density(10)
                    .viscosity(15)
    ));
    public static final DeferredHolder<Fluid, FlowingFluid> COOLANT_FLOWING = FLUIDS.register("coolant_flowing", () ->
            new BaseFlowingFluid.Flowing(IRFluidRegistry.COOLANT_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> COOLANT_STILL = FLUIDS.register("coolant", () -> new BaseFlowingFluid.Source(
            IRFluidRegistry.COOLANT_PROPERTIES));
    public static final BaseFlowingFluid.Properties COOLANT_PROPERTIES = new BaseFlowingFluid.Properties(COOLANT_FLUID_TYPE, COOLANT_STILL, COOLANT_FLOWING)
            .block(IRBlockRegistry.COOLANT);
}

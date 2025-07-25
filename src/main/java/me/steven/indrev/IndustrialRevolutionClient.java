package me.steven.indrev;

import me.steven.indrev.registry.IRFluidRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@Mod(value = IndustrialRevolution.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = IndustrialRevolution.MODID, value = Dist.CLIENT)
public class IndustrialRevolutionClient {
    public IndustrialRevolutionClient(ModContainer container) {
    }

    @SubscribeEvent
    public static void clientInit(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.withDefaultNamespace("water_still");
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.withDefaultNamespace("water_flow");
            }

            @Override
            public int getTintColor() {
                return 0x0C2340;
            }
        }, IRFluidRegistry.COOLANT_FLUID_TYPE);
    }
}

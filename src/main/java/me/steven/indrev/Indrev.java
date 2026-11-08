package me.steven.indrev;

import me.steven.indrev.registry.IRCreativeModeTab;
import me.steven.indrev.registry.IRItemRegistry;
import me.steven.indrev.registry.MaterialHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Indrev implements ModInitializer {
	public static final String MOD_ID = "indrev";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final TagKey<Item> TIN_TOOL_MATERIAL = TagKey.create(Registries.ITEM, id("tin_tool_material"));

	public static final TagKey<Block> INCORRECT_FOR_TIN_TOOL = TagKey.create(Registries.BLOCK, id("incorrect_for_tin_tool"));

	@Override
	public void onInitialize() {
		IRItemRegistry.register();

		//Last
		MaterialHelper.registerAll();
		IRCreativeModeTab.init();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

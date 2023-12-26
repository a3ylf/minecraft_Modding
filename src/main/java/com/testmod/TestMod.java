package com.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("test-mod");
	public static final Item luisamigo = Registry.register
			(Registries.ITEM, new Identifier("test-mod", "luisamigo"), new Item(new FabricItemSettings().maxCount(69)));
	public static final Block blocoamigo = new Block(FabricBlockSettings.create().strength(4.0f));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution

		FuelRegistry.INSTANCE.add(luisamigo,3000);
		CompostingChanceRegistry.INSTANCE.add(luisamigo, 10.0f);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
			content.addAfter(Items.REDSTONE_BLOCK, luisamigo);
		});

		Registry.register(Registries.BLOCK, new Identifier("test-mod", "blocoamigo"), blocoamigo);
		Registry.register(Registries.ITEM, new Identifier("test-mod", "blocoamigo"), new BlockItem(blocoamigo, new FabricItemSettings()));

		LOGGER.info("Hello Fabric world!");
	}
}
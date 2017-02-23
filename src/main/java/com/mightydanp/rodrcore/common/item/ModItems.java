package com.mightydanp.rodrcore.common.item;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.lib.BlockReference;
import com.mightydanp.rodrcore.common.lib.ItemReference;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @auther MightyDanp date class created: Jul 5, 2016
 */
public class ModItems extends CRegistryHandler {

	public static Item					rock;

	public static void preInit() {
		registerObject(rock = new MainItem(ItemReference.ROCK_NAME), ItemReference.ROCK_NAME, null);
		}

	public static void init() {
		registerOreDictionary();
		registerRecipes();
		registerSmeltingRecipes();
	}

	public static void registerOreDictionary() {
		registerOreDictionary(rock, ItemReference.ROCK_NAME, 0);
		}

	public static void registerRecipes() {}

	public static void registerSmeltingRecipes() {}
	
	public static void registerBlockMiningLevel() {}

}

package com.mightydanp.rodrcore.common.item;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.lib.BlockReference;
import com.mightydanp.rodrcore.common.lib.ItemReference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @auther MightyDanp date class created: Jul 5, 2016
 */
public class ModItems extends CRegistryHandler {

	public static Item                  unfired_clay_pan;
	public static Item                  clay_pan;
	public static Item                  pan;
	public static Item                  unfired_clay_pot;
	public static Item                  clay_pot;
	public static Item                  pot;
	public static Item                  small_crucible;
	public static Item					rock;
	public static Item                  flint_and_stone;

	public static void preInit() {
		registerObject(unfired_clay_pan = new CItem(ItemReference.UNFIREDCLAYPAN_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.UNFIREDCLAYPAN_NAME, null);
		registerObject(clay_pan = new CItem(ItemReference.CLAYPAN_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(16).setMaxStackSize(1), ItemReference.CLAYPAN_NAME, null);
		registerObject(pan = new CItem(ItemReference.PAN_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.PAN_NAME, null);
		registerObject(unfired_clay_pot = new CItem(ItemReference.UNFIREDCLAYPOT_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.UNFIREDCLAYPOT_NAME, null);
		registerObject(clay_pot = new CItem(ItemReference.CLAYPOT_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(16).setMaxStackSize(1), ItemReference.CLAYPOT_NAME, null);
		registerObject(pot = new CItem(ItemReference.POT_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.POT_NAME, null);
		registerObject(small_crucible = new CItem(ItemReference.SMALLCRUCIBLE_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.SMALLCRUCIBLE_NAME, null);
		registerObject(rock = new CItem(ItemReference.ROCK_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.ROCK_NAME, null);
		registerObject(flint_and_stone = new CItem(ItemReference.FLINTANDSTONE_NAME).setMaxDamage(1).setMaxStackSize(1).setCreativeTab(RodRCore.tabRodRCore), ItemReference.FLINTANDSTONE_NAME, null);
	}

	public static void init() {
		registerOreDictionary();
	}

	public static void registerOreDictionary() {
		registerOreDictionary(rock, ItemReference.ROCK_NAME, 0);
	}
	
	public static void registerBlockMiningLevel() {}

}

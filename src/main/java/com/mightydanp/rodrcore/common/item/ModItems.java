package com.mightydanp.rodrcore.common.item;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.api.common.item.CItemAxe;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.lib.BlockReference;
import com.mightydanp.rodrcore.common.lib.ItemReference;

import magicbees.main.Config;
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
	public static Item                  fired_clay_pan;
	public static Item                  pan;
	public static Item                  unfired_clay_pot;
	public static Item                  fired_clay_pot;
	public static Item                  pot;
	public static Item                  small_crucible;
	public static Item					rock;
	public static Item                  flint_and_stone;
	public static Item                  bark_strip;
	public static Item                  rope;
	public static Item                  salvaged_axe;
	
	public static Item.ToolMaterial salvagedToolMaterial = EnumHelper.addToolMaterial("salvaged", 1, 10, 0.7F, 0.0F, 0);

	public static void preInit() {
		registerObject(unfired_clay_pan = new CItem(ItemReference.UNFIREDCLAYPAN_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.UNFIREDCLAYPAN_NAME, null);
		registerObject(fired_clay_pan = new CItem(ItemReference.FIREDCLAYPAN_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(16).setMaxStackSize(1), ItemReference.FIREDCLAYPAN_NAME, null);
		registerObject(pan = new CItem(ItemReference.PAN_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.PAN_NAME, null);
		registerObject(unfired_clay_pot = new CItem(ItemReference.UNFIREDCLAYPOT_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.UNFIREDCLAYPOT_NAME, null);
		registerObject(fired_clay_pot = new CItem(ItemReference.FIREDCLAYPOT_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(16).setMaxStackSize(1), ItemReference.FIREDCLAYPOT_NAME, null);
		registerObject(pot = new CItem(ItemReference.POT_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.POT_NAME, null);
		registerObject(small_crucible = new CItem(ItemReference.SMALLCRUCIBLE_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.SMALLCRUCIBLE_NAME, null);
		registerObject(rock = new CItem(ItemReference.ROCK_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.ROCK_NAME, null);
		registerObject(flint_and_stone = new CItem(ItemReference.FLINTANDSTONE_NAME).setMaxDamage(1).setMaxStackSize(1).setCreativeTab(RodRCore.tabRodRCore), ItemReference.FLINTANDSTONE_NAME, null);
		registerObject(bark_strip = new CItem(ItemReference.BARKSTRIP_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.BARKSTRIP_NAME, null);
		registerObject(rope = new CItem(ItemReference.ROPE_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.ROPE_NAME, null);
		registerObject(salvaged_axe = new CItemAxe(salvagedToolMaterial, ItemReference.SALVAGEDAXE_NAME, 0).setCreativeTab(RodRCore.tabRodRCore), ItemReference.SALVAGEDAXE_NAME, null);
	}

	public static void init() {
		registerOreDictionary();
	}

	public static void registerOreDictionary() {
		registerOreDictionary(rock, ItemReference.ROCK_NAME, 0);
		registerOreDictionary(Config.manasteelScoop, ItemReference.SCOOP_NAME, 0);
		registerOreDictionary(Config.thaumiumScoop, ItemReference.SCOOP_NAME, 0);
	}
	
	public static void registerBlockMiningLevel() {}

}

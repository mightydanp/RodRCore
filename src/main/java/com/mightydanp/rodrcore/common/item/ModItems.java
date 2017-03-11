package com.mightydanp.rodrcore.common.item;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceSmallCrucibleRecipes;
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

	public static Item                  ash;
	public static Item                  unfiredClayPan;
	public static Item                  clayPan;
	public static Item                  pan;
	public static Item                  unfiredClayPot;
	public static Item                  clayPot;
	public static Item                  pot;
	public static Item                  smallCrucible;
	public static Item					rock;

	public static void preInit() {
		registerObject(ash = new CItem(ItemReference.ASH_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.ASH_NAME, null);
		registerObject(unfiredClayPan = new CItem(ItemReference.UNFIREDCLAYPAN_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.UNFIREDCLAYPAN_NAME, null);
		registerObject(clayPan = new CItem(ItemReference.CLAYPAN_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(16).setMaxStackSize(1), ItemReference.CLAYPAN_NAME, null);
		registerObject(pan = new CItem(ItemReference.PAN_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.PAN_NAME, null);
		registerObject(unfiredClayPot = new CItem(ItemReference.UNFIREDCLAYPOT_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.UNFIREDCLAYPOT_NAME, null);
		registerObject(clayPot = new CItem(ItemReference.CLAYPOT_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(16).setMaxStackSize(1), ItemReference.CLAYPOT_NAME, null);
		registerObject(pot = new CItem(ItemReference.POT_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.POT_NAME, null);
		registerObject(smallCrucible = new CItem(ItemReference.SMALLCRUCIBLE_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(64).setMaxStackSize(1), ItemReference.SMALLCRUCIBLE_NAME, null);
		registerObject(rock = new CItem(ItemReference.ROCK_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.ROCK_NAME, null);
		}

	public static void init() {
		registerOreDictionary();
	}

	public static void registerOreDictionary() {
		registerOreDictionary(ash, "dustAsh", 0);
		registerOreDictionary(rock, ItemReference.ROCK_NAME, 0);
	}
	
	public static void registerBlockMiningLevel() {}

}

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
		registerObject(clayPan = new ItemClayPan(ItemReference.CLAYPAN_NAME), ItemReference.CLAYPAN_NAME, null);
		registerObject(pan = new ItemPan(ItemReference.PAN_NAME), ItemReference.PAN_NAME, null);
		registerObject(unfiredClayPot = new CItem(ItemReference.UNFIREDCLAYPOT_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.UNFIREDCLAYPOT_NAME, null);
		registerObject(clayPot = new ItemClayPot(ItemReference.CLAYPOT_NAME), ItemReference.CLAYPOT_NAME, null);
		registerObject(pot = new ItemPot(ItemReference.POT_NAME), ItemReference.POT_NAME, null);
		registerObject(smallCrucible = new CItem(ItemReference.SMALLCRUCIBLE_NAME).setCreativeTab(RodRCore.tabRodRCore).setMaxDamage(1).setMaxStackSize(1), ItemReference.SMALLCRUCIBLE_NAME, null);
		registerObject(rock = new CItem(ItemReference.ROCK_NAME).setCreativeTab(RodRCore.tabRodRCore), ItemReference.ROCK_NAME, null);
		}

	public static void init() {
		registerOreDictionary();
		registerRecipes();
		registerSmeltingRecipes();
	}

	public static void registerOreDictionary() {
		registerOreDictionary(ash, "dustAsh", 0);
		registerOreDictionary(rock, ItemReference.ROCK_NAME, 0);
		}

	public static void registerRecipes() {}

	public static void registerSmeltingRecipes() {
		CampfirePanRecipes.addRecipe(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.35F);
		CampfirePanRecipes.addRecipe(Items.beef, new ItemStack(Items.cooked_beef), 0.35F);
		CampfirePanRecipes.addRecipe(Items.chicken, new ItemStack(Items.cooked_chicken), 0.35F);
		
		ItemFishFood.FishType[] afishtype = ItemFishFood.FishType.values();
		
		int i = afishtype.length;

        for (int j = 0; j < i; ++j)
        {
            ItemFishFood.FishType fishtype = afishtype[j];

            if (fishtype.func_150973_i())
            {
            	CampfirePanRecipes.addRecipe(new ItemStack(Items.fish, 1, fishtype.func_150976_a()), new ItemStack(Items.cooked_fished, 1, fishtype.func_150976_a()), 0.35F);
            }
        }
        
        CampfirePotRecipes.addRecipe(Items.potato, new ItemStack(Items.baked_potato), 0.35F);
		CampfireSmallCrucibleRecipes.addRecipe(ModItems.unfiredClayPan, new ItemStack (ModItems.clayPan), 0.0F);
		CampfireSmallCrucibleRecipes.addRecipe(ModItems.unfiredClayPot, new ItemStack (ModItems.clayPot), 0.0F);
	
	}
	
	public static void registerBlockMiningLevel() {}

}

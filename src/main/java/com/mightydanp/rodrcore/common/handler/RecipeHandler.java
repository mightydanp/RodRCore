package com.mightydanp.rodrcore.common.handler;

import com.mightydanp.rodrcore.api.common.handler.CRecipeHandler;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.lib.ItemReference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class RecipeHandler extends CRecipeHandler{
	
	private static ItemFishFood.FishType[] aFishType = ItemFishFood.FishType.values();
	private static int aFishLength = aFishType.length;
	
	public static void init() {
		craftingRecipes();
		furnaceRecipes();
		campfireRecipes();
		newFurnaceRecipes();
	}

	private static void craftingRecipes() {
		
	}
	
	private static void furnaceRecipes() {}
	
    private static void campfireRecipes() {
    	registerCampfireRecipe(Items.porkchop, 0, Items.cooked_porkchop, 0, 1, 0.35F, ItemReference.PAN_NAME);
    	registerCampfireRecipe(Items.beef, 0,Items.cooked_beef, 0, 1, 0.35F, ItemReference.PAN_NAME);
    	registerCampfireRecipe(Items.chicken, 0,Items.cooked_chicken, 0, 1, 0.35F, ItemReference.PAN_NAME);
    	
    	for (int j = 0; j < aFishLength; ++j){
            ItemFishFood.FishType fishtype = aFishType[j];

            if (fishtype.func_150973_i())
            	registerCampfireRecipe(Items.fish, fishtype.func_150976_a(), Items.cooked_fished, 1, fishtype.func_150976_a(), 0.35F, ItemReference.PAN_NAME);
        }
    	
    	registerCampfireRecipe(Items.potato, 0, Items.baked_potato, 0, 1, 0.35F, ItemReference.POT_NAME);
    	registerCampfireRecipe(ModItems.unfiredClayPan, 0, ModItems.clayPan, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerCampfireRecipe(ModItems.unfiredClayPot, 0, ModItems.clayPot, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerCampfireRecipe(Blocks.sand, 0, Blocks.glass, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerCampfireRecipe(Blocks.sand, 1, Blocks.glass, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    }
	
	private static void newFurnaceRecipes() {
		registerNewFurnaceRecipe(Items.porkchop, 0, Items.cooked_porkchop, 0, 1, 0.35F, ItemReference.PAN_NAME);
		registerNewFurnaceRecipe(Items.beef, 0,Items.cooked_beef, 0, 1, 0.35F, ItemReference.PAN_NAME);
		registerNewFurnaceRecipe(Items.chicken, 0,Items.cooked_chicken, 0, 1, 0.35F, ItemReference.PAN_NAME);
    	
    	for (int j = 0; j < aFishLength; ++j){
            ItemFishFood.FishType fishtype = aFishType[j];

            if (fishtype.func_150973_i())
            	registerNewFurnaceRecipe(Items.fish, fishtype.func_150976_a(), Items.cooked_fished, 1, fishtype.func_150976_a(), 0.35F, ItemReference.PAN_NAME);
        }
    	
    	registerNewFurnaceRecipe(Items.potato, 0, Items.baked_potato, 0, 1, 0.35F, ItemReference.POT_NAME);
    	registerNewFurnaceRecipe(ModItems.unfiredClayPan, 0, ModItems.clayPan, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(ModItems.unfiredClayPot, 0, ModItems.clayPot, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.sand, 0, Blocks.glass, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.sand, 1, Blocks.glass, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.coal_ore, 0, Items.coal, 0, 1, 0.1F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.redstone_ore, 0,Items.redstone, 0, 1, 0.7F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.lapis_ore, 0, Items.dye, 4, 1, 0.2F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.quartz_ore, 0, Items.quartz, 0, 1, 0.2F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.iron_ore, 0, Items.iron_ingot, 0, 1, 0.7F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.gold_ore, 0, Items.gold_ingot, 0, 1, 1.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerNewFurnaceRecipe(Blocks.diamond_ore, 0, Items.diamond, 0, 1, 1.0F, ItemReference.SMALLCRUCIBLE_NAME);
		
	}
 
}

package com.mightydanp.rodrcore.common.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mightydanp.rodrcore.api.common.handler.CRecipeHandler;
import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.lib.ItemReference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler extends CRecipeHandler{
	
	private static ItemFishFood.FishType[] aFishType = ItemFishFood.FishType.values();
	private static int aFishLength = aFishType.length;
	static TileEntityNewFurnace furnace;
	
	public static void init() {
		craftingRecipes();
		furnaceRecipes();
		campfireRecipes();
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
    	registerCampfireRecipe(ModItems.unfired_clay_pan, 0, ModItems.clay_pan, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerCampfireRecipe(ModItems.unfired_clay_pot, 0, ModItems.clay_pot, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerCampfireRecipe(Blocks.sand, 0, Blocks.glass, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	registerCampfireRecipe(Blocks.sand, 1, Blocks.glass, 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    }
    	/*(Set<Map.Entry<ItemStack, ItemStack>> set = FurnaceRecipes.smelting().getSmeltingList().entrySet();
    	
    	for(Map.Entry<ItemStack, ItemStack> obj: set) {
    		ItemStack input = (ItemStack)obj.getKey();
    	    ItemStack output = (ItemStack) obj.getValue();
    	    
    	    List<String> input_names = findAllOreNames(input);
    	    List<String> output_names = findAllOreNames(output);
    	    
    	    System.out.println(input_names);
    	    
    	    
    	    for (String element : input_names) {
    	    	System.out.println(element);
    	    	if(element.startsWith("ore")){
    	    		registerNewFurnaceRecipe(input.getItem(), 0, output.getItem(), 0, 1, 0.0F, "");
    	    	}
    	    }
    	}
    	
    	registerNewFurnaceRecipe("", 0, "", 0, 1, 0.0F, ItemReference.SMALLCRUCIBLE_NAME);
    	

	private static List<String> findAllOreNames(ItemStack input) {
		int[] ids = OreDictionary.getOreIDs(input);
		ArrayList<String> results = new ArrayList<String>();
		for(int id : ids){
			String name = OreDictionary.getOreName(id);
			results.add(name);
		}
		return results;
	}
	*/
 
}

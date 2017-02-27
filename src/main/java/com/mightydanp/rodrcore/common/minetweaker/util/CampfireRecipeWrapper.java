package com.mightydanp.rodrcore.common.minetweaker.util;

import org.lwjgl.Sys;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;

import net.minecraft.item.ItemStack;

public class CampfireRecipeWrapper 
{
	private OreRecipeElement input;
	private ItemStack output;
	private float xp;
	private String neededItem;
	
	public CampfireRecipeWrapper(OreRecipeElement input, ItemStack output, float xp, String neededItem) 
	{
		this.input = input;
		this.output = output;
		this.xp = xp;
		this.neededItem = neededItem;
	}
	
	public OreRecipeElement getInput()
	{
		return input;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
	
	public float getXP()
	{
		return xp;
	}
	
	public String isRecipe() 
	{
		return neededItem = "null";
	}
	
	public String isPanRecipe() 
	{
		return neededItem = "pan";
	}
	
	public String isPotRecipe() 
	{
		return neededItem = "pot";
	}
	
	public String isSmallCrucibleRecipe() 
	{
		return neededItem = "small_crucible";
	}

	public void add()
	{
		if(neededItem == "null")
		{
			CampfireRecipes.smelting().putLists(input, output, xp);
		}
		if(neededItem == "pan")
		{
			CampfirePanRecipes.smelting().putLists(input, output, xp);
		}
		if(neededItem == "pot")
		{
			CampfirePotRecipes.smelting().putLists(input, output, xp);
		}
		if(neededItem == "small_crucible")
		{
			CampfireSmallCrucibleRecipes.smelting().putLists(input, output, xp);
		}
	}
	
}

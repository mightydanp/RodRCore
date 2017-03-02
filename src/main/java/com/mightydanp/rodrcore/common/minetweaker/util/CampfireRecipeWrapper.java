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
	
	public Boolean isRecipe() 
	{
		neededItem = "null";
		return true;
	}
	
	public Boolean isPanRecipe() 
	{
		neededItem = "pan";
		return  true;
	}
	
	public Boolean isPotRecipe() 
	{
		neededItem = "pot";
		return true;
	}
	
	public Boolean isSmallCrucibleRecipe() 
	{
		neededItem = "small_crucible";
		return true;
	}

	public void add()
	{
		if(isRecipe())
		{
			CampfireRecipes.smelting().putLists(input, output, xp);
		}
		if(isPanRecipe())
		{
			CampfirePanRecipes.smelting().putLists(input, output, xp);
		}
		if(isPotRecipe())
		{
			CampfirePotRecipes.smelting().putLists(input, output, xp);
		}
		if(isSmallCrucibleRecipe())
		{
			CampfireSmallCrucibleRecipes.smelting().putLists(input, output, xp);
		}
	}
	
}

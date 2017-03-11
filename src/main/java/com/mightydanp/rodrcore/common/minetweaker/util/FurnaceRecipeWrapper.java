package com.mightydanp.rodrcore.common.minetweaker.util;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceSmallCrucibleRecipes;

import net.minecraft.item.ItemStack;

public class FurnaceRecipeWrapper {
	private OreRecipeElement input;
	private ItemStack output;
	private float xp;
	private String neededItem;

	public FurnaceRecipeWrapper(OreRecipeElement input, ItemStack output, float xp, String neededItem) {
		this.input = input;
		this.output = output;
		this.xp = xp;
		this.neededItem = neededItem;
	}

	public OreRecipeElement getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}

	public float getXP() {
		return xp;
	}

	public Boolean isRecipe() {
		neededItem = "null";
		return true;
	}

	public Boolean isPanRecipe() {
		neededItem = "pan";
		return true;
	}

	public Boolean isPotRecipe() {
		neededItem = "pot";
		return true;
	}

	public Boolean isSmallCrucibleRecipe() {
		neededItem = "small_crucible";
		return true;
	}

	public void add() {
		if (isRecipe()) {
			FurnaceRecipes.smelting().putLists(input, output, xp);
		}
		if (isPanRecipe()) {
			FurnacePanRecipes.smelting().putLists(input, output, xp);
		}
		if (isPotRecipe()) {
			FurnacePotRecipes.smelting().putLists(input, output, xp);
		}
		if (isSmallCrucibleRecipe()) {
			FurnaceSmallCrucibleRecipes.smelting().putLists(input, output, xp);
		}
	}

}

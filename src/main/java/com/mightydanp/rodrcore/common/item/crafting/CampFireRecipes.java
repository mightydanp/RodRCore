package com.mightydanp.rodrcore.common.item.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class CampFireRecipes {
	private static final CampFireRecipes smeltingBase = new CampFireRecipes();
	/** The list of smelting results. */
	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();
	private static final String __OBFID = "CL_00000085";

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static CampFireRecipes smelting() {
		return smeltingBase;
	}

	private CampFireRecipes() {
	}

	public void campFireBlockSmelting(Block input,ItemStack output1, ItemStack output2, int precentOutput, float xpGained) {
		//this.campFireItemStackSmelting(Item.getItemFromBlock(input), output1, output2, precentOutput, xpGained);
	}

	public void campFireItemSmelting(Item item, ItemStack output1, ItemStack output2, int precentOutput, float xpGained) {
		this.campFireItemStackSmelting(new ItemStack(item, 1, 32767), output1, output2, precentOutput, xpGained);
	}

	public void campFireItemStackSmelting(ItemStack input, ItemStack output1, ItemStack output2, int precentOutput, float xpGained) {
		this.smeltingList.put(input, output1);
		this.experienceList.put(output1, Float.valueOf(xpGained));
	}

	/**
	 * Returns the smelting result of an item.
	 */
	public ItemStack getSmeltingResult(ItemStack itemStack) {
		Iterator iterator = this.smeltingList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return null;
			}

			entry = (Entry) iterator.next();
		} while (!this.campFireSmelting(itemStack, (ItemStack) entry.getKey()));

		return (ItemStack) entry.getValue();
	}

	private boolean campFireSmelting(ItemStack input, ItemStack output) {
		return output.getItem() == input.getItem()
				&& (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
	}

	public Map getSmeltingList() {
		return this.smeltingList;
	}

	public float smelting(ItemStack itemStack) {
		float ret = itemStack.getItem().getSmeltingExperience(itemStack);
		if (ret != -1)
			return ret;

		Iterator iterator = this.experienceList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return 0.0F;
			}

			entry = (Entry) iterator.next();
		} while (!this.campFireSmelting(itemStack, (ItemStack) entry.getKey()));

		return ((Float) entry.getValue()).floatValue();
	}
}
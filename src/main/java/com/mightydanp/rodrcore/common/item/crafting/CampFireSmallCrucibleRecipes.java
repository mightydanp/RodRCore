package com.mightydanp.rodrcore.common.item.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.common.item.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CampFireSmallCrucibleRecipes {
	private static final CampFireSmallCrucibleRecipes smeltingBase = new CampFireSmallCrucibleRecipes();
	/** The list of smelting results. */
	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();
	private static final String __OBFID = "CL_00000085";

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static CampFireSmallCrucibleRecipes smelting() {
		return smeltingBase;
	}

	private CampFireSmallCrucibleRecipes() {
		this.addRecipe(ModItems.unfiredClayPan, new ItemStack (ModItems.clayPan), 0.0F);
		this.addRecipe(ModItems.unfiredClayPot, new ItemStack (ModItems.clayPot), 0.0F);
	}

    public void addRecipe(Block input, ItemStack output, float experience)
    {
    	smelting().addLists(new OreRecipeElement(new ItemStack(Item.getItemFromBlock(input))), output, experience);
    }

    public void addRecipe(Item input, ItemStack output, float experience)
    {
        smelting().addLists(new OreRecipeElement(new ItemStack(input)), output, experience);
    }

	public static void addRecipe(ItemStack input, ItemStack output, float experience)
	{
		smelting().putLists(new OreRecipeElement(input), output, experience);
	}
    
	public static void addRecipe(String oreDictEntry, ItemStack output, float experience)
	{
		smelting().addLists(new OreRecipeElement(oreDictEntry, 1), output, experience);
	}

	public void addLists(OreRecipeElement input, ItemStack itemStack, float experience)
	{
		putLists(input, itemStack, experience);
	}
	
	public void putLists(OreRecipeElement input, ItemStack itemStack2, float experience)
	{
		smeltingList.put(input, itemStack2);
		experienceList.put(itemStack2, experience);
	}

	public ItemStack getSmeltingResult(ItemStack itemStack) {
		Iterator<Entry<OreRecipeElement, ItemStack>> iterator = smeltingList.entrySet().iterator();
		Entry<OreRecipeElement, ItemStack> entry;

		do {
			if (!iterator.hasNext()) {
				return null;
			}

			entry = (Entry) iterator.next();
		} while (!canBeSmelted(itemStack, entry.getKey()));

		return (ItemStack) entry.getValue();
	}

	private boolean canBeSmelted(ItemStack stack, OreRecipeElement ore)
	{
		return ore.matches(stack);
	}
	
	private boolean canBeSmelted(ItemStack stack, ItemStack stack2)
	{
		return stack2.getItem() == stack.getItem()
				&& (stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == stack
				.getItemDamage());
	}

	public float giveExperience(ItemStack stack)
	{
		Iterator<Entry<ItemStack, Float>> iterator = experienceList.entrySet().iterator();
		Entry<ItemStack, Float> entry;

		do
		{
			if (!iterator.hasNext())
			{
				return 0.0f;
			}

			entry = iterator.next();
		} while (!canBeSmelted(stack, (ItemStack)entry.getKey()));

		if (stack.getItem().getSmeltingExperience(stack) != -1)
		{
			return stack.getItem().getSmeltingExperience(stack);
		}

		return entry.getValue();
	}

	public static Map<OreRecipeElement, ItemStack> getSmeltingList()
	{
		return smelting().smeltingList;
	}
	
	public static Map<ItemStack, Float> getXPList()
	{
		return smelting().experienceList;
	}
}
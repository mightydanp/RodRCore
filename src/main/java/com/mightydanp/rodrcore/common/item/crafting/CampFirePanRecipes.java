package com.mightydanp.rodrcore.common.item.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class CampFirePanRecipes {
	private static final CampFirePanRecipes smeltingBase = new CampFirePanRecipes();
	/** The list of smelting results. */
	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();
	private static final String __OBFID = "CL_00000085";

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static CampFirePanRecipes smelting() {
		return smeltingBase;
	}

	private CampFirePanRecipes() {
        this.addRecipe(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.35F);
        this.addRecipe(Items.beef, new ItemStack(Items.cooked_beef), 0.35F);
        this.addRecipe(Items.chicken, new ItemStack(Items.cooked_chicken), 0.35F);
		
		ItemFishFood.FishType[] afishtype = ItemFishFood.FishType.values();
		
		int i = afishtype.length;

        for (int j = 0; j < i; ++j)
        {
            ItemFishFood.FishType fishtype = afishtype[j];

            if (fishtype.func_150973_i())
            {
                this.addRecipe(new ItemStack(Items.fish, 1, fishtype.func_150976_a()), new ItemStack(Items.cooked_fished, 1, fishtype.func_150976_a()), 0.35F);
            }
        }
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

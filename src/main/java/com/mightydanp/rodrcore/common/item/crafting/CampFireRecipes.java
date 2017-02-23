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
        this.smeltBlock(Blocks.sand, new ItemStack(Blocks.glass), 0.1F);
        this.smeltItem(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.35F);
        this.smeltItem(Items.beef, new ItemStack(Items.cooked_beef), 0.35F);
        this.smeltItem(Items.chicken, new ItemStack(Items.cooked_chicken), 0.35F);
        this.smeltBlock(Blocks.cactus, new ItemStack(Items.dye, 1, 2), 0.2F);
        this.smeltItem(Items.potato, new ItemStack(Items.baked_potato), 0.35F);
		
		ItemFishFood.FishType[] afishtype = ItemFishFood.FishType.values();
		
		int i = afishtype.length;

        for (int j = 0; j < i; ++j)
        {
            ItemFishFood.FishType fishtype = afishtype[j];

            if (fishtype.func_150973_i())
            {
                this.smeltItemStack(new ItemStack(Items.fish, 1, fishtype.func_150976_a()), new ItemStack(Items.cooked_fished, 1, fishtype.func_150976_a()), 0.35F);
            }
        }
	}

    public void smeltBlock(Block block, ItemStack output, float xp)
    {
        this.smeltItem(Item.getItemFromBlock(block), output, xp);
    }

    public void smeltItem(Item item, ItemStack output, float xp)
    {
        this.smeltItemStack(new ItemStack(item, 1, 32767), output, xp);
    }

    public void smeltItemStack(ItemStack input, ItemStack output, float xp)
    {
        this.smeltingList.put(input, output);
        this.experienceList.put(output, Float.valueOf(xp));
    }

	public ItemStack getSmeltingResult(ItemStack itemStack) {
		Iterator iterator = this.smeltingList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return null;
			}

			entry = (Entry) iterator.next();
		} while (!this.getResult(itemStack, (ItemStack) entry.getKey()));

		return (ItemStack) entry.getValue();
	}

	private boolean getResult(ItemStack input, ItemStack output) {
		return output.getItem() == input.getItem() && (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
	}

	public Map getSmeltingList() {
		return this.smeltingList;
	}

	public float func_151398_b(ItemStack p_151398_1_) {
		float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
		if (ret != -1)
			return ret;

		Iterator iterator = this.experienceList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return 0.0F;
			}

			entry = (Entry) iterator.next();
		} while (!this.getResult(p_151398_1_, (ItemStack) entry.getKey()));

		return ((Float) entry.getValue()).floatValue();
	}
}
package com.mightydanp.rodrcore.common.nei;

import java.util.*;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.client.gui.inventory.GuiCampFire;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.ItemList;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class NEICampfireRecipeHandler extends TemplateRecipeHandler
{
	public class CampfireCachedRecipe extends TemplateRecipeHandler.CachedRecipe
	{
		PositionedStack input;
		PositionedStack output;
		PositionedStack pan;
		PositionedStack pot;
		PositionedStack smallCrucible;

		public CampfireCachedRecipe(OreRecipeElement _input, ItemStack _output, String neededItem)
		{
			input = new PositionedStack(_input.getValidItems(), 51, 6);
			output = new PositionedStack(_output, 111, 10);
			
			if(neededItem == "pan")
			{
				pan = new PositionedStack(new ItemStack(ModItems.pan), 25, 24);
			}
			if(neededItem == "pot")
			{
				pot = new PositionedStack(new ItemStack(ModItems.pot), 25, 24);
			}
			if(neededItem == "small_crucible")
			{
				smallCrucible = new PositionedStack(new ItemStack(ModItems.smallCrucible), 25, 24);
			}
		}

		@Override
		public List<PositionedStack> getIngredients()
		{
			if (pan != null)
			{
				return getCycledIngredients(cycleticks / 48, Arrays.asList(input, pan));
			}
			if (pot != null)
			{
				return getCycledIngredients(cycleticks / 48, Arrays.asList(input, pot));
			}
			if (smallCrucible != null)
			{
				return getCycledIngredients(cycleticks / 48, Arrays.asList(input, smallCrucible));
			}
				return getCycledIngredients(cycleticks / 48, Collections.singletonList(input));
		}

		@Override
		public PositionedStack getOtherStack() {
			return afuels.get((cycleticks / 48) % afuels.size()).stack;
		}

		@Override
		public PositionedStack getResult()
		{
			return output;
		}
	}

	public static class FuelPair
	{
		public PositionedStack stack;
		public int burnTime;

		public FuelPair(ItemStack ingred, int burnTime)
		{
			this.stack = new PositionedStack(ingred, 51, 42, false);
			this.burnTime = burnTime;
		}
	}

	public static ArrayList<FuelPair> afuels;

	@Override
	public TemplateRecipeHandler newInstance()
	{
		if (afuels == null || afuels.isEmpty())
		{
			findFuels();
		}
		return super.newInstance();
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiCampFire.class;
	}

	@Override
	public String getGuiTexture()
	{
		return Reference.MODID + ":" + "textures/gui/container/camp_fire.png";
	}

	@Override
	public String getRecipeName()
	{
		return I18n.format("container.camp_fire");
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for (Object obj : CampfireRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (outp.getItem() == result.getItem() &&
				(result.getItemDamage() == OreDictionary.WILDCARD_VALUE ||
					outp.getItemDamage() == result.getItemDamage()))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "null"));
			}
		}

		for (Object obj : CampfirePanRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (outp.getItem() == result.getItem() &&
				(result.getItemDamage() == OreDictionary.WILDCARD_VALUE ||
					outp.getItemDamage() == result.getItemDamage()))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "pan"));
			}
		}
		
		for (Object obj : CampfirePotRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (outp.getItem() == result.getItem() &&
				(result.getItemDamage() == OreDictionary.WILDCARD_VALUE ||
					outp.getItemDamage() == result.getItemDamage()))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "pot"));
			}
		}
		
		for (Object obj : CampfireSmallCrucibleRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (outp.getItem() == result.getItem() &&
				(result.getItemDamage() == OreDictionary.WILDCARD_VALUE ||
					outp.getItemDamage() == result.getItemDamage()))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "small_crucible"));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		for (Object obj : CampfireRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (inp.matches(ingredient))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "null"));
			}
		}

		for (Object obj : CampfirePanRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (inp.matches(ingredient))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "pan"));
			}
		}
		
		for (Object obj : CampfirePotRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (inp.matches(ingredient))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "pot"));
			}
		}
		
		for (Object obj : CampfireSmallCrucibleRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (inp.matches(ingredient))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "small_crucible"));
			}
		}
	}

	@Override
	public void drawExtras(int recipe)
	{
		drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
		drawProgressBar(74, 23, 176, 14, 24, 17, 48, 0);
	}

	@Override
	public String getOverlayIdentifier()
	{
		return "campfire";
	}

	private static Set<Item> excludedFuels()
	{
		Set<Item> efuels = new HashSet<>();
		efuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
		efuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
		efuels.add(Item.getItemFromBlock(Blocks.standing_sign));
		efuels.add(Item.getItemFromBlock(Blocks.wall_sign));
		efuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
		return efuels;
	}

	private static void findFuels()
	{
		afuels = new ArrayList<FuelPair>();
		Set<Item> efuels = excludedFuels();

		for (ItemStack item : ItemList.items)
		{
			Block block = Block.getBlockFromItem(item.getItem());
			if (block instanceof BlockDoor)
			{
				continue;
			}

			if (efuels.contains(item.getItem()))
			{
				continue;
			}

			int burnTime = TileEntityCampFire.getItemBurnTime(item);
			if (burnTime > 0)
			{
				afuels.add(new FuelPair(item.copy(), burnTime));
			}
		}
	}
}

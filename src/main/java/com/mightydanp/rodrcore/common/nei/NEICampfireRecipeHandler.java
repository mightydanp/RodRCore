package com.mightydanp.rodrcore.common.nei;

import java.util.*;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.client.gui.inventory.GuiCampFire;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.item.crafting.CampFirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampFirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampFireRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampFireSmallCrucibleRecipes;
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
			input = new PositionedStack(_input.getValidItems(), 53, 1);
			output = new PositionedStack(_output, 113, 23);
			
			if(neededItem == "pan")
			{
				pan = new PositionedStack(new ItemStack(ModItems.pan), 27, 24);
			}
			if(neededItem == "pot")
			{
				pot = new PositionedStack(new ItemStack(ModItems.pot), 27, 24);
			}
			if(neededItem == "small_crucible")
			{
				smallCrucible = new PositionedStack(new ItemStack(ModItems.smallCrucible), 27, 24);
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
			this.stack = new PositionedStack(ingred, 53, 46, false);
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
		return Reference.MODID + ":textures/gui/container/campfire.png";
	}

	@Override
	public String getRecipeName()
	{
		return I18n.format("container.campfire");
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for (Object obj : CampFireRecipes.getSmeltingList().entrySet())
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

		for (Object obj : CampFirePanRecipes.getSmeltingList().entrySet())
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
		
		for (Object obj : CampFirePotRecipes.getSmeltingList().entrySet())
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
		
		for (Object obj : CampFireSmallCrucibleRecipes.getSmeltingList().entrySet())
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
		for (Object obj : CampFireRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (inp.matches(ingredient))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "null"));
			}
		}

		for (Object obj : CampFirePanRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (inp.matches(ingredient))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "pan"));
			}
		}
		
		for (Object obj : CampFirePotRecipes.getSmeltingList().entrySet())
		{
			Map.Entry entry = (Map.Entry)obj;

			OreRecipeElement inp = (OreRecipeElement) entry.getKey();
			ItemStack outp = (ItemStack)entry.getValue();

			if (inp.matches(ingredient))
			{
				arecipes.add(new CampfireCachedRecipe(inp, outp, "pot"));
			}
		}
		
		for (Object obj : CampFireSmallCrucibleRecipes.getSmeltingList().entrySet())
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
		drawProgressBar(53, 21, 176, 0, 14, 22, 48, 7);
		drawProgressBar(76, 22, 176, 22, 24, 16, 48, 0);
		drawProgressBar(72, 46, 176, 39, 3, 16, 48, 7);
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

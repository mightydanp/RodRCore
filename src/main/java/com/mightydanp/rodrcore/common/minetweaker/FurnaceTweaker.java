package com.mightydanp.rodrcore.common.minetweaker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.minetweaker.util.FurnaceRecipeWrapper;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rodrcore.Furnace")
public class FurnaceTweaker {
	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, xp, "null"));
	}

	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, 0.0f, "null"));
	}

	@ZenMethod
	public static void addPanRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, xp, "pan"));
	}

	@ZenMethod
	public static void addPanRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, 0.0f, "pan"));
	}

	@ZenMethod
	public static void addPotRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, xp, "pot"));
	}

	@ZenMethod
	public static void addPotRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, 0.0f, "pot"));
	}

	@ZenMethod
	public static void addSmallCrucibleRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, xp, "small_crucible"));
	}

	@ZenMethod
	public static void addSmallCrucibleRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddFurnaceRecipe(input, output, 0.0f, "small_crucible"));
	}

	@ZenMethod
	public static void removeRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveFurnaceRecipe(input, output, "null"));
	}

	@ZenMethod
	public static void removePanRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveFurnaceRecipe(input, output, "pan"));
	}

	@ZenMethod
	public static void removePotRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveFurnaceRecipe(input, output, "pot"));
	}

	@ZenMethod
	public static void removeSmallCrucibleRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveFurnaceRecipe(input, output, "small_crucible"));
	}

	@ZenMethod
	public static void removeOutput(IItemStack output) {
		MineTweakerAPI.apply(new RemoveFurnaceOutput(output));
	}

	private static class AddFurnaceRecipe implements IUndoableAction {
		private IIngredient input;
		private ItemStack output;
		private float xp;
		private String neededItem;
		private OreRecipeElement inputAsORE;

		public AddFurnaceRecipe(IIngredient input, IItemStack output, float xp, String neededItem) {
			this.input = input;
			this.output = MineTweakerMC.getItemStack(output);
			this.neededItem = neededItem;
			System.out.println(this.neededItem);
			if (neededItem == "null") {
				this.xp = FurnaceRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pan") {
				this.xp = FurnacePanRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pot") {
				this.xp = FurnacePotRecipes.smelting().giveExperience(this.output);
				System.out.println("Xp Is Working");
			}
			if (neededItem == "small_crucible") {
				this.xp = FurnaceSmallCrucibleRecipes.smelting().giveExperience(this.output);
			}

		}

		@Override
		public void apply() {
			if (this.neededItem == "null") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					FurnaceRecipes.getSmeltingList().put(inputAsORE, output);
					FurnaceRecipes.getXPList().put(output, xp);
				} else if (input instanceof IIngredient) {
					inputAsORE = new OreRecipeElement(MineTweakerMC.getItemStack(input));
					FurnaceRecipes.getSmeltingList().put(inputAsORE, output);
					FurnaceRecipes.getXPList().put(output, xp);
				}
			}
			if (this.neededItem == "pan") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					FurnacePanRecipes.getSmeltingList().put(inputAsORE, output);
					FurnacePanRecipes.getXPList().put(output, xp);
				}else if (input instanceof IIngredient) {
					inputAsORE = new OreRecipeElement(MineTweakerMC.getItemStack(input));
					FurnacePanRecipes.getSmeltingList().put(inputAsORE, output);
					FurnacePanRecipes.getXPList().put(output, xp);
				}
			}
			if (this.neededItem == "pot") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					FurnacePotRecipes.getSmeltingList().put(inputAsORE, output);
					FurnacePotRecipes.getXPList().put(output, xp);
					System.out.println("Smelting Is Working");
				}else if (input instanceof IIngredient) {
					inputAsORE = new OreRecipeElement(MineTweakerMC.getItemStack(input));
					FurnacePotRecipes.getSmeltingList().put(inputAsORE, output);
					FurnacePotRecipes.getXPList().put(output, xp);
				}
			}
			if (this.neededItem == "small_crucible") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					FurnaceSmallCrucibleRecipes.getSmeltingList().put(inputAsORE, output);
					FurnaceSmallCrucibleRecipes.getXPList().put(output, xp);
				}else if (input instanceof IIngredient) {
					inputAsORE = new OreRecipeElement(MineTweakerMC.getItemStack(input));
					FurnaceSmallCrucibleRecipes.getSmeltingList().put(inputAsORE, output);
					FurnaceSmallCrucibleRecipes.getXPList().put(output, xp);
				}
			}

		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public void undo() {
			if (this.neededItem == "null") {
				FurnaceRecipes.getSmeltingList().remove(inputAsORE);
				FurnaceRecipes.getXPList().remove(output);
			}
			if (this.neededItem == "pan") {
				FurnacePanRecipes.getSmeltingList().remove(inputAsORE);
				FurnacePanRecipes.getXPList().remove(output);
			}
			if (this.neededItem == "pot") {
				FurnacePotRecipes.getSmeltingList().remove(inputAsORE);
				FurnacePotRecipes.getXPList().remove(output);
				System.out.println("undo Is Working");
			}
			if (this.neededItem == "small_crucible") {
				FurnaceSmallCrucibleRecipes.getSmeltingList().remove(inputAsORE);
				FurnaceSmallCrucibleRecipes.getXPList().remove(output);
			}
		}

		@Override
		public String describe() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Adding recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " to Furnace";
		}

		@Override
		public String describeUndo() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Removing recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " from Furnace";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}

	}

	private static class RemoveFurnaceRecipe implements IUndoableAction {
		private IIngredient input;
		private ItemStack output;
		private float xp;
		private String neededItem;
		private OreRecipeElement removedORE;

		public RemoveFurnaceRecipe(IIngredient input, IItemStack output, String neededItem) {
			this.input = input;
			this.output = MineTweakerMC.getItemStack(output);
			this.neededItem = neededItem;
			if (neededItem == "null") {
				this.xp = FurnaceRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pan") {
				this.xp = FurnacePanRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pot") {
				this.xp = FurnacePotRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "small_crucible") {
				this.xp = FurnaceSmallCrucibleRecipes.smelting().giveExperience(this.output);
			}
		}

		@Override
		public void apply() {
			ItemStack inputAsStack = MineTweakerMC.getItemStack(input);
			for (Iterator<OreRecipeElement> recipeIter = FurnaceRecipes.getSmeltingList().keySet()
					.iterator(); recipeIter.hasNext();) {
				recipeIter = FurnaceRecipes.getSmeltingList().keySet().iterator();
				removedORE = recipeIter.next();
				if (removedORE.matches(inputAsStack)) {
					recipeIter.remove();
					FurnaceRecipes.getXPList().remove(output);
				}
			}
			for (Iterator<OreRecipeElement> recipeIter = FurnacePanRecipes.getSmeltingList().keySet()
					.iterator(); recipeIter.hasNext();) {
				recipeIter = FurnacePanRecipes.getSmeltingList().keySet().iterator();
				removedORE = recipeIter.next();
				if (removedORE.matches(inputAsStack)) {
					recipeIter.remove();
					FurnacePanRecipes.getXPList().remove(output);
				}
			}
			for (Iterator<OreRecipeElement> recipeIter = FurnacePotRecipes.getSmeltingList().keySet()
					.iterator(); recipeIter.hasNext();) {
				recipeIter = FurnacePotRecipes.getSmeltingList().keySet().iterator();
				removedORE = recipeIter.next();
				if (removedORE.matches(inputAsStack)) {
					recipeIter.remove();
					FurnacePotRecipes.getXPList().remove(output);
				}
			}
			for (Iterator<OreRecipeElement> recipeIter = FurnaceSmallCrucibleRecipes.getSmeltingList().keySet()
					.iterator(); recipeIter.hasNext();) {
				recipeIter = FurnaceSmallCrucibleRecipes.getSmeltingList().keySet().iterator();
				removedORE = recipeIter.next();
				if (removedORE.matches(inputAsStack)) {
					recipeIter.remove();
					FurnaceSmallCrucibleRecipes.getXPList().remove(output);
				}
			}
		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public void undo() {
			if (neededItem == "null") {
				FurnaceRecipes.getSmeltingList().put(removedORE, output);
				FurnaceRecipes.getXPList().put(output, xp);
			}
			if (neededItem == "pan") {
				FurnacePanRecipes.getSmeltingList().put(removedORE, output);
				FurnacePanRecipes.getXPList().put(output, xp);
			}
			if (neededItem == "pot") {
				FurnacePotRecipes.getSmeltingList().put(removedORE, output);
				FurnacePotRecipes.getXPList().put(output, xp);
			}
			if (neededItem == "small_crucible") {
				FurnaceSmallCrucibleRecipes.getSmeltingList().put(removedORE, output);
				FurnaceSmallCrucibleRecipes.getXPList().put(output, xp);
			}
		}

		@Override
		public String describe() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Removing recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " from Furnace";
		}

		@Override
		public String describeUndo() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Readding recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " to Furnace";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}

	private static class RemoveFurnaceOutput implements IUndoableAction {
		private ItemStack output;
		private List<FurnaceRecipeWrapper> removedRecipes = new ArrayList<FurnaceRecipeWrapper>();

		public RemoveFurnaceOutput(IItemStack output) {
			this.output = MineTweakerMC.getItemStack(output);
		}

		public void apply() {
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = FurnaceRecipes.getSmeltingList().entrySet()
					.iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "null");
			}
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = FurnacePanRecipes.getSmeltingList()
					.entrySet().iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "pan");
			}
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = FurnacePotRecipes.getSmeltingList()
					.entrySet().iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "pot");
			}
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = FurnaceSmallCrucibleRecipes
					.getSmeltingList().entrySet().iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "small_crucible");
			}
		}

		// If the recipe is the target recipe, it is removed.
		private void checkAndRemoveRecipe(Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter, String neededItem) {
			Entry<OreRecipeElement, ItemStack> entry = recipeIter.next();
			if (ItemStack.areItemStacksEqual(entry.getValue(), output)) {
				removedRecipes.add(new FurnaceRecipeWrapper(entry.getKey(), output,
						FurnaceRecipes.smelting().giveExperience(output), neededItem));
				recipeIter.remove();

				if (neededItem == "null") {
					FurnaceRecipes.getXPList().remove(output);
				}
				if (neededItem == "pan") {
					FurnacePanRecipes.getXPList().remove(output);
				}
				if (neededItem == "pot") {
					FurnacePotRecipes.getXPList().remove(output);
				}
				if (neededItem == "small_crucible") {
					FurnaceSmallCrucibleRecipes.getXPList().remove(output);
				}
			}
		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public void undo() {
			for (FurnaceRecipeWrapper r : removedRecipes) {
				r.add();
			}
		}

		@Override
		public String describe() {
			return "Removing recipes for " + output.getDisplayName() + " * " + output.stackSize + " from Furnace";
		}

		@Override
		public String describeUndo() {
			return "Readding recipes for " + output.getDisplayName() + " * " + output.stackSize + " to Furnace";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}
}

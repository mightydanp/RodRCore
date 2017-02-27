package com.mightydanp.rodrcore.common.minetweaker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.mightydanp.rodrcore.api.common.handler.OreRecipeElement;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.minetweaker.util.CampfireRecipeWrapper;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rodrcore.Campfire")
public class CampFireTweaker {
	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, xp, "null"));
	}

	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, 0.0f, "null"));
	}

	@ZenMethod
	public static void addPanRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, xp, "pan"));
	}

	@ZenMethod
	public static void addPanRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, 0.0f, "pan"));
	}

	@ZenMethod
	public static void addPotRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, xp, "pot"));
	}

	@ZenMethod
	public static void addPotRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, 0.0f, "pot"));
	}

	@ZenMethod
	public static void addSmallCrucibleRecipe(IItemStack output, IIngredient input, float xp) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, xp, "small_crucible"));
	}

	@ZenMethod
	public static void addSmallCrucibleRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new AddCampFireRecipe(input, output, 0.0f, "small_crucible"));
	}

	@ZenMethod
	public static void removeRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveCampFireRecipe(input, output, "null"));
	}

	@ZenMethod
	public static void removePanRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveCampFireRecipe(input, output, "pan"));
	}

	@ZenMethod
	public static void removePotRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveCampFireRecipe(input, output, "pot"));
	}

	@ZenMethod
	public static void removeSmallCrucibleRecipe(IItemStack output, IIngredient input) {
		MineTweakerAPI.apply(new RemoveCampFireRecipe(input, output, "small_crucible"));
	}

	@ZenMethod
	public static void removeOutput(IItemStack output) {
		MineTweakerAPI.apply(new RemoveCampFireOutput(output));
	}

	private static class AddCampFireRecipe implements IUndoableAction {
		private IIngredient input;
		private ItemStack output;
		private float xp;
		private String neededItem;
		private OreRecipeElement inputAsORE;

		public AddCampFireRecipe(IIngredient input, IItemStack output, float xp, String neededItem) {
			this.input = input;
			this.output = MineTweakerMC.getItemStack(output);
			this.neededItem = neededItem;
			if (neededItem == "null") {
				this.xp = CampfireRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pan") {
				this.xp = CampfirePanRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pot") {
				this.xp = CampfirePotRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "small_crucible") {
				this.xp = CampfireSmallCrucibleRecipes.smelting().giveExperience(this.output);
			}
		}

		@Override
		public void apply() {
			if (neededItem == "null") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					CampfireRecipes.getSmeltingList().put(inputAsORE, output);
					CampfireRecipes.getXPList().put(output, xp);
				} else if (input instanceof IIngredient) {
					inputAsORE = new OreRecipeElement(MineTweakerMC.getItemStack(input));
					CampfireRecipes.getSmeltingList().put(inputAsORE, output);
					CampfireRecipes.getXPList().put(output, xp);
				}
			}
			if (neededItem == "pan") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					CampfirePanRecipes.getSmeltingList().put(inputAsORE, output);
					CampfirePanRecipes.getXPList().put(output, xp);
				}
			}
			if (neededItem == "pot") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					CampfirePotRecipes.getSmeltingList().put(inputAsORE, output);
					CampfirePotRecipes.getXPList().put(output, xp);
				}
			}
			if (neededItem == "small_crucible") {
				if (input instanceof IOreDictEntry) {
					inputAsORE = new OreRecipeElement(((IOreDictEntry) input).getName(), 1);
					CampfireSmallCrucibleRecipes.getSmeltingList().put(inputAsORE, output);
					CampfireSmallCrucibleRecipes.getXPList().put(output, xp);
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
				CampfireRecipes.getSmeltingList().remove(inputAsORE);
				CampfireRecipes.getXPList().remove(output);
			}
			if (neededItem == "pan") {
				CampfirePanRecipes.getSmeltingList().remove(inputAsORE);
				CampfirePanRecipes.getXPList().remove(output);
			}
			if (neededItem == "pot") {
				CampfirePotRecipes.getSmeltingList().remove(inputAsORE);
				CampfirePotRecipes.getXPList().remove(output);
			}
			if (neededItem == "small_crucible") {
				CampfireSmallCrucibleRecipes.getSmeltingList().remove(inputAsORE);
				CampfireSmallCrucibleRecipes.getXPList().remove(output);
			}
		}

		@Override
		public String describe() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Adding recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " to CampFire";
		}

		@Override
		public String describeUndo() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Removing recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " from CampFire";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}

	}

	private static class RemoveCampFireRecipe implements IUndoableAction {
		private IIngredient input;
		private ItemStack output;
		private float xp;
		private String neededItem;
		private OreRecipeElement removedORE;

		public RemoveCampFireRecipe(IIngredient input, IItemStack output, String neededItem) {
			this.input = input;
			this.output = MineTweakerMC.getItemStack(output);
			this.neededItem = neededItem;
			if (neededItem == "null") {
				this.xp = CampfireRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pan") {
				this.xp = CampfirePanRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "pot") {
				this.xp = CampfirePotRecipes.smelting().giveExperience(this.output);
			}
			if (neededItem == "small_crucible") {
				this.xp = CampfireSmallCrucibleRecipes.smelting().giveExperience(this.output);
			}
		}

		@Override
		public void apply() {
			ItemStack inputAsStack = MineTweakerMC.getItemStack(input);
			Iterator<OreRecipeElement> recipeIter = CampfirePanRecipes.getSmeltingList().keySet().iterator();;
			
			if (neededItem == "null") {
				recipeIter = CampfireRecipes.getSmeltingList().keySet().iterator();
				recipeIter.hasNext();
			}
			if (neededItem == "pan") {
				recipeIter = CampfirePanRecipes.getSmeltingList().keySet().iterator();
				recipeIter.hasNext();
			}
			if (neededItem == "pot") {
				recipeIter = CampfirePotRecipes.getSmeltingList().keySet().iterator();
				recipeIter.hasNext();
			}
			if (neededItem == "small_crucible") {
				recipeIter =  CampfireSmallCrucibleRecipes.getSmeltingList().keySet().iterator();
				recipeIter.hasNext();
			}
			removedORE = recipeIter.next();
				if (removedORE.matches(inputAsStack)) {
					recipeIter.remove();
					
					if (neededItem == "null") {
						CampfireRecipes.getXPList().remove(output);
					}
					if (neededItem == "pan") {
						CampfirePanRecipes.getXPList().remove(output);
					}
					if (neededItem == "pot") {
						CampfirePotRecipes.getXPList().remove(output);
					}
					if (neededItem == "small_crucible") {
						CampfireSmallCrucibleRecipes.getXPList().remove(output);
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
				CampfireRecipes.getSmeltingList().put(removedORE, output);
				CampfireRecipes.getXPList().put(output, xp);
			}
			if (neededItem == "pan") {
				CampfirePanRecipes.getSmeltingList().put(removedORE, output);
				CampfirePanRecipes.getXPList().put(output, xp);
			}
			if (neededItem == "pot") {
				CampfirePotRecipes.getSmeltingList().put(removedORE, output);
				CampfirePotRecipes.getXPList().put(output, xp);
			}
			if (neededItem == "small_crucible") {
				CampfireSmallCrucibleRecipes.getSmeltingList().put(removedORE, output);
				CampfireSmallCrucibleRecipes.getXPList().put(output, xp);
			}
		}

		@Override
		public String describe() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Removing recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " from CampFire";
		}

		@Override
		public String describeUndo() {
			String inputIDString = (input instanceof IOreDictEntry) ? ((IOreDictEntry) input).getName()
					: MineTweakerMC.getItemStack(input).getDisplayName();
			return "Readding recipe " + inputIDString + " -> " + output.getDisplayName() + " * " + output.stackSize
					+ " to CampFire";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}

	private static class RemoveCampFireOutput implements IUndoableAction {
		private ItemStack output;
		private List<CampfireRecipeWrapper> removedRecipes = new ArrayList<CampfireRecipeWrapper>();

		public RemoveCampFireOutput(IItemStack output) {
			this.output = MineTweakerMC.getItemStack(output);
		}

		public void apply() {
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = CampfireRecipes.getSmeltingList().entrySet()
					.iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "null");
			}
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = CampfirePanRecipes.getSmeltingList()
					.entrySet().iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "pan");
			}
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = CampfirePotRecipes.getSmeltingList().entrySet()
					.iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "pot");
			}
			for (Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter = CampfireSmallCrucibleRecipes.getSmeltingList().entrySet()
					.iterator(); recipeIter.hasNext();) {
				this.checkAndRemoveRecipe(recipeIter, "small_crucible");
			}
		}

		// If the recipe is the target recipe, it is removed.
		private void checkAndRemoveRecipe(Iterator<Entry<OreRecipeElement, ItemStack>> recipeIter, String neededItem) {
			Entry<OreRecipeElement, ItemStack> entry = recipeIter.next();
			if (ItemStack.areItemStacksEqual(entry.getValue(), output)) {
				removedRecipes.add(new CampfireRecipeWrapper(entry.getKey(), output,
						CampfireRecipes.smelting().giveExperience(output), neededItem));
				recipeIter.remove();

				if (neededItem == "null") {
					CampfireRecipes.getXPList().remove(output);
				}
				if (neededItem == "pan") {
					CampfirePanRecipes.getXPList().remove(output);
				}
				if (neededItem == "pot") {
					CampfirePotRecipes.getXPList().remove(output);
				}
				if (neededItem == "small_crucible") {
					CampfireSmallCrucibleRecipes.getXPList().remove(output);
				}
			}
		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public void undo() {
			for (CampfireRecipeWrapper r : removedRecipes) {
				r.add();
			}
		}

		@Override
		public String describe() {
			return "Removing recipes for " + output.getDisplayName() + " * " + output.stackSize + " from CampFire";
		}

		@Override
		public String describeUndo() {
			return "Readding recipes for " + output.getDisplayName() + " * " + output.stackSize + " to CampFire";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}
}

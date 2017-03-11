package com.mightydanp.rodrcore.api.common.handler;

import javax.annotation.Nullable;

import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnacePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceRecipes;
import com.mightydanp.rodrcore.common.item.crafting.FurnaceSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.lib.ItemReference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CRecipeHandler {

	public static void registerShapedRecipe(Object object, int meta, int amount, Object... params) {
		if (object instanceof Item) {
			GameRegistry.addShapedRecipe(new ItemStack((Item) object, amount, meta), params);
		}
		if (object instanceof Block) {
			GameRegistry.addShapedRecipe(new ItemStack((Block) object, amount, meta), params);
		}
	}

	public static void registerShapelessRecipe(Object object, int meta, int amount, Object... params) {
		if (object instanceof Item) {
			GameRegistry.addShapelessRecipe(new ItemStack((Item) object, amount, meta), params);
		}
		if (object instanceof Block) {
			GameRegistry.addShapelessRecipe(new ItemStack((Block) object, amount, meta), params);
		}
	}

	public static void registerCampfireRecipe(Object objectIn, int metaIn, Object objectOut, int metaOut, int amountOut,
			float experience, @Nullable String type) {
		if (objectIn instanceof Item) {
			if (objectOut instanceof Item) {
				if (type == "")
					CampfireRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.PAN_NAME)
					CampfirePanRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.POT_NAME)
					CampfirePotRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.SMALLCRUCIBLE_NAME)
					CampfireSmallCrucibleRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
			}

			if (objectOut instanceof Block) {
				if (type == "")
					CampfireRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.PAN_NAME)
					CampfirePanRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.POT_NAME)
					CampfirePotRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.SMALLCRUCIBLE_NAME)
					CampfireSmallCrucibleRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
			}

		}

		if (objectIn instanceof Block) {
			if (objectOut instanceof Item) {
				if (type == "")
					CampfireRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.PAN_NAME)
					CampfirePanRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.POT_NAME)
					CampfirePotRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.SMALLCRUCIBLE_NAME)
					CampfireSmallCrucibleRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Item) objectOut, amountOut, metaOut), experience);
			}

			if (objectOut instanceof Block) {
				if (type == "")
					CampfireRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.PAN_NAME)
					CampfirePanRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.POT_NAME)
					CampfirePotRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				if (type == ItemReference.SMALLCRUCIBLE_NAME)
					CampfireSmallCrucibleRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
							new ItemStack((Block) objectOut, amountOut, metaOut), experience);
			}

			if (objectIn instanceof String) {
				if (objectOut instanceof Item) {
					if (type == "")
						CampfireRecipes.addRecipe((String) objectIn,
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.PAN_NAME)
						CampfirePanRecipes.addRecipe((String) objectIn,
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.POT_NAME)
						CampfirePotRecipes.addRecipe((String) objectIn,
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.SMALLCRUCIBLE_NAME)
						CampfireSmallCrucibleRecipes.addRecipe((String) objectIn,
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				}

				if (objectOut instanceof Block) {
					if (type == "")
						CampfireRecipes.addRecipe((String) objectIn,
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.PAN_NAME)
						CampfirePanRecipes.addRecipe((String) objectIn,
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.POT_NAME)
						CampfirePotRecipes.addRecipe((String) objectIn,
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.SMALLCRUCIBLE_NAME)
						CampfireSmallCrucibleRecipes.addRecipe((String) objectIn,
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				}
			}
		}
	}
		
		public static void registerNewFurnaceRecipe(Object objectIn, int metaIn, Object objectOut, int metaOut, int amountOut, float experience, @Nullable String type) {
			if (objectIn instanceof Item) {
				if (objectOut instanceof Item) {
					if (type == "")
						FurnaceRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.PAN_NAME)
						FurnacePanRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.POT_NAME)
						FurnacePotRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.SMALLCRUCIBLE_NAME)
						FurnaceSmallCrucibleRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				}

				if (objectOut instanceof Block) {
					if (type == "")
						FurnaceRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.PAN_NAME)
						FurnacePanRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.POT_NAME)
						FurnacePotRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.SMALLCRUCIBLE_NAME)
						FurnaceSmallCrucibleRecipes.addRecipe(new ItemStack((Item) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				}

			}

			if (objectIn instanceof Block) {
				if (objectOut instanceof Item) {
					if (type == "")
						FurnaceRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.PAN_NAME)
						FurnacePanRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.POT_NAME)
						FurnacePotRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.SMALLCRUCIBLE_NAME)
						FurnaceSmallCrucibleRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Item) objectOut, amountOut, metaOut), experience);
				}

				if (objectOut instanceof Block) {
					if (type == "")
						FurnaceRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.PAN_NAME)
						FurnacePanRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.POT_NAME)
						FurnacePotRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					if (type == ItemReference.SMALLCRUCIBLE_NAME)
						FurnaceSmallCrucibleRecipes.addRecipe(new ItemStack((Block) objectIn, 1, metaIn),
								new ItemStack((Block) objectOut, amountOut, metaOut), experience);
				}

				if (objectIn instanceof String) {
					if (objectOut instanceof Item) {
						if (type == "")
							FurnaceRecipes.addRecipe((String) objectIn,
									new ItemStack((Item) objectOut, amountOut, metaOut), experience);
						if (type == ItemReference.PAN_NAME)
							FurnacePanRecipes.addRecipe((String) objectIn,
									new ItemStack((Item) objectOut, amountOut, metaOut), experience);
						if (type == ItemReference.POT_NAME)
							FurnacePotRecipes.addRecipe((String) objectIn,
									new ItemStack((Item) objectOut, amountOut, metaOut), experience);
						if (type == ItemReference.SMALLCRUCIBLE_NAME)
							FurnaceSmallCrucibleRecipes.addRecipe((String) objectIn,
									new ItemStack((Item) objectOut, amountOut, metaOut), experience);
					}

					if (objectOut instanceof Block) {
						if (type == "")
							FurnaceRecipes.addRecipe((String) objectIn,
									new ItemStack((Block) objectOut, amountOut, metaOut), experience);
						if (type == ItemReference.PAN_NAME)
							FurnacePanRecipes.addRecipe((String) objectIn,
									new ItemStack((Block) objectOut, amountOut, metaOut), experience);
						if (type == ItemReference.POT_NAME)
							FurnacePotRecipes.addRecipe((String) objectIn,
									new ItemStack((Block) objectOut, amountOut, metaOut), experience);
						if (type == ItemReference.SMALLCRUCIBLE_NAME)
							FurnaceSmallCrucibleRecipes.addRecipe((String) objectIn,
									new ItemStack((Block) objectOut, amountOut, metaOut), experience);
					}
				}
			}
	}

}

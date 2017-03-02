package com.mightydanp.rodrcore.common.block;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.item.ItemOreSand;
import com.mightydanp.rodrcore.common.item.ItemTwigs;
import com.mightydanp.rodrcore.common.lib.BlockReference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks extends CRegistryHandler{
	
	private static CRegistryHandler	register;
	
	public static Block                         campFireIdle;
	public static Block                         campFireActive;
	public static Block                         furnaceIdle;
	public static Block                         furnaceActive;
	public static Block							oreSand;
	public static Block							rocks;
	public static Block							twigs;

	public static void preInit() {
		registerObject(campFireIdle = new BlockCampFire(BlockReference.CAMPFIREIDLE_NAME, false).setCreativeTab(RodRCore.tabRodRCore), BlockReference.CAMPFIREIDLE_NAME, null);
		registerObject(campFireActive = new BlockCampFire(BlockReference.CAMPFIREACTIVE_NAME, true).setLightLevel(0.875F), BlockReference.CAMPFIREACTIVE_NAME, null);
		registerObject(campFireIdle = new BlockNewFurnace(BlockReference.FURNACEIDLE_NAME, false).setCreativeTab(RodRCore.tabRodRCore), BlockReference.FURNACEIDLE_NAME, null);
		registerObject(campFireActive = new BlockNewFurnace(BlockReference.FURNACEACTIVE_NAME, true).setLightLevel(0.875F), BlockReference.FURNACEACTIVE_NAME, null);
		registerObject(oreSand = new BlockOreSand(BlockReference.ORESAND_NAME), BlockReference.ORESAND_NAME,ItemOreSand.class);
		registerObject(rocks = new BlockRocks(BlockReference.ROCKS_NAME), BlockReference.ROCKS_NAME, null);
		registerObject(twigs = new BlockTwigs(BlockReference.TWIGS_NAME), BlockReference.TWIGS_NAME, null);
		}
	
	public static void init() {
		registerOreDictionary();
	}
	
	public static void registerOreDictionary() {
		registerOreDictionary(oreSand, "oreCopper", 0);
		registerOreDictionary(oreSand, "oreCopperSand", 0);
		registerOreDictionary(oreSand, "oreTin", 1);
		registerOreDictionary(oreSand, "oreTinSand", 1);
	}
}

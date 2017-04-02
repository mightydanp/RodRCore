package com.mightydanp.rodrcore.common.nei;

import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.lib.Reference;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.Optional;
import net.minecraft.item.ItemStack;

@Optional.Interface(iface = "codechicken.nei.api.IConfigureNEI", modid = "NotEnoughItems")
public class NEIConfig implements IConfigureNEI
{
	@Override
	public void loadConfig()
	{

		NEICampfireRecipeHandler campfireRecipeHandler = new NEICampfireRecipeHandler();
		API.registerRecipeHandler(campfireRecipeHandler);
		API.registerUsageHandler(campfireRecipeHandler);
		
		API.hideItem(new ItemStack(ModBlocks.campFireActive));
		API.hideItem(new ItemStack(ModBlocks.furnaceActive));
		API.hideItem(new ItemStack(ModBlocks.furnaceIdle));
	}

	@Override
	public String getName()
	{
		return Reference.MODNAME;
	}

	@Override
	public String getVersion()
	{
		return Reference.VERSION;
	}
}

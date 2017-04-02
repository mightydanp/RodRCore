package com.mightydanp.rodrcore.common.minetweaker;

import minetweaker.MineTweakerAPI;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MineTweakerCompat 
{
	public static void register()
	{
		MineTweakerAPI.registerClass(CampFireTweaker.class);
		test();
	}

	private static void test()
	{
		
	}
}

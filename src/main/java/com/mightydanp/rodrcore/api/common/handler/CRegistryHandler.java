package com.mightydanp.rodrcore.api.common.handler;

import javax.annotation.Nullable;

import com.mightydanp.rodrcore.common.lib.GuiReference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class CRegistryHandler {
	
	public static void registerOreDictionary(Object object, String oreDictionaryName, int meta){
		if(object instanceof Item){
			OreDictionary.registerOre(oreDictionaryName, (Item)object);
				OreDictionary.registerOre(oreDictionaryName, new ItemStack((Item)object, 1, meta));
		}
		if(object instanceof Block){
			OreDictionary.registerOre(oreDictionaryName, (Block)object);
				OreDictionary.registerOre(oreDictionaryName, new ItemStack((Block)object, 1, meta));
		}
	}

	public static void registerObject(Object object, String unlocalizedName, @Nullable Class itemBlock) {
		if(object instanceof Item){
			GameRegistry.registerItem((Item)object, ((Item)object).getUnlocalizedName());
		}
		
		if(object instanceof Block){
			if(itemBlock != null){
				GameRegistry.registerBlock((Block)object, itemBlock, ((Block)object).getUnlocalizedName());
			}else{
				GameRegistry.registerBlock((Block)object, ((Block)object).getUnlocalizedName());
			}
		}
	}	
	
	public static void registerGuiHandler(Object instance, IGuiHandler guiHandler){
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
	}
	
	public static void registerStringLocalization(String key, String value){
		LanguageRegistry.instance().addStringLocalization(key, value);
	}
	
}

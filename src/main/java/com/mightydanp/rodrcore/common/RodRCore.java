package com.mightydanp.rodrcore.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.handler.GuiHandler;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.lib.*;
import com.mightydanp.rodrcore.common.minetweaker.MineTweakerCompat;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;
import com.mightydanp.rodrcore.common.tileentity.TileEntityRocks;
import com.mightydanp.rodrcore.common.tileentity.TileEntityTwigs;
import com.mightydanp.rodrcore.common.world.gen.WorldGen;
import com.mightydanp.rodrcore.common.world.gen.feature.WorldGenTwigsAndRocks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.MODNAME , version = Reference.VERSION)

public class RodRCore {

	@Mod.Instance(Reference.MODID)
    public static RodRCore instance;
	
	@SidedProxy(clientSide = Reference.CLIENTPROXYLOCATION, serverSide = Reference.COMMONPROXYLOCATION)
    public static CommonProxy proxy;
	
	 public static CreativeTabs tabRodRCore = new CreativeTabs(Reference.MODID){
	        @Override
	        public Item getTabIconItem(){
				return ModItems.rock;
	        }
	    };
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		event.getModMetadata().version = Reference.VERSION;
        ModBlocks.preInit();
        ModItems.preInit();
		RodRCore.proxy.preInit(event);
    }

	@Mod.EventHandler
    public void init(FMLInitializationEvent event) {
		ModBlocks.init();
        ModItems.init();
        GuiHandler guiHandler = new GuiHandler();
		GameRegistry.registerWorldGenerator(new WorldGen(), 0);
		GameRegistry.registerTileEntity(TileEntityCampFire.class, EntityReference.CAMPFIRE_NAME);
		GameRegistry.registerTileEntity(TileEntityTwigs.class, EntityReference.TWIGS_NAME);
		GameRegistry.registerTileEntity(TileEntityRocks.class, EntityReference.ROCKS_NAME);
		if(Loader.isModLoaded("MineTweaker3"))
		{
			MineTweakerCompat.register();
		}
		RodRCore.proxy.init(event);
    }

	@Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {		
		RodRCore.proxy.postInit(event);
    }
}

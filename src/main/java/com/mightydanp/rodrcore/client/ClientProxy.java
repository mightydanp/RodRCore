package com.mightydanp.rodrcore.client;

import com.mightydanp.rodrcore.client.model.ModelCampFire;
import com.mightydanp.rodrcore.client.renderer.tileentity.TileEntityCampFireRenderer;
import com.mightydanp.rodrcore.client.renderer.tileentity.TileEntityNewFurnaceRenderer;
import com.mightydanp.rodrcore.client.renderer.tileentity.TileEntityRocksRenderer;
import com.mightydanp.rodrcore.client.renderer.tileentity.TileEntityTwigsRenderer;
import com.mightydanp.rodrcore.common.CommonProxy;
import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;
import com.mightydanp.rodrcore.common.tileentity.TileEntityRocks;
import com.mightydanp.rodrcore.common.tileentity.TileEntityTwigs;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy{
	
    @Override
    public void preInit(FMLPreInitializationEvent event) {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampFire.class, new TileEntityCampFireRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNewFurnace.class, new TileEntityNewFurnaceRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTwigs.class, new TileEntityTwigsRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRocks.class, new TileEntityRocksRenderer());
    	}

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}

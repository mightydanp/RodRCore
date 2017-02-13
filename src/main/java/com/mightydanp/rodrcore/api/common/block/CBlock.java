package com.mightydanp.rodrcore.api.common.block;

import javax.annotation.Nullable;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CBlock extends Block{

	private static CRegistryHandler handler;
	
	protected CBlock(Material material, String unlocalizedName) {
		super(material);
		setBlockName(unlocalizedName);
		setBlockTextureName(Reference.MODID + ":" + unlocalizedName);
	}
}

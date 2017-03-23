package com.mightydanp.rodrcore.client.renderer.tileentity;

import com.mightydanp.rodrcore.api.common.block.CBlockFluid;
import com.mightydanp.rodrcore.common.RodRCore;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class TileEntityLiquidRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		CBlockFluid fluid = (CBlockFluid) block;
		int color = fluid.colour;
		renderer.renderBlockAsItem(fluid, color, color);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		CBlockFluid fluid = (CBlockFluid) block;
		int color = fluid.colour;
		Tessellator tess = Tessellator.instance;
		renderer.renderBlockLiquid(fluid, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return RodRCore.liquidRender;
	}
}
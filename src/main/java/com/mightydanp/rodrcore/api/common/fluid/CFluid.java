package com.mightydanp.rodrcore.api.common.fluid;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class CFluid extends Fluid{
	private int colour = 0xffffffff;

	public CFluid(String fluidName, int hex) {
		super(fluidName);
		this.colour = hex;
		this.setFlowingIcon(Blocks.flowing_lava.getIcon(0, 0));
		this.setStillIcon(Blocks.lava.getIcon(0, 0));
	}
	
	@Override
    public int getColor()
    {
        return colour;
    }

}

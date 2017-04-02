package com.mightydanp.rodrcore.api.common.fluid;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class CFluid extends Fluid{
	private int colour = 0xffffffff;

	public CFluid(String fluidName) {
		super(fluidName);
	}
	
	@Override
    public int getColor(){
        return setColor(colour);
    }

	public int setColor(int color) {
		return color;
	}

}

package com.mightydanp.rodrcore.api.common.fluid.effect;

import java.util.Random;

import com.mightydanp.rodrcore.api.common.fluid.IFluidAction;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FluidEffect implements IFluidAction{

	@Override
	public void onTouch(World world, int x, int y, int z, Entity entity) {}

	@Override
	public void onTick(World world, int x, int y, int z, Random rand) {}

}

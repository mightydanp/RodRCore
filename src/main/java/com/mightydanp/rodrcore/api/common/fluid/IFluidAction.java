package com.mightydanp.rodrcore.api.common.fluid;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface  IFluidAction {
	public void onTouch(World world, int x, int y, int z, Entity entity);

	public void onTick(World world, int x, int y, int z, Random rand);
}

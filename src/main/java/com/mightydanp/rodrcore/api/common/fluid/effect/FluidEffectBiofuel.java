package com.mightydanp.rodrcore.api.common.fluid.effect;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FluidEffectBiofuel extends FluidEffect {
	@Override
	public void onTouch(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 1200, 0));
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 600, 1));
		}
	}
}
package com.mightydanp.rodrcore.api.common.fluid.effect;

import java.util.Random;

import com.mightydanp.rodrcore.api.common.fluid.IFluidAction;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FluidEffectAlcool extends FluidEffect {
	@Override
	public void onTouch(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 1200, 2));
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 600, 1));
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 600, 1));
		}
	}
}

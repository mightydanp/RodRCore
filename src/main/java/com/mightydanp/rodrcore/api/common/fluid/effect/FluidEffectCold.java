package com.mightydanp.rodrcore.api.common.fluid.effect;

import java.util.Random;

import com.mightydanp.rodrcore.api.common.lib.DamageSourceRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FluidEffectCold extends FluidEffect{
	@Override
	public void onTouch(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			if (!((EntityLivingBase) entity).isPotionActive(Potion.moveSlowdown)) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 1200, 1));
			}

			entity.attackEntityFrom(DamageSourceRegistry.cold, 1);
		}
	}
}

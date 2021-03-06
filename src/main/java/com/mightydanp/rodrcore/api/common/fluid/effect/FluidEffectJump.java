package com.mightydanp.rodrcore.api.common.fluid.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FluidEffectJump extends FluidEffect{
	@Override
	public void onTouch(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			if (!((EntityLivingBase) entity).isPotionActive(Potion.jump)) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.jump.getId(), 1200, 2));
			}
		}
	}
}

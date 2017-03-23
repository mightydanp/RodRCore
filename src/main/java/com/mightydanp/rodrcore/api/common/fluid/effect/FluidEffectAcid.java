package com.mightydanp.rodrcore.api.common.fluid.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.Random;

import com.mightydanp.rodrcore.api.common.block.CBlockFluid;
import com.mightydanp.rodrcore.api.common.fluid.IFluidAction;
import com.mightydanp.rodrcore.api.common.lib.DamageSourceRegistry;

public class FluidEffectAcid extends FluidEffect{
	@Override
	public void onTouch(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			if (!((EntityLivingBase) entity).isPotionActive(Potion.blindness)) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), 1600, 0));
			}

			entity.attackEntityFrom(DamageSourceRegistry.acid, 3);
		}
	}}

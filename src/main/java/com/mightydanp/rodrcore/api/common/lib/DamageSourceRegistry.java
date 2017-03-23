package com.mightydanp.rodrcore.api.common.lib;

import net.minecraft.util.DamageSource;

public class DamageSourceRegistry {
	public static final DamageSource cold = new DamageSource("coldWater").setFireDamage();
	public static final DamageSource acid = new DamageSource("acid");
}

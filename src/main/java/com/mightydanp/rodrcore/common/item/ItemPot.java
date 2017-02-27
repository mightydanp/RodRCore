package com.mightydanp.rodrcore.common.item;

import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.common.RodRCore;

public class ItemPot extends CItem{

	public ItemPot(String unlocalizedName) {
		super(unlocalizedName);
		this.setUnlocalizedName(unlocalizedName);
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
		this.setCreativeTab(RodRCore.tabRodRCore);
	}

}

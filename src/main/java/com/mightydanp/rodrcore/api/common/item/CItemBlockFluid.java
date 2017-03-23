package com.mightydanp.rodrcore.api.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CItemBlockFluid extends ItemBlock {
	public CItemBlockFluid(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		return this.field_150939_a.getLocalizedName();
	}

	@Override
	public int getColorFromItemStack(ItemStack itemStack, int hex) {
		return this.field_150939_a.getRenderColor(hex);
	}
}

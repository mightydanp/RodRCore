package com.mightydanp.rodrcore.common.item;

import java.util.List;

import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.lib.ItemReference;
import com.mightydanp.rodrcore.common.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemPan extends CItem{
	
	public ItemPan(String unlocalizedName) {
		super(unlocalizedName);
		this.setUnlocalizedName(unlocalizedName);
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
		this.setCreativeTab(RodRCore.tabRodRCore);
	}
}
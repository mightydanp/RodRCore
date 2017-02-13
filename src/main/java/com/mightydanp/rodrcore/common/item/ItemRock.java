package com.mightydanp.rodrcore.common.item;

import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemRock  extends CItem {

	public ItemRock(String unlocalizedName) {
		super(unlocalizedName);
		this.setCreativeTab(RodRCore.tabRodRCore);
        this.setUnlocalizedName(unlocalizedName);
	}
}
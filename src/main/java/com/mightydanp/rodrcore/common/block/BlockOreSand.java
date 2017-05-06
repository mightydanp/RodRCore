package com.mightydanp.rodrcore.common.block;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.lib.BlockReference;
import com.mightydanp.rodrcore.common.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockOreSand extends BlockFalling {

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public static String[] subBlocks = new String[] { "copper", "tin" };

	public BlockOreSand(String unlocalizedName) {
		super(Material.rock);
		this.setCreativeTab(RodRCore.tabRodRCore);
		this.setHardness(0.5F);
		this.setBlockName(unlocalizedName);
		this.setStepSound(soundTypeSand);
		this.setHarvestLevel("pickaxe", 0, 0);
		this.setHarvestLevel("shovel", 0, 0);
		this.setHarvestLevel("pickaxe", 1, 1);
		this.setHarvestLevel("shovel", 1, 1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < subBlocks.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public Item getItemDropped(int meta, Random random, int par3) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return iconArray[meta];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.iconArray = new IIcon[subBlocks.length];
		for (int i = 0; i < subBlocks.length; i++) {
			this.iconArray[i] = par1IconRegister.registerIcon(Reference.MODID + ":" + BlockReference.ORESAND_NAME + "_" + subBlocks[i]);
		}
	}

}
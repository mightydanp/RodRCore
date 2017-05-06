package com.mightydanp.rodrcore.common.block;

import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockFormFurnace extends Block {

	@SideOnly(Side.CLIENT)
	public static IIcon frontIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon sideIcon;

	protected BlockFormFurnace(String unlocalizedName) {
		super(Material.clay);
		this.setBlockName(unlocalizedName);
		this.setCreativeTab(RodRCore.tabRodRCore);
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			Block block1 = world.getBlock(x, y, z - 1);
			Block block2 = world.getBlock(x, y, z + 1);
			Block block3 = world.getBlock(x - 1, y, z);
			Block block4 = world.getBlock(x + 1, y, z);
			byte b0 = 3;

			if (block1.func_149730_j() && !block2.func_149730_j()) {
				b0 = 3;
			}
			if (block1.func_149730_j() && !block1.func_149730_j()) {
				b0 = 2;
			}
			if (block1.func_149730_j() && !block4.func_149730_j()) {
				b0 = 5;
			}
			if (block1.func_149730_j() && !block3.func_149730_j()) {
				b0 = 4;
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
		int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 3) {
			return this.frontIcon;
		} else{
			return this.sideIcon;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegistry) {
		this.frontIcon = iconRegistry.registerIcon(Reference.MODID + ":" + this.getUnlocalizedName().substring(5) + "_" + "front");
		this.sideIcon = iconRegistry.registerIcon(Reference.MODID + ":" + this.getUnlocalizedName().substring(5) + "_" + "side");
	}

}

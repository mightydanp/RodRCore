package com.mightydanp.rodrcore.api.common.item;

import com.mightydanp.rodrcore.common.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mantle.world.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import tconstruct.TConstruct;
import tconstruct.smeltery.TinkerSmeltery;

public class CItemFluidBucket extends ItemBucket {

	public IIcon bucket;
	public IIcon fluid;
	public int colour;

	public CItemFluidBucket(Block block, int color) {
		super(block);
		this.colour = color;
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		return par2 == 1 ? this.bucket : fluid;
	}

	public void registerIcons(IIconRegister icon) {
		this.bucket = icon.registerIcon(Reference.MODID + ":bucket");
		this.fluid = icon.registerIcon(Reference.MODID + ":bucket_overlay");
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		if (pass == 0)
			return colour;
		return 0xFFFFFF;
	}
}
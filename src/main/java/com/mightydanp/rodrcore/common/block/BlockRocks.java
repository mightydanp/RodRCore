package com.mightydanp.rodrcore.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nullable;

import com.mightydanp.rodrcore.api.common.block.CBlock;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;
import com.mightydanp.rodrcore.common.tileentity.TileEntityRocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockRocks extends BlockBush implements ITileEntityProvider {

	private Random random = new Random();

	public BlockRocks(String unlocalizedName) {
		super(Material.plants);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0025F, 1.0F);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(Reference.MODID + ":" + unlocalizedName);
		this.setCreativeTab(RodRCore.tabRodRCore);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeGrass);
	}

	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return -1;
	}

	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return false;
	}

	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand || block == Blocks.stone;
	}

	@Override
	public Item getItemDropped(int par1, Random random, int par3) {
		return null;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if(!world.isRemote && player.getCurrentEquippedItem().getItem() == Items.shears){
			EntityItem entityBlockRocks = new EntityItem(world, x, y, z, new ItemStack(ModBlocks.rocks, 1, 0));
			world.spawnEntityInWorld(entityBlockRocks);
		}
		return;
	}

	public Random Random;

	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote && !player.capabilities.isCreativeMode && player.inventory.getCurrentItem() == null) {
			Random rand = null;
			world.setBlockToAir(x, y, z);
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ModItems.rock, 1 + random.nextInt(2), 0));
			EntityItem entityItemFlint = new EntityItem(world, x, y, z, new ItemStack(Items.flint, 1 + random.nextInt(2), 0));
			world.spawnEntityInWorld(entityItem);
			if (Math.random() * 100 < 20) {
				world.spawnEntityInWorld(entityItemFlint);
			}
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
		int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Reference.MODID + ":" + "rocks");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityRocks();
	}

}
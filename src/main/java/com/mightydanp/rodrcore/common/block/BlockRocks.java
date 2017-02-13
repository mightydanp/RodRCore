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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockRocks extends CBlock implements IShearable {

	private Random random = new Random();

	public BlockRocks(String unlocalizedName) {
		super(Material.plants, unlocalizedName);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		this.setBlockTextureName(Reference.MODID + ":" + unlocalizedName);
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
		return 0;
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
		return;
	}

	public Random Random;

	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote && !player.capabilities.isCreativeMode && player.inventory.getCurrentItem() == null) {
			Random rand = null;
			world.setBlockToAir(x, y, z);
			EntityItem entityItem = new EntityItem(world, x, y, z,
					new ItemStack(ModItems.rock, 1 + random.nextInt(2), 0));
			world.spawnEntityInWorld(entityItem);
		}
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ModItems.rocks, 1));
		return ret;
	}

}
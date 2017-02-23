package com.mightydanp.rodrcore.common.block;

import java.util.Random;

import com.mightydanp.rodrcore.api.common.block.CBlock;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.lib.BlockReference;
import com.mightydanp.rodrcore.common.lib.GuiReference;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockCampFire extends BlockContainer {

	private final boolean isActive;

	private final Random random = new Random();

	private static boolean keepInventory;

	protected BlockCampFire(String unlocalizedName, Boolean isActive) {
		super(Material.plants);
		this.isActive = isActive;
		this.setHardness(1.0F);
		this.setBlockName(unlocalizedName);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.3750F, 0.9375F);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(ModBlocks.campFireIdle);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCampFire();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
		TileEntityCampFire tileentityCampfire = (TileEntityCampFire) world.getTileEntity(x, y, z);
		ItemStack getHeldItem = (ItemStack)(entityPlayer.getCurrentEquippedItem() != null ? entityPlayer.getCurrentEquippedItem(): null);
		Item flintAndSteel = Items.flint_and_steel;
		if (!world.isRemote) {
			if (!entityPlayer.isSneaking() && entityPlayer.getCurrentEquippedItem() != null && getHeldItem.getItem() == flintAndSteel) {
				if (!world.isRaining() && !world.isThundering()) {
					tileentityCampfire.setBurning(true);
					if(getHeldItem.getItem() == Items.flint_and_steel && getHeldItem != null && !this.isActive){
						getHeldItem.setItemDamage(getHeldItem.getItemDamage() + 1);
						world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 1.0F, random.nextFloat() * 0.4F + 0.8F);
					}
				}
			}
			FMLNetworkHandler.openGui(entityPlayer, RodRCore.instance, GuiReference.CAMPFIRE_GUI_ID, world, x, y, z);
		}

		return true;
	}
	
	public static void damageItemStack(ItemStack itemStack, int damage, EntityPlayer entityPlayer){
		ItemStack item = entityPlayer.getCurrentEquippedItem().copy();
		if(item != null){
			itemStack.setItemDamage(damage);
		}
	}

	public static void updateCampFireBlockState(boolean active, World worldObj, int x, int y, int z) {
		int i = worldObj.getBlockMetadata(x, y, z);

		TileEntity tileEntity = worldObj.getTileEntity(x, y, z);
		keepInventory = true;

		if (active) {
			worldObj.setBlock(x, y, z, ModBlocks.campFireActive);
		} else {
			worldObj.setBlock(x, y, z, ModBlocks.campFireIdle);
		}

		keepInventory = false;

		worldObj.setBlockMetadataWithNotify(x, y, z, i, 2);

		if (tileEntity != null) {
			tileEntity.validate();
			worldObj.setTileEntity(x, y, z, tileEntity);
		}
	}

	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.isRemote)
			return;

		TileEntityCampFire tileentityCampfire = (TileEntityCampFire) world.getTileEntity(x, y, z);

		if (tileentityCampfire != null) {
			if (tileentityCampfire.burning(world, x, y, z, true))
				if (!world.isRaining() || !world.isThundering())
					if (world.canBlockSeeTheSky(x, y, z))
						tileentityCampfire.isBurning();
		}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		TileEntityCampFire tileentityCampfire = (TileEntityCampFire) world.getTileEntity(x, y, z);
		if (this.isActive) {
			float x1 = (float) x + 1.0F;
			float y1 = (float) y + 0.1F +  random.nextFloat() * 6.0F / 16.0F;
			float z1 = (float) z + 0.5F;
			float f3 = 0.52F;
			float f4 = random.nextFloat() * 0.6F - 0.3F;
			world.spawnParticle("smoke", (double) (x1 - f3), (double) y1, (double) (z1 + f4), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", (double) (x1 - f3), (double) y1, (double) (z1 + f4), 0.0D, 0.0D, 0.0D);
			
		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		super.onEntityCollidedWithBlock(world, x, y, z, entity);

		TileEntityCampFire tileentityCampfire = (TileEntityCampFire) world.getTileEntity(x, y, z);

		if (tileentityCampfire != null) {
			if (this.isActive) {
				entity.attackEntityFrom(DamageSource.inFire, 0.5F);
				entity.setFire(8);
			}
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		if (!keepInventory) {
			TileEntityCampFire tileEntityCampFire = (TileEntityCampFire) world.getTileEntity(x, y, z);

			if (tileEntityCampFire != null) {
				for (int i1 = 0; i1 < tileEntityCampFire.getSizeInventory(); ++i1) {
					ItemStack itemstack = tileEntityCampFire.getStackInSlot(i1);

					if (itemstack != null) {
						float f = this.random.nextFloat() * 0.8F + 0.1F;
						float f1 = this.random.nextFloat() * 0.8F + 0.1F;
						float f2 = this.random.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0) {
							int j1 = this.random.nextInt(21) + 10;

							if (j1 > itemstack.stackSize) {
								j1 = itemstack.stackSize;
							}

							itemstack.stackSize -= j1;
							EntityItem entityitem = new EntityItem(world, (double) ((float) x + f),
									(double) ((float) y + f1), (double) ((float) z + f2),
									new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								entityitem.getEntityItem()
										.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
							}

							float f3 = 0.05F;
							entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
							entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);
							world.spawnEntityInWorld(entityitem);
						}
					}
				}

				world.func_147453_f(x, y, z, p_149749_5_);
			}
		}

		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int i) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(ModBlocks.campFireIdle, 1, 0);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase,
			ItemStack itemStack) {
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

		if (itemStack.hasDisplayName()) {
			((TileEntityCampFire) world.getTileEntity(x, y, z)).sedGuiDisplayName(itemStack.getDisplayName());
		}
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Reference.MODID + ":" + "camp_fire");
	}
}

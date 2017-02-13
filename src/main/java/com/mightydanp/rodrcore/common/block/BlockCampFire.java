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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockCampFire extends BlockContainer {

	private final boolean isActive;

	private static boolean keepInventory;

	protected BlockCampFire(String unlocalizedName, Boolean isActive) {
		super(Material.plants);
		this.isActive = isActive;
		this.setHardness(1.0F);
		this.setBlockName(unlocalizedName);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(ModBlocks.campFireIdle);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCampFire();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
		TileEntityCampFire tileentityCampfire = (TileEntityCampFire)world.getTileEntity(x, y, z);
		if (!world.isRemote) {
			if(!entityPlayer.isSneaking() && entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() == Items.flint_and_steel){
				if(!world.isRaining() && !world.isThundering()){
					tileentityCampfire.setBurning(true);
				}
			}
			FMLNetworkHandler.openGui(entityPlayer, RodRCore.instance, GuiReference.CAMPFIRE_GUI_ID, world, x, y, z);
		}

		return true;
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
	
    public void updateTick(World world, int x, int y, int z, Random random) 
    {
    	if(world.isRemote)
    		return;
    	
    	TileEntityCampFire tileentityCampfire = (TileEntityCampFire)world.getTileEntity(x, y, z);
    	
    	if(tileentityCampfire != null){
        	if(tileentityCampfire.burning(world, x, y, z, true))
        		if(!world.isRaining() || !world.isThundering())
        			if(world.canBlockSeeTheSky(x, y, z))
        				tileentityCampfire.isBurning();
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    	TileEntityCampFire tileentityCampfire = (TileEntityCampFire) world.getTileEntity(x, y, z);
    	
    	if(tileentityCampfire != null && tileentityCampfire.burning(world, x, y, z, true) && tileentityCampfire.isBurning())
    	{
    		for(int i = 0; i < 3; i++)
    		{
    			float motionY = (random.nextFloat() / 40F) + 0.025F;
    			
			double particleX = ((x + 0.5F) - 0.15F) + (random.nextInt(30) / 100F);
			double particleY = y + 0.1F + (random.nextInt(15) / 100F);
			double particleZ = ((z + 0.5F) - 0.15F) + (random.nextInt(30) / 100F);

			//PrimevalForest.proxy.spawnFlame(world, particleX, particleY, particleZ, 0.0F, motionY, 0.0F, 16);
			world.spawnParticle("smoke", particleX, particleY, particleZ, 0.0D, 0.05D, 0.0D);
			world.spawnParticle("flame", particleX, particleY, particleZ, 0.0D, 0.05D, 16D);
    		}
    	}
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
    	super.onEntityCollidedWithBlock(world, x, y, z, entity);
    	
    	TileEntityCampFire tileentityCampfire = (TileEntityCampFire)world.getTileEntity(x, y, z);
    	
    	if(tileentityCampfire != null)
    	{
        	if(tileentityCampfire.burning(world, x, y, z, true))
        	{
        		entity.attackEntityFrom(DamageSource.inFire, 1.0F);
        		entity.setFire(1);
        	}
    	}
    }

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int i) {
		return Container.calcRedstoneFromInventory( (IInventory) world.getTileEntity(x, y, z));
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z){
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

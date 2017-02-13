package com.mightydanp.rodrcore.common.item;

import com.mightydanp.rodrcore.api.common.item.CItem;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.block.ModBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemRocks extends CItem {

	public ItemRocks(String unlocalizedName) {
		super(unlocalizedName);
		this.setCreativeTab(RodRCore.tabRodRCore);
        this.setUnlocalizedName(unlocalizedName);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (movingobjectposition == null){
            return par1ItemStack;
        } else{
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)){
                    return par1ItemStack;
                }

                if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)){
                    return par1ItemStack;
                }

                if ( par2World.getBlock(i, j, k).getMaterial() == Material.grass  || par2World.getBlock(i, j, k).getMaterial() == Material.sand || par2World.getBlock(i, j, k).getMaterial() == Material.rock){
                    par2World.setBlock(i, j + 1, k, ModBlocks.rocks);

                    if (!par3EntityPlayer.capabilities.isCreativeMode){
                        --par1ItemStack.stackSize;
                    }
                }
            }

            return par1ItemStack;
        }
    }
}
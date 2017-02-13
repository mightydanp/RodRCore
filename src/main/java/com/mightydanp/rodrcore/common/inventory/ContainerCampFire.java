package com.mightydanp.rodrcore.common.inventory;

import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerCampFire extends Container {

	private TileEntityCampFire tileEntityCampFire;
	public int lastBurnTime;
	public int LastItemBurnTime;
	public int lastCookTime;

	public ContainerCampFire(InventoryPlayer inventoryPlayer, TileEntityCampFire tileEntityCampFire) {
		this.tileEntityCampFire = tileEntityCampFire;

		this.addSlotToContainer(new Slot(tileEntityCampFire, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileEntityCampFire, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntityCampFire, 2, 116, 21));
		this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntityCampFire, 3, 116, 53));

		// player Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i*9+9, 8 + j*18, 84 + i*18));
			}
		}
		// player HotBar
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i*18, 142));
		}
	}
	
	public void addCraftingToCrafters(ICrafting icrafting){
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.tileEntityCampFire.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.tileEntityCampFire.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.tileEntityCampFire.currentItemBurnTime);
	}
	
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++){
			ICrafting iCrafting = (ICrafting)this.crafters.get(i);
			
			if(this.lastCookTime != this.tileEntityCampFire.cookTime){
				iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityCampFire.cookTime);
			}
			
			if(this.lastBurnTime != this.tileEntityCampFire.burnTime){
				iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityCampFire.burnTime);
			}
			
			if(this.LastItemBurnTime != this.tileEntityCampFire.currentItemBurnTime){
				iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityCampFire.currentItemBurnTime);
			}
			
			this.lastCookTime = this.tileEntityCampFire.cookTime;
			this.lastBurnTime = this.tileEntityCampFire.burnTime;
			this.LastItemBurnTime = this.tileEntityCampFire.currentItemBurnTime;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue){
		if(slot == 0)this.tileEntityCampFire.cookTime = newValue;
		if(slot == 1)this.tileEntityCampFire.burnTime = newValue;
		if(slot ==2)this.tileEntityCampFire.currentItemBurnTime = newValue;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot){
		return null;
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return this.tileEntityCampFire.isUseableByPlayer(entityPlayer);
	}

}

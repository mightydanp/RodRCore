package com.mightydanp.rodrcore.common.inventory;

import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerNewFurnace extends Container {

	private TileEntityNewFurnace tileEntityFurnace;
	public int lastBurnTime;
	public int LastItemBurnTime;
	public int lastCookTime;

	public ContainerNewFurnace(InventoryPlayer inventoryPlayer, TileEntityNewFurnace tileEntityFurnace) {
		this.tileEntityFurnace = tileEntityFurnace;

		this.addSlotToContainer(new Slot(tileEntityFurnace, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileEntityFurnace, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntityFurnace, 2, 116, 21));
		this.addSlotToContainer(new SlotFurnaceItemNeeded(tileEntityFurnace, 3, 30, 35));
		this.addSlotToContainer(new SlotEmpty(tileEntityFurnace, 4, 116, 53));

		// player Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		// player HotBar
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters(ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.tileEntityFurnace.furnaceCookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.tileEntityFurnace.furnaceBurnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.tileEntityFurnace.currentItemBurnTime);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting iCrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.tileEntityFurnace.furnaceCookTime) {
				iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityFurnace.furnaceCookTime);
			}

			if (this.lastBurnTime != this.tileEntityFurnace.furnaceBurnTime) {
				iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityFurnace.furnaceBurnTime);
			}

			if (this.LastItemBurnTime != this.tileEntityFurnace.currentItemBurnTime) {
				iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityFurnace.currentItemBurnTime);
			}

			this.lastCookTime = this.tileEntityFurnace.furnaceCookTime;
			this.lastBurnTime = this.tileEntityFurnace.furnaceBurnTime;
			this.LastItemBurnTime = this.tileEntityFurnace.currentItemBurnTime;
		}
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue) {
		if (slot == 0)
			this.tileEntityFurnace.furnaceCookTime = newValue;
		if (slot == 1)
			this.tileEntityFurnace.furnaceBurnTime = newValue;
		if (slot == 2)
			this.tileEntityFurnace.currentItemBurnTime = newValue;
	}

	public ItemStack transferStackInSlot(EntityPlayer enityPlayer, int slots) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slots);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slots == 2) {
				if (!this.mergeItemStack(itemstack1, 5, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if ( slots != 3 && slots != 1 && slots != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityNewFurnace.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (slots >= 5 && slots < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (slots >= 30 && slots < 39 && !this.mergeItemStack(itemstack1, 5, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 5, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(enityPlayer, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return this.tileEntityFurnace.isUseableByPlayer(entityPlayer);
	}

}
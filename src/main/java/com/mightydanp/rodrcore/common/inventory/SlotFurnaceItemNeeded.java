package com.mightydanp.rodrcore.common.inventory;

import com.mightydanp.rodrcore.common.item.ModItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFurnaceItemNeeded extends Slot {

	public SlotFurnaceItemNeeded(IInventory iInventory, int slot, int x, int y) {
		super(iInventory, slot, x, y);
	}

	public boolean isItemValid(ItemStack itemStack) {
		if(itemStack.getItem() == ModItems.clayPan){
			return true;
		}
		if(itemStack.getItem() == ModItems.clayPot){
			return true;
		}
		if(itemStack.getItem() == ModItems.pan){
			return true;
		}
		if(itemStack.getItem() == ModItems.pot){
			return true;
		}
		if(itemStack.getItem() == ModItems.smallCrucible){
			return true;
		}
		return false;
	}

}
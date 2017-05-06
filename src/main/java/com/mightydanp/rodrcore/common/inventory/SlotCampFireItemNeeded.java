package com.mightydanp.rodrcore.common.inventory;

import com.mightydanp.rodrcore.common.item.ModItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCampFireItemNeeded extends Slot {

	public SlotCampFireItemNeeded(IInventory iInventory, int slot, int x, int y) {
		super(iInventory, slot, x, y);
	}

	public boolean isItemValid(ItemStack itemStack) {
		if(itemStack.getItem() == ModItems.fired_clay_pan){
			return true;
		}
		if(itemStack.getItem() == ModItems.fired_clay_pot){
			return true;
		}
		if(itemStack.getItem() == ModItems.pan){
			return true;
		}
		if(itemStack.getItem() == ModItems.pot){
			return true;
		}
		if(itemStack.getItem() == ModItems.small_crucible){
			return true;
		}
		return false;
	}
}
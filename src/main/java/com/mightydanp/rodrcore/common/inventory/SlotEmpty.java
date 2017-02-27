package com.mightydanp.rodrcore.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEmpty extends Slot {

	public SlotEmpty(IInventory iInventory, int slot, int x, int y) {
		super(iInventory, slot, x, y);
	}

	public boolean isItemValid(ItemStack itemStack) {
		return false;
	}

}

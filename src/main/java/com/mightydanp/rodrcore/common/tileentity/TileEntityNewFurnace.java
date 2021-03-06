package com.mightydanp.rodrcore.common.tileentity;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Random;

import com.mightydanp.rodrcore.common.block.BlockNewFurnace;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePanRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfirePotRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireRecipes;
import com.mightydanp.rodrcore.common.item.crafting.CampfireSmallCrucibleRecipes;
import com.mightydanp.rodrcore.common.lib.GuiReference;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityNewFurnace extends TileEntity implements ISidedInventory {
	private static final int[] slotsTop = new int[] { 0, 3 };
	private static final int[] slotsBottom = new int[] { 2, 1, 4 };
	private static final int[] slotsSides = new int[] { 1 };

	private ItemStack[] slots = new ItemStack[5];
	private String localizedName;

	Random random = new Random();

	public int furnaceSpeed = 200;
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;
	public boolean isLit;
	public static final int coolDownRate = 10;
	public int temperature;
	public int dumbyTemperature;
	private int heatRate = 3;
	private int maxTemp = 600;

	public int getSizeInventory() {
		return this.slots.length;
	}

	public ItemStack getStackInSlot(int slot) {
		return slots[slot];
	}

	public ItemStack decrStackSize(int slot, int amount) {
		if (this.slots[slot] != null) {
			ItemStack itemstack;

			if (this.slots[slot].stackSize <= amount) {
				itemstack = this.slots[slot];
				this.slots[slot] = null;
				return itemstack;
			} else {
				itemstack = this.slots[slot].splitStack(amount);

				if (this.slots[slot].stackSize == 0) {
					this.slots[slot] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.slots[slot] != null) {
			ItemStack itemstack = this.slots[slot];
			this.slots[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		this.slots[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	public int getTemperature() {
		final long tick = getWorldObj().getWorldTime();
		if (tick % 20 == 0) {
			if (this.furnaceBurnTime > 0) {
				temperature = Math.min(temperature + 1, maxTemp);
			}
			if (this.furnaceBurnTime == 0) {
				temperature = Math.max(temperature - coolDownRate, 0);
			}
		}
		return temperature;
	}

	public String getInventoryName() {
		return this.isInvNameLocalized() ? this.localizedName : "container.furnace";
	}

	public boolean isInvNameLocalized() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public boolean hasCustomInventoryName() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;
	}

	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.slots.length) {
				this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.furnaceBurnTime = nbtTagCompound.getShort("BurnTime");
		this.furnaceCookTime = nbtTagCompound.getShort("CookTime");
		this.isLit = nbtTagCompound.getBoolean("Lit");
		this.temperature = nbtTagCompound.getInteger("Temperature");
		this.furnaceSpeed = nbtTagCompound.getInteger("FurnaceSpeed");
		this.currentItemBurnTime = getItemBurnTime(this.slots[1]);
		if (nbtTagCompound.hasKey("CustomName", 8)) {
			this.localizedName = nbtTagCompound.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setShort("BurnTime", (short) this.furnaceBurnTime);
		nbtTagCompound.setShort("CookTime", (short) this.furnaceCookTime);
		nbtTagCompound.setBoolean("Lit", this.isLit);
		nbtTagCompound.setInteger("Temperature", this.temperature);
		nbtTagCompound.setInteger("FurnaceSpeed", this.furnaceSpeed);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.slots.length; ++i) {
			if (this.slots[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbtTagCompound.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()) {
			nbtTagCompound.setString("CustomName", this.localizedName);
		}
		this.markDirty();
	}

	public ItemStack getInputSlot() {
		return slots[0];
	}

	public ItemStack getOutputSlot() {
		return slots[2];
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isBurning() {
		return this.furnaceBurnTime > 0;
	}

	public void updateEntity() {
		final long tick = getWorldObj().getWorldTime();

		temperature = getTemperature();

		boolean flag = this.furnaceBurnTime > 0;
		boolean flag1 = false;

		if (this.furnaceBurnTime > 0) {
			--this.furnaceBurnTime;
			ItemStack ash = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1);

			if (random.nextInt(100) < 0.008) {
				if (this.slots[3] == null) {
					this.slots[3] = ash.copy();
				} else if (this.slots[3].isItemEqual(ash) && this.slots[3].stackSize <= getInventoryStackLimit()) {
					this.slots[3].stackSize += ash.stackSize;
				}
			}
		}
			if (this.furnaceBurnTime == 0 && this.slots[1] == null) {
				this.isLit = false;
			}

		if (!this.worldObj.isRemote && flag != this.furnaceBurnTime > 0 || getItemBurnTime(this.slots[1]) > 0 && isLit) {

			if (this.furnaceBurnTime == 0 && burning(worldObj, xCoord, yCoord, zCoord, false)) {
				this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.slots[1]);

				if (this.furnaceBurnTime > 0) {
					flag1 = true;

					if (this.slots[1] != null) {
						--this.slots[1].stackSize;

						if (this.slots[1].stackSize == 0) {
							this.slots[1] = slots[1].getItem().getContainerItem(slots[1]);
						}
					}
				}
			} else if (!burning(worldObj, xCoord, yCoord, zCoord, true)) {
				this.currentItemBurnTime = this.furnaceBurnTime = 0;
			}
			if (this.slots[1] == null) {
				for (int i = 0; i < 40; i++) {
					if (i == 40) {
						this.isLit = false;
					}
				}
			}

			System.out.println(this.temperature);

			if (this.isBurning() && this.canSmelt() && isLit && this.temperature >= 140) {
				++this.furnaceCookTime;

				if (this.furnaceCookTime == furnaceSpeed) {
					this.furnaceCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.furnaceCookTime = 0;
			}
			flag1 = true;
			BlockNewFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}

		if (flag1) {
			this.markDirty();
		}
	}

	public boolean canSmelt() {
		if (this.slots[0] == null || this.temperature < 140) {
			return false;
		} else {
			ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);

			if (itemStack == null) {
				this.furnaceCookTime = 0;
				return false;
			}
			if (this.slots[2] == null)
				return true;
			if (!this.slots[2].isItemEqual(itemStack))
				return false;
			int result = slots[2].stackSize + itemStack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (this.canSmelt() && isLit && this.slots[0] != null) {
			ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);

			if (this.slots[2] == null) {
				this.slots[2] = itemStack.copy();
			} else if (this.slots[2].getItem() == itemStack.getItem()) {
				this.slots[2].stackSize += itemStack.stackSize;
			}

			--this.slots[0].stackSize;

			if (this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
	}

	public boolean burning(World world, int x, int y, int z, boolean lastArgNeeded) {
		return (world.isRaining() || world.isThundering()) && world.canBlockSeeTheSky(x, y, z) && lastArgNeeded ? isLit : true;
	}

	public static int getItemBurnTime(ItemStack itemStack) {
		if (itemStack != null) {
			int fuelVaul = (GameRegistry.getFuelValue(itemStack) > 0 ? GameRegistry.getFuelValue(itemStack) : TileEntityFurnace.getItemBurnTime(itemStack));

			return fuelVaul;
		}
		return 0;
	}

	public static boolean isItemFuel(ItemStack itemStack) {
		return getItemBurnTime(itemStack) > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p_70300_1_.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return slot == 2 || slot == 3 ? false : (slot == 1 ? isItemFuel(itemStack) : true);
	}

	public int[] getAccessibleSlotsFromSide(int slot) {
		return slot == 0 ? slotsBottom : (slot == 1 ? slotsTop : slotsSides);
	}

	public boolean canInsertItem(int slot, ItemStack itemStack, int amount) {
		return this.isItemValidForSlot(slot, itemStack);
	}

	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		return side != 0 || slot != 1 || itemStack.getItem() == Items.bucket;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i) {
		return this.furnaceCookTime * i / furnaceSpeed;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = furnaceSpeed;
		}

		return this.furnaceBurnTime * i / this.currentItemBurnTime;
	}

	public void setLit(boolean lit) {
		this.isLit = lit;
	}
}
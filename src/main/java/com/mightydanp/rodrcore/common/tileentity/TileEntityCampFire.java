package com.mightydanp.rodrcore.common.tileentity;

import java.util.List;
import java.util.Random;

import com.mightydanp.rodrcore.common.block.BlockCampFire;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityCampFire extends TileEntity implements ISidedInventory {
	private String localizedName;

	private static final int[] slots_top = new int[] { 0, 3 };
	private static final int[] slots_bottom = new int[] { 2, 4 };
	private static final int[] slots_sides = new int[] { 1 };

	private ItemStack[] slots = new ItemStack[5];

	Random random = new Random();

	public int furnaceSpeed = 200;
	public static final int maxDecayTime = 400;
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	public boolean burning;
	// public int canStart = this.slots[2].stackSize = 0;

	public String getInvName() {
		return this.isInvNameLocalized() ? this.localizedName : "container.camp_fire";
	}

	public boolean isInvNameLocalized() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;
	}

	@Override
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.slots[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.slots[slot] != null) {
			ItemStack itemStack;

			if (this.slots[slot].stackSize <= amount) {
				itemStack = this.slots[slot];
				this.slots[slot] = null;
				return itemStack;
			} else {
				itemStack = this.slots[slot].splitStack(amount);

				if (this.slots[slot].stackSize == 0) {
					this.slots[slot] = null;
				}

				return itemStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.slots[slot] != null) {
			ItemStack itemStack = this.slots[slot];
			this.slots[slot] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		this.slots[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.localizedName : "container." + GuiReference.CAMPFIRE_GUI_NAME;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
		return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : entityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.slots.length) {
				this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.burnTime = nbt.getShort("BurnTime");
		this.cookTime = nbt.getShort("CookTime");
		this.burning = nbt.getBoolean("Burning");
		this.currentItemBurnTime = getItemBurnTime(this.slots[1]);

		if (nbt.hasKey("CustomName", 8)) {
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) this.burnTime);
		nbt.setShort("CookTime", (short) this.cookTime);
		nbt.setBoolean("Burning", (boolean) this.burning);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.slots.length; ++i) {
			if (this.slots[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", this.localizedName);
		}
	}

	public boolean isBurning() {
		return this.burnTime > 0;
	}

	@Override
	public void updateEntity() {
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;

		if (this.burnTime > 0) {
			this.burnTime--;
			ItemStack ash = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1);

			if (random.nextInt(100) < 0.008) {
				if (this.slots[4] == null) {
					this.slots[4] = ash.copy();
				} else if (this.slots[4].isItemEqual(ash) && this.slots[4].stackSize <= getInventoryStackLimit()) {
					this.slots[4].stackSize += ash.stackSize;
				}
			}
		}
		if (this.burnTime == 0 && this.slots[1] == null) {
			this.burning = false;
		}

		if (!this.worldObj.isRemote) {

			if (this.burnTime != 0 || this.slots[1] != null && burning != false) {
				if (this.burnTime == 0 && burning(worldObj, xCoord, yCoord, zCoord, false)) {
					this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);

					if (this.burnTime > 0) {
						flag1 = true;

						if (this.slots[1] != null) {
							this.slots[1].stackSize--;

							if (this.slots[1].stackSize == 0) {
								this.slots[1] = slots[1].getItem().getContainerItem(slots[1]);
							}
						}
					}
				} else if (!burning(worldObj, xCoord, yCoord, zCoord, true)) {
					this.currentItemBurnTime = this.burnTime = 0;
				}

				if (this.slots[1] == null) {
					for (int i = 0; i < 40; i++) {
						if (i == 40) {
							this.burning = false;
						}
					}
				}

				if (this.isBurning() && this.canSmelt() || this.slots[1] != null && burning == true) {
					if (this.slots[0] == null) {
						this.cookTime = 0;
						flag1 = true;
					} else
						this.cookTime++;

					if (this.cookTime == 200) {
						this.cookTime = 0;
						this.smeltItem();

						flag1 = true;
					}
				} else {
					this.cookTime = 0;
					this.burning = false;
				}
			}
			// ****
			if (flag != this.burnTime > 0 || getItemBurnTime(this.slots[1]) > 0 && burning == true) {

				flag1 = true;
				BlockCampFire.updateCampFireBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if (flag1) {
			this.markDirty();
		}
	}

	private boolean canSmelt() {
		if (this.slots[0] == null) {
			return false;
		} else {
			ItemStack itemStack = CampfireRecipes.smelting().getSmeltingResult(this.slots[0]);

			if (this.slots[3] != null) {
				if (this.slots[3].getItem() == ModItems.small_crucible) {
					itemStack = CampfireSmallCrucibleRecipes.smelting().getSmeltingResult(this.slots[0]);
				}
				if (this.slots[3].getItem() == ModItems.pan || this.slots[3].getItem() == ModItems.fired_clay_pan) {
					itemStack = CampfirePanRecipes.smelting().getSmeltingResult(this.slots[0]);
				}
				if (this.slots[3].getItem() == ModItems.pot || this.slots[3].getItem() == ModItems.fired_clay_pot) {
					itemStack = CampfirePotRecipes.smelting().getSmeltingResult(this.slots[0]);
				}
			}

			if (itemStack == null) {
				this.cookTime = 0;
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

	private void smeltItem() {
		if (this.canSmelt() && burning == true && this.slots[0] != null) {
			ItemStack itemStack = CampfireRecipes.smelting().getSmeltingResult(this.slots[0]);

			if (this.slots[3] != null) {
				if (this.slots[3].getItem() == ModItems.small_crucible) {
					itemStack = CampfireSmallCrucibleRecipes.smelting().getSmeltingResult(this.slots[0]);
				}
				if (this.slots[3].getItem() == ModItems.pan || this.slots[3].getItem() == ModItems.fired_clay_pan) {
					itemStack = CampfirePanRecipes.smelting().getSmeltingResult(this.slots[0]);
				}
				if (this.slots[3].getItem() == ModItems.pot || this.slots[3].getItem() == ModItems.fired_clay_pot) {
					itemStack = CampfirePotRecipes.smelting().getSmeltingResult(this.slots[0]);
				}
			}

			if (this.slots[2] == null) {
				this.slots[2] = itemStack.copy();
			} else if (this.slots[2].isItemEqual(itemStack)) {
				this.slots[2].stackSize += itemStack.stackSize;
			}

			this.slots[0].stackSize--;

			if (this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}

			if (this.slots[4] != null) {
				int damage = slots[3].getItemDamage();
				slots[3].setItemDamage(damage + 1);
				if (slots[3].getItemDamage() >= slots[3].getMaxDamage()) {
					slots[3] = null;
				}
			}
		}
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

	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		if (slot == 3 && isRecipeItem(itemStack))
			return true;
		return slot == 2 || slot == 4 ? false : (slot == 1 ? isItemFuel(itemStack) : true);
	}

	public boolean isRecipeItem(ItemStack itemStack) {
		if (itemStack.getItem() == ModItems.fired_clay_pan)
			return true;
		if (itemStack.getItem() == ModItems.fired_clay_pot)
			return true;
		if (itemStack.getItem() == ModItems.pan)
			return true;
		if (itemStack.getItem() == ModItems.pot)
			return true;
		if (itemStack.getItem() == ModItems.small_crucible)
			return true;
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? slots_bottom : (side == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
		return this.isItemValidForSlot(slot, itemStack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		return slot != 0 || slot != 1;
	}

	public boolean burning(World world, int x, int y, int z, boolean lastArgNeeded) {
		return (world.isRaining() || world.isThundering()) && world.canBlockSeeTheSky(x, y, z) && lastArgNeeded ? burning : true;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = this.furnaceSpeed;
		}

		return this.burnTime * i / this.currentItemBurnTime;
	}

	public int getCookProgressScaled(int i) {
		return this.cookTime * i / this.furnaceSpeed;
	}

	public void setBurning(boolean burning) {
		this.burning = burning;
	}

}
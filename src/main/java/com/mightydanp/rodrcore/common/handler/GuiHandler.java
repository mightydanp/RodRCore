package com.mightydanp.rodrcore.common.handler;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.client.gui.inventory.GuiCampFire;
import com.mightydanp.rodrcore.client.gui.inventory.GuiNewFurnace;
import com.mightydanp.rodrcore.common.RodRCore;
import com.mightydanp.rodrcore.common.inventory.ContainerCampFire;
import com.mightydanp.rodrcore.common.inventory.ContainerNewFurnace;
import com.mightydanp.rodrcore.common.lib.GuiReference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler extends CRegistryHandler implements IGuiHandler {
	public GuiHandler() {
		registerGuiHandler(RodRCore.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if (entity != null) {
			switch (ID) {
			case GuiReference.CAMPFIRE_GUI_ID:
				;
				if (entity instanceof TileEntityCampFire) {
					return new ContainerCampFire(player.inventory, (TileEntityCampFire) entity);
				}
			}
			switch (ID) {
			case GuiReference.FURNACE_GUI_ID:
				;
				if (entity instanceof TileEntityNewFurnace) {
					return new ContainerNewFurnace(player.inventory, (TileEntityNewFurnace) entity);
				}
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if (entity != null) {
			switch (ID) {
			case GuiReference.CAMPFIRE_GUI_ID:
				;
				if (entity instanceof TileEntityCampFire) {
					return new GuiCampFire(player.inventory, (TileEntityCampFire) entity);
				}
			}
			switch (ID) {
			case GuiReference.FURNACE_GUI_ID:
				;
				if (entity instanceof TileEntityNewFurnace) {
					return new GuiNewFurnace(player.inventory, (TileEntityNewFurnace) entity);
				}
			}
		}
		return null;
	}
}

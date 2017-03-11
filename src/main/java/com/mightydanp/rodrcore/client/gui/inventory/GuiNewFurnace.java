package com.mightydanp.rodrcore.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import com.mightydanp.rodrcore.common.inventory.ContainerCampFire;
import com.mightydanp.rodrcore.common.inventory.ContainerNewFurnace;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiNewFurnace extends GuiContainer {
	
	private static final ResourceLocation texture = new ResourceLocation(
			Reference.MODID + ":" + "textures/gui/container/" + "furnace" + ".png");

	public TileEntityNewFurnace tileEntityFurnace;

	public GuiNewFurnace(InventoryPlayer inventoryPlayer, TileEntityNewFurnace tileEntityFurnace) {
		super(new ContainerNewFurnace(inventoryPlayer, tileEntityFurnace));
		this.tileEntityFurnace = tileEntityFurnace;

		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String guiName = this.tileEntityFurnace.isInvNameLocalized() ? this.tileEntityFurnace.getInventoryName(): I18n.format(this.tileEntityFurnace.getInventoryName(), new Object[0]);
		
		this.fontRendererObj.drawString(guiName, this.xSize / 2 - this.fontRendererObj.getStringWidth(guiName) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);

	}
	
	protected int getTempColor() {
		int tempHex = tileEntityFurnace.getTemperature();
		if (tempHex > 2000) return 0xFF0000;

		float percent = (tempHex - 20) / 1980F;

		// 0xFF0000 <- 0x404040
		int r = (int) ((0xFF - 0x40) * percent) + 0x40;
		int g = (int) ((0x00 - 0x40) * percent) + 0x40;
		int b = (int) ((0x00 - 0x40) * percent) + 0x40;

		return r << 16 | g << 8 | b;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.getTextureManager().bindTexture(texture);
		
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (this.tileEntityFurnace.isBurning()) {
			int i = this.tileEntityFurnace.getBurnTimeRemainingScaled(13);
			this.drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - i, 176, 12 - i, 14, i + 1);
			if (this.tileEntityFurnace.furnaceCookTime > 0 && this.tileEntityFurnace.getStackInSlot(0) != null) {
				i = this.tileEntityFurnace.getCookProgressScaled(24);
				this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, i + 1, 16);
			}
		}
	}

}
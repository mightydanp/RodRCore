package com.mightydanp.rodrcore.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import com.mightydanp.rodrcore.common.inventory.ContainerCampFire;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityCampFire;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCampFire extends GuiContainer{
	
	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":" + "textures/gui/container/" + "camp_fire" + ".png");
	
	public TileEntityCampFire tileEntityCampFire;

	public GuiCampFire(InventoryPlayer inventoryPlayer, TileEntityCampFire tileEntityCampFire) {
		super(new ContainerCampFire(inventoryPlayer, tileEntityCampFire));
		
		this.tileEntityCampFire = tileEntityCampFire;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String guiName = this.tileEntityCampFire.isInvNameLocalized() ? this.tileEntityCampFire.getInvName() :I18n.format(this.tileEntityCampFire.getInvName(), new Object[0]);
		
		this.fontRendererObj.drawString(guiName, this.xSize / 2 - this.fontRendererObj.getStringWidth(guiName)/ 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.getTextureManager().bindTexture(texture);
		
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if(this.tileEntityCampFire.isBurning() || this.tileEntityCampFire.isBurning()){
            int i = this.tileEntityCampFire.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - i, 176, 12 - i, 14, i + 1);
            i = this.tileEntityCampFire.getCookProgressScaled(24);
            this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, i + 1, 16);
		}
	}

}

package com.mightydanp.rodrcore.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import com.mightydanp.rodrcore.client.model.ModelNewFurnace;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityNewFurnaceRenderer extends TileEntitySpecialRenderer {

	private ModelNewFurnace modelFurnace;
	private static final ResourceLocation texture = new ResourceLocation(
			Reference.MODID + ":" + "textures/entity/block/" + "furnace_idle" + ".png");
	private static final ResourceLocation texture_lit = new ResourceLocation(
			Reference.MODID + ":" + "textures/entity/block/" + "furnace_lit" + ".png");

	public TileEntityNewFurnaceRenderer() {
		this.modelFurnace = new ModelNewFurnace();
	}

	public void render(TileEntityNewFurnace tileEntity, double x, double y, double z, float f) {
		if (tileEntity == null) {
			return;
		}

		int tileMeta = 0;
		int rotation = 0;

		if (tileEntity.getWorldObj() != null) {
			tileMeta = tileEntity.getBlockMetadata();
		}

		if (tileMeta == 2) {
			rotation = 270;
		}
		if (tileMeta == 3) {
			rotation = 0;
		}
		if (tileMeta == 4) {
			rotation = 90;
		}
		if (tileMeta == 5) {
			rotation = 180;
		}

		if (tileEntity.canSmelt()) {
			this.bindTexture(texture_lit);
		} else {
			this.bindTexture(texture);
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) ((float) x + 0.5f), (float) ((float) y + 1.5f), (float) ((float) z + 0.5f));
		GL11.glScalef((float) 1.0f, (float) -1.0f, (float) -1.0f);
		GL11.glRotatef((float) rotation, (float) 0.0f, (float) 1.0f, (float) 0.0f);
		this.modelFurnace.renderAll();
		GL11.glPopMatrix();
		if (tileEntity.getWorldObj() != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) ((float) x + 0.5f), (float) ((float) y), (float) ((float) z + 0.5f));
			float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 16383) / 16383.0);
			float blockScale = 0.7f;
			EntityItem cookingItem = new EntityItem(tileEntity.getWorldObj());
			cookingItem.hoverStart = 0.0f;

			if (tileEntity.getStackInSlot(0) != null) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float) 0.0f, (float) 0.4375f, (float) 0.0f);
				if (tileMeta == 2) {
					GL11.glTranslatef((float) 0.1875f, (float) 0.0f, (float) 0.0f);
				}
				if (tileMeta == 3) {
					GL11.glTranslatef((float) -0.1875f, (float) 0.0f, (float) 0.0f);
					GL11.glRotatef((float) 180.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
				}
				if (tileMeta == 4) {
					GL11.glTranslatef((float) 0.0f, (float) 0.0f, (float) -0.1875f);
					GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
				}
				if (tileMeta == 5) {
					GL11.glTranslatef((float) 0.0f, (float) 0.0f, (float) 0.1875f);
					GL11.glRotatef((float) 270.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
				}
				GL11.glScalef((float) (blockScale * 1.1f), (float) (blockScale * 1.1f), (float) (blockScale * 1.1f));
				cookingItem.setEntityItemStack(tileEntity.getStackInSlot(0));
				if (Block.getBlockFromItem((Item) tileEntity.getStackInSlot(0).getItem()).renderAsNormalBlock()) {
					GL11.glTranslatef((float) 0.0f, (float) 0.125f, (float) 0.0f);
				}
				RenderManager.instance.renderEntityWithPosYaw(cookingItem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
				GL11.glPopMatrix();
			}
			if (tileEntity.getStackInSlot(1) != null) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float) 0.0f, (float) 0.0625f, (float) 0.0f);
				if (tileMeta == 4 || tileMeta == 5) {
					GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
				}
				GL11.glScalef((float) (blockScale * 0.8f), (float) (blockScale * 0.8f), (float) (blockScale * 0.8f));
				cookingItem.setEntityItemStack(tileEntity.getStackInSlot(1));
				if (Block.getBlockFromItem((Item) tileEntity.getStackInSlot(1).getItem()).renderAsNormalBlock()) {
					GL11.glTranslatef((float) 0.0f, (float) 0.125f, (float) 0.0f);
				}
				RenderManager.instance.renderEntityWithPosYaw(cookingItem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
				GL11.glPopMatrix();
			}
			if (tileEntity.getStackInSlot(2) != null) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float) 0.20f, (float) 1.0f, (float) 0.0f);
				if (tileMeta == 2) {
					GL11.glTranslatef((float) -0.1875f, (float) 0.0f, (float) 0.0f);
				}
				if (tileMeta == 3) {
					GL11.glTranslatef((float) 0.1875f, (float) 0.0f, (float) 0.0f);
					GL11.glRotatef((float) 180.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
				}
				if (tileMeta == 4) {
					GL11.glTranslatef((float) 0.0f, (float) 0.0f, (float) 0.1875f);
					GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
				}
				if (tileMeta == 5) {
					GL11.glTranslatef((float) 0.0f, (float) 0.0f, (float) -0.1875f);
					GL11.glRotatef((float) 270.0f, (float) 0.0f, (float) 1.0f, (float) 0.0f);
				}
				GL11.glScalef((float) (blockScale * 0.8f), (float) (blockScale * 0.8f), (float) (blockScale * 0.8f));
				cookingItem.setEntityItemStack(tileEntity.getStackInSlot(2));
				if (Block.getBlockFromItem((Item) tileEntity.getStackInSlot(2).getItem()).renderAsNormalBlock()) {
					GL11.glTranslatef((float) 0.0f, (float) 0.125f, (float) 0.0f);
				}
				RenderManager.instance.renderEntityWithPosYaw(cookingItem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();
			GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
		}
	}

	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightOpacity(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
	}

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick) {
		this.render((TileEntityNewFurnace) tileentity, x, y, z, partialTick);
	}

}
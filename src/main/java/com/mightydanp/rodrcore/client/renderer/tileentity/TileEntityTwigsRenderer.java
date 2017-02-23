package com.mightydanp.rodrcore.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import com.mightydanp.rodrcore.client.model.ModelCampFire;
import com.mightydanp.rodrcore.client.model.ModelTwigsAndRocks;
import com.mightydanp.rodrcore.common.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityTwigsRenderer extends TileEntitySpecialRenderer {

	private ModelTwigsAndRocks modelTwigs;
	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":" + "textures/entity/block/" + "Twigs" + ".png");

	public TileEntityTwigsRenderer() {
		this.modelTwigs = new ModelTwigsAndRocks();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		int rotation = 0;
		switch (tileEntity.getBlockMetadata() % 4) {
		case 0:
			rotation = 270;
			break;
		case 1:
			rotation = 0;
			break;
		case 2:
			rotation = 90;
			break;
		case 3:
			rotation = 180;
			break;
		}
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		this.modelTwigs.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
        GL11.glPopMatrix();
	}
	
	private void adjustLightFixture(World world, int i, int j, int k, Block block)
    {
            Tessellator tess = Tessellator.instance;
            float brightness = block.getLightOpacity(world, i, j, k);
            int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
            int modulousModifier = skyLight % 65536;
            int divModifier = skyLight / 65536;
            tess.setColorOpaque_F(brightness, brightness, brightness);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
    }

}
package com.mightydanp.rodrcore.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTwigsAndRocks extends ModelBase {
	// fields
	ModelRenderer block;

	public ModelTwigsAndRocks() {
		textureWidth = 32;
		textureHeight = 32;

		block = new ModelRenderer(this, 0, 0);
		block.addBox(0F, 0F, 0F, 16, 0, 16);
		block.setRotationPoint(-8F, 23.9F, -8F);
		block.setTextureSize(32, 32);
		block.mirror = true;
		setRotation(block, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		block.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void renderModel(float f) {
		block.render(f);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
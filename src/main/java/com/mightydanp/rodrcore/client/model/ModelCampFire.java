package com.mightydanp.rodrcore.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCampFire extends ModelBase {
	// fields
	ModelRenderer outercorner1;
	ModelRenderer outercorner2;
	ModelRenderer outercorner3;
	ModelRenderer outercorner4;
	ModelRenderer outercorner5;
	ModelRenderer outercorner6;
	ModelRenderer outercorner7;
	ModelRenderer outercorner8;
	ModelRenderer outercorner9;
	ModelRenderer outercorner10;
	ModelRenderer outercorner11;
	ModelRenderer outercorner12;
	ModelRenderer outercorner13;
	ModelRenderer outercorner14;
	ModelRenderer outercorner15;
	ModelRenderer outercorner16;
	ModelRenderer log1;
	ModelRenderer log2;
	ModelRenderer log3;

	public ModelCampFire() {
		textureWidth = 64;
		textureHeight = 32;
		
		outercorner1 = new ModelRenderer(this, 0, 0);
		outercorner1.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner1.setRotationPoint(-7F, 22F, -1F);
		outercorner1.rotateAngleX = 0F;
		outercorner1.rotateAngleY = 0F;
		outercorner1.rotateAngleZ = 0F;
		outercorner1.mirror = false;
		outercorner2 = new ModelRenderer(this, 0, 0);
		outercorner2.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner2.setRotationPoint(-6F, 22F, -3F);
		outercorner2.rotateAngleX = 0F;
		outercorner2.rotateAngleY = 0F;
		outercorner2.rotateAngleZ = 0F;
		outercorner2.mirror = false;
		outercorner3 = new ModelRenderer(this, 0, 0);
		outercorner3.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner3.setRotationPoint(-5F, 22F, -5F);
		outercorner3.rotateAngleX = 0F;
		outercorner3.rotateAngleY = 0F;
		outercorner3.rotateAngleZ = 0F;
		outercorner3.mirror = false;
		outercorner4 = new ModelRenderer(this, 0, 0);
		outercorner4.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner4.setRotationPoint(-3F, 22F, -6F);
		outercorner4.rotateAngleX = 0F;
		outercorner4.rotateAngleY = 0F;
		outercorner4.rotateAngleZ = 0F;
		outercorner4.mirror = false;
		outercorner5 = new ModelRenderer(this, 0, 0);
		outercorner5.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner5.setRotationPoint(-1F, 22F, -7F);
		outercorner5.rotateAngleX = 0F;
		outercorner5.rotateAngleY = 0F;
		outercorner5.rotateAngleZ = 0F;
		outercorner5.mirror = false;
		outercorner6 = new ModelRenderer(this, 0, 0);
		outercorner6.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner6.setRotationPoint(1F, 22F, -6F);
		outercorner6.rotateAngleX = 0F;
		outercorner6.rotateAngleY = 0F;
		outercorner6.rotateAngleZ = 0F;
		outercorner6.mirror = false;
		outercorner7 = new ModelRenderer(this, 0, 0);
		outercorner7.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner7.setRotationPoint(3F, 22F, -5F);
		outercorner7.rotateAngleX = 0F;
		outercorner7.rotateAngleY = 0F;
		outercorner7.rotateAngleZ = 0F;
		outercorner7.mirror = false;
		outercorner8 = new ModelRenderer(this, 0, 0);
		outercorner8.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner8.setRotationPoint(4F, 22F, -3F);
		outercorner8.rotateAngleX = 0F;
		outercorner8.rotateAngleY = 0F;
		outercorner8.rotateAngleZ = 0F;
		outercorner8.mirror = false;
		outercorner9 = new ModelRenderer(this, 0, 0);
		outercorner9.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner9.setRotationPoint(5F, 22F, -1F);
		outercorner9.rotateAngleX = 0F;
		outercorner9.rotateAngleY = 0F;
		outercorner9.rotateAngleZ = 0F;
		outercorner9.mirror = false;
		outercorner10 = new ModelRenderer(this, 0, 0);
		outercorner10.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner10.setRotationPoint(4F, 22F, 1F);
		outercorner10.rotateAngleX = 0F;
		outercorner10.rotateAngleY = 0F;
		outercorner10.rotateAngleZ = 0F;
		outercorner10.mirror = false;
		outercorner11 = new ModelRenderer(this, 0, 0);
		outercorner11.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner11.setRotationPoint(3F, 22F, 3F);
		outercorner11.rotateAngleX = 0F;
		outercorner11.rotateAngleY = 0F;
		outercorner11.rotateAngleZ = 0F;
		outercorner11.mirror = false;
		outercorner12 = new ModelRenderer(this, 0, 0);
		outercorner12.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner12.setRotationPoint(1F, 22F, 4F);
		outercorner12.rotateAngleX = 0F;
		outercorner12.rotateAngleY = 0F;
		outercorner12.rotateAngleZ = 0F;
		outercorner12.mirror = false;
		outercorner13 = new ModelRenderer(this, 0, 0);
		outercorner13.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner13.setRotationPoint(-1F, 22F, 5F);
		outercorner13.rotateAngleX = 0F;
		outercorner13.rotateAngleY = 0F;
		outercorner13.rotateAngleZ = 0F;
		outercorner13.mirror = false;
		outercorner14 = new ModelRenderer(this, 0, 0);
		outercorner14.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner14.setRotationPoint(-3F, 22F, 4F);
		outercorner14.rotateAngleX = 0F;
		outercorner14.rotateAngleY = 0F;
		outercorner14.rotateAngleZ = 0F;
		outercorner14.mirror = false;
		outercorner15 = new ModelRenderer(this, 0, 0);
		outercorner15.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner15.setRotationPoint(-5F, 22F, 3F);
		outercorner15.rotateAngleX = 0F;
		outercorner15.rotateAngleY = 0F;
		outercorner15.rotateAngleZ = 0F;
		outercorner15.mirror = false;
		outercorner16 = new ModelRenderer(this, 0, 0);
		outercorner16.addBox(0F, 0F, 0F, 2, 2, 2, 0F);
		outercorner16.setRotationPoint(-6F, 22F, 1F);
		outercorner16.rotateAngleX = 0F;
		outercorner16.rotateAngleY = 0F;
		outercorner16.rotateAngleZ = 0F;
		outercorner16.mirror = false;
		log1 = new ModelRenderer(this, 0, 5);
		log1.addBox(0F, 0F, 0F, 2, 8, 2, 0F);
		log1.setRotationPoint(0.0f, 18F, -3.3f);
		this.setRotation(this.log1, 0.296706f, 0.0f, 0.6457718f);
		log1.mirror = true;
		log2 = new ModelRenderer(this, 0, 5);
		log2.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2, 0F);
		log2.setRotationPoint(-1.0f, 18F, -1.0f);
		this.setRotation(this.log2,  0.0f, 0.0f, -0.4712389f);
		log2.mirror = true;
		log3 = new ModelRenderer(this, 0, 5);
		log3.addBox(0F, 0F, 0F, 2, 8, 2, 0F);
		log3.setRotationPoint(-1.0f, 22F, 3.0f);
		this.setRotation(this.log3, -1.413717f, 0.0f, 0.0f);
		log3.mirror = true;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		outercorner1.render(f5);
		outercorner2.render(f5);
		outercorner3.render(f5);
		outercorner4.render(f5);
		outercorner5.render(f5);
		outercorner6.render(f5);
		outercorner7.render(f5);
		outercorner8.render(f5);
		outercorner9.render(f5);
		outercorner10.render(f5);
		outercorner11.render(f5);
		outercorner12.render(f5);
		outercorner13.render(f5);
		outercorner14.render(f5);
		outercorner15.render(f5);
		outercorner16.render(f5);
		log1.render(f5);
		log2.render(f5);
		log3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderModel(float f) {
		outercorner1.render(f);
		outercorner2.render(f);
		outercorner3.render(f);
		outercorner4.render(f);
		outercorner5.render(f);
		outercorner6.render(f);
		outercorner7.render(f);
		outercorner8.render(f);
		outercorner9.render(f);
		outercorner10.render(f);
		outercorner11.render(f);
		outercorner12.render(f);
		outercorner13.render(f);
		outercorner14.render(f);
		outercorner15.render(f);
		outercorner16.render(f);
		log1.render(f);
		log2.render(f);
		log3.render(f);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
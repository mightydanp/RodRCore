package com.mightydanp.rodrcore.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNewFurnace extends ModelBase {
    ModelRenderer EdgeTF;
    ModelRenderer EdgeTL;
    ModelRenderer EdgeTR;
    ModelRenderer EdgeTB;
    ModelRenderer EdgeBF;
    ModelRenderer EdgeBL;
    ModelRenderer EdgeBR;
    ModelRenderer EdgeBB;
    ModelRenderer EdgeFL;
    ModelRenderer EdgeFR;
    ModelRenderer EdgeRL;
    ModelRenderer EdgeRR;
    ModelRenderer TopCover;
    ModelRenderer LeftCover;
    ModelRenderer RightCover;
    ModelRenderer BackCover;
    ModelRenderer BottomCover;
    ModelRenderer Shelf;
    ModelRenderer FrontCover1;
    ModelRenderer FrontCover2a;
    ModelRenderer FrontCover2b;
    ModelRenderer FrontCover3a;
    ModelRenderer FrontCover3b;
    ModelRenderer FrontCover4a;
    ModelRenderer FrontCover4b;
    ModelRenderer FrontCover5;
    ModelRenderer FrontCover6a;
    ModelRenderer FrontCover6b;
    ModelRenderer FrontCover7a;
    ModelRenderer FrontCover7b;
    ModelRenderer FrontCover8a;
    ModelRenderer FrontCover8b;
    ModelRenderer FrontCover9a;
    ModelRenderer FrontCover9b;

    public ModelNewFurnace() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.EdgeTF = new ModelRenderer((ModelBase)this, 16, 15);
        this.EdgeTF.addBox(-8.0f, 8.0f, -8.0f, 15, 1, 1);
        this.EdgeTF.rotateAngleX = 0F;
        EdgeTF.rotateAngleY = 0F;
        EdgeTF.rotateAngleZ = 0F;
        this.EdgeTF.mirror = true;
        this.setRotation(this.EdgeTF, 0.0f, 0.0f, 0.0f);
        this.EdgeTL = new ModelRenderer((ModelBase)this, 16, 0);
        this.EdgeTL.addBox(7.0f, 8.0f, -8.0f, 1, 1, 15);
        this.EdgeTL.rotateAngleX = 0F;
        EdgeTL.rotateAngleY = 0F;
        EdgeTL.rotateAngleZ = 0F;
        this.EdgeTL.mirror = true;
        this.setRotation(this.EdgeTL, 0.0f, 0.0f, 0.0f);
        this.EdgeTR = new ModelRenderer((ModelBase)this, 16, 0);
        this.EdgeTR.addBox(-8.0f, 8.0f, -7.0f, 1, 1, 15);
        this.EdgeTR.rotateAngleX = 0F;
        EdgeTR.rotateAngleY = 0F;
        EdgeTR.rotateAngleZ = 0F;
        this.EdgeTR.mirror = true;
        this.setRotation(this.EdgeTR, 0.0f, 0.0f, 0.0f);
        this.EdgeTB = new ModelRenderer((ModelBase)this, 16, 15);
        this.EdgeTB.addBox(-7.0f, 8.0f, 7.0f, 15, 1, 1);
        this.EdgeTB.rotateAngleX = 0F;
        EdgeTB.rotateAngleY = 0F;
        EdgeTB.rotateAngleZ = 0F;
        this.EdgeTB.mirror = true;
        this.setRotation(this.EdgeTB, 0.0f, 0.0f, 0.0f);
        this.EdgeBF = new ModelRenderer((ModelBase)this, 16, 15);
        this.EdgeBF.addBox(-8.0f, 23.0f, -8.0f, 15, 1, 1);
        this.EdgeBF.rotateAngleX = 0F;
        EdgeBF.rotateAngleY = 0F;
        EdgeBF.rotateAngleZ = 0F;
        this.EdgeBF.mirror = true;
        this.setRotation(this.EdgeBF, 0.0f, 0.0f, 0.0f);
        this.EdgeBL = new ModelRenderer((ModelBase)this, 16, 0);
        this.EdgeBL.addBox(7.0f, 23.0f, -8.0f, 1, 1, 15);
        this.EdgeBL.rotateAngleX = 0F;
        EdgeBL.rotateAngleY = 0F;
        EdgeBL.rotateAngleZ = 0F;
        this.EdgeBL.mirror = true;
        this.setRotation(this.EdgeBL, 0.0f, 0.0f, 0.0f);
        this.EdgeBR = new ModelRenderer((ModelBase)this, 16, 0);
        this.EdgeBR.addBox(-8.0f, 23.0f, -7.0f, 1, 1, 15);
        this.EdgeBR.rotateAngleX = 0F;
        EdgeBR.rotateAngleY = 0F;
        EdgeBR.rotateAngleZ = 0F;
        this.EdgeBR.mirror = true;
        this.setRotation(this.EdgeBR, 0.0f, 0.0f, 0.0f);
        this.EdgeBB = new ModelRenderer((ModelBase)this, 16, 15);
        this.EdgeBB.addBox(-7.0f, 23.0f, 7.0f, 15, 1, 1);
        this.EdgeBB.rotateAngleX = 0F;
        EdgeBB.rotateAngleY = 0F;
        EdgeBB.rotateAngleZ = 0F;
        this.EdgeBB.mirror = true;
        this.setRotation(this.EdgeBB, 0.0f, 0.0f, 0.0f);
        this.EdgeFL = new ModelRenderer((ModelBase)this, 30, 1);
        this.EdgeFL.addBox(7.0f, 9.0f, -8.0f, 1, 14, 1);
        this.EdgeFL.rotateAngleX = 0F;
        EdgeFL.rotateAngleY = 0F;
        EdgeFL.rotateAngleZ = 0F;
        this.EdgeFL.mirror = true;
        this.setRotation(this.EdgeFL, 0.0f, 0.0f, 0.0f);
        this.EdgeFR = new ModelRenderer((ModelBase)this, 30, 1);
        this.EdgeFR.addBox(-8.0f, 9.0f, -8.0f, 1, 14, 1);
        this.EdgeFR.rotateAngleX = 0F;
        EdgeFR.rotateAngleY = 0F;
        EdgeFR.rotateAngleZ = 0F;
        this.EdgeFR.mirror = true;
        this.setRotation(this.EdgeFR, 0.0f, 0.0f, 0.0f);
        this.EdgeRL = new ModelRenderer((ModelBase)this, 30, 1);
        this.EdgeRL.addBox(7.0f, 9.0f, 7.0f, 1, 14, 1);
        this.EdgeRL.rotateAngleX = 0F;
        EdgeRL.rotateAngleY = 0F;
        EdgeRL.rotateAngleZ = 0F;
        this.EdgeRL.mirror = true;
        this.setRotation(this.EdgeRL, 0.0f, 0.0f, 0.0f);
        this.EdgeRR = new ModelRenderer((ModelBase)this, 30, 1);
        this.EdgeRR.addBox(-8.0f, 9.0f, 7.0f, 1, 14, 1);
        this.EdgeRR.rotateAngleX = 0F;
        EdgeRR.rotateAngleY = 0F;
        EdgeRR.rotateAngleZ = 0F;
        this.EdgeRR.mirror = true;
        this.setRotation(this.EdgeRR, 0.0f, 0.0f, 0.0f);
        this.TopCover = new ModelRenderer((ModelBase)this, 72, 0);
        this.TopCover.addBox(-7.0f, 9.0f, -7.0f, 14, 1, 14);
        this.TopCover.rotateAngleX = 0F;
        TopCover.rotateAngleY = 0F;
        TopCover.rotateAngleZ = 0F;
        this.TopCover.mirror = true;
        this.setRotation(this.TopCover, 0.0f, 0.0f, 0.0f);
        this.LeftCover = new ModelRenderer((ModelBase)this, 99, 20);
        this.LeftCover.addBox(6.0f, 10.0f, -6.0f, 1, 13, 13);
        this.LeftCover.rotateAngleX = 0F;
        LeftCover.rotateAngleY = 0F;
        LeftCover.rotateAngleZ = 0F;
        this.LeftCover.mirror = true;
        this.setRotation(this.LeftCover, 0.0f, 0.0f, 0.0f);
        this.RightCover = new ModelRenderer((ModelBase)this, 70, 20);
        this.RightCover.addBox(-7.0f, 10.0f, -6.0f, 1, 13, 13);
        this.RightCover.rotateAngleX = 0F;
        RightCover.rotateAngleY = 0F;
        RightCover.rotateAngleZ = 0F;
        this.RightCover.mirror = true;
        this.setRotation(this.RightCover, 0.0f, 0.0f, 0.0f);
        this.BackCover = new ModelRenderer((ModelBase)this, 0, 33);
        this.BackCover.addBox(-6.0f, 10.0f, 6.0f, 12, 13, 1);
        this.BackCover.rotateAngleX = 0F;
        BackCover.rotateAngleY = 0F;
        BackCover.rotateAngleZ = 0F;
        this.BackCover.mirror = true;
        this.setRotation(this.BackCover, 0.0f, 0.0f, 0.0f);
        this.BottomCover = new ModelRenderer((ModelBase)this, 33, 33);
        this.BottomCover.addBox(-6.0f, 22.0f, -7.0f, 12, 1, 13);
        this.BottomCover.rotateAngleX = 0F;
        BottomCover.rotateAngleY = 0F;
        BottomCover.rotateAngleZ = 0F;
        this.BottomCover.mirror = true;
        this.setRotation(this.BottomCover, 0.0f, 0.0f, 0.0f);
        this.Shelf = new ModelRenderer((ModelBase)this, 49, 1);
        this.Shelf.addBox(-6.0f, 17.0f, -6.0f, 12, 1, 12);
        this.Shelf.rotateAngleX = 0F;
        Shelf.rotateAngleY = 0F;
        Shelf.rotateAngleZ = 0F;
        this.Shelf.mirror = true;
        this.setRotation(this.Shelf, 0.0f, 0.0f, 0.0f);
        this.FrontCover1 = new ModelRenderer((ModelBase)this, 16, 18);
        this.FrontCover1.addBox(-7.0f, 10.0f, -7.0f, 14, 1, 1);
        this.FrontCover1.rotateAngleX = 0F;
        FrontCover1.rotateAngleY = 0F;
        FrontCover1.rotateAngleZ = 0F;
        this.FrontCover1.mirror = true;
        this.setRotation(this.FrontCover1, 0.0f, 0.0f, 0.0f);
        this.FrontCover2a = new ModelRenderer((ModelBase)this, 16, 19);
        this.FrontCover2a.addBox(-7.0f, 11.0f, -7.0f, 4, 1, 1);
        this.FrontCover2a.rotateAngleX = 0F;
        FrontCover2a.rotateAngleY = 0F;
        FrontCover2a.rotateAngleZ = 0F;
        this.FrontCover2a.mirror = true;
        this.setRotation(this.FrontCover2a, 0.0f, 0.0f, 0.0f);
        this.FrontCover2b = new ModelRenderer((ModelBase)this, 35, 19);
        this.FrontCover2b.addBox(3.0f, 11.0f, -7.0f, 4, 1, 1);
        this.FrontCover2b.rotateAngleX = 0F;
        FrontCover2b.rotateAngleY = 0F;
        FrontCover2b.rotateAngleZ = 0F;
        this.FrontCover2b.mirror = true;
        this.setRotation(this.FrontCover2b, 0.0f, 0.0f, 0.0f);
        this.FrontCover3a = new ModelRenderer((ModelBase)this, 16, 20);
        this.FrontCover3a.addBox(-7.0f, 12.0f, -7.0f, 3, 1, 1);
        this.FrontCover3a.rotateAngleX = 0F;
        FrontCover3a.rotateAngleY = 0F;
        FrontCover3a.rotateAngleZ = 0F;
        this.FrontCover3a.mirror = true;
        this.setRotation(this.FrontCover3a, 0.0f, 0.0f, 0.0f);
        this.FrontCover3b = new ModelRenderer((ModelBase)this, 37, 20);
        this.FrontCover3b.addBox(4.0f, 12.0f, -7.0f, 3, 1, 1);
        this.FrontCover3b.rotateAngleX = 0F;
        FrontCover3b.rotateAngleY = 0F;
        FrontCover3b.rotateAngleZ = 0F;
        this.FrontCover3b.mirror = true;
        this.setRotation(this.FrontCover3b, 0.0f, 0.0f, 0.0f);
        this.FrontCover4a = new ModelRenderer((ModelBase)this, 16, 21);
        this.FrontCover4a.addBox(-7.0f, 13.0f, -7.0f, 2, 3, 1);
        this.FrontCover4a.rotateAngleX = 0F;
        FrontCover4a.rotateAngleY = 0F;
        FrontCover4a.rotateAngleZ = 0F;
        this.FrontCover4a.mirror = true;
        this.setRotation(this.FrontCover4a, 0.0f, 0.0f, 0.0f);
        this.FrontCover4b = new ModelRenderer((ModelBase)this, 39, 21);
        this.FrontCover4b.addBox(5.0f, 13.0f, -7.0f, 2, 3, 1);
        this.FrontCover4b.rotateAngleX = 0F;
        FrontCover4b.rotateAngleY = 0F;
        FrontCover4b.rotateAngleZ = 0F;
        this.FrontCover4b.mirror = true;
        this.setRotation(this.FrontCover4b, 0.0f, 0.0f, 0.0f);
        this.FrontCover5 = new ModelRenderer((ModelBase)this, 48, 24);
        this.FrontCover5.addBox(-7.0f, 16.0f, -7.0f, 14, 2, 1);
        this.FrontCover5.rotateAngleX = 0F;
        FrontCover5.rotateAngleY = 0F;
        FrontCover5.rotateAngleZ = 0F;
        this.FrontCover5.mirror = true;
        this.setRotation(this.FrontCover5, 0.0f, 0.0f, 0.0f);
        this.FrontCover6a = new ModelRenderer((ModelBase)this, 16, 26);
        this.FrontCover6a.addBox(-7.0f, 18.0f, -7.0f, 5, 1, 1);
        this.FrontCover6a.rotateAngleX = 0F;
        FrontCover6a.rotateAngleY = 0F;
        FrontCover6a.rotateAngleZ = 0F;
        this.FrontCover6a.mirror = true;
        this.setRotation(this.FrontCover6a, 0.0f, 0.0f, 0.0f);
        this.FrontCover6b = new ModelRenderer((ModelBase)this, 33, 26);
        this.FrontCover6b.addBox(2.0f, 18.0f, -7.0f, 5, 1, 1);
        this.FrontCover6b.rotateAngleX = 0F;
        FrontCover6b.rotateAngleY = 0F;
        FrontCover6b.rotateAngleZ = 0F;
        this.FrontCover6b.mirror = true;
        this.setRotation(this.FrontCover6b, 0.0f, 0.0f, 0.0f);
        this.FrontCover7a = new ModelRenderer((ModelBase)this, 16, 27);
        this.FrontCover7a.addBox(-7.0f, 19.0f, -7.0f, 4, 1, 1);
        this.FrontCover7a.rotateAngleX = 0F;
        FrontCover7a.rotateAngleY = 0F;
        FrontCover7a.rotateAngleZ = 0F;
        this.FrontCover7a.mirror = true;
        this.setRotation(this.FrontCover7a, 0.0f, 0.0f, 0.0f);
        this.FrontCover7b = new ModelRenderer((ModelBase)this, 35, 27);
        this.FrontCover7b.addBox(3.0f, 19.0f, -7.0f, 4, 1, 1);
        this.FrontCover7b.rotateAngleX = 0F;
        FrontCover7b.rotateAngleY = 0F;
        FrontCover7b.rotateAngleZ = 0F;
        this.FrontCover7b.mirror = true;
        this.setRotation(this.FrontCover7b, 0.0f, 0.0f, 0.0f);
        this.FrontCover8a = new ModelRenderer((ModelBase)this, 16, 28);
        this.FrontCover8a.addBox(-7.0f, 20.0f, -7.0f, 3, 1, 1);
        this.FrontCover8a.rotateAngleX = 0F;
        FrontCover8a.rotateAngleY = 0F;
		FrontCover8a.rotateAngleZ = 0F;
        this.FrontCover8a.mirror = true;
        this.setRotation(this.FrontCover8a, 0.0f, 0.0f, 0.0f);
        this.FrontCover8b = new ModelRenderer((ModelBase)this, 37, 28);
        this.FrontCover8b.addBox(4.0f, 20.0f, -7.0f, 3, 1, 1);
        this.FrontCover8b.rotateAngleX = 0F;
        FrontCover8b.rotateAngleY = 0F;
        FrontCover8b.rotateAngleZ = 0F;
        this.FrontCover8b.mirror = true;
        this.setRotation(this.FrontCover8b, 0.0f, 0.0f, 0.0f);
        this.FrontCover9a = new ModelRenderer((ModelBase)this, 16, 30);
        this.FrontCover9a.addBox(-7.0f, 21.0f, -7.0f, 2, 2, 1);
        this.FrontCover9a.rotateAngleX = 0F;
        FrontCover9a.rotateAngleY = 0F;
        FrontCover9a.rotateAngleZ = 0F;
        this.FrontCover9a.mirror = true;
        this.setRotation(this.FrontCover9a, 0.0f, 0.0f, 0.0f);
        this.FrontCover9b = new ModelRenderer((ModelBase)this, 39, 30);
        this.FrontCover9b.addBox(5.0f, 21.0f, -7.0f, 2, 2, 1);
        this.FrontCover9b.rotateAngleX = 0F;
        this.FrontCover9b.rotateAngleY = 0F;
        this.FrontCover9b.rotateAngleZ = 0F;
        this.FrontCover9b.mirror = true;
        this.setRotation(this.FrontCover9b, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.EdgeTF.render(par7);
        this.EdgeTL.render(par7);
        this.EdgeTR.render(par7);
        this.EdgeTB.render(par7);
        this.EdgeBF.render(par7);
        this.EdgeBL.render(par7);
        this.EdgeBR.render(par7);
        this.EdgeBB.render(par7);
        this.EdgeFL.render(par7);
        this.EdgeFR.render(par7);
        this.EdgeRL.render(par7);
        this.EdgeRR.render(par7);
        this.TopCover.render(par7);
        this.LeftCover.render(par7);
        this.RightCover.render(par7);
        this.BackCover.render(par7);
        this.BottomCover.render(par7);
        this.Shelf.render(par7);
        this.FrontCover1.render(par7);
        this.FrontCover2a.render(par7);
        this.FrontCover2b.render(par7);
        this.FrontCover3a.render(par7);
        this.FrontCover3b.render(par7);
        this.FrontCover4a.render(par7);
        this.FrontCover4b.render(par7);
        this.FrontCover5.render(par7);
        this.FrontCover6a.render(par7);
        this.FrontCover6b.render(par7);
        this.FrontCover7a.render(par7);
        this.FrontCover7b.render(par7);
        this.FrontCover8a.render(par7);
        this.FrontCover8b.render(par7);
        this.FrontCover9a.render(par7);
        this.FrontCover9b.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
    	model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    }

    public void renderAll() {
        this.EdgeTF.render(0.0625f);
        this.EdgeTL.render(0.0625f);
        this.EdgeTR.render(0.0625f);
        this.EdgeTB.render(0.0625f);
        this.EdgeBF.render(0.0625f);
        this.EdgeBL.render(0.0625f);
        this.EdgeBR.render(0.0625f);
        this.EdgeBB.render(0.0625f);
        this.EdgeFL.render(0.0625f);
        this.EdgeFR.render(0.0625f);
        this.EdgeRL.render(0.0625f);
        this.EdgeRR.render(0.0625f);
        this.TopCover.render(0.0625f);
        this.LeftCover.render(0.0625f);
        this.RightCover.render(0.0625f);
        this.BackCover.render(0.0625f);
        this.BottomCover.render(0.0625f);
        this.Shelf.render(0.0625f);
        this.FrontCover1.render(0.0625f);
        this.FrontCover2a.render(0.0625f);
        this.FrontCover2b.render(0.0625f);
        this.FrontCover3a.render(0.0625f);
        this.FrontCover3b.render(0.0625f);
        this.FrontCover4a.render(0.0625f);
        this.FrontCover4b.render(0.0625f);
        this.FrontCover5.render(0.0625f);
        this.FrontCover6a.render(0.0625f);
        this.FrontCover6b.render(0.0625f);
        this.FrontCover7a.render(0.0625f);
        this.FrontCover7b.render(0.0625f);
        this.FrontCover8a.render(0.0625f);
        this.FrontCover8b.render(0.0625f);
        this.FrontCover9a.render(0.0625f);
        this.FrontCover9b.render(0.0625f);
    }
}
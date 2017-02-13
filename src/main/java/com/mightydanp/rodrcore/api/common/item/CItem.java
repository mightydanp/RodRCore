package com.mightydanp.rodrcore.api.common.item;

import com.mightydanp.rodrcore.api.common.handler.CRegistryHandler;
import com.mightydanp.rodrcore.common.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class CItem extends Item {

	public CItem(String unlocalizedName){
        this.setUnlocalizedName(unlocalizedName);
	}
	
	public String getUnwrappedUnlocalizedName(String unlocalizedName){
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister){
        this.itemIcon = iconRegister.registerIcon(Reference.MODID + ":" + getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
}

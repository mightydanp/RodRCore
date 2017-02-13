package com.mightydanp.rodrcore.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOreSand extends ItemBlock{

	public static String[] subBlocks = new String[] {"copper", "tin"};
	
	public ItemOreSand(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		int i = itemStack.getItemDamage();
		if(i < 0 || i >= subBlocks.length){
			i = 0;
		}
		
		return super.getUnlocalizedName() + "_" + subBlocks[i];
	}
	
	@Override
	public int getMetadata(int meta)
    {
        return meta;
    }

}
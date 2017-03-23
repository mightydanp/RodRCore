package com.mightydanp.rodrcore.api.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Random;

import com.mightydanp.rodrcore.api.common.fluid.IFluidAction;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffect;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectAcid;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectAlcool;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectBiofuel;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectCold;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectHealthBoost;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectJump;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectMilk;
import com.mightydanp.rodrcore.api.common.fluid.effect.FluidEffectPoison;
import com.mightydanp.rodrcore.api.common.lib.TinkersFluidRegistry;
import com.mightydanp.rodrcore.common.RodRCore;

@SuppressWarnings("all")
public class CBlockFluid extends BlockFluidClassic {
	@SideOnly(Side.CLIENT)
    private IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    private IIcon flowingIcon;
	private FluidEffect effect;
	private String setEffect;
	public int colour = 0xffffffff;
	private FluidStack fluid;
	private HashMap<String, Fluid> fluids = TinkersFluidRegistry.fluids;
	private FluidEffect FluidEffect = new FluidEffect();
	private FluidEffectAcid acid = new FluidEffectAcid();
	private FluidEffectAlcool alcool = new FluidEffectAlcool();
	private FluidEffectBiofuel biofuel= new FluidEffectBiofuel();
	private FluidEffectCold cold= new FluidEffectCold();
	private FluidEffectHealthBoost healthboost= new FluidEffectHealthBoost();
	private FluidEffectJump jump= new FluidEffectJump();
	private FluidEffectMilk milk= new FluidEffectMilk();
	private FluidEffectPoison poison= new FluidEffectPoison();

	public CBlockFluid(Fluid fluid, String blockName, String setEffect, int color) {
		super(fluid, Material.lava);
		setCreativeTab(CreativeTabs.tabMisc);
		
		this.colour = color;
		this.setEffect = setEffect;
		this.effect = this.getEffect();
		
		this.fluid = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
	}
	
	public FluidEffect getEffect(){
		return setEffect == "acid" ? acid : (setEffect == "alcool" ? alcool :(setEffect == "biofuel" ? biofuel :(setEffect == "cold" ? cold : (setEffect == "healthboost" ? healthboost :(setEffect == "jump" ? jump:(setEffect == "milk" ? jump: (setEffect == "poison" ? poison: FluidEffect)))))));
	
	}
	
 
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (effect != null)
			effect.onTouch(world, x, y, z, entity);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (effect != null)
			effect.onTick(world, x, y, z, rand);

		super.updateTick(world, x, y, z, rand);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (side <= 1) {
			return this.stillIcon;
		}
		return this.flowingIcon;
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
    	stillIcon = register.registerIcon("rodrcore:fluidStill");
    	flowingIcon = register.registerIcon("rodrcore:fluidFlowing");
    	}

	@Override
	public String getLocalizedName() {
		return fluid.getLocalizedName();
	}

	@Override
	public String getUnlocalizedName() {
		return fluid.getUnlocalizedName();
	}

	@Override
	public int getBlockColor() {
		return colour;
	}

	@Override
	public int getRenderColor(int p_149741_1_) {
		return colour;
	}
	
	public IIcon getFlowingIcon() {
		return this.flowingIcon;
	}
	
	public IIcon getStillIcon() {
		return this.stillIcon;
	}
	
	@Override
	public int getRenderType() {
		return RodRCore.liquidRender;
	}
	
	@Override
    public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.getMapColorForBlockColored(colour);
    }
	
	@Override
    public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
        return colour;
    }
}
package com.mightydanp.rodrcore.api.common.lib;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.mightydanp.rodrcore.api.common.block.CBlockFluid;
import com.mightydanp.rodrcore.api.common.fluid.CFluid;
import com.mightydanp.rodrcore.api.common.item.CItemBlockFluid;
import com.mightydanp.rodrcore.api.common.item.CItemFluidBucket;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tconstruct.TinkerMaterial;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TinkersFluidRegistry {

    public static HashMap<String, CFluid> fluids = new HashMap<>();
    public static Block liquid;
    public static Item bucket;

    public static void preinit() {
    	TinkerMaterial.materials.keySet().forEach(mat ->  fluids.putIfAbsent("fluid_" + mat.toLowerCase(), new CFluid("fluid_" + mat.toLowerCase())));
    }

    public static void init () {
        fluids.forEach((name, fluid) -> {
            fluid.setTemperature(400);
            fluid.setViscosity(4);
            fluid.setDensity(3);
            fluid.setColor(TinkerMaterial.materials.get(StringUtils.capitalize(name.substring(6))).Colour);
            FluidRegistry.registerFluid(fluid);
            liquid = new CBlockFluid(fluid, name, "", TinkerMaterial.materials.get(StringUtils.capitalize(name.substring(6))).Colour).setBlockName(name);
            bucket = new CItemFluidBucket(liquid, TinkerMaterial.materials.get(StringUtils.capitalize(name.substring(6))).Colour);
            GameRegistry.registerBlock(liquid, CItemBlockFluid.class, "fluid_" + name).setBlockName(name);
            GameRegistry.registerItem(bucket, name + "_bucket");
            fluid.setUnlocalizedName(name);
            bucket.setUnlocalizedName(name + "_bucket");
            fluids.put(name, fluid);
        });
    }
}

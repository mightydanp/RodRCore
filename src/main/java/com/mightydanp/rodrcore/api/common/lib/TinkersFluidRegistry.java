package com.mightydanp.rodrcore.api.common.lib;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.mightydanp.rodrcore.api.common.block.CBlockFluid;
import com.mightydanp.rodrcore.common.lib.Reference;
import com.mightydanp.rodrcore.common.tconstruct.TinkerMaterial;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TinkersFluidRegistry {

    public static HashMap<String, Fluid> fluids = new HashMap<>();
    public static HashMap<String, Block> leFluids = new HashMap<>();
    public static Block lef;

    public static void preinit() {
    	TinkerMaterial.materials.keySet().forEach(mat ->
                fluids.putIfAbsent("fluid_" + mat.toLowerCase(), new Fluid("fluid_" + mat.toLowerCase())));
    }

    public static void init () {
        fluids.forEach((name, fluid) -> {
            fluid.setTemperature(400);
            fluid.setViscosity(4);
            fluid.setDensity(3);
            FluidRegistry.registerFluid(fluid);
            lef = new CBlockFluid(fluid, TinkerMaterial.materials.get(StringUtils.capitalize(name.substring(6))).Colour).setBlockName(name);
            leFluids.putIfAbsent(name, lef);
            GameRegistry.registerBlock(lef,"fluid_" + name);
            fluid.setUnlocalizedName(lef.getUnlocalizedName());
        });
    }
}

package com.mightydanp.rodrcore.common.world.gen.feature;

import java.util.Random;

import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.lib.Reference;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTwigsAndRocks extends WorldGenerator{
    private Block getBlock;
    private int field_150551_b;

    public WorldGenTwigsAndRocks(Block setBlock){
        this.getBlock = setBlock;
    }

    public void func_150550_a(Block p_150550_1_, int p_150550_2_)
    {
        this.getBlock = p_150550_1_;
        this.field_150551_b = p_150550_2_;
    }

    public boolean generate(World par1World, Random par2Random, int X, int Y, int Z){
		
        for (int l = 0; l < 32; ++l) {
            int i1 = X + par2Random.nextInt(8) - par2Random.nextInt(8);
            int j1 = Y + par2Random.nextInt(4) - par2Random.nextInt(4);
            int k1 = Z + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(X, Y, Z) && j1 < 255 && this.getBlock.canBlockStay(par1World, X, Y, Z)){
            	if(par1World.getBlock(X, Y - 1, Z) == Blocks.grass ||par1World.getBlock(X, Y - 1, Z) == Blocks.sand || par1World.getBlock(X, Y - 1, Z) == Blocks.stone){
            		par1World.setBlock(X, Y, Z, this.getBlock, this.field_150551_b, 2);
            }
                	
            	
            }
        }
		return true;
	}
}
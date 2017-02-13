package com.mightydanp.rodrcore.api.common.world.gen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class CWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1: generateNether(world, random, chunkX* 16, chunkZ* 16);
            break;
		case 0: generateSurface(world, random, chunkX* 16, chunkZ* 16);
            break;
		case 1: generateEnd(world, random, chunkX* 16, chunkZ* 16);
            break;
            default:
            ;
		}
	}

	public void generateNether(World world, Random random, int chunkX, int chunkZ) {}

	public void generateSurface(World world, Random random, int chunkX, int chunkZ) {}

	public void generateEnd(World world, Random random, int chunkX, int chunkZ) {}

	public void spawnOres(Block block, int meta, Block blockReplaced, World world, Random random, int chunkX, int chunkZ, int vainSize, int spawnChance, int YMin, int YMax) {
		//int vainSize = minVainSize + random.nextInt(maxVainSize - minVainSize);
		
		for(int i = 0; i < spawnChance; i ++){
			int posX = chunkX + random.nextInt(16);
			int posY = YMin + random.nextInt(YMax-YMin);
			int posZ = chunkZ + random.nextInt(16);
			(new WorldGenMinable(block, meta, vainSize, blockReplaced)).generate(world, random, posX, posY, posZ);
	    }
     }



}

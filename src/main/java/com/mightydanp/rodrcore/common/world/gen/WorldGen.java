package com.mightydanp.rodrcore.common.world.gen;

import java.util.Random;

import com.mightydanp.rodrcore.api.common.world.gen.CWorldGen;
import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.world.gen.feature.WorldGenTwigsAndRocks;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGen extends CWorldGen {

	@Override
	public void generateNether(World world, Random random, int chunkX, int chunkZ) {

	}

	@Override
	public void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);

		// int rand = new Random().nextInt(100);
		this.spawnTwigsAndRocks(ModBlocks.twigs, world, random, chunkX, chunkZ, 2);
		this.spawnTwigsAndRocks(ModBlocks.rocks, world, random, chunkX, chunkZ, 3);

		if (biome != biome.desert && biome != biome.desertHills) {
			this.spawnOres(ModBlocks.oreSand, 0, Blocks.sand, world, random, chunkX, chunkZ, 4, 80, 0, 250);
			this.spawnOres(ModBlocks.oreSand, 1, Blocks.sand, world, random, chunkX, chunkZ, 4, 75, 0, 250);
		}
	}

	@Override
	public void generateEnd(World world, Random random, int chunkX, int chunkZ) {

	}

	private void spawnTwigsAndRocks(Block block, World world, Random random, int chunkX, int chunkZ, int spawnChance) {
		for (int i = 0; i < spawnChance; i++) {
			int posX = chunkX + random.nextInt(16);
			int posY = (world.getHeightValue(chunkX, chunkZ));
			int posZ = chunkZ + random.nextInt(16);
			(new WorldGenTwigsAndRocks(block)).generate(world, random, posX, posY, posZ);
		}
	}

}

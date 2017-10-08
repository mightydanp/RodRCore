package com.mightydanp.rodrcore.common.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class OreGenEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void vanillaGenerationAttempt(PopulateChunkEvent event) {
		World world = event.world;
		int endX = (event.chunkX * 16) + 16;
		int endZ = (event.chunkZ * 16) + 16;

		// Get the highest possibly existing block location
		int maxY = event.chunkProvider.provideChunk(event.chunkX, event.chunkZ).getTopFilledSegment() + 15;

		for (int x = event.chunkX * 16; x < endX; ++x) {
			for (int z = event.chunkZ * 16; z < endZ; ++z) {
				for (int y = 0; y < maxY; ++y) {
					if ((world.getBlock(x, y, z) == Blocks.emerald_ore)) {
						world.setBlock(x, y, z, Blocks.stone, 0, 2);
					}
					if ((world.getBlock(x, y, z) == Blocks.quartz_ore)) {
						world.setBlock(x, y, z, Blocks.netherrack, 0, 2);
					}
				}
			}
		}
	}
}

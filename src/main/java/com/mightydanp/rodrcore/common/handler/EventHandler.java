package com.mightydanp.rodrcore.common.handler;

import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventHandler {
	
	@SubscribeEvent
    public void onBlockPlaced(BlockEvent.PlaceEvent event) {
		if (!event.world.isRemote && event.block == Blocks.furnace) {
			event.setCanceled(true);
			int l = MathHelper.floor_double((double) (event.player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

			if (l == 0) {
				event.world.setBlock(event.x, event.y, event.z, ModBlocks.furnaceIdle, 5, 2);
			}

			if (l == 1) {
				event.world.setBlock(event.x, event.y, event.z, ModBlocks.furnaceIdle, 2, 2);
			}

			if (l == 2) {
				event.world.setBlock(event.x, event.y, event.z, ModBlocks.furnaceIdle, 3, 2);
			}

			if (l == 3) {
				event.world.setBlock(event.x, event.y, event.z, ModBlocks.furnaceIdle, 4, 2);
			}
			event.setCanceled(false);
		}
	}
}

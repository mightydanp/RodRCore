package com.mightydanp.rodrcore.common.handler;

import cpw.mods.fml.common.eventhandler.Event;
import com.mightydanp.rodrcore.api.common.block.CBlockFluid;
import com.mightydanp.rodrcore.api.common.helper.BlockHelper;
import com.mightydanp.rodrcore.api.common.helper.FluidHelper;
import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

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
	
	@SubscribeEvent
	public void fillBucket(FillBucketEvent event) {
		Block block = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);

		if (block instanceof CBlockFluid) {
			ItemStack filledBucket = FluidContainerRegistry.fillFluidContainer(new FluidStack(((CBlockFluid) block).getFluid(), 1000), event.current);

			if (filledBucket == null) {
				event.setResult(Event.Result.DENY);
				event.setCanceled(true);

				return;
			}

			event.setResult(Event.Result.ALLOW);
			event.result = filledBucket;
			event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
		}
	}

	@SubscribeEvent
	public void emptyBucket(PlayerInteractEvent event) {
		if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return;

		ItemStack stack = event.entityPlayer.getHeldItem();

		if (FluidContainerRegistry.isBucket(stack)) {
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(stack);

			if (fluid == null || !(fluid.getFluid().getBlock() instanceof CBlockFluid)) return;

			// deny block use as we're calling it now
			if (event.world.getBlock(event.x, event.y, event.z).onBlockActivated(event.world, event.x, event.y, event.z, event.entityPlayer, event.face, 0, 0, 0)) {
				event.useBlock = Event.Result.DENY;

				return;
			}

			if (!FluidHelper.playerPlaceFluid(event.entityPlayer, BlockHelper.getAdjacentBlock(event.x, event.y, event.z, event.face), BlockHelper.getOppositeSide(event.face), stack, event.world, fluid)) {
				return;
			}

			ItemStack bucket = new ItemStack(Items.bucket);

			if (stack.stackSize == 1) {
				event.entityPlayer.setCurrentItemOrArmor(0, bucket);

				stack.stackSize = 0;
			} else {
				stack.stackSize--;

				if(!event.entityPlayer.inventory.addItemStackToInventory(bucket)) {
					event.entityPlayer.dropPlayerItemWithRandomChoice(bucket, false);
				}
			}

			if (event.entityPlayer instanceof EntityPlayerMP) {
				((EntityPlayerMP) event.entityPlayer).mcServer.getConfigurationManager().syncPlayerInventory((EntityPlayerMP) event.entityPlayer);
			}
		}
	}
}

package com.mightydanp.rodrcore.common.handler;

import cpw.mods.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mightydanp.rodrcore.api.common.block.CBlockFluid;
import com.mightydanp.rodrcore.api.common.helper.BlockHelper;
import com.mightydanp.rodrcore.api.common.helper.FluidHelper;
import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import gregtech.common.tools.GT_Tool_Knife;
import ic2.core.Ic2Items;
import ic2.core.block.machine.BlockMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.common.blocks.BlockAiry;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

public class EventHandler {

	private Random random = new Random();

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
		if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
			return;

		ItemStack stack = event.entityPlayer.getHeldItem();

		if (FluidContainerRegistry.isBucket(stack)) {
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(stack);

			if (fluid == null || !(fluid.getFluid().getBlock() instanceof CBlockFluid))
				return;

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

				if (!event.entityPlayer.inventory.addItemStackToInventory(bucket)) {
					event.entityPlayer.dropPlayerItemWithRandomChoice(bucket, false);
				}
			}

			if (event.entityPlayer instanceof EntityPlayerMP) {
				((EntityPlayerMP) event.entityPlayer).mcServer.getConfigurationManager().syncPlayerInventory((EntityPlayerMP) event.entityPlayer);
			}
		}
	}

	@SubscribeEvent
	public void cannotPlaceBlock(BlockEvent.PlaceEvent event) {
		if (event.placedBlock == ModBlocks.form_furnace || event.placedBlock instanceof BlockMachine) {
			event.setCanceled(true);
			event.player.addChatMessage(new ChatComponentText("No, no, no, you cant do that."));
		}
	}

	@SubscribeEvent
	public void onFurnaceRightClick(PlayerInteractEvent e) {
		Block block = e.world.getBlock(e.x, e.y, e.z);

		if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && (block == Blocks.furnace || block instanceof BlockMachine)) {
			e.setCanceled(true);
			e.world.setBlockToAir(e.x, e.y, e.z);
			e.entityPlayer.addChatMessage(new ChatComponentText("No, no, no, you cant do that."));
		}
		
		if (!e.world.isRemote && e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && block.getMaterial() == Material.wood) {
			List<String> block_name = findAllOreNames(new ItemStack(block));
			List<String> item_name = findAllOreNames(e.entityLiving.getHeldItem());
			for (String blockName : block_name) {
				for (String itemName : item_name) {
					if (blockName.matches("logWood") && itemName.matches("craftingToolKnife")) {
						EntityItem entityBarkStrip = new EntityItem(e.world, e.entityPlayer.posX, e.entityPlayer.posY, e.entityPlayer.posZ, new ItemStack(ModItems.bark_strip, 1, 0));
						EntityItem entityPlank = new EntityItem(e.world, e.x, e.y, e.z, new ItemStack(Blocks.planks, 1, 0));
						if (Math.random() * 100 < 10) {
							e.world.spawnEntityInWorld(entityBarkStrip);
							e.entityPlayer.getHeldItem().attemptDamageItem(+1, random);
						}
						if (Math.random() * 100 < 10) {
							e.world.setBlockToAir(e.x, e.y, e.z);
							if (Math.random() * 100 < 35) {
								e.world.spawnEntityInWorld(entityPlank);
							}
						}
						if (Math.random() * 100 < 2) {
							--e.entityPlayer.getHeldItem().stackSize;
						}
					}

				}
			}
		}
	}

	private static List<String> findAllOreNames(ItemStack input) {
		int[] ids = OreDictionary.getOreIDs(input);
		ArrayList<String> results = new ArrayList<String>();
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			results.add(name);
		}
		return results;
	}

	@SubscribeEvent
	public void onFurnaceBreak(BlockEvent.BreakEvent e) {
		if (e.block == Blocks.furnace || e.block == Blocks.lit_furnace) {
			e.setCanceled(true);
			e.world.setBlockToAir(e.x, e.y, e.z);
		}
	}

	@SubscribeEvent
	public void onNitorClick(PlayerInteractEvent e) {
		Block block = e.world.getBlock(e.x, e.y, e.z);
		if (!e.world.isRemote && e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && e.entityPlayer.isSneaking() && block == ConfigBlocks.blockAiry && ConfigBlocks.blockAiry.getDamageValue(e.world, e.x, e.y, e.z) == 1) {
			e.world.setBlockToAir(e.x, e.y, e.z);
			EntityItem entityNitor = new EntityItem(e.world, e.x, e.y, e.z, new ItemStack(ConfigItems.itemResource, 1, 1));
			e.world.spawnEntityInWorld(entityNitor);
		}
	}

	@SubscribeEvent
	public void onCraftRock(PlayerEvent.ItemCraftedEvent e) {
		if (e.crafting.getItem().equals(ModItems.rock) && Math.random() * 100 < 15) {
			ItemStack stoneDust = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 1);
			e.player.inventory.addItemStackToInventory(stoneDust);
		}
	}
}

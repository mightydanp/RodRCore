package com.mightydanp.rodrcore.common.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mightydanp.rodrcore.api.common.block.CBlockFluid;
import com.mightydanp.rodrcore.api.common.helper.BlockHelper;
import com.mightydanp.rodrcore.api.common.helper.FluidHelper;
import com.mightydanp.rodrcore.api.common.item.CItemAxe;
import com.mightydanp.rodrcore.common.block.ModBlocks;
import com.mightydanp.rodrcore.common.item.ModItems;
import com.mightydanp.rodrcore.common.tileentity.TileEntityNewFurnace;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Material_Casings;
import gregtech.common.blocks.GT_Material_Machines;
import gregtech.common.blocks.GT_Material_Reinforced;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import gregtech.common.tools.GT_Tool_Knife;
import gregtech.common.tools.GT_Tool_Wrench;
import ic2.core.Ic2Items;
import ic2.core.block.machine.BlockMachine;
import infiniteinvo.core.InfiniteInvo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import tconstruct.items.tools.Battleaxe;
import tconstruct.items.tools.Excavator;
import tconstruct.items.tools.Hammer;
import tconstruct.items.tools.Hatchet;
import tconstruct.items.tools.LumberAxe;
import tconstruct.items.tools.Mattock;
import tconstruct.items.tools.Pickaxe;
import tconstruct.items.tools.Scythe;
import tconstruct.items.tools.Shovel;
import thaumcraft.common.blocks.BlockAiry;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.MaterialAiry;

public class EventHandler {

	private Random random = new Random();

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

		if (event.placedBlock == ModBlocks.form_furnace || event.placedBlock instanceof BlockMachine) {
			event.setCanceled(true);
			event.player.addChatMessage(new ChatComponentText("No, no, no, you cant do that."));
		}
	}

	@SubscribeEvent
	public void onBlockClick(PlayerInteractEvent e) {
		Block block = e.world.getBlock(e.x, e.y, e.z);

		if (!e.world.isRemote && e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
			if (block == Blocks.furnace || block instanceof BlockMachine) {
				e.setCanceled(true);
				e.world.setBlockToAir(e.x, e.y, e.z);
				e.entityPlayer.addChatMessage(new ChatComponentText("No, no, no, you cant do that."));
			}
		}
	}

	@SubscribeEvent
	public void breakSpeed(BreakSpeed e) {
		Block block = e.block;
		ItemStack itemStackHeld = e.entityPlayer.getHeldItem();
		Material getBlockMaterial = e.block.getMaterial();

		if (itemStackHeld != null && !e.entityPlayer.capabilities.isCreativeMode) {
			Item itemHeld = e.entityPlayer.getHeldItem().getItem();
			if (!(itemHeld.canHarvestBlock(block, itemStackHeld) || itemHeld.getDigSpeed(itemStackHeld, block, e.metadata) > 1.0F) && !(getBlockMaterial == Material.plants || getBlockMaterial == Material.redstoneLight || getBlockMaterial == Material.tnt || getBlockMaterial == Material.vine || getBlockMaterial instanceof MaterialAiry)) {
				if (!(hasOreDictName(itemStackHeld, "craftingToolWrench") || hasOreDictName(itemStackHeld, "craftingToolWireCutter") || hasOreDictName(itemStackHeld, "scoop") || hasOreDictName(itemStackHeld, "craftingToolScoop") || itemHeld instanceof Scythe || itemHeld instanceof LumberAxe || itemHeld instanceof LumberAxe || itemHeld instanceof Hatchet || itemHeld instanceof CItemAxe)){
					e.newSpeed = 0;
				}
			}
			
			if (hasOreDictName(itemStackHeld, "scoop") || hasOreDictName(itemStackHeld, "craftingToolScoop")) {
				if (!(getBlockMaterial instanceof forestry.apiculture.MaterialBeehive)) {
					e.newSpeed = 0;
				}
			}

			if (hasOreDictName(itemStackHeld, "craftingToolWrench")) {
				if (!(getBlockMaterial instanceof GT_Material_Machines) &&  !(getBlockMaterial instanceof GT_Material_Casings)) {
					e.newSpeed = 0;
				}
			}
			
			if (hasOreDictName(itemStackHeld, "craftingToolWireCutter")) {
				if (!(getBlockMaterial instanceof GT_Material_Machines)) {
					e.newSpeed = 0;
				}
			}

			if (itemHeld instanceof Hatchet)
				if (!(getBlockMaterial == Material.wood || getBlockMaterial == Material.leaves || getBlockMaterial == Material.vine || getBlockMaterial == Material.circuits || getBlockMaterial == Material.cactus || getBlockMaterial == Material.gourd || getBlockMaterial instanceof GT_Material_Reinforced)) {
					e.newSpeed = 0;
				}

			if (itemHeld instanceof Scythe)
				if (!(getBlockMaterial == Material.web || getBlockMaterial == Material.cactus || getBlockMaterial == Material.plants || getBlockMaterial == Material.leaves || getBlockMaterial == Material.vine || getBlockMaterial == Material.gourd)) {
					e.newSpeed = 0;
				}
			
			if (itemHeld instanceof LumberAxe)
				if (!(getBlockMaterial == Material.wood || getBlockMaterial == Material.vine || getBlockMaterial == Material.circuits || getBlockMaterial == Material.cactus || getBlockMaterial == Material.gourd || getBlockMaterial instanceof GT_Material_Reinforced)) {
					e.newSpeed = 0;
				}
			if (itemHeld instanceof CItemAxe)
				if (!(getBlockMaterial == Material.wood || getBlockMaterial == Material.vine || getBlockMaterial == Material.circuits || getBlockMaterial == Material.cactus || getBlockMaterial == Material.gourd || getBlockMaterial instanceof GT_Material_Reinforced)) {
					e.newSpeed = 0;
				}
			
		} else {
			if (!getBlockMaterial.isToolNotRequired()) {
				e.newSpeed = 0;
			}

			if (!(getBlockMaterial == Material.plants || getBlockMaterial == Material.redstoneLight || getBlockMaterial == Material.tnt || getBlockMaterial == Material.vine || getBlockMaterial instanceof MaterialAiry)) {
				e.newSpeed = 0;
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent e) {
		if (e.block == Blocks.furnace || e.block == Blocks.lit_furnace) {
			e.setCanceled(true);
			e.world.setBlockToAir(e.x, e.y, e.z);
		}
		
		if (hasOreDictName(new ItemStack(e.block), "stoneSmooth")) {
			EntityItem entityUnlockSlot = new EntityItem(e.world, e.x, e.y, e.z, new ItemStack(InfiniteInvo.unlock, 1, 0));
			if (random.nextInt(100) < 0.04) {
				e.world.spawnEntityInWorld(entityUnlockSlot);
			}
		}
	}

	@SubscribeEvent
	public void onCraftRock(PlayerEvent.ItemCraftedEvent e) {
		if (e.crafting.getItem().equals(ModItems.rock) && random.nextInt(100) < 15) {
			ItemStack stoneDust = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 1);
			e.player.inventory.addItemStackToInventory(stoneDust);
		}
	}

	private static boolean hasOreDictName(ItemStack stack, String name) {
		int id = OreDictionary.getOreID(name);
		return Arrays.stream(OreDictionary.getOreIDs(stack)).anyMatch(i -> i == id);
	}

}

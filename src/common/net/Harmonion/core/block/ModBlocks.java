package net.Harmonion.core.block;

import net.Harmonion.core.item.ModItems;
import net.Harmonion.core.main.creativetabs.CreativeTabHarmonionB;
import net.Harmonion.core.block.BlockHarmonionBlock;
import net.Harmonion.core.block.BlockHarmonionDoor;
import net.Harmonion.core.block.BlockHarmonionLeaves;
import net.Harmonion.core.block.BlockHarmonionLog;
import net.Harmonion.core.block.BlockHarmonionOre;
import net.Harmonion.core.block.BlockHarmonionPlank;
import net.Harmonion.core.block.BlockHarmonionSapling;
import net.Harmonion.core.lib.BlockIds;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
    /* Mod Block instances */
	public static Block HarmonionOre;
	public static Block HarmonionBlock;
	public static Block HarmonionLog;
	public static Block HarmonionLeaves;
	public static Block HarmonionSapling;
	public static Block HarmonionPlank;
	public static Block HarmonionDoor;
	public static Block HarmonionWire;
	

	/* WorldGen Reg */
	//public static HarmonionWorldGenerator worldGen = new HarmonionWorldGenerator();
	
	public static final CreativeTabs tabHarmonioncraftB = new CreativeTabHarmonionB(CreativeTabs.getNextID(), "HarmonioncraftB");
	
	public static void init() {
		
		/* Initialize each mod block individually */
		HarmonionOre = new BlockHarmonionOre(BlockIds.Harmonion);
		HarmonionBlock = new BlockHarmonionBlock(BlockIds.Harmonion_Block, 8);
		HarmonionLog = new BlockHarmonionLog(BlockIds.Harmonion_Log);
		HarmonionLeaves = new BlockHarmonionLeaves(BlockIds.Harmonion_Leaves);
		HarmonionSapling = new BlockHarmonionSapling(BlockIds.Harmonion_Sapling, 13);
		HarmonionDoor = new BlockHarmonionDoor(BlockIds.Harmonion_Door, 11, 12, Material.iron);
		HarmonionPlank = new BlockHarmonionPlank(BlockIds.Harmonion_Plank);
		
		/* Gives Blocks its name */
		/** Removed */
		
		/* Adds Blocks into the game */
		GameRegistry.registerBlock(HarmonionOre);
		GameRegistry.registerBlock(HarmonionBlock);
		GameRegistry.registerBlock(HarmonionLog);
		GameRegistry.registerBlock(HarmonionLeaves);
		GameRegistry.registerBlock(HarmonionSapling);
		GameRegistry.registerBlock(HarmonionDoor);
		GameRegistry.registerBlock(HarmonionPlank);
		
		/* Block Recipes*/
		//initBlockRecipes();
		
		/* WorldGen */
		//GameRegistry.registerWorldGenerator(worldGen);
		
	}
	
	public static void initBlockRecipes() {
		
		/* Harmonic Block Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModBlocks.HarmonionBlock), new Object[] {"###", "###", "###", '#', ModItems.Harmonionpearl});
		
		/* Harmonic Block Recipe*/
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Harmonionpearl, 9), new Object[] {ModBlocks.HarmonionBlock});
		
		/* Harmonic Plank Recipe*/
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.HarmonionPlank, 4), new Object[] {ModBlocks.HarmonionLog});
	}
	
	public static void initBlockSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonon Ore Smelting. */
		furnaceRecipes.addSmelting(ModBlocks.HarmonionOre.blockID, new ItemStack(ModItems.Refinedsoundstone), 5.0F);
	}
	
}

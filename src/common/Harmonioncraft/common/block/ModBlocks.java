package Harmonioncraft.common.block;

import Harmonioncraft.common.block.item.ItemHarmonionWire;
import Harmonioncraft.common.item.ModItems;
import Harmonioncraft.common.lib.BlockIds;
import Harmonioncraft.common.worldgen.HarmonionWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

public class ModBlocks {

	/* Block name constants */
	public static final String Sound_Stone_Ore_Name = "Soundstoneore";
	public static final String Sound_Stone_Portal_Name = "Soundstoneportal";
	public static final String Sound_Stone_Block_Name = "Soundstoneblock";
	public static final String Sound_Stone_Fire_Name = "Soundstonefire";
	public static final String Sound_Stone_Log_Name = "Soundstonelog";
	public static final String Sound_Stone_Leaves_Name = "Soundstoneleaves";
	public static final String Sound_Stone_Sapling_Name = "Soundstonesapling";
	public static final String Sound_Stone_Plank_Name = "Soundstoneplank";
	public static final String Sound_Stone_Door_Name = "Soundstonedoor";
	public static final String Sound_Stone_Wire_Name = "Soundstonewire";

    /* Mod Block instances */
	public static Block HarmonionOre;
	public static Block HarmonionBlock;
	public static BlockHarmonionPortal HarmonionPortal;
	public static BlockHarmonionFire HarmonionFire;
	public static Block HarmonionLog;
	public static Block HarmonionLeaves;
	public static Block HarmonionSapling;
	public static Block HarmonionPlank;
	public static Block HarmonionDoor;
	public static Block HarmonionWire;

	/* WorldGen Reg */
	public static HarmonionWorldGenerator worldGen = new HarmonionWorldGenerator();
	
	public static void init() {
		
		/* Initialize each mod block individually */
		HarmonionOre = new BlockHarmonionOre(BlockIds.Harmonion, 0).setBlockName(Sound_Stone_Ore_Name);
		HarmonionPortal = (BlockHarmonionPortal) new BlockHarmonionPortal(BlockIds.Harmonion_Portal, 14).setBlockName(Sound_Stone_Portal_Name);
		HarmonionBlock = new BlockHarmonionBlock(BlockIds.Harmonion_Block, 8).setBlockName(Sound_Stone_Block_Name);
		HarmonionFire = (BlockHarmonionFire) new BlockHarmonionFire(BlockIds.Harmonion_Fire, 31).setBlockName(Sound_Stone_Fire_Name);
		HarmonionLog = new BlockHarmonionLog(BlockIds.Harmonion_Log).setBlockName(Sound_Stone_Log_Name);
		HarmonionLeaves = new BlockHarmonionLeaves(BlockIds.Harmonion_Leaves).setBlockName(Sound_Stone_Leaves_Name);
		HarmonionSapling = new BlockHarmonionSapling(BlockIds.Harmonion_Sapling, 12).setBlockName(Sound_Stone_Sapling_Name);
		HarmonionDoor = new BlockHarmonionDoor(BlockIds.Harmonion_Door, 37, 38, Material.iron).setBlockName(Sound_Stone_Door_Name);
		HarmonionPlank = new BlockHarmonionPlank(BlockIds.Harmonion_Plank).setBlockName(Sound_Stone_Plank_Name);
		HarmonionWire = new BlockHarmonionWire(BlockIds.Harmonion_Wire).setBlockName(Sound_Stone_Wire_Name);
		
		/* Gives Blocks its name */
		/** Removed */
		
		/* Adds Blocks into the game */
		GameRegistry.registerBlock(HarmonionOre);
		GameRegistry.registerBlock(HarmonionPortal);
		GameRegistry.registerBlock(HarmonionBlock);
		GameRegistry.registerBlock(HarmonionFire);
		GameRegistry.registerBlock(HarmonionLog);
		GameRegistry.registerBlock(HarmonionLeaves);
		GameRegistry.registerBlock(HarmonionSapling);
		GameRegistry.registerBlock(HarmonionDoor);
		GameRegistry.registerBlock(HarmonionPlank);
		GameRegistry.registerBlock(HarmonionWire, ItemHarmonionWire.class);
		
		/* Block Recipes*/
		//initBlockRecipes();
		
		/* WorldGen */
		GameRegistry.registerWorldGenerator(worldGen);
		
	}
	
	public static void initBlockRecipes() {
		
		/* Harmonic Block Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModBlocks.HarmonionBlock), new Object[] {"###", "###", "###", '#', ModItems.Harmonionpearl});
		/**GameRegistry.addRecipe(new ItemStack(ModBlocks.HarmonionBlock, 1), 
				new Object[]{"hhh","hhh","hhh", 
			Character.valueOf('h'), ModItems.Harmonionpearl
		});*/
		
		/* Harmonic Block Recipe*/
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Harmonionpearl, 9), new Object[] {ModBlocks.HarmonionBlock});
	}
	
	public static void initBlockSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonon Ore Smelting. */
		furnaceRecipes.addSmelting(ModBlocks.HarmonionOre.blockID, new ItemStack(ModItems.Refinedsoundstone), 5.0F);
	}
	
}

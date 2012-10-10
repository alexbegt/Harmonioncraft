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
	public static final String Soundstoneore = "Soundstoneore";
	public static final String Soundstoneportal = "Soundstoneportal";
	public static final String Soundstoneblock = "Soundstoneblock";
	public static final String Soundstonefire = "Soundstonefire";
	public static final String Soundstonelog = "Soundstonelog";
	public static final String Soundstoneleaves = "Soundstoneleaves";
	public static final String Soundstonesapling = "Soundstonesapling";
	public static final String Soundstoneplank = "Soundstoneplank";
	public static final String Soundstonedoor = "Soundstonedoor";
	public static final String Soundstonewire = "Soundstonewire";

	//hey
	
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
		HarmonionOre = new BlockHarmonionOre(BlockIds.HARMONION, 0).setBlockName(Soundstoneore);
		HarmonionPortal = (BlockHarmonionPortal) new BlockHarmonionPortal(BlockIds.HARMONIONPORTAL, 14).setBlockName(Soundstoneportal);
		HarmonionBlock = new BlockHarmonionBlock(BlockIds.HARMONIONBLOCK, 8).setBlockName(Soundstoneblock);
		HarmonionFire = (BlockHarmonionFire) new BlockHarmonionFire(BlockIds.HARMONIONFIRE, 31).setBlockName(Soundstonefire);
		HarmonionLog = new BlockHarmonionLog(BlockIds.HARMONIONLOG).setBlockName(Soundstonelog);
		HarmonionLeaves = new BlockHarmonionLeaves(BlockIds.HARMONIONLEAVES).setBlockName(Soundstoneleaves);
		HarmonionSapling = new BlockHarmonionSapling(BlockIds.HARMONIONSAPLING, 12).setBlockName(Soundstonesapling);
		HarmonionDoor = new BlockHarmonionDoor(BlockIds.HARMONIONDOOR, 37, 38, Material.iron).setBlockName(Soundstonedoor);
		HarmonionPlank = new BlockHarmonionPlank(BlockIds.HARMONIONPLANK).setBlockName(Soundstoneplank);
		HarmonionWire = new BlockHarmonionWire(BlockIds.HARMONIONWIRE).setBlockName(Soundstonewire);
		
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

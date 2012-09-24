package Harmonioncraft.common.block;

import Harmonioncraft.common.item.ModItems;
import Harmonioncraft.common.lib.BlockIds;
import Harmonioncraft.common.worldgen.HarmonionWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.ItemStack;

public class ModBlocks {
	
	/* Block name constants */
	public static final String Soundstoneore = "Soundstoneore";
	public static final String Soundstoneportal = "Soundstoneportal";
	public static final String Soundstoneblock = "Soundstoneblock";
	public static final String Soundstonefire = "Soundstonefire";

    /* Mod Block instances */
	public static Block HarmonionOre;
	public static Block HarmonionBlock;
	public static BlockHarmonionPortal HarmonionPortal;
	public static BlockHarmonionFire HarmonionFire;
	
	/* WorldGen Reg */
	public static HarmonionWorldGenerator worldGen = new HarmonionWorldGenerator();
	
	public static void init() {
		
		/* Initialize each mod block individually */
		HarmonionOre = new BlockHarmonionOre(BlockIds.HARMONION, 0).setBlockName(Soundstoneore);
		HarmonionPortal = (BlockHarmonionPortal) new BlockHarmonionPortal(BlockIds.HARMONIONPORTAL, 14).setBlockName(Soundstoneportal);
		HarmonionBlock = new BlockHarmonionBlock(BlockIds.HARMONIONBLOCK, 8).setBlockName(Soundstoneblock);
		HarmonionFire = (BlockHarmonionFire) new BlockHarmonionFire(BlockIds.HARMONIONFIRE, 31).setBlockName(Soundstonefire);
		
		/* Gives Blocks its name */
		LanguageRegistry.addName(HarmonionOre, "Harmonion Ore");
		LanguageRegistry.addName(HarmonionPortal, "Soundstone Portal");
		LanguageRegistry.addName(HarmonionBlock, "Soundstone Block");
		LanguageRegistry.addName(HarmonionFire, "Soundstone Fire [WIP]");
		
		/* Adds Blocks into the game */
		GameRegistry.registerBlock(HarmonionOre);
		GameRegistry.registerBlock(HarmonionPortal);
		GameRegistry.registerBlock(HarmonionBlock);
		GameRegistry.registerBlock(HarmonionFire);
		
		/* Block Recipes*/
		initBlockRecipes();
		
		/* WorldGen */
		GameRegistry.registerWorldGenerator(worldGen);
		
	}
	
	private static void initBlockRecipes() {
		
	}
	
	public static void initBlockSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonon Ore Smelting. */
		furnaceRecipes.addSmelting(ModBlocks.HarmonionOre.blockID, new ItemStack(ModItems.Refinedsoundstone), 5.0F);
	}
	
}

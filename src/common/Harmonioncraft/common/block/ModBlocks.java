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

    /* Mod Block instances */
	public static Block HarmonionOre;
	
	/* WorldGen Reg */
	public static HarmonionWorldGenerator worldGen = new HarmonionWorldGenerator();
	
	public static void init() {
		
		/* Initialize each mod block individually */
		HarmonionOre = new BlockHarmonionOre(BlockIds.HARMONION, 0).setBlockName(Soundstoneore);
		
		/* Gives Blocks its name */
		LanguageRegistry.addName(HarmonionOre, "Harmonion Ore");
		
		/* Adds Blocks into the game */
		GameRegistry.registerBlock(HarmonionOre);
		
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

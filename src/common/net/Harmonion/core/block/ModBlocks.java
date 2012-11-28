package net.Harmonion.core.block;

import net.Harmonion.core.item.ModItems;
import net.Harmonion.core.main.creativetabs.CreativeTabHarmonionB;
import net.Harmonion.core.world.WorldPopulator;
import net.Harmonion.core.lib.BlockIds;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
    /* Mod Block instances */
	public static Block HarmonionOre;
	public static Block HarmonionLog;
	public static Block HarmonionLeaves;
	public static Block HarmonionSapling;
	public static Block HarmonionDoor;
	public static Block HarmonionGlass;
	
	public static final CreativeTabs tabHarmonioncraftB = new CreativeTabHarmonionB(CreativeTabs.getNextID(), "HarmonioncraftB");
	
	public static void init() {
		
		/* Initialize each mod block individually */
		HarmonionOre = new BlockHarmonionOre(BlockIds.Harmonion, 0);
		HarmonionLog = new BlockHarmonionLog(BlockIds.Harmonion_Log);
		HarmonionLeaves = new BlockHarmonionLeaves(BlockIds.Harmonion_Leaves);
		HarmonionSapling = new BlockHarmonionSapling(BlockIds.Harmonion_Sapling, 13);
		HarmonionDoor = new BlockHarmonionDoor(BlockIds.Harmonion_Door, 11, 12, Material.iron);
		HarmonionGlass = new BlockHarmonionGlass(BlockIds.Harmonion_Glass, 4, Material.glass, false);
		
		/* Adds Blocks into the game */
		OreDictionary.registerOre("HarmonionOre", HarmonionOre);
        GameRegistry.registerBlock(HarmonionOre, BlockOresMeta.class);
		GameRegistry.registerBlock(HarmonionLeaves);
		GameRegistry.registerBlock(HarmonionLog);
		GameRegistry.registerBlock(HarmonionDoor);
		GameRegistry.registerBlock(HarmonionGlass);
		
		/* WorldGen */
		GameRegistry.registerWorldGenerator(new WorldPopulator());
		
	}
	
	public static void initBlockSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonon Ore Smelting. */
		furnaceRecipes.addSmelting(ModBlocks.HarmonionOre.blockID, new ItemStack(ModItems.Refinedsoundstone), 5.0F);
	}
	
}

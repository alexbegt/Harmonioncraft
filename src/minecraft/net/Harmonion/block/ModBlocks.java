package net.Harmonion.block;

import net.Harmonion.block.power.BlockMicro;
import net.Harmonion.creativetab.CreativeTabHarmonionB;
import net.Harmonion.item.ModItems;
import net.Harmonion.item.power.ItemMicro;
import net.Harmonion.server.Harmonion;
import net.Harmonion.tileentity.CoverLib;
import net.Harmonion.tileentity.MicroPlacementWire;
import net.Harmonion.tileentity.TileBluewire;
import net.Harmonion.tileentity.TileCovered;
import net.Harmonion.util.BlockIds;
import net.Harmonion.world.WorldProviderHarmonion;
import net.Harmonion.world.gen.feature.WorldPopulator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
    /* Mod Block instances */
	public static Block HarmonionOre;
	public static Block HarmonionBlock;
	public static BlockHarmonionPortal HarmonionPortal;
	public static Block HarmonionFire;
	public static Block HarmonionLog;
	public static Block HarmonionLeaves;
	public static Block HarmonionSapling;
	public static Block HarmonionDoor;
	public static Block HarmonionGlass;
	
	public static BlockMicro blockMicro;
	public static int customBlockModel;
	
	public static final CreativeTabs tabHarmonioncraftB = new CreativeTabHarmonionB(CreativeTabs.getNextID(), "HarmonionB");
	public static final CreativeTabs tabHarmonioncraftW = new CreativeTabHarmonionB(CreativeTabs.getNextID(), "HarmonionW");
	
	public static void init() {
		
		/* Initialize each mod block individually */
		HarmonionOre = new BlockHarmonionOre(BlockIds.Harmonion, 0);
		HarmonionBlock = new BlockHarmonionBlock(BlockIds.Harmonion_Block, 3);
		HarmonionLog = new BlockHarmonionLog(BlockIds.Harmonion_Log);
		HarmonionLeaves = new BlockHarmonionLeaves(BlockIds.Harmonion_Leaves);
		HarmonionSapling = new BlockHarmonionSapling(BlockIds.Harmonion_Sapling, 13);
		HarmonionDoor = new BlockHarmonionDoor(BlockIds.Harmonion_Door, 11, 12, Material.iron);
		HarmonionGlass = new BlockHarmonionGlass(BlockIds.Harmonion_Glass, 4, Material.glass, false);
		HarmonionPortal = (BlockHarmonionPortal)((BlockHarmonionPortal)(new BlockHarmonionPortal(BlockIds.Harmonion_Portal, 14)));
		HarmonionFire = (new BlockHarmonionFire(BlockIds.Harmonion_Fire, Block.fire.blockIndexInTexture));
		
		blockMicro = new BlockMicro(609);
        blockMicro.setBlockName("rpwire");
        GameRegistry.registerBlock(blockMicro, ItemMicro.class, "micro");
        blockMicro.addTileEntityMapping(0, TileCovered.class);
    	CoverLib.blockCoverPlate = blockMicro;

		/* Adds Blocks into the game */
		OreDictionary.registerOre("HarmonionOre", HarmonionOre);
        GameRegistry.registerBlock(HarmonionOre, BlockOresMeta.class);
		GameRegistry.registerBlock(HarmonionLeaves);
		GameRegistry.registerBlock(HarmonionLog);
		GameRegistry.registerBlock(HarmonionDoor);
		GameRegistry.registerBlock(HarmonionGlass);
		
		GameRegistry.registerBlock(HarmonionBlock);
		GameRegistry.registerBlock(HarmonionPortal);
		GameRegistry.registerBlock(HarmonionFire);
		
		DimensionManager.registerProviderType(BlockIds.Harmonion_Dimension, WorldProviderHarmonion.class, false);
        DimensionManager.registerDimension(BlockIds.Harmonion_Dimension, BlockIds.Harmonion_Dimension);
		
		/* WorldGen */
		GameRegistry.registerWorldGenerator(new WorldPopulator());
		
	}
	
	public static void initBlockSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonon Ore Smelting. */
		furnaceRecipes.addSmelting(ModBlocks.HarmonionOre.blockID, new ItemStack(ModItems.Refinedsoundstone), 5.0F);
	}
	
}

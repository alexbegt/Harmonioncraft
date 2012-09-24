package Harmonioncraft.common.item;

import Harmonioncraft.common.lib.ItemIds;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.EnumHelper;

/**
 * ModItems
 * 
 * Contains all relevant mod item instances
 * 
 * @author Alexbegt, DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModItems {
	
	/* Item name constants */
	public static final String Harmonicsword = "Harmonicsword";
	public static final String Soundstoneingot = "Refinedsoundstone";
	public static final String Soundstonepearl = "Soundstonepearl";
	public static final String Soundstonepick = "Soundstonepick";
	public static final String Soundstoneaxe = "Soundstoneaxe";
	public static final String Soundstoneshovel = "Soundstoneshovel";
	public static final String Soundstonehoe = "Soundstonehoe";
	
	/* Mod item instances */
	public static Item HarmonionSword;
	public static Item Refinedsoundstone;
	public static Item Harmonionpearl;
	public static Item Harmonionpick;
	public static Item Harmonionaxe;
	public static Item Harmonionshovel;
	public static Item Harmonionhoe;
	
	static EnumToolMaterial harmoniontool = EnumHelper.addToolMaterial("Harmonion", 3, 3000, 40.0F, 3, 9);
	
	public static void init() {
		
        /* Initialize each mod item individually */
		HarmonionSword = new ItemHarmonionSword(ItemIds.HARMONIONSWORD, harmoniontool).setIconCoord(0,4).setItemName(Harmonicsword).setCreativeTab(CreativeTabs.tabCombat);
		Refinedsoundstone = new ItemHarmonionIngot(ItemIds.REFINEDSOUNDSTONE).setIconCoord(6, 1).setItemName(Soundstoneingot).setCreativeTab(CreativeTabs.tabMaterials);
		Harmonionpearl = new ItemHarmonionPearl(ItemIds.HARMONIONPEARL).setIconCoord(11, 6).setItemName(Soundstonepearl).setCreativeTab(CreativeTabs.tabMisc);
		Harmonionpick = new ItemHarmonionPick(ItemIds.HARMONIONPICK, harmoniontool).setIconCoord(0,6).setItemName(Soundstonepick).setCreativeTab(CreativeTabs.tabTools);
		Harmonionaxe = new ItemHarmonionAxe(ItemIds.HARMONIONAXE, harmoniontool).setIconCoord(0,7).setItemName(Soundstoneaxe).setCreativeTab(CreativeTabs.tabTools);
		Harmonionshovel = new ItemHarmonionShovel(ItemIds.HARMONIONSHOVEL, harmoniontool).setIconCoord(0,5).setItemName(Soundstoneshovel).setCreativeTab(CreativeTabs.tabTools);
		Harmonionhoe = new ItemHarmonionHoe(ItemIds.HARMONIONHOE, harmoniontool).setIconCoord(0,8).setItemName(Soundstonehoe).setCreativeTab(CreativeTabs.tabTools);
		
		/* Gives Item its name */
		LanguageRegistry.addName(HarmonionSword, "Harmonic Sword");
		LanguageRegistry.addName(Refinedsoundstone, "Refined Soundstone");
		LanguageRegistry.addName(Harmonionpearl, "Soundstone Pearl");
		LanguageRegistry.addName(Harmonionpick, "Soundstone Pickaxe");
		LanguageRegistry.addName(Harmonionaxe, "Soundstone Axe");
		LanguageRegistry.addName(Harmonionshovel, "Soundstone Shovel");
		LanguageRegistry.addName(Harmonionhoe, "Soundstone Hoe");
		
		/* Item Recipes*/
		initItemRecipes();
		
		/* Item Smelting */
		initItemSmelting();
		
	}
	
	private static void initItemRecipes() {
		
		/* Harmonic Sword Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.HarmonionSword), 
				new Object[]{" h "," h "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Pick Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionpick), 
				new Object[]{"hhh"," s "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Axe Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionaxe), 
				new Object[]{"hh ","hs "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Hoe Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionhoe), 
				new Object[]{"hh "," s "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Shovel Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionshovel), 
				new Object[]{" h "," s "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
	}
	
	private static void initItemSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonion Pearl Smelting. */
		furnaceRecipes.addSmelting(ModItems.Harmonionpearl.shiftedIndex, new ItemStack(ModItems.Refinedsoundstone, 10), 10);
	}
}

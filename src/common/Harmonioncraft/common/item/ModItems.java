package Harmonioncraft.common.item;

import Harmonioncraft.client.core.ClientProxy;
import Harmonioncraft.common.lib.ItemIds;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

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
	public static final String Soundstonehelmet = "Soundstonehelmet";
	public static final String Soundstonechestplate = "Soundstonechestplate";
	public static final String Soundstonelegs = "Soundstonelegs";
	public static final String Soundstoneboots = "Soundstoneboots";
	public static final String Resoniumpowder = "Resoniumpowder";
	public static final String Soundstonechip = "Soundstonechip";
	public static final String Soundstonedoor = "Soundstonedoor";
	
	/* Mod item instances */
	public static Item HarmonionSword;
	public static Item Refinedsoundstone;
	public static Item Harmonionpearl;
	public static Item Harmonionpick;
	public static Item Harmonionaxe;
	public static Item Harmonionshovel;
	public static Item Harmonionhoe;
	public static Item Harmonionhelmet;
	public static Item Harmonionchestplate;
	public static Item Harmonionlegs;
	public static Item Harmonionboots;
	public static Item HarmonionChip;
	public static Item HarmonionPowder;
	public static Item HarmonionDoor;
	
	static EnumToolMaterial harmoniontool = EnumHelper.addToolMaterial("Harmonion", 3, 3000, 40.0F, 3, 9);
	static EnumArmorMaterial harmonionarmor = EnumHelper.addArmorMaterial("Harmonion", 30, new int[]{3, 8, 6, 3}, 9);
	
	public static void init() {
		
        /* Initialize each mod item individually */
		HarmonionSword = new ItemHarmonionSword(ItemIds.HARMONIONSWORD, harmoniontool).setIconIndex(10).setItemName(Harmonicsword).setCreativeTab(CreativeTabs.tabCombat);
		Refinedsoundstone = new ItemHarmonionIngot(ItemIds.REFINEDSOUNDSTONE).setIconIndex(0).setItemName(Soundstoneingot).setCreativeTab(CreativeTabs.tabMaterials);
		Harmonionpearl = new ItemHarmonionPearl(ItemIds.HARMONIONPEARL).setIconIndex(18).setItemName(Soundstonepearl).setCreativeTab(CreativeTabs.tabMisc);
		Harmonionpick = new ItemHarmonionPick(ItemIds.HARMONIONPICK, harmoniontool).setIconIndex(12).setItemName(Soundstonepick).setCreativeTab(CreativeTabs.tabTools);
		Harmonionaxe = new ItemHarmonionAxe(ItemIds.HARMONIONAXE, harmoniontool).setIconIndex(13).setItemName(Soundstoneaxe).setCreativeTab(CreativeTabs.tabTools);
		Harmonionshovel = new ItemHarmonionShovel(ItemIds.HARMONIONSHOVEL, harmoniontool).setIconIndex(11).setItemName(Soundstoneshovel).setCreativeTab(CreativeTabs.tabTools);
		Harmonionhoe = new ItemHarmonionHoe(ItemIds.HARMONIONHOE, harmoniontool).setIconIndex(14).setItemName(Soundstonehoe).setCreativeTab(CreativeTabs.tabTools);
		Harmonionhelmet = new ItemHarmonionArmor(ItemIds.HARMONIONHELMET, 6, harmonionarmor, ModLoader.addArmor("Harmonioncraft/client/armor/soundstone"), 0).setIconIndex(6).setItemName(Soundstonehelmet);
		Harmonionchestplate = new ItemHarmonionArmor(ItemIds.HARMONIONCHESTPLATE, 7, harmonionarmor, ModLoader.addArmor("Harmonioncraft/client/armor/soundstone"), 1).setIconIndex(7).setItemName(Soundstonechestplate);
		Harmonionlegs = new ItemHarmonionArmor(ItemIds.HARMONIONLEGGINGS, 8, harmonionarmor, ModLoader.addArmor("Harmonioncraft/client/armor/soundstone"), 2).setIconIndex(8).setItemName(Soundstonelegs);
		Harmonionboots = new ItemHarmonionArmor(ItemIds.HARMONIONBOOTS, 9, harmonionarmor, ModLoader.addArmor("Harmonioncraft/client/armor/soundstone"), 3).setIconIndex(9).setItemName(Soundstoneboots);
		HarmonionDoor = new ItemHarmonionDoor(ItemIds.HARMONIONDOOR).setItemName(Soundstonedoor).setIconIndex(17);
		
		/* Gives Item its name */
		/** Removed */
		
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
		
		/*Soundstone Armor Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionhelmet), 
				new Object[]{"hhh","h h","   ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionchestplate), 
				new Object[]{"h h","hhh","hhh", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionlegs), 
				new Object[]{"hhh","h h","h h", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionboots), 
				new Object[]{"   ","h h","h h", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		
	}
	
	private static void initItemSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonion Pearl Smelting. */
		furnaceRecipes.addSmelting(ModItems.Harmonionpearl.shiftedIndex, new ItemStack(ModItems.Refinedsoundstone, 10), 10);
	}
}

package net.Harmonioncraft.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.Harmonioncraft.core.creativetabs.CreativeTabHarmonionI;
import net.Harmonioncraft.lib.ItemIds;
import net.Harmonioncraft.mods.Harmonioncraft;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
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
	public static final String Sound_Stone_Sword_Name = "Harmonicsword";
	public static final String Sound_Stone_Ingot_Name = "Refinedsoundstone";
	public static final String Sound_Stone_Pearl_Name = "Soundstonepearl";
	public static final String Sound_Stone_Pickaxe_Name = "Soundstonepick";
	public static final String Sound_Stone_Axe_Name = "Soundstoneaxe";
	public static final String Sound_Stone_Shovel_Name = "Soundstoneshovel";
	public static final String Sound_Stone_Hoe_Name = "Soundstonehoe";
	public static final String Sound_Stone_Helmet_Name = "Soundstonehelmet";
	public static final String Sound_Stone_Chestplate_Name = "Soundstonechestplate";
	public static final String Sound_Stone_Leggings_Name = "Soundstonelegs";
	public static final String Sound_Stone_Boots_Name = "Soundstoneboots";
	public static final String Sound_Stone_Powder_Name = "Resoniumpowder";
	public static final String Sound_Stone_Chip_Name = "Soundstonechip";
	public static final String Sound_Stone_Door_Name = "Soundstonedoor";
	public static final String Sound_Stone_Wire_Name = "Soundstonewire";
	
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
	
	public static final CreativeTabs tabHarmonioncraftI = new CreativeTabHarmonionI(18, "HarmonioncraftI");
	
	public static void init() {
		
        /* Initialize each mod item individually */
		HarmonionSword = new ItemHarmonionSword(ItemIds.Harmonion_Sword, harmoniontool).setIconIndex(7).setItemName(Sound_Stone_Sword_Name).setCreativeTab(ModItems.tabHarmonioncraftI);
		Refinedsoundstone = new ItemHarmonionIngot(ItemIds.Harmonion_Ingot).setIconIndex(0).setItemName(Sound_Stone_Ingot_Name).setCreativeTab(ModItems.tabHarmonioncraftI);
		Harmonionpearl = new ItemHarmonionPearl(ItemIds.Harmonion_Pearl).setIconIndex(15).setItemName(Sound_Stone_Pearl_Name).setCreativeTab(ModItems.tabHarmonioncraftI);
		Harmonionpick = new ItemHarmonionPick(ItemIds.Harmonion_Pickaxe, harmoniontool).setIconIndex(9).setItemName(Sound_Stone_Pickaxe_Name).setCreativeTab(ModItems.tabHarmonioncraftI);
		Harmonionaxe = new ItemHarmonionAxe(ItemIds.Harmonion_Axe, harmoniontool).setIconIndex(10).setItemName(Sound_Stone_Axe_Name).setCreativeTab(ModItems.tabHarmonioncraftI);
		Harmonionshovel = new ItemHarmonionShovel(ItemIds.Harmonion_Shovel, harmoniontool).setIconIndex(8).setItemName(Sound_Stone_Shovel_Name).setCreativeTab(ModItems.tabHarmonioncraftI);
		Harmonionhoe = new ItemHarmonionHoe(ItemIds.Harmonion_Hoe, harmoniontool).setIconIndex(11).setItemName(Sound_Stone_Hoe_Name).setCreativeTab(ModItems.tabHarmonioncraftI);
		Harmonionhelmet = new ItemHarmonionArmor(ItemIds.Harmonion_Helmet, 6, harmonionarmor, Harmonioncraft.proxy.addArmor("Harmonioncraft/client/armor/soundstone"), 0).setIconIndex(3).setItemName(Sound_Stone_Helmet_Name);
		Harmonionchestplate = new ItemHarmonionArmor(ItemIds.Harmonion_Chestplate, 7, harmonionarmor, Harmonioncraft.proxy.addArmor("Harmonioncraft/client/armor/soundstone"), 1).setIconIndex(4).setItemName(Sound_Stone_Chestplate_Name);
		Harmonionlegs = new ItemHarmonionArmor(ItemIds.Harmonion_Leggings, 8, harmonionarmor, Harmonioncraft.proxy.addArmor("Harmonioncraft/client/armor/soundstone"), 2).setIconIndex(5).setItemName(Sound_Stone_Leggings_Name);
		Harmonionboots = new ItemHarmonionArmor(ItemIds.Harmonion_Boots, 9, harmonionarmor, Harmonioncraft.proxy.addArmor("Harmonioncraft/client/armor/soundstone"), 3).setIconIndex(6).setItemName(Sound_Stone_Boots_Name);
		HarmonionDoor = new ItemHarmonionDoor(ItemIds.Harmonion_Door).setItemName(Sound_Stone_Door_Name).setIconIndex(14);
		HarmonionDoor = (new ItemRecords(ItemIds.Harmonion_Door + 1, "HMC.WolfM", "State of the art")).setIconIndex(11).setItemName("record");
		
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

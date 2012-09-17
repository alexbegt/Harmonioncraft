package Harmonioncraft.common.item;

import Harmonioncraft.common.lib.ItemIds;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
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
	
	static EnumToolMaterial harmoniontool = EnumHelper.addToolMaterial("Harmonion", 3, 100, 40.0F, 3, 9);
	
	public static void init() {
		
        /* Initialize each mod item individually */
		HarmonionSword = new ItemHarmonionSword(ItemIds.HARMONIONSWORD, harmoniontool).setIconCoord(0,4).setItemName(Harmonicsword).setTabToDisplayOn(CreativeTabs.tabCombat);
		Refinedsoundstone = new ItemHarmonionIngot(ItemIds.REFINEDSOUNDSTONE).setIconCoord(6, 1).setItemName(Soundstoneingot).setTabToDisplayOn(CreativeTabs.tabMaterials);
		Harmonionpearl = new ItemHarmonionPearl(ItemIds.HARMONIONPEARL).setIconCoord(11, 6).setItemName(Soundstonepearl).setTabToDisplayOn(CreativeTabs.tabMisc);
		Harmonionpick = new ItemHarmonionPick(ItemIds.HARMONIONPICK, harmoniontool).setIconCoord(0,6).setItemName(Soundstonepick).setTabToDisplayOn(CreativeTabs.tabTools);
		Harmonionaxe = new ItemHarmonionAxe(ItemIds.HARMONIONAXE, harmoniontool).setIconCoord(0,7).setItemName(Soundstoneaxe).setTabToDisplayOn(CreativeTabs.tabTools);
		Harmonionshovel = new ItemHarmonionShovel(ItemIds.HARMONIONSHOVEL, harmoniontool).setIconCoord(0,5).setItemName(Soundstoneshovel).setTabToDisplayOn(CreativeTabs.tabTools);
		Harmonionhoe = new ItemHarmonionHoe(ItemIds.HARMONIONHOE, harmoniontool).setIconCoord(0,8).setItemName(Soundstonehoe).setTabToDisplayOn(CreativeTabs.tabTools);
		
		/* Gives Item its name */
		LanguageRegistry.addName(HarmonionSword, "Harmonic Sword");
		LanguageRegistry.addName(Refinedsoundstone, "Refined Soundstone");
		LanguageRegistry.addName(Harmonionpearl, "Soundstone Pearl");
		LanguageRegistry.addName(Harmonionpick, "Soundstone Pickaxe");
		LanguageRegistry.addName(Harmonionaxe, "Soundstone Axe");
		LanguageRegistry.addName(Harmonionshovel, "Soundstone Shovel");
		LanguageRegistry.addName(Harmonionhoe, "Soundstone Hoe");
		
	}
}

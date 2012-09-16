package Harmonioncraft.common.item;

import Harmonioncraft.common.lib.ItemIds;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

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
	
	/* Mod item instances */
	public static Item HarmonionSword;
	
	public static void init() {
		
        /* Initialize each mod item individually */
		HarmonionSword = new ItemHarmonionSword(ItemIds.HARMONIONSWORD).setIconCoord(0,0).setItemName(Harmonicsword).setTabToDisplayOn(CreativeTabs.tabCombat);
		
		LanguageRegistry.addName(HarmonionSword, "Harmonic Sword");
		
	}
}

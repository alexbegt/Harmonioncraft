package Harmonioncraft.common.core.handlers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Harmonioncraft.common.block.ModBlocks;
import Harmonioncraft.common.item.ModItems;

/**
 * AddonHandler
 * 
 * Takes care of initializing of addons to the mod. Occurs after all mods are
 * loaded
 * 
 * @author Alexbegt
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class AddonHandler {

    public static void init() {
    	
    	String minorMods = "";
        try {
          Class modPortalGun = Class.forName("portalgun.common.PortalGun");
          Method addBlockIDToGrabListMeta = modPortalGun.getMethod("addBlockIDToGrabList", new Class[] { Integer.TYPE, int[].class });
          Method addBlockIDToGrabList = modPortalGun.getMethod("addBlockIDToGrabList", new Class[] { Integer.TYPE });
          addBlockIDToGrabList.invoke(null, new Object[] { Integer.valueOf(ModBlocks.HarmonionBlock.blockID) });
          minorMods = new StringBuilder().append(minorMods).append(", Portal Gun").toString(); } catch (Throwable e) {
        }
        /**try {
          Method addCustomItem = Class.forName("mod_Gibbing").getMethod("addCustomItem", new Class[] { Integer.TYPE, Double.TYPE });
          addCustomItem.invoke(null, new Object[] { Integer.valueOf(Ic2Items.nanoSaber.c), Double.valueOf(0.5D) });
          addCustomItem.invoke(null, new Object[] { Integer.valueOf(Ic2Items.chainsaw.c), Double.valueOf(0.5D) });
          addCustomItem.invoke(null, new Object[] { Integer.valueOf(Ic2Items.miningDrill.c), Double.valueOf(0.333D) });
          addCustomItem.invoke(null, new Object[] { Integer.valueOf(Ic2Items.diamondDrill.c), Double.valueOf(0.333D) });
          minorMods = new StringBuilder().append(minorMods).append(", Mob Amputation").toString(); } catch (Throwable e) {
        }*/
        try {
          Field axes = Class.forName("mod_Timber").getDeclaredField("axes");
          axes.set(null, new StringBuilder().append(axes.get(null)).append(", ").append(ModItems.Harmonionaxe.shiftedIndex).append(", ").append(ModItems.Harmonionaxe.shiftedIndex).toString());
          minorMods = new StringBuilder().append(minorMods).append(", Timber").toString(); } catch (Throwable e) {
        }
        try {
          Field idList = Class.forName("mod_treecapitator").getDeclaredField("IDList");
          idList.set(null, new StringBuilder().append(idList.get(null)).append("; ").append(ModItems.Harmonionaxe.shiftedIndex).append("; ").append(ModItems.Harmonionaxe.shiftedIndex).toString());
          minorMods = new StringBuilder().append(minorMods).append(", Treecapitator").toString(); } catch (Throwable e) {
        }
        System.out.println(new StringBuilder().append("[HMC] Loaded minor compatibility modules: ").append(minorMods.isEmpty() ? "none" : minorMods.substring(2)).toString());
        
    }

}
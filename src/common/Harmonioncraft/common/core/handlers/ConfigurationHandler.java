package Harmonioncraft.common.core.handlers;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import Harmonioncraft.common.item.ModItems;
import Harmonioncraft.common.lib.BlockIds;
import Harmonioncraft.common.lib.ConfigurationSettings;
import Harmonioncraft.common.lib.ItemIds;
import Harmonioncraft.common.lib.Reference;
import net.minecraftforge.common.Configuration;
import static net.minecraftforge.common.Configuration.*;

/**
 * ConfigurationManager
 * 
 * Loads in configuration data from disk
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ConfigurationHandler {

    public static void init(File configFile) {
        Configuration configuration = new Configuration(configFile);

        try {
            configuration.load();

            /* General Configs */
            ConfigurationSettings.ENABLE_SOUNDS = configuration.getOrCreateBooleanProperty(Reference.ENABLE_SOUNDS, CATEGORY_GENERAL, ConfigurationSettings.ENABLE_SOUNDS_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_SOUNDS_DEFAULT);
            ConfigurationSettings.ENABLE_PARTICLE_FX = configuration.getOrCreateBooleanProperty(Reference.ENABLE_PARTICLE_FX, CATEGORY_GENERAL, ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT);

            /* Block Configs */
            ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS = configuration.getOrCreateBooleanProperty(Reference.AUTO_RESOLVE_BLOCK_IDS, CATEGORY_BLOCK, ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT).getBoolean(ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT);

            /* Item Configs */
            ItemIds.HARMONIONSWORD = configuration.getOrCreateIntProperty(ModItems.Harmonicsword, CATEGORY_ITEM, ItemIds.HARMONIONSWORD_DEFAULT).getInt(ItemIds.HARMONIONSWORD_DEFAULT);
            ItemIds.REFINEDSOUNDSTONE = configuration.getOrCreateIntProperty(ModItems.Soundstoneingot, CATEGORY_ITEM, ItemIds.REFINEDSOUNDSTONE_DEFAULT).getInt(ItemIds.REFINEDSOUNDSTONE_DEFAULT);
            ItemIds.HARMONIONPEARL = configuration.getOrCreateIntProperty(ModItems.Soundstonepearl, CATEGORY_ITEM, ItemIds.HARMONIONPEARL_DEFAULT).getInt(ItemIds.HARMONIONPEARL_DEFAULT);
            ItemIds.HARMONIONPICK = configuration.getOrCreateIntProperty(ModItems.Soundstonepick, CATEGORY_ITEM, ItemIds.HARMONIONPICK_DEFAULT).getInt(ItemIds.HARMONIONPICK_DEFAULT);
            ItemIds.HARMONIONAXE = configuration.getOrCreateIntProperty(ModItems.Soundstoneaxe, CATEGORY_ITEM, ItemIds.HARMONIONAXE_DEFAULT).getInt(ItemIds.HARMONIONAXE_DEFAULT);
            ItemIds.HARMONIONSHOVEL = configuration.getOrCreateIntProperty(ModItems.Soundstoneshovel, CATEGORY_ITEM, ItemIds.HARMONIONSHOVEL_DEFAULT).getInt(ItemIds.HARMONIONSHOVEL_DEFAULT);
            ItemIds.HARMONIONHOE = configuration.getOrCreateIntProperty(ModItems.Soundstonehoe, CATEGORY_ITEM, ItemIds.HARMONIONHOE_DEFAULT).getInt(ItemIds.HARMONIONHOE_DEFAULT);
            
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
}
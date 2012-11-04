package net.HarmonionCarts.core.handlers;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import net.HarmonionCarts.lib.ConfigurationSettings;
import net.HarmonionCarts.lib.Reference;
import net.Harmonioncraft.mods.Harmonioncraft;
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

    private static final String CATEGORY_KEYBIND = "keybinds";

    public static void init(File configFile) {
        Configuration configuration = new Configuration(configFile);

        try {
            configuration.load();

            /* General Configs */
            ConfigurationSettings.ENABLE_VERSION_CHECK = configuration
            		.get(CATEGORY_GENERAL, Reference.ENABLE_VERSION_CHECK, ConfigurationSettings.ENABLE_VERSION_CHECK_DEFAULT)
            		.getBoolean(ConfigurationSettings.ENABLE_VERSION_CHECK_DEFAULT);
            ConfigurationSettings.ENABLE_SOUNDS = configuration
            		.get(CATEGORY_GENERAL, Reference.ENABLE_SOUNDS, ConfigurationSettings.ENABLE_SOUNDS_DEFAULT)
            		.getBoolean(ConfigurationSettings.ENABLE_SOUNDS_DEFAULT);
            ConfigurationSettings.ENABLE_PARTICLE_FX = configuration
            		.get(CATEGORY_GENERAL, Reference.ENABLE_PARTICLE_FX, ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT)
            		.getBoolean(ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT);

            /* Block Configs */
            ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS = configuration
            		.get(CATEGORY_BLOCK, Reference.AUTO_RESOLVE_BLOCK_IDS, ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT)
            		.getBoolean(ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT);
            
            /* KeyBinding Configs */
            configuration.addCustomCategoryComment(CATEGORY_KEYBIND, "");
            
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
}
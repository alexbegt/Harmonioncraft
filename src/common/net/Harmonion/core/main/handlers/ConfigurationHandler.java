package net.Harmonion.core.main.handlers;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import net.Harmonion.core.item.ModItems;
import net.Harmonion.core.block.ModBlocks;
import net.Harmonion.core.lib.BlockIds;
import net.Harmonion.core.lib.ConfigurationSettings;
import net.Harmonion.core.lib.ItemIds;
import net.Harmonion.core.lib.Reference;
import net.Harmonion.core.lib.Strings;
import net.Harmonion.Harmonion;
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
            		.get(CATEGORY_GENERAL, Reference.ENABLE_VERSION_NOTICE, ConfigurationSettings.ENABLE_VERSION_CHECK_DEFAULT)
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
            BlockIds.Harmonion =  configuration
            		.getBlock(Strings.Sound_Stone_Ore_Name, BlockIds.Harmonion_Default)
            		.getInt(BlockIds.Harmonion_Default);
            BlockIds.Harmonion_Log =  configuration
            		.getBlock(Strings.Sound_Stone_Log_Name, BlockIds.Harmonion_Log_Default)
            		.getInt(BlockIds.Harmonion_Log_Default);
            BlockIds.Harmonion_Leaves = configuration
            		.getBlock(Strings.Sound_Stone_Leaves_Name, BlockIds.Harmonion_Leaves_Default)
            		.getInt(BlockIds.Harmonion_Leaves_Default);
            BlockIds.Harmonion_Sapling =  configuration
            		.getBlock(Strings.Sound_Stone_Sapling_Name, BlockIds.Harmonion_Sapling_Default)
            		.getInt(BlockIds.Harmonion_Default);
            BlockIds.Harmonion_Door = configuration
            		.getBlock(Strings.Sound_Stone_Door_Name, BlockIds.Harmonion_Door_Default)
            		.getInt(BlockIds.Harmonion_Door_Default);
            BlockIds.Harmonion_Glass =  configuration
            		.getBlock(Strings.Sound_Stone_Glass_Name, BlockIds.Harmonion_Glass_Default)
            		.getInt(BlockIds.Harmonion_Glass_Default);

            /* Item Configs */
            ItemIds.Harmonion_Sword =  configuration
            		.getItem(Strings.Sound_Stone_Sword_Name, ItemIds.Harmonion_Sword_Default)
            		.getInt(ItemIds.Harmonion_Sword_Default);
            ItemIds.Harmonion_Ingot = configuration
            		.getItem(Strings.Sound_Stone_Ingot_Name, ItemIds.Harmonion_Ingot_Default)
            		.getInt(ItemIds.Harmonion_Ingot_Default);
            ItemIds.Harmonion_Pearl =  configuration
            		.getItem(Strings.Sound_Stone_Pearl_Name, ItemIds.Harmonion_Pearl_Default)
            		.getInt(ItemIds.Harmonion_Pearl_Default);
            ItemIds.Harmonion_Pickaxe = configuration
            		.getItem(Strings.Sound_Stone_Pickaxe_Name, ItemIds.Harmonion_Pickaxe_Default)
            		.getInt(ItemIds.Harmonion_Pickaxe_Default);
            ItemIds.Harmonion_Axe =  configuration
            		.getItem(Strings.Sound_Stone_Axe_Name, ItemIds.Harmonion_Axe_Default)
            		.getInt(ItemIds.Harmonion_Axe_Default);
            ItemIds.Harmonion_Shovel = configuration
            		.getItem(Strings.Sound_Stone_Shovel_Name, ItemIds.Harmonion_Shovel_Default)
            		.getInt(ItemIds.Harmonion_Shovel_Default);
            ItemIds.Harmonion_Hoe =  configuration
            		.getItem(Strings.Sound_Stone_Hoe_Name, ItemIds.Harmonion_Hoe_Default)
            		.getInt(ItemIds.Harmonion_Hoe_Default);
            ItemIds.Harmonion_Helmet = configuration
            		.getItem(Strings.Sound_Stone_Helmet_Name, ItemIds.Harmonion_Helmet_Default)
            		.getInt(ItemIds.Harmonion_Helmet_Default);
            ItemIds.Harmonion_Chestplate =  configuration
            		.getItem(Strings.Sound_Stone_Chestplate_Name, ItemIds.Harmonion_Chestplate_Default)
            		.getInt(ItemIds.Harmonion_Chestplate_Default);
            ItemIds.Harmonion_Leggings = configuration
            		.getItem(Strings.Sound_Stone_Leggings_Name, ItemIds.Harmonion_Leggings_Default)
            		.getInt(ItemIds.Harmonion_Leggings_Default);
            ItemIds.Harmonion_Boots =  configuration
            		.getItem(Strings.Sound_Stone_Boots_Name, ItemIds.Harmonion_Boots_Default)
            		.getInt(ItemIds.Harmonion_Boots_Default);
            ItemIds.Harmonion_Door = configuration
            		.getItem(Strings.Sound_Stone_Door_Name, ItemIds.Harmonion_Door_Default)
            		.getInt(ItemIds.Harmonion_Door_Default);
            
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
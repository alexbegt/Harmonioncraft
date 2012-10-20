package Harmonioncraft.common.core.handlers;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import Harmonioncraft.common.block.ModBlocks;
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
            BlockIds.Harmonion =  configuration
            		.getBlock(ModBlocks.Sound_Stone_Ore_Name, BlockIds.Harmonion_Default)
            		.getInt(BlockIds.Harmonion_Default);
            BlockIds.Harmonion_Portal = configuration
            		.getBlock(ModBlocks.Sound_Stone_Portal_Name, BlockIds.Harmonion_Portal_Default)
            		.getInt(BlockIds.Harmonion_Portal_Default);
            
            BlockIds.Harmonion_Block =  configuration
            		.getBlock(ModBlocks.Sound_Stone_Block_Name, BlockIds.Harmonion_Block_Default)
            		.getInt(BlockIds.Harmonion_Block_Default);
            BlockIds.Harmonion_Fire = configuration
            		.getBlock(ModBlocks.Sound_Stone_Fire_Name, BlockIds.Harmonion_Fire_Default)
            		.getInt(BlockIds.Harmonion_Fire_Default);
            BlockIds.Harmonion_Log =  configuration
            		.getBlock(ModBlocks.Sound_Stone_Log_Name, BlockIds.Harmonion_Log_Default)
            		.getInt(BlockIds.Harmonion_Log_Default);
            BlockIds.Harmonion_Leaves = configuration
            		.getBlock(ModBlocks.Sound_Stone_Leaves_Name, BlockIds.Harmonion_Leaves_Default)
            		.getInt(BlockIds.Harmonion_Leaves_Default);
            BlockIds.Harmonion_Sapling =  configuration
            		.getBlock(ModBlocks.Sound_Stone_Sapling_Name, BlockIds.Harmonion_Sapling_Default)
            		.getInt(BlockIds.Harmonion_Default);
            BlockIds.Harmonion_Door = configuration
            		.getBlock(ModBlocks.Sound_Stone_Door_Name, BlockIds.Harmonion_Door_Default)
            		.getInt(BlockIds.Harmonion_Door_Default);
            BlockIds.Harmonion_Plank =  configuration
            		.getBlock(ModBlocks.Sound_Stone_Plank_Name, BlockIds.Harmonion_Planks_Default)
            		.getInt(BlockIds.Harmonion_Planks_Default);
            BlockIds.Harmonion_Wire = configuration
            		.getBlock(ModBlocks.Sound_Stone_Wire_Name, BlockIds.Harmonion_Wire_Default)
            		.getInt(BlockIds.Harmonion_Wire_Default);

            /* Item Configs */
            ItemIds.Harmonion_Sword =  configuration
            		.getItem(ModItems.Sound_Stone_Sword_Name, ItemIds.Harmonion_Sword_Default)
            		.getInt(ItemIds.Harmonion_Sword_Default);
            ItemIds.Harmonion_Ingot = configuration
            		.getItem(ModItems.Sound_Stone_Ingot_Name, ItemIds.Harmonion_Ingot_Default)
            		.getInt(ItemIds.Harmonion_Ingot_Default);
            ItemIds.Harmonion_Pearl =  configuration
            		.getItem(ModItems.Sound_Stone_Pearl_Name, ItemIds.Harmonion_Pearl_Default)
            		.getInt(ItemIds.Harmonion_Pearl_Default);
            ItemIds.Harmonion_Pickaxe = configuration
            		.getItem(ModItems.Sound_Stone_Pickaxe_Name, ItemIds.Harmonion_Pickaxe_Default)
            		.getInt(ItemIds.Harmonion_Pickaxe_Default);
            ItemIds.Harmonion_Axe =  configuration
            		.getItem(ModItems.Sound_Stone_Axe_Name, ItemIds.Harmonion_Axe_Default)
            		.getInt(ItemIds.Harmonion_Axe_Default);
            ItemIds.Harmonion_Shovel = configuration
            		.getItem(ModItems.Sound_Stone_Shovel_Name, ItemIds.Harmonion_Shovel_Default)
            		.getInt(ItemIds.Harmonion_Shovel_Default);
            ItemIds.Harmonion_Hoe =  configuration
            		.getItem(ModItems.Sound_Stone_Hoe_Name, ItemIds.Harmonion_Hoe_Default)
            		.getInt(ItemIds.Harmonion_Hoe_Default);
            ItemIds.Harmonion_Helmet = configuration
            		.getItem(ModItems.Sound_Stone_Helmet_Name, ItemIds.Harmonion_Helmet_Default)
            		.getInt(ItemIds.Harmonion_Helmet_Default);
            ItemIds.Harmonion_Chestplate =  configuration
            		.getItem(ModItems.Sound_Stone_Chestplate_Name, ItemIds.Harmonion_Chestplate_Default)
            		.getInt(ItemIds.Harmonion_Chestplate_Default);
            ItemIds.Harmonion_Leggings = configuration
            		.getItem(ModItems.Sound_Stone_Leggings_Name, ItemIds.Harmonion_Leggings_Default)
            		.getInt(ItemIds.Harmonion_Leggings_Default);
            ItemIds.Harmonion_Boots =  configuration
            		.getItem(ModItems.Sound_Stone_Boots_Name, ItemIds.Harmonion_Boots_Default)
            		.getInt(ItemIds.Harmonion_Boots_Default);
            ItemIds.Harmonion_Door = configuration
            		.getItem(ModItems.Sound_Stone_Door_Name, ItemIds.Harmonion_Door_Default)
            		.getInt(ItemIds.Harmonion_Door_Default);
            
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
	/**
    public static void init(File configFile) {
        Configuration configuration = new Configuration(configFile);

        try {
            configuration.load();

            /* General Configs *
            ConfigurationSettings.ENABLE_SOUNDS = configuration.getOrCreateBooleanProperty(Reference.ENABLE_SOUNDS, CATEGORY_GENERAL, ConfigurationSettings.ENABLE_SOUNDS_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_SOUNDS_DEFAULT);
            ConfigurationSettings.ENABLE_PARTICLE_FX = configuration.getOrCreateBooleanProperty(Reference.ENABLE_PARTICLE_FX, CATEGORY_GENERAL, ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT).getBoolean(ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT);
            ConfigurationSettings.ENABLE_VERSION_CHECK = configuration
            		.get(CATEGORY_GENERAL, Reference.ENABLE_VERSION_CHECK, ConfigurationSettings.ENABLE_VERSION_CHECK_DEFAULT)
            		.getBoolean(ConfigurationSettings.ENABLE_VERSION_CHECK_DEFAULT);

            /* Block Configs *
            ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS = configuration.getOrCreateBooleanProperty(Reference.AUTO_RESOLVE_BLOCK_IDS, CATEGORY_BLOCK, ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT).getBoolean(ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT);
            BlockIds.HARMONION = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneore, CATEGORY_BLOCK, BlockIds.HARMONION_DEFAULT).getInt(BlockIds.HARMONION_DEFAULT);
            BlockIds.HARMONIONPORTAL = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneportal, CATEGORY_BLOCK, BlockIds.HARMONIONPORTAL_DEFAULT).getInt(BlockIds.HARMONIONPORTAL_DEFAULT);
            BlockIds.HARMONIONBLOCK = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneblock, CATEGORY_BLOCK, BlockIds.HARMONIONBLOCK_DEFAULT).getInt(BlockIds.HARMONIONBLOCK_DEFAULT);
            BlockIds.HARMONIONFIRE = configuration.getOrCreateIntProperty(ModBlocks.Soundstonefire, CATEGORY_BLOCK, BlockIds.HARMONIONFIRE_DEFAULT).getInt(BlockIds.HARMONIONFIRE_DEFAULT);
            BlockIds.HARMONIONLOG = configuration.getOrCreateIntProperty(ModBlocks.Soundstonelog, CATEGORY_BLOCK, BlockIds.HARMONIONLOG_DEFAULT).getInt(BlockIds.HARMONIONLOG_DEFAULT);
            BlockIds.HARMONIONLEAVES = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneleaves, CATEGORY_BLOCK, BlockIds.HARMONIONLEAVES_DEFAULT).getInt(BlockIds.HARMONIONLEAVES_DEFAULT);
            BlockIds.HARMONIONSAPLING = configuration.getOrCreateIntProperty(ModBlocks.Soundstonesapling, CATEGORY_BLOCK, BlockIds.HARMONIONSAPLING_DEFAULT).getInt(BlockIds.HARMONIONSAPLING_DEFAULT);
            BlockIds.HARMONIONPLANK = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneplank, CATEGORY_BLOCK, BlockIds.HARMONIONPLANK_DEFAULT).getInt(BlockIds.HARMONIONPLANK_DEFAULT);
            BlockIds.HARMONIONDOOR = configuration.getOrCreateIntProperty(ModBlocks.Soundstonedoor, CATEGORY_BLOCK, BlockIds.HARMONIONDOOR_DEFAULT).getInt(BlockIds.HARMONIONDOOR_DEFAULT);
            BlockIds.HARMONIONWIRE = configuration.getOrCreateIntProperty(ModBlocks.Soundstonewire, CATEGORY_BLOCK, BlockIds.HARMONIONWIRE_DEFAULT).getInt(BlockIds.HARMONIONWIRE_DEFAULT);
            
            /* Item Configs *
            ItemIds.HARMONIONSWORD = configuration.getOrCreateIntProperty(ModItems.Harmonicsword, CATEGORY_ITEM, ItemIds.HARMONIONSWORD_DEFAULT).getInt(ItemIds.HARMONIONSWORD_DEFAULT);
            ItemIds.REFINEDSOUNDSTONE = configuration.getOrCreateIntProperty(ModItems.Soundstoneingot, CATEGORY_ITEM, ItemIds.REFINEDSOUNDSTONE_DEFAULT).getInt(ItemIds.REFINEDSOUNDSTONE_DEFAULT);
            ItemIds.HARMONIONPEARL = configuration.getOrCreateIntProperty(ModItems.Soundstonepearl, CATEGORY_ITEM, ItemIds.HARMONIONPEARL_DEFAULT).getInt(ItemIds.HARMONIONPEARL_DEFAULT);
            ItemIds.HARMONIONPICK = configuration.getOrCreateIntProperty(ModItems.Soundstonepick, CATEGORY_ITEM, ItemIds.HARMONIONPICK_DEFAULT).getInt(ItemIds.HARMONIONPICK_DEFAULT);
            ItemIds.HARMONIONAXE = configuration.getOrCreateIntProperty(ModItems.Soundstoneaxe, CATEGORY_ITEM, ItemIds.HARMONIONAXE_DEFAULT).getInt(ItemIds.HARMONIONAXE_DEFAULT);
            ItemIds.HARMONIONSHOVEL = configuration.getOrCreateIntProperty(ModItems.Soundstoneshovel, CATEGORY_ITEM, ItemIds.HARMONIONSHOVEL_DEFAULT).getInt(ItemIds.HARMONIONSHOVEL_DEFAULT);
            ItemIds.HARMONIONHOE = configuration.getOrCreateIntProperty(ModItems.Soundstonehoe, CATEGORY_ITEM, ItemIds.HARMONIONHOE_DEFAULT).getInt(ItemIds.HARMONIONHOE_DEFAULT);
            ItemIds.HARMONIONHELMET = configuration.getOrCreateIntProperty(ModItems.Soundstonehelmet, CATEGORY_ITEM, ItemIds.HARMONIONHELMET_DEFAULT).getInt(ItemIds.HARMONIONHELMET_DEFAULT);
            ItemIds.HARMONIONCHESTPLATE = configuration.getOrCreateIntProperty(ModItems.Soundstonechestplate, CATEGORY_ITEM, ItemIds.HARMONIONCHESTPLATE_DEFAULT).getInt(ItemIds.HARMONIONCHESTPLATE_DEFAULT);
            ItemIds.HARMONIONLEGGINGS = configuration.getOrCreateIntProperty(ModItems.Soundstonelegs, CATEGORY_ITEM, ItemIds.HARMONIONLEGGINGS_DEFAULT).getInt(ItemIds.HARMONIONLEGGINGS_DEFAULT);
            ItemIds.HARMONIONBOOTS = configuration.getOrCreateIntProperty(ModItems.Soundstoneboots, CATEGORY_ITEM, ItemIds.HARMONIONBOOTS_DEFAULT).getInt(ItemIds.HARMONIONBOOTS_DEFAULT);
            ItemIds.HARMONIONDOOR = configuration.getOrCreateIntProperty(ModItems.Soundstonedoor, CATEGORY_ITEM, ItemIds.HARMONIONDOOR_DEFAULT).getInt(ItemIds.HARMONIONDOOR_DEFAULT);
            
            /* Biome Configs *
            BiomeIds.idBiomeLake = configuration.getOrCreateIntProperty(BiomeIds.idBiomeLake_name, CATEGORY_GENERAL, BiomeIds.idBiomeLake_Default).getInt(BiomeIds.idBiomeLake_Default);
            BiomeIds.idBiomeHMC = configuration.getOrCreateIntProperty(BiomeIds.idBiomeHMC_name, CATEGORY_GENERAL, BiomeIds.idBiomeHMC_Default).getInt(BiomeIds.idBiomeHMC_Default);
            BiomeIds.idBiomeHMCVariant = configuration.getOrCreateIntProperty(BiomeIds.idBiomeHMCVariant_name, CATEGORY_GENERAL, BiomeIds.idBiomeHMCVariant_Default).getInt(BiomeIds.idBiomeHMCVariant_Default);
            BiomeIds.idBiomeHighlands = configuration.getOrCreateIntProperty(BiomeIds.idBiomeHighlands_name, CATEGORY_GENERAL, BiomeIds.idBiomeHighlands_Default).getInt(BiomeIds.idBiomeHighlands_Default);
            BiomeIds.idBiomeMushrooms = configuration.getOrCreateIntProperty(BiomeIds.idBiomeMushrooms_name, CATEGORY_GENERAL, BiomeIds.idBiomeMushrooms_Default).getInt(BiomeIds.idBiomeMushrooms_Default);
            BiomeIds.idBiomeSwamp = configuration.getOrCreateIntProperty(BiomeIds.idBiomeSwamp_name, CATEGORY_GENERAL, BiomeIds.idBiomeSwamp_Default).getInt(BiomeIds.idBiomeSwamp_Default);
            BiomeIds.idBiomeStream = configuration.getOrCreateIntProperty(BiomeIds.idBiomeStream_name, CATEGORY_GENERAL, BiomeIds.idBiomeStream_Default).getInt(BiomeIds.idBiomeStream_Default);
            BiomeIds.idBiomeSnowfield = configuration.getOrCreateIntProperty(BiomeIds.idBiomeSnowfield_name, CATEGORY_GENERAL, BiomeIds.idBiomeSnowfield_Default).getInt(BiomeIds.idBiomeSnowfield_Default);
            BiomeIds.idBiomeGlacier = configuration.getOrCreateIntProperty(BiomeIds.idBiomeGlacier_name, CATEGORY_GENERAL, BiomeIds.idBiomeGlacier_Default).getInt(BiomeIds.idBiomeGlacier_Default);
            BiomeIds.idBiomeClearing = configuration.getOrCreateIntProperty(BiomeIds.idBiomeClearing_name, CATEGORY_GENERAL, BiomeIds.idBiomeClearing_Default).getInt(BiomeIds.idBiomeClearing_Default);
            BiomeIds.idBiomeClearingBorder = configuration.getOrCreateIntProperty(BiomeIds.idBiomeClearingBorder_name, CATEGORY_GENERAL, BiomeIds.idBiomeClearingBorder_Default).getInt(BiomeIds.idBiomeClearingBorder_Default);
            BiomeIds.idBiomeLakeBorder = configuration.getOrCreateIntProperty(BiomeIds.idBiomeLakeBorder_name, CATEGORY_GENERAL, BiomeIds.idBiomeLakeBorder_Default).getInt(BiomeIds.idBiomeLakeBorder_Default);
            BiomeIds.idBiomeDeepMushrooms = configuration.getOrCreateIntProperty(BiomeIds.idBiomeDeepMushrooms_name, CATEGORY_GENERAL, BiomeIds.idBiomeDeepMushrooms_Default).getInt(BiomeIds.idBiomeDeepMushrooms_Default);
            BiomeIds.idBiomeMajorFeature = configuration.getOrCreateIntProperty(BiomeIds.idBiomeMajorFeature_name, CATEGORY_GENERAL, BiomeIds.idBiomeMajorFeature_Default).getInt(BiomeIds.idBiomeMajorFeature_Default);
            BiomeIds.idBiomeMinorFeature = configuration.getOrCreateIntProperty(BiomeIds.idBiomeMinorFeature_name, CATEGORY_GENERAL, BiomeIds.idBiomeMinorFeature_Default).getInt(BiomeIds.idBiomeMinorFeature_Default);
            BiomeIds.idBiomeDarkForest = configuration.getOrCreateIntProperty(BiomeIds.idBiomeDarkForest_name, CATEGORY_GENERAL, BiomeIds.idBiomeDarkForest_Default).getInt(BiomeIds.idBiomeDarkForest_Default);
            BiomeIds.idBiomeEnchantedForest = configuration.getOrCreateIntProperty(BiomeIds.idBiomeEnchantedForest_name, CATEGORY_GENERAL, BiomeIds.idBiomeEnchantedForest_Default).getInt(BiomeIds.idBiomeEnchantedForest_Default);
            BiomeIds.idBiomeFireSwamp = configuration.getOrCreateIntProperty(BiomeIds.idBiomeFireSwamp_name, CATEGORY_GENERAL, BiomeIds.idBiomeFireSwamp_Default).getInt(BiomeIds.idBiomeFireSwamp_Default);
            
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
    */
}
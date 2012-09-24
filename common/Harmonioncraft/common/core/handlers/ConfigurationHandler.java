package Harmonioncraft.common.core.handlers;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import Harmonioncraft.common.block.ModBlocks;
import Harmonioncraft.common.item.ModItems;
import Harmonioncraft.common.lib.BiomeIds;
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
            BlockIds.HARMONION = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneore, CATEGORY_BLOCK, BlockIds.HARMONION_DEFAULT).getInt(BlockIds.HARMONION_DEFAULT);
            BlockIds.HARMONIONPORTAL = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneportal, CATEGORY_BLOCK, BlockIds.HARMONIONPORTAL_DEFAULT).getInt(BlockIds.HARMONIONPORTAL_DEFAULT);
            BlockIds.HARMONIONBLOCK = configuration.getOrCreateIntProperty(ModBlocks.Soundstoneblock, CATEGORY_BLOCK, BlockIds.HARMONIONBLOCK_DEFAULT).getInt(BlockIds.HARMONIONBLOCK_DEFAULT);
            BlockIds.HARMONIONFIRE = configuration.getOrCreateIntProperty(ModBlocks.Soundstonefire, CATEGORY_BLOCK, BlockIds.HARMONIONFIRE_DEFAULT).getInt(BlockIds.HARMONIONFIRE_DEFAULT);
            
            /* Item Configs */
            ItemIds.HARMONIONSWORD = configuration.getOrCreateIntProperty(ModItems.Harmonicsword, CATEGORY_ITEM, ItemIds.HARMONIONSWORD_DEFAULT).getInt(ItemIds.HARMONIONSWORD_DEFAULT);
            ItemIds.REFINEDSOUNDSTONE = configuration.getOrCreateIntProperty(ModItems.Soundstoneingot, CATEGORY_ITEM, ItemIds.REFINEDSOUNDSTONE_DEFAULT).getInt(ItemIds.REFINEDSOUNDSTONE_DEFAULT);
            ItemIds.HARMONIONPEARL = configuration.getOrCreateIntProperty(ModItems.Soundstonepearl, CATEGORY_ITEM, ItemIds.HARMONIONPEARL_DEFAULT).getInt(ItemIds.HARMONIONPEARL_DEFAULT);
            ItemIds.HARMONIONPICK = configuration.getOrCreateIntProperty(ModItems.Soundstonepick, CATEGORY_ITEM, ItemIds.HARMONIONPICK_DEFAULT).getInt(ItemIds.HARMONIONPICK_DEFAULT);
            ItemIds.HARMONIONAXE = configuration.getOrCreateIntProperty(ModItems.Soundstoneaxe, CATEGORY_ITEM, ItemIds.HARMONIONAXE_DEFAULT).getInt(ItemIds.HARMONIONAXE_DEFAULT);
            ItemIds.HARMONIONSHOVEL = configuration.getOrCreateIntProperty(ModItems.Soundstoneshovel, CATEGORY_ITEM, ItemIds.HARMONIONSHOVEL_DEFAULT).getInt(ItemIds.HARMONIONSHOVEL_DEFAULT);
            ItemIds.HARMONIONHOE = configuration.getOrCreateIntProperty(ModItems.Soundstonehoe, CATEGORY_ITEM, ItemIds.HARMONIONHOE_DEFAULT).getInt(ItemIds.HARMONIONHOE_DEFAULT);
            
            /* Biome Configs */
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
}
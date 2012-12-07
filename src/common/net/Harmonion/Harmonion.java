package net.Harmonion;

import java.util.logging.Logger;

import net.Harmonion.core.block.ModBlocks;
import net.Harmonion.core.item.ModItems;
import net.Harmonion.core.lib.ConfigurationSettings;
import net.Harmonion.core.lib.Reference;
import net.Harmonion.core.main.CommonProxy;
import net.Harmonion.core.main.handlers.AchievementPageHandler;
import net.Harmonion.core.main.handlers.AddonHandler;
import net.Harmonion.core.main.handlers.ConfigurationHandler;
import net.Harmonion.core.main.handlers.PacketHandler;
import net.Harmonion.core.main.handlers.LocalizationHandler;
import net.Harmonion.core.main.helper.LogHelper;
import net.Harmonion.core.main.helper.Version;
import net.Harmonion.core.network.MapPacketHandler;
import net.Harmonion.core.recipe.HarmonionRecipe;

import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

/**
 * HarmonionCraft
 * 
 * Main mod class for the Minecraft mod HarmonionCraft
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod
(
		modid = Reference.MOD_ID,
		name = Reference.MOD_NAME,
		version = Reference.VERSION,
		useMetadata = true
)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false, 
        tinyPacketHandler = MapPacketHandler.class, 
        packetHandler = PacketHandler.class
)
public class Harmonion {
	
	@Instance(Reference.MOD_ID)
	public static Harmonion instance;
	
	@SidedProxy(
			clientSide = Reference.CLIENT_PROXY_CLASS,
			serverSide = Reference.SERVER_PROXY_CLASS
	)
    public static CommonProxy proxy;
	
	public static Logger hmcLog = Logger.getLogger(Reference.MOD_NAME);
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {	
		
		// Initialize the log helper
    	LogHelper.init();
    	
    	// Load the localization files into the LanguageRegistry
    	LocalizationHandler.loadLanguages();
		
		// Initialize the configuration
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
        // Conduct the version check and log the result
        if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
        	Version.versionCheck();
        }
        hmcLog.setParent(FMLLog.getLogger());
        hmcLog.info("Starting Harmonioncraft " + Version.getVersion());
        hmcLog.info("Copyright (c) alexbegt, DJ, 2011");
    	
    	// Initialize the Version Check Tick Handler (Client only)
    	proxy.registerTickHander();

        // Register the Sound Handler (Client only)
        proxy.registerSoundHandler();
        
	}
	
	@Init
	public void load(FMLInitializationEvent evt) {
		
		/* Initialize the custom item rarity types */
        proxy.initCustomRarityTypes();

        /* Register the GUI Handler */
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);

        /* Initialize mod blocks */
        ModBlocks.init();
        
        /* Initialize mod items */
        ModItems.init();
        
        /* Initialize mod tile entities */
        proxy.initTileEntities();
        
        /* Initialize custom rendering and pre-load textures (Client only) */
        proxy.initRenderingAndTextures();
        
        // Registering the Achevement Handler
        AchievementPage.registerAchievementPage(new AchievementPageHandler());
        
        proxy.initSounds();
        
        proxy.initEntitys();
        
        proxy.initEntitysClient();
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent evt) {
		
		// Initialize the Addon Handler
        AddonHandler.init(); 
        
        HarmonionRecipe.init();
        
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event.getServer());
	}
	
}

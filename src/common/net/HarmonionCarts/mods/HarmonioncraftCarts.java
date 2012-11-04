package net.HarmonionCarts.mods;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import net.HarmonionCarts.core.CommonProxy;
import net.HarmonionCarts.core.handlers.AchievementPageHandler;
import net.HarmonionCarts.core.handlers.AddonHandler;
import net.HarmonionCarts.core.handlers.ConfigurationHandler;
import net.HarmonionCarts.core.handlers.LocalizationHandler;
import net.HarmonionCarts.core.handlers.VersionCheckTickHandler;
import net.HarmonionCarts.core.helper.LogHelper;
import net.HarmonionCarts.core.helper.VersionHelper;
import net.HarmonionCarts.item.ModItems;
import net.HarmonionCarts.lib.ConfigurationSettings;
import net.HarmonionCarts.lib.Reference;
import net.minecraftforge.common.AchievementPage;

@Mod(
		modid = Reference.MOD_ID,
		name = Reference.MOD_NAME,
		version = Reference.VERSION,
		useMetadata = true,
		dependencies = "required-after:Forge@[6.0.1.332,);after:HMC"
)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false//, 
        //packetHandler = PacketHandler.class
)
public class HarmonioncraftCarts {
	
	@Instance(Reference.MOD_ID)
	public static HarmonioncraftCarts instance;
	
	@SidedProxy(
			clientSide = Reference.CLIENT_PROXY_CLASS,
			serverSide = Reference.SERVER_PROXY_CLASS
	)
    public static CommonProxy proxy;
	
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
        	VersionHelper.checkVersion();
        }
    	VersionHelper.logResult();
    	
    	proxy.registerTickHander();
    	
    	 // Initialize the Version Check Tick Handler (Client only)
        TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);

        // Register the Sound Handler (Client only)
        proxy.registerSoundHandler();
        
	}
	
	@Init
	public void load(FMLInitializationEvent evt) {
		
		/* Initialize the custom item rarity types */
        proxy.initCustomRarityTypes();

        /* Register the GUI Handler */
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        
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
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent evt) {
		
		// Initialize the Addon Handler
        AddonHandler.init(); 
        
        proxy.initEntitysClient();
        
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event.getServer());
	}
	
}

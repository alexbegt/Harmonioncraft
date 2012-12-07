package net.Harmonion;

import net.Harmonion.carts.item.ModItems;
import net.Harmonion.carts.lib.ConfigurationSettings;
import net.Harmonion.carts.lib.Reference;
import net.Harmonion.carts.main.CommonProxy;
import net.Harmonion.carts.main.handlers.AchievementPageHandler;
import net.Harmonion.carts.main.handlers.AddonHandler;
import net.Harmonion.carts.main.handlers.ConfigurationHandler;
import net.Harmonion.carts.main.helper.LogHelper;

import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

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
public class HarmonionCart {
	
	@Instance(Reference.MOD_ID)
	public static HarmonionCart instance;
	
	@SidedProxy(
			clientSide = Reference.CLIENT_PROXY_CLASS,
			serverSide = Reference.SERVER_PROXY_CLASS
	)
    public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {	
		
		// Initialize the log helper
    	LogHelper.init();
		
		// Initialize the configuration
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    	
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
	
}

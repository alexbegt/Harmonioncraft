package net.Harmonion.server;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.item.ModItems;
import net.Harmonion.item.crafting.HarmonionRecipe;
import net.Harmonion.network.MapPacketHandler;
import net.Harmonion.util.CommonProxy;
import net.Harmonion.util.ConfigurationHandler;
import net.Harmonion.util.ItemIds;
import net.Harmonion.util.LocalizationHandler;
import net.Harmonion.util.Reference;
import net.Harmonion.village.VillageManager;
import net.Harmonion.village.VillageManager1;
import net.Harmonion.network.packet.PacketHandler;
import net.Harmonion.power.MicroPlacementWire;
import net.Harmonion.power.Packet211TileDesc;
import net.Harmonion.power.TileBluewire;
import net.minecraft.network.packet.Packet;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * HarmonionCraft
 * 
 * Main mod class for the Minecraft mod HarmonionCraft
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, certificateFingerprint = "28f7f8a775e597088f3a418ea29290b6a1d23c7b")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, tinyPacketHandler = MapPacketHandler.class, packetHandler = PacketHandler.class)
public class Harmonion {
	
	@Instance(Reference.MOD_ID)
	public static Harmonion instance;
	
	@SidedProxy(
			clientSide = Reference.CLIENT_PROXY_CLASS,
			serverSide = Reference.SERVER_PROXY_CLASS
	)
    public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {	
    	
    	// Load the localization files into the LanguageRegistry
    	LocalizationHandler.loadLanguages();
		
		// Initialize the configuration
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    	
    	// Initialize the Version Check Tick Handler (Client only)
    	proxy.registerTickHander();

        // Register the Sound Handler (Client only)
        proxy.registerSoundHandler();
        
	}
	
	@Init
	public void load(FMLInitializationEvent evt) {
		
		Packet.addIdClassMapping(300, true, true, Packet211TileDesc.class);
		
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
        
        VillageManager villageManager = new VillageManager();
        VillagerRegistry.instance().registerVillageCreationHandler(villageManager);
        VillagerRegistry.instance().registerVillagerType(ItemIds.Harmonion_Villager, "/thaumcraft/resources/wizard.png");
        VillagerRegistry.instance().registerVillageTradeHandler(ItemIds.Harmonion_Villager, villageManager);
        
        VillageManager1 villageManager1 = new VillageManager1();
        VillagerRegistry.instance().registerVillageCreationHandler(villageManager1);
        
        /* Initialize custom rendering and pre-load textures (Client only) */
        proxy.initRenderingAndTextures();
        
        // Registering the Achevement Handler
        
        proxy.initSounds();
        
        proxy.initEntitys();
        
        proxy.initEntitysClient();
        
        proxy.initPower();
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent evt) {
		
        HarmonionRecipe.init();
        
        LocalizationHandler.saveLanguages();
        
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event.getServer());
	}
	
}

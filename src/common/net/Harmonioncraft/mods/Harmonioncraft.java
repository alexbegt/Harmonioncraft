package net.Harmonioncraft.mods;

import net.Harmonioncraft.block.ModBlocks;
import net.Harmonioncraft.command.CommandHMCV;
import net.Harmonioncraft.core.CommonProxy;
import net.Harmonioncraft.core.handlers.AchievementPageHandler;
import net.Harmonioncraft.core.handlers.AddonHandler;
import net.Harmonioncraft.core.handlers.ConfigurationHandler;
import net.Harmonioncraft.core.handlers.LocalizationHandler;
import net.Harmonioncraft.core.handlers.VersionCheckTickHandler;
import net.Harmonioncraft.core.helper.LogHelper;
import net.Harmonioncraft.core.helper.VersionHelper;
import net.Harmonioncraft.item.ModItems;
import net.Harmonioncraft.lib.ConfigurationSettings;
import net.Harmonioncraft.lib.EntityLib;
import net.Harmonioncraft.lib.Reference;
import net.Harmonioncraft.network.MapPacketHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.CommandHandler;
import net.minecraft.src.EntityEggInfo;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraft.src.WorldServer;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ForgeSubscribe;
import net.Harmonioncraft.core.handlers.PacketHandler;
import net.Harmonioncraft.entity.EntityHarmonionWolf;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent.Save;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkMod.NULL;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
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
public class Harmonioncraft {
	
	@Instance(Reference.MOD_ID)
	public static Harmonioncraft instance;
	
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
    	
    	// Initialize the Version Check Tick Handler (Client only)
    	proxy.registerTickHander();
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

        /* Initialize mod blocks */
        ModBlocks.init();
        
        /* Initialize mod items */
        ModItems.init();
        
        /* Initialize mod tile entities */
        proxy.initTileEntities();
        
        /* Initialize custom rendering and pre-load textures (Client only) */
        proxy.initRenderingAndTextures();
        
        /* Block Smelting */
        ModBlocks.initBlockSmelting();
        ModBlocks.initBlockRecipes();
        
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
        
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event.getServer());
	}
	
}

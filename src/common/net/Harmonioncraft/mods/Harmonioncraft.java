package net.Harmonioncraft.mods;

import net.Harmonioncraft.block.ModBlocks;
import net.Harmonioncraft.client.network.NetworkManagerClient;
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
import net.Harmonioncraft.lib.Reference;
import net.Harmonioncraft.network.NetworkManager;
import net.Harmonioncraft.world.WorldProviderHarmonion;
import net.Harmonioncraft.world.teleporter.TeleporterHarmonion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.CommandHandler;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraft.src.WorldServer;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ForgeSubscribe;
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
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, useMetadata = true)
@NetworkMod(
        clientSideRequired = true,
        clientPacketHandlerSpec =       @SidedPacketHandler(
                channels = {Reference.CHANNEL_NAME},
                packetHandler = NetworkManagerClient.class
                ),
        serverPacketHandlerSpec =       @SidedPacketHandler(
                channels = {Reference.CHANNEL_NAME},
                packetHandler = NetworkManager.class
                )
)
public class Harmonioncraft {
	
	@Instance(Reference.MOD_ID)
	public static Harmonioncraft instance;
	
	@SidedProxy(
			clientSide = Reference.CLIENT_PROXY_CLASS,
			serverSide = Reference.SERVER_PROXY_CLASS
	)
    public static CommonProxy proxy;
	
	@SidedProxy(
			clientSide = Reference.CLIENT_NETWORK_CLASS,
			serverSide = Reference.SERVER_NETWORK_CLASS
	)
    public static NetworkManager network;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {	
		
		proxy.registerTickHander();
		
		// Initialize the configuration
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
        // Conduct the version check and log the result
        if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
        	VersionHelper.checkVersion();
        }
    	VersionHelper.logResult();
    	
    	// Initialize the log helper
    	LogHelper.init();
    	
    	 // Initialize the Version Check Tick Handler (Client only)
        TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);
		
		// Load the localization files into the LanguageRegistry
    	LocalizationHandler.loadLanguages();

        // Register the KeyBinding Handler (Client only)
        proxy.registerKeyBindingHandler();

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
        
        /* Dimension Adding */
        //DimensionManager.registerProviderType(8, WorldProviderHarmonioncraft.class, true);
        //DimensionManager.registerDimension(8, 8);
        
        DimensionManager.registerProviderType(-128, WorldProviderHarmonion.class, true);
        DimensionManager.registerDimension(-128, -128);
        
        // Registering the Achevement Handler
        AchievementPage.registerAchievementPage(new AchievementPageHandler());
        
        proxy.initSounds();
		
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
	
	public static void teleportPlayerToHarmonion1(EntityPlayerMP var0)
    {
		TeleporterHarmonion var1 = new TeleporterHarmonion();
        ServerConfigurationManager var2 = MinecraftServer.getServer().getConfigurationManager();

        if (var0.dimension == 0)
        {
            var2.transferPlayerToDimension(var0, -128, var1);
        }
        else
        {
            var2.transferPlayerToDimension(var0, 0, var1);
        }
    }

    public void initialWorldChunkLoad(WorldServer var1)
    {
        MinecraftServer var2 = FMLCommonHandler.instance().getMinecraftServerInstance();
        short var3 = 196;
        long var4 = System.currentTimeMillis();
        byte var6 = -127;
        MinecraftServer.logger.info("Preparing start region for level " + var6);
        WorldServer var7 = var1;
        ChunkCoordinates var8 = var1.getSpawnPoint();

        for (int var9 = -var3; var9 <= var3 && var2.isServerRunning(); var9 += 16)
        {
            for (int var10 = -var3; var10 <= var3 && var2.isServerRunning(); var10 += 16)
            {
                long var11 = System.currentTimeMillis();

                if (var11 < var4)
                {
                    var4 = var11;
                }

                if (var11 > var4 + 1000L)
                {
                    int var13 = (var3 * 2 + 1) * (var3 * 2 + 1);
                    int var14 = (var9 + var3) * (var3 * 2 + 1) + var10 + 1;
                    var4 = var11;
                }

                var7.theChunkProviderServer.loadChunk(var8.posX + var9 >> 4, var8.posZ + var10 >> 4);

                while (var7.isDaytime() && var2.isServerRunning())
                {
                }
            }
        }
    }

    public static Object getPrivateValueBoth(Class var0, Object var1, String var2, String var3)
    {
        try
        {
            return ModLoader.getPrivateValue(var0, var1, var2);
        }
        catch (Exception var5)
        {
            var5.printStackTrace();
            return null;
        }
    }

    public static void setPrivateValueBoth(Class var0, Object var1, String var2, String var3, Object var4)
    {
        try
        {
            ModLoader.setPrivateValue(var0, var1, var2, var4);
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
        }
    }
	
}

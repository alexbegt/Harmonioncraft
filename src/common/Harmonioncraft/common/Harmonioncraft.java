package Harmonioncraft.common;

import net.minecraft.src.CommandHandler;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.DimensionManager;
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
import cpw.mods.fml.common.registry.GameRegistry;
import Harmonioncraft.common.block.ModBlocks;
import Harmonioncraft.common.commands.CommandHMCV;
import Harmonioncraft.common.core.CommonProxy;
import Harmonioncraft.common.core.handlers.ConfigurationHandler;
import Harmonioncraft.common.dimension.WorldProviderHarmonioncraft;
import Harmonioncraft.common.item.ModItems;
import Harmonioncraft.common.lib.Reference;
import Harmonioncraft.common.lib.Version;
import Harmonioncraft.common.network.PacketHandler;

/**
 * HarmonionCraft
 * 
 * Main mod class for the Minecraft mod HarmonionCraft
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class Harmonioncraft {
	
	@Instance("Harmonioncraft")
	public static Harmonioncraft instance;
	
	@SidedProxy(clientSide = "Harmonioncraft.client.core.ClientProxy", serverSide = "Harmonioncraft.common.core.CommonProxy")
    public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {	
		
		// Checks the Version
		Version.versionCheck();
		
		// Initialize the configuration
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

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
        
        /* Biome Adding */
        DimensionManager.registerProviderType(8, WorldProviderHarmonioncraft.class, false);
        DimensionManager.registerDimension(8, 8);
		
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent evt) {
		
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event.getServer());
	}
}

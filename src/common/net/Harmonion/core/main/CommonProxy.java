package net.Harmonion.core.main;

import java.util.Iterator;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import net.Harmonion.Harmonion;
import net.Harmonion.core.command.CommandHMCV;
import net.Harmonion.core.entity.EntityHarmonionWolf;
import net.Harmonion.core.lib.EntityLib;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.CommandHandler;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ICommand;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ThreadMinecraftServer;
import net.minecraft.src.World;

/**
 * CommonProxy
 * 
 * The common proxy class between client and server. Client proxy extends this
 * for further client specific functionality
 * 
 * @author alexbegt
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommonProxy implements IGuiHandler {
	
	public void registerTickHander() {
		//TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}
	
	public String getCurrentLanguage() {
		return null;
	}
	
	public String getMinecraftVersion() {
		return Loader.instance().getMinecraftModContainer().getVersion();
	}
	
	/* LOCALIZATION */
	public void addName(Object obj, String s) {}
	public void addLocalization(String s1, String string) {}
	public String getItemDisplayName(ItemStack newStack) { return ""; }

    public void registerSoundHandler() {}

    public void initCustomRarityTypes() {}

    public EnumRarity getCustomRarityType(String customRarity) {
        return null;
    }
    
    public void initSounds() {}
    
    public void initEntitys() {
    	EntityRegistry.registerModEntity(EntityHarmonionWolf.class, "HMCW",
 				3, Harmonion.instance, 80, 3, true);
 		EntityRegistry.addSpawn(EntityHarmonionWolf.class, 5, 5, 5,
 				EnumCreatureType.creature, BiomeGenBase.plains);
 		EntityLib.registerEntityEgg(EntityHarmonionWolf.class, 12698049, 12698049);
    }
    
    public void initEntitysClient() {}
    
    public void initRenderingAndTextures() {}
    
    public void initTileEntities() {
    	
    	//GameRegistry.registerTileEntity(TileCalcinator.class, "tileCalcinator");
    }
    
    public int getBlockTexture(Block var1, IBlockAccess var2, int var3, int var4, int var5, int var6)
    {
        return 0;
    }
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { 
        return null;
    }
    
    public boolean isSimulating()
    {
        return Thread.currentThread() instanceof ThreadMinecraftServer;
    }
    
    public void serverStarting(MinecraftServer var1)
    {
        CommandHandler var2 = (CommandHandler)var1.getCommandManager();
        
        ICommand[] var5 = this.getConsoleCommands();
        {
        	ICommand[] var6 = var5;
        	int var7 = var5.length;
        	for (int var8 = 0; var8 < var7; ++var8)
        	{
        		ICommand var9 = var6[var8];
        		var2.registerCommand(var9);
        	}
        }
    }
    
    public ICommand[] getConsoleCommands()
    {
        return new ICommand[] {new CommandHMCV()};
    }
    
    public World getClientWorld()
    {
        return null;
    }
    public int addArmor(String name) {
        return 0;
      }
}
package net.Harmonioncraft.core;

import java.util.Iterator;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import net.Harmonioncraft.ServerTickHandler;
import net.Harmonioncraft.command.CommandHMCV;
import net.Harmonioncraft.lib.GuiIds;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Block;
import net.minecraft.src.CommandHandler;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ICommand;
import net.minecraft.src.ThreadMinecraftServer;
import net.minecraft.src.World;

/**
 * CommonProxy
 * 
 * The common proxy class between client and server. Client proxy extends this
 * for further client specific functionality
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommonProxy implements IGuiHandler {
	
	public void registerTickHander() {
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}

    public void registerKeyBindingHandler() {}

    public void setKeyBinding(String name, int value) {}

    public void registerSoundHandler() {}

    public void initCustomRarityTypes() {}

    public EnumRarity getCustomRarityType(String customRarity) {
        return null;
    }
    
    public void initSounds() {}
    
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
        /*if (ID == GuiIds.HARMONION_CRAFTING) {
            return new ContainerPortableCrafting(player.inventory, world, x, y, z);
        }*/
        
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        /*if (ID == GuiIds.PORTABLE_CRAFTING) {
            return new GuiPortableCrafting(player, world, x, y, z);
        }*/
        
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